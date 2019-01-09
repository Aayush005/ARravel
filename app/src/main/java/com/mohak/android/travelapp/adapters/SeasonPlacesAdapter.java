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
import com.mohak.android.travelapp.models.SeasonPlace;

import java.util.List;

public class SeasonPlacesAdapter extends RecyclerView.Adapter<SeasonPlacesAdapter.MyViewHolder> {

    private Context context;
    private List<SeasonPlace> seasonPlaceList;
    private OnSeasonedPlaceItemClickListener mListener;

    public SeasonPlacesAdapter(Context context, List<SeasonPlace> seasonPlaceList, OnSeasonedPlaceItemClickListener listener) {
        super();
        this.context = context;
        this.seasonPlaceList = seasonPlaceList;
        mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.season_place_list_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
//        myViewHolder.seasonName.setText(seasonList.get(i).getSeasonName());
//        myViewHolder.seasonImage.setImageDrawable(context.getResources().getDrawable(seasonList.get(i).getSeasonImage()));
        myViewHolder.countryFlag.setImageDrawable(context.getResources().getDrawable(seasonPlaceList.get(i).getCountryFlag()));
        myViewHolder.countryImage.setImageDrawable(context.getResources().getDrawable(seasonPlaceList.get(i).getCountryImage()));
        myViewHolder.countryName.setText(seasonPlaceList.get(i).getCountryName());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.OnSeasonedPlaceItemClicked(i);
            }
        });

    }

    public interface OnSeasonedPlaceItemClickListener {
        void OnSeasonedPlaceItemClicked(int position);
    }

    @Override
    public int getItemCount() {
        return seasonPlaceList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView countryName;
        private ImageView countryImage;
        private ImageView countryFlag;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           countryName = itemView.findViewById(R.id.country_name);
            countryImage = itemView.findViewById(R.id.country_image);
            countryFlag = itemView.findViewById(R.id.country_flag);
        }
    }

}

