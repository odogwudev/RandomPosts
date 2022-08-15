package com.odogwudev.benshiposts.data.remote.model

import com.odogwudev.benshiposts.data.local.dto.comment.CommentDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentResponse(
	@Json(name = "id")
	val id: Int? = null,

	@Json(name = "name")
	val name: String? = null,

	@Json(name = "postId")
	val postId: Int? = null,

	@Json(name = "body")
	val body: String? = null,

	@Json(name = "email")
	val email: String? = null

)

fun CommentResponse.toDto() = CommentDto(
	id = this.id ?: 0,
	name = this.name.orEmpty(),
	postId = this.postId ?: 0,
	body = this.body.orEmpty(),
	email = this.email.orEmpty()
)
