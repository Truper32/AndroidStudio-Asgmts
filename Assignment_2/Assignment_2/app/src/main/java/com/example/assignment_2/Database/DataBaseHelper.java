/*
 * Name: Paulina Navarro Aviles
 * ID: 40109825
 */

package com.example.assignment_2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import com.example.assignment_2.Model.Assignments;
import com.example.assignment_2.Model.Course;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Data Helper Class
 * This class is the Data Helper of the entire database. From here, the database tables are created.
 * This class gets called when we insert or delete courses and assignments or as needed.
 */


public class DataBaseHelper extends SQLiteOpenHelper {

    public static int mcounter = 0;
    private static final String DATABASE_NAME = Config.DATABASE_NAME_COURSES;
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DataBaseHelper";
    private SQLiteDatabase db;
    private Context context;
    private String[] mAllColumnsCourses = { Config.COLUMN_COURSE_ID,
            Config.COLUMN_COURSE_TITLE, Config.COLUMN_COURSE_CODE};
    private String[] mAllColumnsAssignments = { Config.COLUMN_ASSIGNMENT_ID,
            Config.COLUMN_COURSE_ID_ASSIGNMENTS,
            Config.COLUMN_ASSIGNMENT_TITLE, Config.COLUMN_ASSIGNMENT_GRADE};

    // Table Create Statements
    // SQL statements - Courses table creation

    private static final String CREATE_COURSE_TABLE = "CREATE TABLE " + Config.TABLE_COURSE_NAME + " ("
            + Config.COLUMN_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Config.COLUMN_COURSE_TITLE + " TEXT NOT NULL,"
            + Config.COLUMN_COURSE_CODE + " TEXT NOT NULL"
            + ")";

    // SQL statements - Assignments table creation

    private static final String CREATE_ASSIGNMENT_TABLE = "CREATE TABLE " + Config.TABLE_ASSIGNMENT_NAME + " ("
            + Config.COLUMN_ASSIGNMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Config.COLUMN_COURSE_ID_ASSIGNMENTS + " INTEGER NOT NULL,"
            + Config.COLUMN_ASSIGNMENT_TITLE + " TEXT NOT NULL,"
            + Config.COLUMN_ASSIGNMENT_GRADE + " TEXT NOT NULL"
             + ")";


