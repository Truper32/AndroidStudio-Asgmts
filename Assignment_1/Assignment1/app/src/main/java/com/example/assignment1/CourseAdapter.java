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
 * CourseAdapter Class
 * This class is the custom adapter of the percentage grades.
 * It gets called from the GradeActivity class.
 */

public class CourseAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Course> courses;
    private ArrayList<Assignment> assignments_i = new ArrayList<>();
    private int layoutResourceID;

    // CourseAdapter Constructor

    public CourseAdapter(Context context, int resource, ArrayList<Course> courses) {

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
    public Course getItem(int position) {
        return courses.get(position);
    }

    // Get position

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Displaying all the Courses and Assignments Information.

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Course data;
        int number1 = position;
        int number2;
        int total = 0;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.courseadapter_layout, parent, false);
        }

        data = courses.get(number1);
        assignments_i = data.getAssignments();

        TextView course = (TextView) convertView.findViewById(R.id.adapter_Courses);
        TextView assignment = (TextView) convertView.findViewById(R.id.adapter_Assignments);
        TextView grade = (TextView) convertView.findViewById(R.id.adapter_Grades_x);
        TextView average = (TextView) convertView.findViewById(R.id.adapter_Average);

        String courses_array = "Courses: ";
        String assignments_array = "Assignments: \n";
        String grades_array = "\n";
        number2 = assignments_i.size();
        courses_array += data.getCourseTitle();

        // Putting into Strings  all the Assignments Information

        for (int j = 0; j < assignments_i.size(); j++) {
            assignments_array += assignments_i.get(j).getAssignmentTitle() + "\n";
            grades_array += assignments_i.get(j).getAssGrade() + "\n";
            total += (data.getAssignments().get(j).getAssGrade())/(number2);
        }

        course.setText(courses_array);
        assignment.setText(assignments_array);
        grade.setText(grades_array);
        average.setText("Average:   " + total + "");

        return convertView;
   }
}
