package com.example.ing_app.ui.posts

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.ing_app.R
import com.example.ing_app.databinding.FragmentPostBinding
import kotlinx.android.synthetic.main.fragment_post.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class PostFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private val viewModel: PostViewModel by sharedViewModel()
    // Propably it can be done in databinding
    private var isDark: Boolean = false

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // I am not sure if it's good idea
        isDark = !isDark
        setHasOptionsMenu(false)
        setHasOptionsMenu(true)
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        if(!isDark) {
            menu.findItem(R.id.theme_icon_menu)
                .setIcon(R.drawable.ic_brightness_low_black_24dp)
                .setTitle(R.string.change_dark_theme)
        } else {
            menu.findItem(R.id.theme_icon_menu)
                .setIcon(R.drawable.ic_brightness_high_black_24dp)
                .setTitle(R.string.change_light_theme)
        }
        super.onPrepareOptionsMenu(menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        swipeRefreshLayout.setOnRefreshListener() {
            Timber.d("onRefreshListener")
            onRefresh()
        }
        setHasOptionsMenu(true)
    }

    override fun onRefresh() {
        viewModel.getPosts()
        swipeRefreshLayout.isRefreshing = false
    }
}