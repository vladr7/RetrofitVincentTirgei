package com.example.retrofitvincenttirgei.network

import com.example.retrofitvincenttirgei.Constants
import com.example.retrofitvincenttirgei.network.models.LoginRequest
import com.example.retrofitvincenttirgei.network.models.LoginResponse
import com.example.retrofitvincenttirgei.network.models.PostsResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST(Constants.LOGIN_URL)
    @FormUrlEncoded
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET(Constants.POSTS_URL)
    fun fetchPosts(): Call<PostsResponse>

}