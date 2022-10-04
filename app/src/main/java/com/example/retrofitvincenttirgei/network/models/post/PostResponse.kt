package com.example.retrofitvincenttirgei.network.models.post

import com.google.gson.annotations.SerializedName

data class PostsResponse (
    @SerializedName("status_code")
    var status: Int,

    @SerializedName("message")
    var message: String,

    @SerializedName("posts")
    var posts: List<Post>
)