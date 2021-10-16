package com.example.vshopapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vshopapp.adapters.DPAdapter
import com.example.vshopapp.model.Product
import kotlinx.android.synthetic.main.activity_products.*

class ProductsActivity : AppCompatActivity() {

    private var product = ArrayList<Product>()
    private var dpAdapter: DPAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        setSupportActionBar(dp_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if(intent.extras != null) {
            product = intent.getSerializableExtra("products") as ArrayList<Product>
        }

        dpAdapter = DPAdapter(product)
        dp_recycler_view.adapter = dpAdapter
    }
}