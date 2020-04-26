package com.example.courseplanningapp.model;


import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.courseplanningapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/*
    CourseManager class holds all courses
    provides a singleton to access the data from
*/
public class CourseManager {
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Course> filteredCourses = null;

    // Singleton
    private static CourseManager instance;

    // read the course data file
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private CourseManager(Context context) throws IOException {
        InputStreamReader isr;

        try {
            isr = new InputStreamReader(
                    context.openFileInput("courses.txt"),
                    StandardCharsets.UTF_8);
        } catch (IOException e){
            isr = new InputStreamReader(
                    context.getResources().openRawResource(R.raw.courses),
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
            courses.add(new Course(courseData));
            System.out.println("标识："+line);
        }
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    public ArrayList<Course> getFilteredRestaurants() {
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
