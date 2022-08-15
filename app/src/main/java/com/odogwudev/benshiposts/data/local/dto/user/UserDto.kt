package com.odogwudev.benshiposts.data.local.dto.user

data class UserDto(
    val website: String = "",
    val address: AddressDto = AddressDto(),
    val phone: String = "",
    val name: String = "",
    val company: CompanyDto = CompanyDto(),
    val id: Int = 0,
    val email: String = "",
    val username: String = ""
)
