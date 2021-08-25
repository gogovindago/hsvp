package hsvp.survey.hry.pkl.ui.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.adapter.StudentVerificationListAdapter;
import hsvp.survey.hry.pkl.allinterface.GetAllVerificationStudentListData_interface;
import hsvp.survey.hry.pkl.allinterface.StudentVerificationListData_interface;
import hsvp.survey.hry.pkl.apicall.WebAPiCall;
import hsvp.survey.hry.pkl.databinding.ActivityStudentVerificationListBinding;
import hsvp.survey.hry.pkl.models.VerificationStudentListResponse;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.CSPreferences;
import hsvp.survey.hry.pkl.utility.GlobalClass;

public class StudentVerificationListActivity extends BaseActivity implements GetAllVerificationStudentListData_interface, StudentVerificationListAdapter.ItemListener, StudentVerificationListData_interface {

    ActivityStudentVerificationListBinding binding;
    GridLayoutManager manager;
    String COllegeID, token, User_Id, title, schemeId, NodalOfficreId, userGender;

    private List<VerificationStudentListResponse.Datum> arrayList = new ArrayList<VerificationStudentListResponse.Datum>();

    StudentVerificationListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_student_verification_list);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_student_verification_list);


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {


                if (GlobalClass.isNetworkConnected(StudentVerificationListActivity.this)) {
                    WebAPiCall aPiCall = new WebAPiCall();
                    aPiCall.allverificationStudentMethod(StudentVerificationListActivity.this, "Bearer " + token, schemeId, COllegeID, binding.simpleSwipeRefreshLayout, StudentVerificationListActivity.this);

                } else {
                    Toast.makeText(StudentVerificationListActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

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

        binding.toolbar.tvToolbarTitle.setText("Verification Student List");

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


        if (GlobalClass.isNetworkConnected(StudentVerificationListActivity.this)) {
            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.allverificationStudentMethod(StudentVerificationListActivity.this, "Bearer " + token, schemeId, COllegeID, binding.simpleSwipeRefreshLayout, StudentVerificationListActivity.this);

        } else {
            Toast.makeText(StudentVerificationListActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

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
    public void GetAllVerificationStudentListData(List<VerificationStudentListResponse.Datum> list) {


        if (list == null) {
            binding.txtnodata.setVisibility(View.VISIBLE);
            binding.recyclerView.setVisibility(View.GONE);


        } else {

            binding.txtnodata.setVisibility(View.GONE);
            binding.recyclerView.setVisibility(View.VISIBLE);

            arrayList.clear();
            arrayList.addAll(list);
            adapter = new StudentVerificationListAdapter(this, (ArrayList) arrayList, schemeId, title, this);
            binding.recyclerView.setAdapter(adapter);
            LinearLayoutManager manager = new LinearLayoutManager(this, GridLayoutManager.VERTICAL, false);
            binding.recyclerView.setLayoutManager(manager);
        }


    }

    @Override
    public void onItemClick(VerificationStudentListResponse.Datum item, int currposition, String remarks, String actionStatus) {

        if (actionStatus.equalsIgnoreCase("Approved")) {


            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // set the custom layout
            final View dialogView = this.getLayoutInflater().inflate(R.layout.customviewalertcancel, null);
            builder.setView(dialogView);


            Button btnalertcancel = dialogView.findViewById(R.id.btnalertcancel);
            Button buttonOk = dialogView.findViewById(R.id.btnalertOk);
            TextView txtsuccess = dialogView.findViewById(R.id.txtalertTitle);
            TextView txtmsg = dialogView.findViewById(R.id.txtalertmsg);
            txtsuccess.setText("Student APPROVAL" + "!");
            txtmsg.setText("Are you sure want to APPROVE Student?");

            btnalertcancel.setText("NO");
            buttonOk.setText("YES");

            builder.setView(dialogView);
            final AlertDialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);


            btnalertcancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();


                }
            });

            buttonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();

                    if (GlobalClass.isNetworkConnected(StudentVerificationListActivity.this)) {
                        WebAPiCall aPiCall = new WebAPiCall();
                        aPiCall.verificationStudentMethod(StudentVerificationListActivity.this, StudentVerificationListActivity.this, "Bearer " + token, item.getId(), User_Id, binding.simpleSwipeRefreshLayout,remarks, actionStatus, StudentVerificationListActivity.this);

                    } else {
                        Toast.makeText(StudentVerificationListActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

                    }
                }
            });

            alertDialog.show();

























/*


            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(StudentVerificationListActivity.this);
            sweetAlertDialog.setTitle("Student APPROVAL" + "!");
            sweetAlertDialog.setContentText("Are you sure want to APPROVE Student?");
            sweetAlertDialog.setVolumeControlStream(2);
            sweetAlertDialog.getAlerType();
            sweetAlertDialog.changeAlertType(3);
            sweetAlertDialog.setCancelable(true);
            sweetAlertDialog.setCancelText("NO");
            sweetAlertDialog.setConfirmText("YES");

            sweetAlertDialog.setCanceledOnTouchOutside(false);
            sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                }
            });
            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();


                    if (GlobalClass.isNetworkConnected(StudentVerificationListActivity.this)) {
                        WebAPiCall aPiCall = new WebAPiCall();
                        aPiCall.verificationStudentMethod(StudentVerificationListActivity.this, StudentVerificationListActivity.this, "Bearer " + token, item.getId(), User_Id, binding.simpleSwipeRefreshLayout,remarks, actionStatus, StudentVerificationListActivity.this);

                    } else {
                        Toast.makeText(StudentVerificationListActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

                    }


                }
            });
            sweetAlertDialog.show();
*/


        } else if (actionStatus.equalsIgnoreCase("Rejected")) {


            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // set the custom layout
            final View dialogView = this.getLayoutInflater().inflate(R.layout.customviewalertcancel, null);
            builder.setView(dialogView);


            Button btnalertcancel = dialogView.findViewById(R.id.btnalertcancel);
            Button buttonOk = dialogView.findViewById(R.id.btnalertOk);
            TextView txtsuccess = dialogView.findViewById(R.id.txtalertTitle);
            TextView txtmsg = dialogView.findViewById(R.id.txtalertmsg);
            txtsuccess.setText("Student Rejection" + "!");
            txtmsg.setText("Are you sure want to REJECT Student?");

            btnalertcancel.setText("NO");
            buttonOk.setText("YES");

            builder.setView(dialogView);
            final AlertDialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);


            btnalertcancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();


                }
            });

            buttonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();

                    if (GlobalClass.isNetworkConnected(StudentVerificationListActivity.this)) {
                        WebAPiCall aPiCall = new WebAPiCall();
                        aPiCall.verificationStudentMethod(StudentVerificationListActivity.this, StudentVerificationListActivity.this, "Bearer " + token, item.getId(), User_Id, binding.simpleSwipeRefreshLayout,remarks, actionStatus, StudentVerificationListActivity.this);

                    } else {
                        Toast.makeText(StudentVerificationListActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

                    }

                }
            });

            alertDialog.show();










