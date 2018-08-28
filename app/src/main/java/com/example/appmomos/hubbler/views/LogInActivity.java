package com.example.appmomos.hubbler.views;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmomos.hubbler.MainActivity;
import com.example.appmomos.hubbler.R;
import com.example.appmomos.hubbler.getterSetters.LogInCredentials;
import com.example.appmomos.hubbler.interfaces.ForgotPasswordInterface;
import com.example.appmomos.hubbler.interfaces.LogInActivityInterface;
import com.example.appmomos.hubbler.presenters.ForgotPasswordPresenter;
import com.example.appmomos.hubbler.presenters.LogInActivityPresenter;

import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInActivity extends AppCompatActivity implements LogInActivityInterface.viewInterface,ForgotPasswordInterface.viewInterface
{


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.emailEt)
    EditText emailEt;

    @BindView(R.id.passwordEt)
    EditText passwordEt;

    @BindView(R.id.checkBox)
    CheckBox checkBox;

    @BindView(R.id.logInBtn)
    Button logInBtn;

    @BindView(R.id.forgotPassTxt)
    TextView forgotPassTxt;

    Boolean isCheckBoxChecked = false;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private LogInActivityInterface.presenterInterface logInActivityPresenter;

    //Forgot pasword dialog
    AlertDialog.Builder dialogBulider;
    AlertDialog alertDialog;
    TextView title;
    EditText fpEmailEt;
    Button fpSubmitEmailBtn;
    EditText fpOtpEt;
    Button fpSubmitOTPBtn;
    LinearLayout firstHalf;
    LinearLayout secondHalf;

    private ForgotPasswordInterface.presenterIntereface fpPresenterIntereface;

    SharedPreferences sharedPreferencesForTheme;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        sharedPreferencesForTheme = getSharedPreferences("themeDetails",MODE_PRIVATE);

        String themeName = sharedPreferencesForTheme.getString("theme","default");
        switch (themeName)
        {
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
        setContentView(R.layout.activity_log_in);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.logInSectionTitle));

        forgotPassTxt.setPaintFlags(forgotPassTxt.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        logInActivityPresenter = new LogInActivityPresenter(this);

        fpPresenterIntereface = new ForgotPasswordPresenter(this);



        sharedPreferences = getSharedPreferences("Log In Details",MODE_PRIVATE);

        logInActivityPresenter.updateStoredData(this);
        initFun();

    }


    private void setAppLanguage()
    {
        Locale locale = new Locale(sharedPreferencesForTheme.getString("lang","en"));
        Locale.setDefault(locale);
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
    }



    private void initFun()
    {
        logInBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                LogInCredentials logInCredential = new LogInCredentials();
                logInCredential.setEmail(emailEt.getText().toString().trim());
                logInCredential.setPass(passwordEt.getText().toString().trim());
                logInActivityPresenter.onLogInBtnClickFun(logInCredential,LogInActivity.this);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               isCheckBoxChecked = b;
            }
        });


        forgotPassTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    @SuppressLint("NewApi")
    private void showDialog()
    {
        // custom dialog
        dialogBulider = new AlertDialog.Builder(LogInActivity.this);
        LayoutInflater inflater = LogInActivity.this.getLayoutInflater();
        @SuppressLint("InflateParams") View alertView = inflater.inflate(R.layout.forgot_pass_dialog, null);
        dialogBulider.setView(alertView);

        alertDialog = dialogBulider.show();

        alertDialog.setCanceledOnTouchOutside(false);


        title = alertView.findViewById(R.id.title);
        fpEmailEt = alertView.findViewById(R.id.fpEmailEt);
        fpSubmitEmailBtn = alertView.findViewById(R.id.fpSubmitEmailBtn);
        fpOtpEt = alertView.findViewById(R.id.fpOtpEt);
        fpSubmitOTPBtn = alertView.findViewById(R.id.fpSubmitOTPBtn);
        firstHalf = alertView.findViewById(R.id.firstHalf);
        secondHalf = alertView.findViewById(R.id.secondHalf);

        fpSubmitEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fpPresenterIntereface.validateEmail(fpEmailEt.getText().toString().trim(),LogInActivity.this);
            }
        });

        fpSubmitOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fpPresenterIntereface.validateOtp(fpOtpEt.getText().toString().trim(),LogInActivity.this);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       // alertDialog.cancel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.blank_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void setEmailError(String msg)
    {
        emailEt.setError(msg);
    }

    @Override
    public void setPasswordError(String msg)
    {
        passwordEt.setError(msg);
    }

    @Override
    public void showToast(String msg)
    {
        showCustomToast(msg);
    }

    @Override
    public void redirectToHomeFun()
    {
        editor = sharedPreferences.edit();
        editor.putString("isLoggedIn","yes");
        editor.apply();
        startActivity(new Intent(LogInActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void updateSharedPreferenceValueFun(String email,String pass)
    {
        editor = sharedPreferences.edit();
        editor.putString("email",email);
        editor.putString("pass",pass);
        editor.apply();
    }

    @Override
    public boolean needToRememberCredentials() {
        return isCheckBoxChecked;
    }

    @Override
    public void setData(String email, String pass)
    {
        Log.d("updateDetails",email+pass);
        emailEt.setText(email);
        passwordEt.setText(pass);
    }



    public void showCustomToast(String msg)
    {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,(ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = layout.findViewById(R.id.text);
        text.setText(msg);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_VERTICAL, 0, 100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }



    /* Forgot password */

    @Override
    public void fpSetEmailError(String msg)
    {
        fpEmailEt.setError(msg);
    }

    @Override
    public void fpSetOTPError(String msg)
    {
        fpOtpEt.setError(msg);
    }

    @SuppressLint({"ResourceType", "RtlHardcoded"})
    @Override
    public void fpOnEmailCorrect()
    {
        title.setTextSize(16);
        title.setGravity(Gravity.LEFT);
        title.setText(getResources().getString(R.string.otpSentTxt));
        firstHalf.setVisibility(View.GONE);
        secondHalf.setVisibility(View.VISIBLE);
    }

    @Override
    public void fpOnOtpCorrect()
    {
        showCustomToast(getResources().getString(R.string.passwordSentTxt));
        alertDialog.cancel();
    }

    @Override
    public void fpShowToast(String msg) {
        showCustomToast(msg);
    }

    /* Forgot password */
}
