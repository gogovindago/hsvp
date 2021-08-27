package hsvp.survey.hry.pkl.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.databinding.ActivitySurveyBinding;
import hsvp.survey.hry.pkl.utility.BaseActivity;

public class SurveyActivity extends BaseActivity {
    ActivitySurveyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_survey);

    }

    @Override
    public void initData() {
        binding.toolbar.tvToolbarTitle.setText("Property Detail");

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