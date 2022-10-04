package com.example.retrofitvincenttirgei.network.models.resource

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("color")
    val color: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("pantone_value")
    val pantone_value: String,

    @SerializedName("year")
    val year: Int
)