package com.mohak.android.travelapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohak.android.travelapp.DetailActivity;
import com.mohak.android.travelapp.FlowerData;
import com.mohak.android.travelapp.R;
import com.mohak.android.travelapp.models.Country;

import java.util.List;

/**
 * Created by abdalla on 1/12/18.
 */

public class MyAdapter extends RecyclerView.Adapter<FlowerViewHolder> {

    private Context mContext;
    private List<Country> countryList;
    private OnCountryItemSelectedListener mListener;

    public MyAdapter(Context mContext, List<Country> countryList, OnCountryItemSelectedListener listener) {
        this.mContext = mContext;
        this.countryList = countryList;
        mListener = listener;
    }

    @Override
    public FlowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.countries_list_recyclerview, parent, false);
        return new FlowerViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final FlowerViewHolder holder, final int position) {

        holder.mImage.setImageDrawable(mContext.getResources().getDrawable(countryList.get(position).getCountryImage()));
        holder.mTitle.setText(countryList.get(position).getCountryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.OnCountryItemClicked(position);
            }
        });


//        holder.mImage.setImageResource(mFlowerList.get(position).getFlowerImage());
//        holder.mTitle.setText(mFlowerList.get(position).getFlowerName());
//        holder.mCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent mIntent = new Intent(mContext, DetailActivity.class);
//                mIntent.putExtra("Title", mFlowerList.get(holder.getAdapterPosition()).getFlowerName());
//                mIntent.putExtra("Description", mFlowerList.get(holder.getAdapterPosition()).getFlowerDescription());
//                mIntent.putExtra("Image", mFlowerList.get(holder.getAdapterPosition()).getFlowerImage());
//                mContext.startActivity(mIntent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public interface OnCountryItemSelectedListener {
        void OnCountryItemClicked(int position);
    }
}

class FlowerViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle;

    FlowerViewHolder(View itemView) {
        super(itemView);

        mImage = itemView.findViewById(R.id.iv_country_image);
        mTitle = itemView.findViewById(R.id.tv_country_name);
//        mCardView = itemView.findViewById(R.id.cardview);
    }
}
