package com.example.androiddeveloperbasics;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Toolbar myToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return new MainToolbar().onSelectItems(item, this);
    }

    public void onCLickShowAlert(View view) {
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(DialogActivity.this);

//        Set the dialog title and message.
        myAlertBuilder.setTitle("Alert");
        myAlertBuilder.setMessage("Click OK to continue, or Cancel to stop:");

//        Add the dialog buttons.
        myAlertBuilder.setPositiveButton("OK", (dialog, which) -> {
//            User clicked OK button.
            Toast.makeText(getApplicationContext(), "Pressed OK",
                    Toast.LENGTH_SHORT).show();
        });
        myAlertBuilder.setNegativeButton("Cancel", (dialog, which) -> {
//            User cancelled the dialog.
            Toast.makeText(getApplicationContext(), "Pressed Cancel",
                    Toast.LENGTH_SHORT).show();
        });

//        Create and show the AlertDialog.
        myAlertBuilder.show();
    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string +
                "/" + day_string + "/" + year_string);
        Toast.makeText(this, "Date: " + dateMessage,
                Toast.LENGTH_SHORT).show();
    }

    public void showTimePicker(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void processTimePickerResult(int hour, int minute) {
        String hour_string = Integer.toString(hour);
        String minute_string = Integer.toString(minute);
        String dateMessage = (hour_string + ":" + minute_string);
        Toast.makeText(this, "Time " + dateMessage,
                Toast.LENGTH_SHORT).show();
    }
}