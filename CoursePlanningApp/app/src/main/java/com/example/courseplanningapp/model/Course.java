package com.example.courseplanningapp.model;

/*
    Course class that holds the data associated with a single course
 */


import androidx.annotation.NonNull;

import com.example.courseplanningapp.constants.Constants;


public class Course implements Comparable<Course> {
    private String courseId;
    private String semester;
    private String year;
    private String semesterCode;
    private String subject;
    private String courseNumber;
    private String title;
    private Boolean w;
    private Boolean q;
    private Boolean bHum;
    private Boolean bSoc;
    private Boolean bSci;
    private String url;


    // Constructor that parses a .csv line split by ","
    public Course(String[] courseData){
        subject = courseData[0];
        courseNumber = courseData[1];
        courseId = subject + courseNumber;
        title = courseData[2];
//        w = Boolean.parseBoolean(courseData[5]);
//        q = Boolean.parseBoolean(courseData[6]);
//        bHum = Boolean.parseBoolean(courseData[7]);
//        bSoc = Boolean.parseBoolean(courseData[8]);
//        bSci = Boolean.parseBoolean(courseData[9]);

        year = Constants.SAMPLE_YEAR;
        semester = Constants.SAMPLE_SEMESTER;

        getSemesterCode();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String subject, String courseNumber) {
        courseId = "" + subject + courseNumber;
        this.courseId = courseId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemesterCode() {
        double tempSemesterCode;
        if(semester.equals("")) {
            tempSemesterCode = Integer.parseInt(year) + 0.0;
            semesterCode = Double.toString(tempSemesterCode);
        }
        if(semester.equals(Constants.SPRING_SEMESTER)) {
            tempSemesterCode = Integer.parseInt(year) + 0.1;
            semesterCode = Double.toString(tempSemesterCode);
        }
        if(semester.equals(Constants.SUMMER_SEMESTER)) {
            tempSemesterCode = Integer.parseInt(year) + 0.2;
            semesterCode = Double.toString(tempSemesterCode);
        }
        if(semester.equals(Constants.FALL_SEMESTER)) {
            tempSemesterCode = Integer.parseInt(year) + 0.3;
            semesterCode = Double.toString(tempSemesterCode);
        }
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getW() {
        return w;
    }

    public void setW(Boolean w) {
        this.w = w;
    }

    public Boolean getQ() {
        return q;
    }

    public void setQ(Boolean q) {
        this.q = q;
    }

    public Boolean getbHum() {
        return bHum;
    }

    public void setbHum(Boolean bHum) {
        this.bHum = bHum;
    }

    public Boolean getbSoc() {
        return bSoc;
    }

    public void setbSoc(Boolean bSoc) {
        this.bSoc = bSoc;
    }

    public Boolean getbSci() {
        return bSci;
    }

    public void setbSci(Boolean bSci) {
        this.bSci = bSci;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @NonNull
    @Override
    public String toString() {
        return year + '\t' +
                semester + '\t' +
                subject + '\t' +
                courseNumber + '\t' +
                title;
    }


    @Override
    public int compareTo(Course o) {
        return (this.getSemesterCode().compareTo(o.getSemesterCode()));
    }
}
