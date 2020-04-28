package com.example.courseplanningapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courseplanningapp.R;
import com.example.courseplanningapp.constants.Subject;

import java.util.Collections;

public class AddCourse extends AppCompatActivity {
    private TextView yearText, subjectText, courseNumberText;
    private Spinner yearSpinner, subjectSpinner, courseNemberSpinner;
    private ArrayAdapter yearAdapter, subjectAdapter, courseNumberAdapter;
    String subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        setupYearSpinner();
        setupSemesterRadio();
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

    private void setupSemesterRadio() {

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

    private void findCourseNumberBySubject(String subject) {

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
            System.out.println("父类是：" + parent.getSelectedItem());
            String temp = parent.getSelectedItem().toString();
            if(temp.equals(Subject.ACMA)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.acma_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.ALS)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.als_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.APMA)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.als_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }
            else if(temp.equals(Subject.ARAB)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.arab_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.ARCH)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.arch_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.BISC)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.bisc_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.BPK)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.bpk_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.BUS)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.bus_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.CHEM)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.chem_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.CHIN)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.als_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.COGS)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.cogs_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.CMNS)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.cmns_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.CMPT)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.cmpt_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.CA)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.ca_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.CRIM)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.crim_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.DATA)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.data_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.DIAL)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.dial_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.DMED)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.dmed_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.EASC)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.easc_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.ECO)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.eco_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.ECON)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.econ_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.EDUC)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.educ_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.EDPR)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.edpr_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.ETEC)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.etec_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.ENSC)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.ensc_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.ENGL)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.engl_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.EAS)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.eas_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.ENV)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.env_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.EVSC)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.evsc_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.FASS)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.fass_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.FNLG)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.fnlg_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.FNST)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.fnst_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.FAL)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.fal_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.FAN)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.fan_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.FREN)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.fren_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.GSWS)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.gsws_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.GS)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.gs_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.GEOG)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.geog_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.GERM)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.germ_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.GERO)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.gero_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.GA)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.ga_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.GRK)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.grk_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.HSCI)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.hsci_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.HS)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.hs_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.HIST)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.hist_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.HUM)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.hum_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.INS)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.ins_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.IAT)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.iat_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.IS)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.is_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.ISPO)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.ispo_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.ITAL)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.ital_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.JAPN)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.japn_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.LBST)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.lbst_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.LANG)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.lang_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.LAS)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.las_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.LBRL)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.lbrl_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.LS)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.ls_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.LING)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.ling_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.MTEC)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.mtec_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.MASC)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.masc_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.MATH)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.math_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.MACM)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.macm_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.MSE)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.mse_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.MBB)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.mbb_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.NEUR)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.neur_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.NUSC)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.nusc_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.ONC)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.onc_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.PERS)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.pers_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.PHIL)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.phil_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.PHYS)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.phys_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.PLAN)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.plan_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.POL)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.pol_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.PSYC)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.psyc_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.PLCY)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.plcy_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.PUB)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.pub_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.PUNJ)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.punj_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.REM)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.rem_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.SCI)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.sci_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.SA)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.sa_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.SPAN)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.span_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.STAT)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.stat_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.SD)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.sd_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.SEE)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.see_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.TEKX)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.tekx_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.TRSS)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.trss_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.URB)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.urb_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else if(temp.equals(Subject.WL)){
                courseNumberAdapter = ArrayAdapter.createFromResource(AddCourse.this, R.array.wl_spinner, android.R.layout.select_dialog_item);
                courseNemberSpinner.setAdapter(courseNumberAdapter);
            }else{
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
