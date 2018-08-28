package com.example.appmomos.hubbler;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferencesForTheme;
    SharedPreferences.Editor editorForTheme;
    String themeName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        sharedPreferencesForTheme = getSharedPreferences("themeDetails",MODE_PRIVATE);

        themeName = sharedPreferencesForTheme.getString("theme","default");

        switch (themeName) {
            case "blue":
                setTheme(R.style.AppTheme_BlueColor);
                break;
            case "red":
                setTheme(R.style.AppTheme_RedColor);
                break;
            case "green":
                setTheme(R.style.AppTheme_GreenColor);
                break;
            case "orange":
                setTheme(R.style.AppTheme_OrangeColor);
                break;
            default:
                setTheme(R.style.AppTheme);
                break;
        }

        setAppLanguage();

        sharedPreferences = getSharedPreferences("Log In Details",MODE_PRIVATE);

        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "No function assigned", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        LinearLayout navHeaderLayout =  headerView.findViewById(R.id.navHeaderLayout);
        TextView navEmail = headerView.findViewById(R.id.email);
        navEmail.setText(sharedPreferences.getString("email",""));

        switch (themeName) {
            case "blue":
                navHeaderLayout.setBackground(getResources().getDrawable(R.drawable.side_nav_bar_blue));
                break;
            case "red":
                navHeaderLayout.setBackground(getResources().getDrawable(R.drawable.side_nav_bar_red));
                break;
            case "green":
                navHeaderLayout.setBackground(getResources().getDrawable(R.drawable.side_nav_bar_green));
                break;
            case "orange":
                navHeaderLayout.setBackground(getResources().getDrawable(R.drawable.side_nav_bar_orange));
                break;
            default:
                navHeaderLayout.setBackground(getResources().getDrawable(R.drawable.side_nav_bar));
                break;
        }



    }

    private void setAppLanguage()
    {
        Locale locale = new Locale(sharedPreferencesForTheme.getString("lang","en"));
        Locale.setDefault(locale);
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
    }


    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.logout)
        {
            editor = sharedPreferences.edit();
            editor.putString("isLoggedIn","no");
            editor.apply();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void recreateActivity() {
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.blue)
        {
            // Handle the camera action
           editorForTheme = sharedPreferencesForTheme.edit();
            editorForTheme.putString("theme","blue");
            editorForTheme.apply();
            recreateActivity();
        }
        else if (id == R.id.red)
        {

            editorForTheme = sharedPreferencesForTheme.edit();
            editorForTheme.putString("theme","red");
            editorForTheme.apply();
            recreateActivity();
        }
        else if (id == R.id.green)
        {
            editorForTheme = sharedPreferencesForTheme.edit();
            editorForTheme.putString("theme","green");
            editorForTheme.apply();
            recreateActivity();
        }
        else if (id == R.id.orange)
        {
            editorForTheme = sharedPreferencesForTheme.edit();
            editorForTheme.putString("theme","orange");
            editorForTheme.apply();
            recreateActivity();
        }
        else if (id == R.id.english)
        {
            editorForTheme = sharedPreferencesForTheme.edit();
            editorForTheme.putString("lang","en");
            editorForTheme.apply();
            recreateActivity();/*

            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = getBaseContext().getResources().getConfiguration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
            recreateActivity();*/
        }
        else if (id == R.id.kannada)
        {
            editorForTheme = sharedPreferencesForTheme.edit();
            editorForTheme.putString("lang","kn");
            editorForTheme.apply();
            recreateActivity();
        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

