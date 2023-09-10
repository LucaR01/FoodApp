package com.example.foodapp.Application;

import android.app.Application;
import android.content.res.Configuration;

import java.util.Locale;

public class FoodAppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //TODO: uncomment when fixed.
        /*final Locale locale = new Locale("it");
        Locale.setDefault(locale);

        // Check if the app's locale is already set
        if (Locale.getDefault().getLanguage().equals("it")) {
            return; // Locale is already Italian, no need to change it again
        }
        
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());*/
    }
}

//TODO: uncomment?
/*
public class FoodAppApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(updateBaseContextLocale(base));
    }

    private Context updateBaseContextLocale(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String language = preferences.getString("language", "en"); // Default to English if not set

        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.locale = locale;

        return context.createConfigurationContext(configuration);
    }
}

 */
