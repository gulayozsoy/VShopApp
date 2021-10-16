package com.example.vshopapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.vshopapp.model.Collectioon
import kotlinx.android.synthetic.main.activity_collections.*

class CollectionsActivity : AppCompatActivity() {

    private var collection = ArrayList<Collectioon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collections)

        setSupportActionBar(dco_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        if(intent.extras != null) {
            collection = intent.getSerializableExtra("collections") as ArrayList<Collectioon>
        }

        val dcoimage = findViewById<ImageView>(R.id.dco_image)

        dco_title.text = collection[0].title
        dco_definition.text = collection[0].definition

        val url = collection[0].cocover.courl
        if(url.isNotEmpty()) {
            Glide.with(dcoimage.context)
                .load(url)
                .into(dcoimage)
        }
    }
}