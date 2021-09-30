package com.example.androiddeveloperbasics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainToolbar {
    public void displayToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public boolean onSelectItems(MenuItem item, AppCompatActivity activity) {
//        getString 은 context 가 존재하는 환경에서 실행 되기 때문에 함수 호출 컨텍스트를 불러옴
        Context context = activity.getApplicationContext();
        switch (item.getItemId()) {
            case android.R.id.home:
            case R.id.action_order:
//                displayToast(context.getString(R.string.action_order_message), context);
                Intent orderIntent = new Intent(context, OrderActivity.class);
                activity.startActivity(orderIntent);
                return true;
            case R.id.action_status:
                displayToast(context.getString(R.string.action_status_message), context);
                return true;
            case R.id.action_favorites:
                displayToast(context.getString(R.string.action_favorites_message), context);
                return true;
            case R.id.action_contact:
                displayToast(context.getString(R.string.action_contact_message), context);
                return true;
            case R.id.action_settings:
                Intent settingsIntent = new Intent(context, SettingsActivity.class);
                activity.startActivity(settingsIntent);
                return true;
            case R.id.night_mode:
//                Get the night mode state of the app.
                int nightMode = AppCompatDelegate.getDefaultNightMode();
//                Set the theme mode for the restarted activity
                if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
//                Recreate the activity for the theme change to take effect.
                activity.recreate();
                return true;
            default:
                // Do nothing
        }
        return new AppCompatActivity().onOptionsItemSelected(item);
    }

    public boolean onCreateMenu(Menu menu, AppCompatActivity activity) {
        // Change the label of the menu based on the state of the app.
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }
}
