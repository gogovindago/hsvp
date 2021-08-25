package hsvp.survey.hry.pkl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.models.OptionDataModel;


public class NodalSchemeTypesAdapter extends RecyclerView.Adapter<NodalSchemeTypesAdapter.ViewHolder> {

    //ArrayList<DataModel> mValues = new ArrayList<DataModel>();
    private List<OptionDataModel> mValues = new ArrayList<OptionDataModel>();


    Context mContext;
    protected ItemListener mListener;
    public int currposition;

    public NodalSchemeTypesAdapter(Context context, List<OptionDataModel> values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public int currposition;
        public TextView textView, text;
        public ImageView imageView, leavelock, forward;
        public LinearLayout ll;
        OptionDataModel item;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.title);
            text = (TextView) v.findViewById(R.id.text);
            imageView = (ImageView) v.findViewById(R.id.image);
            leavelock = (ImageView) v.findViewById(R.id.leavelock);
            forward = (ImageView) v.findViewById(R.id.forward);
            ll = (LinearLayout) v.findViewById(R.id.ll);

        }

        public void setData(OptionDataModel item, int currposition) {
            this.item = item;
            this.currposition = currposition;
            textView.setText(item.text);
            //  if (item.getLeaveTypeId() == 1) {
            ll.setBackgroundResource(R.color.colorPrimaryDark);
            forward.setVisibility(View.VISIBLE);
           // leavelock.setVisibility(View.GONE);

//            } else {
//
//                leavelock.setVisibility(View.VISIBLE);
//            }

            /// imageView.setImageResource(item.drawable);
            // ll.setBackgroundColor(Color.parseColor(item.color));

        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item, currposition);
            }
        }
    }

    @Override
    public NodalSchemeTypesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.viewertypesofleave_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        currposition = position;
        holder.setData(mValues.get(position), currposition);

    }


    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(OptionDataModel item, int currposition);
    }
}