package com.example.androiddeveloperbasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class CafeActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.android.droidcafe.extra.MESSAGE";
    private String mOrderMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe);

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
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this.getApplicationContext(),
                        "action_settings", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_favorite:
                Toast.makeText(this.getApplicationContext(),
                        "action_favorite", Toast.LENGTH_SHORT).show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void showDonutOrder(View view) {
        mOrderMessage = getString(R.string.donut_order_message);
        displayToast(mOrderMessage);
    }

    public void showIceCreamOrder(View view) {
        mOrderMessage = getString(R.string.ice_cream_order_message);
        displayToast(mOrderMessage);
    }

    public void showFroyoOrder(View view) {
        mOrderMessage = getString(R.string.froyo_order_message);
        displayToast(mOrderMessage);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        Intent intent = new Intent(CafeActivity.this, OrderActivity.class);
        intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
        startActivity(intent);
    }
}