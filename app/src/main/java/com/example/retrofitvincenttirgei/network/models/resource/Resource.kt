package com.example.retrofitvincenttirgei.network.models.resource

import com.google.gson.annotations.SerializedName

data class Resource(

    @SerializedName("data")
    val data: Data,

    @SerializedName("support")
    val support: Support
)