package com.example.homepage2

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage2.databinding.ActivityMainBinding
import com.example.homepage2.databinding.DialogBoxLayoutBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private var isRecyclerViewSelected = false
    private lateinit var binding: ActivityMainBinding

    private lateinit var dialog: AlertDialog
    private lateinit var dialogBinding: DialogBoxLayoutBinding
    private lateinit var quantityTextView: TextView
    private lateinit var minusButton: ImageButton
    private lateinit var plusButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.your_status_bar_color, theme)
        }

        quantityTextView = dialogBinding.quantityTextView
        minusButton = dialogBinding.minusButton
        plusButton = dialogBinding.plusButton

        // Set click listeners for minus and plus buttons
        minusButton.setOnClickListener {
            val currentQuantity = quantityTextView.text.toString().toInt()
            if (currentQuantity > 0) {
                quantityTextView.text = (currentQuantity - 1).toString()
            }
        }

        plusButton.setOnClickListener {
            val currentQuantity = quantityTextView.text.toString().toInt()
            quantityTextView.text = (currentQuantity + 1).toString()
        }

        val spinner: Spinner = findViewById(R.id.spinner)
        var SelectedItems = 0
        val items = listOf("Number of selected items is ${SelectedItems}")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.setOnClickListener {
            showDialogBox()
            SelectedItems = quantityTextView.text.toString().toInt()
            updateSpinnerSelectionState()

        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position > 0) {
                    showDialogBox()
                }
                updateSpinnerSelectionState()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Not needed in this case
            }
        }
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val data = ArrayList<ItemsViewModel>()
        data.add(ItemsViewModel(R.drawable.r1, "Small"))
        data.add(ItemsViewModel(R.drawable.r2, "Medium"))
        data.add(ItemsViewModel(R.drawable.r3, "Large"))
        data.add(ItemsViewModel(R.drawable.r4, "Electric"))

        val adapter1 = CustomAdapter(data)
        recyclerview.adapter = adapter1

        val recyclerview1 = findViewById<RecyclerView>(R.id.recyclerview1)
        recyclerview1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val data1 = ArrayList<ItemsViewModel2>()
        data1.add(ItemsViewModel2("0-12", "Months"))
        data1.add(ItemsViewModel2("12-24", "Months"))
        data1.add(ItemsViewModel2("24-36", "Months"))
        data1.add(ItemsViewModel2("Older than 36", "Months"))

        val adapter2 = CustomAdapter2(data1)
        recyclerview1.adapter = adapter2
        val isRecyclerViewSelected1 = (recyclerview.adapter as? CustomAdapter)?.isItemSelected == true ||
                (recyclerview1.adapter as? CustomAdapter2)?.isItemSelected == true
        dialogBinding = DialogBoxLayoutBinding.inflate(LayoutInflater.from(this))
        dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()


    }

    fun showStartDatePicker(view: View) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = "${year}-${monthOfYear + 1}-${dayOfMonth}"
                val startDateEditText = findViewById<EditText>(R.id.startDateEditText)
                startDateEditText.setText(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    fun showEndDatePicker(view: View) {
        val calendar = Calendar.getInstance()
        val startDateEditText = findViewById<EditText>(R.id.startDateEditText)
        val currentStartDateText = startDateEditText.text.toString()

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = "${year}-${monthOfYear + 1}-${dayOfMonth}"
                val endDateEditText = findViewById<EditText>(R.id.endDateEditText)

                if (selectedDate != currentStartDateText) {
                    endDateEditText.setText(selectedDate)
                } else {
                    Toast.makeText(this, "End date cannot be the same as start date", Toast.LENGTH_SHORT).show()
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun updateSpinnerSelectionState() {
        val spinner = findViewById<Spinner>(R.id.spinner)
        val selectedItemPosition = spinner.selectedItemPosition

        isRecyclerViewSelected = selectedItemPosition > 0
        updateButtonState() // Update button state after spinner selection changes
    }

    private fun updateButtonState() {
        val finalButton = findViewById<ImageButton>(R.id.finalButton)
        if (areAllFieldsFilled()) {
            finalButton.setBackgroundColor(ContextCompat.getColor(this, R.color.your_desired_color))
            Toast.makeText(this,"Valid!!",Toast.LENGTH_SHORT).show()
        } else {
            finalButton.setBackgroundColor(ContextCompat.getColor(this, R.color.default_button_color))
        }
    }



    private fun areAllFieldsFilled(): Boolean {
        val locationEditText = findViewById<EditText>(R.id.location)
        val startDateEditText = findViewById<EditText>(R.id.startDateEditText)
        val endDateEditText = findViewById<EditText>(R.id.endDateEditText)
        val timeEditText = findViewById<EditText>(R.id.time)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val selectedItemPosition = spinner.selectedItemPosition

        return locationEditText.text.isNotBlank() &&
                startDateEditText.text.isNotBlank() &&
                endDateEditText.text.isNotBlank() &&
                timeEditText.text.isNotBlank() &&
                selectedItemPosition > 0 &&
                isRecyclerViewSelected

    }
    private fun showDialogBox() {
        // Clear previous quantity value
        dialogBinding.quantityTextView.text = "0"

        // Show the dialog box
        dialog.show()
    }

}