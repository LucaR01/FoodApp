package com.example.foodapp.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.foodapp.Activities.PrivacyPolicyActivity;
import com.example.foodapp.Activities.TermsAndConditionsActivity;
import com.example.foodapp.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        Preference themePreference = findPreference("theme_preference");

        //TODO: remove commented code?
        /*themePreference.setOnPreferenceChangeListener(((preference, newValue) -> {
            boolean isDarkThemeEnabled = (boolean) newValue;

            if (isDarkThemeEnabled) {
                requireActivity().setTheme(R.style.Theme_FoodApp_Dark);
            } else {
                requireActivity().setTheme(R.style.Theme_FoodApp);
            }

            getActivity().recreate(); // Recreate the activity to apply the new theme
            return true;
        }));*/

        themePreference.setOnPreferenceChangeListener(((preference, newValue) -> {
            boolean isDarkThemeEnabled = (boolean) newValue;

            // Store the theme choice in shared preferences
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
            preferences.edit().putBoolean("is_dark_theme_enabled", isDarkThemeEnabled).apply();

            int nightMode = isDarkThemeEnabled ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO;
            AppCompatDelegate.setDefaultNightMode(nightMode);

            requireActivity().recreate();

            return true;
        }));


        Preference privacyPolicyPreference = findPreference("privacy_preference");
        privacyPolicyPreference.setOnPreferenceClickListener(preference -> {
            startActivity(new Intent(getContext(), PrivacyPolicyActivity.class));
            return true;
        });

        Preference termsAndConditionsPreference = findPreference("terms_preference");
        termsAndConditionsPreference.setOnPreferenceClickListener(preference -> {
            startActivity(new Intent(getContext(), TermsAndConditionsActivity.class));
            return true;
        });
    }

    //TODO: update/remove?
    private void loadSettings() {
        var sp = PreferenceManager.getDefaultSharedPreferences(getContext());

        boolean themePreference = sp.getBoolean("dark_theme", false);
    }
}