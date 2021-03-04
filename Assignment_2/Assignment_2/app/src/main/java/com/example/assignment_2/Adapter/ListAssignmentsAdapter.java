/*
 * Name: Paulina Navarro Aviles
 * ID: 40109825
 */

package com.example.assignment_2.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.assignment_2.Activities.AssignmentActivity;
import com.example.assignment_2.Database.DataBaseHelper;
import com.example.assignment_2.Model.Assignments;
import com.example.assignment_2.Model.Course;
import com.example.assignment_2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * List Assignments Adapter Class
 * This class is the custom adapter of the assignments.
 * It gets called from the GradeActivity class.
 */


public class ListAssignmentsAdapter extends BaseAdapter {
    private static final String TAG = "ListAssignmentAdapter";
    private Context mcontext;
    private List<Assignments> massignments;
    public static Assignments assignments;
    private int layoutResourceID;


    public ListAssignmentsAdapter() {}

    // CourseAdapter Constructor

    public ListAssignmentsAdapter(Context context, int resource, List<Assignments> assignments) {

        this.layoutResourceID = resource;
        this.mcontext = context;
        this.massignments = assignments;
    }

    // Get Courses ArrayList Size

    @Override
    public int getCount() {
        return massignments.size();
    }

    // Get Item inside Courses ArrayList

    @Override
    public Assignments getItem(int position) {
        return massignments.get(position);
    }

    // Get position

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Displaying all the Assignments Information.

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Assignments data;

        assignments = this.getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.adapter_assignments, parent, false);
        }

        data = massignments.get(position);

        TextView assignment = (TextView) convertView.findViewById(R.id.txt_assignment_name);
        TextView grade = (TextView) convertView.findViewById(R.id.txt_assignment_grade);


        String assignments_array = " ";
        String assignments_grade = " ";

        assignments_array += data.getTitle();
        assignments_grade += data.getGrade();

        assignment.setText(assignments_array);
        grade.setText(assignments_grade + " %");

        return convertView;
    }


}
