package com.example.androiddeveloperbasics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainToolbar extends AppCompatActivity {
    public void displayToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public boolean onSelectItems(MenuItem item, AppCompatActivity activity) {
//        getString 은 context 가 존재하는 환경에서 실행 되기 때문에 함수 호출 컨텍스트를 불러옴
        Context context = activity.getApplicationContext();
        switch (item.getItemId()) {
            case R.id.action_order:
//                displayToast(context.getString(R.string.action_order_message), context);
                Intent intent = new Intent(context, OrderActivity.class);
                activity.startActivity(intent);
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
                displayToast(context.getString(R.string.action_settings), context);
                return true;
            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);
    }
}
