package com.httpurlcon.androidnetworking107kot.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.httpurlcon.androidnetworking107kot.R
import com.httpurlcon.androidnetworking107kot.mdel.contacts
import kotlinx.android.synthetic.main.items.view.*

class ContactsAdapter (var context: Context, var list: List<contacts>): RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ContactViewHolder {
        var v = LayoutInflater.from(context).inflate(R.layout.items,p0,false)
        return ContactViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.name.text = list.get(position).name
        holder.email.text = list.get(position).email
        holder.address.text = list.get(position).address
        holder.gender.text = list.get(position).gender
        holder.mobile.text = list.get(position).phone.mobile
        holder.home.text = list.get(position).phone.home
        holder.office.text = list.get(position).phone.office
    }
    class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.name
        val email = itemView.email
        val address = itemView.address
        val gender = itemView.gender
        val mobile  = itemView.mobile
        val home = itemView.home
        val office = itemView.office
    }


}