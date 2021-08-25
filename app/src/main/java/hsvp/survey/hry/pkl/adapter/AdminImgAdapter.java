package hsvp.survey.hry.pkl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.models.HomePageResponse;

public class AdminImgAdapter extends RecyclerView.Adapter<AdminImgAdapter.ViewHolder> {

    ArrayList<HomePageResponse.Datum.ResponeData1> mValues = new ArrayList<HomePageResponse.Datum.ResponeData1>();
    Context mContext;
    protected ItemListener mListener;
    int currposition;

    public AdminImgAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtName;
        public ImageView imageView;
        HomePageResponse.Datum.ResponeData1 item;
        LinearLayout llmain;
        public int currposition;
        CardView maincard;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            maincard =  v.findViewById(R.id.maincard);
            llmain =  v.findViewById(R.id.llmain);
            txtName = (TextView) v.findViewById(R.id.txtName);
            imageView = (ImageView) v.findViewById(R.id.ivThumb);


        }

        public void setData(HomePageResponse.Datum.ResponeData1 item, int currposition) {
            this.currposition = currposition;
            this.item = item;
            txtName.setText(item.getDescription());
            if (item.getSrNo() == 1) {
                maincard.setBackgroundResource(R.drawable.edit_text_borderdash);

                Glide.with(itemView)
                        .load(item.getImagePath())
                        .fitCenter()
                        .into(imageView);

               // imageView.setImageResource(item.drawable);

            } else if (item.getSrNo() == 2) {
                Glide.with(itemView)
                        .load(item.getImagePath())
                        .fitCenter()
                        .into(imageView);
              //  imageView.setImageResource(item.drawable);

            } else if (item.getSrNo() == 3) {


                Glide.with(itemView)
                        .load(item.getImagePath())
                        .fitCenter()
                        .into(imageView);
              //  imageView.setImageResource(item.drawable);

            } else if (item.getSrNo() == 4) {

                Glide.with(itemView)
                        .load(item.getImagePath())
                        .fitCenter()
                        .into(imageView);
               // imageView.setImageResource(item.drawable);

            }
            //  imageView.setImageResource(item.drawable);

        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onadminimgItemClick(item, currposition);
            }
        }
    }

    @Override
    public AdminImgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_itemadminimg_row, parent, false);

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
        void onadminimgItemClick(HomePageResponse.Datum.ResponeData1 item, int currposition);
    }
}