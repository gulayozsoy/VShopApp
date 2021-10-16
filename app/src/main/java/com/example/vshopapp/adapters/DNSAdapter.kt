package com.example.vshopapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vshopapp.R
import com.example.vshopapp.model.EditorShops

class DNSAdapter(var nslist: ArrayList<EditorShops>): RecyclerView.Adapter<DNSAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.detail_newshops_item, parent, false))
    }

    override fun getItemCount(): Int = nslist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(nslist[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: EditorShops)= with(itemView) {
            var cover = findViewById<ImageView>(R.id.dns_image)
            val myDrawable = resources.getDrawable(R.drawable.ic_launcher_foreground)


            if (item.escover != null && item.escover.esurl != null) {
                val url = item.escover.esurl
                Glide.with(cover.context)
                    .load(url)
                    .into(cover)
            }

            val newshopsName = findViewById<TextView>(R.id.dns_name)
            newshopsName.text = item.name
            val nsDefinition = findViewById<TextView>(R.id.dns_definition)
            nsDefinition.text = item.definition
            val nscount = findViewById<TextView>(R.id.dns_count)
            nscount.text = item.nsproductcount
        }
    }
}