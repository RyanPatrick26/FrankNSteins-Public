package com.example.install.franknsteins;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        BuildAFrankFragment.OnFragmentInteractionListener,
        MainFragment.OnFragmentInteractionListener,
        MenuListFragment.OnFragmentInteractionListener,
        BillEstimatorFragment.OnFragmentInteractionListener,
        ContactFragment.OnFragmentInteractionListener,
        MenuFragment.OnFragmentInteractionListener,
        TableReserveFragment.OnFragmentInteractionListener{

    // Create a fragment manager
    FragmentManager fragMan = getSupportFragmentManager();

    // string for language
    private String language;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        language = settings.getString("lang","en");
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        setContentView(R.layout.activity_main);

        settings.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            /**
             * 
             *
             * @author Nicholas Allaire
             * @param sharedPreferences
             * @param key
             */
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                language = sharedPreferences.getString(key,"en");
                Locale locale = new Locale(language);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                Intent intent = getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                Toast.makeText(getBaseContext(),language+" selected.",
                        Toast.LENGTH_SHORT).show();
            }

        });

        // Replace the current activity with the Fragment and create a fragment transaction
        FragmentTransaction fragTran = fragMan.beginTransaction();
        fragTran.addToBackStack(null);
        fragTran.replace(R.id.mainContent, new MainFragment());
        fragTran.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, PreferencesActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // Check which nav item is selected and then perform the fragment transaction
        if (id == R.id.nav_about_us) {
            // replace whatever is on the mainContent screen with the MainFragment
            FragmentTransaction trans = fragMan.beginTransaction();
            trans.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
            trans.replace(R.id.mainContent, new MainFragment());
            trans.addToBackStack(null);
            trans.commit();
        } else if (id == R.id.nav_menu) {
            // replace whatever is on the mainContent screen with the MenuFragment
            FragmentTransaction trans = fragMan.beginTransaction();
            trans.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
            trans.replace(R.id.mainContent, new MenuFragment());
            trans.addToBackStack(null);
            trans.commit();
        } else if (id == R.id.nav_bill_estimator) {
            // replace whatever is on the mainContent screen with the BillEstimatorFragment
            FragmentTransaction trans = fragMan.beginTransaction();
            trans.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
            trans.replace(R.id.mainContent, new BillEstimatorFragment());
            trans.addToBackStack(null);
            trans.commit();
        } else if (id == R.id.nav_reserve_table) {
            // replace whatever is on the mainContent screen with the TableReserveFragment
            FragmentTransaction trans = fragMan.beginTransaction();
            trans.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
            trans.replace(R.id.mainContent, new TableReserveFragment());
            trans.addToBackStack(null);
            trans.commit();
        }
        else if (id == R.id.nav_build_a_frank) {
            // replace whatever is on the mainContent screen with the BuildAFrankFragment
            FragmentTransaction trans = fragMan.beginTransaction();
            trans.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
            trans.replace(R.id.mainContent, new BuildAFrankFragment());
            trans.addToBackStack(null);
            trans.commit();
        } else if (id == R.id.nav_contact) {
            // replace whatever is on the mainContent screen with the ContactFragment
            FragmentTransaction trans = fragMan.beginTransaction();
            trans.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
            trans.replace(R.id.mainContent, new ContactFragment());
            trans.addToBackStack(null);
            trans.commit();
        }
        else if (id == R.id.nav_menu) {
            // replace whatever is on the mainContent screen with the MenuFragment
            FragmentTransaction trans = fragMan.beginTransaction();
            trans.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
            trans.replace(R.id.mainContent, new MenuFragment());
            trans.addToBackStack(null);
            trans.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
