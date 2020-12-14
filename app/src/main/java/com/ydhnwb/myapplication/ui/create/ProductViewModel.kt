package com.ydhnwb.myapplication.ui.create

import androidx.lifecycle.ViewModel
import com.ydhnwb.myapplication.data.Product
import com.ydhnwb.myapplication.data.database.AppDatabase

class ProductViewModel(private val appDatabase: AppDatabase) : ViewModel() {

    fun insert(name: String, price: Int, qty: Int, expiryDate : String) = appDatabase.productDao().insert(Product(name = name, price = price, qty = qty, expiryDate = expiryDate))
}