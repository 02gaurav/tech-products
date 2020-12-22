package com.example.sprinklerassignement

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sprinklerassignement.dao.ProductDao
import com.example.sprinklerassignement.data.TechProduct

@Database(entities = [TechProduct::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase(){

    abstract  fun productDao(): ProductDao
}