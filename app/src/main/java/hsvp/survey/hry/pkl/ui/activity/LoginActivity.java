package hsvp.survey.hry.pkl.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.regex.Pattern;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.allinterface.OldAgedLoginData_interface;
import hsvp.survey.hry.pkl.apicall.WebAPiCall;
import hsvp.survey.hry.pkl.models.LoginRequest;
import hsvp.survey.hry.pkl.models.LoginResponse;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.CSPreferences;
import hsvp.survey.hry.pkl.utility.GlobalClass;
import hsvp.survey.hry.pkl.utility.MyLoaders;

public class LoginActivity extends BaseActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OldAgedLoginData_interface {
    @TargetApi(Build.VERSION_CODES.O)

    GoogleApiClient mGoogleApiClient;
    private int RESOLVE_HINT = 2;
    TextView login, createaccount, txtforget, btnskip;
    private TextInputEditText edtmobile;
    private TextInputEditText edtpass;
    Boolean firstTimelogin = true;
    private String refreshedToken;
    private static final String TAG = "LoginActivity";
    private MyLoaders myLoaders;
    String imageurl = "https://i.picsum.photos/id/599/200/200.jpg?hmac=2WLKs3sxIsaEQ-6WZaa6YMxgl6ZC4cNnid0aqupm2is";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        myLoaders = new MyLoaders(getApplicationContext());

        findViews();

        //set google api client for hint request
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .addApi(Auth.CREDENTIALS_API)
                .build();
        getHintPhoneNumber();



        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            //  Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        refreshedToken = task.getResult();
                        // Log.d("fcm",refreshedToken);

                    }
                });


        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                //Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
