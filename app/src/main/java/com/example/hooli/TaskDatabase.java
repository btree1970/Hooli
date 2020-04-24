package com.example.hooli;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TaskDatabase extends SQLiteOpenHelper {

    // database name
    private static final String DATABASE_NAME = "TaskDatabase.db";

    private static final String TABLE_NAME = "TASK_TABLE";

    //Columns values
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String BLOCKING = "blocking";
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final String COMPLETED = "completed";
    private static final String REPEAT = "repeat";



    public TaskDatabase(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASK_TABLE = "CREATE TABLE " + TABLE_NAME  +
                "("
                + ID + " INTEGER PRIMARY KEY,"
                + TITLE + " TEXT,"
                + DATE + " TEXT,"
                + TIME + " INTEGER, "
                + COMPLETED + " BOOLEAN, "
                + BLOCKING + " BOOLEAN,"
                + REPEAT + " BOOLEAN"
                + ")";

        try {
            db.execSQL(CREATE_TASK_TABLE);
//            Message.message(context, "onCreate() called");
        } catch (SQLException e) {
              System.out.println(e);
//            Message.message(context,e.toString());
        }



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

        values.put(TITLE, task.getTitle());
        values.put(DATE, task.getDate());
        values.put(TIME, task.getTime());
        values.put(COMPLETED, task.getCompleted());
        values.put(BLOCKING, task.getBlocking());
        values.put(REPEAT, task.getRepeat());

        long ID = db.insert(TABLE_NAME, null, values);
        db.close();

        return (int) ID;

    }

    public TaskItem getTask(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{
                TITLE,
                DATE,
                TIME,
                COMPLETED,
                BLOCKING,
                REPEAT,
        }, ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        TaskItem Task = new TaskItem(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6));

        return Task;

    }

    public List<TaskItem> getAllTasks() {
        List<TaskItem> TaskItems = new ArrayList<>();

        String query = "SELECT * FROM  " +  TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();


        Cursor cursor;

        try {
            cursor = db.rawQuery(query, null);

//            Message.message(context, "onCreate() called");
        } catch (SQLException e) {
//            Message.message(context,e.toString());
            System.out.println(e);
            return null;
        }


        if (cursor.moveToFirst()) {
            do {
                TaskItem Task = new TaskItem(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                );

                TaskItems.add(Task);

            } while (cursor.moveToNext());
        }

        return TaskItems;
    }

    public void deleteTask(TaskItem Item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + "=?",
                   new String[]{String.valueOf(Item.getId())});
        db.close();
    }

    public int updateTask(TaskItem Item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TITLE, Item.getTitle());
        values.put(DATE, Item.getDate());
        values.put(TIME, Item.getTime());
        values.put(COMPLETED, Item.getCompleted());
        values.put(BLOCKING, Item.getBlocking());
        values.put(REPEAT, Item.getRepeat());



        return db.update(TABLE_NAME, values, ID + "=?",
                new String[]{String.valueOf(Item.getId())});
    }


}
