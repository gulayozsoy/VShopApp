package com.example.vshopapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vshopapp.adapters.DEAdapter
import com.example.vshopapp.model.EditorShops
import kotlinx.android.synthetic.main.activity_editor.*

class EditorActivity : AppCompatActivity() {

    private var editor = ArrayList<EditorShops>()
    private var deAdapter: DEAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        setSupportActionBar(de_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if(intent.extras != null) {
            editor = intent.getSerializableExtra("editor") as ArrayList<EditorShops>
        }

        deAdapter = DEAdapter(editor)
        de_recycler_view.adapter = deAdapter
    }
}