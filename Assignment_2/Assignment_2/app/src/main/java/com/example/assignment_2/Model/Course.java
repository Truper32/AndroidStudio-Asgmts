/*
 * Name: Paulina Navarro Aviles
 * ID: 40109825
 */

package com.example.assignment_2.Model;

/**
 * Course class
 * This is the base class of the courses
 * This class gets called when we want to create a course
 */

public class Course {

    private long id;
    private String title;
    private String code;

    public Course() {
    }

    // Course  Constructor

    public Course(long id, String title, String code) {
        this.id = id;
        this.title = title;
        this.code = code;
    }

    // To String Method

    @Override
    public String toString() {
        return title + '\t' + code + '\n';
    }

    // Getter and Setter Methods

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}

