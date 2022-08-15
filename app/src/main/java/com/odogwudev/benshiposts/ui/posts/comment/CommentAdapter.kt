package com.odogwudev.benshiposts.ui.posts.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.odogwudev.benshiposts.data.local.dto.comment.CommentDto
import com.odogwudev.benshiposts.databinding.CommentItemBinding
import com.odogwudev.benshiposts.shared.Constants

class CommentAdapter : ListAdapter<CommentDto, CommentAdapter.ViewHolder>(Constants.commentDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            binding.comment = getItem(position)
        }
    }

    inner class ViewHolder(val binding: CommentItemBinding) : RecyclerView.ViewHolder(binding.root)
}
