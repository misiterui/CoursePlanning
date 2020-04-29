package com.example.courseplanningapp.model;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.courseplanningapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

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
    private CourseManager(Context context) throws IOException {
        loadAddedCourses(context);
        InputStreamReader isr;
        try {
            isr = new InputStreamReader(
                    context.openFileInput("select_courses.txt"),
                    StandardCharsets.UTF_8);
        } catch (IOException e){
            isr = new InputStreamReader(
                    context.getResources().openRawResource(R.raw.select_courses),
                    StandardCharsets.UTF_8);
        }

        BufferedReader reader = new BufferedReader(isr);

        // populate the data structure
        courses.clear();

        String line;

        // read the first line first so skip the title
        reader.readLine();

        // iteratively read the whole file
        while((line = reader.readLine()) != null) {
            String[] courseData = line.split(",");
            //courses.add(new Course());
            System.out.println("标识："+line);
        }


        Collections.sort(courses);

    }

    public void resort() {
        Collections.sort(courses);
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    public void saveAddedCourses(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        StringBuilder serializedCourses = new StringBuilder();
        for (Course course : courses) {

            for (int i = 0; i < courses.size(); i++) {
                String courseId = courses.get(i).getCourseId();
                serializedCourses.append(courseId);
                serializedCourses.append(",");

            }
            editor.putString("AddedCourseId", serializedCourses.toString());
            editor.commit();
        }
    }

    public void loadAddedCourses(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE);
        String serializedCourses = preferences.getString("AddedCourseId", "");
        String[] serializedCoursesId = serializedCourses.split(",");
        for (String courseId: serializedCoursesId){
            if (courseId != null && !courseId.isEmpty()) {
                addedCourseId.add(courseId);
            }
        }
    }


    public ArrayList<Course> getFilteredCourses() {
        return filteredCourses == null ? courses : filteredCourses;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static CourseManager getInstance(Context context) {
        if (instance == null) {
            try {
                instance = new CourseManager(context);
            } catch (IOException e) {
                Toast.makeText(context, "Sorry, there was an error reading course data", Toast.LENGTH_SHORT).show();
            }
        }
        return instance;
    }


}
