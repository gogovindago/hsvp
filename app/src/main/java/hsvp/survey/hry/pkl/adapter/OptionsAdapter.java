package hsvp.survey.hry.pkl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.models.OptionDataModel;
import hsvp.survey.hry.pkl.utility.CSPreferences;

import static hsvp.survey.hry.pkl.R.*;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.ViewHolder> {

    ArrayList<OptionDataModel> mValues = new ArrayList<OptionDataModel>();
    Context mContext;
    protected ItemListener mListener;
    int currposition;
    String Unverified, Verified, SchemeRequest;

    public OptionsAdapter(Context context, ArrayList values, ItemListener itemListener, String unverified, String verified, String schemeRequest) {
        Unverified = unverified;
        Verified = verified;
        SchemeRequest = schemeRequest;
        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView, txtnumber;
        public ImageView imageView;
        public RelativeLayout relativeLayout;
        OptionDataModel item;
        public int currposition;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            txtnumber = (TextView) v.findViewById(id.txtnumber);
            textView = (TextView) v.findViewById(id.textView);
            imageView = (ImageView) v.findViewById(id.imageView);
            relativeLayout = (RelativeLayout) v.findViewById(id.relativeLayout);

        }


        public void setData(OptionDataModel item, int currposition) {

            this.currposition = currposition;
            this.item = item;

            if (!CSPreferences.readString(mContext, "role").equalsIgnoreCase("3")) {
                if (currposition == 1) {
                    if (Unverified.equalsIgnoreCase("0")) {

                    } else {
                        txtnumber.setVisibility(View.VISIBLE);
                        txtnumber.setTextColor(mContext.getResources().getColor(R.color.reddark));
                        txtnumber.setText("Pending ( " + Unverified + " )");
                    }

                }

                if (currposition == 2) {
                    if (Verified.equalsIgnoreCase("0")) {


                    } else {
                        txtnumber.setVisibility(View.VISIBLE);
                        txtnumber.setTextColor(mContext.getResources().getColor(R.color.drkgreeenn));

                        txtnumber.setText("( " + Verified + " )");
                    }
                }

                if (currposition == 3) {

                    if (SchemeRequest.equalsIgnoreCase("0")) {

                    } else {
                        txtnumber.setVisibility(View.VISIBLE);
                        txtnumber.setTextColor(mContext.getResources().getColor(R.color.reddark));
                        txtnumber.setText("Pending ( " + SchemeRequest + " )");
                    }

                }
            }

            textView.setText(item.text);
            imageView.setImageResource(item.drawable);
           // relativeLayout.setBackgroundColor(Color.parseColor(item.color));

        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item, currposition);
            }
        }
    }

    @Override
    public OptionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(layout.recycler_view_itemmain, parent, false);

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