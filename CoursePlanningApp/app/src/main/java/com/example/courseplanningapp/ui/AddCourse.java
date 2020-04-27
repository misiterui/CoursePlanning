package com.example.courseplanningapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.courseplanningapp.R;


public class AddCourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
    }





    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, AddCourse.class);
        return intent;
    }
}
