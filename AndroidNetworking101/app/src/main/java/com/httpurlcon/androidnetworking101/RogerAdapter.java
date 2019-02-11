package com.httpurlcon.androidnetworking101;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class RogerAdapter extends RecyclerView.Adapter<RogerAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<RogerModel> rogerModelArrayList;

    public RogerAdapter(Context ctx, ArrayList<RogerModel> rogerModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.rogerModelArrayList = rogerModelArrayList;
    }

    @Override
    public RogerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rv_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    //onBindViewHolder() method from the above class, set the information in imageview and textviews using rogerModelArrayList.
    @Override
    public void onBindViewHolder(RogerAdapter.MyViewHolder holder, int position) {

        Picasso.get().load(rogerModelArrayList.get(position).getImgURL()).into(holder.iv);
        holder.name.setText(rogerModelArrayList.get(position).getName());
        holder.country.setText(rogerModelArrayList.get(position).getCountry());
        holder.city.setText(rogerModelArrayList.get(position).getCity());
    }

    @Override
    public int getItemCount() {
        return rogerModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView country, name, city;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);

            country = (TextView) itemView.findViewById(R.id.country);
            name = (TextView) itemView.findViewById(R.id.name);
            city = (TextView) itemView.findViewById(R.id.city);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }

    }

}
