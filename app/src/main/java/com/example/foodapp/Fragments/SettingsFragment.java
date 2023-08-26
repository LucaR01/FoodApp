package com.example.foodapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.Activities.PrivacyPolicyActivity;
import com.example.foodapp.Activities.TermsAndConditionsActivity;
import com.example.foodapp.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        Preference themePreference = findPreference("theme_preference");
        themePreference.setOnPreferenceChangeListener(((preference, newValue) -> {
            boolean isDarkThemeEnabled = (boolean) newValue;

            if (isDarkThemeEnabled) {
                getContext().setTheme(R.style.Theme_FoodApp_Dark);
            } else {
                getContext().setTheme(R.style.Theme_FoodApp);
            }

            getActivity().recreate(); // Recreate the activity to apply the new theme
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