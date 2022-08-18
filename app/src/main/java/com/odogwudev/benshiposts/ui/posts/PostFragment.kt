package com.odogwudev.benshiposts.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.odogwudev.benshiposts.databinding.FragmentPostsBinding
import com.odogwudev.benshiposts.shared.utils.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PostViewModel by viewModels()

    private val postAdapter: PostAdapter by lazy {
        PostAdapter { view, post ->
            view.findNavController()
                .navigate(PostFragmentDirections.actionPostFragmentToPostDetailFragment(post))
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.postsList.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        viewModel.posts.observe(viewLifecycleOwner) { uiState ->
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
                        postsList.visibility = View.GONE
                    }
                }
                is UIState.Success -> {
                    binding.apply {
                        shimmerContainer.apply {
                            stopShimmer()
                            visibility = View.GONE
                        }
                        postsList.visibility = View.VISIBLE
                    }
                    postAdapter.submitList(uiState.data)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.postsList.adapter = null
        _binding = null
    }
}
