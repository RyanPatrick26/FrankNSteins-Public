package com.example.install.franknsteins;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Locale;

public class PreferencesActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

        settings.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            /**
             * When the user selects a different language, the application will change the device's
             * location settings to display the app in either French of English
             *
             * @author Nicholas Allaire
             * @param sharedPreferences
             * @param key
             */
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                String language = sharedPreferences.getString(key,"en");
                Locale locale = new Locale(language);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                Toast.makeText(getBaseContext(),R.string.changed_lang,
                        Toast.LENGTH_SHORT).show();
                restartActivity();
            }
        });
    }

    private void restartActivity() {
        Intent intent = new Intent(PreferencesActivity.this,MainActivity.class);
        finish();
        startActivity(intent);

    }
}
