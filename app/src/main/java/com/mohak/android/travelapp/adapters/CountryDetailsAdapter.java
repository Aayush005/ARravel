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
import com.mohak.android.travelapp.models.Country;
import com.mohak.android.travelapp.models.Season;

import java.util.List;

public class CountryDetailsAdapter extends RecyclerView.Adapter<CountryDetailsAdapter.MyViewHolder> {

    private Context context;
    private List<Country> countryList;
    private OnCountryDetailItemClickListener mListener;

    public CountryDetailsAdapter(Context context, List<Country> countryList, OnCountryDetailItemClickListener listener) {
        super();
        this.context = context;
        this.countryList = countryList;
        mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_detail_list, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        myViewHolder.seasonName.setText(countryList.get(i).getCountryName());
        myViewHolder.seasonImage.setImageDrawable(context.getResources().getDrawable(countryList.get(i).getCountryImage()));
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.OnCountryDetailItemClicked(i);
            }
        });


    }

    public interface OnCountryDetailItemClickListener {
        void OnCountryDetailItemClicked(int position);
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView seasonName;
        private ImageView seasonImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            seasonName = itemView.findViewById(R.id.place_name);
            seasonImage = itemView.findViewById(R.id.place_image);
        }
    }

}
