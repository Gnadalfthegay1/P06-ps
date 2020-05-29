package com.myapplicationdev.android.p05_ps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "revisionnote.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TASK = "task";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TASK = "task";
    private static final String COLUMN_TASK_CONTENT = "task_content";
    private static final String COLUMN_SEC= "sec";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE Note
        // (id INTEGER PRIMARY KEY AUTOINCREMENT, note_content TEXT, rating
        // INTEGER );
        String createNoteTableSql = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TASK + " TEXT, "
                + COLUMN_TASK_CONTENT + " TEXT, " + COLUMN_SEC + " INTEGER )";
        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);
    }

    public void inserTask(String task, String taskContent, int sec) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the note content as value
        values.put(COLUMN_TASK, task);
        // Store the column name as key and the rating as value
        values.put(COLUMN_TASK_CONTENT, taskContent);

        values.put(COLUMN_SEC, sec);
        // Insert the row into the TABLE_NOTE
        db.insert(TABLE_TASK, null, values);
        // Close the database connection
        db.close();
    }


    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> notes = new ArrayList<Task>();
        // "SELECT id, note_content, stars FROM note"
        String selectQuery = "SELECT * FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String taskTitle = cursor.getString(1);
                String taskContent = cursor.getString(2);
                int sec = cursor.getInt(3);

                Task task = new Task(id, taskTitle, taskContent, sec);
                notes.add(task);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return notes;
    }

    public ArrayList<String> getTask() {
        //TODO return records in Strings

        // Create an ArrayList that holds String objects
        ArrayList<String> notes = new ArrayList<String>();
        // Select all the notes' content
        String selectQuery = "SELECT " + COLUMN_TASK+ " FROM "
                + TABLE_TASK;

        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        // Run the SQL query and get back the Cursor object
        Cursor cursor = db.rawQuery(selectQuery, null);
        // moveToFirst() moves to first row
        if (cursor.moveToFirst()) {
            // Loop while moveToNext() points to next row and returns true;
            // moveToNext() returns false when no more next row to move to
            do {
                // Add the note content to the ArrayList object
                notes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return notes;
    }
}