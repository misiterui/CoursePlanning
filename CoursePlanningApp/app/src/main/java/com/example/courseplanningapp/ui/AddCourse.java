package com.example.courseplanningapp.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.courseplanningapp.R;
import com.example.courseplanningapp.constants.Constants;
import com.example.courseplanningapp.constants.Subject;
import com.example.courseplanningapp.constants.Year;
import com.example.courseplanningapp.model.Course;
import com.example.courseplanningapp.model.CourseManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class AddCourse extends AppCompatActivity {
    private TextView yearText, subjectText, courseNumberText;
    private Spinner yearSpinner, subjectSpinner, courseNemberSpinner;
    private ArrayAdapter yearAdapter, subjectAdapter, courseNumberAdapter;
    RadioGroup semesterRadioGroup;
    String year, semester, subject, courseNumber, title;
    public ArrayList<String> subjectList = new ArrayList<>();
    String url, specificSemester;

    private CourseManager courseManager = CourseManager.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        setTitle(Constants.ADD_COURSE_TOOL_BAR_TITLE);

        initUI();
    }

    private void initUI(){
        setupYearSpinner();
        setupSemesterRadioGroup();
        setupSubjectSpinner();
        setupCourseNumberSpinner(subject);
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
                specificSemester = year + semester;
            }
        });
    }

    private void setupSubjectSpinner() {
        subjectSpinner = findViewById(R.id.subjectSpinner);
        subjectAdapter = ArrayAdapter.createFromResource(this, R.array.subject_spinner, android.R.layout.select_dialog_item);
        subjectSpinner.setAdapter(subjectAdapter);
        subjectSpinner.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
    }

    private void setupCourseNumberSpinner(String subject) {
        courseNemberSpinner = findViewById(R.id.numberSpinner);
        courseNumberAdapter = ArrayAdapter.createFromResource(this, R.array.acma_spinner, android.R.layout.select_dialog_item);
        courseNemberSpinner.setAdapter(courseNumberAdapter);
        courseNemberSpinner.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
    }


    private void setupSaveActivity() {

        ImageButton saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Year = " + year);
                System.out.println("Semester = "+semester);
                System.out.println("Subject = "+subject);
                System.out.println("CourseNumber = "+courseNumber);
                System.out.println("Title = "+title);
                Course courseSelected = new Course(year, semester, subject, courseNumber, title);
                System.out.println("比较器：" + courseSelected.getSemesterCode());
                courseManager.addCourse(courseSelected);
                courseManager.saveCourseIdIntoSharedPreference(AddCourse.this);
                courseManager.saveCourseInfoToFile(AddCourse.this, courseSelected, Constants.SAVE_DATA_FILENAME);
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



    protected class SpinnerXMLSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            System.out.println("父类是：" + parent.getSelectedItem());
            String temp = parent.getSelectedItem().toString();
            if(temp != null){
                if (temp.equals("2020") || temp.equals("2021") || temp.equals("2022") ||
                        temp.equals("2023") || temp.equals("2024") || temp.equals("2025") ||
                        temp.equals("2026") || temp.equals("2027") || temp.equals("2028") ||
                        temp.equals("2029")){
                    year = temp;
                } else if(temp.equals(Subject.ACMA)){
                    subject = "ACMA";
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.acma_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.ALS)){
                    subject = Subject.ALS;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.als_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.APMA)){
                    subject = Subject.APMA;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.als_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }
                else if(temp.equals(Subject.ARAB)){
                    subject = Subject.ARAB;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.arab_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.ARCH)){
                    subject = Subject.ARCH;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.arch_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.BISC)){
                    subject = Subject.BISC;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.bisc_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.BPK)){
                    subject = Subject.BPK;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.bpk_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.BUS)){
                    subject =Subject.BUS;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.bus_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.CHEM)){
                    subject = Subject.CHEM;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.chem_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.CHIN)){
                    subject = Subject.CHIN;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.als_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.COGS)){
                    subject = Subject.COGS;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.cogs_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.CMNS)){
                    subject = Subject.CMNS;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.cmns_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.CMPT)){
                    subject = Subject.CMPT;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.cmpt_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.CA)){
                    subject = Subject.CA;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.ca_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.CRIM)){
                    subject = Subject.CRIM;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.crim_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.DATA)){
                    subject = Subject.DATA;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.data_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.DIAL)){
                    subject = Subject.DIAL;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.dial_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.DMED)){
                    subject = Subject.DMED;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.dmed_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.EASC)){
                    subject =Subject.EASC;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.easc_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.ECO)){
                    subject = Subject.ECO;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.eco_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.ECON)){
                    subject = Subject.ECON;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.econ_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.EDUC)){
                    subject = Subject.EDUC;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.educ_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.EDPR)){
                    subject = Subject.EDPR;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.edpr_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.ETEC)){
                    subject = Subject.ETEC;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.etec_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.ENSC)){
                    subject = Subject.ENSC;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.ensc_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.ENGL)){
                    subject = Subject.ENGL;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.engl_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.EAS)){
                    subject = Subject.EAS;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.eas_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.ENV)){
                    subject =Subject.ENV;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.env_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.EVSC)){
                    subject = Subject.EVSC;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.evsc_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.FASS)){
                    subject = Subject.FASS;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.fass_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.FNLG)){
                    subject = Subject.FNLG;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.fnlg_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.FNST)){
                    subject = Subject.FNST;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.fnst_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.FAL)){
                    subject = Subject.FAL;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.fal_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.FAN)){
                    subject = Subject.FAN;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.fan_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.FREN)){
                    subject = Subject.FREN;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.fren_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.GSWS)){
                    subject = Subject.GSWS;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.gsws_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.GS)){
                    subject = Subject.GS;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.gs_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.GEOG)){
                    subject = Subject.GEOG;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.geog_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.GERM)){
                    subject = Subject.GERM;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.germ_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.GERO)){
                    subject = Subject.GERO;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.gero_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.GA)){
                    subject = Subject.GA;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.ga_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.GRK)){
                    subject = Subject.GRK;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.grk_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.HSCI)){
                    subject = Subject.HSCI;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.hsci_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.HS)){
                    subject = Subject.HS;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.hs_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.HIST)){
                    subject = Subject.HIST;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.hist_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.HUM)){
                    subject = Subject.HUM;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.hum_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.INS)){
                    subject = Subject.INS;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.ins_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.IAT)){
                    subject = Subject.IAT;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.iat_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.IS)){
                    subject = Subject.IS;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.is_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.ISPO)){
                    subject = Subject.ISPO;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.ispo_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.ITAL)){
                    subject = Subject.ITAL;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.ital_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.JAPN)){
                    subject = Subject.JAPN;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.japn_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.LBST)){
                    subject = Subject.LBST;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.lbst_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.LANG)){
                    subject = Subject.LANG;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.lang_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.LAS)){
                    subject = Subject.LAS;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.las_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.LBRL)){
                    subject = Subject.LBRL;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.lbrl_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.LS)){
                    subject = Subject.LS;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.ls_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.LING)){
                    subject = Subject.LING;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.ling_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.MTEC)){
                    subject = Subject.MTEC;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.mtec_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.MASC)){
                    subject = Subject.MASC;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.masc_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.MATH)){
                    subject = Subject.MATH;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.math_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.MACM)){
                    subject = Subject.MACM;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.macm_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.MSE)){
                    subject = Subject.MSE;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.mse_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.MBB)){
                    subject = Subject.MBB;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.mbb_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.NEUR)){
                    subject = Subject.NEUR;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.neur_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.NUSC)){
                    subject = Subject.NUSC;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.nusc_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.ONC)){
                    subject = Subject.ONC;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.onc_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.PERS)){
                    subject = Subject.PERS;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.pers_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.PHIL)){
                    subject = Subject.PHIL;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.phil_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.PHYS)){
                    subject = Subject.PHYS;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.phys_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.PLAN)){
                    subject = Subject.PLAN;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.plan_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.POL)){
                    subject = Subject.POL;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.pol_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.PSYC)){
                    subject = Subject.PSYC;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.psyc_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.PLCY)){
                    subject = Subject.PLCY;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.plcy_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.PUB)){
                    subject = Subject.PUB;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.pub_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.PUNJ)){
                    subject = Subject.PUNJ;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.punj_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.REM)){
                    subject = Subject.REM;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.rem_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.SCI)){
                    subject = Subject.SCI;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.sci_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.SA)){
                    subject = Subject.SA;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.sa_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.SPAN)){
                    subject = Subject.SPAN;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.span_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.STAT)){
                    subject = Subject.STAT;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.stat_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.SD)){
                    subject = Subject.SD;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.sd_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.SEE)){
                    subject = Subject.SEE;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.see_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.TEKX)){
                    subject = Subject.TEKX;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.tekx_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.TRSS)){
                    subject = Subject.TRSS;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.trss_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.URB)){
                    subject = Subject.URB;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.urb_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else if(temp.equals(Subject.WL)){
                    subject = Subject.WL;
                    courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.wl_spinner, android.R.layout.select_dialog_item);
                    courseNemberSpinner.setAdapter(courseNumberAdapter);
                }else{
                    // split the long course title
                    splitCourseTitle(temp);
                }
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        // Split the long course title
        private void splitCourseTitle(String courseTitle) {
            String[] temp = courseTitle.split(" ");
            subject = temp[0];
            courseNumber = temp[1];
            title = temp[2];
            for (int i=3; i<temp.length; i++){
                title = title + " " + temp[i];
            }
        }

        protected class DataUpdater extends AsyncTask<AddCourse, String, AddCourse> {
            String specificSemesterJson, subjectJson;

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            protected AddCourse doInBackground(AddCourse... addCoursesActivity) {
                AddCourse activity = addCoursesActivity[0];
                getJsonResource(url);
                        return activity;
            }

            public void getJsonResource(String url) {
                try {
                    URLConnection connection = new URL(url).openConnection();
                    connection.connect();

                    JsonParser parser = new JsonParser();
                    JsonElement element = parser.parse(new InputStreamReader((InputStream) connection.getContent()));
                    JsonArray result = element.getAsJsonObject().get("data").getAsJsonArray();

                    subjectList.clear();
                    for(int i=0; i< result.size(); i++){
                        i++;
                        JsonArray resArray = result.get(i).getAsJsonArray();
                        System.out.println(resArray.toString());
                        specificSemesterJson = resArray.get(0).toString().replaceAll("\\\"",""); //ex.Summer 2020
                        String course = resArray.get(1).toString().split("\\s+")[1] + " " +
                                        resArray.get(1).toString().split("\\s+")[2];
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

            private ArrayList removeDuplicate(ArrayList arrayList) {
                ArrayList listTemp = new ArrayList();
                for(int i=0; i<arrayList.size(); i++) {
                    if(!listTemp.contains(arrayList.get(i))){
                        listTemp.add(arrayList.get(i));
                    }
                }
                return listTemp;
            }
        }
    }
}
