package com.example.sprinklerassignement.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sprinklerassignement.ProductActionsListener
import com.example.sprinklerassignement.R
import com.example.sprinklerassignement.data.TechProduct
import java.lang.Exception

class ProductAdapter(private val mClickListener: ProductActionsListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mTechProduct: ArrayList<TechProduct> = arrayListOf()

    private val TYPE_PRODUCT_VIEWHOLDER = 1

    private val KEY_UPVOTE_PAYLOAD = "KEY_UPVOTE_PAYLOAD"
    private val KEY_BOOKMARK_PRODUCT = "KEY_BOOKMARK_PRODUCT"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_PRODUCT_VIEWHOLDER -> ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_tech_products, parent, false), mClickListener)
            else -> {
                throw Exception("unknown view holder")
            }
        }

    }

    override fun getItemCount(): Int {
        return mTechProduct.size
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_PRODUCT_VIEWHOLDER
    }

    fun addToBottom(list: List<TechProduct>) {
        val startPos = list.size
        mTechProduct.addAll(list)
        notifyItemRangeInserted(startPos, list.size)
    }

    fun clearAdapter() {
        mTechProduct.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductViewHolder) {
            holder.setData(mTechProduct[position])
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
       if(payloads.isNotEmpty()) {
           for (payload in payloads) {
               when(payload) {
                   KEY_UPVOTE_PAYLOAD -> {
                       (holder as? ProductViewHolder)?.toggleUpvote(mTechProduct[position].isUpvoted, mTechProduct[position].upvoteCount)
                   }
                   KEY_BOOKMARK_PRODUCT -> {
                       (holder as? ProductViewHolder)?.toggleBookMark(mTechProduct[position].isBookmarked)
                   }
               }
           }
       }else {
           super.onBindViewHolder(holder, position, payloads)
       }
    }

    fun updateUpvoteData(updatedProduct: TechProduct) {
        val index =  mTechProduct.indexOfFirst{ updatedProduct.productId == it.productId}
        if(index != -1) {
            mTechProduct[index] = updatedProduct
            notifyItemChanged(index, KEY_UPVOTE_PAYLOAD)
        }
    }

    fun updateBookMarkState(bookmarkProduct: TechProduct) {
        val index =  mTechProduct.indexOfFirst{ bookmarkProduct.productId == it.productId}
        if(index != -1) {
            mTechProduct[index] = bookmarkProduct
            notifyItemChanged(index, KEY_BOOKMARK_PRODUCT)
        }
    }

}