package com.ydhnwb.myapplication.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ydhnwb.myapplication.R
import com.ydhnwb.myapplication.data.Product
import com.ydhnwb.myapplication.databinding.ItemProductBinding

class MainAdapter (private val products: MutableList<Product>, private val mainAdapterInterface: MainAdapterClick)
    : RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(products[position])

    override fun getItemCount() = products.size

    fun updateList(updated: List<Product>) {
        products.clear()
        products.addAll(updated)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ItemProductBinding.bind(itemView)

        fun bind(p : Product){
            binding.productNameTextview.text = p.name
            binding.productExpTextview.text = p.expiryDate
            binding.productPriceTextview.text = "Rp. ${p.price}"
            itemView.setOnClickListener {
                mainAdapterInterface.onTap(p)
            }
        }
    }

}