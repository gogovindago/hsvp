package hsvp.survey.hry.pkl.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.allinterface.ProfileData_interface;
import hsvp.survey.hry.pkl.apicall.WebAPiCall;
import hsvp.survey.hry.pkl.databinding.ActivityProfileBinding;
import hsvp.survey.hry.pkl.models.ProfileDataResponse;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.CSPreferences;
import hsvp.survey.hry.pkl.utility.GlobalClass;


public class ProfileActivity extends BaseActivity implements ProfileData_interface {
    ActivityProfileBinding binding;
    String User_PhoneNo, token, Url = "http://library.highereduhry.com/Admin/Login.aspx", tittle = "Library management System (LMS)";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(ProfileActivity.this)) {

                    WebAPiCall webapiCall = new WebAPiCall();
                    webapiCall.ProfileDataMethod(ProfileActivity.this, ProfileActivity.this, binding.allconst, ProfileActivity.this, User_PhoneNo);

                } else {

                    Toast.makeText(ProfileActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }
                binding.simpleSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }


    @Override
    public void initData() {

        try {
            User_PhoneNo = CSPreferences.readString(ProfileActivity.this, "PhoneNo");
            token = CSPreferences.readString(ProfileActivity.this, "token");
            binding.edtRegistraionId.setText(User_PhoneNo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (GlobalClass.isNetworkConnected(ProfileActivity.this)) {
            WebAPiCall webapiCall = new WebAPiCall();
            webapiCall.ProfileDataMethod(ProfileActivity.this, ProfileActivity.this, binding.allconst, ProfileActivity.this, User_PhoneNo);

        } else {

            Toast.makeText(this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void initListeners() {


        binding.txtAccountCreatedat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });
        binding.btnLms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent certificate = new Intent(ProfileActivity.this, OpenBooksActivity.class);
//                certificate.putExtra("bookurl", Url);
//                certificate.putExtra("title", tittle);
//                startActivity(certificate);

            }
        });

    }

    @Override
    public void userprofiledata(List<ProfileDataResponse.Datum> list) {


        try {

            Glide.with(this)
                    .load(list.get(0).getFilePath()) // image url
                    .placeholder(R.mipmap.ic_launcher_round) // any placeholder to load at start
                    .error(R.mipmap.ic_launcher_round)  // any image in case of error
                    .override(85, 85) // resizing
                    .centerCrop()
                    .into(binding.profileImage);


            // Just use the appropriate method: String#split().

           /* String string = list.get(0).getFirstName();
            String[] parts = string.split(" ");
            String part1 = parts[0]; // 004
            String part2 = parts[1]; // 034556
           */
            binding.name.setText(list.get(0).getFirstName());
            binding.Lastname.setText(list.get(0).getLastName());

            // binding.fathername.setText(list.get(0).getFatherName());
            binding.edtmobile.setText(list.get(0).getMobile());
            binding.edtemail.setText(list.get(0).getEmail());


            if (list.get(0).isOldAgeTakingCare() || CSPreferences.readString(this, "oldAgeTakingCare").equalsIgnoreCase("true")) {

                binding.chktcareOldAgeProfile.setEnabled(false);

                binding.chktcareOldAgeProfile.setChecked(true);
                binding.chktcareOldAgeProfile.setTypeface(null, Typeface.BOLD);

            }

            if (list.get(0).getOldAgeAdoptionOfLibrary() || CSPreferences.readString(this, "oldAgeAdoptionOfLibrary").equalsIgnoreCase("true")) {
                binding.chkAdoptionofLibrariesProfile.setEnabled(false);

                binding.chkAdoptionofLibrariesProfile.setChecked(true);
                binding.chkAdoptionofLibrariesProfile.setTypeface(null, Typeface.BOLD);

            }


            if (list.get(0).getOldAgePeopleInRuralArea() || CSPreferences.readString(this, "oldAgePeopleInRuralArea").equalsIgnoreCase("true")) {
                binding.chkruralAreaProfile.setEnabled(false);

                binding.chkruralAreaProfile.setChecked(true);

                binding.chkruralAreaProfile.setTypeface(null, Typeface.BOLD);

            }

//            else {
//                binding.chkruralAreaProfile.setChecked(false);
//                binding.chkAdoptionofLibrariesProfile.setChecked(false);
//                binding.chktcareOldAgeProfile.setChecked(false);
//
//            }


            //            chktcareOldAgeProfile
            //chkAdoptionofLibrariesProfile
            //chkruralAreaProfile

            //            binding.edtwatchedvideo.setText(list.get(0).getVideo());
//            binding.edtreadbooks.setText(list.get(0).getBook());


            // binding.edtgender.setText(list.get(0).getGender());
            /// binding.edtcollegeName.setText(list.get(0).getCollege());
            //binding.edtfacultyDesignation.setText(list.get(0).getDesignation());
            //binding.edtdistrict.setText(list.get(0).getCDISTRICT());
            // binding.edtsubject.setText(list.get(0).getSubject());
            // binding.txtAccountCreatedat.setText(" DOFRetirement: " + list.get(0).getDORetirement());


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}