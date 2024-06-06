package com.example.simpleui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "courseManager";
    private static final String TABLE_COURSES = "courses";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";

    // Create Sqlite Database with name and version
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table with fields
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COURSES_TABLE = "CREATE TABLE " + TABLE_COURSES
                + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT" + ")";
        db.execSQL(CREATE_COURSES_TABLE);
    }

    // Drop table if exists
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        onCreate(db);
    }

    // Add new course
    void addCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, course.getId());
        values.put(KEY_NAME, course.getName());
        values.put(KEY_DESCRIPTION, course.getDescription());

        db.insert(TABLE_COURSES, null, values);
        db.close();
    }

    // Get course by ID
    Course getCourse(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_COURSES, new String[]{KEY_ID,
                        KEY_NAME, KEY_DESCRIPTION}, KEY_ID + "=?",
                new String[]{id}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Course course = new Course(cursor.getString(0),
                cursor.getString(1), cursor.getString(2));
        cursor.close();
        return course;
    }

    // Get all courses
    List<Course> getAllCourses() {

        List<Course> courseList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_COURSES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Course course = new Course(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2));
                courseList.add(course);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return courseList;
    }

    // Update course
    int updateCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, course.getName());
        values.put(KEY_DESCRIPTION, course.getDescription());
        return db.update(TABLE_COURSES, values, KEY_ID + " = ?",
                new String[]{course.getId()});
    }

    // Delete course
    void deleteCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COURSES, KEY_ID + " = ?",
                new String[]{course.getId()});
        db.close();
    }
}
