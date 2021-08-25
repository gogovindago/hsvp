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
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import hsvp.survey.hry.pkl.R;
import hsvp.survey.hry.pkl.models.NssGalleryResponse;

public class NssGalleryAdapter extends RecyclerView.Adapter<NssGalleryAdapter.ViewHolder> {

    ArrayList<NssGalleryResponse.Datum> mValues = new ArrayList<NssGalleryResponse.Datum>();
    Context mContext;
    protected ItemListener mListener;
    int currposition;

    public NssGalleryAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtName;
        public ImageView imageView;
        NssGalleryResponse.Datum item;
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

        public void setData(NssGalleryResponse.Datum item, int currposition) {
            this.currposition = currposition;
            this.item = item;

           // txtName.setText(item.getDescription());
            Glide.with(mContext).load(item.getFileName())
                    .thumbnail(0.5f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);

//            Glide.with(mContext)
//                    .load(item.getFileName())
//                    .fitCenter()
//                    .into(imageView);


            //  imageView.setImageResource(item.drawable);

        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item, currposition);
            }
        }
    }

    @Override
    public NssGalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_nssgallery_row, parent, false);

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
        void onItemClick(NssGalleryResponse.Datum item, int currposition);
    }
}