/*
 * Name: Paulina Navarro Aviles
 * ID: 40109825
 */

package com.example.assignment_2.Model;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.assignment_2.Activities.AssignmentActivity;
import com.example.assignment_2.Database.DataBaseHelper;
import com.example.assignment_2.R;

import java.util.List;

/**
 * Assignment Dialog class
 * This class deals with the assignment fragment
 * It calls the DataBaseHelper class to insert a new assignment into the database.
 */

public class AssignmentDialog extends DialogFragment{

    private EditText meditTextAssignmentTitle;
    private EditText meditTextAssignmentGrade;
    private Button msaveAssignmentButton;
    private Button mcancelAssignmentButton;
    private DataBaseHelper mdbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_assignmentdialogfragment, container, false);
        meditTextAssignmentTitle = view.findViewById(R.id.edit_assignmentTitle);
        meditTextAssignmentGrade= view.findViewById(R.id.edit_assignmentGrade);
        msaveAssignmentButton = view.findViewById(R.id.SaveButton_Assignment);
        mcancelAssignmentButton = view.findViewById(R.id.CancelButton_Assignment);

        // Save Button

        msaveAssignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = meditTextAssignmentTitle.getText().toString();
                String grade = meditTextAssignmentGrade.getText().toString();
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
                long id = -1;

                // Insert assignments in database and display them (Up to 10 courses)

                if(!(title.equals("") || grade.equals(""))) {

                    if(AssignmentActivity.mCourseId == 1){
                        dataBaseHelper.insertAssignment(new Assignments(id, title, grade), 1);
                        ((AssignmentActivity) getActivity()).loadListViewAssignments();
                        getDialog().dismiss();
                    }
                    else  if(AssignmentActivity.mCourseId == 2){
                        dataBaseHelper.insertAssignment(new Assignments(id, title, grade),2);
                        ((AssignmentActivity)getActivity()).loadListViewAssignments();
                        getDialog().dismiss();
                    }
                    else  if(AssignmentActivity.mCourseId == 3){
                        dataBaseHelper.insertAssignment(new Assignments(id, title, grade),3);
                        ((AssignmentActivity)getActivity()).loadListViewAssignments();
                        getDialog().dismiss();
                    }
                    else  if(AssignmentActivity.mCourseId == 4){
                        dataBaseHelper.insertAssignment(new Assignments(id, title, grade),4);
                        ((AssignmentActivity)getActivity()).loadListViewAssignments();
                        getDialog().dismiss();
                    }
                    else  if(AssignmentActivity.mCourseId == 5){
                        dataBaseHelper.insertAssignment(new Assignments(id, title, grade),5);
                        ((AssignmentActivity)getActivity()).loadListViewAssignments();
                        getDialog().dismiss();
                    }
                    else  if(AssignmentActivity.mCourseId == 6){
                        dataBaseHelper.insertAssignment(new Assignments(id, title, grade),6);
                        ((AssignmentActivity)getActivity()).loadListViewAssignments();
                        getDialog().dismiss();
                    }
                    else  if(AssignmentActivity.mCourseId == 7){
                        dataBaseHelper.insertAssignment(new Assignments(id, title, grade),7);
                        ((AssignmentActivity)getActivity()).loadListViewAssignments();
                        getDialog().dismiss();
                    }
                    else  if(AssignmentActivity.mCourseId == 8){
                        dataBaseHelper.insertAssignment(new Assignments(id, title, grade),8);
                        ((AssignmentActivity)getActivity()).loadListViewAssignments();
                        getDialog().dismiss();
                    }
                    else  if(AssignmentActivity.mCourseId == 9){
                        dataBaseHelper.insertAssignment(new Assignments(id, title, grade),9);
                        ((AssignmentActivity)getActivity()).loadListViewAssignments();
                        getDialog().dismiss();
                    }
                    else  if(AssignmentActivity.mCourseId == 10){
                        dataBaseHelper.insertAssignment(new Assignments(id, title, grade),10);
                        ((AssignmentActivity)getActivity()).loadListViewAssignments();
                        getDialog().dismiss();
                    }
                }
            }

        });

        // Cancel Button

        mcancelAssignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return view;
    }
}