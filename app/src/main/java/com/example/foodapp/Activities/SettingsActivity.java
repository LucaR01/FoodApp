package com.example.foodapp.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.ImageView;

import com.example.foodapp.Fragments.SettingsFragment;
import com.example.foodapp.R;

public class SettingsActivity extends AppCompatActivity {

    private ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

        loadSettings();

        //initView(); //TODO: remove

        //onArrowBackPressed(); //TODO: remove

        //PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);

        /*ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
        //TODO: onBackPressed()?
        //TODO: return true?
    }

    //TODO: remove
    /*private void initView() {
        this.backArrow = findViewById(R.id.settingsBackArrow);
    }

    private void onArrowBackPressed() {
        this.backArrow.setOnClickListener(view -> {
            onBackPressed();
        });
    }*/

    //TODO: update
    private void loadSettings() {
        var sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        boolean themePreference = sp.getBoolean("dark_theme", false);
        System.out.println("themePreference: " + themePreference); //TODO: remove
    }

}