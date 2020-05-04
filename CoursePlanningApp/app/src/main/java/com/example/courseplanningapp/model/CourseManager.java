package com.example.courseplanningapp.model;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.courseplanningapp.R;
import com.example.courseplanningapp.constants.Constants;
import com.example.courseplanningapp.ui.AddCourse;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;;
import java.util.ArrayList;
import java.util.Collections;


/*
    CourseManager class holds all courses_s
    provides a singleton to access the data from
*/
public class CourseManager {
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Course> filteredCourses = null;
    private ArrayList<String> addedCourseId = new ArrayList<>();

    // Singleton
    private static CourseManager instance;

    // read the course data file
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private CourseManager(Context context, String major, String startYear, String startSemester, int courseCount) throws IOException {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.ADDED_COURSE_PREFERENCE_KEY, Context.MODE_PRIVATE);
        String serializedCourses = sharedPreferences.getString(Constants.ADDED_COURSE_KEY, "");
        String[] coursesData = serializedCourses.split(",");
        //String addedC = "";
        for(String courseId : coursesData) {
            if(courseId != null) {
                addedCourseId.add(courseId);
                //addedC += courseId + ",";
            }
        }

        SharedPreferences majorSharedPreferences = context.getSharedPreferences("readMajor", Context.MODE_PRIVATE);
        boolean isMajorRead = majorSharedPreferences.getBoolean("is_major_read", false);


        if (!isMajorRead && major.equals("Computing Science")){
            readCmptData(context, startYear, startSemester, courseCount);
            SharedPreferences.Editor editor = majorSharedPreferences.edit();
            editor.putBoolean("is_major_read", true);
            editor.apply();
        } else { // major == null
            InputStreamReader isr;
            try {
                isr = new InputStreamReader(
                        context.openFileInput(Constants.SAVE_DATA_FILENAME),
                        StandardCharsets.UTF_8);
            } catch (IOException e){
                isr = new InputStreamReader(
                        context.getResources().openRawResource(R.raw.select_courses),
                        StandardCharsets.UTF_8);
            }

            BufferedReader reader = new BufferedReader(isr);

            // populate the data structure
            courses.clear();
            System.out.println(courses);

            String line;
            String year, semester, subject, courseNumber, title;

            // iteratively read the whole file
            while((line = reader.readLine()) != null) {
                //if(!line.equals("Removed")){
                    System.out.println("这一门课是：" + line);
                    String[] courseInfo = line.split(",");
                    System.out.println(courseInfo);
                    year = courseInfo[0];
                    System.out.println(year);
                    semester = courseInfo[1];
                    System.out.println(semester);
                    subject = courseInfo[2];
                    System.out.println(subject);
                    if(subject.equals("Elective ")){
                        courseNumber = "";
                        title = "Elective";
                    }else if (courseInfo.length == 4){
                        courseNumber = courseInfo[3];
                        title = "";
                    } else {
                        courseNumber = courseInfo[3];
                        title = courseInfo[4];
                    }

                    Course course = new Course(year, semester, subject, courseNumber, title);

                    System.out.println("那courseIDlist里有什么？" + addedCourseId.toString());
                    System.out.println("courseId是：" + course.getCourseId());
                    if(addedCourseId.contains(course.getCourseId())){
                        courses.add(course);
                    }

//                    for(String courseId: addedCourseId){
//                        if (course.getCourseId().equals(courseId)){
//                            courses.add(course);
//                        }
//                    }
                //}


            }
        }

        Collections.sort(courses);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void readCmptData(Context context, String startYear, String startSemester, int courseCount) throws IOException {
        InputStreamReader reader;

        reader = new InputStreamReader(context.getResources().openRawResource(R.raw.cmpt), StandardCharsets.UTF_8);

        courses.clear();
        addedCourseId.clear();

        String year, semester = null, subject, courseNumber, title;
        int yearCode, startSemesterCode, semesterCode,i;
        if(startSemester.equals("Spring")){
            startSemesterCode = 0;
            i = -1;
        } else if (startSemester.equals("Summer")){
            startSemesterCode = 1;
            i = -1 + courseCount;
        } else {
            startSemesterCode = 2;
            i = -1 + 2*courseCount;
        }

        CSVReader csvReader = new CSVReader(reader);
        String[] line;
        csvReader.readNext();
        int j = -1;
        while ((line = csvReader.readNext())!=null) {
            i++; j++;
            yearCode = Integer.parseInt(startYear) + i/(3*courseCount);
            year = "" + yearCode;
            semesterCode = startSemesterCode + j/courseCount;
            switch (semesterCode%3){
                case 0:
                    semester = "Spring";
                    break;
                case 1:
                    semester = "Summer";
                    break;
                case 2:
                    semester = "Fall";
                    break;
                default:
                    break;
            }
            subject = line[0];
            courseNumber = line[1];
            title = line[2];
            Course course = new Course(year, semester ,subject, courseNumber, title);
            courses.add(course);
            addedCourseId.add(course.getCourseId());
            System.out.println("来，瞅瞅你是怎么加进去的" + addedCourseId);
            saveCourseIdIntoSharedPreference(context);
            saveCourseInfoToFile(context,course);
        }
        Collections.sort(courses);
    }

