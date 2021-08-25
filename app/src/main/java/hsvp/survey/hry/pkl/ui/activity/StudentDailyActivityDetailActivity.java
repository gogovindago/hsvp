package hsvp.survey.hry.pkl.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.databinding.ActivityStudentDailyDetailBinding;
import hsvp.survey.hry.pkl.utility.BaseActivity;

public class StudentDailyActivityDetailActivity extends BaseActivity {
ActivityStudentDailyDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_student_daily_detail);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_student_daily_detail);

    }

    @Override
    public void initData() {
        binding.toolbar.tvToolbarTitle.setText("Activity Detail");
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