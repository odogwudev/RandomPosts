package com.odogwudev.benshiposts.data.remote.model.user

import com.odogwudev.benshiposts.data.local.dto.user.GeoDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GeoResponse(

    @Json(name = "lng")
    val lng: String? = null,

    @Json(name = "lat")
    val lat: String? = null

)

fun GeoResponse.toDto() = GeoDto(
    lng = this.lng.orEmpty(),
    lat = this.lat.orEmpty()
)