//                Intent i = new Intent(LoginActivity.this, RegistrationNewActivity.class);
//                startActivity(i);
//                finish();

            }
        });
        txtforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(i);


            }
        });
    }

    public void getHintPhoneNumber() {
        HintRequest hintRequest =
                new HintRequest.Builder()
                        .setPhoneNumberIdentifierSupported(true)
                        .build();
        PendingIntent mIntent = Auth.CredentialsApi.getHintPickerIntent(mGoogleApiClient, hintRequest);
        try {
            startIntentSenderForResult(mIntent.getIntentSender(), RESOLVE_HINT, null, 0, 0, 0);

        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Result if we want hint number
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                    // credential.getId();  <-- will need to process phone number string
                    String mobilenumberwithcountrycode = credential.getId().substring(3);
                    edtmobile.setText(mobilenumberwithcountrycode);

                }
            }
        }
    }


    private void findViews() {
        login = (TextView) findViewById(R.id.button);
        txtforget = (TextView) findViewById(R.id.txtforget);
        btnskip = (TextView) findViewById(R.id.btnskip);
        createaccount = (TextView) findViewById(R.id.button2);
        edtmobile = (TextInputEditText) findViewById(R.id.textInputEditText);
        edtpass = (TextInputEditText) findViewById(R.id.textInputEditText2);
        login.setOnClickListener(this);
    }

    private boolean isValidMobile(String phone) {
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() >= 10 && phone.length() < 11;
            //return phone.length()==10;
        }
        return false;
    }

    public boolean Check_Data(View view) {

        if (TextUtils.isEmpty(edtmobile.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Registered Mobile Number");
            return false;
        } else if (!isValidMobile(edtmobile.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter 10 digits Registered Mobile Number");
            return false;

        } else if (TextUtils.isEmpty(edtpass.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Password");
            return false;
        }
        return true;
    }


    @Override
    public void onClick(View v) {
        if (v == login) {


            if (Check_Data(v)) {

                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setFCMToken(refreshedToken);
                loginRequest.setPassword(edtpass.getText().toString().trim());
                loginRequest.setMobile(edtmobile.getText().toString().trim());


                if (GlobalClass.isNetworkConnected(LoginActivity.this)) {
                    CSPreferences.putString(this, "User_Name", "Test");
                    CSPreferences.putString(this, "PhoneNo", "8269970959");
                    CSPreferences.putString(this, "Email", "Test@gmail.com");
                    CSPreferences.putString(this, "token", "32132132132121");
                    CSPreferences.putString(this, "role", "3");

                    CSPreferences.putString(this, "oldAgeTakingCare", "true");
                    CSPreferences.putString(this, "oldAgeAdoptionOfLibrary","true");
                    CSPreferences.putString(this, "oldAgePeopleInRuralArea","true");



                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    WebAPiCall webapiCall = new WebAPiCall();
                    //  webapiCall.loginPostDataMethod(this, this, LoginActivity.this, edtmobile.getText().toString().trim(), edtpass.getText().toString().trim(), refreshedToken);
                    // webapiCall.loginPostDataMethod(this, this, LoginActivity.this, loginRequest);
                //    webapiCall.loginPostDataMethod(this, this, LoginActivity.this, loginRequest);


                } else {

                    Toast.makeText(this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }

            }


        }
    }


    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {

        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intentsignup = new Intent(WelcomeActivity.this, SignupActivityold.class);

                try {
                    CSPreferences.putString(LoginActivity.this, "User_Name", "Digital Library Haryana");
//                    CSPreferences.putString(WelcomeActivity.this, "Purpose", data2.getPurpose());
//                    CSPreferences.putString(WelcomeActivity.this, "Status", data2.getStatus());
//                    CSPreferences.putString(WelcomeActivity.this, "LibraryId", String.valueOf(data2.getLibraryId()));
//                    CSPreferences.putString(WelcomeActivity.this, "otp", String.valueOf(data2.getOtp()));
//                    CSPreferences.putString(WelcomeActivity.this, "PhoneNo", data2.getPhoneNo());
//                    CSPreferences.putString(WelcomeActivity.this, "Email", data2.getEmail());
//                    CSPreferences.putString(WelcomeActivity.this, "token", data2.getToken());
                    //  CSPreferences.putBolean(WelcomeActivity.this, "firstTimelogin", firstTimelogin);
                    CSPreferences.putBolean(LoginActivity.this, "skiplogin", true);


//                    if (data2.getPic() == null) {
//                        data2.setPic(imageurl);
//                    }
//
//                    CSPreferences.putString(WelcomeActivity.this, "pic", data2.getPic());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("skiplogin", "skiplogin");
                CSPreferences.putBolean(LoginActivity.this, "skiplogin", true);
                startActivity(intent);
                finish();

            }
        });


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void Loginuserdata(LoginResponse.Data data) {
        try {

            CSPreferences.putString(this, "User_Name", "Test");
            //   CSPreferences.putString(this, "Purpose", data.getPurpose());
            //    CSPreferences.putString(this, "Status", data.getStatus());
            //   CSPreferences.putString(this, "LibraryId", String.valueOf(data.getLibraryId()));
            //  CSPreferences.putString(this, "otp", String.valueOf(data.getOtp()));
            /* /*"oldAgeTakingCare": "True",
        "oldAgeAdoptionOfLibrary": "",
        "oldAgePeopleInRuralArea": "",*/



            CSPreferences.putString(this, "PhoneNo", "8269970959");
            CSPreferences.putString(this, "Email", "Test@gmail.com");
            CSPreferences.putString(this, "token", "32132132132121");
            CSPreferences.putString(this, "role", "Admin");
           // CSPreferences.putString(this, "role", "4");
            CSPreferences.putString(this, "token", data.getToken());
            CSPreferences.putString(this, "CollegeId", data.getCollegeId());
            CSPreferences.putString(this, "userId", data.getId());


            CSPreferences.putString(this, "TakeCareStep1", data.getTakeCareStep1());
            CSPreferences.putString(this, "LibrariesAdoptionStep1", data.getLibrariesAdoptionStep1());
            CSPreferences.putString(this, "RuralTakeCareStep1", data.getRuralTakeCareStep1());


            CSPreferences.putString(this, "oldAgeTakingCare", data.getOldAgeTakingCare());
            CSPreferences.putString(this, "oldAgeAdoptionOfLibrary", data.getOldAgeAdoptionOfLibrary());
            CSPreferences.putString(this, "oldAgePeopleInRuralArea", data.getOldAgePeopleInRuralArea());







            CSPreferences.putBolean(this, "firstTimelogin", firstTimelogin);
            CSPreferences.putBolean(this, "skiplogin", false);


            if (data.getFilePath() == null) {
                data.setFilePath(imageurl);
            }

            CSPreferences.putString(this, "pic", data.getFilePath());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}