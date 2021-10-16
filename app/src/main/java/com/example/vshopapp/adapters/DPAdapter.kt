package com.example.vshopapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vshopapp.R
import com.example.vshopapp.model.Product

class DPAdapter(var productlist: ArrayList<Product>): RecyclerView.Adapter<DPAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.detail_product_item, parent, false))
    }

    override fun getItemCount(): Int = productlist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(productlist[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Product)= with(itemView) {
            val cover = findViewById<ImageView>(R.id.dp_image)
            val url = item.pimages[0].medium.purl
            if(url.isNotEmpty()) {
                Glide.with(cover.context)
                    .load(url)
                    .into(cover)
            }

            val productTitle = findViewById<TextView>(R.id.dp_title)
            productTitle.text = item.title
            val productShop = findViewById<TextView>(R.id.dp_shopname)
            productShop.text = item.pshop.name
        }
    }

}