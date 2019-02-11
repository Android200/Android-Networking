package com.httpurlcon.androidnetworkin106kot.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.httpurlcon.androidnetworkin106kot.R
import com.httpurlcon.androidnetworkin106kot.model.Tennis
import kotlinx.android.synthetic.main.tennis_items.view.*

class TennisAdapter (var context: Context, var list: List<Tennis>): RecyclerView.Adapter<TennisAdapter.TennisViewHolder>() {

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
    override fun onCreateViewHolder(p0: ViewGroup, pviewType: Int): TennisViewHolder {
        var v = LayoutInflater.from(context).inflate(R.layout.tennis_items,p0,false)
        return TennisViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TennisViewHolder, position: Int) {

        val bgColor: Int = ContextCompat.getColor(context, backgroundColors[position % 12])

        holder.imageView.setBackgroundColor(bgColor)
        holder.tvname.text = list.get(position).name
        holder.tvcountry.text = list.get(position).country
        holder.tvcity.text = list.get(position).city
        Glide.with(holder.itemView.context)
                .load(list.get(position).imgURL)
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                .into(holder.imgURL)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "Position: $position",Toast.LENGTH_SHORT).show()
        }

    }

    class TennisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvname = itemView.tvname
        val tvcountry = itemView.tvcountry
        val tvcity = itemView.tvcity
        val imgURL = itemView.tvavatar
        val imageView = itemView.imageView6
    }


}