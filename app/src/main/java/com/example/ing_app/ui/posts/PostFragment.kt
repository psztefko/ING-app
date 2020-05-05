package com.example.ing_app.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.ing_app.databinding.FragmentPostBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PostFragment : Fragment() {
    private val viewModel: PostViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPostBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val adapter = PostAdapter( UserClickListener {
            userId -> viewModel.onPostUserClicked(userId)
        },
        CommentClickListener {
            id -> viewModel.onPostCommentClicked(id)
        })
        binding.postsList.adapter = adapter

        viewModel.posts.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToSelectedUser.observe(viewLifecycleOwner, Observer {userId ->
            userId?.let {
                this.findNavController().navigate(PostFragmentDirections.postsToUsername(userId))
                viewModel.displayUserComplete()
            }
        })

        viewModel.navigateToSelectedComments.observe(viewLifecycleOwner, Observer {id ->
            id?.let {
                this.findNavController().navigate(PostFragmentDirections.postsToComments(id))
                viewModel.displayCommentsComplete()
            }
        })

        return binding.root
    }
}