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

class EditorAdapter (var editorlist: ArrayList<EditorShops>): RecyclerView.Adapter<EditorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.editor_item, parent, false))
    }

    override fun getItemCount(): Int = editorlist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(editorlist[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: EditorShops)= with(itemView) {

            val logo = findViewById<ImageView>(R.id.editorlogo)
            val url = item.elogo.url
            if(url.isNotEmpty()) {
                Glide.with(logo.context)
                    .load(url)
                    .into(logo)
            }

            val smallview1 = findViewById<ImageView>(R.id.imageView1)
            val smallview2 = findViewById<ImageView>(R.id.imageView2)
            val smallview3 = findViewById<ImageView>(R.id.imageView3)

            val imageviewarray = ArrayList<ImageView>()
            imageviewarray.add(smallview1)
            imageviewarray.add(smallview2)
            imageviewarray.add(smallview3)


            for (i in 0 until item.popularproducts.size) {
                val eurl = item.popularproducts[i].images[0].emedium.url
                if(eurl.isNotEmpty()) {
                    Glide.with(imageviewarray[i].context)
                        .load(eurl)
                           .into(imageviewarray[i])
                }
            }

            val editorName = findViewById<TextView>(R.id.editor_name)
            editorName.text = item.name
            val editorDefinition = findViewById<TextView>(R.id.editor_definition)
            editorDefinition.text = item.definition

            val backgroundImage = findViewById<ImageView>(R.id.editor_cover)

        }
    }

    // Clean all elements of the recycler
    fun clear() {
        editorlist.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(list: ArrayList<EditorShops>) {
        editorlist.addAll(list)
        notifyDataSetChanged()
    }

}