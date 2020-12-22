package com.example.sprinklerassignement.repository

import android.util.Log
import com.example.sprinklerassignement.AppDataBase
import com.example.sprinklerassignement.data.FILTER_TYPE
import com.example.sprinklerassignement.data.TechProduct
import com.example.sprinklerassignement.prefs.GlobalPref
import com.example.sprinklerassignement.schedulers.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TechProductRepository
@Inject
constructor(private val mGlobalPref: GlobalPref,
            private val mSchedulerProvider: SchedulerProvider,
            private val database: AppDataBase) {

    private val mData: ArrayList<TechProduct> = arrayListOf()

    init {
        mData.add(TechProduct().apply {
            this.productId = 1
            this.title = "1.1mg"
            this.description = "1mg is India’s leading consumer health platform." +
                    " It aspires to be the trusted health partner for all Indians and its mission is to make healthcare accessible, understandable " +
                    "and affordable for a billion Indians. 1mg (formerly Healthkart Plus) is an online pharmacy network and generic medicine engine." +
                    " It enables users to find information about medicines prescribed by doctors and also buy them. Users can find drugs by ailments, class, companies, and brands."
            this.productImage = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"
            this.profileLink = "https://www.linkedin.com/in/agarwalga/"
            this.founderName = "Gaurav Agarwal"
            this.upvoteCount = 120
            this.isBookmarked = false
            this.isUpvoted = false
            this.type = FILTER_TYPE.MEDICAL_TECH.name
        })
        mData.add(TechProduct().apply {
            this.productId = 2
            this.title = "Acko"
            this.description = "Acko General Insurance is a digital insurance company in India that redefines insurance for the consumers. " +
                    "The company gives them killer prices, extreme convenience and stress-free claims."
            this.productImage = "https://homepages.cae.wisc.edu/~ece533/images/monarch.png"
            this.profileLink = "https://www.linkedin.com/in/ruchi-deepak-67b90b194/"
            this.founderName ="Ruchi Deepak"
            this.upvoteCount = 120
            this.isBookmarked = false
            this.isUpvoted = false
            this.type = FILTER_TYPE.TRENDING.name
        })

        mData.add(TechProduct().apply {
            this.productId = 3
            this.title = "Bigbasket"
            this.description = "Bigbasket.com is an online food and grocery store. " +
                    "You can browse through their varied range of products at bigbasket, order them and get them to be delivered at your own convenient time slot.\n"
            this.productImage = "https://homepages.cae.wisc.edu/~ece533/images/mountain.png"
            this.profileLink = "https://www.linkedin.com/in/vipulparekh/"
            this.founderName = "Vipul Parekh"
            this.upvoteCount = 90
            this.isBookmarked = false
            this.isUpvoted = false
            this.type = FILTER_TYPE.ED_TECH.name
        })
        mData.add(TechProduct().apply {
            this.productId = 4
            this.title = "Bizongo"
            this.description = "Bizongo can be simply defined as a platform for everything packaging." +
                    " It’s a comprehensive technological platform created to service the needs of enterprises seeking solutions at any stage of the packaging value-chain.\n" +
                    "\n"
            this.productImage = "https://homepages.cae.wisc.edu/~ece533/images/boat.png"
            this.profileLink = "https://www.linkedin.com/in/ankit-tomar-46240418/"
            this.founderName = "Ankit Tomar"
            this.upvoteCount = 120
            this.isBookmarked = false
            this.isUpvoted = false
            this.type = FILTER_TYPE.MEDICAL_TECH.name
        })
        mData.add(TechProduct().apply {
            this.productId = 5
            this.title = "BYJU'S"
            this.description = "BYJU’S – The Learning App BYJU’S is India’s largest Ed-tech company and the creator of India’s largest K12 " +
                    "learning app which offers highly adaptive, engaging and effective learning " +
                    "programs for students in classes 4-12 (K-12) and competitive exams like JEE, NEET, CAT, IAS, GRE and GMAT."
            this.productImage = "https://homepages.cae.wisc.edu/~ece533/images/fruits.png"
            this.profileLink = "https://www.linkedin.com/in/divya-gokulnath-6b5a7a108/"
            this.founderName = "Divya Gokulnath"
            this.upvoteCount = 45
            this.isBookmarked = false
            this.isUpvoted = false
            this.type = FILTER_TYPE.ED_TECH.name
        })
        mData.add(TechProduct().apply {
            this.productId = 6
            this.title = "CARS24"
            this.description = "CARS24 is a tech enabled used cars disruptor. CARS24 makes selling a car an easy, fair and quick experience." +
                    " CARS24’s mission is to revolutionize the way used cars are sold in India. " +
                    "CARS24 creates an efficient and reliable way for car owners to sell their used cars at the Best Price"
            this.productImage =  "https://homepages.cae.wisc.edu/~ece533/images/frymire.png"
            this.profileLink = "https://www.linkedin.com/in/mehul-agrawal-75123514/"
            this.founderName = "Mehul agrawal"
            this.upvoteCount = 80
            this.isBookmarked = false
            this.isUpvoted = false
            this.type = FILTER_TYPE.TRENDING.name
        })
        mData.add(TechProduct().apply {
            this.productId = 7
            this.title = "CleverTap"
            this.description = "CleverTap helps consumer brands retain their users for life. It is a powerful mobile marketing solution that brings together user data from online and offline channels on one centralized platform.\n" +
                    "\n"
            this.productImage =  "https://homepages.cae.wisc.edu/~ece533/images/peppers.png"
            this.profileLink = "https://www.linkedin.com/in/anand-jain-07a8012/"
            this.founderName = "Anand Jain"
            this.upvoteCount = 20
            this.isBookmarked = false
            this.isUpvoted = false
            this.type = FILTER_TYPE.ED_TECH.name
        })
        mData.add(TechProduct().apply {
            this.productId = 8
            this.title = "CureFit"
            this.description = "CureFit is an Indian health and fitness company offering digital and offline experiences across fitness, nutrition, and mental well-being. " +
                    "CureFit will be an innovative combination of engagement, coaching and delivery through a combination of online and offline channels."
            this.productImage = "https://homepages.cae.wisc.edu/~ece533/images/pool.png"
            this.profileLink = "https://in.linkedin.com/in/ankitnagori"
            this.founderName ="Mukesh Bansal"
            this.upvoteCount = 10
            this.isBookmarked = false
            this.isUpvoted = false
            this.type = FILTER_TYPE.MACHINE_LEARNING.name
        })

        mData.add(TechProduct().apply {
            this.productId = 9
            this.title = "Daily Ninja i"
            this.description = "Daily Ninja is an online hyper local shopping start-up which especially caters to the regular needs of people, daily at their doorstep.\n" +
                    "\n"
            this.productImage = "https://homepages.cae.wisc.edu/~ece533/images/tulips.png"
            this.profileLink = "https://www.linkedin.com/in/anurag-gupta-0a230217/"
            this.founderName = "Anurag Gupta"
            this.upvoteCount = 2120
            this.isBookmarked = false
            this.isUpvoted = false
            this.type = FILTER_TYPE.ED_TECH.name
        })

        mData.add(TechProduct().apply {
            this.productId = 10
            this.title = "CloudCherry"
            this.description = "CloudCherry is the next generation Experience Management SaaS platform that is disrupting the way organizations listen to the Voice of Customer.\n" +
                    "\n"
            this.productImage = "https://homepages.cae.wisc.edu/~ece533/images/watch.png"
            this.profileLink = "https://www.linkedin.com/in/venkateshwar-viswanathan-1b464340/"
            this.founderName = "venkateshwar viswanathan"
            this.upvoteCount = 1210
            this.isBookmarked = false
            this.isUpvoted = false
            this.type = FILTER_TYPE.TRENDING.name
        })
    }

    fun fetchProducts(filterType: FILTER_TYPE = FILTER_TYPE.NONE): Single<List<TechProduct>> {

        return if(filterType == FILTER_TYPE.NONE) {
            fetchFromDb()
                .map {
                    Log.e("TESTS", " data is == $it")
                    if(it.isEmpty()) throw Exception("empty data ")
                    it
                }
                .onErrorResumeNext {
                Log.e("TESTS", " db call error == ${it.printStackTrace()}")
                insertDataInDbAsync(mData)
            }
        } else {
            fetchDataFromDbByFilter(filterType)
        }
    }

    private fun fetchFromDb() : Single<List<TechProduct>> {
        Log.e("TESTS", " db call ")
        return database.productDao().loadAllData()
    }

    private fun fetchDataFromDbByFilter(filterType: FILTER_TYPE): Single<List<TechProduct>> {
        return if (filterType == FILTER_TYPE.BOOKMARK) {
            database.productDao().loadBookMarkedData()
        } else {
            database.productDao().loadDataByFilter(filterType.name)
        }

    }

    private fun insertDataInDbAsync(list: List<TechProduct>): Single<ArrayList<TechProduct>> {

        fun insertData() : Completable = Completable.fromCallable {
          //  list.forEach {
                Log.e("TESTS", " insert data")
                database.productDao().insertProductEntity(list)
         //   }
        }

        return insertData()
            .andThen(Single.just(mData))
            .subscribeOn(mSchedulerProvider.io())
    }


    fun fetchBookMarkProducts() {

    }

    fun markProductBookMark(isBookmarked: Boolean) {

    }

    fun updateUpvoteCount(techProduct: TechProduct): Single<Pair<TechProduct, Boolean>> {
        techProduct.isUpvoted = techProduct.isUpvoted.not()
        techProduct.upvoteCount =  if(techProduct.isUpvoted) techProduct.upvoteCount+1 else techProduct.upvoteCount-1
       return Completable.fromAction {
            database.productDao().updateUpvoteCount(techProduct)
        }.andThen(Single.just(Pair(techProduct, true))
            .onErrorResumeNext {
                Single.just(Pair(techProduct, false))
            })
    }

    fun bookmarkProduct(techProduct: TechProduct): Single<Pair<TechProduct, Boolean>> {
        techProduct.isBookmarked = !techProduct.isBookmarked
        return Completable.fromAction {
            database.productDao().bookMarkProduct(techProduct)
        }.andThen(Single.just(Pair(techProduct, true))
            .onErrorResumeNext {
                Single.just(Pair(techProduct, false))
            })
    }
}