package com.httpurlcon.androidnetworking108kot.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.httpurlcon.androidnetworking108kot.R
import com.httpurlcon.androidnetworking108kot.model.Payload
import kotlinx.android.synthetic.main.phonebook_items.view.*
import java.text.FieldPosition

class PhonebookAdapter (var context: Context, var list: List<Payload>): RecyclerView.Adapter<PhonebookAdapter.PhonebookHolder>() {

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

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int):PhonebookHolder {
        var v  = LayoutInflater.from(context).inflate(R.layout.phonebook_items, p0,false)
        return PhonebookHolder(v)
    }

    override fun getItemCount(): Int {
            return list.size
    }

    override fun onBindViewHolder(holder: PhonebookHolder, position: Int) {
       val bgColor: Int = ContextCompat.getColor(context,backgroundColors[position % 12])

        holder.imageView.setBackgroundColor(bgColor)

        holder.tvFirstname.text = list[position].firstName
        holder.tvLastname.text = list[position].lastName
        holder.tvDOB.text = list[position].dOB
        holder.tvAddress.text = list[position].address
        holder.tvMobile.text = list[position].phone.mobile
        holder.tvHome.text = list[position].phone.home
        holder.tvOffice.text = list[position].phone.office
        Glide.with(holder.itemView.context)
                .load(list[position].avatar)
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                .into(holder.Avatar)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "Position: $position",Toast.LENGTH_SHORT).show()
        }

    }

    class PhonebookHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tvFirstname = itemView.tvFirstname
        val tvLastname = itemView.tvLastname
        val tvDOB = itemView.tvDatebirth
        val tvAddress = itemView.tvAddress
        val tvMobile = itemView.tvMobile
        val tvHome = itemView.tvHome
        val tvOffice = itemView.tvOffice
        val Avatar = itemView.tvavatar
        val imageView = itemView.imageView6
    }

}