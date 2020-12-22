package com.example.sprinklerassignement

import com.example.sprinklerassignement.base.MvpPresenter
import com.example.sprinklerassignement.base.MvpView
import com.example.sprinklerassignement.data.FILTER_TYPE
import com.example.sprinklerassignement.data.TechProduct

interface MainContract {


    interface View: MvpView {
        fun populateProductsData(data: List<TechProduct>)

        fun handleLoadingView(show: Boolean)
        fun updateUpvotedData(updatedProduct: TechProduct)
        fun updateBookMarkedState(bookmarkProduct: TechProduct)
        fun openFilterPage(filterType: String)
    }

    interface Presenter: MvpPresenter<View> {
        fun fetchProducts(type:FILTER_TYPE = FILTER_TYPE.NONE, updateFilter: Boolean=false)
        fun updateUpvoteCount(techProduct: TechProduct)
        fun markProductBookmarked(techProduct: TechProduct)
        fun checkAndOpenFilterFragment()
    }

}