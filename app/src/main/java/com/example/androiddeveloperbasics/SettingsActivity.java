package com.example.androiddeveloperbasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    public static final String KEY_PREF_EXAMPLE_SWITCH = "example_switch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

//        액션바 세팅
        Toolbar myToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new SettingsFragment())
                .commit();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean switchPref = sharedPref.getBoolean
                (SettingsActivity.KEY_PREF_EXAMPLE_SWITCH, false);
        Toast.makeText(this, switchPref.toString(), Toast.LENGTH_SHORT).show();
    }
}