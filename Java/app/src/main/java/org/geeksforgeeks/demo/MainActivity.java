package org.geeksforgeeks.demo;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity extends AppCompatActivity {
    private SwitchMaterial darkModeSwitch;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        darkModeSwitch = findViewById(R.id.darkModeSwitch);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);

        // Apply the saved theme preference
        if (isDarkModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            darkModeSwitch.setChecked(true);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            darkModeSwitch.setChecked(false);
        }

        // Toggle theme when switch is clicked
        darkModeSwitch.setOnClickListener(v -> {
            boolean newDarkModeState = !isDarkModeOn; // Toggle state
            if (newDarkModeState) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            // Save preference
            editor.putBoolean("isDarkModeOn", newDarkModeState);
            editor.apply();
        });
    }
}
