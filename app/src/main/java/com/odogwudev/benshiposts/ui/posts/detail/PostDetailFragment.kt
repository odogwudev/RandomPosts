package com.odogwudev.benshiposts.ui.posts.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.odogwudev.benshiposts.R
import com.odogwudev.benshiposts.databinding.FragmentPostDetailBinding
import com.odogwudev.benshiposts.shared.HashUtils
import com.odogwudev.benshiposts.shared.utils.UIState
import com.odogwudev.benshiposts.ui.posts.comment.CommentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : Fragment() {

    private var _binding: FragmentPostDetailBinding? = null
    private val binding get() = _binding!!

    private val args: PostDetailFragmentArgs by navArgs()

    private val viewModel: PostDetailViewModel by viewModels()

    private val commentAdapter: CommentAdapter by lazy {
        CommentAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postDto = args.postDto

        binding.apply {
            post = postDto
            commentList.apply {
                adapter = commentAdapter
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
            }
            toolbar.setNavigationOnClickListener {
                it.findNavController().navigateUp()
            }
            userName.setOnClickListener {
                it.findNavController().navigate(
                    PostDetailFragmentDirections.actionPostDetailFragmentToUserDetailFragment(
                        postDto.userId
                    )
                )
            }
            post?.apply {
                val imageLink = title
                val hashedLinkForSeed = imageLink.let { HashUtils.sha256(it) }
                val getImageUrl = "https://picsum.photos/seed/$hashedLinkForSeed/200/200"

                Glide.with(ivPostImg)
                    .load(getImageUrl)
                    .placeholder(R.drawable.ic_place_holder)
                    .into(ivPostImg)

            }

        }
        viewModel.getCommentByPostId(postDto.id)

        observeComment()
    }

    private fun observeComment() {
        viewModel.comment.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Error -> {
                    Toast.makeText(requireContext(), uiState.message, Toast.LENGTH_SHORT).show()
                }
                is UIState.Loading -> {
                    binding.apply {
                        shimmerContainer.apply {
                            visibility = View.VISIBLE
                            startShimmer()
                        }
                        commentList.visibility = View.GONE
                    }
                }
                is UIState.Success -> {
                    binding.apply {
                        shimmerContainer.apply {
                            stopShimmer()
                            visibility = View.GONE
                        }
                        commentList.visibility = View.VISIBLE
                    }
                    commentAdapter.submitList(uiState.data)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.commentList.adapter = null
        _binding = null
    }
}
