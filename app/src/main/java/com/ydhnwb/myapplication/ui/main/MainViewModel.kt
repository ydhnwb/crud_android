package com.ydhnwb.myapplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ydhnwb.myapplication.data.Product
import com.ydhnwb.myapplication.data.database.AppDatabase

class MainViewModel(private val appDb : AppDatabase) : ViewModel() {
    private val state  = MutableLiveData<MainState>()
    private val persons = MutableLiveData<List<Product>>(arrayListOf())

    private fun showMessage(message: String) {
        state.value = MainState.ShowToast(message)
    }

    fun allProduct() {
        persons.postValue(appDb.productDao().all())
    }

    fun getState() = state
    fun getProduct() = persons

}

sealed class MainState {
    data class ShowToast(val message : String) : MainState()
}