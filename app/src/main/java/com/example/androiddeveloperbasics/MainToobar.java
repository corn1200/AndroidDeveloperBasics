package com.example.androiddeveloperbasics;

import android.app.Activity;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainToobar extends AppCompatActivity {

    public boolean onSelectItems(MenuItem item, AppCompatActivity activity) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(activity.getApplicationContext(),
                        "action_settings", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_favorites:
                Toast.makeText(activity.getApplicationContext(),
                        "action_favorite", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_order:
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
