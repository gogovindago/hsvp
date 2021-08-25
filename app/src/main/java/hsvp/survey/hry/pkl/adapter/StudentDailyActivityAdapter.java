package hsvp.survey.hry.pkl.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.models.StudentDailActivityForNodalofficerResponse;
import hsvp.survey.hry.pkl.ui.activity.StudentDailyActivityDetailActivity;

public class StudentDailyActivityAdapter extends RecyclerView.Adapter<StudentDailyActivityAdapter.ViewHolder> implements Filterable {

    ArrayList<StudentDailActivityForNodalofficerResponse.Datum> mValues = new ArrayList<StudentDailActivityForNodalofficerResponse.Datum>();
    ArrayList<StudentDailActivityForNodalofficerResponse.Datum> newmValues = new ArrayList<StudentDailActivityForNodalofficerResponse.Datum>();
    Context mContext;
    protected ItemListener mListener;
    int currposition;
    String Student;

    public StudentDailyActivityAdapter(Context context, ArrayList values, String StudentName, ItemListener itemListener) {

        Student = StudentName;
        mValues = values;
        newmValues = values;
        mContext = context;
        mListener = itemListener;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    newmValues = mValues;
                } else {
                    List<StudentDailActivityForNodalofficerResponse.Datum> filteredList = new ArrayList<>();
                    for (StudentDailActivityForNodalofficerResponse.Datum row : mValues) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getDateOfVisit().toLowerCase().contains(charString.toLowerCase()) || row.getDateOfVisit().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    newmValues = (ArrayList<StudentDailActivityForNodalofficerResponse.Datum>) filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = newmValues;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                newmValues = (ArrayList<StudentDailActivityForNodalofficerResponse.Datum>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public ImageView imageView;
        public RelativeLayout relativeLayout;
        StudentDailActivityForNodalofficerResponse.Datum data;
        public int currposition;
        AppCompatButton btnviewActivity;
        public TextView textView, txtschemeName, txtStudentnamevalue,
                txtstudentvalue, txtMobilevalue,
                txtDistrictvalue, txtCollegevalue, txtCoursevalue,
                texttypeOfVisitvalue, txtSectionvalue, txtSemestervalue,
                txtemailvalue, textdateOfVisitvalue;
        public ImageView profile_image;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            texttypeOfVisitvalue = v.findViewById(R.id.texttypeOfVisitvalue);
            textdateOfVisitvalue = v.findViewById(R.id.textdateOfVisitvalue);
            btnviewActivity = v.findViewById(R.id.btnviewActivity);
            txtschemeName = v.findViewById(R.id.txtschemeName);
            profile_image = v.findViewById(R.id.profile_image);
            txtemailvalue = v.findViewById(R.id.txtemailvalue);
            txtSemestervalue = v.findViewById(R.id.txtSemestervalue);
            // txtSectionvalue = v.findViewById(R.id.txtSectionvalue);
            txtCoursevalue = v.findViewById(R.id.txtCoursevalue);
            txtDistrictvalue = v.findViewById(R.id.txtDistrictvalue);
            txtstudentvalue = v.findViewById(R.id.txtstudentvalue);
            btnviewActivity = v.findViewById(R.id.btnviewActivity);
            txtMobilevalue = v.findViewById(R.id.txtMobilevalue);

            textView = (TextView) v.findViewById(R.id.textView);
            txtStudentnamevalue = (TextView) v.findViewById(R.id.txtStudentnamevalue);
            txtMobilevalue = (TextView) v.findViewById(R.id.txtMobilevalue);
            txtCollegevalue = (TextView) v.findViewById(R.id.txtCollegevalue);

            // textView = (TextView) v.findViewById(R.id.textView);
            //  imageView = (ImageView) v.findViewById(R.id.imageView);
            // relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);

        }

        public void setData(StudentDailActivityForNodalofficerResponse.Datum data, int currposition) {
            this.currposition = currposition;
            this.data = data;

            final StudentDailActivityForNodalofficerResponse.Datum item = newmValues.get(currposition);

            txtStudentnamevalue.setText(data.getStudentName());
            txtstudentvalue.setText(data.getStudentId());
            texttypeOfVisitvalue.setText(data.getTypeOfVisit());
            textdateOfVisitvalue.setText(data.getDateOfVisit());
            txtMobilevalue.setText(data.getProblemFacedByPerson());
            txtemailvalue.setText(data.getSolutionProvidedByStudent());
///            txtMobilevalue.setText(data.getMobile());
//            txtemailvalue.setText(data.getEmail());
//            txtDistrictvalue.setText(data.getDistrict());
//            txtCollegevalue.setText(data.getCollege());
//            txtCoursevalue.setText(data.getCourseName());
//            txtSemestervalue.setText(data.getSemester());
            Glide.with(mContext)
                    .load(item.getFilePath()) // image url
                    .placeholder(R.mipmap.ic_launcher_round) // any placeholder to load at start
                    .error(R.mipmap.ic_launcher_round)  // any image in case of error
                    .override(140, 140) // resizing
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(profile_image);

            // textView.setText(data.text);
            //  imageView.setImageResource(data.drawable);
            //  relativeLayout.setBackgroundColor(Color.parseColor(data.color));

        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(data, currposition);
            }
        }
    }

    @Override
    public StudentDailyActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.student_daily_activity_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        currposition = position;
        holder.setData(mValues.get(position), currposition);

        holder.btnviewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, StudentDailyActivityDetailActivity.class);
                //  intent.putExtra("leaveId", mValues.get(position).getCcLId());
                mContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {

        return newmValues.size();

    }

    public interface ItemListener {
        void onItemClick(StudentDailActivityForNodalofficerResponse.Datum data, int currposition);
    }
}