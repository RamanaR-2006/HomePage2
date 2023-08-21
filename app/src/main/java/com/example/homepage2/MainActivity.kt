package com.example.homepage2

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
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
    private lateinit var quantityTextView2: TextView
    private lateinit var minusButton2: ImageButton
    private lateinit var plusButton2: ImageButton
    private lateinit var quantityTextView3: TextView
    private lateinit var minusButton3: ImageButton
    private lateinit var plusButton3: ImageButton
    private lateinit var quantityTextView4: TextView
    private lateinit var minusButton4: ImageButton
    private lateinit var plusButton4: ImageButton
    private var selectedEquipmentCount = 0
    private var selectedEquipmentCount2 = 0
    private var selectedEquipmentCount3 = 0
    private var selectedEquipmentCount4 = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.your_status_bar_color, theme)
        }

        val spinner: Spinner = findViewById(R.id.spinner)
        val items = listOf("--Select--", "1")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf(getSpinnerText()))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                showDialogBox()
            }
            true
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

        dialogBinding = DialogBoxLayoutBinding.inflate(LayoutInflater.from(this))
        dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        quantityTextView = dialogBinding.quantityTextView
        minusButton = dialogBinding.minusButton
        plusButton = dialogBinding.plusButton

        quantityTextView2 = dialogBinding.quantityTextView2
        minusButton2 = dialogBinding.minusButton2
        plusButton2 = dialogBinding.plusButton2

        quantityTextView3 = dialogBinding.quantityTextView3
        minusButton3 = dialogBinding.minusButton3
        plusButton3 = dialogBinding.plusButton3

        quantityTextView4 = dialogBinding.quantityTextView4
        minusButton4 = dialogBinding.minusButton4
        plusButton4 = dialogBinding.plusButton4

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
        minusButton2.setOnClickListener {
            val currentQuantity2 = quantityTextView2.text.toString().toInt()
            if (currentQuantity2 > 0) {
                quantityTextView2.text = (currentQuantity2 - 1).toString()
            }
        }

        plusButton2.setOnClickListener {
            val currentQuantity2 = quantityTextView2.text.toString().toInt()
            quantityTextView2.text = (currentQuantity2 + 1).toString()
        }
        minusButton3.setOnClickListener {
            val currentQuantity3 = quantityTextView3.text.toString().toInt()
            if (currentQuantity3 > 0) {
                quantityTextView3.text = (currentQuantity3 - 1).toString()
            }
        }

        plusButton3.setOnClickListener {
            val currentQuantity3 = quantityTextView3.text.toString().toInt()
            quantityTextView3.text = (currentQuantity3 + 1).toString()
        }
        minusButton4.setOnClickListener {
            val currentQuantity4 = quantityTextView4.text.toString().toInt()
            if (currentQuantity4 > 0) {
                quantityTextView4.text = (currentQuantity4 - 1).toString()
            }
        }

        plusButton4.setOnClickListener {
            val currentQuantity4 = quantityTextView4.text.toString().toInt()
            quantityTextView4.text = (currentQuantity4 + 1).toString()
        }
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

    private fun updateSpinnerText() {
        val spinner: Spinner = findViewById(R.id.spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf(getSpinnerText()))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
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
            Toast.makeText(this, "Valid!!", Toast.LENGTH_SHORT).show()
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

    private fun getSpinnerText(): String {
        var a = 0
        var b = 0
        var c = 0
        var d = 0
        if (selectedEquipmentCount>0){
            a = 1
        }
        if (selectedEquipmentCount2>0){
            b = 1
        }
        if (selectedEquipmentCount3>0){
            c = 1
        }
        if (selectedEquipmentCount4>0){
            d = 1
        }
        var sum1 = a+b+c+d
        if(sum1==0){
        return "Number of selected items: 0"}
        else if(sum1==1){
            return "Number of selected items: 1"
        }
        return "Number of selected items: 2"
    }

    private fun showDialogBox() {
        // Clear previous quantity value
        minusButton.setOnClickListener {
            val currentQuantity = dialogBinding.quantityTextView.text.toString().toInt()
            if (currentQuantity > 0) {
                dialogBinding.quantityTextView.text = (currentQuantity - 1).toString()
                selectedEquipmentCount--
                updateSpinnerText()
            }
        }

        plusButton.setOnClickListener {
            val currentQuantity = dialogBinding.quantityTextView.text.toString().toInt()
            dialogBinding.quantityTextView.text = (currentQuantity + 1).toString()
            selectedEquipmentCount++
            updateSpinnerText()
        }

        minusButton2.setOnClickListener {
            val currentQuantity2 = dialogBinding.quantityTextView2.text.toString().toInt()
            if (currentQuantity2 > 0) {
                dialogBinding.quantityTextView2.text = (currentQuantity2 - 1).toString()
                selectedEquipmentCount2--
                updateSpinnerText()
            }
        }

        plusButton2.setOnClickListener {
            val currentQuantity2 = dialogBinding.quantityTextView2.text.toString().toInt()
            dialogBinding.quantityTextView2.text = (currentQuantity2 + 1).toString()
            selectedEquipmentCount2++
            updateSpinnerText()
        }
        minusButton3.setOnClickListener {
            val currentQuantity3 = dialogBinding.quantityTextView3.text.toString().toInt()
            if (currentQuantity3 > 0) {
                dialogBinding.quantityTextView3.text = (currentQuantity3 - 1).toString()
                selectedEquipmentCount3--
                updateSpinnerText()
            }
        }

        plusButton3.setOnClickListener {
            val currentQuantity3 = dialogBinding.quantityTextView3.text.toString().toInt()
            dialogBinding.quantityTextView3.text = (currentQuantity3 + 1).toString()
            selectedEquipmentCount3++
            updateSpinnerText()
        }
        minusButton4.setOnClickListener {
            val currentQuantity4 = dialogBinding.quantityTextView4.text.toString().toInt()
            if (currentQuantity4 > 0) {
                dialogBinding.quantityTextView4.text = (currentQuantity4 - 1).toString()
                selectedEquipmentCount4--
                updateSpinnerText()
            }
        }

        plusButton4.setOnClickListener {
            val currentQuantity4 = dialogBinding.quantityTextView4.text.toString().toInt()
            dialogBinding.quantityTextView4.text = (currentQuantity4 + 1).toString()
            selectedEquipmentCount4++
            updateSpinnerText()
        }
        // Show the dialog box
        dialog.show()
    }
}