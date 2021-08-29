package hsvp.survey.hry.pkl.apicall;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.allinterface.GetAdminAllData_interface;
import hsvp.survey.hry.pkl.allinterface.GetAllCollegeData_interface;
import hsvp.survey.hry.pkl.allinterface.GetAllDistricData_interface;
import hsvp.survey.hry.pkl.allinterface.GetAllNodelOfficerCollegeData_interface;
import hsvp.survey.hry.pkl.allinterface.GetAllSchemeForChangeData_interface;
import hsvp.survey.hry.pkl.allinterface.GetAllSectionData_interface;
import hsvp.survey.hry.pkl.allinterface.GetAllStudentDailyActivityForNodalOfficerData_interface;
import hsvp.survey.hry.pkl.allinterface.GetAllVerificationStudentListData_interface;
import hsvp.survey.hry.pkl.allinterface.GetAllVerifiedStudentListData_interface;
import hsvp.survey.hry.pkl.allinterface.GetAllnodalOfficerStudentData_interface;
import hsvp.survey.hry.pkl.allinterface.GetChangeSchemeRequsetListData_interface;
import hsvp.survey.hry.pkl.allinterface.GetCollegeSchemeData_interface;
import hsvp.survey.hry.pkl.allinterface.GetCourseByCollegeIdData_interface;
import hsvp.survey.hry.pkl.allinterface.GetSubjectCombinationData_interface;
import hsvp.survey.hry.pkl.allinterface.GetbannersData_interface;
import hsvp.survey.hry.pkl.allinterface.GetnssGalleryData_interface;
import hsvp.survey.hry.pkl.allinterface.OldAgedLoginData_interface;
import hsvp.survey.hry.pkl.allinterface.OtpVerifyData_interface;
import hsvp.survey.hry.pkl.allinterface.ProcessSchemeChangeData_interface;
import hsvp.survey.hry.pkl.allinterface.ProfileData_interface;
import hsvp.survey.hry.pkl.allinterface.ResetForget_interface;
import hsvp.survey.hry.pkl.allinterface.SignupData_interface;
import hsvp.survey.hry.pkl.allinterface.StudentVerificationListData_interface;
import hsvp.survey.hry.pkl.allinterface.schemeRequestByStudentData_interface;
import hsvp.survey.hry.pkl.models.AdminDataResponse;
import hsvp.survey.hry.pkl.models.AdoptionStep1Response;
import hsvp.survey.hry.pkl.models.AdoptionStep2Response;
import hsvp.survey.hry.pkl.models.ChangeSchemeByNodalRequest;
import hsvp.survey.hry.pkl.models.ChangeSchemeByNodalResponse;
import hsvp.survey.hry.pkl.models.ChangeSchemeForStudentResponse;
import hsvp.survey.hry.pkl.models.ChangeSchemeResquestByStudent;
import hsvp.survey.hry.pkl.models.ChangeSchemeResquestByStudentResponse;
import hsvp.survey.hry.pkl.models.ChangeSchemeResquestListResponse;
import hsvp.survey.hry.pkl.models.CollegeListResponse;
import hsvp.survey.hry.pkl.models.CourseByCollegeIDResponse;
import hsvp.survey.hry.pkl.models.ForgotPasswordRequest;
import hsvp.survey.hry.pkl.models.ForgotPasswordResponse;
import hsvp.survey.hry.pkl.models.GetDistrictResponse;
import hsvp.survey.hry.pkl.models.HomePageResponse;
import hsvp.survey.hry.pkl.models.LoginRequest;
import hsvp.survey.hry.pkl.models.LoginResponse;
import hsvp.survey.hry.pkl.models.NodalofficerOfCollegeResponse;
import hsvp.survey.hry.pkl.models.NssGalleryResponse;
import hsvp.survey.hry.pkl.models.OldagetakecarStep1Response;
import hsvp.survey.hry.pkl.models.OldagetakecarStep2Response;
import hsvp.survey.hry.pkl.models.ProfileDataResponse;
import hsvp.survey.hry.pkl.models.RegistrationRespone;
import hsvp.survey.hry.pkl.models.ResetPasswordRequest;
import hsvp.survey.hry.pkl.models.ResetPasswordResponse;
import hsvp.survey.hry.pkl.models.RuraltakecarStep2Response;
import hsvp.survey.hry.pkl.models.SchemeOfCollegeResponse;
import hsvp.survey.hry.pkl.models.SectionResponse;
import hsvp.survey.hry.pkl.models.SignupRequest;
import hsvp.survey.hry.pkl.models.SignupResponse;
import hsvp.survey.hry.pkl.models.StudentDailActivityForNodalofficerResponse;
import hsvp.survey.hry.pkl.models.StudentListUnderNodalOfficerResponse;
import hsvp.survey.hry.pkl.models.SubjectCombinationResponse;
import hsvp.survey.hry.pkl.models.TakecareRuralStep1Response;
import hsvp.survey.hry.pkl.models.VerificationStudentListResponse;
import hsvp.survey.hry.pkl.models.VerifiedStudentDataResponse;
import hsvp.survey.hry.pkl.models.VerifiedStudentResponse;
import hsvp.survey.hry.pkl.models.VerifyOtpRequest;
import hsvp.survey.hry.pkl.models.VerifyOtpResponse;
import hsvp.survey.hry.pkl.retrofitinterface.ApiClient;
import hsvp.survey.hry.pkl.ui.activity.LoginActivity;
import hsvp.survey.hry.pkl.ui.activity.MainActivity;
import hsvp.survey.hry.pkl.ui.activity.SurveyActivity;
import hsvp.survey.hry.pkl.utility.CSPreferences;
import hsvp.survey.hry.pkl.utility.GlobalClass;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebAPiCall {


   // SweetAlertDialog pDialog;
    ProgressDialog pDialog;


    public void dailogsuccessanddismis(final Context context, final Activity activity, String msgtitle, String msgcontentText) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // set the custom layout
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.customviewsuccess, null);
        builder.setView(dialogView);


        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        TextView txtsuccess = dialogView.findViewById(R.id.txtsuccess);
        TextView txtmsg = dialogView.findViewById(R.id.txtmsg);
        txtsuccess.setText(msgtitle + " ! ");
        txtmsg.setText(msgcontentText);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }
        });
        alertDialog.show();

    }


    public void dailogsuccessWithActivity(final Context context, final Activity activity, String msgtitle, String msgcontentText) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // set the custom layout
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.customviewsuccess, null);
        builder.setView(dialogView);


        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        TextView txtsuccess = dialogView.findViewById(R.id.txtsuccess);
        TextView txtmsg = dialogView.findViewById(R.id.txtmsg);
        txtsuccess.setText(msgtitle + " ! ");
        txtmsg.setText(msgcontentText);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                activity.finish();
            }
        });
        alertDialog.show();

    }

    public void dailogsuccessWithActivityFInishGotoNext(final Context context, final Activity activity, String msgtitle, String msgcontentText) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // set the custom layout
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.customviewsuccess, null);
        builder.setView(dialogView);


        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        TextView txtsuccess = dialogView.findViewById(R.id.txtsuccess);
        TextView txtmsg = dialogView.findViewById(R.id.txtmsg);
        txtsuccess.setText(msgtitle + " ! ");
        txtmsg.setText(msgcontentText);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent i = new Intent(activity, SurveyActivity.class);
                activity.startActivity(i);
                activity.finish();
            }
        });
        alertDialog.show();



      /*  SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog.setTitle(msgtitle + "!");
        sweetAlertDialog.setContentText(msgcontentText);
        sweetAlertDialog.setVolumeControlStream(2);
        sweetAlertDialog.getAlerType();
        sweetAlertDialog.changeAlertType(2);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setCanceledOnTouchOutside(false);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
                Intent i = new Intent(activity, OldAgeDailyActivitiesActivity.class);
                activity.startActivity(i);
                activity.finish();
            }
        });
        sweetAlertDialog.show();*/

    }


    public void dailogsuccessWithActivityFInishGotoNextRural(final Context context, final Activity activity, String msgtitle, String msgcontentText) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // set the custom layout
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.customviewsuccess, null);
        builder.setView(dialogView);


        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        TextView txtsuccess = dialogView.findViewById(R.id.txtsuccess);
        TextView txtmsg = dialogView.findViewById(R.id.txtmsg);
        txtsuccess.setText(msgtitle + " ! ");
        txtmsg.setText(msgcontentText);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent i = new Intent(activity, SurveyActivity.class);
                activity.startActivity(i);
                activity.finish();
            }
        });
        alertDialog.show();



       /* SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog.setTitle(msgtitle + "!");
        sweetAlertDialog.setContentText(msgcontentText);
        sweetAlertDialog.setVolumeControlStream(2);
        sweetAlertDialog.getAlerType();
        sweetAlertDialog.changeAlertType(2);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setCanceledOnTouchOutside(false);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
                Intent i = new Intent(activity, RuralOldAgedDailyActivitiesActivity.class);
                activity.startActivity(i);
                activity.finish();
            }
        });
        sweetAlertDialog.show();*/

    }

    public void dailogsuccessGotoLogin(final Context context, final Activity activity, String msgtitle, String msgcontentText) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // set the custom layout
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.customviewsuccess, null);
        builder.setView(dialogView);


        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        TextView txtsuccess = dialogView.findViewById(R.id.txtsuccess);
        TextView txtmsg = dialogView.findViewById(R.id.txtmsg);
        txtsuccess.setText(msgtitle + " ! ");
        txtmsg.setText(msgcontentText);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent = new Intent(activity, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
            }
        });
        alertDialog.show();




     /*   SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog.setTitle(msgtitle + "!");
        sweetAlertDialog.setContentText(msgcontentText);
        sweetAlertDialog.setVolumeControlStream(2);
        sweetAlertDialog.getAlerType();
        sweetAlertDialog.changeAlertType(2);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setCanceledOnTouchOutside(false);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
                Intent intent = new Intent(activity, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);

            }
        });
        sweetAlertDialog.show();*/

    }

    public void dailogsuccessWithActivityFInishGotoNextadoption(final Context context, final Activity activity, String msgtitle, String msgcontentText) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // set the custom layout
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.customviewsuccess, null);
        builder.setView(dialogView);


        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        TextView txtsuccess = dialogView.findViewById(R.id.txtsuccess);
        TextView txtmsg = dialogView.findViewById(R.id.txtmsg);
        txtsuccess.setText(msgtitle + " ! ");
        txtmsg.setText(msgcontentText);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent i = new Intent(activity, SurveyActivity.class);
                activity.startActivity(i);
                activity.finish();
            }
        });
        alertDialog.show();




       /* SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog.setTitle(msgtitle + "!");
        sweetAlertDialog.setContentText(msgcontentText);
        sweetAlertDialog.setVolumeControlStream(2);
        sweetAlertDialog.getAlerType();
        sweetAlertDialog.changeAlertType(2);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setCanceledOnTouchOutside(false);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
                Intent i = new Intent(activity, AdoptionDailyActivitiesActivity.class);
                activity.startActivity(i);
                activity.finish();
            }
        });
        sweetAlertDialog.show();*/

    }

    public void dailogsuccess(final Activity activity, String msgtitle, String msgcontentText) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // set the custom layout
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.customviewsuccess, null);
        builder.setView(dialogView);


        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        TextView txtsuccess = dialogView.findViewById(R.id.txtsuccess);
        TextView txtmsg = dialogView.findViewById(R.id.txtmsg);
        txtsuccess.setText(msgtitle + " ! ");
        txtmsg.setText(msgcontentText);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }
        });
        alertDialog.show();




      /*  SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog.setTitle(msgtitle + "!");
        sweetAlertDialog.setContentText(msgcontentText);
        sweetAlertDialog.setVolumeControlStream(2);
        sweetAlertDialog.getAlerType();
        sweetAlertDialog.changeAlertType(2);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setCanceledOnTouchOutside(false);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();

            }
        });
        sweetAlertDialog.show();*/

    }

    public void dailogError(final Activity activity, String msgtitle, String msgcontentText) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // set the custom layout
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.customviewerror, null);
        builder.setView(dialogView);


        Button buttonOk = dialogView.findViewById(R.id.btnerrorOk);
        TextView txtsuccess = dialogView.findViewById(R.id.txterrortitle);
        TextView txtmsg = dialogView.findViewById(R.id.txterrormsg);
        txtsuccess.setText(msgtitle + " ! ");
        txtmsg.setText(msgcontentText);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }
        });
        alertDialog.show();







    }


    public void dailogshow(final Context context) {



        pDialog = new ProgressDialog(context);
        pDialog.setMessage("loading...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void loadershowwithMsg(final Context context, String msg) {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage(msg);
        pDialog.setCancelable(false);
        pDialog.show();

//        pd = new ProgressDialog(context);
//        pd.setMessage("loading...");
//        pd.setCancelable(false);
//        pd.show();
    }

    public void dailoghide(final Context context) {
        // pd.dismiss();
        pDialog.dismiss();
    }


    /*
        public void getHomePageDataMethod(final Activity activity, final Context context, String token,String collegeID, LinearLayout llmain, SwipeRefreshLayout mSwipeRefreshLayout, final GetbannersData_interface getbannersData_interface) {

            // loadershowwithMsg(context, "Loading...");
            mSwipeRefreshLayout.setRefreshing(true);

            Call<HomePageResponse> responseCall = ApiClient.getClient().getHomePageDataAPi(token,collegeID);
            responseCall.enqueue(new Callback<HomePageResponse>() {
                @Override
                public void onResponse(Call<HomePageResponse> call, Response<HomePageResponse> response) {
                    // dailoghide(context);
                    if (response.isSuccessful()) {

                        if (response.body().getResponse() == 200) {

                            mSwipeRefreshLayout.setRefreshing(false);

                            llmain.setVisibility(View.VISIBLE);

                            getbannersData_interface.GetbannersData(response.body().getData());
    //                        getbannersData_interface.GetOtherDigitalTrendingLibraryData(response.body().getData().getOtherDigitalTrendingLibraries());
    //                        getbannersData_interface.GetTrendingeBookData(response.body().getData().getTrendingeBooks());
    //                        getbannersData_interface.GetTrendingVideosData(response.body().getData().getTrendingVideos());
    //                        getbannersData_interface.GetTrendingJournalData(response.body().getData().getTrendingJournals());
    //                        getbannersData_interface.GetImportantsLinkData(response.body().getData().getImportantLinks());
    //                        getbannersData_interface.GetUdaanVideosData(response.body().getData().getTrendingUdaanVideos());


                        } else if (response.code() == 401) {
                            CSPreferences.clearPref(context);
                            Intent intent = new Intent(context, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                    Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            GlobalClass.showtost(context, "" + response.message());
                        } else {
                            GlobalClass.showtost(context, "" + response.message());
                        }


                    } else {
                        GlobalClass.showtost(context, "" + response.message());
                    }
                }

                @Override
                public void onFailure(Call<HomePageResponse> call, Throwable t) {
                    mSwipeRefreshLayout.setRefreshing(false);
                    // dailoghide(context);
                    t.printStackTrace();

                    Log.d("dddddd", "onFailure: " + t.getMessage());
                }
            });
        }
    */
   /* public void getHomePageDataMethod(final Activity activity, final Context context, String token, LinearLayout llmain, SwipeRefreshLayout mSwipeRefreshLayout, final GetbannersData_interface getbannersData_interface) {

        // loadershowwithMsg(context, "Loading...");*/

    public void getHomePageDataMethod(final Activity activity, final Context context, String token, String collegeID, LinearLayout llmain, SwipeRefreshLayout mSwipeRefreshLayout, final GetbannersData_interface getbannersData_interface) {

        mSwipeRefreshLayout.setRefreshing(true);

        Call<HomePageResponse> responseCall = ApiClient.getClient().getHomePageDataAPi(token, collegeID);
        responseCall.enqueue(new Callback<HomePageResponse>() {
            @Override
            public void onResponse(Call<HomePageResponse> call, Response<HomePageResponse> response) {
                // dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        mSwipeRefreshLayout.setRefreshing(false);

                        llmain.setVisibility(View.VISIBLE);

                        getbannersData_interface.GetbannersData(response.body().getData().getResponeData());
                        getbannersData_interface.GetadminimageData(response.body().getData().getResponeData1());

//                        getbannersData_interface.GetOtherDigitalTrendingLibraryData(response.body().getData().getOtherDigitalTrendingLibraries());
//                        getbannersData_interface.GetTrendingeBookData(response.body().getData().getTrendingeBooks());
//                        getbannersData_interface.GetTrendingVideosData(response.body().getData().getTrendingVideos());
//                        getbannersData_interface.GetTrendingJournalData(response.body().getData().getTrendingJournals());
//                        getbannersData_interface.GetImportantsLinkData(response.body().getData().getImportantLinks());
//                        getbannersData_interface.GetUdaanVideosData(response.body().getData().getTrendingUdaanVideos());


                    } else {
                        GlobalClass.showtost(context, "" + response.message());
                    }


                } else if (response.code() == 401) {
                    CSPreferences.clearPref(context);
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    GlobalClass.showtost(context, "" + response.message());
                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<HomePageResponse> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                // dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void getNssGalleryDataMethod(final Activity activity, final Context context, String token, LinearLayout llmain, SwipeRefreshLayout mSwipeRefreshLayout, final GetnssGalleryData_interface anInterface) {

        mSwipeRefreshLayout.setRefreshing(true);

        Call<NssGalleryResponse> responseCall = ApiClient.getClient().nssGalleryDataAPi(token);
        responseCall.enqueue(new Callback<NssGalleryResponse>() {
            @Override
            public void onResponse(Call<NssGalleryResponse> call, Response<NssGalleryResponse> response) {
                // dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        mSwipeRefreshLayout.setRefreshing(false);

                        llmain.setVisibility(View.VISIBLE);

                        anInterface.GetnssgalleryData(response.body().getData());


                    } else {
                        GlobalClass.showtost(context, "" + response.message());
                    }


                } else if (response.code() == 401) {
                    CSPreferences.clearPref(context);
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<NssGalleryResponse> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                // dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void allDistrict_listMethod(final Context context, final GetAllDistricData_interface getAllDistricData_interface) {

        loadershowwithMsg(context, "Getting All District List...");
        Call<GetDistrictResponse> userpost_responseCall = ApiClient.getClient().DivyangjanGetDistrictDataAPi();
        userpost_responseCall.enqueue(new Callback<GetDistrictResponse>() {
            @Override
            public void onResponse(Call<GetDistrictResponse> call, Response<GetDistrictResponse> response) {
                dailoghide(context);
                if (response.isSuccessful() && response.code() == 200) {
                    GlobalClass.showtost(context, "" + response.message());
                    getAllDistricData_interface.GetAllDistricData((response.body().getData()));
                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<GetDistrictResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void allCollege_listMethod(final Context context, String distric, final GetAllCollegeData_interface getAllCollegeData_interface) {

        loadershowwithMsg(context, "Getting All College List...");
        Call<CollegeListResponse> userpost_responseCall = ApiClient.getClient().CollegeDataAPi(distric);
        userpost_responseCall.enqueue(new Callback<CollegeListResponse>() {
            @Override
            public void onResponse(Call<CollegeListResponse> call, Response<CollegeListResponse> response) {
                dailoghide(context);
                if (response.isSuccessful() && response.code() == 200) {
                    GlobalClass.showtost(context, "" + response.message());
                    getAllCollegeData_interface.GetAllCollegeData((response.body().getData()));
                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<CollegeListResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void allnodalOfficerStudent_listMethod(final Context context, String token, String UserID, String SchemeID, SwipeRefreshLayout simpleSwipeRefreshLayout, final GetAllnodalOfficerStudentData_interface dataInterface) {

        //  loadershowwithMsg(context, "Getting All Student List...");

        simpleSwipeRefreshLayout.setRefreshing(true);

        Call<StudentListUnderNodalOfficerResponse> CollegeScheme = ApiClient.getClient().NodalOfficerStudentDataAPi(token, UserID, SchemeID);
        CollegeScheme.enqueue(new Callback<StudentListUnderNodalOfficerResponse>() {
            @Override
            public void onResponse(Call<StudentListUnderNodalOfficerResponse> call, Response<StudentListUnderNodalOfficerResponse> response) {
                // dailoghide(context);


                simpleSwipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful() && response.code() == 200) {
                    GlobalClass.showtost(context, "" + response.message());
                    dataInterface.GetAllStudentListUnderNodalOfficerData((response.body().getData()));
                } else if (response.code() == 401) {
                    simpleSwipeRefreshLayout.setRefreshing(false);
                    CSPreferences.clearPref(context);
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    GlobalClass.showtost(context, "" + response.message());
                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<StudentListUnderNodalOfficerResponse> call, Throwable t) {
                simpleSwipeRefreshLayout.setRefreshing(false);

                // dailoghide(context);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void admindata_listMethod(final Context context, String token, SwipeRefreshLayout simpleSwipeRefreshLayout, final GetAdminAllData_interface dataInterface) {

        //  loadershowwithMsg(context, "Getting All Student List...");

        simpleSwipeRefreshLayout.setRefreshing(true);

        Call<AdminDataResponse> CollegeScheme = ApiClient.getClient().adminDataAPi(token);
        CollegeScheme.enqueue(new Callback<AdminDataResponse>() {
            @Override
            public void onResponse(Call<AdminDataResponse> call, Response<AdminDataResponse> response) {
                // dailoghide(context);


                simpleSwipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful() && response.code() == 200) {
                    GlobalClass.showtost(context, "" + response.message());
                    dataInterface.GetAdminAllData((response.body().getData().getResponeData()));
                } else if (response.code() == 401) {
                    CSPreferences.clearPref(context);
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    GlobalClass.showtost(context, "" + response.message());
                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<AdminDataResponse> call, Throwable t) {
                simpleSwipeRefreshLayout.setRefreshing(false);

                // dailoghide(context);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void allverificationStudentMethod(final Context context, String token, String SchemeID, String collegeID, SwipeRefreshLayout mSwipeRefreshLayout, final GetAllVerificationStudentListData_interface dataInterface) {

        //  loadershowwithMsg(context, "Getting All Student List...");
        mSwipeRefreshLayout.setRefreshing(true);


        Call<VerificationStudentListResponse> CollegeScheme = ApiClient.getClient().verificationstudentlistDataAPi(token, SchemeID, collegeID);
        CollegeScheme.enqueue(new Callback<VerificationStudentListResponse>() {
            @Override
            public void onResponse(Call<VerificationStudentListResponse> call, Response<VerificationStudentListResponse> response) {
                //  dailoghide(context);
                mSwipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful() && response.code() == 200) {
                    GlobalClass.showtost(context, "" + response.message());
                    dataInterface.GetAllVerificationStudentListData((response.body().getData()));
                } else if (response.code() == 401) {
                    CSPreferences.clearPref(context);
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    GlobalClass.showtost(context, "" + response.message());
                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<VerificationStudentListResponse> call, Throwable t) {

                //  dailoghide(context);
                mSwipeRefreshLayout.setRefreshing(false);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void allChangeSchemeRequsetListMethod(final Context context, String token, String COllegeID, SwipeRefreshLayout mSwipeRefreshLayout, final GetChangeSchemeRequsetListData_interface dataInterface) {

        //  loadershowwithMsg(context, "Getting All Student List...");
        mSwipeRefreshLayout.setRefreshing(true);


        Call<ChangeSchemeResquestListResponse> CollegeScheme = ApiClient.getClient().StudentSchemeChangeRequestListDataAPi(token, COllegeID);
        CollegeScheme.enqueue(new Callback<ChangeSchemeResquestListResponse>() {
            @Override
            public void onResponse(Call<ChangeSchemeResquestListResponse> call, Response<ChangeSchemeResquestListResponse> response) {
                //  dailoghide(context);
                mSwipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful() && response.code() == 200) {
                    GlobalClass.showtost(context, "" + response.message());
                    dataInterface.GetChangeSchemeResquestData((response.body().getData()));
                } else if (response.code() == 401) {
                    CSPreferences.clearPref(context);
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    GlobalClass.showtost(context, "" + response.message());
                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<ChangeSchemeResquestListResponse> call, Throwable t) {

                //  dailoghide(context);
                mSwipeRefreshLayout.setRefreshing(false);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void allverifiedStudentMethod(final Context context, String token, String SchemeID, String collegeID, SwipeRefreshLayout mSwipeRefreshLayout, final GetAllVerifiedStudentListData_interface dataInterface) {

        //  loadershowwithMsg(context, "Getting All Student List...");
        mSwipeRefreshLayout.setRefreshing(true);


        Call<VerifiedStudentDataResponse> CollegeScheme = ApiClient.getClient().verifiedtudentlistDataAPi(token, SchemeID, collegeID);
        CollegeScheme.enqueue(new Callback<VerifiedStudentDataResponse>() {
            @Override
            public void onResponse(Call<VerifiedStudentDataResponse> call, Response<VerifiedStudentDataResponse> response) {
                //  dailoghide(context);
                mSwipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful() && response.code() == 200) {
                    GlobalClass.showtost(context, "" + response.message());
                    dataInterface.GetAllVerifiedStudentListData((response.body().getData()));
                } else if (response.code() == 401) {
                    CSPreferences.clearPref(context);
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    GlobalClass.showtost(context, "" + response.message());
                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<VerifiedStudentDataResponse> call, Throwable t) {

                //  dailoghide(context);
                mSwipeRefreshLayout.setRefreshing(false);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void allStudentDailyActivityForNodalMethod(final Context context, String token, String UserID, String SchemeID, SwipeRefreshLayout mSwipeRefreshLayout, final GetAllStudentDailyActivityForNodalOfficerData_interface dataInterface) {

        //  loadershowwithMsg(context, "Getting All Student List...");
        mSwipeRefreshLayout.setRefreshing(true);


        Call<StudentDailActivityForNodalofficerResponse> CollegeScheme = ApiClient.getClient().StudentDailyActivityForNodalDataAPi(token, UserID, SchemeID);
        CollegeScheme.enqueue(new Callback<StudentDailActivityForNodalofficerResponse>() {
            @Override
            public void onResponse(Call<StudentDailActivityForNodalofficerResponse> call, Response<StudentDailActivityForNodalofficerResponse> response) {
                //  dailoghide(context);
                mSwipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful() && response.code() == 200) {
                    GlobalClass.showtost(context, "" + response.message());
                    dataInterface.GetAllData((response.body().getData()));
                } else if (response.code() == 401) {
                    CSPreferences.clearPref(context);
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    GlobalClass.showtost(context, "" + response.message());
                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<StudentDailActivityForNodalofficerResponse> call, Throwable t) {

                //  dailoghide(context);
                mSwipeRefreshLayout.setRefreshing(false);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void allCollegeScheme_listMethod(final Context context, String collegeId, final GetCollegeSchemeData_interface getCollegeSchemeData_interface) {

        loadershowwithMsg(context, "Getting All College Scheme List...");
        Call<SchemeOfCollegeResponse> CollegeScheme = ApiClient.getClient().CollegeSchemeDataAPi(collegeId);
        CollegeScheme.enqueue(new Callback<SchemeOfCollegeResponse>() {
            @Override
            public void onResponse(Call<SchemeOfCollegeResponse> call, Response<SchemeOfCollegeResponse> response) {
                dailoghide(context);
                if (response.isSuccessful() && response.code() == 200) {
                    GlobalClass.showtost(context, "" + response.message());
                    getCollegeSchemeData_interface.GetSchemeCollegeData((response.body().getData()));
                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<SchemeOfCollegeResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    /*StudentID}/{collegeID}/{schemeID*/
    public void getNodelofficerofCollegeScheme_listMethod(final Activity activity, final Context context, String token, String StudentID, String collegeID, String schemeID, final GetAllNodelOfficerCollegeData_interface getAllNodelOfficerCollegeData_interface) {

        loadershowwithMsg(context, "Getting College Scheme Officer...");

        Call<NodalofficerOfCollegeResponse> CollegeScheme = ApiClient.getClient().GetNodalOfficerApi(token, StudentID, collegeID, schemeID);
        CollegeScheme.enqueue(new Callback<NodalofficerOfCollegeResponse>() {
            @Override
            public void onResponse(Call<NodalofficerOfCollegeResponse> call, Response<NodalofficerOfCollegeResponse> response) {
                dailoghide(context);
                if (response.isSuccessful() && response.code() == 200) {
                    GlobalClass.showtost(context, "" + response.message());
                    getAllNodelOfficerCollegeData_interface.GetAllNodelOfficerCollegeData(response.body().getData().getResponeData());
                    getAllNodelOfficerCollegeData_interface.Getform1CollegeData(response.body().getData().getResponeData1());
                } else if (response.code() == 401) {
                    CSPreferences.clearPref(activity);
                    Intent intent = new Intent(activity, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(intent);
                    GlobalClass.showtost(context, "" + response.message());
                } else {
                    GlobalClass.showtost(context, "" + response.message());

                }
            }

            @Override
            public void onFailure(Call<NodalofficerOfCollegeResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void allCourse_listMethod(final Context context, String collegeID, final GetCourseByCollegeIdData_interface getCourseByCollegeIdData_interface) {

        loadershowwithMsg(context, "Getting All Course List...");
        Call<CourseByCollegeIDResponse> userpost_responseCall = ApiClient.getClient().CourseDataAPi(collegeID);
        userpost_responseCall.enqueue(new Callback<CourseByCollegeIDResponse>() {
            @Override
            public void onResponse(Call<CourseByCollegeIDResponse> call, Response<CourseByCollegeIDResponse> response) {
                dailoghide(context);
                if (response.isSuccessful() && response.code() == 200) {
                    GlobalClass.showtost(context, "" + response.message());
                    getCourseByCollegeIdData_interface.GetAllCourseByCollegeIDData((response.body().getData()));
                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<CourseByCollegeIDResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void allSection_listMethod(final Context context, String collegeID, String courseID, final GetAllSectionData_interface allSectionData_interface) {

        loadershowwithMsg(context, "Getting All Section List...");
        Call<SectionResponse> userpost_responseCall = ApiClient.getClient().SectionDataAPi(collegeID, courseID);
        userpost_responseCall.enqueue(new Callback<SectionResponse>() {
            @Override
            public void onResponse(Call<SectionResponse> call, Response<SectionResponse> response) {
                dailoghide(context);
                if (response.isSuccessful() && response.code() == 200) {
                    GlobalClass.showtost(context, "" + response.message());
                    allSectionData_interface.GetAllSectionData((response.body().getData()));
                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<SectionResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void registrationPostDataMethod(final Activity activity, final Context context, RequestBody
            District_Id,
                                           RequestBody
                                                   college,
                                           RequestBody
                                                   Course,
                                           RequestBody
                                                   Section,
                                           RequestBody
                                                   Combination,
                                           RequestBody
                                                   RollNo,
                                           RequestBody
                                                   StudentName,
                                           RequestBody
                                                   FatherName,
                                           RequestBody
                                                   DOB,
                                           RequestBody
                                                   Gender,
                                           RequestBody
                                                   MobileNo,
                                           RequestBody
                                                   ParentMobileNo,
                                           RequestBody
                                                   PercentageOfDisability,
                                           RequestBody
                                                   Diabiltyid,
                                           RequestBody
                                                   DifferentlyAbledCategory,

                                           RequestBody
                                                   DifferentlyAskedCategory_Type,


                                           RequestBody
                                                   CasteCategory,
                                           RequestBody
                                                   Address,
                                           RequestBody
                                                   confirmpassword,

                                           RequestBody
                                                   StateId,
                                           RequestBody
                                                   EmailID,
                                           RequestBody
                                                   FcmToken_Id,
                                           RequestBody
                                                   Sem,
                                           RequestBody
                                                   OtherCollege,
                                           RequestBody
                                                   OtherCourse,
                                           RequestBody
                                                   OtherSection,
                                           RequestBody
                                                   OtherSubjectCombination,


                                           RequestBody
                                                   userType,
                                           MultipartBody.Part Document,
                                           MultipartBody.Part CertificatefileName) {

        loadershowwithMsg(context, "Registration process is going on...");
        Call<RegistrationRespone> userpost_responseCall = ApiClient.getClient().SignupUsermultipart(District_Id,
                college,
                Course,
                Section,
                Combination,
                RollNo,
                StudentName,
                FatherName,
                DOB,
                Gender,
                MobileNo,
                ParentMobileNo,
                PercentageOfDisability,
                Diabiltyid,
                DifferentlyAbledCategory,
                DifferentlyAskedCategory_Type,
                CasteCategory,
                Address,
                confirmpassword,
                StateId,
                EmailID,
                FcmToken_Id,
                userType,
                Sem,
                OtherCollege,
                OtherCourse,
                OtherSection,
                OtherSubjectCombination,


                Document,
                CertificatefileName);
        userpost_responseCall.enqueue(new Callback<RegistrationRespone>() {

            @Override
            public void onResponse(Call<RegistrationRespone> call, Response<RegistrationRespone> response) {
                dailoghide(context);
                if (response.code() == 200) {
                    if (response.body().getResponse() == 200) {
                        // dailogsuccessWithActivity(context, activity, "Registration successful", "Registration process completed successful.");
                        boolean firstTimelogin = true;


                        try {
                            CSPreferences.putString(activity, "User_Name", response.body().getData().getFirstName());
                            //  CSPreferences.putString(activity, "Status", response.body().getData().getActive());
                            CSPreferences.putString(activity, "PhoneNo", response.body().getData().getMobile());
                            CSPreferences.putString(activity, "Email", response.body().getData().getEmail());
                            CSPreferences.putString(activity, "token", response.body().getData().getMobile());
                            CSPreferences.putString(activity, "pic", response.body().getData().getFilePath());
                            CSPreferences.putString(activity, "role", response.body().getData().getAccountType());

                            CSPreferences.putString(activity, "token", response.body().getData().getToken());
                            CSPreferences.putString(activity, "CollegeId", response.body().getData().getCollegeId());
                            CSPreferences.putString(activity, "userId", response.body().getData().getId());


                            CSPreferences.putString(activity, "TakeCareStep1", response.body().getData().getTakeCareStep1());
                            CSPreferences.putString(activity, "LibrariesAdoptionStep1", response.body().getData().getLibrariesAdoptionStep1());
                            CSPreferences.putString(activity, "RuralTakeCareStep1", response.body().getData().getRuralTakeCareStep1());

                            CSPreferences.putString(activity, "oldAgeTakingCare", response.body().getData().getOldAgeTakingCare());
                            CSPreferences.putString(activity, "oldAgeAdoptionOfLibrary", response.body().getData().getOldAgeAdoptionOfLibrary());
                            CSPreferences.putString(activity, "oldAgePeopleInRuralArea", response.body().getData().getOldAgePeopleInRuralArea());


                            CSPreferences.putBolean(activity, "firstTimelogin", firstTimelogin);
                            CSPreferences.putBolean(activity, "skiplogin", false);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // Intent intent = new Intent(activity, MainActivity.class);
//                        Intent intent = new Intent(activity, LoginActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        activity.startActivity(intent);

                        dailogsuccessGotoLogin(context, activity, "Registration Done. Wait for activation!", "Your Account will activate within 48 hours.");

                        //  dailogsuccess(context, "Registration Done. Wait for activation!", "Your Account will activate within 48 hours.");


                    } else if (response.body().getResponse() == 2002) {
                        dailogError(activity, "Registration Done. Wait for activation!", "Your Account will activate within 48 hours.");

                        //  dailogError(context, "Error!", "This Number is already Registered with Us, you can try with other Number!");
                    } else if (response.body().getResponse() == 0) {
                        dailogError(activity, "Error!", "Server is busy,Registration process failed!,Please try after sometimes");

                        //  dailogError(context, "Error!", "This Number is already Registered with Us, you can try with other Number!");
                    } else if (response.body().getResponse() == 403) {

                        dailogError(activity, "Already Exist!", "Already,Your Account is Created,Please Go for Login.");


                        //  dailogError(context, "Error!", "This Number is already Registered with Us, you can try with other Number!");
                    } else {
                        dailogError(activity, "Error!", "Server is busy,Registration process failed!,Please try after sometimes");


                    }


                } else {
                    //  GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<RegistrationRespone> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void adoptedStep1Method(final Activity activity, final Context context,

                                   String token,

                                   String StudentId,
                                   String NodalOfficreId,


                                   String LibraryInchargeName,
                                   String LibraryAlreadyExist,
                                   String LibraryFacility,

                                   String OldAge_OrphanName,
                                   String OldAGe_OrphanAddress,
                                   String OldAge_OrphanInchargeName
    ) {

        loadershowwithMsg(context, "We are Submitting your Record.");

        Call<AdoptionStep1Response> userpost_responseCall = ApiClient.getClient().AdoptionStep1Api(token, StudentId,
                NodalOfficreId,

                LibraryInchargeName,
                LibraryAlreadyExist,
                LibraryFacility,

                OldAge_OrphanName,
                OldAGe_OrphanAddress,
                OldAge_OrphanInchargeName


        );
        userpost_responseCall.enqueue(new Callback<AdoptionStep1Response>() {
            @Override
            public void onResponse(Call<AdoptionStep1Response> call, Response<AdoptionStep1Response> response) {
                dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {

                        //dailogsuccess(context, "Forget password Reset Successfully.", response.message());
                        // dailogsuccessWithActivity(context, activity, "Your Record Submmited Successfully.", response.message());
                        CSPreferences.putString(context, "LibrariesAdoptionStep1", "Y");

                        dailogsuccessWithActivityFInishGotoNextadoption(context, activity, "Your Record Submmited Successfully.", response.message());


                    } else {
                        dailogError(activity, "Something is wrong!", "please try later.");
                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }

            }

            @Override
            public void onFailure(Call<AdoptionStep1Response> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void verificationStudentMethod(final Activity activity, final Context context,

                                          String token, String StudentId,
                                          String NodalOfficreId, SwipeRefreshLayout mSwipeRefreshLayout,
                                          String remarks, String actionStatus, StudentVerificationListData_interface anInterface) {

        //  loadershowwithMsg(context, "We are Verifying your Record.");
        mSwipeRefreshLayout.setRefreshing(true);
        Call<VerifiedStudentResponse> userpost_responseCall = ApiClient.getClient().studentverificationApi(token, StudentId,
                NodalOfficreId, remarks, actionStatus);
        userpost_responseCall.enqueue(new Callback<VerifiedStudentResponse>() {
            @Override
            public void onResponse(Call<VerifiedStudentResponse> call, Response<VerifiedStudentResponse> response) {
                //dailoghide(context);
                mSwipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {
                        String msg;

                        if (actionStatus.equalsIgnoreCase("Approved")) {
                            msg = "Student Record  has been Verified Successfully.";

                        } else {

                            msg = "Student Record  has been Rejected Successfully.";
                        }

                        anInterface.VerifiedStudentResponsedata(response.body().getResponse());
                        dailogsuccessanddismis(context, activity, msg, response.message());


                    } else {
                        dailogError(activity, "Something is wrong!", "please try later.");
                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }

            }

            @Override
            public void onFailure(Call<VerifiedStudentResponse> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                //   dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void adoptionStep2DataMethod(final Activity activity, final Context context,

                                        String token,
                                        RequestBody StudentId,
                                        RequestBody NodalOfficerId,
                                        RequestBody ProblemFacedByPerson,

                                        RequestBody DateOfVisit,
                                        RequestBody ContactByPhone,
                                        RequestBody AwarenessProgrammesOrg,

                                        RequestBody OtherObservation,
                                        RequestBody TypeOfVisit,
                                        RequestBody Lat,
                                        RequestBody Long,
                                        RequestBody Accuracy,
                                        RequestBody uplodingTime,
                                        MultipartBody.Part Document) {

        loadershowwithMsg(context, "Daily Report Uploading process is going on...");
        Call<AdoptionStep2Response> takecareStep2Api = ApiClient.getClient().AdoptionStep2Api(

                token,
                StudentId,
                NodalOfficerId,
                ProblemFacedByPerson,

                DateOfVisit,
                ContactByPhone,
                AwarenessProgrammesOrg,
                OtherObservation,
                TypeOfVisit,

                Lat,
                Long,
                Accuracy,
                uplodingTime,
                Document

        );
        takecareStep2Api.enqueue(new Callback<AdoptionStep2Response>() {

            @Override
            public void onResponse(Call<AdoptionStep2Response> call, Response<AdoptionStep2Response> response) {
                dailoghide(context);
                if (response.isSuccessful()) {
                    if (response.body().getResponse() == 200) {
                        dailogsuccessWithActivity(context, activity, "Daily Report Uploaded successfully", "Daily Report Uploading process completed successful.");
                    } else {
                        dailogError(activity, "Error!", "Server is busy,Daily Report Uploading process failed!,Please try after sometimes");


                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }


            }

            @Override
            public void onFailure(Call<AdoptionStep2Response> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void RuralTakecareStep1Method(final Activity activity, final Context context,

                                         String token,

                                         String StudentId,
                                         String NodalOfficreId,

                                         String VillageName,
                                         String Block,
                                         String DistrictId,
                                         String VillagePopulation,
                                         String VillageMainProblem,
                                         String Above60YearsPersons,

                                         String OldAge_OrphanName,
                                         String OldAGe_OrphanAddress,
                                         String OldAge_OrphanInchargeName,

                                         String AdoptedPerson,
                                         String Mobile,
                                         String Age,
                                         String Gender,
                                         String HealthStatus) {

        loadershowwithMsg(context, "We are Submitting your Record.");

        Call<TakecareRuralStep1Response> userpost_responseCall = ApiClient.getClient().TakecareRuralStep1Api(token, StudentId,
                NodalOfficreId,
                VillageName,
                Block,
                DistrictId,
                VillagePopulation,
                VillageMainProblem,
                Above60YearsPersons,

                OldAge_OrphanName,
                OldAGe_OrphanAddress,
                OldAge_OrphanInchargeName,

                AdoptedPerson,
                Mobile,
                Age,
                Gender,
                HealthStatus
        );
        userpost_responseCall.enqueue(new Callback<TakecareRuralStep1Response>() {
            @Override
            public void onResponse(Call<TakecareRuralStep1Response> call, Response<TakecareRuralStep1Response> response) {
                dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {

                        //dailogsuccess(context, "Forget password Reset Successfully.", response.message());
                        // dailogsuccessWithActivity(context, activity, "Your Record Submmited Successfully.", response.message());
                        CSPreferences.putString(context, "RuralTakeCareStep1", "Y");

                        dailogsuccessWithActivityFInishGotoNextRural(context, activity, "Your Record Submmited Successfully.", response.message());


                    } else {
                        dailogError(activity, "Something is wrong!", "please try later.");
                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }

            }

            @Override
            public void onFailure(Call<TakecareRuralStep1Response> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void RuralTakecareStep2DataMethod(final Activity activity, final Context context,

                                             String token,
                                             RequestBody StudentId,
                                             RequestBody NodalOfficerId,
                                             RequestBody ProblemFacedByPerson,
                                             RequestBody SolutionProvidedByStudent,
                                             RequestBody RquirmentOfPerson,
                                             RequestBody DateOfVisit,
                                             RequestBody ContactByPhone,
                                             RequestBody AwarenessProgrammesOrg,

                                             RequestBody OtherObservation,
                                             RequestBody TypeOfVisit,
                                             RequestBody Lat,
                                             RequestBody Long,
                                             RequestBody Accuracy,
                                             RequestBody uplodingTime,
                                             MultipartBody.Part Document) {

        loadershowwithMsg(context, "Daily Report Uploading process is going on...");
        Call<RuraltakecarStep2Response> takecareStep2Api = ApiClient.getClient().RuralTakecareStep2Api(
                token,
                StudentId,
                NodalOfficerId,
                ProblemFacedByPerson,
                SolutionProvidedByStudent,
                RquirmentOfPerson,
                DateOfVisit,
                ContactByPhone,
                AwarenessProgrammesOrg,
                OtherObservation,
                TypeOfVisit,
                Lat,
                Long,
                Accuracy,
                uplodingTime,
                Document);
        takecareStep2Api.enqueue(new Callback<RuraltakecarStep2Response>() {

            @Override
            public void onResponse(Call<RuraltakecarStep2Response> call, Response<RuraltakecarStep2Response> response) {
                dailoghide(context);
                if (response.isSuccessful()) {
                    if (response.body().getResponse() == 200) {
                        dailogsuccessWithActivity(context, activity, "Daily Report Uploaded successfully", "Daily Report Uploading process completed successful.");
                    } else {
                        dailogError(activity, "Error!", "Server is busy,Daily Report Uploading process failed!,Please try after sometimes");


                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }


            }

            @Override
            public void onFailure(Call<RuraltakecarStep2Response> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void TakecareStep1Method(final Activity activity, final Context context, String token, String StudentId,
                                    String NodalOfficreId,
                                    String OldAge_OrphanName,
                                    String OldAGe_OrphanAddress,
                                    String OldAge_OrphanInchargeName, String AdoptedPerson,
                                    String Mobile,
                                    String Age,
                                    String Gender,
                                    String HealthStatus) {

        loadershowwithMsg(context, "We are Submitting your Record.");

        Call<OldagetakecarStep1Response> userpost_responseCall = ApiClient.getClient().TakecareStep1Api(token, StudentId,
                NodalOfficreId,
                OldAge_OrphanName,
                OldAGe_OrphanAddress,
                OldAge_OrphanInchargeName,

                AdoptedPerson,
                Mobile,
                Age,
                Gender,
                HealthStatus
        );
        userpost_responseCall.enqueue(new Callback<OldagetakecarStep1Response>() {
            @Override
            public void onResponse(Call<OldagetakecarStep1Response> call, Response<OldagetakecarStep1Response> response) {
                dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {

                        //dailogsuccess(context, "Forget password Reset Successfully.", response.message());
                        // dailogsuccessWithActivity(context, activity, "Your Record Submmited Successfully.", response.message());
                        CSPreferences.putString(context, "TakeCareStep1", "Y");

                        dailogsuccessWithActivityFInishGotoNext(context, activity, "Your Record Submmited Successfully.", response.message());


                    } else {
                        dailogError(activity, "Something is wrong!", "please try later.");
                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }

            }

            @Override
            public void onFailure(Call<OldagetakecarStep1Response> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void TakecareStep2DataMethod(final Activity activity, final Context context,

                                        String token,
                                        RequestBody StudentId,
                                        RequestBody NodalOfficerId,
                                        RequestBody ProblemFacedByPerson,
                                        RequestBody SolutionProvidedByStudent,
                                        RequestBody RquirmentOfPerson,
                                        RequestBody DateOfVisit,
                                        RequestBody ContactByPhone,
                                        RequestBody OtherObservation,
                                        RequestBody TypeOfVisit,
                                        RequestBody Lat,
                                        RequestBody Long,
                                        RequestBody Accuracy,
                                        RequestBody uplodingTime,
                                        MultipartBody.Part Document) {

        loadershowwithMsg(context, "Daily Report Uploading process is going on...");
        Call<OldagetakecarStep2Response> takecareStep2Api = ApiClient.getClient().TakecareStep2Api(
                token,
                StudentId,
                NodalOfficerId,
                ProblemFacedByPerson,
                SolutionProvidedByStudent,
                RquirmentOfPerson,
                DateOfVisit,
                ContactByPhone,
                OtherObservation,
                TypeOfVisit,
                Lat,
                Long,
                Accuracy,
                uplodingTime,
                Document);
        takecareStep2Api.enqueue(new Callback<OldagetakecarStep2Response>() {

            @Override
            public void onResponse(Call<OldagetakecarStep2Response> call, Response<OldagetakecarStep2Response> response) {
                dailoghide(context);
                if (response.isSuccessful()) {
                    if (response.body().getResponse() == 200) {
                        dailogsuccessWithActivity(context, activity, "Daily Report Uploaded successfully", "Daily Report Uploading process completed successful.");
                    } else {
                        dailogError(activity, "Error!", "Server is busy,Daily Report Uploading process failed!,Please try after sometimes");


                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }


            }

            @Override
            public void onFailure(Call<OldagetakecarStep2Response> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void allSubjectCombination_listMethod(final Context context, String collegeID, String courseID, String SectionId, final GetSubjectCombinationData_interface getSubjectCombinationData_interface) {

        loadershowwithMsg(context, "Getting  Subject Combination List...");
        Call<SubjectCombinationResponse> userpost_responseCall = ApiClient.getClient().SubjectCombinationAPi(collegeID, courseID, SectionId);
        userpost_responseCall.enqueue(new Callback<SubjectCombinationResponse>() {
            @Override
            public void onResponse(Call<SubjectCombinationResponse> call, Response<SubjectCombinationResponse> response) {
                dailoghide(context);
                if (response.isSuccessful() && response.code() == 200) {
                    GlobalClass.showtost(context, "" + response.message());
                    getSubjectCombinationData_interface.GetSubjectCombinationData((response.body().getData()));
                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<SubjectCombinationResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void ProfileDataMethod(final Activity activity, final Context context, ConstraintLayout llmain, final ProfileData_interface profileData_interface, String MobileNo) {

        loadershowwithMsg(context, "Loading...");

        Call<ProfileDataResponse> responseCall = ApiClient.getClient().getProfileDataAPi(MobileNo);
        responseCall.enqueue(new Callback<ProfileDataResponse>() {
            @Override
            public void onResponse(Call<ProfileDataResponse> call, Response<ProfileDataResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {
                        llmain.setVisibility(View.VISIBLE);

                        profileData_interface.userprofiledata(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<ProfileDataResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void loginPostDataMethod(final Activity activity, final Context context, final OldAgedLoginData_interface astavkraLoginData_interface, LoginRequest request) {

        loadershowwithMsg(context, "We are veryfing your Detail for login.");

        // Call<LoginResponse> userpost_responseCall = ApiClient.getClient().LoginUser(PhoneNo, Password, FcmToken);
        Call<LoginResponse> userpost_responseCall = ApiClient.getClient().LoginUser(request);
        userpost_responseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {

                        // dailogsuccess(context, "Login Successfull.", "Welcome to Shiksha Sahyogi,Haryana.");
                        astavkraLoginData_interface.Loginuserdata(response.body().getData());

                        Intent intentlogin = new Intent(context, MainActivity.class);
                        intentlogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intentlogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intentlogin);
                        activity.finish();


                    } else if (response.body().getResponse() == 202) {
                        dailogError(activity, "Unverified Account!", " Your Account is Not Verified yet. Contact to Nodal officer.");
                    } else if (response.body().getResponse() == 203) {
                        dailogError(activity, "Verification Rejected!", response.body().getSysMessage());
                    } else  if (response.body().getResponse() ==400 ){
                        dailogError(activity, "Credentials Not Found!", "You have entered wrong credentials.");
                    }

                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void forgotPasswordPostDataMethod(final Activity activity, final Context context, ForgotPasswordRequest request) {

        loadershowwithMsg(context, "We are Sending auto generated password on your Registered Mobile number.");

        // Call<LoginResponse> userpost_responseCall = ApiClient.getClient().LoginUser(PhoneNo, Password, FcmToken);
        Call<ForgotPasswordResponse> userpost_responseCall = ApiClient.getClient().ForgetPasswordUser(request);
        userpost_responseCall.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {

                        dailogsuccessWithActivity(context, activity, " Password has been Changed and Sent Successfully.", "New auto generated password has been sent on your Registered Mobile number .");


                    } else {
                        dailogError(activity, "Mobile Number Not Found!", "The Mobile Number You have entered is not Regitered with Us.");
                    }


                } else {
                    GlobalClass.showtost(context, "Something went wrong. Please try after sometimes." + response.message());
                }

            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void VerifyOtpPostDataMethod(final Activity activity, final Context context, OtpVerifyData_interface otpVerifyData_interface, VerifyOtpRequest request) {

        loadershowwithMsg(context, "Registration Process is going on....");

        // Call<LoginResponse> userpost_responseCall = ApiClient.getClient().LoginUser(PhoneNo, Password, FcmToken);
        Call<VerifyOtpResponse> userpost_responseCall = ApiClient.getClient().verifyOTP(request);
        userpost_responseCall.enqueue(new Callback<VerifyOtpResponse>() {
            @Override
            public void onResponse(Call<VerifyOtpResponse> call, Response<VerifyOtpResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {

                        // dailogsuccessanddismis(context,activity, "New Password Sent Successfull.", "New auto generated password has been sent on your Registered Mobile number .");
                        otpVerifyData_interface.userOtpVerifydata(response.body().getResponse());


                    } else {
                        dailogError(activity, "Mobile Number Not Found!", "The Mobile Number You have entered is not Regitered with Us.");
                    }


                } else {
                    GlobalClass.showtost(context, "Something went wrong. Please try after sometimes." + response.message());
                }

            }

            @Override
            public void onFailure(Call<VerifyOtpResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void signupPostDataMethod(final Activity activity, final Context context, final SignupData_interface data_interface, SignupRequest request) {

        loadershowwithMsg(context, "we are sending a 4 digits OTP on given Mobile Number for Registration....");

        Call<SignupResponse> userpost_responseCall = ApiClient.getClient().signupUser(request);
        userpost_responseCall.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {

                        dailogsuccess(activity, "OTP Sent.", "Please check your Mobile. OTP is  valid for 10 minutes.");
                        data_interface.alluserdata((SignupResponse.Data) response.body().getData());

                    } else if (response.body().getResponse() == 303) {
                        data_interface.alluserdata((SignupResponse.Data) response.body().getData());

                    } else if (response.body().getResponse() == 304) {
                        dailogsuccess(activity, "Already,This Mobile Number is Registered", "Already,This Mobile Number is Registered with us,Please try with different number. ");
                    } else {

                        dailogError(activity, "Something went wrong !", "Please try after sometimes.");

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }

            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void schemeChangeRequestByStudentMethod(final Activity activity, final Context context, String token, ChangeSchemeResquestByStudent request, final schemeRequestByStudentData_interface data_interface) {

        loadershowwithMsg(context, "Applying for new Scheme/ modifying is processing ....");

        Call<ChangeSchemeResquestByStudentResponse> userpost_responseCall = ApiClient.getClient().RequestToChangeSchemeByStudentApi(token, request);
        userpost_responseCall.enqueue(new Callback<ChangeSchemeResquestByStudentResponse>() {
            @Override
            public void onResponse(Call<ChangeSchemeResquestByStudentResponse> call, Response<ChangeSchemeResquestByStudentResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {

                        dailogsuccess(activity, "Scheme Change Done.", "Scheme change request for new scheme has applied seccessfully.");
                        data_interface.alldata(response.body().getResponse());

                    } else if (response.body().getResponse() == 303) {
                        data_interface.alldata(response.body().getResponse());

                    } else if (response.body().getResponse() == 304) {
                        dailogsuccess(activity, (response.body().getSysMessage()), response.body().getSysMessage());
                    } else {

                        dailogError(activity, response.body().getSysMessage(), response.body().getSysMessage());

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }

            }

            @Override
            public void onFailure(Call<ChangeSchemeResquestByStudentResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void ProcessSchemeChangeRequestMethod(final Activity activity, final Context context, String token, ChangeSchemeByNodalRequest request, final ProcessSchemeChangeData_interface data_interface, String nodalOfficerAction) {

        loadershowwithMsg(context, "Processing ....");

        Call<ChangeSchemeByNodalResponse> userpost_responseCall = ApiClient.getClient().ProcessSchemeChangeRequestApi(token, request);
        userpost_responseCall.enqueue(new Callback<ChangeSchemeByNodalResponse>() {
            @Override
            public void onResponse(Call<ChangeSchemeByNodalResponse> call, Response<ChangeSchemeByNodalResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {

                        dailogsuccess(activity, " Done.", "seccessfully.");
                        data_interface.ProcessSchemeChangedata(response.body().getResponse());

                    } else if (response.body().getResponse() == 303) {
                        data_interface.ProcessSchemeChangedata(response.body().getResponse());

                    } else if (response.body().getResponse() == 304) {
                        dailogsuccess(activity, (response.body().getSysMessage()), response.body().getSysMessage());
                    } else {

                        dailogError(activity, response.body().getSysMessage(), response.body().getSysMessage());

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }

            }

            @Override
            public void onFailure(Call<ChangeSchemeByNodalResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void schemechangeForStudentMethod(final Context context, String token, String StudentID, String collegeID, final GetAllSchemeForChangeData_interface dataInterface) {

        loadershowwithMsg(context, "Getting All Schemes of college and selected by Student...");
        // mSwipeRefreshLayout.setRefreshing(true);


        Call<ChangeSchemeForStudentResponse> CollegeScheme = ApiClient.getClient().changeschemeforstudentDataAPi(token, StudentID, collegeID);
        CollegeScheme.enqueue(new Callback<ChangeSchemeForStudentResponse>() {
            @Override
            public void onResponse(Call<ChangeSchemeForStudentResponse> call, Response<ChangeSchemeForStudentResponse> response) {
                dailoghide(context);
                ///  mSwipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful() && response.code() == 200) {
                    GlobalClass.showtost(context, "" + response.message());
                    dataInterface.GetAllSchemeForChangeData((response.body().getData()));
                } else if (response.code() == 401) {
                    CSPreferences.clearPref(context);
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    GlobalClass.showtost(context, "" + response.message());
                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<ChangeSchemeForStudentResponse> call, Throwable t) {

                dailoghide(context);
                // mSwipeRefreshLayout.setRefreshing(false);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void resetforgotPostDataMethod(final Activity activity, final Context context, String token, ResetForget_interface resetForget_interface, ResetPasswordRequest request) {

        loadershowwithMsg(context, "We are Changing your password.");

        Call<ResetPasswordResponse> userpost_responseCall = ApiClient.getClient().ResetforgotPassword(token, request);
        userpost_responseCall.enqueue(new Callback<ResetPasswordResponse>() {
            @Override
            public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        //dailogsuccessWithActivity(context, activity, "Password Re-Set Done.", "Successfully Reset your Password.");
                        GlobalClass.showtost(context, "Password Reset Done.\n Successfully Reset your Password.");


                        resetForget_interface.resetpassword(response.body().getResponse());

                    } else {
                        // GlobalClass.showtost(context, "This  Number is Not Registered with Us.");
                        dailogError(activity, "Failed!", "Try Again");

                    }

                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

}









