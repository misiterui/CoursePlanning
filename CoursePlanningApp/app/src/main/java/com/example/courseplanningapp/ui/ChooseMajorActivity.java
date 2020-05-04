package com.example.courseplanningapp.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("is_user_guide_showed", true);
        editor.apply();
        System.out.println("那这里有改动吗" + sp.getBoolean("is_uesr_guide_showed", true));
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
                SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("major", majorSelected);
                editor.apply();
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
                SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("startYear", startYear);
                editor.apply();
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
                SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("startSemester", startSemester);
                editor.apply();
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
                SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("courseCount", courseCount);
                editor.apply();
            }
        });
    }

    private void setupButton() {
        Button readyBtn = findViewById(R.id.ready);
        readyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseMajorActivity.this, CourseList.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, ChooseMajorActivity.class);
        return intent;
    }
}
