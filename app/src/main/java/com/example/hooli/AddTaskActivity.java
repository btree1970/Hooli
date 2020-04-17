package com.example.hooli;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.time.Month;
import java.time.Year;
import java.util.*;

public class AddTaskActivity extends AppCompatActivity {


    private Calendar cal;


    private String Title;
    private String Date;
    private String Time;

    private int Year, Month, Hour, Minute, Day;



    private EditText task_title;
    private TextView task_date;
    private TextView task_time;
    private Button submit_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task_activity);

        task_title = (EditText) findViewById(R.id.set_task_title);
        task_date = (TextView) findViewById(R.id.set_task_date);
        task_time = (TextView) findViewById(R.id.set_task_time);

        submit_btn = (Button) findViewById(R.id.save_task);

        submit_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Check for missing items and save the task
                if (task_title.getText().length() == 0) {
                    task_title.setError("Task title can't be empty!");
                } else {
                    saveTask();
                }
            }
        } );




        cal = Calendar.getInstance();

        Hour = cal.get((Calendar.HOUR_OF_DAY));
        Minute = cal.get(Calendar.MINUTE);
        Day = cal.get(Calendar.DATE);
        Year = cal.get(Calendar.YEAR);

        Date = Month + "/" + Day + "/" + Year;
        Time = Hour + ":" + Minute;


        task_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Title = s.toString().trim();
                task_title.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        task_date.setText(Date);
        task_time.setText(Time);


    }


    // On clicking Time picker
    public void setTime(View v){
        Calendar now = Calendar.getInstance();

        TimePickerDialog time_dialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        task_time.setText(hourOfDay + ":" + minute);
                    }
                }, now.HOUR_OF_DAY, now.MINUTE, false);
        time_dialog.show();
    }

    public void setDate(View v) {
        Calendar now = Calendar.getInstance();
        Year = now.get(Calendar.YEAR);
        Month = now.get(Calendar.MONTH);
        Day = now.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog date_dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    task_date.setText(dayOfMonth + "/" + month + "/" + year);
            }
        }, Year, Month, Day );

        date_dialog.show();

    }

    public void saveTask() {
        TaskDatabase db = new TaskDatabase(this);

        int ID = db.addTask(
                new TaskItem(
                      Title,
                      Date,
                      Time,
                      "false",
                      "false"

                )
        );

        Toast.makeText(getApplicationContext(), "Saved",
                Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
