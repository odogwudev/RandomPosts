package com.odogwudev.benshiposts.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.odogwudev.benshiposts.R
import com.odogwudev.benshiposts.data.local.dto.post.PostDto
import com.odogwudev.benshiposts.databinding.PostItemBinding
import com.odogwudev.benshiposts.shared.Constants.postDiff
import com.odogwudev.benshiposts.shared.HashUtils
import com.odogwudev.benshiposts.shared.HashUtils.sha256

class PostAdapter(private val clickListener: (view: View, post: PostDto) -> Unit) :
    ListAdapter<PostDto, PostAdapter.ViewHolder>(postDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: PostItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.post_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val currentItem = getItem(position)
            binding.post = getItem(position)
            itemView.setOnClickListener {
                clickListener(it, getItem(position))
            }

            //found this online
            val imageLink = currentItem?.title
            val hashedLinkForSeed = imageLink?.let { sha256(it) }
            val getImageUrl = "https://picsum.photos/seed/$hashedLinkForSeed/200/200"

            Glide.with(holder.itemView.context).load(getImageUrl)
                .placeholder(R.drawable.ic_place_holder)
                .into(holder.binding.ivPostImg)
        }
    }


    inner class ViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root)


}

