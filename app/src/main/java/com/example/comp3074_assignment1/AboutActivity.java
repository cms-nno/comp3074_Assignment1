package com.example.comp3074_assignment1;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Show the back (Up) arrow in the ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Finish this activity so we return to the existing MainActivity
            // instead of creating a new instance. Also works with
            // android:parentActivityName + launchMode="singleTop" in the manifest.
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
