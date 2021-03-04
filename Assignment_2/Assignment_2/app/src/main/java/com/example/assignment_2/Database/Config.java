/*
 * Name: Paulina Navarro Aviles
 * ID: 40109825
 */

package com.example.assignment_2.Database;

/**
 * Config Class
 * This class holds the strings related to the database tables.
 * DataBaseHelper class calls this class when needed.
 */


public class Config {

    // We can access it directly from other classes
    public static final String DATABASE_NAME_COURSES = "courses_ass-db";

    // Names of column of table
    public static final String TABLE_COURSE_NAME = "course";
    public static final String COLUMN_COURSE_ID = "course_id";
    public static final String COLUMN_COURSE_TITLE = "course_title";
    public static final String COLUMN_COURSE_CODE = "course_code";

    // Names of column of table
    public static final String TABLE_ASSIGNMENT_NAME = "assignment";
    public static final String COLUMN_ASSIGNMENT_ID = "assignment_id";
    public static final String COLUMN_COURSE_ID_ASSIGNMENTS = "courseAss_id";
    public static final String COLUMN_ASSIGNMENT_TITLE = "assignment_title";
    public static final String COLUMN_ASSIGNMENT_GRADE = "assignment_grade";



}
