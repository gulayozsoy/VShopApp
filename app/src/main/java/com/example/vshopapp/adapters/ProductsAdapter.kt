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


class ProductsAdapter(var productlist: ArrayList<Product>): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false))
    }

    override fun getItemCount(): Int = productlist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(productlist[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(item: Product)= with(itemView) {
                val cover = findViewById<ImageView>(R.id.product_image)
                val url = item.pimages[0].medium.purl
                if(url.isNotEmpty()) {
                    Glide.with(cover.context)
                        .load(url)
                        .into(cover)
                }

                val productTitle = findViewById<TextView>(R.id.product_detail_title)
                productTitle.text = item.title
                val productShop = findViewById<TextView>(R.id.product_shop_name)
                productShop.text = item.pshop.name
            }
    }

    // Clean all elements of the recycler
    fun clear() {
        productlist.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(list: ArrayList<Product>) {
        productlist.addAll(list)
        notifyDataSetChanged()
    }

}