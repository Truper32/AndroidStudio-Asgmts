/*
 * Name: Paulina Navarro Aviles
 * ID: 40109825
 */
package com.example.assignment1;
import java.util.ArrayList;
import java.util.Random;

/**
 * Course Class.
 * This class generates random courses with random assignments and grades.
 */

public class Course extends GradeActivity{

    private static int courseID = 0;
    private String courseTitle;
    private ArrayList<Assignment> assignments;


    // Course Constructor

    public Course(String title, ArrayList<Assignment> assignment){

        courseTitle = title;
        assignments = assignment;
        courseID++;

    }

    // Returns a Course Instant with Random Assignments Values

    static public Course generateRandomCourses(){

        Random random = new Random();
        int assNo = random.nextInt(5);
        ArrayList<Assignment> tempAss = new ArrayList<Assignment>();

        for(int i = 0; i < assNo; i++){
            tempAss.add(Assignment.generateRandomAssignment());
        }

        return new Course("Course " + courseID, tempAss);

    }


    // Get Course Title Information

    public String getCourseTitle(){
        return courseTitle;
    }

    // Get Assignments Information (Number of assignment and grades)

    public ArrayList<Assignment> getAssignments(){
        return assignments;
    }


}
