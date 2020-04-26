package com.example.courseplanningapp.model;

/*
    Course class that holds the data associated with a single course
 */


import androidx.annotation.NonNull;

public class Course implements Comparable<Course>{
    private String courseId;
    private String semester;
    private int year;
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
        courseId = courseData[0];
        subject = courseData[2];
        courseNumber = courseData[3];
        title = courseData[4];
        w = Boolean.parseBoolean(courseData[5]);
        q = Boolean.parseBoolean(courseData[6]);
        bHum = Boolean.parseBoolean(courseData[7]);
        bSoc = Boolean.parseBoolean(courseData[8]);
        bSci = Boolean.parseBoolean(courseData[9]);
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
        return this.getCourseId().compareTo(o.getCourseId());
    }
}
