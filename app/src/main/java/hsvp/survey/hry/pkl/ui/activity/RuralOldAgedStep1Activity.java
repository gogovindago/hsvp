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
import hsvp.survey.hry.pkl.adapter.SpinnerDistricAdapter;
import hsvp.survey.hry.pkl.adapter.SpinnerGenderAdapter;
import hsvp.survey.hry.pkl.allinterface.GetAllDistricData_interface;
import hsvp.survey.hry.pkl.allinterface.GetAllNodelOfficerCollegeData_interface;
import hsvp.survey.hry.pkl.apicall.WebAPiCall;
import hsvp.survey.hry.pkl.databinding.ActivityRuralOldAgedStep1Binding;
import hsvp.survey.hry.pkl.models.DummyData;
import hsvp.survey.hry.pkl.models.GetDistrictResponse;
import hsvp.survey.hry.pkl.models.NodalofficerOfCollegeResponse;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.CSPreferences;
import hsvp.survey.hry.pkl.utility.GlobalClass;

public class RuralOldAgedStep1Activity extends BaseActivity implements GetAllDistricData_interface, AdapterView.OnItemSelectedListener, GetAllNodelOfficerCollegeData_interface {
    ActivityRuralOldAgedStep1Binding binding;
    SpinnerDistricAdapter districAdapter;
    private List<GetDistrictResponse.Datum> allDistrict = new ArrayList<GetDistrictResponse.Datum>();
    int spnDistricCurrentPosition = 0;
    String userDistricID, userCollegeDistrict;
    SpinnerGenderAdapter spnGenderAdapter;
    ArrayList<DummyData> genderlist;
    int spnGenderCurrentPosition = 0;
    String userGender, COllegeID, token, User_Id, schemeId, NodalOfficreId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rural_old_aged__step1);
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

        if (CSPreferences.readString(RuralOldAgedStep1Activity.this, "RuralTakeCareStep1").equalsIgnoreCase("N")) {
            if (GlobalClass.isNetworkConnected(RuralOldAgedStep1Activity.this)) {

                WebAPiCall aPiCall = new WebAPiCall();
                aPiCall.allDistrict_listMethod(RuralOldAgedStep1Activity.this, RuralOldAgedStep1Activity.this);

            } else {

                Toast.makeText(RuralOldAgedStep1Activity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
            }

        }else {


            binding.btnSubmit.setText("Ok, Let's go for daily activity");
        }




        districAdapter = new SpinnerDistricAdapter(getApplicationContext(), allDistrict);
        binding.spnstudentDistric.setAdapter(districAdapter);

        binding.spnstudentDistric.setOnItemSelectedListener(this);

        if (GlobalClass.isNetworkConnected(RuralOldAgedStep1Activity.this)) {
            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.getNodelofficerofCollegeScheme_listMethod(RuralOldAgedStep1Activity.this, RuralOldAgedStep1Activity.this, "Bearer " + token, User_Id, COllegeID, schemeId, RuralOldAgedStep1Activity.this);


        } else {

            Toast.makeText(RuralOldAgedStep1Activity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void initData() {
        binding.toolbar.tvToolbarTitle.setText("Scheme C Basic Detail");



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

                if (CSPreferences.readString(RuralOldAgedStep1Activity.this, "RuralTakeCareStep1").equalsIgnoreCase("N")) {

                    if (Check_Data(view)) {

                        String genderId = "0";
                        if (userGender.equalsIgnoreCase("Male")) {

                            genderId = "1";

                        } else if (userGender.equalsIgnoreCase("Female")) {

                            genderId = "2";
                        } else {

                            genderId = "3";
                        }



/* String VillageName,
                                         String Block,
                                         String DistrictId,
                                         String VillagePopulation,
                                         String VillageMainProblem,
                                         String Above60YearsPersons,
*/


                        if (GlobalClass.isNetworkConnected(RuralOldAgedStep1Activity.this)) {

                            WebAPiCall webapiCall = new WebAPiCall();
                            webapiCall.RuralTakecareStep1Method(RuralOldAgedStep1Activity.this, RuralOldAgedStep1Activity.this,

                                    "Bearer " + token, User_Id, NodalOfficreId,


                                    binding.edtvillage.getText().toString().trim(),
                                    binding.edtblock.getText().toString().trim(),
                                    userDistricID,
                                    binding.edtpopulation.getText().toString().trim(),
                                    binding.edtprobleminvillage.getText().toString().trim(),
                                    binding.edt60yearaged.getText().toString().trim(),
                                    "N/A", "N/A", "N/A",


                                    binding.edtName.getText().toString().trim(),
                                    binding.edcontactno.getText().toString().trim(),
                                    binding.edtage.getText().toString().trim(),
                                    genderId,
                                    binding.edthealthStatus.getText().toString().trim()


                            );


                        } else {

                            Toast.makeText(RuralOldAgedStep1Activity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                        }


                    }


                } else {


                    if (Check_Data_AfterFormFilledUp(view)) {

                        Intent i = new Intent(RuralOldAgedStep1Activity.this, RuralOldAgedDailyActivitiesActivity.class);
                        startActivity(i);

                    }

                }


            }
        });


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
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


        int id = adapterView.getId();
        if (id == R.id.spnstudentDistric) {

            if (position != 0) {

                spnDistricCurrentPosition = position;

                userCollegeDistrict = allDistrict.get(position).getCollegeDistrict();
                userDistricID = String.valueOf(allDistrict.get(position).getDistrictId());


                //Toast.makeText(getApplicationContext(), courseYear.get(position).getName(), Toast.LENGTH_LONG).show();

            } else {
                spnDistricCurrentPosition = position;

            }

        } else if (id == R.id.spnGender) {

            if (position != 0) {

                spnGenderCurrentPosition = position;

                userGender = genderlist.get(position).getName();
                Toast.makeText(getApplicationContext(), genderlist.get(position).getName(), Toast.LENGTH_LONG).show();

            } else {
                spnGenderCurrentPosition = position;

            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public boolean Check_Data(View view) {
        if (TextUtils.isEmpty(binding.edtnodalOfficer.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Nodal Officer Missing!", "Please contact to your college to know your Nodal officer detail.");

            return false;
        } else if (TextUtils.isEmpty(binding.edtnodalOfficermobile.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Nodal Officer Mobile Number Missing!", "Please contact to your college to know your Nodal officer detail.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtvillage.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Village Name Missing!", "Please type your Village Name.");

            return false;
        } else if (TextUtils.isEmpty(binding.edtblock.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Block Name Missing!", "Please type your Block Name.");

            return false;

        } else if (spnDistricCurrentPosition == 0) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "District name Missing!", "Please Select your District.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtpopulation.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Village population Missing!", "Please Type your village population.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtprobleminvillage.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "village problem  Missing!", "Please Type your village problem.");

            return false;

        } else if (TextUtils.isEmpty(binding.edt60yearaged.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Persons with more than 60 year age(Male/Female) Missing!", "Please Type total number of Persons with more than 60 year age(Male/Female).");

            return false;

        } else if (TextUtils.isEmpty(binding.edtName.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Adopted person Name Missing!", "Please Type Adopted person Name.");

            return false;

        } else if (TextUtils.isEmpty(binding.edcontactno.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Adopted person Contact number. Missing!", "Please Type Adopted person Contact Number.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtage.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Adopted person Age. Missing!", "Please Type Adopted person Age.");

            return false;

        } else if (spnGenderCurrentPosition == 0) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Adopted person Gender. Missing!", "Please Type Adopted person Gender.");

            return false;

        } else if (TextUtils.isEmpty(binding.edthealthStatus.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Adopted person Health Status. Missing!", "Please Type Adopted person Health Status.");

            return false;

        } /*else if (imagefile == null) {
            // myLoaders.showSnackBar(view, "Please Enter Password");
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Image Missing!", "Please take a picture of your Activities.");
            return false;
        } else if (binding.lat.getText().toString().trim().isEmpty() && binding.longitude.getText().toString().trim().isEmpty()) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Location of your Activity is  Missing!", "Please make sure gps or location is Enable / ON of phone to get plant location.");

            return false;

        }*/
        return true;
    }


    public boolean Check_Data_AfterFormFilledUp(View view) {
        if (TextUtils.isEmpty(binding.edtnodalOfficer.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Nodal Officer Missing!", "Please contact to your college to know your Nodal officer detail.");

            return false;
        } else if (TextUtils.isEmpty(binding.edtnodalOfficermobile.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Nodal Officer Mobile Number Missing!", "Please contact to your college to know your Nodal officer detail.");

            return false;

        }/*else if (TextUtils.isEmpty(binding.edtvillage.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Village Name Missing!", "Please type your Village Name.");

            return false;
        } else if (TextUtils.isEmpty(binding.edtblock.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Block Name Missing!", "Please type your Block Name.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtdistrict.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "District name Missing!", "Please Type your District.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtpopulation.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Village population Missing!", "Please Type your village population.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtprobleminvillage.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "village problem  Missing!", "Please Type your village problem.");

            return false;

        } else if (TextUtils.isEmpty(binding.edt60yearaged.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Persons with more than 60 year age(Male/Female) Missing!", "Please Type total number of Persons with more than 60 year age(Male/Female).");

            return false;

        } else if (TextUtils.isEmpty(binding.edtName.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Adopted person Name Missing!", "Please Type Adopted person Name.");

            return false;

        } else if (TextUtils.isEmpty(binding.edcontactno.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Adopted person Contact number. Missing!", "Please Type Adopted person Contact Number.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtage.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Adopted person Age. Missing!", "Please Type Adopted person Age.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtgender.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Adopted person Gender. Missing!", "Please Type Adopted person Gender.");

            return false;

        } else if (TextUtils.isEmpty(binding.edthealthStatus.getText().toString().trim())) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Adopted person Health Status. Missing!", "Please Type Adopted person Health Status.");

            return false;

        } else if (imagefile == null) {
            // myLoaders.showSnackBar(view, "Please Enter Password");
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Image Missing!", "Please take a picture of your Activities.");
            return false;
        } else if (binding.lat.getText().toString().trim().isEmpty() && binding.longitude.getText().toString().trim().isEmpty()) {
            GlobalClass.dailogError(RuralOldAgedStep1Activity.this, "Location of your Activity is  Missing!", "Please make sure gps or location is Enable / ON of phone to get plant location.");

            return false;

        }*/
        return true;
    }

    @Override
    public void GetAllNodelOfficerCollegeData(List<NodalofficerOfCollegeResponse.ResponeDatum> list) {

        binding.edtnodalOfficer.setText(list.get(0).getFirstName());
        binding.edtnodalOfficermobile.setText(String.valueOf(list.get(0).getMobile()));
        binding.edtnodalOfficeremail.setText(list.get(0).getEmail());
        CSPreferences.putString(RuralOldAgedStep1Activity.this, "RuraltakecarenodalOfficerId", list.get(0).getId());
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
            binding.edtvillage.setEnabled(true);
            binding.edtblock.setEnabled(true);
            binding.edtpopulation.setEnabled(true);
            binding.edtprobleminvillage.setEnabled(true);
            binding.edt60yearaged.setEnabled(true);

            binding.tldistrict.setVisibility(View.GONE);

            binding.lldistrict.setVisibility(View.VISIBLE);
            binding.tlgender.setVisibility(View.GONE);
            binding.llgender.setVisibility(View.VISIBLE);


        } else {

            try {

                binding.lldistrict.setVisibility(View.GONE);
                binding.tldistrict.setVisibility(View.VISIBLE);

                binding.llgender.setVisibility(View.GONE);
                binding.tlgender.setVisibility(View.VISIBLE);

                binding.edtName.setEnabled(false);
                binding.edcontactno.setEnabled(false);
                binding.edtage.setEnabled(false);
                binding.edthealthStatus.setEnabled(false);
                binding.edtoldandorphanname.setEnabled(false);
                binding.edtoldandorphannaddress.setEnabled(false);
                binding.edtvillage.setEnabled(false);
                binding.edtblock.setEnabled(false);
                binding.edtpopulation.setEnabled(false);
                binding.edtprobleminvillage.setEnabled(false);
                binding.edt60yearaged.setEnabled(false);

                binding.edtdistrict.setEnabled(false);
                binding.edtgender.setEnabled(false);


                binding.edtdistrict.setText(list.get(0).getDistrictId());
                binding.edtgender.setText(list.get(0).getGender());
                binding.edtName.setText(list.get(0).getAdoptedPerson());
                binding.edcontactno.setText(list.get(0).getMobile());
                binding.edtage.setText(list.get(0).getAge());
                binding.edthealthStatus.setText(list.get(0).getHealthStatus());
                binding.edtoldandorphanname.setText(list.get(0).getOldAgeOrphanName());
                binding.edtoldandorphannaddress.setText(list.get(0).getOldAGeOrphanAddress());
                binding.edtoldandorphanninchargeName.setText(list.get(0).getOldAgeOrphanInchargeName());


                binding.edtvillage.setText(list.get(0).getVillageName());
                binding.edtblock.setText(list.get(0).getBlock());
                binding.edtpopulation.setText(list.get(0).getVillagePopulation());
                binding.edtprobleminvillage.setText(list.get(0).getVillageMainProblem());
                binding.edt60yearaged.setText(list.get(0).getAbove60YearsPersons());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}