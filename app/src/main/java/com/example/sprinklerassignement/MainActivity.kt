package com.example.sprinklerassignement

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sprinklerassignement.adapter.ProductAdapter
import com.example.sprinklerassignement.base.BaseMvpActivity
import com.example.sprinklerassignement.base.MvpPresenter
import com.example.sprinklerassignement.data.FILTER_TYPE
import com.example.sprinklerassignement.data.TechProduct
import com.example.sprinklerassignement.extension.gone
import com.example.sprinklerassignement.extension.show
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity: BaseMvpActivity<MainContract.View>(), MainContract.View, ProductActionsListener, FilterClickListener{


    @Inject
    protected lateinit var mPresenter: MainContract.Presenter

    private var mAdapter: ProductAdapter? = null

    override fun getPresenter(): MvpPresenter<MainContract.View> = mPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.takeView(this)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        setListeners()
    }

    private fun setListeners() {
        tv_filters.setOnClickListener {
           mPresenter.checkAndOpenFilterFragment()
        }
    }

    private fun initRecyclerView() {
        mAdapter = ProductAdapter(this)
        val listLayoutManager= LinearLayoutManager(this)
        recyclerview.layoutManager = listLayoutManager
        recyclerview.adapter = mAdapter
        val scrollListener = object : EndlessRecyclerOnScrollListener(listLayoutManager) {
            override fun onLoadMore(currentPage: Int) {
               // mPresenter.fetchProducts()
            }
        }
        recyclerview.addOnScrollListener(scrollListener)
        mPresenter.fetchProducts()
    }

    override fun populateProductsData(data: List<TechProduct>) {
        mAdapter?.addToBottom(data)
    }

    override fun handleLoadingView(show: Boolean) {
        runOnUiThread {
            if (show) {
                pb_progress.show()
            } else {
                pb_progress.gone()
            }
        }
    }

    override fun openFilterPage(filterType: String) {
        FilterFragment.show(supportFragmentManager, filterType)
    }

    override fun updateFilterSelection(filterType: FILTER_TYPE) {
        mAdapter?.clearAdapter()
        mPresenter.fetchProducts(filterType, true)
    }

    override fun onUpvoteClicked(techProduct: TechProduct) {
       mPresenter.updateUpvoteCount(techProduct)
    }

    override fun updateUpvotedData(updatedProduct: TechProduct) {
        mAdapter?.updateUpvoteData(updatedProduct)
    }

    override fun onShareClicked(techProduct: TechProduct) {

        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Tech Product Data")
            var shareMessage = "\n I found a new app that allows you to see trending tech products\n" // add description here
            shareMessage = "${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}".trimIndent() // add link of product here
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "Please select one app"))
        } catch (e: Exception) {
            //e.toString();
            Toast.makeText(this, "No Suitable app found to share this", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBookmarkClicked(techProduct: TechProduct) {
        mPresenter.markProductBookmarked(techProduct)
    }

    override fun updateBookMarkedState(bookmarkProduct: TechProduct) {
        mAdapter?.updateBookMarkState(bookmarkProduct)
    }

    override fun onLinkClicked(profileLink: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(profileLink))
        startActivity(browserIntent)
    }
}