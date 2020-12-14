package com.ydhnwb.myapplication.ui.update

import android.app.DatePickerDialog
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.ydhnwb.myapplication.R
import com.ydhnwb.myapplication.databinding.ActivityUpdateProductBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class UpdateProductActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUpdateProductBinding
    private val vm : UpdateViewModel by viewModel()
    private var expiryDate : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        getPassedId()
        setupUI()
        saveChange()
        delete()
    }

    private fun setupUI(){
        binding.content.productExpDatePicker.setOnClickListener {
            setupDatePicker()
        }
    }

    private fun delete(){
        binding.content.saveDelete.setOnClickListener {
            val product = vm.findById(intent.getIntExtra("id", 0))
            vm.delete(product)
            finish()
        }
    }

    private fun saveChange(){
        val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        binding.content.saveButton.setOnClickListener {
            val id = intent.getIntExtra("id", 0)
            val name = binding.content.nameEditText.text.toString().trim()
            val price : Int = binding.content.priceEditText.text.toString().toIntOrNull() ?: 0
            val qty : Int = binding.content.qtyEditText.text.toString().toIntOrNull() ?: 0
            vm.update(id  = id,name = name, price,  qty, expiryDate ?: dateFormatter.format(Calendar.getInstance().time))
            finish()
        }
    }

    private fun getPassedId() {
        val product = vm.findById(intent.getIntExtra("id", 0))
        binding.content.nameEditText.setText(product.name)
        binding.content.priceEditText.setText(product.price.toString())
        binding.content.qtyEditText.setText(product.qty.toString())
        //date....

    }

    private fun setupDatePicker() {
        val newCalendar: Calendar = Calendar.getInstance()

        val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)

        val datePickerDialog = DatePickerDialog(
            this, { _, year, monthOfYear, dayOfMonth ->
                val newDate: Calendar = Calendar.getInstance()
                newDate.set(year, monthOfYear, dayOfMonth)
                expiryDate = dateFormatter.format(newDate.time)
            },
            newCalendar.get(Calendar.YEAR),
            newCalendar.get(Calendar.MONTH),
            newCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}