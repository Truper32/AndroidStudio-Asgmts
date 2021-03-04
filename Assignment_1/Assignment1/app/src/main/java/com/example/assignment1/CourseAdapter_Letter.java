/*
 * Name: Paulina Navarro Aviles
 * ID: 40109825
 */
package com.example.assignment1;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * CourseAdapter_Letter Class.
 * This class is the custom adapter of the letter grades.
 * It gets called from the GradeActivity class.
 */

public class CourseAdapter_Letter  extends BaseAdapter {

    private Context context;
    private ArrayList<Course> courses;
    private ArrayList<Assignment> assignments_i = new ArrayList<>();
    private ArrayList<String> course_letter_array;
    private ArrayList<String> average;
    private int layoutResourceID;

    // CourseAdapter_Letter Constructor

    public CourseAdapter_Letter(Context context, int resource, ArrayList<Course> courses) {

        this.layoutResourceID = resource;
        this.context = context;
        this.courses = courses;

    }

    // Get Courses ArrayList Size

    @Override
    public int getCount() {
        return courses.size();
    }

    // Get Item inside Courses ArrayList

    @Override
    public Object getItem(int position) {
        return courses.get(position);
    }

    // Get position

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Displaying all the Courses and Assignments Information

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Course data;
        int number1 = position;
        int number2;
        int total = 0;
        average = new ArrayList<String>();
        course_letter_array = new ArrayList<String>();
        data = courses.get(number1);
        assignments_i = data.getAssignments();

        //

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.courseadapter_layout_grades, parent, false);
        }

        TextView course_letter = (TextView) convertView.findViewById(R.id.adapter_Courses_letter);
        TextView assignment_letter = (TextView) convertView.findViewById(R.id.adapter_Assignments_letter);
        TextView grade_letter = (TextView) convertView.findViewById(R.id.adapter_Grades_letter);
        TextView average_letter = (TextView) convertView.findViewById(R.id.adapter_Average_letter);

        String courses_array = "Courses: ";
        String assignments_array = "Assignments: \n";
        String grades_array_letter = "\n";
        String average_array = "";

        number2 = assignments_i.size();

        courses_array += data.getCourseTitle();

        // Converting to Letter and Putting into Strings all the Assignments Information

        for (int j = 0; j < assignments_i.size(); j++) {
            assignments_array += assignments_i.get(j).getAssignmentTitle() + "\n";
            total += (assignments_i.get(j).getAssGrade()) / (number2);

            if (total > 90) {
                average.add("A" + "\n");
            } else if (total <= 90 && total > 70) {
                average.add("B" + "\n");
            } else if (total <= 70 && total > 60) {
                average.add("C" + "\n");
            } else if (total <= 60 && total > 50) {
                average.add("D" + "\n");
            } else {
                average.add("F" + "\n");
            }
            average_array = average.get(j);

            if (assignments_i.get(j).getAssGrade() > 90) {
                course_letter_array.add("A" + "\n");
            } else if (assignments_i.get(j).getAssGrade() <= 90 && assignments_i.get(j).getAssGrade() > 70) {
                course_letter_array.add("B" + "\n");
            } else if (assignments_i.get(j).getAssGrade() <= 70 && assignments_i.get(j).getAssGrade() > 60) {
                course_letter_array.add("C" + "\n");
            } else if (assignments_i.get(j).getAssGrade() <= 60 && assignments_i.get(j).getAssGrade() > 50) {
                course_letter_array.add("D" + "\n");
            } else {
                course_letter_array.add("F" + "\n");
            }
            grades_array_letter += course_letter_array.get(j);
        }

        course_letter.setText(courses_array);
        assignment_letter.setText(assignments_array);
        grade_letter.setText(grades_array_letter);
        average_letter.setText("Average:   " + average_array);

        return convertView;
    }
}
