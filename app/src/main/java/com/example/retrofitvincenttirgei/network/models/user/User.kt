package com.example.retrofitvincenttirgei.network.models.user

import com.google.gson.annotations.SerializedName

data class User (

    @SerializedName("id")
    var id: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("job")
    var job: String,

    @SerializedName("createdAt")
    var createdAt: String
)