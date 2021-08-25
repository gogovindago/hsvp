package hsvp.survey.hry.pkl.ui.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.adapter.StudentDailyActivityAdapter;
import hsvp.survey.hry.pkl.allinterface.GetAllStudentDailyActivityForNodalOfficerData_interface;
import hsvp.survey.hry.pkl.apicall.WebAPiCall;


import hsvp.survey.hry.pkl.databinding.ActivityStudentDailyActivityListBinding;
import hsvp.survey.hry.pkl.models.StudentDailActivityForNodalofficerResponse;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.CSPreferences;
import hsvp.survey.hry.pkl.utility.GlobalClass;

public class StudentDailyActivityListActivity extends BaseActivity implements StudentDailyActivityAdapter.ItemListener, GetAllStudentDailyActivityForNodalOfficerData_interface {
    ActivityStudentDailyActivityListBinding binding;

    GridLayoutManager manager;
    String COllegeID, token, User_Id, schemeId, studentId,StudentName, NodalOfficreId, userGender;
    StudentDailyActivityAdapter studentRecordAdapter;
    private List<StudentDailActivityForNodalofficerResponse.Datum> arrayList = new ArrayList<StudentDailActivityForNodalofficerResponse.Datum>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_student_daily_activity_list);


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(StudentDailyActivityListActivity.this)) {
                    WebAPiCall aPiCall = new WebAPiCall();
                    aPiCall.allStudentDailyActivityForNodalMethod(StudentDailyActivityListActivity.this, "Bearer " + token, studentId, schemeId,binding.simpleSwipeRefreshLayout, StudentDailyActivityListActivity.this);

                } else {
                    Toast.makeText(StudentDailyActivityListActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

                }


            }

        });

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        binding.serachView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        binding.serachView.setMaxWidth(Integer.MAX_VALUE);


        // listening to search query text change
        binding.serachView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                studentRecordAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                studentRecordAdapter.getFilter().filter(query);
                return false;
            }


        });


    }


    @Override
    public void initData() {
        binding.simpleSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        binding.toolbar.tvToolbarTitle.setText("Student Daily Activity List");

        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                schemeId = extras.getString("schemeId");
                studentId = extras.getString("studentId");

            }

            User_Id = CSPreferences.readString(this, "userId");
            token = CSPreferences.readString(this, "token");
            COllegeID = CSPreferences.readString(this, "CollegeId");
            StudentName = CSPreferences.readString(this, "StudentName");

        } catch (Exception e) {

            e.printStackTrace();
        }


        if (GlobalClass.isNetworkConnected(StudentDailyActivityListActivity.this)) {
            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.allStudentDailyActivityForNodalMethod(StudentDailyActivityListActivity.this, "Bearer " + token, studentId, schemeId, binding.simpleSwipeRefreshLayout, StudentDailyActivityListActivity.this);

        } else {
            Toast.makeText(StudentDailyActivityListActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

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
    public void GetAllData(List<StudentDailActivityForNodalofficerResponse.Datum> list) {


        if (list == null) {
            binding.txtnodata.setVisibility(View.VISIBLE);
            binding.recyclerView.setVisibility(View.GONE);


        } else {

            binding.txtnodata.setVisibility(View.GONE);
            binding.recyclerView.setVisibility(View.VISIBLE);

            arrayList.clear();
            arrayList.addAll(list);
            studentRecordAdapter = new StudentDailyActivityAdapter(this, (ArrayList) arrayList, StudentName,this);
            binding.recyclerView.setAdapter(studentRecordAdapter);
            LinearLayoutManager manager = new LinearLayoutManager(this, GridLayoutManager.VERTICAL, false);
            binding.recyclerView.setLayoutManager(manager);
        }

    }

    @Override
    public void onItemClick(StudentDailActivityForNodalofficerResponse.Datum item, int currposition) {

    }
}