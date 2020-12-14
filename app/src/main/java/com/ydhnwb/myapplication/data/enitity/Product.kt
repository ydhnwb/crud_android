package com.ydhnwb.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["id"], unique = true)])
data class Product (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "name")
    var name : String,
    @ColumnInfo(name = "qty")
    var qty: Int,
    @ColumnInfo(name = "expiry_date")
    val expiryDate : String,
    @ColumnInfo(name = "price")
    var price: Int = 0
)