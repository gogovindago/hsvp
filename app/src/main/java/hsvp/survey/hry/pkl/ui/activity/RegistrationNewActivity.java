package hsvp.survey.hry.pkl.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.loader.content.CursorLoader;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.adapter.SpinnerCollegeAdapter;
import hsvp.survey.hry.pkl.adapter.SpinnerCourseBycollegeIdAdapter;
import hsvp.survey.hry.pkl.adapter.SpinnerDistricAdapter;
import hsvp.survey.hry.pkl.adapter.SpinnerGenderAdapter;
import hsvp.survey.hry.pkl.adapter.SpinnerSectionAdapter;
import hsvp.survey.hry.pkl.adapter.SpinnerSemsterAdapter;
import hsvp.survey.hry.pkl.adapter.SpinnerSubjectCobinationAdapter;
import hsvp.survey.hry.pkl.allinterface.GetAllCollegeData_interface;
import hsvp.survey.hry.pkl.allinterface.GetAllDistricData_interface;
import hsvp.survey.hry.pkl.allinterface.GetAllSectionData_interface;
import hsvp.survey.hry.pkl.allinterface.GetCollegeSchemeData_interface;
import hsvp.survey.hry.pkl.allinterface.GetCourseByCollegeIdData_interface;
import hsvp.survey.hry.pkl.allinterface.GetSubjectCombinationData_interface;
import hsvp.survey.hry.pkl.apicall.WebAPiCall;
import hsvp.survey.hry.pkl.databinding.ActivityRegistrationNewBinding;
import hsvp.survey.hry.pkl.models.CollegeListResponse;
import hsvp.survey.hry.pkl.models.CourseByCollegeIDResponse;
import hsvp.survey.hry.pkl.models.DummyData;
import hsvp.survey.hry.pkl.models.GetDistrictResponse;
import hsvp.survey.hry.pkl.models.SchemeOfCollegeResponse;
import hsvp.survey.hry.pkl.models.SectionResponse;
import hsvp.survey.hry.pkl.models.SubjectCombinationResponse;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.GlobalClass;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RegistrationNewActivity extends BaseActivity implements AdapterView.OnItemSelectedListener, GetAllCollegeData_interface,
        GetAllDistricData_interface, GetCourseByCollegeIdData_interface, GetAllSectionData_interface, GetSubjectCombinationData_interface, GetCollegeSchemeData_interface {

    private final int PICK_IMAGE_CAMERA = 1;
    private final int PICK_IMAGE_GALLERY = 2;
    private File imagefile;

    String refreshedToken, userdOtheruserDisability, userOtheruserDisabilityID, userstateID, userdstate, userdisability, userdisabilityID,
            AccountType = "3", userGender, usercast, userCastID, userDistricID, userCollegeDistrict, userCollegeID, userCollegeName, usercourseID,
            usercoursName, usersemester, usersection, usersectionID, usersubjectID, usersubjectName, userEmailId, userName, userFatherName, userDob, confirmpassword, userMobilenumber,
            fatherMobilenumber, userpercantageDiasbility, userrollnumber, useraddress, edtothersCollegeDataManually,
            edtothersCourseDataManually,
            edtotherssectionnameDataManually,
            edtotherssubjctcombination,
            edtotherssemester, DifferentlyAskedCategory;
    Boolean chktcareOldAge = false, chkAdoptionofLibraries = false, chkruralArea = false, schemeselected = false;


    ActivityRegistrationNewBinding binding;
    RadioGroup btnRadiogroup;
    RadioButton checkedRadioButton;

    ArrayList<DummyData> genderlist;
    ArrayList<DummyData> semesterList;
    int spnsemesterCurrentPosition = 0, spnGenderCurrentPosition = 0, spnDistricCurrentPosition = 0,
            spncollegeCurrentPosition = 0, spncourseCurrentPosition = 0, spnsubCombinationCurrentPosition = 0,
            spnsectionCurrentPosition = 0;


    SpinnerDistricAdapter districAdapter;
    SpinnerCollegeAdapter spinnerCollegeAdapter;
    SpinnerCourseBycollegeIdAdapter spinnerCourseBycollegeIdAdapter;
    SpinnerSectionAdapter sectionAdapter;
    SpinnerSubjectCobinationAdapter subjectCobinationAdapter;
    SpinnerGenderAdapter spnGenderAdapter;

    private List<GetDistrictResponse.Datum> allDistrict = new ArrayList<GetDistrictResponse.Datum>();
    private List<CollegeListResponse.Datum> allcollegeList = new ArrayList<CollegeListResponse.Datum>();
    private List<SchemeOfCollegeResponse.Datum> collegeSchemedata = new ArrayList<SchemeOfCollegeResponse.Datum>();
    private List<CourseByCollegeIDResponse.Datum> allcourseList = new ArrayList<CourseByCollegeIDResponse.Datum>();
    private List<SectionResponse.Datum> allSectionList = new ArrayList<SectionResponse.Datum>();
    private List<SubjectCombinationResponse.Datum> SubjectCombList = new ArrayList<SubjectCombinationResponse.Datum>();
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_registration_new);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration_new);

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


        btnRadiogroup = (RadioGroup) findViewById(R.id.btnRadiogroup);
        checkedRadioButton = (RadioButton) btnRadiogroup.findViewById(btnRadiogroup.getCheckedRadioButtonId());

        if (binding.utypeStudent.isChecked()) {


//                            binding.llcourseName.setVisibility(View.VISIBLE);
            binding.llsemester.setVisibility(View.VISIBLE);
//                            binding.llsectionName.setVisibility(View.VISIBLE);


            if (GlobalClass.isNetworkConnected(RegistrationNewActivity.this)) {

                WebAPiCall aPiCall = new WebAPiCall();
                aPiCall.allDistrict_listMethod(RegistrationNewActivity.this, RegistrationNewActivity.this);

            } else {

                Toast.makeText(RegistrationNewActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
            }
            AccountType = "3";

        }


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

                        case R.id.principle:


                            spnGenderAdapter = new SpinnerGenderAdapter(getApplicationContext(), genderlist);
                            binding.spnGender.setAdapter(spnGenderAdapter);

                            binding.llcourseName.setVisibility(View.GONE);
                            binding.llsemester.setVisibility(View.GONE);
                            binding.llsectionName.setVisibility(View.GONE);


                            if (GlobalClass.isNetworkConnected(RegistrationNewActivity.this)) {

                                WebAPiCall aPiCall = new WebAPiCall();
                                aPiCall.allDistrict_listMethod(RegistrationNewActivity.this, RegistrationNewActivity.this);

                            } else {

                                Toast.makeText(RegistrationNewActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                            }

                            AccountType = "1";


                            break;
                        case R.id.NodalOfficer:


                            spnGenderAdapter = new SpinnerGenderAdapter(getApplicationContext(), genderlist);
                            binding.spnGender.setAdapter(spnGenderAdapter);

                            binding.llcourseName.setVisibility(View.GONE);
                            binding.llsemester.setVisibility(View.GONE);
                            binding.llsectionName.setVisibility(View.GONE);


                            if (GlobalClass.isNetworkConnected(RegistrationNewActivity.this)) {

                                WebAPiCall aPiCall = new WebAPiCall();
                                aPiCall.allDistrict_listMethod(RegistrationNewActivity.this, RegistrationNewActivity.this);

                            } else {

                                Toast.makeText(RegistrationNewActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                            }

                            AccountType = "2";
                            break;


                        case R.id.utypeStudent:


                            spnGenderAdapter = new SpinnerGenderAdapter(getApplicationContext(), genderlist);
                            binding.spnGender.setAdapter(spnGenderAdapter);

//                            binding.llcourseName.setVisibility(View.VISIBLE);
                            binding.llsemester.setVisibility(View.VISIBLE);
//                            binding.llsectionName.setVisibility(View.VISIBLE);


                            if (GlobalClass.isNetworkConnected(RegistrationNewActivity.this)) {

                                WebAPiCall aPiCall = new WebAPiCall();
                                aPiCall.allDistrict_listMethod(RegistrationNewActivity.this, RegistrationNewActivity.this);

                            } else {

                                Toast.makeText(RegistrationNewActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                            }
                            AccountType = "3";
                            break;

                    }
                    Toast.makeText(RegistrationNewActivity.this, checkedRadioButton.getText(), Toast.LENGTH_SHORT).show();

                }
            }
        });


        genderlist = new ArrayList<DummyData>();
        for (int i = 0; i <= 3; i++) {
            DummyData dummyData = new DummyData();
            if (i == 0) {
                dummyData.setImage(R.drawable.ic_gender);
                dummyData.setName("Select Your Gender");
                genderlist.add(dummyData);
            } else if (i == 1) {
                dummyData.setImage(R.drawable.person);
                dummyData.setName("Male");
                genderlist.add(dummyData);
            } else if (i == 2) {
                dummyData.setImage(R.drawable.ic_female);
                dummyData.setName("Female");
                genderlist.add(dummyData);
            } else if (i == 3) {
                dummyData.setImage(R.drawable.ic_icon);
                dummyData.setName("Others");
                genderlist.add(dummyData);
            } else {

            }

        }


        spnGenderAdapter = new SpinnerGenderAdapter(getApplicationContext(), genderlist);
        binding.spnGender.setAdapter(spnGenderAdapter);


        semesterList = new ArrayList<DummyData>();
        for (int i = 0; i <= 8; i++) {
            DummyData dummyData = new DummyData();
            if (i == 0) {
                dummyData.setImage(R.drawable.courseyear);
                dummyData.setName("Select Your Semester");
                semesterList.add(dummyData);
            } else if (i == 1) {
                dummyData.setImage(R.drawable.courseyear);
                dummyData.setName("1");
                semesterList.add(dummyData);
            } else if (i == 2) {
                dummyData.setImage(R.drawable.courseyear);
                dummyData.setName("2");
                semesterList.add(dummyData);
            } else if (i == 3) {
                dummyData.setImage(R.drawable.courseyear);
                dummyData.setName("3");
                semesterList.add(dummyData);
            } else if (i == 4) {
                dummyData.setImage(R.drawable.courseyear);
                dummyData.setName("4");
                semesterList.add(dummyData);
            } else if (i == 5) {
                dummyData.setImage(R.drawable.courseyear);
                dummyData.setName("5");
                semesterList.add(dummyData);
            } else if (i == 6) {
                dummyData.setImage(R.drawable.courseyear);
                dummyData.setName("6");
                semesterList.add(dummyData);
            } else if (i == 7) {
                dummyData.setImage(R.drawable.courseyear);
                dummyData.setName("7");
                semesterList.add(dummyData);
            } else if (i == 8) {
                dummyData.setImage(R.drawable.courseyear);
                dummyData.setName("8");
                semesterList.add(dummyData);
            } else {

            }

        }


        districAdapter = new SpinnerDistricAdapter(getApplicationContext(), allDistrict);
        binding.spnstudentDistric.setAdapter(districAdapter);


        spinnerCollegeAdapter = new SpinnerCollegeAdapter(getApplicationContext(), allcollegeList);
        binding.spnstudentCollegeName.setAdapter(spinnerCollegeAdapter);

        spinnerCourseBycollegeIdAdapter = new SpinnerCourseBycollegeIdAdapter(getApplicationContext(), allcourseList);
        binding.spnstudentcourseName.setAdapter(spinnerCourseBycollegeIdAdapter);


        subjectCobinationAdapter = new SpinnerSubjectCobinationAdapter(getApplicationContext(), SubjectCombList);
        binding.spnstudentsubcombination.setAdapter(subjectCobinationAdapter);


        binding.spnGender.setOnItemSelectedListener(this);
        binding.spnstudentDistric.setOnItemSelectedListener(this);
        binding.spnstudentsemester.setOnItemSelectedListener(this);
        binding.spnstudentCollegeName.setOnItemSelectedListener(this);
        binding.spnstudentcourseName.setOnItemSelectedListener(this);
        binding.spnstudentsubcombination.setOnItemSelectedListener(this);
        binding.spnstudentsectionName.setOnItemSelectedListener(this);


    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {

            selectImage();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            checkpermissions(RegistrationNewActivity.this);


        }

    };

    public void checkpermissions(Activity context) {
        if (Build.VERSION.SDK_INT >= 23) {

            new TedPermission(context)
                    .setPermissionListener(permissionListener)
                    //.setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                    .setPermissions(
                            android.Manifest.permission.INTERNET,
                            android.Manifest.permission.CAMERA,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE

                    )
                    .setGotoSettingButton(true)
                    .check();

        }

    }

    private boolean isValidMobile(String phone) {
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() >= 10 && phone.length() < 11;
            //return phone.length()==10;
        }
        return false;
    }

    @Override
    public void initData() {

    }

    public boolean Check_Data(View view) {
        if (!(AccountType == null)) {

            if ((AccountType.equalsIgnoreCase("1"))) {
                if (TextUtils.isEmpty(binding.edtusername.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing First Name", "Please Enter User First Name");
                    return false;
                } else if (TextUtils.isEmpty(binding.edtlastname.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Last Name", "Please Enter User Last Name");

                    return false;
                } else if (TextUtils.isEmpty(binding.edtmobile.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Mobile Number", "Please Enter Mobile Number");

                    return false;
                } else if (!isValidMobile(binding.edtmobile.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing 10 digits Mobile Number", "Please Enter 10 digits Mobile Number");

                    return false;

                } else if (!binding.edtemail.getText().toString().trim().matches(emailPattern)) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Email-Id", "Please Enter Correct Email");

                    return false;

                } else if (TextUtils.isEmpty(binding.edtpass.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Password", "Please Enter Password");

                    return false;

                } else if (TextUtils.isEmpty(binding.edtconfirmpass.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Confirm Password", "Please Enter Confirm Password");
                    return false;

                } else if (!binding.edtpass.getText().toString().trim().equals(binding.edtconfirmpass.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Miss-match Password and Confirm-password", "Your Password and Confirm-password does not match.");

                    return false;

                } else if (spnGenderCurrentPosition == 0) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Gender", "Please Select Your Gender.");

                    return false;
                } else if (spnDistricCurrentPosition == 0) {

                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing College District", "Please Select Your College District.");

                    return false;
                } else if (spncollegeCurrentPosition == 0) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing College", "Please Select Your College.");

                    return false;
                } /*else if (spnStreamCurrentPosition == 0) {
                    myLoaders.showSnackBar(view, "Please Select Your Stream.");
                    return false;
                }  else if (spnYearCurrentPosition == 0) {
                    myLoaders.showSnackBar(view, "Please Select Your Year.");
                    return false;
                }*/
                return true;
            } else if ((AccountType.equalsIgnoreCase("2"))) {


                if (TextUtils.isEmpty(binding.edtusername.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing First Name", "Please Enter User First Name");
                    return false;
                } else if (TextUtils.isEmpty(binding.edtlastname.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Last Name", "Please Enter User Last Name");

                    return false;
                } else if (TextUtils.isEmpty(binding.edtmobile.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Mobile Number", "Please Enter Mobile Number");

                    return false;
                } else if (!isValidMobile(binding.edtmobile.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing 10 digits Mobile Number", "Please Enter 10 digits Mobile Number");

                    return false;

                } else if (!binding.edtemail.getText().toString().trim().matches(emailPattern)) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Email-Id", "Please Enter Correct Email");

                    return false;

                } else if (TextUtils.isEmpty(binding.edtpass.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Password", "Please Enter Password");

                    return false;

                } else if (TextUtils.isEmpty(binding.edtconfirmpass.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Confirm Password", "Please Enter Confirm Password");
                    return false;

                } else if (!binding.edtpass.getText().toString().trim().equals(binding.edtconfirmpass.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Miss-match Password and Confirm-password", "Your Password and Confirm-password does not match.");

                    return false;

                } else if (spnGenderCurrentPosition == 0) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Gender", "Please Select Your Gender.");

                    return false;
                } else if (spnDistricCurrentPosition == 0) {

                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing College District", "Please Select Your College District.");

                    return false;
                } else if (spncollegeCurrentPosition == 0) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing College", "Please Select Your College.");

                    return false;
                }
                return true;
            } else if ((AccountType.equalsIgnoreCase("3"))) {
                if (TextUtils.isEmpty(binding.edtusername.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing First Name", "Please Enter User First Name");
                    return false;
                } else if (TextUtils.isEmpty(binding.edtlastname.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Last Name", "Please Enter User Last Name");

                    return false;
                } else if (TextUtils.isEmpty(binding.edtmobile.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Mobile Number", "Please Enter Mobile Number");

                    return false;
                } else if (!isValidMobile(binding.edtmobile.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing 10 digits Mobile Number", "Please Enter 10 digits Mobile Number");

                    return false;

                } else if (!binding.edtemail.getText().toString().trim().matches(emailPattern)) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Email-Id", "Please Enter Correct Email");

                    return false;

                } else if (TextUtils.isEmpty(binding.edtpass.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Password", "Please Enter Password");

                    return false;

                } else if (TextUtils.isEmpty(binding.edtconfirmpass.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Confirm Password", "Please Enter Confirm Password");
                    return false;

                } else if (!binding.edtpass.getText().toString().trim().equals(binding.edtconfirmpass.getText().toString().trim())) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Miss-match Password and Confirm-password", "Your Password and Confirm-password does not match.");

                    return false;

                } else if (spnGenderCurrentPosition == 0) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Gender", "Please Select Your Gender.");

                    return false;
                } else if (spnDistricCurrentPosition == 0) {

                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing College District", "Please Select Your College District.");

                    return false;
                } else if (spncollegeCurrentPosition == 0) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing College", "Please Select Your College.");

                    return false;
                } else if (schemeselected == false) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Scheme type", "Please Select Your Scheme Type.");


                    return false;
                } else if (spncourseCurrentPosition == 0) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Course Name", "Please Select Your Course Name.");


                    return false;
                } else if (spnsemesterCurrentPosition == 0) {
                    GlobalClass.dailogError(RegistrationNewActivity.this, "Missing Semester Name", "Please Select Your Semester Name.");
                    return false;
                }
            }
            return true;

        } else {

            GlobalClass.dailogError(RegistrationNewActivity.this, "Missing User Type", "Please Select Your User Type First.");

            return false;
        }

    }

    @Override
    public void initListeners() {

        binding.profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Build.VERSION.SDK_INT >= 23) {
                    checkpermissions(RegistrationNewActivity.this);
                } else {

                    selectImage();
                }

            }
        });


        binding.txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RegistrationNewActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });


        binding.chktcareOldAge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                              @Override
                                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                                  if (isChecked){
                                                                      chktcareOldAge = true;
                                                                      schemeselected = true;


                                                                  }else {
                                                                      chktcareOldAge = false;
                                                                    //  schemeselected = false;
                                                                      if (binding.chkruralArea.isChecked()||binding.chkAdoptionofLibraries.isChecked()){

                                                                          schemeselected = true;
                                                                      }else {

                                                                          schemeselected = false;
                                                                      }


                                                                  }

                                                              }
                                                          }
        );

        binding.chkAdoptionofLibraries.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                                      @Override
                                                                      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {



                                                                          if (isChecked){
                                                                              chkAdoptionofLibraries = true;
                                                                              schemeselected = true;


                                                                          }else {
                                                                              chkAdoptionofLibraries = false;
                                                                            //  schemeselected = false;
                                                                              if (binding.chkruralArea.isChecked()||binding.chktcareOldAge.isChecked()){

                                                                                  schemeselected = true;
                                                                              }else {

                                                                                  schemeselected = false;
                                                                              }



                                                                          }





                                                                      }
                                                                  }
        );

        binding.chkruralArea.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                            @Override
                                                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                                                                if (isChecked){

                                                                    chkruralArea = true;
                                                                    schemeselected = true;


                                                                }else {
                                                                    chkruralArea = false;

                                                                    if (binding.chkAdoptionofLibraries.isChecked()||binding.chktcareOldAge.isChecked()){

                                                                        schemeselected = true;
                                                                    }else {

                                                                        schemeselected = false;
                                                                    }



                                                                }

                                                            }
                                                        }
        );


        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             /*   if (binding.chktcareOldAge.isChecked()) {
                    chktcareOldAge = true;
                    schemeselected = true;

                }
                if (binding.chkAdoptionofLibraries.isChecked()) {

                    chkAdoptionofLibraries = true;
                    schemeselected = true;
                }
                if (binding.chkruralArea.isChecked()) {

                    chkruralArea = true;
                    schemeselected = true;
                }*/

                if (Check_Data(view)) {


                    userEmailId = binding.edtemail.getText().toString().trim();
                    userName = binding.edtusername.getText().toString().trim();
                    userFatherName = binding.edtlastname.getText().toString().trim();
                    confirmpassword = binding.edtconfirmpass.getText().toString().trim();
                    userMobilenumber = binding.edtmobile.getText().toString().trim();


                    if ((AccountType.equalsIgnoreCase("3"))) {

                        // userDistrictId="";
                        // userCollegeId="";
//                        userCourseType = "";
//                        otherProfessions = "";
//                        userCourseName = "";
//                        userYear = "";
//                        otheruserStateId = "";
//                        otherDistrictName = "";
                        //  facultyDesignation="";

                    } else if (AccountType.equalsIgnoreCase("2")) {
                        // userDistrictId="";
//                        userCollegeId = "";
//                        userCourseType = "";
//                        userCourseName = "";
//                        userYear = "";
//                        otherProfessions = edtuserProfession.getText().toString().trim();
//                        //  otheruserStateId="";
//                        //   otherDistrictName="";
//                        facultyDesignation = "";

                    }
                    if (AccountType.equalsIgnoreCase("1")) {

//                   userDistrictId="";
//                    userCollegeId="";
//                    userCourseType="";
//                    userCourseName="";
//                   userYear="";
//                        otherProfessions = "";
//                        otheruserStateId = "";
//                        otherDistrictName = "";
//                        facultyDesignation = "";
                    } else {


                    }


                    if (userstateID == null) {

                        userstateID = "13";
                    } else {


                    }
                    RequestBody userstate_ID = null;
                    try {
                        userstate_ID = RequestBody.create(MediaType.parse("multipart/form-data"), userstateID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    RequestBody user_address = null;
                    try {
                        user_address = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(chktcareOldAge));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    RequestBody userdisability_ID = null;
                    try {
                        userdisability_ID = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(chkAdoptionofLibraries));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    RequestBody DifferentlyAskedCategory_Type = null;
                    try {
                        DifferentlyAskedCategory_Type = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(chkruralArea));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    RequestBody userEmail_Id = null;
                    try {
                        userEmail_Id = RequestBody.create(MediaType.parse("multipart/form-data"), userEmailId);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    RequestBody user_Name = null;
                    try {
                        user_Name = RequestBody.create(MediaType.parse("multipart/form-data"), userName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    RequestBody user_Mobilenumber = null;
                    try {
                        user_Mobilenumber = RequestBody.create(MediaType.parse("multipart/form-data"), userMobilenumber);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    RequestBody confirmpasswordbody = null;
                    try {
                        confirmpasswordbody = RequestBody.create(MediaType.parse("multipart/form-data"), confirmpassword);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    String genderId = "0";
                    if (userGender.equalsIgnoreCase("Male")) {

                        genderId = "1";

                    } else if (userGender.equalsIgnoreCase("Female")) {

                        genderId = "2";
                    } else {

                        genderId = "3";
                    }
//userGender
                    RequestBody Gender = null;
                    try {
                        Gender = RequestBody.create(MediaType.parse("multipart/form-data"), genderId);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    RequestBody userDistric_ID = null;
                    try {
                        userDistric_ID = RequestBody.create(MediaType.parse("multipart/form-data"), userDistricID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    RequestBody userCollege_ID = null;
                    try {
                        userCollege_ID = RequestBody.create(MediaType.parse("multipart/form-data"), userCollegeID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    RequestBody usercourse_ID = null;
                    try {
                        usercourse_ID = RequestBody.create(MediaType.parse("multipart/form-data"), usercourseID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    RequestBody usersection_ID = null;
                    try {
                        usersection_ID = RequestBody.create(MediaType.parse("multipart/form-data"), usersectionID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    RequestBody usersubject_ID = null;
                    try {
                        usersubject_ID = RequestBody.create(MediaType.parse("multipart/form-data"), usersubjectID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    RequestBody userCast_ID = null;
                    try {
                        userCast_ID = RequestBody.create(MediaType.parse("multipart/form-data"), userCastID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    RequestBody user_percantageDiasbility = null;
                    try {
                        user_percantageDiasbility = RequestBody.create(MediaType.parse("multipart/form-data"), userpercantageDiasbility);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    RequestBody usersemesterName = null;
                    try {
                        usersemesterName = RequestBody.create(MediaType.parse("multipart/form-data"), usersemester);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    RequestBody userFather_Name = null;
                    try {
                        userFather_Name = RequestBody.create(MediaType.parse("multipart/form-data"), userFatherName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    RequestBody user_Dob = null;
                    try {
                        user_Dob = RequestBody.create(MediaType.parse("multipart/form-data"), userDob);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    RequestBody father_Mobilenumber = null;
                    try {
                        father_Mobilenumber = RequestBody.create(MediaType.parse("multipart/form-data"), fatherMobilenumber);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                   /* RequestBody user_Gender = null;fhfd
                    try {
                        user_Gender = RequestBody.create(MediaType.parse("multipart/form-data"), userGender);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/
                    RequestBody user_Rollnumber = null;
                    try {
                        user_Rollnumber = RequestBody.create(MediaType.parse("multipart/form-data"), userrollnumber);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    RequestBody FcmToken_Id = null;
                    try {
                        FcmToken_Id = RequestBody.create(MediaType.parse("multipart/form-data"), refreshedToken);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String AccountTypeSendToServer = "";

                    if (AccountType == "1") {
                        AccountTypeSendToServer = "1";

                    } else if (AccountType == "2") {
                        AccountTypeSendToServer = "2";

                    } else if (AccountType == "3") {
                        AccountTypeSendToServer = "3";

                    } else {

                    }


                    RequestBody Account_Type = null;
                    try {
                        Account_Type = RequestBody.create(MediaType.parse("multipart/form-data"), AccountTypeSendToServer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//userdisabilityID  --userdisability
                    RequestBody userdisability_Type = null;
                    try {
                        userdisability_Type = RequestBody.create(MediaType.parse("multipart/form-data"), userdisabilityID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    RequestBody edtothersCollegeData_Manually = null;
                    try {
                        edtothersCollegeData_Manually = RequestBody.create(MediaType.parse("multipart/form-data"), edtothersCollegeDataManually);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    RequestBody edtothersCourseData_Manually = null;
                    try {
                        edtothersCourseData_Manually = RequestBody.create(MediaType.parse("multipart/form-data"), edtothersCourseDataManually);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    RequestBody edtotherssectionnameData_Manually = null;
                    try {
                        edtotherssectionnameData_Manually = RequestBody.create(MediaType.parse("multipart/form-data"), edtotherssectionnameDataManually);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    RequestBody edtotherssubjctcombination_Manually = null;
                    try {
                        edtotherssubjctcombination_Manually = RequestBody.create(MediaType.parse("multipart/form-data"), edtotherssubjctcombination);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    RequestBody edtotherssemester_Manually = null;
                    try {
                        //  edtotherssemester_Manually = RequestBody.create(MediaType.parse("multipart/form-data"), edtotherssemester);
                        // edtotherssemester_Manually = RequestBody.create(MediaType.parse("multipart/form-data"), usersemesterName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    RequestBody imagerequestFile;
                    MultipartBody.Part imagebody = null;


                    if (imagefile == null) {
                        //GlobalClass.showtost(SignupActivity.this, "Select your image");
                        imagebody = null;
                    } else {
                        imagerequestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imagefile);
                        imagebody = MultipartBody.Part.createFormData("Document", imagefile.getName(), imagerequestFile);

                    }


                    RequestBody imagerequestFilecertificate;
                    MultipartBody.Part imagebodycertificate = null;




                    /*Calling registration APi after validation.... */

                    if (GlobalClass.isNetworkConnected(RegistrationNewActivity.this)) {
                        WebAPiCall webapiCall = new WebAPiCall();
                        webapiCall.registrationPostDataMethod(RegistrationNewActivity.this, RegistrationNewActivity.this, userDistric_ID, userCollege_ID, usercourse_ID, usersection_ID,
                                usersubject_ID, user_Rollnumber, user_Name, userFather_Name, user_Dob, Gender, user_Mobilenumber, father_Mobilenumber,
                                user_percantageDiasbility, userdisability_Type, userdisability_ID, DifferentlyAskedCategory_Type, userCast_ID,
                                user_address, confirmpasswordbody, userstate_ID, userEmail_Id, FcmToken_Id, usersemesterName, edtothersCollegeData_Manually, edtothersCourseData_Manually, edtotherssectionnameData_Manually, edtotherssubjctcombination_Manually, Account_Type,
                                imagebody,
                                imagebodycertificate);

                    } else {

                        Toast.makeText(RegistrationNewActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }


                  /*  Intent intent = new Intent(RegistrationNewActivity.this, MainActivity.class);
                    intent.putExtra("skiplogin", "skiplogin");
                    CSPreferences.putBolean(RegistrationNewActivity.this, "skiplogin", true);
                    startActivity(intent);
                    finish();*/
                }

            }
        });


    }

    // Select image from camera and gallery
    private void selectImage() {
        try {
            PackageManager pm = getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] options = {"Take Photo", "Choose From Gallery", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Select Option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            //  private final int others_user_type_disability_certificate_PICK_IMAGE_CAMERA = 5;
                            //   private final int others_user_type_disability_certificate_PICK_IMAGE_GALLERY = 6;
                            //   if (profile) {
                            startActivityForResult(intent, PICK_IMAGE_CAMERA);

//                            } else if (others_user_type_disability_certificate) {
//                                startActivityForResult(intent, others_user_type_disability_certificate_PICK_IMAGE_CAMERA);
//                            } else {
//                                startActivityForResult(intent, PICK_IMAGE_CAMERA_CERTIFICATE);
//                            }

                        } else if (options[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            // if (profile) {
                            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);

//                            } else if (others_user_type_disability_certificate) {
//                                startActivityForResult(pickPhoto, others_user_type_disability_certificate_PICK_IMAGE_GALLERY);
//                            } else {
//                                startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY_CERTIFICATE);
//                            }

                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (this.PICK_IMAGE_GALLERY == requestCode && resultCode == RESULT_OK) {


            Uri picUri = data.getData();
            imagefile = new File(getPaths(picUri));
            Log.d("imagegallary", String.valueOf(imagefile));
            binding.profilePic.setImageURI(picUri);
//            binding.btnUploadcertificatepic.setText("Captured");
//            binding.takecertificatePhoto.setImageResource(R.drawable.checkgreen);
            binding.btnUploadprofilepic.setText("Captured");
            binding.btnUploadprofilepic.setTextColor(ContextCompat.getColor(RegistrationNewActivity.this,
                    R.color.green));

            binding.materialCardView.setBackgroundColor(ContextCompat.getColor(RegistrationNewActivity.this,
                    R.color.green));
            binding.takePhoto.setImageResource(R.drawable.checkgreen);

        }

        if (requestCode == PICK_IMAGE_CAMERA && resultCode == Activity.RESULT_OK && data != null) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");

            binding.profilePic.setImageBitmap(photo);
            Uri tempUri = getImageUri(this, photo);
            imagefile = new File(getRealPathFromURI(tempUri));
//            binding.btnUploadcertificatepic.setText("Captured");
//            binding.takecertificatePhoto.setImageResource(R.drawable.checkgreen);
            binding.btnUploadprofilepic.setText("Captured");
            binding.btnUploadprofilepic.setTextColor(ContextCompat.getColor(RegistrationNewActivity.this,
                    R.color.green));
            binding.materialCardView.setBackgroundColor(ContextCompat.getColor(RegistrationNewActivity.this,
                    R.color.green));
            binding.takePhoto.setImageResource(R.drawable.checkgreen);

            Log.d("imagecamera", String.valueOf(imagefile));

        }

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

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {


        Calendar cal = Calendar.getInstance();
        //  cal.add(Calendar.DATE, 1);
        Date date = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
        String inActiveDate = null;
        inActiveDate = format1.format(date);
        System.out.println(inActiveDate);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        //String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title"+Calendar.getInstance().getTime(), null);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "ProfilePicName" + inActiveDate, null);
        return Uri.parse(path);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        int id = adapterView.getId();
        if (id == R.id.spnGender) {

            if (position != 0) {

                spnGenderCurrentPosition = position;

                userGender = genderlist.get(position).getName();
                Toast.makeText(getApplicationContext(), genderlist.get(position).getName(), Toast.LENGTH_LONG).show();

            } else {
                spnGenderCurrentPosition = position;

            }

        } else if (id == R.id.spnstudentsemester) {

            if (position != 0) {

                spnsemesterCurrentPosition = position;

                usersemester = semesterList.get(position).getName();
                Toast.makeText(getApplicationContext(), semesterList.get(position).getName(), Toast.LENGTH_LONG).show();

            } else {
                spnsemesterCurrentPosition = position;

            }

        } else if (id == R.id.spnstudentDistric) {

            if (position != 0) {

                spnDistricCurrentPosition = position;

                userCollegeDistrict = allDistrict.get(position).getCollegeDistrict();
                userDistricID = String.valueOf(allDistrict.get(position).getDistrictId());

                if (GlobalClass.isNetworkConnected(RegistrationNewActivity.this)) {

                    WebAPiCall aPiCall = new WebAPiCall();
                    aPiCall.allCollege_listMethod(RegistrationNewActivity.this, userDistricID, RegistrationNewActivity.this);

                } else {

                    Toast.makeText(RegistrationNewActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }


                //Toast.makeText(getApplicationContext(), courseYear.get(position).getName(), Toast.LENGTH_LONG).show();

            } else {
                spnDistricCurrentPosition = position;

            }
        } else if (id == R.id.spnstudentCollegeName) {

            if (position != 0) {

                spncollegeCurrentPosition = position;

                userCollegeName = allcollegeList.get(position).getCollege();
                userCollegeID = String.valueOf(allcollegeList.get(position).getCollegeId());

                if (AccountType.equalsIgnoreCase("3")) {


                    if (allcollegeList.get(position).getCollegeId() == 0) {

                        binding.llcourseName.setVisibility(View.GONE);


                    } else {


                        if (GlobalClass.isNetworkConnected(RegistrationNewActivity.this)) {
                            WebAPiCall aPiCall = new WebAPiCall();
                            aPiCall.allCourse_listMethod(RegistrationNewActivity.this, userCollegeID, RegistrationNewActivity.this);

                        } else {
                            Toast.makeText(RegistrationNewActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

                        }

                        if (GlobalClass.isNetworkConnected(RegistrationNewActivity.this)) {
                            WebAPiCall aPiCall = new WebAPiCall();
                            aPiCall.allCollegeScheme_listMethod(RegistrationNewActivity.this, userCollegeID, RegistrationNewActivity.this);

                        } else {
                            Toast.makeText(RegistrationNewActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

                        }

                        binding.llcourseName.setVisibility(View.VISIBLE);

                    }
                } else {

                    binding.llcourseName.setVisibility(View.GONE);
                    binding.llsectionName.setVisibility(View.GONE);
                    binding.llsemester.setVisibility(View.GONE);
                }


                //Toast.makeText(getApplicationContext(), courseYear.get(position).getName(), Toast.LENGTH_LONG).show();

            } else {
                spncollegeCurrentPosition = position;

            }


        } else if (id == R.id.spnstudentcourseName) {

            if (position != 0) {

                spncourseCurrentPosition = position;

                usercourseID = String.valueOf(allcourseList.get(position).getCourseId());
                usercoursName = allcourseList.get(position).getCourseName();


                if (allcourseList.get(position).getCourseId() == 0) {
                    //  binding.llcourseName.setVisibility(View.GONE);
                    binding.llsectionName.setVisibility(View.GONE);


                } else {
                    if (GlobalClass.isNetworkConnected(RegistrationNewActivity.this)) {

                        WebAPiCall aPiCall = new WebAPiCall();
                        aPiCall.allSection_listMethod(RegistrationNewActivity.this, userCollegeID, usercourseID, RegistrationNewActivity.this);

                    } else {

                        Toast.makeText(RegistrationNewActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }


                    // binding.llcourseName.setVisibility(View.VISIBLE);
                    binding.llsectionName.setVisibility(View.VISIBLE);


                }


                //Toast.makeText(getApplicationContext(), courseYear.get(position).getName(), Toast.LENGTH_LONG).show();

            } else {
                spncourseCurrentPosition = position;

            }

        } else if (id == R.id.spnstudentsectionName) {

            if (position != 0) {

                spnsectionCurrentPosition = position;
                binding.spnstudentsemester.setVisibility(View.VISIBLE);
                usersection = allSectionList.get(position).getSectionName();
                usersectionID = String.valueOf(allSectionList.get(position).getId());

                if (GlobalClass.isNetworkConnected(RegistrationNewActivity.this)) {

                    SpinnerSemsterAdapter spinnerYearAdapter = new SpinnerSemsterAdapter(getApplicationContext(), semesterList);
                    binding.spnstudentsemester.setAdapter(spinnerYearAdapter);


                    // WebAPiCall aPiCall = new WebAPiCall();
                    // aPiCall.allSubjectCombination_listMethod(RegistrationNewActivity.this, userCollegeID, usercourseID, usersectionID, RegistrationNewActivity.this);

                } else {

                    Toast.makeText(RegistrationNewActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }

                //Toast.makeText(getApplicationContext(), courseYear.get(position).getName(), Toast.LENGTH_LONG).show();

            } else {

                spnsectionCurrentPosition = position;
                binding.spnstudentsemester.setVisibility(View.GONE);

            }
        } else if (id == R.id.spnstudentsubcombination) {

            if (position != 0) {

                spnsubCombinationCurrentPosition = position;

                usersubjectID = String.valueOf(SubjectCombList.get(position).getSubjectCombinationId());
                usersubjectName = SubjectCombList.get(position).getSubjectCombination();


                //Toast.makeText(getApplicationContext(), courseYear.get(position).getName(), Toast.LENGTH_LONG).show();

            } else {
                spnsubCombinationCurrentPosition = position;

            }

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void GetAllCollegeData(List<CollegeListResponse.Datum> list) {

        allcollegeList.clear();
        allcollegeList.addAll(list);

        CollegeListResponse.Datum datum = new CollegeListResponse.Datum();
        datum.setCollege("Select your College.");
        datum.setCollegeId(0);
        allcollegeList.add(0, datum);

        spinnerCollegeAdapter = new SpinnerCollegeAdapter(getApplicationContext(), allcollegeList);
        binding.spnstudentCollegeName.setAdapter(spinnerCollegeAdapter);

    }

    @Override
    public void GetAllDistricData(List<GetDistrictResponse.Datum> list) {

        allDistrict.clear();
        allDistrict.addAll(list);

        GetDistrictResponse.Datum district = new GetDistrictResponse.Datum();
        district.setCollegeDistrict("Select your District Name");
        district.setDistrictId(0);
        allDistrict.add(0, district);

        districAdapter = new SpinnerDistricAdapter(getApplicationContext(), allDistrict);
        binding.spnstudentDistric.setAdapter(districAdapter);

    }


    @Override
    public void GetAllCourseByCollegeIDData(List<CourseByCollegeIDResponse.Datum> list) {

        allcourseList.clear();
        allcourseList.addAll(list);

        CourseByCollegeIDResponse.Datum datum = new CourseByCollegeIDResponse.Datum();
        datum.setCourseName("Select your Course.");
        datum.setCourseId(0);
        allcourseList.add(0, datum);

        spinnerCourseBycollegeIdAdapter = new SpinnerCourseBycollegeIdAdapter(getApplicationContext(), allcourseList);
        binding.spnstudentcourseName.setAdapter(spinnerCourseBycollegeIdAdapter);
    }

    @Override
    public void GetAllSectionData(List<SectionResponse.Datum> list) {
        allSectionList.clear();
        allSectionList.addAll(list);

        SectionResponse.Datum datum = new SectionResponse.Datum();
        datum.setSectionName("Select your Section.");
        datum.setId(0);
        allSectionList.add(0, datum);

        sectionAdapter = new SpinnerSectionAdapter(getApplicationContext(), allSectionList);
        binding.spnstudentsectionName.setAdapter(sectionAdapter);

    }

    @Override
    public void GetSubjectCombinationData(List<SubjectCombinationResponse.Datum> list) {


        SubjectCombList.clear();
        SubjectCombList.addAll(list);

        SubjectCombinationResponse.Datum datum = new SubjectCombinationResponse.Datum();
        datum.setSubjectCombination("Select your Subject Combination.");
        datum.setSubjectCombinationId(0);
        SubjectCombList.add(0, datum);

        subjectCobinationAdapter = new SpinnerSubjectCobinationAdapter(getApplicationContext(), SubjectCombList);
        binding.spnstudentsubcombination.setAdapter(subjectCobinationAdapter);


    }

    @Override
    public void GetSchemeCollegeData(List<SchemeOfCollegeResponse.Datum> list) {

        if (!(list == null)) {
            binding.llscheme.setVisibility(View.VISIBLE);
            binding.txtscheme.setVisibility(View.VISIBLE);

            if (list.get(0).isTakeCare()) {

                binding.chktcareOldAge.setVisibility(View.VISIBLE);

            }

            if (list.get(0).isAdoptionOfLibrary()) {

                binding.chkAdoptionofLibraries.setVisibility(View.VISIBLE);


            }

            if (list.get(0).isTakeCareInRural()) {

                binding.chkruralArea.setVisibility(View.VISIBLE);


            }/* else {

                binding.llscheme.setVisibility(View.GONE);
                binding.txtscheme.setVisibility(View.GONE);
                binding.chktcareOldAge.setVisibility(View.GONE);
                binding.chkAdoptionofLibraries.setVisibility(View.GONE);
                binding.chkruralArea.setVisibility(View.GONE);


            }*/
        } else {

            binding.llscheme.setVisibility(View.GONE);
            binding.txtscheme.setVisibility(View.GONE);

        }


    }
}