package com.example.appmomos.hubbler.presenters;

import android.os.Handler;

import com.example.appmomos.hubbler.interfaces.SplashActivityInterface;
import com.example.appmomos.hubbler.views.SplashActivity;


public class SplashActivityPresenter implements SplashActivityInterface.presenterInterface
{

    private SplashActivityInterface.viewInterface viewInterface;

    public SplashActivityPresenter(SplashActivity splashActivity)
    {
        viewInterface = splashActivity;
    }

    @Override
    public void returnRedirectActivityFun(final String loggedInStatus)
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                String redirectActivityName = "";

                if (loggedInStatus.equals("yes"))
                {
                    redirectActivityName = "MainActivity";
                }

                viewInterface.redirectToActivityFun(redirectActivityName);
            }
        },3000);
    }
}
