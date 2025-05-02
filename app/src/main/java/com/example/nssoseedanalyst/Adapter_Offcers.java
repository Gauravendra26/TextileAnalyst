package com.example.nssoseedanalyst;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_Offcers extends RecyclerView.Adapter<Adapter_Offcers.MyViewHolder> {


    private Context context;
    private List<Model_Officers> liveList;

    private ProductClick productClick;

    //make interface like this
    public interface  ProductClick{


        void officersClick(int position,int seed_analyst_seed_office_map_id, int seedanalyst_id, int seedofficer_id,
                           int user_id, int user_role, int user_status, String user_name, String user_phone,
                           String email, String user_empid, String user_image, String created_at, String updated_at);

     }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName,tvID;

        RelativeLayout rlView;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvID = view.findViewById(R.id.tvID);
            rlView = view.findViewById(R.id.rlView);


        }
    }

    public Adapter_Offcers(Context context, List<Model_Officers> liveList) {
        //List<SiderImageModel> slider_image_list
        this.context = context;
        this.liveList = liveList;
        ////this.slider_image_list = slider_image_list;

    }
    public void setData(List<Model_Officers> newData) {
        this.liveList = newData;
        // Save data to cache
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {

        return liveList.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_officers, parent, false);
        return new MyViewHolder(itemView);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Model_Officers live = liveList.get(position);

        holder.tvName.setText(live.getUser_name());
        holder.tvID.setText(live.getUser_empid());

        holder.rlView.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                productClick.officersClick(position,live.getSeed_analyst_seed_office_map_id(),
                        live.getSeedanalyst_id(),live.getSeedofficer_id(), live.getUser_id(),live.getUser_role(),
                        live.getUser_status(),live.getUser_name(),live.getUser_phone(),live.getEmail(),
                        live.getUser_empid(),live.getUser_image(),live.getCreated_at(),live.getUpdated_at());
                final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                holder.rlView.startAnimation(myAnim);

            }
        });


    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    public void set(ProductClick onClick)
    {
        this.productClick = onClick;
    }

}
