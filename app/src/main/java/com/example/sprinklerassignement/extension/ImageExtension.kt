package com.example.sprinklerassignement.extension

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this.context)
        .load(imageUrl)
        .into(this)
}