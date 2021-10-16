package com.example.vshopapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vshopapp.adapters.DNSAdapter
import com.example.vshopapp.model.EditorShops
import kotlinx.android.synthetic.main.activity_editor.*
import kotlinx.android.synthetic.main.activity_new_shops.*

class NewShopsActivity : AppCompatActivity() {

    private var newshops = ArrayList<EditorShops>()
    private var dnsAdapter: DNSAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_shops)

        setSupportActionBar(dns_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if(intent.extras != null) {
            newshops = intent.getSerializableExtra("newshops") as ArrayList<EditorShops>
        }

        dnsAdapter = DNSAdapter(newshops)
        dns_recycler_view.adapter = dnsAdapter

    }
}