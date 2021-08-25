package hsvp.survey.hry.pkl.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;
import java.util.List;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.adapter.SpinnerGenderAdapter;
import hsvp.survey.hry.pkl.allinterface.GetAllNodelOfficerCollegeData_interface;
import hsvp.survey.hry.pkl.apicall.WebAPiCall;
import hsvp.survey.hry.pkl.databinding.ActivityOldAgeTakeCareStep1Binding;
import hsvp.survey.hry.pkl.models.DummyData;
import hsvp.survey.hry.pkl.models.NodalofficerOfCollegeResponse;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.CSPreferences;
import hsvp.survey.hry.pkl.utility.GlobalClass;

public class OldAgeTakeCareStep1Activity extends BaseActivity implements GetAllNodelOfficerCollegeData_interface, AdapterView.OnItemSelectedListener {
    ActivityOldAgeTakeCareStep1Binding binding;
    String COllegeID, token, User_Id, schemeId, NodalOfficreId, userGender;
    SpinnerGenderAdapter spnGenderAdapter;
    ArrayList<DummyData> genderlist;
    int spnGenderCurrentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_old_age_take_care_step1);

        genderlist = new ArrayList<DummyData>();

        for (int i = 0; i <= 3; i++) {
            DummyData dummyData = new DummyData();
            if (i == 0) {
                dummyData.setImage(R.drawable.ic_gender);
                dummyData.setName("Select Adopted person Gender");
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
        binding.spnGender.setOnItemSelectedListener(this);

        try {


            Bundle extras = getIntent().getExtras();


            if (extras != null) {

                schemeId = extras.getString("schemeId");

            }
            User_Id = CSPreferences.readString(this, "userId");
            token = CSPreferences.readString(this, "token");
            COllegeID = CSPreferences.readString(this, "CollegeId");


        } catch (Exception e) {

            e.printStackTrace();
        }

        if (GlobalClass.isNetworkConnected(OldAgeTakeCareStep1Activity.this)) {
            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.getNodelofficerofCollegeScheme_listMethod(OldAgeTakeCareStep1Activity.this, OldAgeTakeCareStep1Activity.this, "Bearer " + token, User_Id, COllegeID, schemeId, OldAgeTakeCareStep1Activity.this);


        } else {

            Toast.makeText(OldAgeTakeCareStep1Activity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void initData() {

        binding.toolbar.tvToolbarTitle.setText("Scheme A Basic Detail");

/*
                            CSPreferences.putString(activity, "token", response.body().getData().getToken());
                            CSPreferences.putString(activity, "CollegeId", response.body().getData().getCollegeId());
                            CSPreferences.putString(activity, "userId", response.body().getData().getId());
*/


    }

    @Override
    public void initListeners() {


        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CSPreferences.readString(OldAgeTakeCareStep1Activity.this, "TakeCareStep1").equalsIgnoreCase("N")) {

                    if (Check_Data(view)) {


                        String genderId = "0";
                        if (userGender.equalsIgnoreCase("Male")) {

                            genderId = "1";

                        } else if (userGender.equalsIgnoreCase("Female")) {

                            genderId = "2";
                        } else {

                            genderId = "3";
                        }

                    /*
token
StudentId
NodalOfficreId
OldAge_OrphanName
OldAGe_OrphanAddress
OldAge_OrphanInchargeName*/


                        if (GlobalClass.isNetworkConnected(OldAgeTakeCareStep1Activity.this)) {

                            WebAPiCall webapiCall = new WebAPiCall();
                            webapiCall.TakecareStep1Method(OldAgeTakeCareStep1Activity.this, OldAgeTakeCareStep1Activity.this,

                                    "Bearer " + token, User_Id, NodalOfficreId, binding.edtoldandorphanname.getText().toString().trim(),
                                    binding.edtoldandorphannaddress.getText().toString().trim(),
                                    binding.edtoldandorphanninchargeName.getText().toString().trim(),


                                    binding.edtName.getText().toString().trim(),
                                    binding.edcontactno.getText().toString().trim(),
                                    binding.edtage.getText().toString().trim(),
                                    genderId,
                                    binding.edthealthStatus.getText().toString().trim()


                            );


                        } else {

                            Toast.makeText(OldAgeTakeCareStep1Activity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                        }


                   /* Intent i = new Intent(OldAgeTakeCareStep1Activity.this, OldAgeDailyActivitiesActivity.class);
                    startActivity(i);
                    finish();*/

                    }


                } else {


                    binding.btnSubmit.setText("Ok, Let's go for daily activity");
                    if (Check_Data_AfterFormFilledUp(view)) {

                        Intent i = new Intent(OldAgeTakeCareStep1Activity.this, OldAgeDailyActivitiesActivity.class);
                        startActivity(i);
                    }

                }


            }
        });


    }


    public boolean Check_Data(View view) {

        if (TextUtils.isEmpty(binding.edtnodalOfficer.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Nodal Officer Missing!", "Please contact to your college to know your Nodal officer detail.");

            return false;
        } else if (TextUtils.isEmpty(binding.edtnodalOfficermobile.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Nodal Officer Mobile Number Missing!", "Please contact to your college to know your Nodal officer detail.");

            return false;

        } /*else if (TextUtils.isEmpty(binding.edtrequiredment.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Requirement of person/orphans Missing!", "Please type solution provided by student.");

            return false;

        }*/ else if (TextUtils.isEmpty(binding.edtoldandorphanname.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Name of Old age Or Orphan age Home Missing!", "Please Type Name of Old age Or Orphan age Home.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtoldandorphannaddress.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Address of Old age Or Orphan age Home Missing!", "Please Address Name of Old age Or Orphan age Home.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtoldandorphanninchargeName.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Name of Incharge Missing!", "Please Type Name of Incharge of Old age Or Orphan age Home.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtName.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Adopted person Name Missing!", "Please Type Adopted person Name.");

            return false;

        } else if (TextUtils.isEmpty(binding.edcontactno.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Adopted person Contact number. Missing!", "Please Type Adopted person Contact Number.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtage.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Adopted person Age. Missing!", "Please Type Adopted person Age.");

            return false;

        } else if (spnGenderCurrentPosition == 0) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Adopted person Gender. Missing!", "Please Select Adopted person Gender.");

            return false;

        } else if (TextUtils.isEmpty(binding.edthealthStatus.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Adopted person Health Status. Missing!", "Please Type Adopted person Health Status.");

            return false;

        } /*else if (imagefile == null) {
            // myLoaders.showSnackBar(view, "Please Enter Password");
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Image Missing!", "Please take a picture of your Activities.");
            return false;
        } else if (binding.lat.getText().toString().trim().isEmpty() && binding.longitude.getText().toString().trim().isEmpty()) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Location of your Activity is  Missing!", "Please make sure gps or location is Enable / ON of phone to get plant location.");

            return false;

        }*/
        return true;
    }


    public boolean Check_Data_AfterFormFilledUp(View view) {

        if (TextUtils.isEmpty(binding.edtnodalOfficer.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Nodal Officer Missing!", "Please contact to your college to know your Nodal officer detail.");

            return false;
        } else if (TextUtils.isEmpty(binding.edtnodalOfficermobile.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Nodal Officer Mobile Number Missing!", "Please contact to your college to know your Nodal officer detail.");

            return false;

        } /*else if (TextUtils.isEmpty(binding.edtrequiredment.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Requirement of person/orphans Missing!", "Please type solution provided by student.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtoldandorphanname.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Name of Old age Or Orphan age Home Missing!", "Please Type Name of Old age Or Orphan age Home.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtoldandorphannaddress.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Address of Old age Or Orphan age Home Missing!", "Please Address Name of Old age Or Orphan age Home.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtoldandorphanninchargeName.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Name of Incharge Missing!", "Please Type Name of Incharge of Old age Or Orphan age Home.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtName.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Adopted person Name Missing!", "Please Type Adopted person Name.");

            return false;

        } else if (TextUtils.isEmpty(binding.edcontactno.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Adopted person Contact number. Missing!", "Please Type Adopted person Contact Number.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtage.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Adopted person Age. Missing!", "Please Type Adopted person Age.");

            return false;

        } else if (spnGenderCurrentPosition == 0) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Adopted person Gender. Missing!", "Please Select Adopted person Gender.");

            return false;

        } else if (TextUtils.isEmpty(binding.edthealthStatus.getText().toString().trim())) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Adopted person Health Status. Missing!", "Please Type Adopted person Health Status.");

            return false;

        } else if (imagefile == null) {
            // myLoaders.showSnackBar(view, "Please Enter Password");
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Image Missing!", "Please take a picture of your Activities.");
            return false;
        } else if (binding.lat.getText().toString().trim().isEmpty() && binding.longitude.getText().toString().trim().isEmpty()) {
            GlobalClass.dailogError(OldAgeTakeCareStep1Activity.this, "Location of your Activity is  Missing!", "Please make sure gps or location is Enable / ON of phone to get plant location.");

            return false;

        }*/
        return true;
    }

    @Override
    public void GetAllNodelOfficerCollegeData(List<NodalofficerOfCollegeResponse.ResponeDatum> list) {


        binding.edtnodalOfficer.setText(list.get(0).getFirstName());
        binding.edtnodalOfficermobile.setText(String.valueOf(list.get(0).getMobile()));
        binding.edtnodalOfficeremail.setText(list.get(0).getEmail());
        CSPreferences.putString(OldAgeTakeCareStep1Activity.this, "takecarenodalOfficerId", list.get(0).getId());
        NodalOfficreId = list.get(0).getId();


    }

    @Override
    public void Getform1CollegeData(List<NodalofficerOfCollegeResponse.ResponeData1> list) {

        if (list == null || list.isEmpty()) {


            binding.edtName.setEnabled(true);
            binding.edcontactno.setEnabled(true);
            binding.edtage.setEnabled(true);
            binding.edthealthStatus.setEnabled(true);
            binding.edtoldandorphanname.setEnabled(true);
            binding.edtoldandorphannaddress.setEnabled(true);
            binding.edtoldandorphanninchargeName.setEnabled(true);

            binding.tlgender.setVisibility(View.GONE);
            binding.llgender.setVisibility(View.VISIBLE);


        } else {
            try {


                binding.llgender.setVisibility(View.GONE);
                binding.tlgender.setVisibility(View.VISIBLE);
                binding.edtgender.setEnabled(false);
                binding.edtName.setEnabled(false);
                binding.edcontactno.setEnabled(false);
                binding.edtage.setEnabled(false);
                binding.edthealthStatus.setEnabled(false);
                binding.edtoldandorphanname.setEnabled(false);
                binding.edtoldandorphannaddress.setEnabled(false);
                binding.edtoldandorphanninchargeName.setEnabled(false);

                binding.edtgender.setText(list.get(0).getGender());
                binding.edtName.setText(list.get(0).getAdoptedPerson());
                binding.edcontactno.setText(list.get(0).getMobile());
                binding.edtage.setText(list.get(0).getAge());
                binding.edthealthStatus.setText(list.get(0).getHealthStatus());
                binding.edtoldandorphanname.setText(list.get(0).getOldAgeOrphanName());
                binding.edtoldandorphannaddress.setText(list.get(0).getOldAGeOrphanAddress());
                binding.edtoldandorphanninchargeName.setText(list.get(0).getOldAgeOrphanInchargeName());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (position != 0) {

            spnGenderCurrentPosition = position;

            userGender = genderlist.get(position).getName();
            Toast.makeText(getApplicationContext(), genderlist.get(position).getName(), Toast.LENGTH_LONG).show();

        } else {
            spnGenderCurrentPosition = position;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}