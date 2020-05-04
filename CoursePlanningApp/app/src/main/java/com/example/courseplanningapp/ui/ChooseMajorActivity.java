package com.example.courseplanningapp.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.courseplanningapp.R;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;

public class ChooseMajorActivity extends AppCompatActivity {
    ArrayList<String> majorList = new ArrayList<>();
    ArrayList<String> startYearList = new ArrayList<>();
    ArrayList<String> startSemesterList = new ArrayList<>();
    ArrayList<String> courseCountList = new ArrayList<>();
    String majorSelected, startYear, startSemester, courseCount;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_major);
        this.getWindow().setStatusBarColor(Color.RED);

        majorList.add("");
        majorList.add("Computing Science");
        //majorList.add("Mathematics");

        startYearList.add("");
        for (int i = 2020; i<2025; i++){
            startYearList.add("" + i);
        }

        startSemesterList.add("");
        startSemesterList.add("Spring");
        startSemesterList.add("Summer");
        startSemesterList.add("Fall");

        courseCountList.add("");
        for (int i = 2; i < 6; i++){
            courseCountList.add("" + i);
        }

        init();
    }

    private void init() {
        setupMajorSpinner();
        setupStartYearSpinner();
        setupStartSemesterSpinner();
        setupCourseCountSpinner();
        setupButton();
    }

    private void setupMajorSpinner() {
        final NiceSpinner majorSpinner = findViewById(R.id.major);
        majorSpinner.attachDataSource(majorList);
        majorSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                majorSelected = majorList.get(position);
            }
        });
    }

    private void setupStartYearSpinner() {
        final NiceSpinner startYearSpinner = findViewById(R.id.start_year);
        startYearSpinner.attachDataSource(startYearList);
        startYearSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startYear = startYearList.get(position);
            }
        });
    }

    private void setupStartSemesterSpinner() {
        final NiceSpinner startSemesterSpinner = findViewById(R.id.start_semester);
        startSemesterSpinner.attachDataSource(startSemesterList);
        startSemesterSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startSemester = startSemesterList.get(position);
            }
        });
    }

    private void setupCourseCountSpinner() {
        final NiceSpinner courseCountSpinner = findViewById(R.id.course_count);
        courseCountSpinner.attachDataSource(courseCountList);
        courseCountSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                courseCount = courseCountList.get(position);
            }
        });
    }

    private void setupButton() {
        Button readyBtn = findViewById(R.id.ready);
        readyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseMajorActivity.this, CourseList.class);
                intent.putExtra("major", majorSelected);
                intent.putExtra("startYear", startYear);
                intent.putExtra("startSemester", startSemester);
                intent.putExtra("courseCount", courseCount);
                startActivity(intent);
            }
        });
    }
}
