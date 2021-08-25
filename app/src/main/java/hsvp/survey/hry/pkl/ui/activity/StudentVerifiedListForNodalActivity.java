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
import hsvp.survey.hry.pkl.adapter.StudentVerifiedListAdapter;
import hsvp.survey.hry.pkl.allinterface.GetAllVerifiedStudentListData_interface;
import hsvp.survey.hry.pkl.apicall.WebAPiCall;
import hsvp.survey.hry.pkl.databinding.ActivityStudentVerifiedListForNodalBinding;
import hsvp.survey.hry.pkl.models.VerifiedStudentDataResponse;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.CSPreferences;
import hsvp.survey.hry.pkl.utility.GlobalClass;

public class StudentVerifiedListForNodalActivity extends BaseActivity implements StudentVerifiedListAdapter.ItemListener, GetAllVerifiedStudentListData_interface {

    ActivityStudentVerifiedListForNodalBinding binding;
    GridLayoutManager manager;
    String COllegeID, token, User_Id, title, schemeId, NodalOfficreId, userGender;

    private List<VerifiedStudentDataResponse.Datum> arrayList = new ArrayList<VerifiedStudentDataResponse.Datum>();

    StudentVerifiedListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_student_verification_list);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_student_verified_list_for_nodal);


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {


                if (GlobalClass.isNetworkConnected(StudentVerifiedListForNodalActivity.this)) {
                    WebAPiCall aPiCall = new WebAPiCall();
                    aPiCall.allverifiedStudentMethod(StudentVerifiedListForNodalActivity.this, "Bearer " + token, schemeId, COllegeID, binding.simpleSwipeRefreshLayout, StudentVerifiedListForNodalActivity.this);

                } else {
                    Toast.makeText(StudentVerifiedListForNodalActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

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
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapter.getFilter().filter(query);
                return false;
            }


        });


    }


    @Override
    public void initData() {

        binding.simpleSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        binding.toolbar.tvToolbarTitle.setText(" Verified Student List");

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


        if (GlobalClass.isNetworkConnected(StudentVerifiedListForNodalActivity.this)) {
            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.allverifiedStudentMethod(StudentVerifiedListForNodalActivity.this, "Bearer " + token, schemeId, COllegeID, binding.simpleSwipeRefreshLayout, StudentVerifiedListForNodalActivity.this);

        } else {
            Toast.makeText(StudentVerifiedListForNodalActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

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
    public void GetAllVerifiedStudentListData(List<VerifiedStudentDataResponse.Datum> list) {


        if (list == null) {
            binding.txtnodata.setVisibility(View.VISIBLE);
            binding.recyclerView.setVisibility(View.GONE);


        } else {

            binding.txtnodata.setVisibility(View.GONE);
            binding.recyclerView.setVisibility(View.VISIBLE);

            arrayList.clear();
            arrayList.addAll(list);
            adapter = new StudentVerifiedListAdapter(this, (ArrayList) arrayList, schemeId, title, this);
            binding.recyclerView.setAdapter(adapter);
            LinearLayoutManager manager = new LinearLayoutManager(this, GridLayoutManager.VERTICAL, false);
            binding.recyclerView.setLayoutManager(manager);
        }


    }

    @Override
    public void onItemClick(VerifiedStudentDataResponse.Datum item, int currposition) {



    }

  /*  @Override
    public void onItemClick(VerificationStudentListResponse.Datum item, int currposition) {

        if (GlobalClass.isNetworkConnected(StudentVerifiedListForNodalActivity.this)) {
            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.verificationStudentMethod(StudentVerifiedListForNodalActivity.this, StudentVerifiedListForNodalActivity.this, "Bearer " + token, item.getId(), User_Id, binding.simpleSwipeRefreshLayout);

        } else {
            Toast.makeText(StudentVerifiedListForNodalActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

        }


    }
*/

}