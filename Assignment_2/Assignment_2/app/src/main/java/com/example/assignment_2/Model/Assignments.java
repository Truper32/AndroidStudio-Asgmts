/*
 * Name: Paulina Navarro Aviles
 * ID: 40109825
 */

package com.example.assignment_2.Model;

/**
 * Assignment Class
 * This is the base class of the assignments.
 * This class gets called when we want to create an assignment.
 */


public class Assignments {

    private long AssignmentId;
    private Course CoursetId;
    private String title;
    private String grade;


    public Assignments(){

    }

    // Assignments  Constructor

    public Assignments(long AssId, String title, String grade) {

        this.AssignmentId = AssId;
        this.title = title;
        this.grade = grade;
    }

    // To String Method

    @Override
    public String toString() {

        return title + '\n' + grade + "%" + '\n';
    }

    // Getter and Setter Methods

    public Course getCourseId() { return CoursetId;
    }
    public long getAssignmentId() { return AssignmentId;
    }
    public void setCourseId(Course courseid) {
        this.CoursetId = courseid;
    }

    public void setAssignmentId(long id) {
        this.AssignmentId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


}
