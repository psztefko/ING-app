package com.example.ing_app.ui.posts

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.ing_app.R
import com.example.ing_app.databinding.FragmentPostBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.fragment_post.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

lateinit var mAdView : AdView

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

        val adView = AdView(context)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = getString(R.string.admob_banner_ad)
        MobileAds.initialize(context) {}
        mAdView = binding.adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?){
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.theme_icon_menu -> {
            ContextThemeWrapper(context, R.style.LightTheme).apply { setTheme(R.style.DarkTheme) }

            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        swipeRefreshLayout.setOnRefreshListener() {
            Timber.d("onRefreshListener")
            onRefresh()
        }
    }

    override fun onRefresh() {
        viewModel.getPosts()
        swipeRefreshLayout.isRefreshing = false
    }
}