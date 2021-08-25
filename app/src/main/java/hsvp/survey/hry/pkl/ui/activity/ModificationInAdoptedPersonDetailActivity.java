package hsvp.survey.hry.pkl.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.databinding.ActivityModificationInAdoptedPersonDetailBinding;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.GlobalClass;

public class ModificationInAdoptedPersonDetailActivity extends BaseActivity {
    ActivityModificationInAdoptedPersonDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_modification_in_adopted_person_detail);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_modification_in_adopted_person_detail);

    }

    @Override
    public void initData() {
        binding.toolbar.tvToolbarTitle.setText("Adopted Person Detail");

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

                    GlobalClass.dailogsuccessWithActivity(ModificationInAdoptedPersonDetailActivity.this, ModificationInAdoptedPersonDetailActivity.this, "Adopted person detail changed Done", "Your Adopted person detail changed successfully.");

//                    Intent i = new Intent(ModificationInAdoptedPersonDetailActivity.this, OldAgeDailyActivitiesActivity.class);
//                    startActivity(i);
//                    finish();
                }
            }
        });


    }


    public boolean Check_Data(View view) {

        /*if (TextUtils.isEmpty(binding.edtnodalOfficer.getText().toString().trim())) {
            GlobalClass.dailogError(ModificationInAdoptedPersonDetailActivity.this, "Nodal Officer Missing!", "Please contact to your college to know your Nodal officer detail.");

            return false;
        } else if (TextUtils.isEmpty(binding.edtnodalOfficermobile.getText().toString().trim())) {
            GlobalClass.dailogError(ModificationInAdoptedPersonDetailActivity.this, "Nodal Officer Mobile Number Missing!", "Please contact to your college to know your Nodal officer detail.");

            return false;

        } *//*else if (TextUtils.isEmpty(binding.edtrequiredment.getText().toString().trim())) {
            GlobalClass.dailogError(ModificationInAdoptedPersonDetailActivity.this, "Requirement of person/orphans Missing!", "Please type solution provided by student.");

            return false;

        }*//* else if (TextUtils.isEmpty(binding.edtoldandorphanname.getText().toString().trim())) {
            GlobalClass.dailogError(ModificationInAdoptedPersonDetailActivity.this, "Name of Old age Or Orphan age Home Missing!", "Please Type Name of Old age Or Orphan age Home.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtoldandorphannaddress.getText().toString().trim())) {
            GlobalClass.dailogError(ModificationInAdoptedPersonDetailActivity.this, "Address of Old age Or Orphan age Home Missing!", "Please Address Name of Old age Or Orphan age Home.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtoldandorphanninchargeName.getText().toString().trim())) {
            GlobalClass.dailogError(ModificationInAdoptedPersonDetailActivity.this, "Name of Incharge Missing!", "Please Type Name of Incharge of Old age Or Orphan age Home.");

            return false;

        } else */if (TextUtils.isEmpty(binding.edtName.getText().toString().trim())) {
            GlobalClass.dailogError(ModificationInAdoptedPersonDetailActivity.this, "Adopted person Name Missing!", "Please Type Adopted person Name.");

            return false;

        } else if (TextUtils.isEmpty(binding.edcontactno.getText().toString().trim())) {
            GlobalClass.dailogError(ModificationInAdoptedPersonDetailActivity.this, "Adopted person Contact number. Missing!", "Please Type Adopted person Contact Number.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtage.getText().toString().trim())) {
            GlobalClass.dailogError(ModificationInAdoptedPersonDetailActivity.this, "Adopted person Age. Missing!", "Please Type Adopted person Age.");

            return false;

        } else if (TextUtils.isEmpty(binding.edtgender.getText().toString().trim())) {
            GlobalClass.dailogError(ModificationInAdoptedPersonDetailActivity.this, "Adopted person Gender. Missing!", "Please Type Adopted person Gender.");

            return false;

        } else if (TextUtils.isEmpty(binding.edthealthStatus.getText().toString().trim())) {
            GlobalClass.dailogError(ModificationInAdoptedPersonDetailActivity.this, "Adopted person Health Status. Missing!", "Please Type Adopted person Health Status.");

            return false;

        } /*else if (imagefile == null) {
            // myLoaders.showSnackBar(view, "Please Enter Password");
            GlobalClass.dailogError(ModificationInAdoptedPersonDetailActivity.this, "Image Missing!", "Please take a picture of your Activities.");
            return false;
        } else if (binding.lat.getText().toString().trim().isEmpty() && binding.longitude.getText().toString().trim().isEmpty()) {
            GlobalClass.dailogError(ModificationInAdoptedPersonDetailActivity.this, "Location of your Activity is  Missing!", "Please make sure gps or location is Enable / ON of phone to get plant location.");

            return false;

        }*/
        return true;
    }
}