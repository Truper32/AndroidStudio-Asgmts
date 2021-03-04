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
import com.example.assignment_2.Activities.MainActivity;
import com.example.assignment_2.Model.Assignments;
import com.example.assignment_2.Model.Course;
import com.example.assignment_2.R;
import java.util.List;

/**
 * CourseAdapter Class
 * This class is the custom adapter of the courses.
 * It gets called from the GradeActivity class.
 */

public class ListCoursesAdapter extends BaseAdapter {

    private static final String TAG = "ListCoursesAdapter";
    public Context mcontext;
    private List<Course> mcourses;
    private List<Assignments>massignments;
    private int layoutResourceID;

    // ListCourseAdapter Constructor

    public ListCoursesAdapter(Context context, int resource, List<Course> courses, List<Assignments> assignments) {

        this.layoutResourceID = resource;

        //this.Grades = grades;
        this.mcontext = context;
        this.mcourses = courses;
        this.massignments = assignments;
    }

    // Get Courses ArrayList Size

    @Override
    public int getCount() {
        return mcourses.size();
    }

    // Get Item inside Courses ArrayList

    @Override
    public Object getItem(int position) {
        return mcourses.get(position);
    }


    // Get position

    @Override
    public long getItemId(int position) {
        return position;
    }


    // Displaying all the Courses Information.

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Course data;
        int grades = 0;

        if (convertView == null) {
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.adapter_courses, parent, false);
        }
        data = mcourses.get(position);

        TextView course = (TextView) convertView.findViewById(R.id.txt_course_name);
        TextView code = (TextView) convertView.findViewById(R.id.txt_course_code);
        TextView average = (TextView) convertView.findViewById(R.id.txt_average);

        String courses_array = " ";
        String courses_code = " ";
        String average_grade = "Average grade: ";

        courses_array += data.getTitle();
        courses_code += data.getCode();


        for (int i = 0; i < massignments.size() ; i++) {
            if (massignments.size() != 0) {
                grades += Integer.parseInt(massignments.get(i).getGrade()) / massignments.size();
            }
        }

        // TODO --- FIX COURSE AVERAGE DISPLAY - FOR EACH COURSE

        if(MainActivity.listviewPosition == position)
            average.setText(average_grade + grades + "%");
        if(MainActivity.listviewPosition != position)
            average.setText(average_grade + "N/A");

        course.setText(courses_array);
        code.setText(courses_code);

        return convertView;
    }

}

