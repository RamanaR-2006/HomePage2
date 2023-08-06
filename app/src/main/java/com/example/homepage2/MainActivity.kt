package com.example.homepage2

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.your_status_bar_color, theme)
        }

        val spinner: Spinner = findViewById(R.id.spinner)

        // Sample data for the spinner
        val items = listOf("--Select--", "Wrench", "Spare Tire", "Gasoline", "Chocolates?? idk")

        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        spinner.adapter = adapter

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        data.add(ItemsViewModel(R.drawable.r1, "Small"))
        data.add(ItemsViewModel(R.drawable.r2, "Medium"))
        data.add(ItemsViewModel(R.drawable.r3, "Large"))
        data.add(ItemsViewModel(R.drawable.r4, "Electric"))

        // This will pass the ArrayList to our Adapter
        val adapter1 = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter1

        val recyclerview1 = findViewById<RecyclerView>(R.id.recyclerview1)

        // this creates a vertical layout Manager
        recyclerview1.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)

        // ArrayList of class ItemsViewModel
        val data1 = ArrayList<ItemsViewModel2>()

        // This loop will create 20 Views containing
        // the image with the count of view
        data1.add(ItemsViewModel2("0-12", "Months"))
        data1.add(ItemsViewModel2("12-24", "Months"))
        data1.add(ItemsViewModel2("24-36", "Months"))
        data1.add(ItemsViewModel2("Older than 36", "Months"))

        // This will pass the ArrayList to our Adapter
        val adapter2 = CustomAdapter2(data1)

        // Setting the Adapter with the recyclerview
        recyclerview1.adapter = adapter2


    }
    fun showStartDatePicker(view: View) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                // Handle selected start date
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    fun showEndDatePicker(view: View) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                // Handle selected end date
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}