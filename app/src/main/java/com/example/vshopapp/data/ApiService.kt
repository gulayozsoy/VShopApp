package com.example.vshopapp.data

import com.example.vshopapp.model.DiscoverResults
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    //Sections
    @GET("api/v2/discover")
    fun getSections(): Call<ArrayList<DiscoverResults>>

}