package hsvp.survey.hry.pkl.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import java.util.List;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.allinterface.GetAllSchemeForChangeData_interface;
import hsvp.survey.hry.pkl.allinterface.schemeRequestByStudentData_interface;
import hsvp.survey.hry.pkl.apicall.WebAPiCall;
import hsvp.survey.hry.pkl.databinding.ActivitySchemeChangeForStudentBinding;
import hsvp.survey.hry.pkl.models.ChangeSchemeForStudentResponse;
import hsvp.survey.hry.pkl.models.ChangeSchemeResquestByStudent;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.CSPreferences;
import hsvp.survey.hry.pkl.utility.GlobalClass;

public class SchemeChangeForStudentActivity extends BaseActivity implements GetAllSchemeForChangeData_interface, schemeRequestByStudentData_interface {

    ActivitySchemeChangeForStudentBinding binding;
    Boolean chktcareOldAgechange = false, chkAdoptionofLibrarieschange = false, chkruralAreachange = false, schemeselected = false;
    String COllegeID, token, User_Id, title, schemeId, NodalOfficreId, userGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_scheme_change_for_student);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scheme_change_for_student);

    }

    @Override
    public void initData() {
        binding.toolbar.tvToolbarTitle.setText("Scheme Change/Modify");
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


        if (GlobalClass.isNetworkConnected(SchemeChangeForStudentActivity.this)) {
            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.schemechangeForStudentMethod(SchemeChangeForStudentActivity.this, "Bearer " + token, User_Id, COllegeID, SchemeChangeForStudentActivity.this);

        } else {
            Toast.makeText(SchemeChangeForStudentActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

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


        binding.btnschememodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Check_Data(view)) {


                    final AlertDialog.Builder builder = new AlertDialog.Builder(SchemeChangeForStudentActivity.this);
                    // set the custom layout
                    final View dialogView = SchemeChangeForStudentActivity.this.getLayoutInflater().inflate(R.layout.customviewalertcancel, null);
                    builder.setView(dialogView);


                    Button btnalertcancel = dialogView.findViewById(R.id.btnalertcancel);
                    Button buttonOk = dialogView.findViewById(R.id.btnalertOk);
                    TextView txtsuccess = dialogView.findViewById(R.id.txtalertTitle);
                    TextView txtmsg = dialogView.findViewById(R.id.txtalertmsg);

                    btnalertcancel.setText("NO");
                    buttonOk.setText("YES");
                    txtsuccess.setText("Scheme change" + "!");
                    txtmsg.setText("Are you sure want to change/modify your scheme?");

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

                            if (GlobalClass.isNetworkConnected(SchemeChangeForStudentActivity.this)) {

                                ChangeSchemeResquestByStudent resquest = new ChangeSchemeResquestByStudent();

                                resquest.setStudentId(User_Id);
                                resquest.setCollegeId(COllegeID);
                                resquest.setRemarks(binding.edtanynotes.getText().toString().trim());

                                resquest.setTakeCare(chktcareOldAgechange.toString());
                                resquest.setAdoptionOfLibrary(chkAdoptionofLibrarieschange.toString());
                                resquest.setTakeCareRural(chkruralAreachange.toString());

                                WebAPiCall aPiCall = new WebAPiCall();

                                aPiCall.schemeChangeRequestByStudentMethod(SchemeChangeForStudentActivity.this, SchemeChangeForStudentActivity.this, "Bearer " + token, resquest, SchemeChangeForStudentActivity.this);

                            } else {
                                Toast.makeText(SchemeChangeForStudentActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

                            }
                            //GlobalClass.dailogsuccess(SchemeChangeForStudentActivity.this, "Scheme Change Request Done.", "Your Scheme change request has been submitted successfully.");



                        }
                    });
                    alertDialog.show();



/*


                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(SchemeChangeForStudentActivity.this);
                    sweetAlertDialog.setTitle("Scheme change" + "!");
                    sweetAlertDialog.setContentText("Are you sure want to change/modify your scheme?");
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

                            if (GlobalClass.isNetworkConnected(SchemeChangeForStudentActivity.this)) {

                                ChangeSchemeResquestByStudent resquest = new ChangeSchemeResquestByStudent();

                                resquest.setStudentId(User_Id);
                                resquest.setCollegeId(COllegeID);
                                resquest.setRemarks(binding.edtanynotes.getText().toString().trim());

                                resquest.setTakeCare(chktcareOldAgechange.toString());
                                resquest.setAdoptionOfLibrary(chkAdoptionofLibrarieschange.toString());
                                resquest.setTakeCareRural(chkruralAreachange.toString());

                                WebAPiCall aPiCall = new WebAPiCall();

                                aPiCall.schemeChangeRequestByStudentMethod(SchemeChangeForStudentActivity.this, SchemeChangeForStudentActivity.this, "Bearer " + token, resquest, SchemeChangeForStudentActivity.this);

                            } else {
                                Toast.makeText(SchemeChangeForStudentActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

                            }
                            //GlobalClass.dailogsuccess(SchemeChangeForStudentActivity.this, "Scheme Change Request Done.", "Your Scheme change request has been submitted successfully.");


                        }
                    });
                    sweetAlertDialog.show();

                    */










                }
            }
        });


        binding.chktcareOldAgechange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                                    @Override
                                                                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                                        if (isChecked) {
                                                                            chktcareOldAgechange = true;
                                                                            schemeselected = true;


                                                                        } else {
                                                                            chktcareOldAgechange = false;
                                                                            //  schemeselected = false;
                                                                            if (binding.chkruralAreachange.isChecked() || binding.chkAdoptionofLibrarieschange.isChecked()) {

                                                                                schemeselected = true;
                                                                            } else {

                                                                                schemeselected = false;
                                                                            }


                                                                        }

                                                                    }
                                                                }
        );

        binding.chkAdoptionofLibrarieschange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                                            @Override
                                                                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                                                                                if (isChecked) {
                                                                                    chkAdoptionofLibrarieschange = true;
                                                                                    schemeselected = true;


                                                                                } else {
                                                                                    chkAdoptionofLibrarieschange = false;
                                                                                    //  schemeselected = false;
                                                                                    if (binding.chkruralAreachange.isChecked() || binding.chktcareOldAgechange.isChecked()) {

                                                                                        schemeselected = true;
                                                                                    } else {

                                                                                        schemeselected = false;
                                                                                    }


                                                                                }


                                                                            }
                                                                        }
        );

        binding.chkruralAreachange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                                  @Override
                                                                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                                                                      if (isChecked) {

                                                                          chkruralAreachange = true;
                                                                          schemeselected = true;


                                                                      } else {
                                                                          chkruralAreachange = false;

                                                                          if (binding.chkAdoptionofLibrarieschange.isChecked() || binding.chktcareOldAgechange.isChecked()) {

                                                                              schemeselected = true;
                                                                          } else {

                                                                              schemeselected = false;
                                                                          }


                                                                      }

                                                                  }
                                                              }
        );


    }

    public boolean Check_Data(View view) {

        if (schemeselected == false) {
            GlobalClass.dailogError(this, "Missing Scheme type", "Please Select Your Scheme Type.");


            return false;
        }
        return true;
    }


    @Override
    public void GetAllSchemeForChangeData(List<ChangeSchemeForStudentResponse.Datum> list) {

        if (list == null) {
            GlobalClass.dailogError(this, "Scheme fetching Error", "");

        } else {



            /*scheme of college visible*/

            if (list.get(0).isStudentTakecare()) {

                binding.chktcareOldAgeshow.setVisibility(View.VISIBLE);
            }

            if (list.get(0).isStudentAdoptionOfLibrary()) {

                binding.chkAdoptionofLibrariesshow.setVisibility(View.VISIBLE);
            }

            if (list.get(0).isStudentRural()) {
                binding.chkruralAreashow.setVisibility(View.VISIBLE);
            }



            /*scheme  selected by student*/


           // chktcareOldAgechange
                //    chkAdoptionofLibrarieschange
         //   chkruralAreachange

            if (list.get(0).isStudentTakecare()) {

                binding.chktcareOldAgeshow.setChecked(true);
                binding.chktcareOldAgechange.setChecked(true);
            }

            if (list.get(0).isStudentAdoptionOfLibrary()) {

                binding.chkAdoptionofLibrariesshow.setChecked(true);
                binding.chkAdoptionofLibrarieschange.setChecked(true);
            }

            if (list.get(0).isStudentRural()) {
                binding.chkruralAreashow.setChecked(true);
                binding.chkruralAreachange.setChecked(true);
            }


        }


    }


    @Override
    public void alldata(int data) {

    }
}