package com.example.sprinklerassignement

import android.util.Log
import com.example.sprinklerassignement.base.BasePresenter
import com.example.sprinklerassignement.data.FILTER_TYPE
import com.example.sprinklerassignement.data.TechProduct
import com.example.sprinklerassignement.prefs.GlobalPref
import com.example.sprinklerassignement.repository.TechProductRepository
import com.example.sprinklerassignement.schedulers.SchedulerProvider
import javax.inject.Inject

class MainPresenter
@Inject
constructor(private val mSchedulerProvider: SchedulerProvider,
            private val mTechRepository: TechProductRepository,
            private val mGlobalPref: GlobalPref): BasePresenter<MainContract.View>(), MainContract.Presenter {



    override fun fetchProducts(filterType: FILTER_TYPE, updateFilter:Boolean) {

        val updatedFilter:FILTER_TYPE = if(updateFilter) {
            mGlobalPref.currentFilter = filterType.name
            filterType
        } else {
            FILTER_TYPE.getFilterByValue(mGlobalPref.currentFilter)
        }
        mCompositeDisposable.add(mTechRepository.fetchProducts(updatedFilter)
            .doOnSubscribe {
                mView?.handleLoadingView(true)
            }
            .subscribeOn(mSchedulerProvider.io())
            .observeOn(mSchedulerProvider.ui())
            .subscribe({
                mView?.handleLoadingView(false)
                mView?.populateProductsData(it)
            },{
                it.printStackTrace()
            }))
    }

    override fun updateUpvoteCount(techProduct: TechProduct) {
        mCompositeDisposable.add( mTechRepository.updateUpvoteCount(techProduct)
            .subscribeOn(mSchedulerProvider.io())
            .observeOn(mSchedulerProvider.ui())
            .subscribe({
                if (it.second) {
                    mView?.updateUpvotedData(it.first)
                } else  {
                    Log.e("TEST", " some error occured")
                }
            },{
                it.printStackTrace()
            }))

    }

    override fun markProductBookmarked(techProduct: TechProduct) {
        mCompositeDisposable.add( mTechRepository.bookmarkProduct(techProduct)
            .subscribeOn(mSchedulerProvider.io())
            .observeOn(mSchedulerProvider.ui())
            .subscribe({
                if (it.second) {
                    mView?.updateBookMarkedState(it.first)
                } else  {
                    Log.e("TEST", " some error occured")
                }
            },{
                it.printStackTrace()
            }))
    }

    override fun checkAndOpenFilterFragment() {
        val filter = mGlobalPref.currentFilter
        Log.e("TEXTS", " filter is $filter")
        mView?.openFilterPage(filter)
    }
}