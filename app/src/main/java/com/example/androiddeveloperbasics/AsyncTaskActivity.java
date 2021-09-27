package com.example.androiddeveloperbasics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsyncTaskActivity extends AppCompatActivity {
    private TextView mTextView;
    private ProgressBar mProgressBar;
    private static final String TEXT_STATE = "currentText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        Toolbar myToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTextView = findViewById(R.id.textView1);
        mProgressBar = findViewById(R.id.progressBar);

//        Restore TextView if there is a savedInstanceState
        if (savedInstanceState != null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        Save the state of the TextView
        outState.putString(TEXT_STATE, mTextView.getText().toString());
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

    public void startTask(View view) {
//        Put a message in the text view
        mTextView.setText(R.string.napping);

//        Start the AsyncTask.
        new SimpleAsyncTask(mTextView, mProgressBar).execute();
    }

}