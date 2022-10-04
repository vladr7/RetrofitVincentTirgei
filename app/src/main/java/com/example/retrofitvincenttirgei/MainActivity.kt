package com.example.retrofitvincenttirgei

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.retrofitvincenttirgei.network.ApiClient
import com.example.retrofitvincenttirgei.network.SessionManager
import com.example.retrofitvincenttirgei.network.models.login.LoginRequest
import com.example.retrofitvincenttirgei.network.models.login.LoginResponse
import com.example.retrofitvincenttirgei.network.models.user.UserRequest
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Article
 * https://medium.com/android-news/token-authorization-with-retrofit-android-oauth-2-0-747995c79720
 *
 * Dummy REST API
 * https://dummyjson.com/docs/auth
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
        lifecycleScope.launch {
            createUser()
//            fetchResources()
        }

    }

    private suspend fun fetchResources() {
        try {
            val result = apiClient.getApiService(this)
                .getResources()
            println("vladr: ${result}")
        } catch (e: java.lang.Exception) {
            println("vladr: ${e.message}")
        }
    }

    private suspend fun createUser() {
        val userRequest = UserRequest(
            "Ion",
            "Smile"
        )
        try {
            val result = apiClient.getApiService(this)
                .createUser(userRequest)
            println("vladr: $result")
        } catch (e: java.lang.Exception) {
            println("vladr: ${e.message}")
        }

    }

    private fun login() {
        apiClient.getApiService(this)
            .login(LoginRequest(email = "eve.holt@reqres.in", password = "mypassword"))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Error logging in
                    Toast.makeText(this@MainActivity, "Failed to login!", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                    println("vladr: ${t.message}")
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val loginResponse = response.body()
                    Toast.makeText(this@MainActivity, "OnResponse", Toast.LENGTH_SHORT).show()
                    println("vladr: ${loginResponse?.token}")

//                    if (loginResponse?.statusCode == 200) {
//                        Toast.makeText(this@MainActivity, "Successfull login!", Toast.LENGTH_SHORT)
//                            .show()
//                        sessionManager.saveAuthToken(loginResponse.authToken)
//                    } else {
//                        // Error logging in
//                        Toast.makeText(
//                            this@MainActivity,
//                            "Failed to login on Response!",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        println("vladr: ${loginResponse?.statusCode}")
//                    }
                }
            })
    }
}