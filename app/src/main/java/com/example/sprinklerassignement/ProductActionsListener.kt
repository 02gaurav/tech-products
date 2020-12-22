package com.example.sprinklerassignement

import com.example.sprinklerassignement.data.TechProduct

interface ProductActionsListener {

    fun onUpvoteClicked(techProduct: TechProduct)

    fun onShareClicked(techProduct: TechProduct)

    fun onBookmarkClicked(techProduct: TechProduct)
    fun onLinkClicked(profileLink: String)
}