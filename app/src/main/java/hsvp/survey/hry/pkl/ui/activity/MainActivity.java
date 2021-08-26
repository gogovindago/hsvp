package hsvp.survey.hry.pkl.ui.activity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.adapter.AdminImgAdapter;
import hsvp.survey.hry.pkl.adapter.DrawerItemCustomAdapter;
import hsvp.survey.hry.pkl.adapter.OptionsAdapter;
import hsvp.survey.hry.pkl.adapter.SliderAdapter;
import hsvp.survey.hry.pkl.allinterface.GetbannersData_interface;
import hsvp.survey.hry.pkl.apicall.WebAPiCall;
import hsvp.survey.hry.pkl.models.DataModelLeftNew;
import hsvp.survey.hry.pkl.models.HomePageResponse;
import hsvp.survey.hry.pkl.models.OptionDataModel;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.CSPreferences;
import hsvp.survey.hry.pkl.utility.GlobalClass;


public class MainActivity extends BaseActivity implements OptionsAdapter.ItemListener, GetbannersData_interface, AdminImgAdapter.ItemListener {

    private AppUpdateManager mAppUpdateManager;
    private static final int RC_APP_UPDATE = 11;
    private static final String TAG = "MainActivity";

    private static DrawerLayout mDrawerLayout;
    ImageView toggle, profile_image;
    ImageButton mainNotification;
    TextView toolbartxt, uname, textView, txtrole, txtwelcome;
    Toolbar toolbar;
    List<HomePageResponse.Datum.ResponeDatum> sliderItemList;
    private TextView mTextMessage;
    private String[] mNavigationDrawerItemTitles;
    private ListView mDrawerList;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    RecyclerView recyclerView, rvadminimage;
    ArrayList arrayList = new ArrayList();
    List<DataModelLeftNew> dataModelLeftList;
    List<HomePageResponse.Datum.ResponeData1> adminimagelist = new ArrayList<>();
    LinearLayout llmain;
    SliderView sliderView;
    SliderAdapter sliderAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    String token, collegeID = "0", Unverified = "0", Verified = "0", SchemeRequest = "0";
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static void drawerCheck() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTextMessage = findViewById(R.id.message);
        llmain = findViewById(R.id.llmain);
        mSwipeRefreshLayout = findViewById(R.id.simpleSwipeRefreshLayoutmain);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mainNotification = findViewById(R.id.notifcationmain);

        toggle = findViewById(R.id.toggle);
        toolbartxt = findViewById(R.id.toolbartxt);


        /// mTitle = mDrawerTitle = getTitle();
        // mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerList = findViewById(R.id.left_drawer);
        uname = findViewById(R.id.uname);
        textView = findViewById(R.id.textView);
        txtrole = findViewById(R.id.txtrole);
        txtwelcome = findViewById(R.id.txtwelcome);
        profile_image = findViewById(R.id.profile_image);

        try {

            collegeID = CSPreferences.readString(this, "CollegeId");
            if (collegeID.equalsIgnoreCase("")) {

                collegeID = "0";

            }

            if (CSPreferences.getBoolean(this, "firstTimelogin")) {

                GlobalClass.dailogsuccess(MainActivity.this,this, "Login Successfull.", "Welcome to Sauhardam.");
                CSPreferences.putBolean(this, "firstTimelogin", false);
                mDrawerLayout.openDrawer(GravityCompat.START);


            } else {

                mDrawerLayout.closeDrawers();

            }


            if (CSPreferences.readString(this, "role").equalsIgnoreCase("1")) {

                txtrole.setText("Principle");

            } else if (CSPreferences.readString(this, "role").equalsIgnoreCase("2")) {

                txtrole.setText("Nodal Officer");

            } else if (CSPreferences.readString(this, "role").equalsIgnoreCase("3")) {

                txtrole.setText("Student");

            } else if (CSPreferences.readString(this, "role").equalsIgnoreCase("4")) {

                txtrole.setText("Admin");

            }

            token = CSPreferences.readString(this, "token");
            uname.setText(CSPreferences.readString(this, "PhoneNo"));


            textView.setText(CSPreferences.readString(this, "Email"));
            toolbartxt.setText(CSPreferences.readString(this, "User_Name"));
            //txtrole.setText(CSPreferences.readString(this, "Email"));
            txtwelcome.setText("Welcome, " + CSPreferences.readString(this, "User_Name"));


            Glide.with(MainActivity.this)
                    .load(CSPreferences.readString(MainActivity.this, "pic").trim()) // image url
                    .placeholder(R.mipmap.ic_launcher_round) // any placeholder to load at start
                    .error(R.mipmap.ic_launcher_round)  // any image in case of error
                    .override(140, 140) // resizing
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(profile_image);


        } catch (Exception e) {

        }


