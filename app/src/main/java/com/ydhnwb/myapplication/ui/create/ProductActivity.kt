package com.ydhnwb.myapplication.ui.create

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ydhnwb.myapplication.databinding.ActivityProductBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class ProductActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProductBinding
    private val vm : ProductViewModel by viewModel()
    private var expiryDate : String? = null
    private val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setupUI()
        doSave()
    }

    private fun setupUI(){
        val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        val date = dateFormatter.format(Calendar.getInstance().time).toString()
        expiryDate = date
        binding.content.currentDateTextView.text = expiryDate

        binding.content.productExpDatePicker.setOnClickListener {
            setupDatePicker()
        }
    }

    private fun setDateView(newDate : Date){
        expiryDate = dateFormatter.format(newDate.time)
        binding.content.currentDateTextView.text = expiryDate
    }

    private fun doSave(){
        binding.content.saveButton.setOnClickListener {
            val name = binding.content.nameEditText.text.toString().trim()
            val price : Int = binding.content.priceEditText.text.toString().toIntOrNull() ?: 0
            val qty : Int = binding.content.qtyEditText.text.toString().toIntOrNull() ?: 0
            vm.insert(name = name, price,  qty, expiryDate ?: dateFormatter.format(Calendar.getInstance().time))
            finish()
        }
    }


    private fun setupDatePicker() {
        val newCalendar: Calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this, { _, year, monthOfYear, dayOfMonth ->
                val newDate: Calendar = Calendar.getInstance()
                newDate.set(year, monthOfYear, dayOfMonth)
                setDateView(newDate.time)
            },
            newCalendar.get(Calendar.YEAR),
            newCalendar.get(Calendar.MONTH),
            newCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}