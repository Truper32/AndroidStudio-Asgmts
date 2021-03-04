/*
 * Name: Paulina Navarro Aviles
 * ID: 40109825
 */
package com.example.assignment1;

/**
 *  StudentProfile Class.
 *  This class deals with the entire student profile information.
 *  It gets called from the ProfileActivity class.
 */

public class StudentProfile {

    private String Id;
    private String age;
    private String Name;

    // StudentProfile Constructor

    public StudentProfile(String name, String age, String id) {
        this.Id = id;
        this.age = age;
        this.Name = name;
    }

    // Set Student Name

    public void setName(String name) {
        Name = name;
    }

    // Set Student ID

    public void setId(String id) {
        Id = id;
    }

    // Set Student Age

    public void setAge(String age) {
        this.age = age;
    }

    // Get Student Name

    public String getName() {
        return Name;
    }

    // Get Student Age

    public String getAge() {
        return age;
    }

    //Get Student ID

    public String getId() {
        return Id;
    }

}
