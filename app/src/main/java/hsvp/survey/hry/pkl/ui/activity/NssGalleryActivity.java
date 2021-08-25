package hsvp.survey.hry.pkl.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.adapter.NssGalleryAdapter;
import hsvp.survey.hry.pkl.allinterface.GetnssGalleryData_interface;
import hsvp.survey.hry.pkl.apicall.WebAPiCall;

import hsvp.survey.hry.pkl.databinding.ActivityNssGalleryBinding;
import hsvp.survey.hry.pkl.models.NssGalleryResponse;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.CSPreferences;
import hsvp.survey.hry.pkl.utility.GlobalClass;

public class NssGalleryActivity extends BaseActivity implements GetnssGalleryData_interface, NssGalleryAdapter.ItemListener {
    ActivityNssGalleryBinding binding;
    List<NssGalleryResponse.Datum> nssgallerylist = new ArrayList<>();
    GridLayoutManager manager;
    NssGalleryAdapter adapter;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nss_gallery);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nss_gallery);

        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(NssGalleryActivity.this)) {
                    WebAPiCall aPiCall = new WebAPiCall();
                    aPiCall.getNssGalleryDataMethod(NssGalleryActivity.this, NssGalleryActivity.this, "Bearer " + token, binding.llstate, binding.simpleSwipeRefreshLayout, NssGalleryActivity.this);

                } else {
                    Toast.makeText(NssGalleryActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

                }


            }

        });

    }


    @Override
    public void initData() {
        binding.simpleSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        binding.toolbar.tvToolbarTitle.setText("NSS Gallery");

        try {


            token = CSPreferences.readString(this, "token");

        } catch (Exception e) {

            e.printStackTrace();
        }


        if (GlobalClass.isNetworkConnected(NssGalleryActivity.this)) {
            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.getNssGalleryDataMethod(NssGalleryActivity.this, NssGalleryActivity.this, "Bearer " + token, binding.llstate, binding.simpleSwipeRefreshLayout, NssGalleryActivity.this);

        } else {
            Toast.makeText(NssGalleryActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();

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
    public void GetnssgalleryData(List<NssGalleryResponse.Datum> list) {

        binding.recyclerView.setVisibility(View.VISIBLE);


        nssgallerylist.clear();
        nssgallerylist = new ArrayList();
        nssgallerylist.addAll(list);


        manager = new GridLayoutManager(getApplicationContext(), 3, LinearLayoutManager.VERTICAL, false);


        //  manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(manager);

        adapter = new NssGalleryAdapter(this, (ArrayList) nssgallerylist, this);
        binding.recyclerView.setAdapter(adapter);


    }

    @Override
    public void onItemClick(NssGalleryResponse.Datum item, int currposition) {

        openDialog(item);

    }


    public void openDialog(NssGalleryResponse.Datum item) {



        /*Dialog dialog = new Dialog(context, android.R.style.Theme_Light);
dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
dialog.setContentView(R.layout.MyCustomDialogLayout); */
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Light); // Context, this, etc.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_demo);

     //   dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        ImageView dialog_img = (ImageView) dialog.findViewById(R.id.dialog_img);

        Glide.with(this).load(item.getFileName())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(dialog_img);


        Button dialogButton = (Button) dialog.findViewById(R.id.dialog_ok);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }
}

