/*
 * Name: Paulina Navarro Aviles
 * ID: 40109825
 */
package com.example.assignment1;
import java.util.Random;

/**
 * Assignment class.
 * This class generates assignments and its corresponding grades in a random manner.
 */

public class Assignment {

    private static int assID = 0;
    private String assTitle;
    private int assGrade;

    // Assignment Constructor - Increments ID

    private Assignment (String title, int grade){

        assTitle = title;
        assGrade = grade;
        assID++;
    }

    //Returns assignment instance with random values

    static public Assignment generateRandomAssignment(){

        Random random = new Random();
        String tempTitle = "Assignment " + assID;
        int tempGrade = random.nextInt(100) + 1;

        return new Assignment(tempTitle, tempGrade);

    }
    // Get Assignment Title

    public String getAssignmentTitle(){
        return assTitle;
    }

    // Get Assignment Grade

    public int getAssGrade(){
        return assGrade;
    }

}


