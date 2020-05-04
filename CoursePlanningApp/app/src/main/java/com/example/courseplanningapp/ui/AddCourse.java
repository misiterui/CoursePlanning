package com.example.courseplanningapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courseplanningapp.R;
import com.example.courseplanningapp.constants.Constants;
import com.example.courseplanningapp.constants.SpecificSemester;
import com.example.courseplanningapp.constants.Subject;
import com.example.courseplanningapp.constants.WebConstants;
import com.example.courseplanningapp.model.Course;
import com.example.courseplanningapp.model.CourseManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.angmarch.views.NiceSpinner;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class AddCourse extends AppCompatActivity {
    private Spinner yearSpinner, subjectSpinner, courseNemberSpinner;
    private ArrayAdapter yearAdapter, subjectAdapter, courseNumberAdapter;
    RadioGroup semesterRadioGroup;
    String year, semester, subject, courseNumber, title;
    public List<String> subjectList = new ArrayList<>();
    String url;
    String specificSemester;

    private CourseManager courseManager = CourseManager.getInstance(this, "", "", "", 3);

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        setTitle(Constants.ADD_COURSE_TOOL_BAR_TITLE);
        this.getWindow().setStatusBarColor(Color.RED);

        initUI();
    }

    private void initUI(){
        url = "https://coursys.sfu.ca/browse/?draw=5&columns%5B0%5D%5Bdata%5D=0&columns%5B0%5D%5Bname%5D=&columns%5B0%5D%5Bsearchable%5D=true&columns%5B0%5D%5Borderable%5D=true&columns%5B0%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B0%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B1%5D%5Bdata%5D=1&columns%5B1%5D%5Bname%5D=&columns%5B1%5D%5Bsearchable%5D=true&columns%5B1%5D%5Borderable%5D=true&columns%5B1%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B1%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B2%5D%5Bdata%5D=2&columns%5B2%5D%5Bname%5D=&columns%5B2%5D%5Bsearchable%5D=true&columns%5B2%5D%5Borderable%5D=true&columns%5B2%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B2%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B3%5D%5Bdata%5D=3&columns%5B3%5D%5Bname%5D=&columns%5B3%5D%5Bsearchable%5D=true&columns%5B3%5D%5Borderable%5D=true&columns%5B3%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B3%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B4%5D%5Bdata%5D=4&columns%5B4%5D%5Bname%5D=&columns%5B4%5D%5Bsearchable%5D=true&columns%5B4%5D%5Borderable%5D=false&columns%5B4%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B4%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B5%5D%5Bdata%5D=5&columns%5B5%5D%5Bname%5D=&columns%5B5%5D%5Bsearchable%5D=true&columns%5B5%5D%5Borderable%5D=true&columns%5B5%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B5%5D%5Bsearch%5D%5Bregex%5D=false&order%5B0%5D%5Bcolumn%5D=0&order%5B0%5D%5Bdir%5D=desc&order%5B1%5D%5Bcolumn%5D=1&order%5B1%5D%5Bdir%5D=asc&start=0&length=20&search%5Bvalue%5D=&search%5Bregex%5D=false&semester%5B%5D=1204&subject%5B%5D=ACMA&tabledata=yes";
        semester = SpecificSemester.SUMMER_2020;
        setupYearSpinner();
        setupSemesterRadioGroup();
        setupSubjectSpinner();
        //setupCourseNumberSpinner(subject);
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

    private void setupSemesterRadioGroup() {
        semesterRadioGroup = findViewById(R.id.semesterRadio);
        semesterRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.semesterSpring:
                        semester = Constants.SPRING_SEMESTER;
                        break;
                    case R.id.semesterSummer:
                        semester = Constants.SUMMER_SEMESTER;
                        break;
                    case R.id.semesterFall:
                        semester = Constants.FALL_SEMESTER;
                        break;
                    default:
                        break;
                }
                specificSemester = semester + " " + year;
            }
        });
    }

    private void setupSubjectSpinner() {
        subjectSpinner = findViewById(R.id.subjectSpinner);
        subjectAdapter = ArrayAdapter.createFromResource(this, R.array.subject_spinner, android.R.layout.select_dialog_item);
        subjectSpinner.setAdapter(subjectAdapter);
        subjectSpinner.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
    }



    private void setupSaveActivity() {

        ImageButton saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("SpecificSemester = " + year);
                System.out.println("Semester = "+semester);
                System.out.println("Subject = "+subject);
                System.out.println("CourseNumber = "+courseNumber);
                System.out.println("Title = "+title);
                Course courseSelected = new Course(year, semester, subject, courseNumber, title);
                System.out.println("比较器：" + courseSelected.getSemesterCode());
                courseManager.addCourse(courseSelected);
                courseManager.saveCourseIdIntoSharedPreference(AddCourse.this);
                courseManager.saveCourseInfoToFile(AddCourse.this, courseSelected);
                System.out.println(courseSelected.getTitle());
                Intent intent = CourseList.makeIntent(AddCourse.this);
                startActivity(intent);
                finish();
            }
        });

    }

    private void setupCancelActivity() {
        ImageButton cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, AddCourse.class);
        return intent;
    }

    public void writeToNiceSpinner(){
        NiceSpinner mTextNiceSpinner = (NiceSpinner) findViewById(R.id.text_nice_spinner);
        mTextNiceSpinner.attachDataSource(subjectList);
        mTextNiceSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mTextNiceSpinnerValue = subjectList.get(position);
                System.out.println(mTextNiceSpinnerValue);
            }
        });
        String courseInfo = mTextNiceSpinner.getText().toString();
        subject = courseInfo.split("\\s+")[0];
        courseNumber = courseInfo.split("\\s+")[1];
        title = courseInfo.split("-")[1];
        System.out.println("subject是：" + subject);
        System.out.println("courseNumber是：" + courseNumber);
        System.out.println("title是：" + title);
    }



    protected class SpinnerXMLSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            System.out.println("父类是：" + parent.getSelectedItem());
            if(parent.getSelectedItem().toString() != null){
                String temp = parent.getSelectedItem().toString();
                if (temp.length() == 4){
                    year = temp;
                    System.out.println("此时year是："+year);
                } else if (temp.equals(Subject.CMPT)){
                    subject = Subject.CMPT;
                    int semesterCode = findSemesterCode(specificSemester);
                    switch (semesterCode){
                        case 1:
                            url = WebConstants.SUMMER_2020_CMPT;
                            new DataUpdater().execute(AddCourse.this);
                            break;
                        case 2:
                            url = WebConstants.FALL_2018_CMPT;
                            new DataUpdater().execute(AddCourse.this);
                            break;
                        case 3:
                            url = WebConstants.SPRING_2019_CMPT;
                            new DataUpdater().execute(AddCourse.this);
                            break;
                        case 4:
                            url = WebConstants.SUMMER_2019_CMPT;
                            new DataUpdater().execute(AddCourse.this);
                            break;
                        case 5:
                            url = WebConstants.FALL_2019_CMPT;
                            new DataUpdater().execute(AddCourse.this);
                            break;
                        case 6:
                            url = WebConstants.SPRING_2020_CMPT_1;
                            new DataUpdater().execute(AddCourse.this);
                            break;
                        default:
                            url = "";
                            break;
                    }
                } else if (temp.equals(Subject.MATH)){
                    subject = Subject.MATH;
                    int semesterCode = findSemesterCode(specificSemester);
                    System.out.println("此时sepcificSemester是：" + specificSemester);
                    switch (semesterCode){
                        case 1:
                            url = WebConstants.SUMMER_2020_MATH;
                            new DataUpdater().execute(AddCourse.this);
                            break;
                        case 2:
                            url = WebConstants.FALL_2018_MATH;
                            new DataUpdater().execute(AddCourse.this);
                            break;
                        case 3:
                            url = WebConstants.SPRING_2019_MATH;
                            new DataUpdater().execute(AddCourse.this);
                            break;
                        case 4:
                            url = WebConstants.SUMMER_2019_MATH;
                            new DataUpdater().execute(AddCourse.this);
                            break;
                        case 5:
                            url = WebConstants.FALL_2019_MATH;
                            new DataUpdater().execute(AddCourse.this);
                            break;
                        case 6:
                            url = WebConstants.SPRING_2020_MATH;
                            new DataUpdater().execute(AddCourse.this);
                            break;
                        default:
                            url = "";
                            break;
                    }
                } else if (temp.equals("Elective Course")) {
                    subject = "Elective Course";
                        courseNumber = "";
                        title = "Elective Course";
                }else {
                    Toast.makeText(AddCourse.this, "Not available in current version", Toast.LENGTH_SHORT).show();
                }
                System.out.println("此时url是：" + url);
            }

        }
        private int findSemesterCode(String specificSemester) {
            if(specificSemester != null){
                if(specificSemester.equals(SpecificSemester.SUMMER_2020) ||
                        specificSemester.equals(SpecificSemester.SUMMER_2022) ||
                        specificSemester.equals(SpecificSemester.SUMMER_2024) ||
                        specificSemester.equals(SpecificSemester.SUMMER_2026) ||
                        specificSemester.equals(SpecificSemester.SUMMER_2028)) {
                    return 1;
                } else if (specificSemester.equals(SpecificSemester.FALL_2020) ||
                        specificSemester.equals(SpecificSemester.FALL_2022) ||
                        specificSemester.equals(SpecificSemester.FALL_2024) ||
                        specificSemester.equals(SpecificSemester.FALL_2026) ||
                        specificSemester.equals(SpecificSemester.FALL_2028)) {
                    return 2;
                } else if (specificSemester.equals(SpecificSemester.SPRING_2021) ||
                        specificSemester.equals(SpecificSemester.SPRING_2023) ||
                        specificSemester.equals(SpecificSemester.SPRING_2025) ||
                        specificSemester.equals(SpecificSemester.SPRING_2027) ||
                        specificSemester.equals(SpecificSemester.SPRING_2029)){
                    return 3;
                } else if (specificSemester.equals(SpecificSemester.SUMMER_2021) ||
                        specificSemester.equals(SpecificSemester.SUMMER_2023) ||
                        specificSemester.equals(SpecificSemester.SUMMER_2025) ||
                        specificSemester.equals(SpecificSemester.SUMMER_2027) ||
                        specificSemester.equals(SpecificSemester.SUMMER_2029)){
                    return 4;
                } else if (specificSemester.equals(SpecificSemester.FALL_2021) ||
                        specificSemester.equals(SpecificSemester.FALL_2023) ||
                        specificSemester.equals(SpecificSemester.FALL_2025) ||
                        specificSemester.equals(SpecificSemester.FALL_2027) ||
                        specificSemester.equals(SpecificSemester.FALL_2029)){
                    return 5;
                } else if (specificSemester.equals(SpecificSemester.SPRING_2022) ||
                        specificSemester.equals(SpecificSemester.SPRING_2024) ||
                        specificSemester.equals(SpecificSemester.SPRING_2026) ||
                        specificSemester.equals(SpecificSemester.SPRING_2028)) {
                    return 6;
                }
            }
            return 0;
        }


        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        public void setCourseNumber(String cn){
            courseNumber = cn;
        }
        public void setTitle (String t) {
            title = t;
        }

        protected class DataUpdater extends AsyncTask<AddCourse, String, AddCourse> {
            String specificSemesterJson, subjectJson;

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            protected AddCourse doInBackground(AddCourse... addCoursesActivity) {
                AddCourse activity = addCoursesActivity[0];
                getJsonResource();
                return activity;
            }

            public void getJsonResource() {
                try {
                    URLConnection connection = new URL(url).openConnection();
                    connection.connect();

                    JsonParser parser = new JsonParser();
                    JsonElement element = parser.parse(new InputStreamReader((InputStream) connection.getContent()));
                    JsonArray result = element.getAsJsonObject().get("data").getAsJsonArray();

                    subjectList.clear();
                    for(int i=0; i< result.size()-1; i++){
                        i++;
                        JsonArray resArray = result.get(i).getAsJsonArray();
                        System.out.println(resArray.toString());
                        specificSemesterJson = resArray.get(0).toString().replaceAll("\\\"",""); //ex.Summer 2020
                        String course = resArray.get(1).toString().split("\\s+")[1] + " " +
                                        resArray.get(1).toString().split("\\s+")[2] + " - " +
                                resArray.get(2).toString().replaceAll("\\\"", "");
                        subjectJson = course.split(">")[1]; //ex.ARCH
                        subjectList.add(subjectJson);
                        subjectList = removeDuplicate(subjectList);

                    }
                    System.out.println(subjectList.toString());
                    System.out.println(subjectList.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private List removeDuplicate(List arrayList) {
                List listTemp = new ArrayList();
                for(int i=0; i<arrayList.size(); i++) {
                    if(!listTemp.contains(arrayList.get(i))){
                        listTemp.add(arrayList.get(i));
                    }
                }
                return listTemp;
            }

            @Override
            public void onPostExecute(AddCourse addCourse) {
                //mainActivity.writetoFile("subject_item.txt", subjectList.toString(), MainActivity.this);
                System.out.println("标志" + subjectList.toString());
                addCourse.writeToNiceSpinner();


            }
        }
    }


}
