package com.berteodosio.seriemesmo.presentation.home.adapter

import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.RecyclerView
import com.berteodosio.seriemesmo.R
import com.berteodosio.seriemesmo.domain.model.Show
import com.berteodosio.seriemesmo.presentation.custom.view.inflate
import com.berteodosio.seriemesmo.presentation.custom.view.loadCenterCrop
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_show_poster.view.*


class HomeShowsAdapter(private val windowManager: WindowManager) : RecyclerView.Adapter<HomeShowsAdapter.ViewHolder>() {

    // TODO: use view model
    private val shows: MutableList<Show> = mutableListOf()
    private val showClickListener: PublishSubject<Show> = PublishSubject.create()

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

    fun addOnShowClickListener(action: (Show) -> Unit) {
        // TODO: manage disposable
        showClickListener.subscribe { action(it) }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(show: Show) {
            dynamicallyCalculateImageViewSize()
            itemView.show_poster?.loadCenterCrop(show.posterUrl)
            itemView.show_poster?.setOnClickListener { showClickListener.onNext(show) }
        }

        private fun dynamicallyCalculateImageViewSize() {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            val screenWidthInPx = displayMetrics.widthPixels

            val imageViewWidth = (screenWidthInPx) / 2f
            val imageViewHeight = imageViewWidth * 1.5f     // keeping the proportion between width and height

            itemView.show_poster?.layoutParams?.width = imageViewWidth.toInt()
            itemView.show_poster?.layoutParams?.height = imageViewHeight.toInt()
            itemView.show_poster?.requestLayout()
            itemView.parent?.requestLayout()
            itemView.parent?.parent?.requestLayout()
        }
    }
}