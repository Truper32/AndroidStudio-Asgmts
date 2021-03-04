/*
 * Name: Paulina Navarro Aviles
 * ID: 40109825
 */

package com.example.assignment_2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.assignment_2.Adapter.ListCoursesAdapter;
import com.example.assignment_2.Database.DataBaseHelper;
import com.example.assignment_2.Model.Assignments;
import com.example.assignment_2.Model.Course;
import com.example.assignment_2.Model.CourseDialog;
import com.example.assignment_2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

/**
 * Main Activity Class
 * This class is the launcher activity of the application.
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static int listviewPosition = 0;
    private DataBaseHelper mdbHelper;
    private ListView listView;
    private TextView AverageAssignments;
    private final ArrayList<Long> ar_ids = new ArrayList<Long>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
    }

    // Initializing UI

    private void setupUI() {
        AverageAssignments = (TextView) findViewById(R.id.Average);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.MainFloatingButton);
        listView = findViewById(R.id.ListCourse);
        mdbHelper = new DataBaseHelper(this);
        calculateAverageAssignments();
        loadListViewCourses();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    // Open dialog fragment - Courses

    public void openDialog(){
        CourseDialog courseDialog = new CourseDialog();
        courseDialog.show(getSupportFragmentManager(), "CourseDialog");
    }

    // Calculate average of all assignments

    @SuppressLint("SetTextI18n")
    public void calculateAverageAssignments() {
        DataBaseHelper mdbhelper = new DataBaseHelper(this);
        List<Integer> grades;
        List<Assignments> assignments;
        assignments = mdbhelper.getAllAssignments();
        grades = mdbhelper.getAssignmentsGrades();
        String overallAverage = "Average of All Assignments: ";
        int sum = 0;
        int result;
        int numberAssignments = assignments.size();

        if (numberAssignments != 0) {
            for (int i : grades) {
                sum += i;
            }
            result = sum / numberAssignments;
            Log.d(TAG, "Number Assignments: " + numberAssignments);
            AverageAssignments.setText(overallAverage + result + "%");
        }
        else {
            AverageAssignments.setText(overallAverage + "N/A");
        }

    }

    // Loading Courses on ListView

    public void loadListViewCourses(){

        List<Course> mcourses = mdbHelper.getAllCourses();
        List<Assignments> mAssignmentsList = mdbHelper.getAllAssignmentsByCourse(AssignmentActivity.mCourseId);

        ListCoursesAdapter madapter = new ListCoursesAdapter(this, R.layout.adapter_courses, mcourses, mAssignmentsList);
        listView.setAdapter(madapter);

        // Clickable ListView

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Getting courses ids and passing them as an extra

                ArrayList<Long> ar_ids;
                listviewPosition = position;
                ar_ids = mdbHelper.GetCoursesIDS();
                String name = listView.getItemAtPosition(position).toString();
                Intent intent = new Intent(view.getContext(), AssignmentActivity.class);
                intent.putExtra(getString(R.string.CourseIDString),ar_ids.get(position));
                intent.putExtra(getString(R.string.CourseName), name);
                startActivity(intent);

            }
        });

    }
}