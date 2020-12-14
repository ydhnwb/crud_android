package com.ydhnwb.myapplication.data.dao

import androidx.room.*
import com.ydhnwb.myapplication.data.Product

@Dao
interface ProductDao {
    @Query("SELECT * from Product")
    fun all() : List<Product>

    @Query("SELECT * FROM Product WHERE id = :id LIMIT 1")
    fun findById(id: Int): Product

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(prd: Product)

    @Update
    fun update(prd: Product)

    @Delete
    fun delete(prd: Product)

}