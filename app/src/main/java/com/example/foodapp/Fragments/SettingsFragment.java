package com.example.foodapp.Fragments;

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

import com.example.foodapp.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        Preference themePreference = findPreference("theme_preference");
        themePreference.setOnPreferenceClickListener(preference -> {

            System.out.println("theme"); //TODO: remove

            if (preference.isEnabled()) {
                System.out.println("enabled"); //TODO: remove
                //getContext().setTheme(R.style.Theme_FoodAppNight); //TODO: remove
                getContext().setTheme(R.style.Theme_FoodApp);
            } else { //FIXME non va nell'else
                System.out.println("disabled"); //TODO: remove
                getContext().setTheme(R.style.Theme_FoodApp);
            }

            //getActivity().recreate(); //TODO: uncomment when ready.
            return true;

        }); //TODO: uncomment when ready.

        Preference privacyPolicyPreference = findPreference("privacy_preference");
        //privacyPolicyPreference.setOnPreferenceClickListener(preference -> startActivity(new Intent(getContext(), PrivacyPolicy.class))); //TODO: uncomment when ready.

        Preference termsAndConditionsPreference = findPreference("terms_preference");
        //termsAndConditionsPreference.setOnPreferenceClickListener(preference -> startActivity(new Intent(getContext(), TermsAndConditions.class))); //TODO: uncomment when ready.
    }

    //TODO: update/remove?
    private void loadSettings() {
        var sp = PreferenceManager.getDefaultSharedPreferences(getContext());

        boolean themePreference = sp.getBoolean("dark_theme", false);
    }
}