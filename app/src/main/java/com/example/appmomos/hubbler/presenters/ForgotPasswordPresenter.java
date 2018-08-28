package com.example.appmomos.hubbler.presenters;

import android.content.Context;

import com.example.appmomos.hubbler.R;
import com.example.appmomos.hubbler.interfaces.ForgotPasswordInterface;
import com.example.appmomos.hubbler.models.ForgotPasswordModle;
import com.example.appmomos.hubbler.views.LogInActivity;

public class ForgotPasswordPresenter implements ForgotPasswordInterface.presenterIntereface
{

    private ForgotPasswordInterface.viewInterface viewInterface;
    private ForgotPasswordInterface.modleInterface modleInterface;

    public ForgotPasswordPresenter(LogInActivity logInActivity)
    {
        viewInterface = logInActivity;
        modleInterface = new ForgotPasswordModle();
    }

    @Override
    public void validateEmail(String email, Context context)
    {
        if (modleInterface.checkInternet(context))
        {
            // if net connection is there
            if (modleInterface.checkEmail(email,context,this))
            {
                //if email is in correct format
                viewInterface.fpOnEmailCorrect();
            }
        }
        else
        {
            //if not net

            viewInterface.fpShowToast(context.getResources().getString(R.string.noInternetTxt));
        }
    }

    @Override
    public void validateOtp(String otp,Context context)
    {
        if (modleInterface.checkInternet(context))
        {
            // if net connection is there
            if (modleInterface.checkOTP(otp,context,this))
            {
                //if email is in correct format
                viewInterface.fpOnOtpCorrect();
            }
        }
        else
        {
            //if not net
            viewInterface.fpShowToast(context.getResources().getString(R.string.noInternetTxt));
        }
    }

    @Override
    public void setEmailError(String msg)
    {
        viewInterface.fpSetEmailError(msg);
    }

    @Override
    public void setOtpError(String msg) {
        viewInterface.fpSetOTPError(msg);
    }
}
