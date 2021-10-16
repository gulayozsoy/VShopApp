package com.example.vshopapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.vshopapp.model.DiscoverResults

class AllDataViewModel: ViewModel() {

    private val repository: com.example.vshopapp.MainRepository by lazy{ com.example.vshopapp.MainRepository() }

    fun getData(): LiveData<ArrayList<DiscoverResults>>? = repository.getData()
}