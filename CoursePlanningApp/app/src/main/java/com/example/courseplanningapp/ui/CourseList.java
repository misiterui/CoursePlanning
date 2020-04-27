package com.example.courseplanningapp.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.courseplanningapp.R;
import com.example.courseplanningapp.model.CourseManager;

import java.util.Collections;

public class CourseList extends AppCompatActivity {

    public static CourseRecyclerAdapter recyclerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        initRecyclerView();
        initAddBtn();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.courseRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new CourseRecyclerAdapter(this);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void initAddBtn() {
        ImageButton addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: start addCourse activity
                startActivity(AddCourse.makeIntent(CourseList.this));
            }
        });
    }



    public void refreshRecyclerView() {
        recyclerAdapter.notifyDataSetChanged();
    }

}