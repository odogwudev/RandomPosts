package com.odogwudev.benshiposts.data.remote.model.user

import com.odogwudev.benshiposts.data.local.dto.user.CompanyDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CompanyResponse(

    @Json(name = "bs")
    val bs: String? = null,

    @Json(name = "catchPhrase")
    val catchPhrase: String? = null,

    @Json(name = "name")
    val name: String? = null

)

fun CompanyResponse.toDto() = CompanyDto(
    bs = this.bs.orEmpty(),
    catchPhrase = this.catchPhrase.orEmpty(),
    name = this.name.orEmpty()
)
