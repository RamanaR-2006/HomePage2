package com.example.homepage2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter2(private val mList: List<ItemsViewModel2>) : RecyclerView.Adapter<CustomAdapter2.ViewHolder>() {
    var isItemSelected = false
    private var selectedItemPosition = RecyclerView.NO_POSITION

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design1, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val item = mList[position]

        holder.textView2.text = item.text1
        holder.textView1.text = item.text2

        // Update item view based on selection state
        if (position == selectedItemPosition) {
            holder.itemView.setBackgroundResource(R.drawable.selected_item_background)
        } else {
            holder.itemView.setBackgroundResource(0)
        }

        // Set item click listener to update selection state
        holder.itemView.setOnClickListener {
            val previousSelectedItem = selectedItemPosition
            selectedItemPosition = position
            notifyItemChanged(previousSelectedItem)
            notifyItemChanged(selectedItemPosition)
            isItemSelected = true // Mark the item as selected
            notifyDataSetChanged() // Notify adapter to refresh the views
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView2: TextView = itemView.findViewById(R.id.textView)
        val textView1: TextView = itemView.findViewById(R.id.textView1)
    }
}