    // Calling Constructor

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
        this.context = context;

    }

   // Create database

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_COURSE_TABLE);
        sqLiteDatabase.execSQL(CREATE_ASSIGNMENT_TABLE);

    }

    // Takes versions of database
    // Used on Upgrades to alter databases on upgrades
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Clear all data from tables
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_COURSE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_ASSIGNMENT_NAME);

        // Recreate table
        onCreate(db);

    }

    //***************************************************************************************************//
    //*---------------------------------- COURSES Table Methods ----------------------------------------*//
    //***************************************************************************************************//

    /**
     * Inserting Courses
     * @param course
     * @return
     */


    public long insertCourse(Course course){
        SQLiteDatabase db = this.getWritableDatabase();

        // Create content values that hold information

        long course_id = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_COURSE_TITLE, course.getTitle());
        contentValues.put(Config.COLUMN_COURSE_CODE, course.getCode());

        // Insert row - Throws Exception
        try{
            course_id = db.insertOrThrow(Config.TABLE_COURSE_NAME, null, contentValues);

        }catch (SQLException e ){
            Log.d(TAG, "Exception: " + e.getMessage());
            Toast.makeText(context, "Operation Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        // Close Database
        finally {
            db.close();
        }
        return course_id;
    }


    /**
     * Getting all courses
     * @return
     */

    public List<Course> getAllCourses(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try{

            cursor = db.query(Config.TABLE_COURSE_NAME, null, null, null, null, null, null);

            // If we have something
            if (cursor != null){

                // Looping through all rows - Move cursor to first row

                if(cursor.moveToFirst()){
                    List<Course> courseList = new ArrayList<>();
                    // Move cursor one row
                    do{
                        // Getting information from cursor
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_COURSE_ID));
                        String title = cursor.getString((cursor.getColumnIndex(Config.COLUMN_COURSE_TITLE)));
                        String code = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_CODE));

                        // Adding this course object to course list
                        courseList.add(new Course(id, title, code));
                    }while(cursor.moveToNext());
                    return courseList;
                }
            }
        }catch(Exception e){
            Log.d(TAG, "Exception: " + e.getMessage());
        }
        finally {
            if (cursor!=null)
                cursor.close();
            db.close();
        }
        //Returning empty list
        return Collections.emptyList();
    }

    /**
     * Get Course by its ID
     * @param id
     * @return
     */

    public Course getCourseById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Config.TABLE_COURSE_NAME,mAllColumnsCourses,Config.COLUMN_COURSE_ID + " = ?",
                new String[] {String.valueOf(id)}, null, null, null );
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Course course = cursorToCourse(cursor);
        Log.d(TAG, "Course: " + course);
        return course;

    }

    /**
     * Get a Complete Course when needed
     * @param cursor
     * @return
     */

    protected Course cursorToCourse(Cursor cursor) {
        Course course = new Course();
        course.setId(cursor.getLong(0));
        course.setTitle(cursor.getString(1));
        course.setCode(cursor.getString(2));
        return course;
    }


    // TODO !!! NO SE SI ESTA BIEN !!!!!!

    /**
     * Get ID's of courses in ListView
     * @return
     */
    public ArrayList<Long> GetCoursesIDS() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + Config.TABLE_COURSE_NAME;
        Cursor cur = db.rawQuery(sql, null);
        String out = "";
        ArrayList<Long> course_ids = new ArrayList<Long>();
        if (cur.moveToFirst()) {
            do {

                course_ids.add(cur.getLong(0));
            } while (cur.moveToNext());
        } else {

        }
        cur.close();
        return course_ids;
    }

    /**
     * Delete Course and all its assignments
     */

    public void deleteCourse(long id) {

        // Delete all assignment of this course

        List<Assignments> listAssignments = this.getAllAssignmentsByCourse(id);
        if (listAssignments != null && !listAssignments.isEmpty()) {
            for (Assignments assignments : listAssignments) {
                this.deleteAssignments(assignments);
            }
        }

        // Delete course

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE  FROM " + Config.TABLE_COURSE_NAME + " WHERE " + Config.COLUMN_COURSE_ID + "=" + id;

        Cursor cursor = null;
        try {
            db.execSQL(query);

        } catch (SQLiteException e) {
            Log.e("Error in delete Subjects", e.getMessage());
        }finally{
            if(cursor != null)
                cursor.close();

            db.close();
        }
    }


    //***************************************************************************************************//
    //* ------------------------------- ASSIGNMENTS Table Methods --------------------------------------*//
    //***************************************************************************************************//
        /**
         * Inserting Assignments
         * @param assignments
         * @param CourseID
         * @return
         */

        public long insertAssignment(Assignments assignments,  long CourseID){

            mcounter++;
            long id = -1;
            SQLiteDatabase db = this.getWritableDatabase();

            //Create content values that hold info

            ContentValues contentValues = new ContentValues();
            contentValues.put(Config.COLUMN_COURSE_ID_ASSIGNMENTS, CourseID);
            contentValues.put(Config.COLUMN_ASSIGNMENT_TITLE, assignments.getTitle());
            contentValues.put(Config.COLUMN_ASSIGNMENT_GRADE, assignments.getGrade());

            // Insert Row - Throws Exception
            try{
                id = db.insertOrThrow(Config.TABLE_ASSIGNMENT_NAME, null, contentValues);

            }catch (SQLException e ){
                Log.d(TAG, "Exception: " + e.getMessage());
                Toast.makeText(context, "Operation Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            // Closing  database

            finally {
                db.close();
            }
            return id;
        }

    /**
     * Get all Assignments
     * @return
     */
    public List<Assignments> getAllAssignments(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try{
            cursor = db.query(Config.TABLE_ASSIGNMENT_NAME, null, null, null, null, null, null);

            // If we have something
            if (cursor != null){

                // Move cursor to first row
                if(cursor.moveToFirst()){
                    List<Assignments> assignmentList = new ArrayList<>();
                    // Move cursor one row
                    do{
                        // Getting information from cursor

                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_ID));
                        String title = cursor.getString(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_TITLE));
                        String grade = cursor.getString(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_GRADE));

                        // Creating a new course object with the information and adding it to the course list

                        assignmentList.add(new Assignments(id, title, grade));
                    }while(cursor.moveToNext());

                    return assignmentList;
                }
            }

        }catch(Exception e){
            Log.d(TAG, "Exception: " + e.getMessage());
        }

        finally {
            if (cursor!=null)
                cursor.close();

            db.close();
        }
        //Returning Empty List
        return Collections.emptyList();
    }

    /**
     * Getting all Assignments of a Course
     * @param courseID
     * @return
     */

    public List<Assignments> getAllAssignmentsByCourse(long courseID) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Assignments> listAssignments = new ArrayList<Assignments>();


        Cursor cursor = db.query(Config.TABLE_ASSIGNMENT_NAME, mAllColumnsAssignments, Config.COLUMN_COURSE_ID_ASSIGNMENTS + " = ?",
                new String [] {String.valueOf(courseID)}, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Assignments assignments = this.cursorToAssignment(cursor);
            listAssignments.add(assignments);
            cursor.moveToNext();
        }
        // Close the cursor

        cursor.close();
        return listAssignments;


    }

    /**
     * Getting a complete assignment
     * @param cursor
     * @return
     */

    private Assignments cursorToAssignment(Cursor cursor) {
        Assignments assignments = new Assignments();
        assignments.setAssignmentId(cursor.getLong(0));
        assignments.setTitle(cursor.getString(2));
        assignments.setGrade(cursor.getString(3));

        // Get course by id

        long courseId = cursor.getLong(1);
        Course course = this.getCourseById(courseId);
        if (course != null)
            assignments.setCourseId(course);

        return assignments;
    }

    /**
     * Delete Assignments
     * @param assignments
     */


    public void deleteAssignments(Assignments assignments) {
        db = this.getWritableDatabase();
        long id = assignments.getAssignmentId();
        db.delete(Config.TABLE_ASSIGNMENT_NAME, Config.COLUMN_ASSIGNMENT_ID + " = " + id, null);
        db.close();
    }

    /**
     * Get Grades of all the Assignments
     * @return
     */
    public List <Integer> getAssignmentsGrades(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try{
            cursor = db.query(Config.TABLE_ASSIGNMENT_NAME, mAllColumnsAssignments, Config.COLUMN_ASSIGNMENT_GRADE, null, null, null, null);

            // If we have something
            if (cursor != null){

                // Looping through all rows - Move cursor to first row

                if(cursor.moveToFirst()){
                    List<Integer> grades = new ArrayList<>();
                    // Move cursor one row
                    do{
                        // Getting information from cursor
                        int grade = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ASSIGNMENT_GRADE));

                        // Adding this course object to course list
                        grades.add(grade);
                    }while(cursor.moveToNext());
                    return grades;
                }
            }
        }catch(Exception e){
            Log.d(TAG, "Exception: " + e.getMessage());
        }
        finally {
            if (cursor!=null)
                cursor.close();
            db.close();
        }
        //Returning empty list
        return Collections.emptyList();
    }

}
