package hsvp.survey.hry.pkl.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import java.util.List;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.allinterface.GetAllNodelOfficerCollegeData_interface;
import hsvp.survey.hry.pkl.apicall.WebAPiCall;
import hsvp.survey.hry.pkl.databinding.ActivityAdoptionOfLibStep1Binding;
import hsvp.survey.hry.pkl.models.NodalofficerOfCollegeResponse;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.CSPreferences;
import hsvp.survey.hry.pkl.utility.GlobalClass;

public class AdoptionOfLibStep1Activity extends BaseActivity implements GetAllNodelOfficerCollegeData_interface {
    ActivityAdoptionOfLibStep1Binding binding;
    String userGender, COllegeID, token, User_Id, schemeId, NodalOfficreId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_adoption_of_lib_step1);
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

        if (GlobalClass.isNetworkConnected(AdoptionOfLibStep1Activity.this)) {
            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.getNodelofficerofCollegeScheme_listMethod(AdoptionOfLibStep1Activity.this, AdoptionOfLibStep1Activity.this, "Bearer " + token, User_Id, COllegeID, schemeId, AdoptionOfLibStep1Activity.this);


        } else {

            Toast.makeText(AdoptionOfLibStep1Activity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void initData() {
        binding.toolbar.tvToolbarTitle.setText("Scheme B Basic Detail");

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

                if (Check_Data(view)) {


                    if (CSPreferences.readString(AdoptionOfLibStep1Activity.this, "LibrariesAdoptionStep1").equalsIgnoreCase("N")) {

                        if (GlobalClass.isNetworkConnected(AdoptionOfLibStep1Activity.this)) {

                            WebAPiCall webapiCall = new WebAPiCall();
                            webapiCall.adoptedStep1Method(AdoptionOfLibStep1Activity.this, AdoptionOfLibStep1Activity.this,

                                    "Bearer " + token,
                                    User_Id,
                                    NodalOfficreId,


                                    binding.edtlibexistornot.getText().toString().trim(),
                                    binding.edtlibfacilities.getText().toString().trim(),
                                    binding.edtLibraryInchargeName.getText().toString().trim(),


                                    binding.edtoldandorphanname.getText().toString().trim(),
                                    binding.edtoldandorphannaddress.getText().toString().trim(),
                                    binding.edtoldandorphanninchargeName.getText().toString().trim()


                            );


                        } else {

                            Toast.makeText(AdoptionOfLibStep1Activity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                        }

                    } else {
                        binding.btnSubmit.setText("Ok, Let's go for daily activity");
                        Intent i = new Intent(AdoptionOfLibStep1Activity.this, AdoptionDailyActivitiesActivity.class);
                        startActivity(i);
                        // finish();
                    }

                }

            }
        });


    }


    public boolean Check_Data(View view) {

        if (TextUtils.isEmpty(binding.edtnodalOfficer.getText().toString().trim())) {
            GlobalClass.dailogError(AdoptionOfLibStep1Activity.this, "Nodal Officer Missing!", "Please contact to your college to know your Nodal officer.");

            return false;
        } else if (TextUtils.isEmpty(binding.edtnodalOfficermobile.getText().toString().trim())) {
            GlobalClass.dailogError(AdoptionOfLibStep1Activity.this, "Nodal Officer Mobile Number Missing!", "Please type solution provided by student.");

            return false;

        } /*else if (TextUtils.isEmpty(binding.edtrequiredment.getText().toString().trim())) {
            GlobalClass.dailogError(AdoptionOfLibStep1Activity.this, "Requirement of person/orphans Missing!", "Please type solution provided by student.");

            return false;

        }*/ else if (TextUtils.isEmpty(binding.edtoldandorphanname.getText().toString().trim())) {
            GlobalClass.dailogError(AdoptionOfLibStep1Activity.this, "Name of Old age Or Orphan age Home Missing!", "Please Type Name of Old age Or Orphan age Home.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtoldandorphannaddress.getText().toString().trim())) {
            GlobalClass.dailogError(AdoptionOfLibStep1Activity.this, "Address of Old age Or Orphan age Home Missing!", "Please Address Name of Old age Or Orphan age Home.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtoldandorphanninchargeName.getText().toString().trim())) {
            GlobalClass.dailogError(AdoptionOfLibStep1Activity.this, "Name of Incharge Missing!", "Please Type Name of Incharge of Old age Or Orphan age Home.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtlibexistornot.getText().toString().trim())) {
            GlobalClass.dailogError(AdoptionOfLibStep1Activity.this, "Lirary existance Missing!", "Please Type Lirary existance Information.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtLibraryInchargeName.getText().toString().trim())) {
            GlobalClass.dailogError(AdoptionOfLibStep1Activity.this, "Lirary Incharge Name Missing!", "Please Type Lirary Incharge Name.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtlibfacilities.getText().toString().trim())) {
            GlobalClass.dailogError(AdoptionOfLibStep1Activity.this, "Lirary Facilites Missing!", "Please Type Lirary faciliteis.");

            return false;

        }/* else if (TextUtils.isEmpty(binding.edtage.getText().toString().trim())) {
            GlobalClass.dailogError(AdoptionOfLibStep1Activity.this, "Adopted person Age. Missing!", "Please Type Adopted person Age.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtgender.getText().toString().trim())) {
            GlobalClass.dailogError(AdoptionOfLibStep1Activity.this, "Adopted person Gender. Missing!", "Please Type Adopted person Gender.");

            return false;

        } else if (TextUtils.isEmpty(binding.edthealthStatus.getText().toString().trim())) {
            GlobalClass.dailogError(AdoptionOfLibStep1Activity.this, "Adopted person Health Status. Missing!", "Please Type Adopted person Health Status.");

            return false;

        } else if (imagefile == null) {
            // myLoaders.showSnackBar(view, "Please Enter Password");
            GlobalClass.dailogError(AdoptionOfLibStep1Activity.this, "Image Missing!", "Please take a picture of your Activities.");
            return false;
        } else if (binding.lat.getText().toString().trim().isEmpty() && binding.longitude.getText().toString().trim().isEmpty()) {
            GlobalClass.dailogError(AdoptionOfLibStep1Activity.this, "Location of your Activity is  Missing!", "Please make sure gps or location is Enable / ON of phone to get plant location.");

            return false;

        }*/
        return true;
    }

    @Override
    public void GetAllNodelOfficerCollegeData(List<NodalofficerOfCollegeResponse.ResponeDatum> list) {
        binding.edtnodalOfficer.setText(list.get(0).getFirstName());
        binding.edtnodalOfficermobile.setText(String.valueOf(list.get(0).getMobile()));
        binding.edtnodalOfficeremail.setText(list.get(0).getEmail());


        CSPreferences.putString(AdoptionOfLibStep1Activity.this, "adoptednodalOfficerId", list.get(0).getId());
        NodalOfficreId = list.get(0).getId();


    }

    @Override
    public void Getform1CollegeData(List<NodalofficerOfCollegeResponse.ResponeData1> list) {

        if (list == null || list.isEmpty()) {


            binding.edtlibexistornot.setEnabled(true);
            binding.edtLibraryInchargeName.setEnabled(true);
            binding.edtlibfacilities.setEnabled(true);
            binding.edtoldandorphanname.setEnabled(true);
            binding.edtoldandorphannaddress.setEnabled(true);
            binding.edtoldandorphanninchargeName.setEnabled(true);

        } else {

            try {


                binding.edtlibexistornot.setEnabled(false);
                binding.edtLibraryInchargeName.setEnabled(false);
                binding.edtlibfacilities.setEnabled(false);
                binding.edtoldandorphanname.setEnabled(false);
                binding.edtoldandorphannaddress.setEnabled(false);
                binding.edtoldandorphanninchargeName.setEnabled(false);

                binding.edtlibexistornot.setText(list.get(0).getLibraryAlreadyExist());
                binding.edtLibraryInchargeName.setText(list.get(0).getLibraryInchargeName());
                binding.edtlibfacilities.setText(list.get(0).getLibraryFacility());
                binding.edtoldandorphanname.setText(list.get(0).getOldAgeOrphanName());
                binding.edtoldandorphannaddress.setText(list.get(0).getOldAGeOrphanAddress());
                binding.edtoldandorphanninchargeName.setText(list.get(0).getOldAgeOrphanInchargeName());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}