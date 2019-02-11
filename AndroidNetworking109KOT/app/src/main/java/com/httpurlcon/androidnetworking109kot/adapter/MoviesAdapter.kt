package com.httpurlcon.androidnetworking109kot.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.httpurlcon.androidnetworking109kot.R
import com.httpurlcon.androidnetworking109kot.models.Movy
import kotlinx.android.synthetic.main.items.view.*

class MoviesAdapter (var context: Context, var list: List<Movy>): RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    private val backgroundColors = intArrayOf(
        R.color.list_color14,
        R.color.list_color13,
        R.color.list_color12,
        R.color.list_color11,
        R.color.list_color10,
        R.color.list_color9,
        R.color.list_color8,
        R.color.list_color7,
        R.color.list_color6,
        R.color.list_color5,
        R.color.list_color4,
        R.color.list_color3,
        R.color.list_color2,
        R.color.list_color1)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MoviesHolder {
        var v = LayoutInflater.from(context).inflate(R.layout.items, viewGroup, false)
        return MoviesHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        val bgColor: Int = ContextCompat.getColor(context, backgroundColors[position % 12])

        holder.imageView.setBackgroundColor(bgColor)

        holder.Name.text = list[position].name
        holder.Timestamp.text = list[position].timestamp
        Glide.with(holder.itemView.context)
            .load(list[position].url.small)
            .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
            .into(holder.Avatar2)

        Glide.with(holder.itemView.context)
            .load(list[position].url.medium)
            .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
            .into(holder.Avatar3)

        Glide.with(holder.itemView.context)
            .load(list[position].url.large)
            .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
            .into(holder.Avatar4)

        holder.Avatar2.setOnClickListener {
            Glide.with(holder.itemView.context)
                .load(list[position].url.small)
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                .into(holder.Avatarholer)

        }

        holder.Avatar3.setOnClickListener {
            Glide.with(holder.itemView.context)
                .load(list[position].url.medium)
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                .into(holder.Avatarholer)

        }

        holder.Avatar4.setOnClickListener {
            Glide.with(holder.itemView.context)
                .load(list[position].url.large)
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                .into(holder.Avatarholer)

        }




    }

    class MoviesHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val Name = itemView.tvname
        val Timestamp = itemView.tvTimestamp
        val Avatarholer = itemView.tvavatar
        val Avatar2 = itemView.tvavatar2
        val Avatar3 = itemView.tvavatar3
        val Avatar4 = itemView.tvavatar4
        val imageView = itemView.imageView6
    }


}