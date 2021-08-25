package hsvp.survey.hry.pkl.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.allinterface.GetAdminAllData_interface;
import hsvp.survey.hry.pkl.apicall.WebAPiCall;
import hsvp.survey.hry.pkl.databinding.ActivityAdminDashboardBinding;
import hsvp.survey.hry.pkl.models.AdminDataResponse;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.CSPreferences;
import hsvp.survey.hry.pkl.utility.GlobalClass;

public class adminDashboardActivity extends BaseActivity implements GetAdminAllData_interface {

    ActivityAdminDashboardBinding binding;
    String COllegeID, token, User_Id, title, schemeId, NodalOfficreId, userGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_admin_dashboard);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_dashboard);


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {


                if (GlobalClass.isNetworkConnected(adminDashboardActivity.this)) {
                    WebAPiCall aPiCall = new WebAPiCall();
                    aPiCall.admindata_listMethod(adminDashboardActivity.this, "Bearer " + token, binding.simpleSwipeRefreshLayout, adminDashboardActivity.this);

                } else {
                    Toast.makeText(adminDashboardActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

                }


            }

        });


    }

    @Override
    public void initData() {
        binding.toolbar.tvToolbarTitle.setText("Admin Dashboard");
        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                schemeId = extras.getString("schemeId");
                title = extras.getString("title");

            }

            User_Id = CSPreferences.readString(this, "userId");
            token = CSPreferences.readString(this, "token");
            COllegeID = CSPreferences.readString(this, "CollegeId");

        } catch (Exception e) {

            e.printStackTrace();
        }


        if (GlobalClass.isNetworkConnected(adminDashboardActivity.this)) {
            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.admindata_listMethod(adminDashboardActivity.this, "Bearer " + token, binding.simpleSwipeRefreshLayout, adminDashboardActivity.this);

        } else {
            Toast.makeText(adminDashboardActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

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


    }

    @Override
    public void GetAdminAllData(List<AdminDataResponse.ResponeDatum> list) {

        binding.edttotalStudent.setText(list.get(0).getTotalStudent());

        binding.edttotaltakeCareStudnet.setText(list.get(0).getTakeCareStudnet());
        binding.edttotaladoptionLibraryStudent.setText(list.get(0).getAdoptionLibraryStudent());
        binding.edttotaltakeCareRuralStudent.setText(list.get(0).getTakeCareRuralStudent());


        binding.edttotaltakeCareTotalActivity.setText(list.get(0).getTakeCareTotalActivity());
        binding.edttotaladoptionLibraryTotalActivity.setText(list.get(0).getAdoptionLibraryTotalActivity());
        binding.edttotaltakeCareRuralTotalActivity.setText(list.get(0).getTakeCareRuralTotalActivity());


        binding.edttotalunveryfied.setText(list.get(0).getUnverified());
        binding.edttotalveryfied.setText(list.get(0).getVerified());


    }
}