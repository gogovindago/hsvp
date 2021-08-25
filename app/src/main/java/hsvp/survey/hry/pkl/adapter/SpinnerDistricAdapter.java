package hsvp.survey.hry.pkl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.models.GetDistrictResponse;

public class SpinnerDistricAdapter extends BaseAdapter {
    Context context;
    private List<GetDistrictResponse.Datum> districlist = new ArrayList<GetDistrictResponse.Datum>();

    LayoutInflater inflter;

    public SpinnerDistricAdapter(Context applicationContext, List<GetDistrictResponse.Datum> districlist) {
        this.context = applicationContext;
        this.districlist = districlist;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return districlist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_items, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
       icon.setImageResource(R.drawable.ic_place);
        names.setText(districlist.get(i).getCollegeDistrict());
        return view;
    }
}