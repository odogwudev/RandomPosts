package com.odogwudev.benshiposts.data.model.user

import com.odogwudev.benshiposts.data.local.dto.user.AddressDto
import com.odogwudev.benshiposts.data.local.dto.user.GeoDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddressResponse(

    @Json(name="zipcode")
    val zipcode: String? = null,

    @Json(name="geo")
    val geo: GeoResponse? = null,

    @Json(name = "suite")
    val suite: String? = null,

    @Json(name = "city")
    val city: String? = null,

    @Json(name = "street")
    val street: String? = null

)

fun AddressResponse.toDto() = AddressDto(
    zipcode = this.zipcode.orEmpty(),
    geo = if (this.geo != null) this.geo.toDto() else GeoDto("", ""),
    suite = this.suite.orEmpty(),
    city = this.city.orEmpty(),
    street = this.street.orEmpty()
)
