/*
 * Name: Paulina Navarro Aviles
 * ID: 40109825
 */
package com.example.assignment1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Random;

/**
 * GradeActivity Class.
 * This class is a child activity to MainActivity.
 * This class deals with the percentage and letter grades of the assignments.
 * It uses two custom adapters:
 * 1. Percentage grades
 * 2. Letter grades.
 */

public class GradeActivity extends AppCompatActivity {


    private ArrayList<Course> courses;
    private ListView listCourses;
    private CourseAdapter_Letter adapter_letter;
    private CourseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        setupUI();
    }

    // Initializing UI

    private void setupUI() {

        getSupportActionBar().setTitle(R.string.Grades);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listCourses = (ListView) findViewById(R.id.List);

        // Calling  courseGenerator method

        courseGenerator();

        // Custom Adapters for the Percentage and Letter Courses

        adapter_letter= new CourseAdapter_Letter(this, R.layout.courseadapter_layout_grades, courses);
        adapter = new CourseAdapter(this, R.layout.courseadapter_layout, courses);
        listCourses.setAdapter(adapter);
    }

    // Side Menu Options (Menu bar)

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ConvertGrades_Letter:
                listCourses.setAdapter(adapter_letter);
                break;
            case R.id.ConvertGrades_Percentage:
                listCourses.setAdapter(adapter);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Method that Generates Random Courses

    public void courseGenerator() {
        Random random = new Random();
        int nxt = random.nextInt(5) + 1;
        courses = new ArrayList<Course>(nxt);

        for (int i = 0; i < 5; i++) {
            Course course = Course.generateRandomCourses();
            courses.add(course);
        }
    }

    // Menu Bar Options - Inflater

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate((R.menu.menu_letter), menu);
        inflater.inflate(R.menu.menu_percentage,menu);
        return true;
    }

}



