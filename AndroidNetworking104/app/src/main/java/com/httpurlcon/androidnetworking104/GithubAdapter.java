package com.httpurlcon.androidnetworking104;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.ViewHolder> {

    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE =  "image";
    public static final String KEY_URL = "url";

    private List<githubmodel> githubmodelList;
    private Context context;
    private final int[] backgroundColors = {R.color.list_color1, R.color.list_color2, R.color.list_color3, R.color.list_color4, R.color.list_color5, R.color.list_color6, R.color.list_color7};

    public GithubAdapter(List<githubmodel> githubmodelList, Context context){
        this.githubmodelList = githubmodelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_json_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
     final githubmodel model = githubmodelList.get(position);
     int bgColor = ContextCompat.getColor(context, backgroundColors[position % 7]);

     holder.linearLayout.setBackgroundColor(bgColor);
     holder.login.setText(model.getLogin());
     holder.html_url.setText(model.getHtml_url());
     holder.location.setText("Kano");
        RequestOptions placeholderRequest = new RequestOptions();
        placeholderRequest.placeholder(R.drawable.error);
        Glide.with(context).setDefaultRequestOptions(placeholderRequest).load(model.getAvatar_url()).into(holder.avatar_url);

     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             githubmodel model1 = githubmodelList.get(position);
             Toast.makeText(v.getContext(),"Position: "+ position, Toast.LENGTH_SHORT).show();
             Intent kill = new Intent(v.getContext(), Details.class);
             kill.putExtra(KEY_NAME, model1.getLogin());
             kill.putExtra(KEY_URL, model1.getHtml_url());
             kill.putExtra(KEY_IMAGE, model1.getAvatar_url());
             v.getContext().startActivity(kill);

         }
     });


    }

    @Override
    public int getItemCount() {
        return githubmodelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView login, html_url, location;
        public ImageView avatar_url;
        LinearLayout linearLayout;

        public ViewHolder(View itemView){
            super(itemView);

            login = itemView.findViewById(R.id.tvLogin);
            html_url = itemView.findViewById(R.id.tvhtml_url);
            location = itemView.findViewById(R.id.tvlocation);
            avatar_url = itemView.findViewById(R.id.ivavatar_url);
            linearLayout = itemView.findViewById(R.id.LinearLayout1);
        }
    }
}
