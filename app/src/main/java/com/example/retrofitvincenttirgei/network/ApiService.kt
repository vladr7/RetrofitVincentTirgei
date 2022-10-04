package com.example.retrofitvincenttirgei.network

import com.example.retrofitvincenttirgei.Constants
import com.example.retrofitvincenttirgei.network.models.LoginRequest
import com.example.retrofitvincenttirgei.network.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @POST(Constants.LOGIN_URL)
    @FormUrlEncoded
    fun login(@Body request: LoginRequest): Call<LoginResponse>

}