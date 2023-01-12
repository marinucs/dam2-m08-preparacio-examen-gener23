package com.dam2.examenm08
import android.annotation.SuppressLint
import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.dam2.examenm08.MainActivity.Companion.filmDbHelper

class ListFragment : Fragment() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val v: View = inflater.inflate(R.layout.fragment_filmlist, container, false)
        val mRecyclerView: RecyclerView = v.findViewById(R.id.recyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        val filmList : MutableList<Film> = filmDbHelper.getFilms()

        if (filmList.isNotEmpty()) {
            val mAdapter = RecyclerViewAdapter(filmList, context)
            mRecyclerView.adapter = mAdapter
        }

        return v
    }
}