package com.example.retrofitvincenttirgei

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofitvincenttirgei.network.ApiClient
import com.example.retrofitvincenttirgei.network.SessionManager
import com.example.retrofitvincenttirgei.network.models.LoginRequest
import com.example.retrofitvincenttirgei.network.models.LoginResponse
import com.example.retrofitvincenttirgei.network.models.PostsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Article
 * https://medium.com/android-news/token-authorization-with-retrofit-android-oauth-2-0-747995c79720
 */

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

//        login()
        fetchPosts()
    }

    private fun login() {
        apiClient.getApiService().login(LoginRequest(email = "s@sample.com", password = "mypassword"))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Error logging in
                    Toast.makeText(this@MainActivity, "Failed to login!", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val loginResponse = response.body()

                    if (loginResponse?.statusCode == 200) {
                        Toast.makeText(this@MainActivity, "Successfull login!", Toast.LENGTH_SHORT)
                            .show()
                        sessionManager.saveAuthToken(loginResponse.authToken)
                    } else {
                        // Error logging in
                        Toast.makeText(this@MainActivity, "Failed to login on Response!", Toast.LENGTH_SHORT).show()
                    }
                }
            })    }

    private fun fetchPosts() {

        // Pass the token as parameter
        apiClient.getApiService().fetchPosts(token = "Bearer ${sessionManager.fetchAuthToken()}")
            .enqueue(object : Callback<PostsResponse> {
                override fun onFailure(call: Call<PostsResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error fetching posts!", Toast.LENGTH_SHORT).show()
                    println("vladr: ${t.message}")
                }

                override fun onResponse(call: Call<PostsResponse>, response: Response<PostsResponse>) {
                    Toast.makeText(this@MainActivity, "Success fetching posts!", Toast.LENGTH_SHORT).show()
                }
            })
    }
}