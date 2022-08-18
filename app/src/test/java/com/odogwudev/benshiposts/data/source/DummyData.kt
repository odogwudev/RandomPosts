package com.odogwudev.benshiposts.data.source

import com.odogwudev.benshiposts.data.local.dto.comment.CommentDto
import com.odogwudev.benshiposts.data.local.dto.post.PostDto
import com.odogwudev.benshiposts.data.local.dto.user.AddressDto
import com.odogwudev.benshiposts.data.local.dto.user.CompanyDto
import com.odogwudev.benshiposts.data.local.dto.user.UserDto
import com.odogwudev.benshiposts.data.model.CommentResponse
import com.odogwudev.benshiposts.data.model.PostResponse
import com.odogwudev.benshiposts.data.model.user.CompanyResponse
import com.odogwudev.benshiposts.data.model.user.UserResponse

object DummyData {

    const val userId = 1
    const val id = 1
    const val postId = 1
    const val albumId = 1

    val dummyPostsResponse = listOf(
        PostResponse(id = 1, userId = userId),
        PostResponse(id = 2, userId = userId),
        PostResponse(id = 3, userId = userId)
    )
    val dummyPost = PostResponse(id)
    val dummyPostDto = listOf(
        PostDto(
            id = 1,
            userId = 1,
            username = "a",
            userCompanyName = "aa",
            body = "",
            title = ""
        ),
        PostDto(
            id = 2,
            userId = 1,
            username = "a",
            userCompanyName = "aa",
            body = "",
            title = ""
        ),
        PostDto(
            id = 3,
            userId = 1,
            username = "a",
            userCompanyName = "aa",
            body = "",
            title = ""
        ),
        PostDto(
            id = 1,
            userId = 2,
            username = "b",
            userCompanyName = "bb",
            body = "",
            title = ""
        ),
        PostDto(
            id = 2,
            userId = 2,
            username = "b",
            userCompanyName = "bb",
            body = "",
            title = ""
        ),
        PostDto(
            id = 3,
            userId = 2,
            username = "b",
            userCompanyName = "bb",
            body = "",
            title = ""
        ),
        PostDto(
            id = 1,
            userId = 3,
            username = "c",
            userCompanyName = "cc",
            body = "",
            title = ""
        ),
        PostDto(
            id = 2,
            userId = 3,
            username = "c",
            userCompanyName = "cc",
            body = "",
            title = ""
        ),
        PostDto(
            id = 3,
            userId = 3,
            username = "c",
            userCompanyName = "cc",
            body = "",
            title = ""
        ),
    )

    val dummyUsersResponse = listOf(
        UserResponse(id = 1, username = "a", company = CompanyResponse(name = "aa")),
        UserResponse(id = 2, username = "b", company = CompanyResponse(name = "bb")),
        UserResponse(id = 3, username = "c", company = CompanyResponse(name = "cc")),
    )
    val dummyUserResponse =
        UserResponse(id = 1, username = "a", company = CompanyResponse(name = "aa"))
    val dummyUserDto = UserDto(
        id = 1, username = "a",
        company = CompanyDto(name = "aa"),
        address = AddressDto()
    )

    val dummyCommentsResponse = listOf(
        CommentResponse(id = 1, postId = postId),
        CommentResponse(id = 2, postId = postId),
        CommentResponse(id = 3, postId = postId),
    )
    val dummyCommentsDto = listOf(
        CommentDto(id = 1, postId = 1, body = "", email = "", name = ""),
        CommentDto(id = 2, postId = 1, body = "", email = "", name = ""),
        CommentDto(id = 3, postId = 1, body = "", email = "", name = "")
    )


}