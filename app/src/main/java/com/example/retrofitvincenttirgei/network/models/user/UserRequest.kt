package com.example.retrofitvincenttirgei.network.models.user

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("name")
    var name: String,

    @SerializedName("job")
    var job: String
)
