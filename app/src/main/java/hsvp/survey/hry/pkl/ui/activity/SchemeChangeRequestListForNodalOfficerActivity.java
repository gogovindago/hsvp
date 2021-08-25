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
import hsvp.survey.hry.pkl.adapter.StudentSchemeChangeListAdapter;
import hsvp.survey.hry.pkl.allinterface.GetChangeSchemeRequsetListData_interface;
import hsvp.survey.hry.pkl.allinterface.ProcessSchemeChangeData_interface;
import hsvp.survey.hry.pkl.apicall.WebAPiCall;
import hsvp.survey.hry.pkl.databinding.ActivitySchemechangerequestfornodalofficerListBinding;
import hsvp.survey.hry.pkl.models.ChangeSchemeByNodalRequest;
import hsvp.survey.hry.pkl.models.ChangeSchemeResquestListResponse;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.CSPreferences;
import hsvp.survey.hry.pkl.utility.GlobalClass;

public class SchemeChangeRequestListForNodalOfficerActivity extends BaseActivity implements StudentSchemeChangeListAdapter.ItemListener, GetChangeSchemeRequsetListData_interface, ProcessSchemeChangeData_interface {
    String nodalOfficerAction;
    ActivitySchemechangerequestfornodalofficerListBinding binding;
    GridLayoutManager manager;
    String COllegeID, token, User_Id, title, schemeId, NodalOfficreId, userGender, TakeCare,
            AdoptionOfLibrary,
            TakeCareRural;


    private List<ChangeSchemeResquestListResponse.Datum> arrayList = new ArrayList<ChangeSchemeResquestListResponse.Datum>();