/*



            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(StudentVerificationListActivity.this);
            sweetAlertDialog.setTitle("Student Rejection" + "!");
            sweetAlertDialog.setContentText("Are you sure want to REJECT Student?");
            sweetAlertDialog.setVolumeControlStream(2);
            sweetAlertDialog.getAlerType();
            sweetAlertDialog.changeAlertType(3);
            sweetAlertDialog.setCancelable(true);
            sweetAlertDialog.setCancelText("NO");
            sweetAlertDialog.setConfirmText("YES");

            sweetAlertDialog.setCanceledOnTouchOutside(false);
            sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                }
            });
            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();


                    if (GlobalClass.isNetworkConnected(StudentVerificationListActivity.this)) {
                        WebAPiCall aPiCall = new WebAPiCall();
                        aPiCall.verificationStudentMethod(StudentVerificationListActivity.this, StudentVerificationListActivity.this, "Bearer " + token, item.getId(), User_Id, binding.simpleSwipeRefreshLayout,remarks, actionStatus, StudentVerificationListActivity.this);

                    } else {
                        Toast.makeText(StudentVerificationListActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

                    }


                }
            });
            sweetAlertDialog.show();
*/


        }


    }

    @Override
    public void VerifiedStudentResponsedata(int data) {

        if (data == 200) {

            if (GlobalClass.isNetworkConnected(StudentVerificationListActivity.this)) {
                WebAPiCall aPiCall = new WebAPiCall();
                aPiCall.allverificationStudentMethod(StudentVerificationListActivity.this, "Bearer " + token, schemeId, COllegeID, binding.simpleSwipeRefreshLayout, StudentVerificationListActivity.this);

            } else {
                Toast.makeText(StudentVerificationListActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

            }

        }

    }
}