    public void resort() {
        Collections.sort(courses);
    }

    public void addCourse(Course course){
        courses.add(course);
        addedCourseId.add(course.getCourseId());
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        addedCourseId.remove(course.getCourseId());
    }

    public void saveCourseIdIntoSharedPreference(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("addedCoursePref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        StringBuilder serializedCourses = new StringBuilder();
        for (String courseId : addedCourseId) {
            serializedCourses.append(courseId);
            serializedCourses.append(",");
        }
        editor.putString(Constants.ADDED_COURSE_KEY, serializedCourses.toString());
        editor.apply();
    }

    public void removeCourseIdFromSharedPreference(Context context, String courseIdToRemove) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("addedCoursePref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        StringBuilder serializedCourses = new StringBuilder();
        for (String courseId : addedCourseId) {
            if (courseId.equals(courseIdToRemove)){
                serializedCourses.append("Removed");
                serializedCourses.append(",");
            }else{
                serializedCourses.append(courseId);
                serializedCourses.append(",");
            }
            editor.putString("addedCourse", serializedCourses.toString());
            editor.apply();
        }
    }


    // save new added course to txt file
    public void saveCourseInfoToFile(Context context, Course course){
        String fileName = Constants.SAVE_DATA_FILENAME;
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter;
        StringBuilder serializedCourses = new StringBuilder();
        try {
            // MODE_APPEND - if a txt file with the same name, then append this file
            fileOutputStream = context.openFileOutput(fileName, Context.MODE_APPEND);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            serializedCourses.append(course.getYear());
            serializedCourses.append(",");
            serializedCourses.append(course.getSemester());
            serializedCourses.append(",");
            serializedCourses.append(course.getSubject());
            serializedCourses.append(",");
            serializedCourses.append(course.getCourseNumber());
            serializedCourses.append(",");
            serializedCourses.append(course.getTitle());
            serializedCourses.append("\r\n");
            String data = serializedCourses.toString();
            fileOutputStream.write(data.getBytes());
        }catch (IOException e){
            Toast.makeText(context, "Sorry, there's an error on saving this data", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // read data from txt file
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void loadCourseInfoFromFile(Context context) {
        FileInputStream fileInputStream;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fileInputStream = context.openFileInput(Constants.SAVE_DATA_FILENAME);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
            String result = "";
            while((result = bufferedReader.readLine()) != null) {
                stringBuilder.append(result);
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        courses.clear();
        String[] courseInformation;
        String year, semester, subject, courseNumber, title;
        String[] allCourseInfo = stringBuilder.toString().split("\n");
        if (!stringBuilder.toString().isEmpty()) {
            for (String courseInfo : allCourseInfo) {
                courseInformation = courseInfo.split(",");
                year = courseInformation[0];
                semester = courseInformation[1];
                subject = courseInformation[2];
                courseNumber = courseInformation[3];
                title = courseInformation[4];
                Course course = new Course(year, semester, subject, courseNumber, title);
                courses.add(course);
            }
        }
    }


    public ArrayList<Course> getFilteredCourses() {
        return filteredCourses == null ? courses : filteredCourses;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static CourseManager getInstance(Context context, String major, String startYear, String startSemester, int courseCount) {
        if (instance == null) {
            try {
                instance = new CourseManager(context, major, startYear, startSemester, courseCount);
            } catch (IOException e) {
                Toast.makeText(context, "Sorry, there was an error reading course data", Toast.LENGTH_SHORT).show();
            }
        }
        return instance;
    }


}