    //   StudentVerificationListAdapter adapter;
    StudentSchemeChangeListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_student_verification_list);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_schemechangerequestfornodalofficer_list);


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {


                if (GlobalClass.isNetworkConnected(SchemeChangeRequestListForNodalOfficerActivity.this)) {
                    WebAPiCall aPiCall = new WebAPiCall();
                    aPiCall.allChangeSchemeRequsetListMethod(SchemeChangeRequestListForNodalOfficerActivity.this, "Bearer " + token, COllegeID, binding.simpleSwipeRefreshLayout, SchemeChangeRequestListForNodalOfficerActivity.this);

                } else {
                    Toast.makeText(SchemeChangeRequestListForNodalOfficerActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

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

        binding.toolbar.tvToolbarTitle.setText("Scheme Change Student List");

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


        if (GlobalClass.isNetworkConnected(SchemeChangeRequestListForNodalOfficerActivity.this)) {
            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.allChangeSchemeRequsetListMethod(SchemeChangeRequestListForNodalOfficerActivity.this, "Bearer " + token, COllegeID, binding.simpleSwipeRefreshLayout, SchemeChangeRequestListForNodalOfficerActivity.this);

        } else {
            Toast.makeText(SchemeChangeRequestListForNodalOfficerActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

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
    public void GetChangeSchemeResquestData(List<ChangeSchemeResquestListResponse.Datum> list) {


        if (list == null) {
            binding.txtnodata.setVisibility(View.VISIBLE);
            binding.recyclerView.setVisibility(View.GONE);


        } else {

            binding.txtnodata.setVisibility(View.GONE);
            binding.recyclerView.setVisibility(View.VISIBLE);

            arrayList.clear();
            arrayList.addAll(list);

            TakeCare = String.valueOf(list.get(0).getNewTakeCare());
            AdoptionOfLibrary = String.valueOf(list.get(0).getNewAdoptionLibrary());
            TakeCareRural = String.valueOf(list.get(0).getNewTakeCareRural());

            adapter = new StudentSchemeChangeListAdapter(this, (ArrayList) arrayList, schemeId, title, this);
            binding.recyclerView.setAdapter(adapter);
            LinearLayoutManager manager = new LinearLayoutManager(this, GridLayoutManager.VERTICAL, false);
            binding.recyclerView.setLayoutManager(manager);
        }


    }

    @Override
    public void onItemClick(ChangeSchemeResquestListResponse.Datum item, int currposition, String anyremarks, String Type) {


        if (Type.equalsIgnoreCase("Verify")) {


            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // set the custom layout
            final View dialogView = this.getLayoutInflater().inflate(R.layout.customviewalertcancel, null);
            builder.setView(dialogView);


            Button btnalertcancel = dialogView.findViewById(R.id.btnalertcancel);
            Button buttonOk = dialogView.findViewById(R.id.btnalertOk);
            TextView txtsuccess = dialogView.findViewById(R.id.txtalertTitle);
            TextView txtmsg = dialogView.findViewById(R.id.txtalertmsg);

            txtsuccess.setText("Scheme change APPROVE" + "!");
            txtmsg.setText("Are you sure want to APPROVE scheme change Request?");
            btnalertcancel.setText("NO");
            buttonOk.setText("YES");

            builder.setView(dialogView);
            final AlertDialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);

            buttonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();

                }
            });

            btnalertcancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();

                    if (GlobalClass.isNetworkConnected(SchemeChangeRequestListForNodalOfficerActivity.this)) {

                        ChangeSchemeByNodalRequest resquest = new ChangeSchemeByNodalRequest();

                        resquest.setStudentId(item.getStudentId());
                        resquest.setNodalOfficerId(User_Id);
                        resquest.setNodalOfficerRemarks(anyremarks);
                        resquest.setAction("Approved");
                        nodalOfficerAction="1";
                        resquest.setTakeCare(TakeCare);
                        resquest.setAdoptionOfLibrary(AdoptionOfLibrary);
                        resquest.setTakeCareRural(TakeCareRural);


                        WebAPiCall aPiCall = new WebAPiCall();
                        aPiCall.ProcessSchemeChangeRequestMethod(SchemeChangeRequestListForNodalOfficerActivity.this, SchemeChangeRequestListForNodalOfficerActivity.this, "Bearer " + token, resquest, SchemeChangeRequestListForNodalOfficerActivity.this,nodalOfficerAction);

                    } else {
                        Toast.makeText(SchemeChangeRequestListForNodalOfficerActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

                    }


                }
            });
            alertDialog.show();





















/*


            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(SchemeChangeRequestListForNodalOfficerActivity.this);
            sweetAlertDialog.setTitle("Scheme change APPROVE" + "!");
            sweetAlertDialog.setContentText("Are you sure want to APPROVE scheme change Request?");
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


                    if (GlobalClass.isNetworkConnected(SchemeChangeRequestListForNodalOfficerActivity.this)) {

                        ChangeSchemeByNodalRequest resquest = new ChangeSchemeByNodalRequest();

                        resquest.setStudentId(item.getStudentId());
                        resquest.setNodalOfficerId(User_Id);
                        resquest.setNodalOfficerRemarks(anyremarks);
                        resquest.setAction("Approved");
                        nodalOfficerAction="1";
                        resquest.setTakeCare(TakeCare);
                        resquest.setAdoptionOfLibrary(AdoptionOfLibrary);
                        resquest.setTakeCareRural(TakeCareRural);


                        WebAPiCall aPiCall = new WebAPiCall();
                        aPiCall.ProcessSchemeChangeRequestMethod(SchemeChangeRequestListForNodalOfficerActivity.this, SchemeChangeRequestListForNodalOfficerActivity.this, "Bearer " + token, resquest, SchemeChangeRequestListForNodalOfficerActivity.this,nodalOfficerAction);

                    } else {
                        Toast.makeText(SchemeChangeRequestListForNodalOfficerActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

                    }


                }
            });
            sweetAlertDialog.show();


*/







        } else if (Type.equalsIgnoreCase("Reject")) {



            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // set the custom layout
            final View dialogView = this.getLayoutInflater().inflate(R.layout.customviewalertcancel, null);
            builder.setView(dialogView);


            Button btnalertcancel = dialogView.findViewById(R.id.btnalertcancel);
            Button buttonOk = dialogView.findViewById(R.id.btnalertOk);
            TextView txtsuccess = dialogView.findViewById(R.id.txtalertTitle);
            TextView txtmsg = dialogView.findViewById(R.id.txtalertmsg);
            txtsuccess.setText("Scheme change Request Reject" + "!");
            txtmsg.setText("Are you sure want to REJECT scheme change Request?");

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

                    if (GlobalClass.isNetworkConnected(SchemeChangeRequestListForNodalOfficerActivity.this)) {


                        ChangeSchemeByNodalRequest resquest = new ChangeSchemeByNodalRequest();
                        nodalOfficerAction="2";
                        resquest.setStudentId(item.getStudentId());
                        resquest.setNodalOfficerId(User_Id);
                        resquest.setNodalOfficerRemarks(anyremarks);
                        resquest.setAction("Rejected");

                        resquest.setTakeCare(TakeCare);
                        resquest.setAdoptionOfLibrary(AdoptionOfLibrary);
                        resquest.setTakeCareRural(TakeCareRural);



                        WebAPiCall aPiCall = new WebAPiCall();
                        aPiCall.ProcessSchemeChangeRequestMethod(SchemeChangeRequestListForNodalOfficerActivity.this, SchemeChangeRequestListForNodalOfficerActivity.this, "Bearer " + token, resquest, SchemeChangeRequestListForNodalOfficerActivity.this,nodalOfficerAction);

                    } else {
                        Toast.makeText(SchemeChangeRequestListForNodalOfficerActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

                    }

                }
            });

            alertDialog.show();














