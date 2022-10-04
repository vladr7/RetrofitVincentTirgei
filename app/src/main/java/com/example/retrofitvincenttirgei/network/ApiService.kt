package com.example.retrofitvincenttirgei.network

import com.example.retrofitvincenttirgei.Constants
import com.example.retrofitvincenttirgei.network.models.login.LoginRequest
import com.example.retrofitvincenttirgei.network.models.login.LoginResponse
import com.example.retrofitvincenttirgei.network.models.post.PostsResponse
import com.example.retrofitvincenttirgei.network.models.user.User
import com.example.retrofitvincenttirgei.network.models.user.UserRequest
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

//    @FormUrlEncoded
    @POST(Constants.LOGIN_URL)
    fun login(@Body request: LoginRequest): Call<LoginResponse> // bullshit callbacks :)

    @GET(Constants.POSTS_URL)
    fun fetchPosts(): Call<PostsResponse>

    @POST(Constants.CREATE_USERS_URL)
    suspend fun createUser(@Body userRequest: UserRequest): User // the new God in town

}