package hsvp.survey.hry.pkl.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.loader.content.CursorLoader;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.apicall.WebAPiCall;
import hsvp.survey.hry.pkl.databinding.ActivityOldAgeDailyActivitiesBinding;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.CSPreferences;
import hsvp.survey.hry.pkl.utility.GlobalClass;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class OldAgeDailyActivitiesActivity extends BaseActivity {
    ActivityOldAgeDailyActivitiesBinding binding;

    String Description, ImageDate, Longitude, Latitude, time, TypeOfVisit,
            accuracyvalue, currentDateandTime, COllegeID, token, User_Id, schemeId,
            takecarenodalOfficerId, userGender, ContactByPhone;

    LocationManager locationManager = null;
    boolean gps_enabled = false;
    boolean network_enabled = false;
    private LocationRequest mLocationRequest;

    private long UPDATE_INTERVAL = 2 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */
    Bitmap photo;
    Boolean PhotocapturedwithLatLont = false;


    TextView lat, longitude, accuracy, gpstime, txtupload, tv_toolbarTitle;
    private static final int BUFFER_SIZE = 1024 * 3;
    private static final String IMAGE_DIRECTORY = "/demonuts_upload_gallery";
    private Location gpslocation;
    CheckBox chktenplant;

    SimpleDraweeView image;

    File imagefile;

    RadioGroup btnRadiogroup;
    RadioButton checkedRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_old_age_daily_activities);
        checkpermissions(this);
        checkGPSStatus();


        btnRadiogroup = (RadioGroup) findViewById(R.id.btnRadiogroup);
        checkedRadioButton = (RadioButton) btnRadiogroup.findViewById(btnRadiogroup.getCheckedRadioButtonId());

        btnRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton) btnRadiogroup.findViewById(checkedId);
                // This puts the value (true/false) into the variable

                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...llCollegestudent
                if (isChecked) {
                    // Changes the textview's text to "Checked: example radiobutton text"
                    switch (checkedId) {

                        case R.id.radiovisitingphysical:

                            ContactByPhone = "NO";
                            TypeOfVisit = "PhysicalVsit";
                            binding.cardview.setVisibility(View.VISIBLE);
                            binding.tlactivitiesOrganized.setVisibility(View.VISIBLE);

                            break;

                        case R.id.radiobyphone:

                            ContactByPhone = "Yes";
                            TypeOfVisit = "PhoneVsit";
                            binding.cardview.setVisibility(View.GONE);
                            binding.tlactivitiesOrganized.setVisibility(View.GONE);

                            break;


                    }
                    Toast.makeText(OldAgeDailyActivitiesActivity.this, checkedRadioButton.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    // Trigger new location updates at interval
    protected void startLocationUpdates() {

        // Create the location request to start receiving updates
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

        // Create LocationSettingsRequest object using location request
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        // Check whether location settings are satisfied
        // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);

        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {


                        Latitude = String.valueOf(locationResult.getLastLocation().getLatitude());
                        Longitude = String.valueOf(locationResult.getLastLocation().getLongitude());
                        time = String.valueOf(locationResult.getLastLocation().getTime());
                        accuracyvalue = String.valueOf(locationResult.getLastLocation().getAccuracy());

                        binding.lat.setText("Lat:- " + Latitude);
                        binding.longitude.setText("Long:- " + Longitude);
                        binding.accuracy.setText("Accuracy:- " + accuracyvalue);

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
                        String currentDateandTime = sdf.format(new Date());
                        binding.gpstime.setText("Time:- " + currentDateandTime);


                        // do work here
                        onLocationChanged(locationResult.getLastLocation());
                    }
                },
                Looper.myLooper());
    }


    private void checkGPSStatus() {

        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
        try {
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }
        try {
            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }
        if (!gps_enabled && !network_enabled) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(OldAgeDailyActivitiesActivity.this);
            dialog.setMessage("GPS not enabled");

            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //this will navigate user to the device location settings screen
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            AlertDialog alert = dialog.create();
            alert.setCanceledOnTouchOutside(false);
            alert.show();
        }
    }


    public void onLocationChanged(Location location) {
        gpslocation = location;
        // New location has now been determined
        String msg = "Updated Location: " +
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());
        // Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        // You can now create a LatLng Object for use with maps

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        binding.lat.setText("Lat:- " + location.getLatitude());
        binding.longitude.setText("Long:- " + location.getLongitude());
        binding.accuracy.setText("Accuracy:- " + location.getAccuracy());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        String currentDateandTime = sdf.format(new Date());
        binding.gpstime.setText("Time:- " + currentDateandTime);
    }


    public void checkpermissions(Activity context) {
        if (Build.VERSION.SDK_INT >= 23) {
            new TedPermission(context)

                    .setPermissionListener(permissionListener)
                    //.setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                    .setPermissions(
                            android.Manifest.permission.INTERNET,
                            android.Manifest.permission.CAMERA,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_NETWORK_STATE
                    )

                    .setGotoSettingButton(true)
                    .check();

        }


    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {


        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            checkpermissions(OldAgeDailyActivitiesActivity.this);


        }

    };


    // Select image
    private final int requestCode = 10;
    private static final int CAMERA_REQUEST = 188;

    public void showpictures(View.OnClickListener activity) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);


        dialog.setTitle(getResources().getString(R.string.txt_slct_optionn));
        //  String[] items = {getResources().getString(R.string.txt_gelleryy),
        String[] items = {
                getResources().getString(R.string.txt_camerayy), getResources()
                .getString(R.string.txt_warningg)};
        // dialog.setMessage("*for your security reason we blocked!");
        dialog.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                switch (which) {
//                    case 0:
//                        showFileChoosesr();
//                        break;
                    case 0:

                        checkpermissions(OldAgeDailyActivitiesActivity.this);
                        takePhotoFromCameras();
                        break;
                }
            }
        });
        dialog.show();
    }


    private void takePhotoFromCameras() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.requestCode == requestCode && resultCode == RESULT_OK) {

            Uri picUri = data.getData();
            imagefile = new File(getPaths(picUri));
            Log.d("imagegallary", String.valueOf(imagefile));
            binding.myImageView.setImageURI(picUri);

        }
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            // imageView.setImageBitmap(photo);
            binding.myImageView.setImageBitmap(photo);
            binding.txtupload.setVisibility(View.GONE);

            Uri tempUri = getImageUri(this, photo);
            imagefile = new File(getRealPathFromURI(tempUri));
            binding.myImageView.setImageURI(tempUri);


            int currentBitmapWidth = photo.getWidth();
            int currentBitmapHeight = photo.getHeight();


            Log.d("imagecamera", String.valueOf(imagefile));
            //image.setImageURI(Uri.fromFile(imagefile));

        }

    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        // String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage,"Title", null);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, String.valueOf(Calendar.getInstance()
                .getTimeInMillis()), null);
        return Uri.parse(path);
    }


    private String getPaths(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }


    @Override
    public void initData() {
        binding.toolbar.tvToolbarTitle.setText("Scheme A Daily Activity");


        checkGPSStatus();
        startLocationUpdates();


        try {


            Bundle extras = getIntent().getExtras();


            if (extras != null) {

                schemeId = extras.getString("schemeId");

            }
            User_Id = CSPreferences.readString(this, "userId");
            token = CSPreferences.readString(this, "token");
            COllegeID = CSPreferences.readString(this, "CollegeId");
            takecarenodalOfficerId = CSPreferences.readString(this, "takecarenodalOfficerId");


        } catch (Exception e) {

            e.printStackTrace();
        }


    }

    @Override
    public void initListeners() {


        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.txtAdoptedpersondetailedt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OldAgeDailyActivitiesActivity.this, ModificationInAdoptedPersonDetailActivity.class);
                startActivity(i);

            }
        });


        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TypeOfVisit == null || TypeOfVisit.isEmpty()) {

                    GlobalClass.dailogError(OldAgeDailyActivitiesActivity.this, "Visiting Type Missing!", "Please select Visiting Type.");
                } else {


                    if (ContactByPhone.equalsIgnoreCase("Yes")) {


                        if (Check_Dataforphone(view)) {



                     /*edtsolutions
edtdateorcontact
imagefile
lat
longitude*/
                            currentDateandTime = (String) android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", new java.util.Date());
                            Log.d("goo..", currentDateandTime);

                            try {
                                ImageDate = currentDateandTime;
                                Longitude = String.valueOf(gpslocation.getLongitude());
                                Latitude = String.valueOf(gpslocation.getLatitude());

                            } catch (Exception e) {

                                e.printStackTrace();
                            }


                            String problemfaced = binding.edtproblemfaced.getText().toString().trim();
                            String solutions = binding.edtsolutions.getText().toString().trim();
                            String edtrequiredment = binding.edtrequiredment.getText().toString().trim();
                            String activitiesOrganized = binding.edtactivitiesOrganized.getText().toString().trim();
                            String dateorcontact = binding.edtdateorcontact.getText().toString().trim();
                            String anyotherorbservation = binding.edtotherorbservation.getText().toString().trim();
/*String token,
RequestBody StudentId,RequestBody NodalOfficerId,
RequestBody ProblemFacedByPerson,RequestBody SolutionProvidedByStudent,
RequestBody RquirmentOfPerson,RequestBody DateOfVisit,
RequestBody ContactByPhone, RequestBody OtherObservation,
RequestBody TypeOfVisit,RequestBody Lat,
RequestBody Long,RequestBody Accuracy,RequestBody uplodingTime,
Document*/
                            try {

                                RequestBody StudentId = RequestBody.create(MediaType.parse("multipart/form-data"), User_Id);
                                RequestBody takecarenodalOfficer_Id = RequestBody.create(MediaType.parse("multipart/form-data"), takecarenodalOfficerId);


                                RequestBody ProblemFacedByPerson = RequestBody.create(MediaType.parse("multipart/form-data"), problemfaced);
                                RequestBody SolutionProvidedByStudent = RequestBody.create(MediaType.parse("multipart/form-data"), solutions);
                                RequestBody RquirmentOfPerson = RequestBody.create(MediaType.parse("multipart/form-data"), edtrequiredment);
                                RequestBody OtherObservation = RequestBody.create(MediaType.parse("multipart/form-data"), anyotherorbservation);


                                RequestBody DateOfVisit = RequestBody.create(MediaType.parse("multipart/form-data"), dateorcontact);
                                // RequestBody DateOfVisit = RequestBody.create(MediaType.parse("multipart/form-data"), "01/02/2020");
                                RequestBody ContactBy_Phone = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                                RequestBody TypeOf_Visit = RequestBody.create(MediaType.parse("multipart/form-data"), TypeOfVisit);

                                RequestBody uplodingTime = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                                // RequestBody uplodingTime = RequestBody.create(MediaType.parse("multipart/form-data"), "11:10");

                                RequestBody Lat = null;
                                try {
                                    Lat = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                RequestBody Long = null;
                                try {
                                    Long = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                RequestBody Accuracy = null;
                                try {
                                    Accuracy = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                RequestBody imagefilerequestFile;
                                MultipartBody.Part Document = null;


                                if (imagefile == null) {
                                    //GlobalClass.showtost(SignupActivity.this, "Select your image");
                                    Document = null;
                                } else {


                                try {
                                    imagefilerequestFile = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                                    Document = MultipartBody.Part.createFormData("Document", imagefile.getName(), imagefilerequestFile);
                                } catch (Exception e) {

                                    e.printStackTrace();
                                }




                                }
                                RequestBody imagerequestFilecertificate;
                                MultipartBody.Part imagebodycertificate = null;



                                if (GlobalClass.isNetworkConnected(OldAgeDailyActivitiesActivity.this)) {
                                    WebAPiCall webapiCall = new WebAPiCall();
                                    webapiCall.TakecareStep2DataMethod(OldAgeDailyActivitiesActivity.this, OldAgeDailyActivitiesActivity.this, "Bearer " + token,
                                            StudentId,
                                            takecarenodalOfficer_Id,
                                            ProblemFacedByPerson,
                                            SolutionProvidedByStudent,
                                            RquirmentOfPerson,
                                            DateOfVisit,
                                            ContactBy_Phone,
                                            OtherObservation,
                                            TypeOf_Visit,
                                            Lat,
                                            Long,
                                            Accuracy,
                                            uplodingTime,
                                            imagebodycertificate
                                    );

                                } else {

                                    Toast.makeText(OldAgeDailyActivitiesActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                                }


                                //  GlobalClass.dailogsuccessWithActivity(OldAgeDailyActivitiesActivity.this, OldAgeDailyActivitiesActivity.this, "Daily Activity Done", "Your Activity submitted successfully.");

                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }

                    } else {

                        if (Check_Data(view)) {



                     /*edtsolutions
edtdateorcontact
imagefile
lat
longitude*/
                            currentDateandTime = (String) android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", new java.util.Date());
                            Log.d("goo..", currentDateandTime);

                            try {
                                ImageDate = currentDateandTime;
                                Longitude = String.valueOf(gpslocation.getLongitude());
                                Latitude = String.valueOf(gpslocation.getLatitude());

                            } catch (Exception e) {

                                e.printStackTrace();
                            }


                            String problemfaced = binding.edtproblemfaced.getText().toString().trim();
                            String solutions = binding.edtsolutions.getText().toString().trim();
                            String edtrequiredment = binding.edtrequiredment.getText().toString().trim();
                            String activitiesOrganized = binding.edtactivitiesOrganized.getText().toString().trim();
                            String dateorcontact = binding.edtdateorcontact.getText().toString().trim();

                            String anyotherorbservation = binding.edtotherorbservation.getText().toString().trim();
/*String token,
RequestBody StudentId,RequestBody NodalOfficerId,
RequestBody ProblemFacedByPerson,RequestBody SolutionProvidedByStudent,
RequestBody RquirmentOfPerson,RequestBody DateOfVisit,
RequestBody ContactByPhone, RequestBody OtherObservation,
RequestBody TypeOfVisit,RequestBody Lat,
RequestBody Long,RequestBody Accuracy,RequestBody uplodingTime,
Document*/
                            try {

                                RequestBody StudentId = RequestBody.create(MediaType.parse("multipart/form-data"), User_Id);
                                RequestBody takecarenodalOfficer_Id = RequestBody.create(MediaType.parse("multipart/form-data"), takecarenodalOfficerId);


                                RequestBody ProblemFacedByPerson = RequestBody.create(MediaType.parse("multipart/form-data"), problemfaced);
                                RequestBody SolutionProvidedByStudent = RequestBody.create(MediaType.parse("multipart/form-data"), solutions);
                                RequestBody RquirmentOfPerson = RequestBody.create(MediaType.parse("multipart/form-data"), edtrequiredment);
                                RequestBody OtherObservation = RequestBody.create(MediaType.parse("multipart/form-data"), anyotherorbservation);


                                 RequestBody DateOfVisit = RequestBody.create(MediaType.parse("multipart/form-data"), dateorcontact);
                               // RequestBody DateOfVisit = RequestBody.create(MediaType.parse("multipart/form-data"), "01/02/2020");
                                RequestBody ContactBy_Phone = RequestBody.create(MediaType.parse("multipart/form-data"), ContactByPhone);
                                RequestBody TypeOf_Visit = RequestBody.create(MediaType.parse("multipart/form-data"), TypeOfVisit);

                                RequestBody uplodingTime = RequestBody.create(MediaType.parse("multipart/form-data"), ImageDate);
                               // RequestBody uplodingTime = RequestBody.create(MediaType.parse("multipart/form-data"), "11:10");
                                RequestBody Lat = RequestBody.create(MediaType.parse("multipart/form-data"), Latitude);
                                RequestBody Long = RequestBody.create(MediaType.parse("multipart/form-data"), Longitude);
                                RequestBody Accuracy = RequestBody.create(MediaType.parse("multipart/form-data"), accuracyvalue);


                                RequestBody imagefilerequestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imagefile);
                                MultipartBody.Part imagefilebody = MultipartBody.Part.createFormData("Document", imagefile.getName(), imagefilerequestFile);

                                if (GlobalClass.isNetworkConnected(OldAgeDailyActivitiesActivity.this)) {
                                    WebAPiCall webapiCall = new WebAPiCall();
                                    webapiCall.TakecareStep2DataMethod(OldAgeDailyActivitiesActivity.this, OldAgeDailyActivitiesActivity.this, "Bearer " + token,
                                            StudentId,
                                            takecarenodalOfficer_Id,
                                            ProblemFacedByPerson,
                                            SolutionProvidedByStudent,
                                            RquirmentOfPerson,
                                            DateOfVisit,
                                            ContactBy_Phone,
                                            OtherObservation,
                                            TypeOf_Visit,
                                            Lat,
                                            Long,
                                            Accuracy,
                                            uplodingTime,
                                            imagefilebody
                                    );

                                } else {

                                    Toast.makeText(OldAgeDailyActivitiesActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                                }


                                //  GlobalClass.dailogsuccessWithActivity(OldAgeDailyActivitiesActivity.this, OldAgeDailyActivitiesActivity.this, "Daily Activity Done", "Your Activity submitted successfully.");

                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }

                    }


                }


            }
        });


        binding.myImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showpictures(this);
            }
        });

        binding.edtdateorcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get Current Date

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(OldAgeDailyActivitiesActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                                //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());


                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);

                                String selectedDate = dateFormat.format(calendar.getTime());


                                binding.edtdateorcontact.setText(selectedDate);


                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.show();

            }
        });


    }


    public boolean Check_Data(View view) {

        if (TextUtils.isEmpty(binding.edtproblemfaced.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeDailyActivitiesActivity.this, "problems Missing!", "Please type problems you are facing.");

            return false;
        } else if (TextUtils.isEmpty(binding.edtsolutions.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeDailyActivitiesActivity.this, "Solution provided by student Missing!", "Please type solution provided by student.");

            return false;

        } /*else if (TextUtils.isEmpty(binding.edtrequiredment.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeDailyActivitiesActivity.this, "Requirement of person/orphans Missing!", "Please type solution provided by student.");

            return false;

        }*/ else if (TextUtils.isEmpty(binding.edtdateorcontact.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeDailyActivitiesActivity.this, "Visited date Missing!", "Please select date on which you visited.");

            return false;

        } else if (imagefile == null) {
            // myLoaders.showSnackBar(view, "Please Enter Password");
            GlobalClass.dailogError(OldAgeDailyActivitiesActivity.this, "Image Missing!", "Please take a picture of your Activities.");
            return false;
        } else if (binding.lat.getText().toString().trim().isEmpty() && binding.longitude.getText().toString().trim().isEmpty()) {
            GlobalClass.dailogError(OldAgeDailyActivitiesActivity.this, "Location of your Activity is  Missing!", "Please make sure gps or location is Enable / ON of phone to get plant location.");

            return false;

        }
        return true;
    }

    public boolean Check_Dataforphone(View view) {

        if (TextUtils.isEmpty(binding.edtproblemfaced.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeDailyActivitiesActivity.this, "problems Missing!", "Please type problems you are facing.");

            return false;
        } else if (TextUtils.isEmpty(binding.edtsolutions.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeDailyActivitiesActivity.this, "Solution provided by student Missing!", "Please type solution provided by student.");

            return false;

        } /*else if (TextUtils.isEmpty(binding.edtrequiredment.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeDailyActivitiesActivity.this, "Requirement of person/orphans Missing!", "Please type solution provided by student.");

            return false;

        }*/ else if (TextUtils.isEmpty(binding.edtdateorcontact.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeDailyActivitiesActivity.this, "Visited date Missing!", "Please select date on which you visited.");

            return false;

        }/* else if (imagefile == null) {
            // myLoaders.showSnackBar(view, "Please Enter Password");
            GlobalClass.dailogError(OldAgeDailyActivitiesActivity.this, "Image Missing!", "Please take a picture of your Activities.");
            return false;
        } else if (binding.lat.getText().toString().trim().isEmpty() && binding.longitude.getText().toString().trim().isEmpty()) {
            GlobalClass.dailogError(OldAgeDailyActivitiesActivity.this, "Location of your Activity is  Missing!", "Please make sure gps or location is Enable / ON of phone to get plant location.");

            return false;

        }*/
        return true;
    }

}