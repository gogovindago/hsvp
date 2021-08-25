package hsvp.survey.hry.pkl.adapter;

import android.content.Context;
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
import hsvp.survey.hry.pkl.models.VerifiedStudentDataResponse;

public class StudentVerifiedListAdapter extends RecyclerView.Adapter<StudentVerifiedListAdapter.ViewHolder> implements Filterable {

    ArrayList<VerifiedStudentDataResponse.Datum> mValues = new ArrayList<VerifiedStudentDataResponse.Datum>();
    List<VerifiedStudentDataResponse.Datum> newmValues = new ArrayList<VerifiedStudentDataResponse.Datum>();

    Context mContext;
    protected ItemListener mListener;
    int currposition;
    String schemeId, title;

    public StudentVerifiedListAdapter(Context context, ArrayList values, String schemeIdfromclass, String titleofitem, ItemListener itemListener) {
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
                    List<VerifiedStudentDataResponse.Datum> filteredList = new ArrayList<>();
                    for (VerifiedStudentDataResponse.Datum row : mValues) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getFirstName().toLowerCase().contains(charString.toLowerCase()) || row.getFirstName().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    newmValues = (ArrayList<VerifiedStudentDataResponse.Datum>) filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = newmValues;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                newmValues = (ArrayList<VerifiedStudentDataResponse.Datum>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView, txtschemeName, txtStudentnamevalue,
                txtstudentvalue, txtMobilevalue, txtDistrictvalue, txtverifiedby, txtverifieddate,
                txtCollegevalue, txtCoursevalue, txtSectionvalue,
                txtSemestervalue, txtemailvalue;
        public ImageView profile_image;
        public RelativeLayout relativeLayout;
        VerifiedStudentDataResponse.Datum data;
        public int currposition;
        AppCompatButton btnviewActivity;

        public ViewHolder(View v) {

            super(v);

            // v.setOnClickListener(this);





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

            txtverifiedby = v.findViewById(R.id.txtverifiedby);
            txtverifieddate = v.findViewById(R.id.txtverifieddate);
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

            btnviewActivity.setOnClickListener(this);


        }

        public void setData(VerifiedStudentDataResponse.Datum data, int currposition) {
            this.currposition = currposition;
            this.data = data;


            final VerifiedStudentDataResponse.Datum item = newmValues.get(currposition);


            try {
                txtschemeName.setText(title);
                txtStudentnamevalue.setText(item.getFirstName() + " " + item.getLastName());
                txtstudentvalue.setText(item.getId());
                txtMobilevalue.setText(item.getMobile());
                txtemailvalue.setText(item.getEmail());
                txtDistrictvalue.setText(item.getDistrict());
                txtCollegevalue.setText(item.getCollege());
                txtCoursevalue.setText(item.getCourseName());
                txtverifiedby.setText(" Verified By :- "+item.getVerifiedBy());
                txtverifieddate.setText(" Verified Date :- "+item.getVerifiedDate());
                txtSemestervalue.setText(String.valueOf(item.getSemester()));
            } catch (Exception e) {
                e.printStackTrace();
            }
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


//            if (mListener != null) {
//                mListener.onItemClick(data, currposition);
//            }


            switch (view.getId()) {

                case R.id.btnviewActivity:

                    if (mListener != null) {

                        mListener.onItemClick(data, currposition);
                    }

                    break;


            }

        }


    }

    @Override
    public StudentVerifiedListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.studentverifiedlist_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        currposition = position;

        holder.setData(mValues.get(position), currposition);


    }


    @Override
    public int getItemCount() {

        return newmValues.size();

    }

    public interface ItemListener {
        void onItemClick(VerifiedStudentDataResponse.Datum item, int currposition);
    }
}