package com.example.retrofitvincenttirgei.network.models.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse (

    @SerialName("token")
    val token: String,
)