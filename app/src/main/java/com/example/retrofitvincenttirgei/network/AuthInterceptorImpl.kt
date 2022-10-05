package com.example.retrofitvincenttirgei.network

import android.app.Application
import android.content.Context
import com.example.retrofitvincenttirgei.MainActivity
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection

//class AuthInterceptorImpl @Inject constructor() {
class AuthInterceptorImpl(context: Context): Interceptor {

    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var accessToken = sessionManager.fetchAuthToken()

        val response = chain.proceed(newRequestWithAccessToken(accessToken, request))

        if(response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            val newAccessToken = sessionManager.fetchAuthToken()
            if(newAccessToken != accessToken) {
                return chain.proceed(newRequestWithAccessToken(accessToken, request))
            } else {
                accessToken = refreshToken()
                if(accessToken.isNullOrBlank()) {
                    sessionManager.logout()
                    return response
                }
                return chain.proceed(newRequestWithAccessToken(accessToken, request))
            }
        }
        return response
    }

    private fun newRequestWithAccessToken(accessToken: String?, request: Request): Request =
        request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()

    private fun refreshToken(): String? {
        synchronized(this) {
            val refreshToken = sessionManager.fetchAuthToken()
            refreshToken?.let {
                return sessionManager.refreshToken(refreshToken)
            } ?: return null
        }
    }
}