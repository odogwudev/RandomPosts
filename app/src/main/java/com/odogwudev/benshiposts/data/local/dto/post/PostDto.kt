package com.odogwudev.benshiposts.data.local.dto.post

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostDto(
    val id: Int,
    val username: String,
    val userCompanyName: String,
    val title: String,
    val body: String,
    val userId: Int
) : Parcelable
