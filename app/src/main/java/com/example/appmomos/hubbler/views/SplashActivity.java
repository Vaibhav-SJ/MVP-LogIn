package com.example.appmomos.hubbler.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.appmomos.hubbler.MainActivity;
import com.example.appmomos.hubbler.R;
import com.example.appmomos.hubbler.interfaces.SplashActivityInterface;
import com.example.appmomos.hubbler.presenters.SplashActivityPresenter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashActivityInterface.viewInterface
{

    SplashActivityPresenter splashActivityPresenter;

    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferencesForTheme;

    @BindView(R.id.text)
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        sharedPreferencesForTheme = getSharedPreferences("themeDetails",MODE_PRIVATE);

        String themeName = sharedPreferencesForTheme.getString("theme","default");
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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashActivityPresenter = new SplashActivityPresenter(this);

        ButterKnife.bind(this);


        sharedPreferences = getSharedPreferences("Log In Details",MODE_PRIVATE);

        Log.d("sharedDetails",String.valueOf(sharedPreferences.getString("isLoggedIn","no")));

        splashActivityPresenter.returnRedirectActivityFun(sharedPreferences.getString("isLoggedIn","no"));
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
    public void redirectToActivityFun(String redirectActivityName)
    {
        if (redirectActivityName.equals("MainActivity"))
        {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();
        }
        else
        {
            startActivity(new Intent(SplashActivity.this,LogInActivity.class));
            finish();
        }
    }
}
