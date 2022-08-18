package com.odogwudev.benshiposts.shared

import androidx.recyclerview.widget.DiffUtil
import com.odogwudev.benshiposts.data.local.dto.comment.CommentDto
import com.odogwudev.benshiposts.data.local.dto.post.PostDto
import java.security.MessageDigest

object Constants {
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val postDiff = object : DiffUtil.ItemCallback<PostDto>() {
        override fun areItemsTheSame(oldItem: PostDto, newItem: PostDto): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PostDto, newItem: PostDto): Boolean =
            oldItem == newItem
    }

    val commentDiff = object : DiffUtil.ItemCallback<CommentDto>() {
        override fun areItemsTheSame(oldItem: CommentDto, newItem: CommentDto): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CommentDto, newItem: CommentDto): Boolean =
            oldItem == newItem
    }
}



object HashUtils {
    fun sha512(input: String) = hashString("SHA-512", input)

    fun sha256(input: String) = hashString("SHA-256", input)

    fun sha1(input: String) = hashString("SHA-1", input)


    private fun hashString(type: String, input: String): String {
        val HEX_CHARS = "0123456789ABCDEF"
        val bytes = MessageDigest
            .getInstance(type)
            .digest(input.toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(HEX_CHARS[i shr 4 and 0x0f])
            result.append(HEX_CHARS[i and 0x0f])
        }

        return result.toString()
    }
}