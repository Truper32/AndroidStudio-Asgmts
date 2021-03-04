/*
 * Name: Paulina Navarro Aviles
 * ID: 40109825
 */
package com.example.assignment1;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferenceHelper Class.
 * This class is the controller of the entire application and it gets called by the ProfileActivity class
 * when a student profile needs to be saved or retrieved.
 */

public class SharedPreferenceHelper {

    private SharedPreferences sharedPreferences;
    private Context context;

    // SharedPreferenceHelper Constructor

    public SharedPreferenceHelper(Context context)
    {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.SharedPreferenceHelper_FileName), Context.MODE_PRIVATE);
    }

    // Save the profile of the Student

    public void saveProfile (StudentProfile profile){

        String name = profile.getName();
        String age = profile.getAge();
        String Id = profile.getId();

        SharedPreferences.Editor obj_editor = sharedPreferences.edit();
        obj_editor.putString(context.getString(R.string.SharedPreferenceHelper_Name), name);
        obj_editor.putString(context.getString(R.string.SharedPreferenceHelper_Age), age);
        obj_editor.putString(context.getString(R.string.SharedPreferenceHelper_studentID), Id);
        obj_editor.apply();

    }

    // Get Student Profile

    public StudentProfile getProfile(){

        String name = sharedPreferences.getString(context.getString(R.string.SharedPreferenceHelper_Name), null);
        String age = sharedPreferences.getString(context.getString(R.string.SharedPreferenceHelper_Age), null);
        String id = sharedPreferences.getString(context.getString(R.string.SharedPreferenceHelper_studentID), null);

        StudentProfile profile = new StudentProfile(name, age, id);

       return profile;

    }

    // Get Student Name

    public String getProfileName(){

        return sharedPreferences.getString(context.getString(R.string.SharedPreferenceHelper_Name), null);
    }

    // Get Student Age

    public String getProfileAge(){
        return sharedPreferences.getString(context.getString(R.string.SharedPreferenceHelper_Age), null);

    }

    // Get Student ID

    public String getProfileID(){
        return sharedPreferences.getString(context.getString(R.string.SharedPreferenceHelper_studentID), null);
    }
}
