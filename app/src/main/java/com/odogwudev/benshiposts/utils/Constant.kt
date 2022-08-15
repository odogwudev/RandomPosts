package com.odogwudev.benshiposts.utils

import androidx.recyclerview.widget.DiffUtil
import com.odogwudev.benshiposts.data.local.dto.comment.CommentDto
import com.odogwudev.benshiposts.data.local.dto.post.PostDto

object Constant {
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
