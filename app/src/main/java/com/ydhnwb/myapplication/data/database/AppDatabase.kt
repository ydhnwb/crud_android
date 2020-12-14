package com.ydhnwb.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ydhnwb.myapplication.data.Product
import com.ydhnwb.myapplication.data.dao.ProductDao

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}