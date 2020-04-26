package com.example.courseplanningapp.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.courseplanningapp.R;
import com.example.courseplanningapp.model.CourseManager;

public class CourseList extends AppCompatActivity {

    public static CourseRecyclerAdapter recyclerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        initRecyclerView();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.courseRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new CourseRecyclerAdapter(this);
        recyclerView.setAdapter(recyclerAdapter);
    }

    public void refreshRecyclerView() {
        recyclerAdapter.notifyDataSetChanged();
    }

}
