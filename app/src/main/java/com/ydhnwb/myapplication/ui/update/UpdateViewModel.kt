package com.ydhnwb.myapplication.ui.update

import androidx.lifecycle.ViewModel
import com.ydhnwb.myapplication.data.Product
import com.ydhnwb.myapplication.data.database.AppDatabase

class UpdateViewModel(private val appDatabase: AppDatabase) : ViewModel() {
    fun update(id: Int, name: String, price: Int, qty: Int, expiryDate: String) = appDatabase.productDao().insert(
        Product(id = id, name = name, price = price, expiryDate = expiryDate, qty = qty)
    )
    fun findById(id: Int) = appDatabase.productDao().findById(id)
    fun delete(product: Product) = appDatabase.productDao().delete(product)

}