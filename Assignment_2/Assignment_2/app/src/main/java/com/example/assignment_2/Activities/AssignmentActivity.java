/*
 * Name: Paulina Navarro Aviles
 * ID: 40109825
 */

package com.example.assignment_2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.assignment_2.Adapter.ListAssignmentsAdapter;
import com.example.assignment_2.Database.DataBaseHelper;
import com.example.assignment_2.Model.AssignmentDialog;
import com.example.assignment_2.Model.Assignments;
import com.example.assignment_2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

/**
 * Assignment Activity Class
 * This class is the child activity to MainActivity
 * It deals with the course assignments, and, when needed, calls the DataBaseHelper class
 */


public class AssignmentActivity extends AppCompatActivity{
    private static final String TAG = "AssignmentActivity";
    public static long mCourseId = -1;
    private ListView listView;
    TextView courseAssignmentsName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        setupUI();
    }

    // Initializing UI

    private void setupUI() {
        getSupportActionBar().setTitle(R.string.assignment_activity_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button deleteButton = (Button) findViewById(R.id.DeleteCourseButton);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.AssignmentsFloatingButton);
        listView = findViewById(R.id.ListAssignments);
        courseAssignmentsName = (TextView) findViewById(R.id.AssignmentsTextViewName);
        loadListViewAssignments();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
    }


    // Delete a course method

    private void delete() {
        DataBaseHelper mdbHelper = new DataBaseHelper(this);
        Intent intent  = getIntent();

        if(intent != null) {
            mCourseId = intent.getLongExtra(getString(R.string.CourseIDString),-1);
        }
        mdbHelper.deleteCourse(mCourseId);
        startActivity(new Intent(AssignmentActivity.this, MainActivity.class));
        Toast.makeText(AssignmentActivity.this, "Course Deleted", Toast.LENGTH_SHORT).show();

    }

    // Open dialog fragment - Assignments

    public void openDialog(){
        AssignmentDialog assignmentDialog = new AssignmentDialog();
        assignmentDialog.show(getSupportFragmentManager(), "Assignment Dialog");

    }

    // Load all assignments in the listView

    public void loadListViewAssignments() {
        DataBaseHelper mdbHelper = new DataBaseHelper(this);
        Intent intent1  = getIntent();
        Intent intent2 = getIntent();
        String name;

        if(intent1 != null && intent2 != null) {
           mCourseId = intent1.getLongExtra(getString(R.string.CourseIDString),-1);
           name = intent2.getStringExtra(getString(R.string.CourseName));
           courseAssignmentsName.setText(name);
        }
        if(mCourseId != -1) {
            List<Assignments> mAssignmentsList = mdbHelper.getAllAssignmentsByCourse(mCourseId);

            // Fill the listView
            if (mAssignmentsList != null && !mAssignmentsList.isEmpty()) {

                ListAssignmentsAdapter madapter = new ListAssignmentsAdapter(this, R.layout.adapter_assignments, mAssignmentsList);
                listView.setAdapter(madapter);
            }
        }
    }
}