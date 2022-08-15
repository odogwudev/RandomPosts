package com.odogwudev.benshiposts.data.local.dto.user

data class AddressDto(
    val zipcode: String = "",
    val geo: GeoDto = GeoDto(),
    val suite: String = "",
    val city: String = "",
    val street: String = ""
)
