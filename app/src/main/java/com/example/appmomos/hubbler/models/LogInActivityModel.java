package com.example.appmomos.hubbler.models;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.appmomos.hubbler.R;
import com.example.appmomos.hubbler.getterSetters.LogInCredentials;
import com.example.appmomos.hubbler.interfaces.LogInActivityInterface;
import com.example.appmomos.hubbler.presenters.LogInActivityPresenter;

public class LogInActivityModel implements LogInActivityInterface.modelInterface
{


    @Override
    public boolean checkInternetConnection(Context context)
    {
        ConnectivityManager connectivityManager  = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean validateCredentials(LogInCredentials logInCredential,Context context, LogInActivityPresenter logInActivityPresenter)
    {
        if (logInCredential.getEmail().equals(""))
        {
            logInActivityPresenter.emailValidationResult(context.getResources().getString(R.string.mandatoryTxt));
            return false;
        }
        else if (!logInCredential.getEmail().matches(context.getResources().getString(R.string.emailFormat)))
        {
            logInActivityPresenter.emailValidationResult(context.getResources().getString(R.string.invalidEmailFormatTxt));
            return false;
        }
        else  if (logInCredential.getPass().equals(""))
        {
            logInActivityPresenter.passwordValidationResult(context.getResources().getString(R.string.mandatoryTxt));
            return false;
        }
        else  if (logInCredential.getEmail().equals("vj@gmail.com") && logInCredential.getPass().equals("1234"))
        {
            logInActivityPresenter.showToast(context.getResources().getString(R.string.logInSuccessTxt));
            return true;
        }
        else
        {
            logInActivityPresenter.showToast(context.getResources().getString(R.string.logInFailedTxt));
            return false;
        }

    }


}
