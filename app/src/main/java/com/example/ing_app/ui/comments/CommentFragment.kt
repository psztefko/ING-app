package com.example.ing_app.ui.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.ing_app.databinding.FragmentCommentsBinding
import com.example.ing_app.ui.user.UserFragmentArgs
import kotlinx.android.synthetic.main.fragment_post.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber
import kotlin.properties.Delegates

class CommentFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    var args by Delegates.notNull<Int>()
    private val viewModel: CommentViewModel by sharedViewModel {parametersOf(args)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCommentsBinding.inflate(inflater)

        binding.lifecycleOwner = this

        args = UserFragmentArgs.fromBundle(requireArguments()).id

        Timber.d("On comment args given: ${args}")

        binding.viewModel = viewModel

        val adapter = CommentAdapter()
        binding.commentsList.adapter = adapter

        viewModel.comments.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToPosts.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    CommentFragmentDirections.commentsToPosts())
                viewModel.doneNavigating()
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        swipeRefreshLayout.setOnRefreshListener() {
            Timber.d("onRefreshListener")
            onRefresh()
        }
    }

    override fun onRefresh() {
        viewModel.getComments()
        swipeRefreshLayout.isRefreshing = false
    }

}