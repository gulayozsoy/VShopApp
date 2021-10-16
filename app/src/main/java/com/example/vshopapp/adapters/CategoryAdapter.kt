package com.example.vshopapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vshopapp.R
import com.example.vshopapp.model.Category

class CategoryAdapter(var categorylist: ArrayList<Category>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false))
    }

    override fun getItemCount(): Int = categorylist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(categorylist[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Category)= with(itemView) {
            val cover = findViewById<ImageView>(R.id.category_image)
            val url = item.clogo.curl
            if(url.isNotEmpty()) {
                Glide.with(cover.context)
                    .load(url)
                    .into(cover)
            }

            val categoryName = findViewById<TextView>(R.id.category_name)
            categoryName.text = item.name
        }
    }

    // Clean all elements of the recycler
    fun clear() {
        categorylist.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(list: ArrayList<Category>) {
        categorylist.addAll(list)
        notifyDataSetChanged()
    }

}