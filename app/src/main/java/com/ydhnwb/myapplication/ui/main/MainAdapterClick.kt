package com.ydhnwb.myapplication.ui.main

import com.ydhnwb.myapplication.data.Product

interface MainAdapterClick {
    fun onTap(product: Product)
}