package com.example.sprinklerassignement.adapter

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sprinklerassignement.ProductActionsListener
import com.example.sprinklerassignement.R
import com.example.sprinklerassignement.data.TechProduct
import com.example.sprinklerassignement.extension.loadImage
import kotlinx.android.synthetic.main.viewholder_tech_products.view.*

class ProductViewHolder(itemView: View, private val mClickListener: ProductActionsListener): RecyclerView.ViewHolder(itemView) {

    private val mProductTitle: TextView = itemView.tv_product_name
    private val mProductDescription: TextView = itemView.tv_description
    private val mProductIcon: ImageView = itemView.iv_product

    private val mBookmark:ImageView = itemView.iv_bookmark
    private val mShare:ImageView = itemView.iv_share


    private val mFounderProfile: TextView = itemView.tv_founder_profile
    private val mUpvote: ImageView = itemView.iv_upvote
    private val mLinearLayoutUpvote: LinearLayout = itemView.ll_upvote
    private val mUpvoteCount : TextView = itemView.tv_upvote_count
    private val mFounderName: TextView = itemView.tv_founder_profile

    fun setData(techProduct: TechProduct) {
        mProductTitle.text = techProduct.title
        mProductDescription.text = techProduct.description
        techProduct.productImage?.let {
            mProductIcon.loadImage(it)
        }
        mFounderName.text = techProduct.founderName



        toggleUpvote(techProduct.isUpvoted, techProduct.upvoteCount)
        toggleBookMark(techProduct.isBookmarked)

        mLinearLayoutUpvote.setOnClickListener {
            mClickListener.onUpvoteClicked(techProduct)
        }

        mBookmark.setOnClickListener {
            mClickListener.onBookmarkClicked(techProduct)
        }

        mShare.setOnClickListener {
            mClickListener.onShareClicked(techProduct)
        }

        mFounderName.setOnClickListener {
            techProduct.profileLink?.let {
                mClickListener.onLinkClicked(it)
            }
        }


    }

    fun toggleBookMark(isBookMarked : Boolean) {
        if(isBookMarked) {
            mBookmark.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_bookmark_done))
        } else {
            mBookmark.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_bookmark))
        }
    }

    fun toggleUpvote(isUpvoted: Boolean, count:Int) {
        mUpvoteCount.text = count.toString()
        if(isUpvoted) {
            mUpvote.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_upvoted_state))
        } else {
            mUpvote.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_upvote))
        }
    }

}