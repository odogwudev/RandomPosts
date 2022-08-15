package com.odogwudev.benshiposts.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostResponse(

    @Json(name = "id")
    val id: Int? = 0,

    @Json(name = "title")
    val title: String? = "",

    @Json(name = "body")
    val body: String? = "",

    @Json(name = "userId")
    val userId: Int? = 0

)
