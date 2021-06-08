package com.example.coolcoin.util.extensions

import android.content.res.ColorStateList
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

import com.bumptech.glide.Glide
import com.example.coolcoin.R

@BindingAdapter("bind:isVisible")
fun View.isVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("bind:imageUrl")
fun ImageView.imageUrl(imageUrl: String?) {
    Glide.with(context)
        .load(imageUrl)
        .placeholder(R.color.grey)
        .error(R.color.grey)
        .into(this)
}

@BindingAdapter("bind:tint")
fun ImageView.tint(@ColorRes colorRes: Int?) {
    colorRes ?: return
    this.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(context, colorRes))
}
