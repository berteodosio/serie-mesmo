package com.berteodosio.seriemesmo.presentation.custom.view

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

private fun ImageView.glideLoad(url: String) = Glide.with(this.context)
    .load(url)

private fun RequestBuilder<Drawable>.applyCenterCrop() = this.apply(RequestOptions().centerCrop())

fun ImageView.loadCenterCrop(url: String) {
    glideLoad(url)
        .applyCenterCrop()
        .into(this)
}

fun ImageView.loadCenterCropCrossFade(url: String) {
    glideLoad(url)
        .applyCenterCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}