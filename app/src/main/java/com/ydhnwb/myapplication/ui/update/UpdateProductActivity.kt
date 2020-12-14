package com.ydhnwb.myapplication.ui.update

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.ydhnwb.myapplication.R
import com.ydhnwb.myapplication.databinding.ActivityUpdateProductBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateProductActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUpdateProductBinding
    private val vm : UpdateViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        getPassedId()
    }

    private fun getPassedId() {
        val product = vm.findById(intent.getIntExtra("id", 0))
        binding.content.nameEditText.setText(product.name)
        binding.content.priceEditText.setText(product.price)
        binding.content.qtyEditText.setText(product.qty)
        //date....

    }
}