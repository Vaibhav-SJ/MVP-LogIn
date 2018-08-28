package com.example.appmomos.hubbler.interfaces;


public interface SplashActivityInterface
{
    interface viewInterface
    {
        void redirectToActivityFun(String redirectActivityName);
    }

    interface presenterInterface
    {
        void returnRedirectActivityFun(String loggedInStatus);
    }
}
