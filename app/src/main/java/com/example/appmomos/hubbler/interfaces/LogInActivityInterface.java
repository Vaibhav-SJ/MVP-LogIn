package com.example.appmomos.hubbler.interfaces;

import android.content.Context;

import com.example.appmomos.hubbler.getterSetters.LogInCredentials;
import com.example.appmomos.hubbler.presenters.LogInActivityPresenter;

public interface LogInActivityInterface
{
    interface viewInterface
    {
        void setEmailError(String msg);
        void setPasswordError(String msg);
        void showToast(String msg);
        void redirectToHomeFun();
        void updateSharedPreferenceValueFun(String email,String pass);
        boolean needToRememberCredentials();
        void setData(String email,String pass);
    }

    interface presenterInterface
    {
        void updateStoredData(Context context);
        void onLogInBtnClickFun(LogInCredentials logInCredential, Context context);
        void emailValidationResult(String msg);
        void passwordValidationResult(String msg);
        void showToast(String msg);
        void onForgotPassClickFun();
    }


    interface modelInterface
    {
        boolean checkInternetConnection(Context context);
        boolean validateCredentials(LogInCredentials logInCredential,Context context, LogInActivityPresenter logInActivityPresenter);
    }

}
