package com.example.hooli;

public class TaskItem {

    private int Id;
    private String title;
    private String date;
    private String time;
    private String completed;
    private String blocking;
    private String repeat;

    public TaskItem(int id, String title, String date, String time, String completed, String blocking, String repeat) {
        this.Id = id;
        this.title = title;
        this.date = date;
        this.time = time;
        this.completed = completed;
        this.blocking = blocking;
        this.repeat = repeat;
    }


    public TaskItem(String title, String date, String time, String completed, String blocking, String repeat) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.completed = completed;
        this.blocking = blocking;
        this.repeat = repeat;
    }

    //setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void setTime(String time) {
        this.title = time;
    }
    public void setCompleted(String completed) {
        this.completed = completed;
    }
    public void setRepeat(String repeat) { this.repeat = repeat; }

    public void setBlocking(String blocking) {
        this.blocking = blocking;
    }


    //set getters

    public int getId() {
        return this.Id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public String getCompleted() {
        return this.completed;
    }

    public String getBlocking() {
        return this.blocking;
    }

    public String getRepeat(){
        return this.repeat;
    }




}
