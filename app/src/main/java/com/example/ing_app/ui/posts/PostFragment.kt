package com.example.ing_app.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.ing_app.R
import com.example.ing_app.R.id.fragment_post
import com.example.ing_app.databinding.FragmentPostBinding
import com.google.android.gms.dynamic.SupportFragmentWrapper
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.fragment_post.view.*
import kotlinx.android.synthetic.main.fragment_post.view.swipeRefreshLayout
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class PostFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        swipeRefreshLayout.setOnRefreshListener() {
            Timber.d("onRefreshListener")
            onRefresh()
        }
    }

    override fun onRefresh() {
        val viewGroup: ViewGroup? = view?.findViewById(R.id.fragment_post)
        viewGroup?.invalidate()

        //swipeRefreshLayout.isRefreshing = false
    }

    fun RefreshFragment(){

        //getFragmentManager()?.beginTransaction()?.detach(this)?.attach(this)?.commit();
/*        Timber.d("onRefresh")
        findNavController().navigate(
            fragment_post,
            arguments,
            NavOptions.Builder()
                .setPopUpTo(fragment_post, true)
                .build()
        )*/
    }
}

