package com.example.courseplanningapp.ui;

import androidx.annotation.BinderThread;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.courseplanningapp.R;
import com.example.courseplanningapp.constants.Constants;
import com.example.courseplanningapp.model.CourseManager;

import java.io.IOException;
import java.util.Collections;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class CourseList extends AppCompatActivity {

    public static CourseRecyclerAdapter recyclerAdapter;
    private String major, startYear, startSemester;
    private int courseCount;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        setTitle(Constants.COURSE_LIST_TOOL_BAR_TITLE);
        this.getWindow().setStatusBarColor(Color.RED);

        initRecyclerView();
        initAddBtn();
        initMajorBtn();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.courseRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        major = sharedPreferences.getString("major", "");
        startYear = sharedPreferences.getString("startYear", "2020");
        startSemester = sharedPreferences.getString("startSemester", "Summer");
        courseCount = Integer.parseInt(sharedPreferences.getString("courseCount", "3"));

        Intent intent = getIntent();
//        if(intent.getStringExtra("major")!=null){
//            major = intent.getStringExtra("major");
//            System.out.println("此时major是：" + major);
//            if (major.equals("Computing Science")){
//                startYear = intent.getStringExtra("startYear");
//                startSemester = intent.getStringExtra("startSemester");
//                courseCount = Integer.parseInt(intent.getStringExtra("courseCount"));
//                System.out.println("此时的courseCount是：" + courseCount);
//
//
//        }

        if (intent != null && recyclerAdapter!=null){
            //refreshRecyclerView();
            recyclerAdapter.resort();
        }
        recyclerAdapter = new CourseRecyclerAdapter(this, major, startYear, startSemester, courseCount);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void initAddBtn() {
        ImageButton addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                startActivity(AddCourse.makeIntent(CourseList.this));
            }
        });
    }

    private void initMajorBtn() {
        Button chooseMajorBtn = findViewById(R.id.choose_major_btn);
        chooseMajorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ChooseMajorActivity.makeIntent(CourseList.this));
            }
        });
    }

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, CourseList.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }


//    public void refreshRecyclerView() {
//        recyclerAdapter.notifyDataSetChanged();
//    }

}
