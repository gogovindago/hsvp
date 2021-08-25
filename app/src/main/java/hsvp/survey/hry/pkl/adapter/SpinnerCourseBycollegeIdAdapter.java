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
import hsvp.survey.hry.pkl.models.CourseByCollegeIDResponse;

public class SpinnerCourseBycollegeIdAdapter extends BaseAdapter {
    Context context;
    private List<CourseByCollegeIDResponse.Datum> districlist = new ArrayList<CourseByCollegeIDResponse.Datum>();

    LayoutInflater inflter;

    public SpinnerCourseBycollegeIdAdapter(Context applicationContext, List<CourseByCollegeIDResponse.Datum> districlist) {
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
       icon.setImageResource(R.drawable.appcolorlibrary_books_24);
        names.setText(districlist.get(i).getCourseName());
        return view;
    }
}