package com.example.hooli;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Time;

public class TaskDatabase extends SQLiteOpenHelper {

    // database name
    private static final String DATABASE_NAME = "TaskDatabase";

    private static final String TABLE_NAME = "TASK_TABLE"




    //Columns values
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String BLOCKING = "blocking";
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final String COMPLETED = "completed";



    public TaskDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASK_TABLE = "CREATE TABLE " + "TABLE_NAME"  +
                "("
                + ID + " INTEGER PRIMARY KEY,"
                + TITLE + " TEXT,"
                + BLOCKING + " BOOLEAN,"
                + DATE + " TEXT,"
                + TIME + " INTEGER, "
                + COMPLETED + " BOOLEAN" + ")";

        db.execSQL(CREATE_TASK_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion) {
            return ;
        }

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public int addTask(TaskItem task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TITLE, task.getTime());
        values.put(BLOCKING, task.getBlocking());
        values.put(DATE, task.getDate());
        values.put(TIME, task.getTime());
        values.put(COMPLETED, task.getCompleted());

    }


}
