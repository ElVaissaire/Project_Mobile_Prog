package com.example.project_mobile_prog.presentation.api

import com.example.project_mobile_prog.presentation.list.GW2
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GW2Api {
    @GET("item_details")
    fun getItemList(@Query("item_id") item_id: Int): Call<GW2>
}