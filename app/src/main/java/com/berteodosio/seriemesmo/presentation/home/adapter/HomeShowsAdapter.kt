package com.berteodosio.seriemesmo.presentation.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.domain.usecase.model.Show
import com.berteodosio.seriemesmo.presentation.custom.view.inflate
import com.berteodosio.seriemesmo.presentation.custom.view.load
import com.berteodosio.seriemesmo.presentation.custom.view.loadCenterCrop
import kotlinx.android.synthetic.main.item_show_poster.view.*

class HomeShowsAdapter : RecyclerView.Adapter<HomeShowsAdapter.ViewHolder>() {

    // TODO: use view model
    private val shows: MutableList<Show> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_show_poster))
    }

    override fun getItemCount(): Int = shows.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(shows[position])
    }

    fun addShow(show: Show) {
        shows.add(show)
        notifyItemChanged(shows.lastIndex)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(show: Show) {
            itemView.show_poster?.loadCenterCrop(show.posterUrl)
        }
    }
}