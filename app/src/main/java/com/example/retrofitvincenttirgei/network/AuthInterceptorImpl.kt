package com.example.retrofitvincenttirgei.network

import android.app.Application
import com.example.retrofitvincenttirgei.MainActivity
import okhttp3.Interceptor
import okhttp3.Response

//class AuthInterceptorImpl @Inject constructor() {
class AuthInterceptorImpl: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var accessToken = 
    }
}