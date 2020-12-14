package com.ydhnwb.myapplication.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.ydhnwb.myapplication.R
import com.ydhnwb.myapplication.data.Product
import com.ydhnwb.myapplication.databinding.ActivityMainBinding
import com.ydhnwb.myapplication.ui.create.ProductActivity
import com.ydhnwb.myapplication.ui.update.UpdateProductActivity
import com.ydhnwb.myapplication.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MainAdapterClick {
    private lateinit var binding : ActivityMainBinding
    private val vm : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setupRecycler()
        observe()


        binding.fab.setOnClickListener {
            startActivity(Intent(this@MainActivity, ProductActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onResume() {
        super.onResume()
        vm.allProduct()
    }



    private fun setupRecycler(){
        binding.content.productRecyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter(mutableListOf(), this@MainActivity)
        }
    }

    private fun observe() {
        vm.getProduct().observe(this, { handleProductChange(it) })
        vm.getState().observe(this, { handleMainState(it) })
    }

    private fun handleProductChange(products: List<Product>){
        binding.content.productRecyclerview.adapter?.let { adapter ->
            if(adapter is MainAdapter){
                adapter.updateList(products)
            }
        }
    }

    private fun handleMainState(state: MainState){
        when(state){
            is MainState.ShowToast -> showToast(state.message)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onTap(product: Product) {
        startActivity(Intent(this@MainActivity, UpdateProductActivity::class.java).apply {
            putExtra("id", product.id)
        })
    }
}