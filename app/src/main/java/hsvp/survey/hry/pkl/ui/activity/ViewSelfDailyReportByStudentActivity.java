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
import hsvp.survey.hry.pkl.adapter.StudentViewDailyActivityByStudentAdapter;
import hsvp.survey.hry.pkl.allinterface.GetAllStudentDailyActivityForNodalOfficerData_interface;
import hsvp.survey.hry.pkl.apicall.WebAPiCall;
import hsvp.survey.hry.pkl.databinding.ActivityViewSelfDailyReportByStudentBinding;
import hsvp.survey.hry.pkl.models.StudentDailActivityForNodalofficerResponse;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.CSPreferences;
import hsvp.survey.hry.pkl.utility.GlobalClass;

public class ViewSelfDailyReportByStudentActivity extends BaseActivity implements StudentViewDailyActivityByStudentAdapter.ItemListener, GetAllStudentDailyActivityForNodalOfficerData_interface {

    ActivityViewSelfDailyReportByStudentBinding binding;
    GridLayoutManager manager;
    String COllegeID, token, User_Id, schemeId, NodalOfficreId, userGender, title;
    private List<StudentDailActivityForNodalofficerResponse.Datum> arrayList = new ArrayList<StudentDailActivityForNodalofficerResponse.Datum>();
    StudentViewDailyActivityByStudentAdapter viewDailyActivityByStudentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_view_self_daily_report_by_student);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_self_daily_report_by_student);

      /*  arrayList = new ArrayList();
        arrayList.add(new OptionDataModel(R.string.profile, R.drawable.profile, 1, "#FFFFFF"));
        arrayList.add(new OptionDataModel(R.string.profile, R.drawable.profile, 1, "#FFFFFF"));
        arrayList.add(new OptionDataModel(R.string.profile, R.drawable.profile, 1, "#FFFFFF"));
        arrayList.add(new OptionDataModel(R.string.profile, R.drawable.profile, 1, "#FFFFFF"));
        arrayList.add(new OptionDataModel(R.string.profile, R.drawable.profile, 1, "#FFFFFF"));
        arrayList.add(new OptionDataModel(R.string.profile, R.drawable.profile, 1, "#FFFFFF"));
        arrayList.add(new OptionDataModel(R.string.profile, R.drawable.profile, 1, "#FFFFFF"));
        arrayList.add(new OptionDataModel(R.string.profile, R.drawable.profile, 1, "#FFFFFF"));
        arrayList.add(new OptionDataModel(R.string.profile, R.drawable.profile, 1, "#FFFFFF"));
        arrayList.add(new OptionDataModel(R.string.profile, R.drawable.profile, 1, "#FFFFFF"));
        arrayList.add(new OptionDataModel(R.string.profile, R.drawable.profile, 1, "#FFFFFF"));


        manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        binding.recyclerView.setLayoutManager(manager);

        StudentViewDailyActivityByStudentAdapter adaptermain = new StudentViewDailyActivityByStudentAdapter(this, arrayList, this);
        binding.recyclerView.setAdapter(adaptermain);
*/


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {


                if (GlobalClass.isNetworkConnected(ViewSelfDailyReportByStudentActivity.this)) {
                    WebAPiCall aPiCall = new WebAPiCall();
                    aPiCall.allStudentDailyActivityForNodalMethod(ViewSelfDailyReportByStudentActivity.this, "Bearer " + token, User_Id, schemeId, binding.simpleSwipeRefreshLayout, ViewSelfDailyReportByStudentActivity.this);

                } else {
                    Toast.makeText(ViewSelfDailyReportByStudentActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

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
                viewDailyActivityByStudentAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                viewDailyActivityByStudentAdapter.getFilter().filter(query);
                return false;
            }


        });

    }

    @Override
    public void initData() {
        binding.toolbar.tvToolbarTitle.setText("My Daily Activity List");


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


        if (GlobalClass.isNetworkConnected(ViewSelfDailyReportByStudentActivity.this)) {
            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.allStudentDailyActivityForNodalMethod(ViewSelfDailyReportByStudentActivity.this, "Bearer " + token, User_Id, schemeId, binding.simpleSwipeRefreshLayout, ViewSelfDailyReportByStudentActivity.this);

        } else {
            Toast.makeText(ViewSelfDailyReportByStudentActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

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
            viewDailyActivityByStudentAdapter = new StudentViewDailyActivityByStudentAdapter(this, (ArrayList) arrayList, title, this);
            binding.recyclerView.setAdapter(viewDailyActivityByStudentAdapter);
            LinearLayoutManager manager = new LinearLayoutManager(this, GridLayoutManager.VERTICAL, false);
            binding.recyclerView.setLayoutManager(manager);
        }


    }

    @Override
    public void onItemClick(StudentDailActivityForNodalofficerResponse.Datum item, int currposition) {

    }
}