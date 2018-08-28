package com.example.appmomos.hubbler.interfaces;

import android.content.Context;

import com.example.appmomos.hubbler.presenters.ForgotPasswordPresenter;

public interface ForgotPasswordInterface
{
    interface viewInterface
    {
        void fpSetEmailError(String msg);
        void fpSetOTPError(String msg);
        void fpOnEmailCorrect();
        void fpOnOtpCorrect();
        void fpShowToast(String msg);
    }

    interface presenterIntereface
    {
        void validateEmail(String email,Context context);
        void validateOtp(String otp,Context context);
        void setEmailError(String msg);
        void setOtpError(String msg);

    }

    interface modleInterface
    {
        boolean checkInternet(Context context);
        boolean checkEmail(String email, Context context, ForgotPasswordPresenter forgotPasswordPresenter);
        boolean checkOTP(String otp, Context context, ForgotPasswordPresenter forgotPasswordPresenter);
    }




}
