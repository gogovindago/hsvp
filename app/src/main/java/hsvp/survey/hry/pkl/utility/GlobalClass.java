package hsvp.survey.hry.pkl.utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import cn.pedant.SweetAlert.SweetAlertDialog;
import hsvp.survey.hry.pkl.R;


public class GlobalClass {

    Context context;

    /* CCL leave mi max and entire service limit */
    public static int totalleaveInHistory = 730;
    public static int minimumtotalleaveaccepted = 30;
    public static int maxtotalleaveaccepted = 135;

    /* Paternity leave mi max and entire service limit */
    public static int totalleaveInPaternityleaveHistory = 30;
    public static int maxtotalleaveacceptedforPaternityleave = 15;
    public static int minimumtotalleaveacceptedforPaternityleave = 1;


    /* Maternity leave mi max and entire service limit */
    public static int totalleaveInmaternityleaveHistory = 180;
    public static int maxtotalleaveacceptedformaternityleave = 180;
    public static int minimumtotalleaveacceptedformaternityleave = 180;


    /* Miscarriage leave mi max and entire service limit */
    public static int totalleaveInMiscarriageleaveHistory = 45;
    public static int maxtotalleaveacceptedforMiscarriageleave = 45;
    public static int minimumtotalleaveacceptedforMiscarriageleave = 1;

    //Live url

    // public static String baseurl = "";

    // test url
   // public static String baseurl = "http://112.196.99.107:81/api/commonapi/";
   // public static String baseurl = "http://oldage.highereduhry.com/api/OldAgeApi/";
    public static String baseurl = "https://oldage.highereduhry.ac.in/api/OldAgeApi/";


   // public static String baseurl = "http://112.196.99.108:92/api/commonapi/";



    public static String nointernet = "No Internet Connection";
    public static String nodatafound = "No Data Found";

   /* // Call fragmnet
    public static void fragment(FragmentActivity fragmentActivity, Fragment fragment, boolean addToBackStack){
        if (addToBackStack){
             fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fram, fragment).addToBackStack(null).commit();
        }else {
          fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fram, fragment).commit();
        }
    }*/

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

  /*  public static void dailogshow(Context context) {
        ProgressDialog pd;

        pd = new ProgressDialog(context);
        pd.setMessage("loading...");
        pd.setCancelable(false);
        pd.show();
    }

    public static void dailoghide(Context context) {
        ProgressDialog pd;
        pd = new ProgressDialog(context);
        pd.dismiss();
    }*/


    // Show toast
    public static void showtost(Context context, String message) {
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }

    // Email validation
    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public static void progressdailogShow(Context context, ProgressDialog pd) {
        pd = new ProgressDialog(context);
        pd.setMessage("loading...");
        pd.setCancelable(false);
        pd.show();
    }

    public static void progressdailogHide(Context context, ProgressDialog pd) {
        pd = new ProgressDialog(context);
        pd.dismiss();
    }


    SweetAlertDialog pDialog;
    ProgressDialog pd;



    public static void dailogsuccessWithActivity(final Context context, final Activity activity, String msgtitle, String msgcontentText) {
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


     /*
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog.setTitle(msgtitle + "!");
        sweetAlertDialog.setContentText(msgcontentText);
        sweetAlertDialog.setVolumeControlStream(2);
        sweetAlertDialog.getAlerType();
        sweetAlertDialog.changeAlertType(2);
        sweetAlertDialog.setCanceledOnTouchOutside(false);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();

            }
        });
        sweetAlertDialog.show();*/

    }

    public static void dailogsuccess(Activity activity,final Context context, String msgtitle, String msgcontentText) {

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
               // activity.finish();
            }
        });
        alertDialog.show();



      /*
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog.setTitle(msgtitle + "!");
        sweetAlertDialog.setContentText(msgcontentText);
        sweetAlertDialog.setVolumeControlStream(2);
        sweetAlertDialog.getAlerType();
        sweetAlertDialog.changeAlertType(2);
        sweetAlertDialog.setCanceledOnTouchOutside(false);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        });
        sweetAlertDialog.show();
*/
    }

    public static void dailogError(final Activity activity, String msgtitle, String msgcontentText) {



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



      /*  SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog.setTitle(msgtitle + "!");
        sweetAlertDialog.setContentText(msgcontentText);
        sweetAlertDialog.setVolumeControlStream(2);
        sweetAlertDialog.getAlerType();
        sweetAlertDialog.changeAlertType(1);
        sweetAlertDialog.setCanceledOnTouchOutside(false);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        });
        sweetAlertDialog.show();*/

    }


    public void dailogshow(final Context context) {
        pDialog = new SweetAlertDialog(context);
        pDialog.changeAlertType(5);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

//        pd = new ProgressDialog(context);
//        pd.setMessage("loading...");
//        pd.setCancelable(false);
//        pd.show();
    }

    public void loadershowwithMsg(final Context context, String msg) {
        pDialog = new SweetAlertDialog(context);
        pDialog.changeAlertType(5);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(msg);
        pDialog.setCancelable(false);
        pDialog.show();

//        pd = new ProgressDialog(context);
//        pd.setMessage("loading...");
//        pd.setCancelable(false);
//        pd.show();
    }

    public void dailoghide(final Context context) {
        // pd.dismiss();
        pDialog.dismissWithAnimation();
    }
}
