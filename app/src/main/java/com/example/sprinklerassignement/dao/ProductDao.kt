package com.example.sprinklerassignement.dao

import androidx.room.*
import com.example.sprinklerassignement.data.TechProduct
import io.reactivex.Single

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductEntity(techProduct: List<TechProduct>)

    @Query("select * from techProducts")
    fun loadAllData(): Single<List<TechProduct>>

    @Query("delete from techProducts where  productId= :id")
    fun deleteProductById(id: Long)

    @Update
    fun updateUpvoteCount(techProduct: TechProduct)

    @Update
    fun bookMarkProduct(techProduct: TechProduct)

    @Query("select * from techProducts where type=:name")
    fun loadDataByFilter(name: String): Single<List<TechProduct>>

    @Query("select * from techProducts where isBookmarked=1")
    fun loadBookMarkedData(): Single<List<TechProduct>>
}