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
import com.example.assignment_2.Database.DataBaseHelper;
import com.example.assignment_2.Activities.MainActivity;
import com.example.assignment_2.R;

/**
 * Course Dialog class
 * This class deals with the course fragment.
 * It calls the DataBaseHelper class to insert a new course into the database.
 */


public class CourseDialog extends DialogFragment {
    private EditText meditTextCourseTitle;
    private EditText meditTextCourseCode;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_coursedialogfragment, container, false);

        meditTextCourseTitle = view.findViewById(R.id.edit_courseTitle);
        meditTextCourseCode = view.findViewById(R.id.edit_courseCode);
        Button msaveCourseButton = view.findViewById(R.id.SaveButton_Course);
        Button mcancelCourseButton = view.findViewById(R.id.CancelButton_Course);

        // Save Button

        msaveCourseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String title = meditTextCourseTitle.getText().toString();
                String code = meditTextCourseCode.getText().toString();
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
                long id = -1;

                // Insert course in database and display it

                if(!(title.equals("")|| code.equals(""))) {
                    dataBaseHelper.insertCourse(new Course(id, title, code));
                    ((MainActivity)getActivity()).loadListViewCourses();
                    getDialog().dismiss();
                }
            }
        });

        // Cancel Button

        mcancelCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }



    }