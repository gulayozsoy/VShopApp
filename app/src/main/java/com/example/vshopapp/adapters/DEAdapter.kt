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

class DEAdapter(var editorlist: ArrayList<EditorShops>): RecyclerView.Adapter<DEAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.detail_editor_item, parent, false))
    }

    override fun getItemCount(): Int = editorlist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(editorlist[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: EditorShops)= with(itemView) {

            val logo = findViewById<ImageView>(R.id.delogo)
            val url = item.elogo.url
            if(url.isNotEmpty()) {
                Glide.with(logo.context)
                    .load(url)
                    .into(logo)
            }

            val smallview1 = findViewById<ImageView>(R.id.eimageView1)
            val smallview2 = findViewById<ImageView>(R.id.eimageView2)
            val smallview3 = findViewById<ImageView>(R.id.eimageView3)

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

            val editorName = findViewById<TextView>(R.id.de_name)
            editorName.text = item.name
            val editorDefinition = findViewById<TextView>(R.id.de_definition)
            editorDefinition.text = item.definition

        }
    }

}