/*

            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(SchemeChangeRequestListForNodalOfficerActivity.this);
            sweetAlertDialog.setTitle("Scheme change Request Reject" + "!");
            sweetAlertDialog.setContentText("Are you sure want to REJECT scheme change Request?");
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


                    if (GlobalClass.isNetworkConnected(SchemeChangeRequestListForNodalOfficerActivity.this)) {


                        ChangeSchemeByNodalRequest resquest = new ChangeSchemeByNodalRequest();
                        nodalOfficerAction="2";
                        resquest.setStudentId(item.getStudentId());
                        resquest.setNodalOfficerId(User_Id);
                        resquest.setNodalOfficerRemarks(anyremarks);
                        resquest.setAction("Rejected");

                        resquest.setTakeCare(TakeCare);
                        resquest.setAdoptionOfLibrary(AdoptionOfLibrary);
                        resquest.setTakeCareRural(TakeCareRural);



                        WebAPiCall aPiCall = new WebAPiCall();
                        aPiCall.ProcessSchemeChangeRequestMethod(SchemeChangeRequestListForNodalOfficerActivity.this, SchemeChangeRequestListForNodalOfficerActivity.this, "Bearer " + token, resquest, SchemeChangeRequestListForNodalOfficerActivity.this,nodalOfficerAction);

                    } else {
                        Toast.makeText(SchemeChangeRequestListForNodalOfficerActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

                    }


                }
            });
            sweetAlertDialog.show();
*/





        } else {
        }


    }


    @Override
    public void ProcessSchemeChangedata(int data) {

        if (data == 200) {

            if (GlobalClass.isNetworkConnected(SchemeChangeRequestListForNodalOfficerActivity.this)) {
                WebAPiCall aPiCall = new WebAPiCall();
                aPiCall.allChangeSchemeRequsetListMethod(SchemeChangeRequestListForNodalOfficerActivity.this, "Bearer " + token, COllegeID, binding.simpleSwipeRefreshLayout, SchemeChangeRequestListForNodalOfficerActivity.this);

            } else {
                Toast.makeText(SchemeChangeRequestListForNodalOfficerActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

            }

        }
    }
}