        setupToolbar();


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(MainActivity.this)) {
                    llmain.setVisibility(View.GONE);

                    WebAPiCall webapiCall = new WebAPiCall();
                    webapiCall.getHomePageDataMethod(MainActivity.this, MainActivity.this, "Bearer " + token, collegeID, llmain, mSwipeRefreshLayout, MainActivity.this);

                } else {

                    Toast.makeText(MainActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }
                shuffle();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        //  mSwipeRefreshLayout.setRefreshing(true);

        if (GlobalClass.isNetworkConnected(MainActivity.this)) {
            WebAPiCall webapiCall = new WebAPiCall();
            webapiCall.getHomePageDataMethod(MainActivity.this, MainActivity.this, "Bearer " + token, collegeID, llmain, mSwipeRefreshLayout, this);

        } else {

            Toast.makeText(this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }

        sliderView = findViewById(R.id.imageSlider);

        sliderAdapter = new SliderAdapter(this);
        sliderItemList = new ArrayList<>();
        //renewItems();

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        // sliderView.setIndicatorSelectedColor(Color.WHITE);
        //sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });


        rvadminimage = (RecyclerView) findViewById(R.id.rvadminimage);


     /*   adminimagelist.add(new OptionDataModel(R.string.cm, R.drawable.manohar, 11, "#FFFFFF"));
        adminimagelist.add(new OptionDataModel(R.string.edm, R.drawable.kanwarpalgujjar, 12, "#FFFFFF"));
        adminimagelist.add(new OptionDataModel(R.string.psnow, R.drawable.anand_png, 13, "#FFFFFF"));
        adminimagelist.add(new OptionDataModel(R.string.dgnow, R.drawable.vijay, 14, "#FFFFFF"));
        AdminImgAdapter adapteradminimage = new AdminImgAdapter(this, adminimagelist, this);
        rvadminimage.setAdapter(adapteradminimage);
        GridLayoutManager adminimagemanager = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        rvadminimage.setLayoutManager(adminimagemanager);
*/

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        if (CSPreferences.readString(this, "role").equalsIgnoreCase("1")) {

            //   arrayList.add(new OptionDataModel(R.string.profile, R.drawable.profile, 1, "#FFFFFF"));
            arrayList.add(new OptionDataModel(R.string.studentreportcard, R.drawable.ic_baseline_assignment_turned_in_24, 2, "#FFFFFF"));
//            arrayList.add(new OptionDataModel(R.string.plansecond, R.mipmap.ic_launcher, 3, "#FFFFFF"));
//            arrayList.add(new OptionDataModel(R.string.planthrd, R.mipmap.ic_launcher, 4, "#FFFFFF"));


        } else if (CSPreferences.readString(this, "role").equalsIgnoreCase("2")) {

            //   arrayList.add(new OptionDataModel(R.string.profile, R.drawable.profile, 1, "#FFFFFF"));
            arrayList.add(new OptionDataModel(R.string.studentreportcard, R.drawable.ic_baseline_assignment_turned_in_24, 2, "#FFFFFF"));
            arrayList.add(new OptionDataModel(R.string.studentverification, R.drawable.ic_baseline_pending_actions_24, 222, "#FFFFFF"));
            arrayList.add(new OptionDataModel(R.string.studentverified, R.drawable.ic_baseline_verified_user_24, 333, "#FFFFFF"));
            arrayList.add(new OptionDataModel(R.string.studentschemechange, R.drawable.swape, 334, "#FFFFFF"));
//            arrayList.add(new OptionDataModel(R.string.plansecond, R.drawable.profile, 3, "#FFFFFF"));
//            arrayList.add(new OptionDataModel(R.string.planthrd, R.drawable.profile, 4, "#FFFFFF"));


        } else if (CSPreferences.readString(this, "role").equalsIgnoreCase("4")) {

            //   arrayList.add(new OptionDataModel(R.string.profile, R.drawable.profile, 1, "#FFFFFF"));
            arrayList.add(new OptionDataModel(R.string.reportcard, R.drawable.ic_baseline_assignment_turned_in_24, 202, "#FFFFFF"));
//            arrayList.add(new OptionDataModel(R.string.plansecond, R.drawable.profile, 3, "#FFFFFF"));
//            arrayList.add(new OptionDataModel(R.string.planthrd, R.drawable.profile, 4, "#FFFFFF"));


        } else {


            //   arrayList.add(new OptionDataModel(R.string.profile, R.drawable.profile, 1, "#FFFFFF"));
            /*
                            CSPreferences.putString(activity, "oldAgeTakingCare", response.body().getData().getOldAgeTakingCare());
                            CSPreferences.putString(activity, "oldAgeAdoptionOfLibrary", response.body().getData().getOldAgeAdoptionOfLibrary());
                            CSPreferences.putString(activity, "oldAgePeopleInRuralArea", response.body().getData().getOldAgePeopleInRuralArea());
*/

            if (CSPreferences.readString(this, "oldAgeTakingCare").equalsIgnoreCase("true")) {

                arrayList.add(new OptionDataModel(R.string.planfirst, R.drawable.ic_cane, 2, "#FFFFFF"));
            }

            if (CSPreferences.readString(this, "oldAgeAdoptionOfLibrary").equalsIgnoreCase("true")) {

                arrayList.add(new OptionDataModel(R.string.planfirst, R.drawable.ic_adoption, 3, "#FFFFFF"));
            }

            if (CSPreferences.readString(this, "oldAgePeopleInRuralArea").equalsIgnoreCase("true")) {

                arrayList.add(new OptionDataModel(R.string.planfirst, R.drawable.ic_cane, 4, "#FFFFFF"));
            }
            //  arrayList.add(new OptionDataModel(R.string.viewdailyactivitybystudent, R.drawable.profile, 44, "#FFFFFF"));

            //arrayList.add(new OptionDataModel(R.string.app_name, R.mipmap.ic_launcher, 3, "#FFFFFF"));


        }

        OptionsAdapter adaptermain = new OptionsAdapter(this, arrayList, this, Unverified,
                Verified,
                SchemeRequest);

        recyclerView.setAdapter(adaptermain);


        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/

        /*AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 500);
        recyclerView.setLayoutManager(layoutManager);*/


        /**
         Simple GridLayoutManager that spans two columns
         **/
        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);


        dataModelLeftList = new ArrayList<DataModelLeftNew>();
        dataModelLeftList.clear();

//        adminimagelist.add(new OptionDataModel(R.string.viewdailyactivitybystudent, R.drawable.profile, 44, "#FFFFFF"));
//        adminimagelist.add(new OptionDataModel(R.string.profile, R.drawable.profile, 1, "#FFFFFF"));

        if (CSPreferences.readString(this, "role").equalsIgnoreCase("3")) {
            DataModelLeftNew mydailyactivity = new DataModelLeftNew(R.drawable.ic_baseline_local_activity_24, "My Daily Activity", 44);
            dataModelLeftList.add(mydailyactivity);

//            DataModelLeftNew changescheme = new DataModelLeftNew(R.drawable.ic_baseline_swap_horiz_24, "Change Scheme Request", 444);
//            dataModelLeftList.add(changescheme);

        }
        DataModelLeftNew myprofile = new DataModelLeftNew(R.drawable.personwhite, "My Profile", 55);
        dataModelLeftList.add(myprofile);

        DataModelLeftNew Resetpswd = new DataModelLeftNew(R.drawable.ic_baseline_lock_24, "Reset-My Password", 999);
        dataModelLeftList.add(Resetpswd);


        DataModelLeftNew MyNotification = new DataModelLeftNew(R.drawable.notifications, "My Notification", 0);
        dataModelLeftList.add(MyNotification);

//        DataModelLeftNew nssgallery = new DataModelLeftNew(R.drawable.ic_baseline_photo_album_24, "NSS Gallery", 101);
//        dataModelLeftList.add(nssgallery);

        DataModelLeftNew RateApp = new DataModelLeftNew(R.drawable.rate_review, "Rate App", 1);
        dataModelLeftList.add(RateApp);

        DataModelLeftNew ShareApp = new DataModelLeftNew(R.drawable.share, "Share App", 2);
        dataModelLeftList.add(ShareApp);

        DataModelLeftNew Logout = new DataModelLeftNew(R.drawable.ic_baseline_exit_to_app_24, "Logout", 3);
        dataModelLeftList.add(Logout);

        DataModelLeftNew version = new DataModelLeftNew(R.mipmap.ic_launcher_round, "Version " + getAppVersion(), 21);
        dataModelLeftList.add(version);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, dataModelLeftList);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

    }

    /// get  app version code


    public String getAppVersion() {
        String versionCode = "";
        try {
            versionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return versionCode;
    }

//    public void renewItems() {
//        sliderItemList = new ArrayList<>();
//
//        //dummy data
//        for (int i = 1; i <= 6; i++) {
//            SliderItem sliderItem = new SliderItem();
//            sliderItem.setDescription("Slider Item " + i);
//
//            if (i % 2 == 0) {
//                sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
//            } else {
//                sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
//            }
//            sliderItemList.add(sliderItem);
//        }
//
//
//        sliderAdapter.renewItems(sliderItemList);
//    }
//
//    public void removeLastItem(View view) {
//        if (sliderAdapter.getCount() - 1 >= 0)
//            sliderAdapter.deleteItem(sliderAdapter.getCount() - 1);
//    }
//
//    public void addNewItem(View view) {
//        NoticeboardResponse.NoticeBoard sliderItem = new NoticeboardResponse.NoticeBoard();
//        sliderItem.setDescription("Slider Item Added Manually");
//        sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
//        sliderAdapter.addItem(sliderItem);
//    }

    @Override
    public void initData() {

        mAppUpdateManager = AppUpdateManagerFactory.create(this);

        mAppUpdateManager.registerListener(installStateUpdatedListener);

        mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(appUpdateInfo -> {

            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE /*AppUpdateType.IMMEDIATE*/)) {

                try {
                    mAppUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo, AppUpdateType.IMMEDIATE /*AppUpdateType.IMMEDIATE*/, MainActivity.this, RC_APP_UPDATE);

                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }

            } else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                //CHECK THIS if AppUpdateType.FLEXIBLE, otherwise you can skip
                popupSnackbarForCompleteUpdate();
            } else {
                Log.e(TAG, "checkForAppUpdateAvailability: something else");
            }
        });




    }

    InstallStateUpdatedListener installStateUpdatedListener = new
            InstallStateUpdatedListener() {
                @Override
                public void onStateUpdate(InstallState state) {
                    if (state.installStatus() == InstallStatus.DOWNLOADED) {
                        //CHECK THIS if AppUpdateType.FLEXIBLE, otherwise you can skip
                        popupSnackbarForCompleteUpdate();
                    } else if (state.installStatus() == InstallStatus.INSTALLED) {
                        if (mAppUpdateManager != null) {
                            mAppUpdateManager.unregisterListener(installStateUpdatedListener);
                        }

                    } else {
                        Log.i(TAG, "InstallStateUpdatedListener: state: " + state.installStatus());
                    }
                }
            };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_APP_UPDATE) {
            if (resultCode != RESULT_OK) {
                Log.e(TAG, "onActivityResult: app download failed");
            }
        }
    }


    private void popupSnackbarForCompleteUpdate() {

        Snackbar snackbar =
                Snackbar.make(
                        mDrawerLayout,
                        "New app is ready!",
                        Snackbar.LENGTH_INDEFINITE);

        snackbar.setAction("Install", view -> {
            if (mAppUpdateManager != null) {
                mAppUpdateManager.completeUpdate();
            }
        });


        snackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    public void initListeners() {
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.drawerCheck();
            }
        });


        mainNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainActivity.drawerCheck();
                Intent notification = new Intent(MainActivity.this, NotificationsActivity.class);
                startActivity(notification);
            }
        });


    }

    private void selectItem(int position) {

        Fragment fragment = null;

        switch (dataModelLeftList.get(position).id) {
            case 1:
                mDrawerLayout.closeDrawers();
                // Intent intent = new Intent(this, RoomDBMainActivity.class);
                // startActivity(intent);
                // fragment = new My_chanlel();

                rateApp();
                break;
            case 2:

                mDrawerLayout.closeDrawers();

                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Reach to more user");
               // String app_url = "https://play.google.com/store/apps/details?id=shikshasahyog.hry.edu.govt";
                String app_url = "https://play.google.com/store/apps/details?id=dhe.oldage.orphanagehomes.haryana";
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, app_url);
                startActivity(Intent.createChooser(shareIntent, "Share via"));

                break;
            case 44:

                mDrawerLayout.closeDrawers();


                Intent NodalSchemTypeListActivity = new Intent(this, NodalSchemTypeListActivity.class);
                startActivity(NodalSchemTypeListActivity);
                break;

            case 444:

                mDrawerLayout.closeDrawers();


                Intent schemechange = new Intent(this, SchemeChangeForStudentActivity.class);
                startActivity(schemechange);
                break;

            case 999:

                mDrawerLayout.closeDrawers();
                Intent reset = new Intent(this, ResetPasswordActivity.class);
                startActivity(reset);

                break;

            case 55:

                mDrawerLayout.closeDrawers();
                Intent ProfileActivity = new Intent(this, ProfileActivity.class);
                startActivity(ProfileActivity);
                break;


            case 101:

                mDrawerLayout.closeDrawers();
                Intent nssGallery = new Intent(this, NssGalleryActivity.class);
                startActivity(nssGallery);
                break;

                case 0:

                mDrawerLayout.closeDrawers();
                Intent notification = new Intent(this, NotificationsActivity.class);
                startActivity(notification);
                break;
            case 3:

                mDrawerLayout.closeDrawers();
                logout();
                break;


            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            // setTitle(mNavigationDrawerItemTitles[position]);
            // mDrawerLayout.closeDrawer(mDrawerList);
            mDrawerLayout.closeDrawers();

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    public void rateApp() {
        try {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        } catch (ActivityNotFoundException e) {
            Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details");
            startActivity(rateIntent);
        }
    }

    private Intent rateIntentForUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21) {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        } else {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        //toolbar.setTitle(mTitle);
        //toolbartxt.setText(mTitle.toString());

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    public void shuffle() {
        sliderItemList.clear();
        sliderAdapter.notifyDataSetChanged();

        //  renewItems();

    }

    void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        // hide the title bar  0
        setSupportActionBar(toolbar);
        (getSupportActionBar()).setDisplayShowHomeEnabled(false);
    }

    void setupDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, null, R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    @Override
    public void onItemClick(OptionDataModel item, int currposition) {

     /*   if (item.Id == 1) {
            // Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

            Intent captureplant = new Intent(this, ProfileActivity.class);
            startActivity(captureplant);


        } else*/
        if (item.Id == 2) {

            /*
            CSPreferences.putString(this, "TakeCareStep1", data.getTakeCareStep1());
            CSPreferences.putString(this, "LibrariesAdoptionStep1", data.getLibrariesAdoptionStep1());
            CSPreferences.putString(this, "RuralTakeCareStep1", data.getRuralTakeCareStep1());
*/
            if (CSPreferences.readString(this, "role").equalsIgnoreCase("1") || (CSPreferences.readString(this, "role").equalsIgnoreCase("2"))) {
                // Intent intent = new Intent(this, StudentRecordActivity.class);
                Intent intent = new Intent(this, NodalSchemTypeListActivity.class);
                startActivity(intent);

            } else {


                if (CSPreferences.readString(this, "TakeCareStep1").equalsIgnoreCase("N")) {
                    Intent intent = new Intent(this, OldAgeTakeCareStep1Activity.class);
                    intent.putExtra("schemeId", "1");

                    startActivity(intent);

                } else {


                    Intent intent = new Intent(this, OldAgeTakeCareStep1Activity.class);//OldAgeTakeCareStep1Activity  OldAgeDailyActivitiesActivity
                    intent.putExtra("schemeId", "1");
                    startActivity(intent);
                }
            }

        } else if (item.Id == 3) {


            if (CSPreferences.readString(this, "LibrariesAdoptionStep1").equalsIgnoreCase("N")) {
                Intent intent = new Intent(this, AdoptionOfLibStep1Activity.class);
                intent.putExtra("schemeId", "2");

                startActivity(intent);

            } else {


                Intent intent = new Intent(this, AdoptionOfLibStep1Activity.class);//AdoptionDailyActivitiesActivity
                intent.putExtra("schemeId", "2");

                startActivity(intent);
            }


        } else if (item.Id == 4) {


            if (CSPreferences.readString(this, "RuralTakeCareStep1").equalsIgnoreCase("N")) {
                Intent intent = new Intent(this, RuralOldAgedStep1Activity.class);
                intent.putExtra("schemeId", "3");

                startActivity(intent);

            } else {


                Intent intent = new Intent(this, RuralOldAgedStep1Activity.class);// RuralOldAgedDailyActivitiesActivity
                intent.putExtra("schemeId", "3");

                startActivity(intent);
            }


        } else if (item.Id == 44) {

            // Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

            Intent captureplant = new Intent(this, NodalSchemTypeListActivity.class);//NodalSchemTypeListActivity  ViewSelfDailyReportByStudentActivity
            startActivity(captureplant);

        } else if (item.Id == 202) {

            // Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

            Intent captureplant = new Intent(this, adminDashboardActivity.class);//NodalSchemTypeListActivity  ViewSelfDailyReportByStudentActivity
            startActivity(captureplant);

        } else if (item.Id == 222) {

            // Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

            Intent captureplant = new Intent(this, NodalSchemeTypeListForVerificationActivity.class);//NodalSchemTypeListActivity  ViewSelfDailyReportByStudentActivity
            captureplant.putExtra("studentStatus", "222");
            startActivity(captureplant);

        } else if (item.Id == 333) {

            // Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

            Intent captureplant = new Intent(this, NodalSchemeTypeListForVerificationActivity.class);//NodalSchemTypeListActivity  ViewSelfDailyReportByStudentActivity
            captureplant.putExtra("studentStatus", "333");
            startActivity(captureplant);

        } else if (item.Id == 334) {

            // Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

            Intent captureplant = new Intent(this, SchemeChangeRequestListForNodalOfficerActivity.class);//NodalSchemTypeListActivity  ViewSelfDailyReportByStudentActivity
            captureplant.putExtra("studentStatus", "334");
            startActivity(captureplant);

        }    /*else if (currposition == 5) {

            Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

//            Intent captureplant = new Intent(this, CapturePlantationActivity.class);
//            startActivity(captureplant);

        } else if (currposition == 6) {

            Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

//            Intent captureplant = new Intent(this, CapturePlantationActivity.class);
//            startActivity(captureplant);

        } else if (currposition == 7) {

            Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

//            Intent captureplant = new Intent(this, CapturePlantationActivity.class);
//            startActivity(captureplant);

        } else if (currposition == 8) {

            Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

//            Intent captureplant = new Intent(this, CapturePlantationActivity.class);
//            startActivity(captureplant);

        } else if (currposition == 9) {

            Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

//            Intent captureplant = new Intent(this, CapturePlantationActivity.class);
//            startActivity(captureplant);

        } else if (currposition == 10) {

            Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

            Intent captureplant = new Intent(this, AssignmentListingActivity.class);
            startActivity(captureplant);

        } else if (currposition == 11) {

            // Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

            mDrawerLayout.closeDrawers();
            Intent notification = new Intent(this, GalleryActivity.class);
            startActivity(notification);
        } else if (currposition == 12) {

            // Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

            mDrawerLayout.closeDrawers();
            Intent notification = new Intent(this, ApplyLeavesTypesActivity.class);
            startActivity(notification);
        } */ else {
            Toast.makeText(getApplicationContext(), " Nothing to clicked", Toast.LENGTH_SHORT).show();

        }


    }


    @Override
    public void GetbannersData(List<HomePageResponse.Datum.ResponeDatum> list) {




        sliderItemList.clear();
        sliderAdapter = new SliderAdapter(this);
        sliderItemList.addAll(list);
        sliderAdapter.renewItems(list);
        Unverified = list.get(0).getUnverified();
        Verified = list.get(0).getVerified();
        SchemeRequest = list.get(0).getSchemeRequest();


        //renewItems();

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        // sliderView.setIndicatorSelectedColor(Color.WHITE);
        // sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

        OptionsAdapter adaptermain = new OptionsAdapter(this, arrayList, this, Unverified,
                Verified,
                SchemeRequest);

        recyclerView.setAdapter(adaptermain);


    }

    @Override
    public void onadminimgItemClick(HomePageResponse.Datum.ResponeData1 item, int currposition) {

    }

    @Override
    public void GetadminimageData(List<HomePageResponse.Datum.ResponeData1> list) {

        adminimagelist.clear();
        adminimagelist = new ArrayList();
        adminimagelist.addAll(list);

        AdminImgAdapter adapteradminimage = new AdminImgAdapter(this, (ArrayList) adminimagelist, this);
        rvadminimage.setAdapter(adapteradminimage);
        GridLayoutManager adminimagemanager = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        rvadminimage.setLayoutManager(adminimagemanager);


    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);

        }

    }

    void logout() {
        mDrawerLayout.closeDrawers();
        new FancyGifDialog.Builder(this)
                .setTitle("Log out")
                .setMessage("Are you sure logout from Sauhardam?")
                .setNegativeBtnText("No")
                .setPositiveBtnBackground("#4CAF50")
                .setPositiveBtnText("YES")
                .setNegativeBtnBackground("#FF3D00")
                .setGifResource(R.drawable.logoutgif)   //Pass your Gif here
                .isCancellable(false)
                .OnPositiveClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {

                      /*  if (GlobalClass.isNetworkConnected(MainActivity.this)) {
                            logout(MainActivity.this, CSPreferences.readString(MainActivity.this, "tooken"), CSPreferences.readString(MainActivity.this, "tooken"));

                        } else {

                            Toast.makeText(MainActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                        }*/

                        Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        CSPreferences.clearPref(MainActivity.this);
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        //onBackPressed();
                    }
                })
                .OnNegativeClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
    }

    // LogOut APi................

  /*  public void logout(final Context context, String auth, String tooken) {
        dailogshow(context);
        Call<Logout_model> userpost_responseCall = ApiClient.getClient().app_logout(GlobalClass.apikey, "Bearer " + auth, tooken);
        userpost_responseCall.enqueue(new Callback<Logout_model>() {
            @Override
            public void onResponse(Call<Logout_model> call, Response<Logout_model> response) {
                dailoghide(context);

                if (response.code() == 200) {
                    Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawers();
                    CSPreferences.clearPref(MainActivity.this);
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                } else {
                    GlobalClass.showtost(context, "" + response.message());
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Logout_model> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }*/

    ProgressDialog pd;

    public void dailogshow(Context context) {
        pd = new ProgressDialog(context);
        pd.setMessage("loading...");
        pd.setCancelable(false);
        pd.show();
    }

    public void dailoghide(Context context) {
        pd.dismiss();
    }


}
