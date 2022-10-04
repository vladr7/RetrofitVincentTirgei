package com.example.retrofitvincenttirgei.network.models.user

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class User (

    @SerialName("id")
    var id: String,

    @SerialName("name")
    var name: String,

    @SerialName("job")
    var job: String,

    @SerialName("createdAt")
    var createdAt: String
)