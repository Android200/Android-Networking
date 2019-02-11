package com.httpurlcon.androidnetworking105kot.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.httpurlcon.androidnetworking105kot.R
import com.httpurlcon.androidnetworking105kot.model.Item
import kotlinx.android.synthetic.main.items.view.*
import org.jetbrains.anko.toast




class MyAdapter (var context: Context, var list: List<Item>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

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


    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): MyViewHolder {
        var v = LayoutInflater.from(context).inflate(R.layout.items,p0,false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bgColor:Int = ContextCompat.getColor(context,backgroundColors[position % 12])

        holder.imageview.setBackgroundColor(bgColor)
        holder.tvlogin.text = list.get(position).login
        holder.tvhtml_url.text = list.get(position).htmlUrl
        holder.tvlocation.text = "Kano"
        Glide.with(holder.itemView.context)
                .load(list.get(position).avatarUrl)
                .apply(RequestOptions().placeholder(R.drawable.error))
                .into(holder.tvavatar_url)
        holder.itemView.setOnClickListener {
            //context.toast(list[position].login)
            Toast.makeText(context,"Positon: $position ", Toast.LENGTH_SHORT).show()
        }

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val  tvlogin = itemView.tvlogin
        val tvavatar_url = itemView.tvavatar_url
        val tvhtml_url = itemView.tvhtml_url
        val imageview = itemView.imageView6
        val tvlocation = itemView.tvlocation
    }

}


