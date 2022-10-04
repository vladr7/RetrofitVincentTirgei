package com.example.retrofitvincenttirgei.network.models.user

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class UserRequest(
    @SerialName("name")
    var name: String,

    @SerialName("job")
    var job: String
)
