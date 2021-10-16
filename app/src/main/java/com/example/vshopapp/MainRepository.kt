package com.example.vshopapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vshopapp.data.ApiClient
import com.example.vshopapp.data.ApiService
import com.example.vshopapp.model.DiscoverResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {

    private val apiService: ApiService by lazy { ApiClient.getApiService() }

    fun getData(): LiveData<ArrayList<DiscoverResults>>? {
        val allData: MutableLiveData<ArrayList<DiscoverResults>> = MutableLiveData()
        apiService.getSections().enqueue(object : Callback<ArrayList<DiscoverResults>> {
            override fun onFailure(call: Call<ArrayList<DiscoverResults>>, t: Throwable) {
                Log.e("getSectionsError", t.message!!)
            }

            override fun onResponse( call: Call<ArrayList<DiscoverResults>>,
                response: Response<ArrayList<DiscoverResults>>) {
                allData.value = response.body()
            }
        })
        return allData
    }
}
