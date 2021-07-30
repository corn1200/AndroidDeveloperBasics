package com.example.androiddeveloperbasics;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    public void onCLickShowAlert(View view) {
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(DialogActivity.this);

//        Set the dialog title and message.
        myAlertBuilder.setTitle("Alert");
        myAlertBuilder.setMessage("Click OK to continue, or Cancel to stop:");

//        Add the dialog buttons.
        myAlertBuilder.setPositiveButton("OK", (dialog, which) -> {
            Toast.makeText(getApplicationContext(), "Pressed OK", Toast.LENGTH_SHORT).show();
        });
        myAlertBuilder.setNegativeButton("Cancel", (dialog, which) -> {
            // User cancelled the dialog.
            Toast.makeText(getApplicationContext(), "Pressed Cancel",
                    Toast.LENGTH_SHORT).show();
        });
        myAlertBuilder.show();
    }
}