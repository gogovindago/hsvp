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
import hsvp.survey.hry.pkl.models.StudentListUnderNodalOfficerResponse;
import hsvp.survey.hry.pkl.ui.activity.StudentDailyActivityListActivity;

public class StudentRecordAdapter extends RecyclerView.Adapter<StudentRecordAdapter.ViewHolder> implements Filterable {

    ArrayList<StudentListUnderNodalOfficerResponse.Datum> mValues = new ArrayList<StudentListUnderNodalOfficerResponse.Datum>();
    List<StudentListUnderNodalOfficerResponse.Datum> newmValues = new ArrayList<StudentListUnderNodalOfficerResponse.Datum>();

    Context mContext;
    protected ItemListener mListener;
    int currposition;
    String schemeId, title;

    public StudentRecordAdapter(Context context, ArrayList values, String schemeIdfromclass, String titleofitem, ItemListener itemListener) {
        title = titleofitem;
        schemeId = schemeIdfromclass;
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
                    List<StudentListUnderNodalOfficerResponse.Datum> filteredList = new ArrayList<>();
                    for (StudentListUnderNodalOfficerResponse.Datum row : mValues) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getFirstname().toLowerCase().contains(charString.toLowerCase()) || row.getFirstname().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    newmValues = (ArrayList<StudentListUnderNodalOfficerResponse.Datum>) filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = newmValues;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                newmValues = (ArrayList<StudentListUnderNodalOfficerResponse.Datum>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView, txtschemeName, txtStudentnamevalue, txtstudentvalue, txtMobilevalue, txtDistrictvalue, txtCollegevalue, txtCoursevalue, txtSectionvalue, txtSemestervalue, txtemailvalue;
        public ImageView profile_image;
        public RelativeLayout relativeLayout;
        StudentListUnderNodalOfficerResponse.Datum data;
        public int currposition;
        AppCompatButton btnviewActivity;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);





            /*"studentid": "219",
            "firstname": "Testing",
            "lastname": "kumar",
            "mobile": "7004280425",
            "email": "go@gmail.com",
            "district": "Bhiwani",
            "college": "GC Bhiwani",
            "course_name": "Bachelor of Science (B.Sc.) - Non Medical",
            "semester": "1"
        }*/
            txtschemeName = v.findViewById(R.id.txtschemeName);
            profile_image = v.findViewById(R.id.profile_image);
            txtemailvalue = v.findViewById(R.id.txtemailvalue);
            txtSemestervalue = v.findViewById(R.id.txtSemestervalue);
            //  txtSectionvalue = v.findViewById(R.id.txtSectionvalue);
            txtCoursevalue = v.findViewById(R.id.txtCoursevalue);
            txtDistrictvalue = v.findViewById(R.id.txtDistrictvalue);
            txtstudentvalue = v.findViewById(R.id.txtstudentvalue);
            btnviewActivity = v.findViewById(R.id.btnviewActivity);
            txtMobilevalue = v.findViewById(R.id.txtMobilevalue);

            textView = (TextView) v.findViewById(R.id.textView);
            txtStudentnamevalue = (TextView) v.findViewById(R.id.txtStudentnamevalue);
            txtMobilevalue = (TextView) v.findViewById(R.id.txtMobilevalue);
            txtCollegevalue = (TextView) v.findViewById(R.id.txtCollegevalue);


            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);

        }

        public void setData(StudentListUnderNodalOfficerResponse.Datum data, int currposition) {
            this.currposition = currposition;
            this.data = data;



            final StudentListUnderNodalOfficerResponse.Datum item = newmValues.get(currposition);

            txtschemeName.setText(title);
            txtStudentnamevalue.setText(item.getFirstname() + " " + item.getLastname());
            txtstudentvalue.setText(item.getStudentid());
            txtMobilevalue.setText(item.getMobile());
            txtemailvalue.setText(item.getEmail());
            txtDistrictvalue.setText(item.getDistrict());
            txtCollegevalue.setText(item.getCollege());
            txtCoursevalue.setText(item.getCourseName());
            txtSemestervalue.setText(item.getSemester());
            Glide.with(mContext)
                    .load(item.getFilePath()) // image url
                    .placeholder(R.mipmap.ic_launcher_round) // any placeholder to load at start
                    .error(R.mipmap.ic_launcher_round)  // any image in case of error
                    .override(140, 140) // resizing
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(profile_image);

            // textView.setText(item.text);
            //  imageView.setImageResource(item.drawable);
            //  relativeLayout.setBackgroundColor(Color.parseColor(item.color));

        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(data, currposition);
            }
        }
    }

    @Override
    public StudentRecordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.student_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        currposition = position;
        holder.setData(mValues.get(position), currposition);

        holder.btnviewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, StudentDailyActivityListActivity.class);
                intent.putExtra("studentId", mValues.get(position).getStudentid());
                intent.putExtra("schemeId", schemeId);
                try {
                    intent.putExtra("StudentName", mValues.get(position).getFirstname()+mValues.get(position).getLastname());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                mContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {

        return newmValues.size();

    }

    public interface ItemListener {
        void onItemClick(StudentListUnderNodalOfficerResponse.Datum item, int currposition);
    }
}