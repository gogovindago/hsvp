package hsvp.survey.hry.pkl.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.adapter.NodalSchemeTypesAdapter;
import hsvp.survey.hry.pkl.databinding.ActivityNodalSchemTypeListBinding;
import hsvp.survey.hry.pkl.models.OptionDataModel;
import hsvp.survey.hry.pkl.utility.BaseActivity;
import hsvp.survey.hry.pkl.utility.CSPreferences;

public class NodalSchemTypeListActivity extends BaseActivity implements NodalSchemeTypesAdapter.ItemListener {

    ActivityNodalSchemTypeListBinding binding;

    ArrayList arrayList;
    GridLayoutManager manager;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nodal_schem_type_list);

        arrayList = new ArrayList();


        if (CSPreferences.readString(this, "oldAgeTakingCare").equalsIgnoreCase("true")) {
            arrayList.add(new OptionDataModel(R.string.planfirst, R.drawable.profile, 1, "#FFFFFF"));
        }


        if (CSPreferences.readString(this, "oldAgeAdoptionOfLibrary").equalsIgnoreCase("true")) {
            arrayList.add(new OptionDataModel(R.string.plansecond, R.drawable.profile, 2, "#FFFFFF"));
        }


        if (CSPreferences.readString(this, "oldAgePeopleInRuralArea").equalsIgnoreCase("true")) {

            arrayList.add(new OptionDataModel(R.string.planthrd, R.drawable.profile, 3, "#FFFFFF"));
        }

        //manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        linearLayoutManager = new LinearLayoutManager(this, GridLayoutManager.VERTICAL, false);

        binding.recyclerView.setLayoutManager(linearLayoutManager);


        NodalSchemeTypesAdapter adaptermain = new NodalSchemeTypesAdapter(this, arrayList, this);
        binding.recyclerView.setAdapter(adaptermain);


    }

    @Override
    public void onItemClick(OptionDataModel item, int currposition) {

        if (item.Id == 1) {

            if (CSPreferences.readString(this, "role").equalsIgnoreCase("3")) {

                Intent intent = new Intent(this, ViewSelfDailyReportByStudentActivity.class);
                intent.putExtra("schemeId", "1");
                intent.putExtra("title", "Taking Care of old Age or Orphans");
                startActivity(intent);

            } else {
                Intent intent = new Intent(this, StudentRecordActivity.class);
                intent.putExtra("schemeId", "1");
                intent.putExtra("title", "Taking Care of old Age or Orphans");

                startActivity(intent);
            }


        } else if (item.Id == 2) {
            if (CSPreferences.readString(this, "role").equalsIgnoreCase("3")) {

                Intent intent = new Intent(this, ViewSelfDailyReportByStudentActivity.class);
                intent.putExtra("schemeId", "2");
                intent.putExtra("title", "Adoption of Lib Old Age or Orphans");

                startActivity(intent);

            } else {
                Intent intent = new Intent(this, StudentRecordActivity.class);
                intent.putExtra("schemeId", "2");
                intent.putExtra("title", "Adoption of Lib Old Age or Orphans");
                startActivity(intent);
            }


        } else if (item.Id == 3) {

            if (CSPreferences.readString(this, "role").equalsIgnoreCase("3")) {

                Intent intent = new Intent(this, ViewSelfDailyReportByStudentActivity.class);
                intent.putExtra("schemeId", "3");
                intent.putExtra("title", "Taking Care of Rural  old Age or Orphans");
                startActivity(intent);

            } else {
                Intent intent = new Intent(this, StudentRecordActivity.class);
                intent.putExtra("schemeId", "3");
                intent.putExtra("title", "Taking Care of Rural  old Age or Orphans");
                startActivity(intent);
            }


        }

    }

    @Override
    public void initData() {

        binding.toolbar.tvToolbarTitle.setText("Scheme Type");

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
}