package com.httpurlcon.androidnetworking110kot.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.httpurlcon.androidnetworking110kot.R
import com.httpurlcon.androidnetworking110kot.models.Story
import kotlinx.android.synthetic.main.movies.view.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

class MovieAdapter (var context: Context, var list: List<Story>): RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

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


    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): MovieHolder {
        var v = LayoutInflater.from(context).inflate(R.layout.movies, p0, false)
        return MovieHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val bgColor: Int = ContextCompat.getColor(context,backgroundColors[position % 12])

        holder.imageView.setBackgroundColor(bgColor)

        holder.Title.text = list [position].title
        holder.rating.text = list[position].rating.toString()
        holder.releaseYear.text = list[position].releaseYear.toString()
        holder.genre.text = list[position].genre.toString()

        Glide.with(holder.itemView.context)
                .load(list[position].image)
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                .into(holder.Image)

        holder.itemView.setOnClickListener {
            context.longToast("Position: $position")
        }

    }

    class MovieHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val Title = itemView.tvTitle
        val Image = itemView.tvavatar
        val rating = itemView.tvrating
        val releaseYear = itemView.tvreleaseYear
        val genre = itemView.tvgenre
        val imageView = itemView.imageView6
    }

}