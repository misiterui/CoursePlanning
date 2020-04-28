package com.example.courseplanningapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.courseplanningapp.R;

import java.util.Collections;

public class AddCourse extends AppCompatActivity {
    private TextView yearText, subjectText, courseNumberText;
    private Spinner yearSpinner, subjectSpinner, courseNemberSpinner;
    private ArrayAdapter yearAdapter, subjectAdapter, courseNumberSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        setupYearSpinner();
        setupSemesterRadio();
        setupSubjectSpinner();
        setupCourseNumberSpinner();
        setupSaveActivity();
        setupCancelActivity();
    }

    private void setupYearSpinner() {
        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        //yearText = (TextView) findViewById(R.id.yearText);
        yearAdapter = ArrayAdapter.createFromResource(this, R.array.year_spinner, android.R.layout.select_dialog_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        yearAdapter.setDropDownViewResource(R.layout.spinner_dropdown_style);
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
    }

    private void setupSemesterRadio() {

    }

    private void setupSubjectSpinner() {
        subjectSpinner = findViewById(R.id.subjectSpinner);
        subjectAdapter = ArrayAdapter.createFromResource(this, R.array.subject_spinner, android.R.layout.select_dialog_item);
        subjectSpinner.setAdapter(subjectAdapter);
        subjectSpinner.setOnItemSelectedListener(new SpinnerXMLSelectedListener());

    }

    private void setupCourseNumberSpinner() {

    }

    private void setupSaveActivity() {


    }

    private void setupCancelActivity() {
    }

    private void getInputData() {

    }


    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, AddCourse.class);
        return intent;
    }

    private class SpinnerXMLSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //yearText.setText("" + yearAdapter.getItem(position));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
