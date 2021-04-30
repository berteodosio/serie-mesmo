package com.berteodosio.seriemesmo.presentation.showDetails.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.domain.model.Season
import com.berteodosio.seriemesmo.presentation.custom.view.inflate
import com.berteodosio.seriemesmo.presentation.custom.view.loadCenterCrop
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_season_poster.view.*

class ShowDetailsSeasonsAdapter : RecyclerView.Adapter<ShowDetailsSeasonsAdapter.ViewHolder>() {

    private val seasons: MutableList<Season> = mutableListOf()
    private val seasonClickListener: PublishSubject<Season> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_season_poster))
    }

    override fun getItemCount(): Int = seasons.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(seasons[position])
    }

    fun addSeason(season: Season) {
        seasons.add(season)
        notifyItemChanged(seasons.lastIndex)
    }

    fun addSeasons(seasons: List<Season>) {
        this.seasons.addAll(seasons)
        notifyDataSetChanged()
    }

    fun addOnClickListener(action: (Season) -> Unit) {
        seasonClickListener.subscribe(action)           // TODO: handle disposable
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(season: Season) {
            itemView.season_poster?.loadCenterCrop(season.posterUrl)
            itemView.season_title?.text = season.name
            itemView.season_episodes_count?.text = mountEpisodesCount(season.episodeCount)
            itemView.season_card?.setOnClickListener { seasonClickListener.onNext(season) }
        }

        private fun mountEpisodesCount(episodeCount: Long): String {
            if (episodeCount == 1L) {
                return "1 episode"
            }

            // TODO REFACTOR USE STRINGS FILE
            return "$episodeCount episodes"
        }

    }
}