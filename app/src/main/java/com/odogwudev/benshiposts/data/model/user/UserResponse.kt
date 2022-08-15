package com.odogwudev.benshiposts.data.model.user

import com.odogwudev.benshiposts.data.local.dto.user.AddressDto
import com.odogwudev.benshiposts.data.local.dto.user.CompanyDto
import com.odogwudev.benshiposts.data.local.dto.user.UserDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(

    @Json(name = "website")
    val website: String? = null,

    @Json(name = "address")
    val address: AddressResponse? = null,

    @Json(name = "phone")
    val phone: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "company")
    val company: CompanyResponse? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "email")
    val email: String? = null,

    @Json(name = "username")
    val username: String? = null

)

fun UserResponse.toDto() = UserDto(
    website = this.website.orEmpty(),
    address = this.address?.toDto() ?: AddressDto(),
    phone = this.phone.orEmpty(),
    name = this.name.orEmpty(),
    company = this.company?.toDto() ?: CompanyDto(),
    id = this.id ?: 0,
    email = this.email.orEmpty(),
    username = this.username.orEmpty()
)
