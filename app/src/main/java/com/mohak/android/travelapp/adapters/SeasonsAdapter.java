package com.mohak.android.travelapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohak.android.travelapp.R;
import com.mohak.android.travelapp.models.Season;

import java.util.List;

public class SeasonsAdapter extends RecyclerView.Adapter<SeasonsAdapter.MyViewHolder> {

    private Context context;
    private int NO_OF_ITEMS;
    private List<Season> seasonList;
    private OnSeasonedItemClickListener mListener;

    public SeasonsAdapter(Context context, int NUMBR_OF_ITEMS, List<Season> seasonsList, OnSeasonedItemClickListener listener) {
        super();
        this.context = context;
        seasonList = seasonsList;
        NO_OF_ITEMS = NUMBR_OF_ITEMS;
        mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.season_list_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {



            myViewHolder.seasonName.setText(seasonList.get(i).getSeasonName());
            myViewHolder.seasonImage.setImageDrawable(context.getResources().getDrawable(seasonList.get(i).getSeasonImage()));
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.OnSeasonedItemClicked(i);
                }
            });


    }

    public interface OnSeasonedItemClickListener {
        void OnSeasonedItemClicked(int position);
    }

    @Override
    public int getItemCount() {
        return NO_OF_ITEMS;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView seasonName;
        private ImageView seasonImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            seasonName = itemView.findViewById(R.id.tvSeasonName);
            seasonImage = itemView.findViewById(R.id.appCompatImageView);
        }
    }

}
