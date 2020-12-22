package com.example.sprinklerassignement.extension

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

fun View.gone(){
    this.isVisible = false
}

fun View.show(){
    this.isVisible = true
}

fun View.invisible(){
    this.isInvisible = true
}