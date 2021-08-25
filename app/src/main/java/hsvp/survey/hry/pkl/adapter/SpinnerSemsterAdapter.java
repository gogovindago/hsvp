package hsvp.survey.hry.pkl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.models.DummyData;

public class SpinnerSemsterAdapter extends BaseAdapter {
    Context context;
    int flags[];
    ArrayList<DummyData> courseYear;

    LayoutInflater inflter;

    public SpinnerSemsterAdapter(Context applicationContext, ArrayList<DummyData> courseYear) {
        this.context = applicationContext;
        this.flags = flags;
        this.courseYear = courseYear;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return courseYear.size();
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
        icon.setImageResource(courseYear.get(i).getImage());
        names.setText(courseYear.get(i).getName());
        return view;
    }
}