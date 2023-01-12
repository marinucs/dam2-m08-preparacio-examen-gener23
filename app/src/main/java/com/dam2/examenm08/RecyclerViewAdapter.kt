package com.dam2.examenm08

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private var filmList: MutableList<Film>, var context: Context?): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.filmTitle.text = filmList[position].getTitle()
        holder.filmRating.text = filmList[position].getRating().toString()
        holder.filmCategory.text = filmList[position].getCategory()
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var filmTitle: TextView = itemView.findViewById(R.id.titleTV) as TextView
        var filmRating: TextView = itemView.findViewById(R.id.ratingTV) as TextView
        var filmCategory: TextView = itemView.findViewById(R.id.categoryTV) as TextView

    }
}
