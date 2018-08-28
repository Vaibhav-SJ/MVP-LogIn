package com.example.appmomos.hubbler.models;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.appmomos.hubbler.R;
import com.example.appmomos.hubbler.interfaces.ForgotPasswordInterface;
import com.example.appmomos.hubbler.presenters.ForgotPasswordPresenter;

public class ForgotPasswordModle implements ForgotPasswordInterface.modleInterface
{

    @Override
    public boolean checkInternet(Context context)
    {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
        {
            //we are connected to a network
            connected = true;
        }
        return connected;
    }

    @Override
    public boolean checkEmail(String email, Context context, ForgotPasswordPresenter forgotPasswordPresenter)
    {
        if (email.equals(""))
        {
            forgotPasswordPresenter.setEmailError(context.getResources().getString(R.string.mandatoryTxt));
            return false;
        }
        else if (!email.matches(context.getResources().getString(R.string.emailFormat)))
        {
            forgotPasswordPresenter.setEmailError(context.getResources().getString(R.string.invalidEmailFormatTxt));
            return false;
        }
        else if (!email.equals("vj@gmail.com"))
        {
            forgotPasswordPresenter.setEmailError(context.getResources().getString(R.string.emailNotRegisteredTxt));
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public boolean checkOTP(String otp, Context context, ForgotPasswordPresenter forgotPasswordPresenter)
    {
        if (otp.equals(""))
        {
            forgotPasswordPresenter.setOtpError(context.getResources().getString(R.string.mandatoryTxt));
            return false;
        }
        else if (otp.length() != 6)
        {
            forgotPasswordPresenter.setOtpError(context.getResources().getString(R.string.otpLengthErrorTxt));
            return false;
        }
        else if (!otp.equals("123456"))
        {
            forgotPasswordPresenter.setOtpError(context.getResources().getString(R.string.incorrectOtpTxt));
            return false;
        }
        else
        {
            return true;
        }

    }


}
