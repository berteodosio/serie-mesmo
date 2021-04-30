package com.berteodosio.seriemesmo.presentation.seasonDetails.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.domain.model.Episode
import com.berteodosio.seriemesmo.presentation.custom.view.inflate
import kotlinx.android.synthetic.main.item_season_details.view.*

class SeasonDetailsAdapter : RecyclerView.Adapter<SeasonDetailsAdapter.ViewHolder>() {

    private val episodes: MutableList<Episode> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_season_details))
    }

    override fun getItemCount(): Int = episodes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(episodes[position])
    }

    fun addEpisodes(episodes: List<Episode>) {
        this.episodes.addAll(episodes)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(episode: Episode) {
            itemView.episode_number?.text = episode.number.toString()
            itemView.episode_title?.text = episode.name
        }

    }

}