package hsvp.survey.hry.pkl.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.allinterface.ResetForget_interface;
import hsvp.survey.hry.pkl.apicall.WebAPiCall;
import hsvp.survey.hry.pkl.databinding.ActivityResetPasswordBinding;

import hsvp.survey.hry.pkl.models.ResetPasswordRequest;
import hsvp.survey.hry.pkl.ui.welcome.WelcomeActivity;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.CSPreferences;
import hsvp.survey.hry.pkl.utility.GlobalClass;
import hsvp.survey.hry.pkl.utility.MyLoaders;

public class ResetPasswordActivity extends BaseActivity implements ResetForget_interface {

    ActivityResetPasswordBinding binding;
    private String userMobileNumber, confirmoasswd, Registration_Id, umobile, token;
    private MyLoaders myLoaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_forget_password);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password);
        myLoaders = new MyLoaders(getApplicationContext());

        try {

            Registration_Id = CSPreferences.readString(this, "userId");
            umobile = CSPreferences.readString(ResetPasswordActivity.this, "User_mobile");
            token = CSPreferences.readString(ResetPasswordActivity.this, "token");
            binding.edtmobile.setText(umobile);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initData() {

        binding.toolbar.tvToolbarTitle.setText("Reset password");

    }

    @Override
    public void initListeners() {

        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        binding.resetpasswdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Check_Data(v)) {


                    userMobileNumber = binding.edtmobile.getText().toString().trim();
                    confirmoasswd = binding.textInputEditText.getText().toString().trim();

                    ResetPasswordRequest request = new ResetPasswordRequest();
                    request.setId(Registration_Id);
                    request.setPassword(confirmoasswd);


                    if (GlobalClass.isNetworkConnected(ResetPasswordActivity.this)) {
                        WebAPiCall webapiCall = new WebAPiCall();
                        webapiCall.resetforgotPostDataMethod(ResetPasswordActivity.this, ResetPasswordActivity.this, "Bearer " + token, ResetPasswordActivity.this, request);

                    } else {

                        Toast.makeText(ResetPasswordActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }


                }


            }
        });


    }


    public boolean Check_Data(View view) {

        if (TextUtils.isEmpty(binding.edtpassword.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Password");
            return false;
        } else if (TextUtils.isEmpty(binding.textInputEditText.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Confirm Password");
            return false;
        } else if (!binding.edtpassword.getText().toString().trim().equalsIgnoreCase(binding.textInputEditText.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter  same password");
            return false;
        }

        return true;
    }

    @Override
    public void resetpassword(int statuscode) {

        if (statuscode == 200) {

            CSPreferences.clearPref(ResetPasswordActivity.this);
            Intent intent = new Intent(ResetPasswordActivity.this, WelcomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);


        } else {


        }


    }
}