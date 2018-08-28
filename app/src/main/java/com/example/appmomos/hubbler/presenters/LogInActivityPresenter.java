package com.example.appmomos.hubbler.presenters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.appmomos.hubbler.R;
import com.example.appmomos.hubbler.getterSetters.LogInCredentials;
import com.example.appmomos.hubbler.interfaces.LogInActivityInterface;
import com.example.appmomos.hubbler.models.LogInActivityModel;
import com.example.appmomos.hubbler.views.LogInActivity;

import static android.content.Context.MODE_PRIVATE;

public class LogInActivityPresenter implements LogInActivityInterface.presenterInterface
{

    private LogInActivityInterface.viewInterface viewInterface;
    private LogInActivityInterface.modelInterface modelInterface;

    public LogInActivityPresenter(LogInActivity logInActivity)
    {
        viewInterface = logInActivity;
        modelInterface = new LogInActivityModel();
    }


    @Override
    public void updateStoredData(Context context)
    {
       SharedPreferences sharedPreferences = context.getSharedPreferences("Log In Details",MODE_PRIVATE);

        if (!sharedPreferences.getString("email","").equals(""))
        {
            viewInterface.setData(sharedPreferences.getString("email","") , sharedPreferences.getString("pass",""));
        }
        else
        {
            viewInterface.setData("","");
        }

    }

    @Override
    public void onLogInBtnClickFun(LogInCredentials logInCredential, Context context)
    {
        if (modelInterface.checkInternetConnection(context))
        {
            //If Internet connection is there
            if(modelInterface.validateCredentials(logInCredential,context,this))
            {
                if (viewInterface.needToRememberCredentials())
                {
                    viewInterface.updateSharedPreferenceValueFun(logInCredential.getEmail(),logInCredential.getPass());
                }
                viewInterface.redirectToHomeFun();
            }

        }
        else
        {
            //If Internet connection is not there
            viewInterface.showToast(context.getResources().getString(R.string.noInternetTxt));
        }

    }

    @Override
    public void emailValidationResult(String msg)
    {
        viewInterface.setEmailError(msg);
    }

    @Override
    public void passwordValidationResult(String msg)
    {
        viewInterface.setPasswordError(msg);
    }

    @Override
    public void showToast(String msg) {
        viewInterface.showToast(msg);
    }


    @Override
    public void onForgotPassClickFun() {

    }

}
