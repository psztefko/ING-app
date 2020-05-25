package com.example.ing_app.ui.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ing_app.databinding.FragmentImagesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_post.*
import timber.log.Timber
import kotlin.properties.Delegates

class ImageFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    var args by Delegates.notNull<Int>()
    private val viewModel: ImageViewModel by viewModel{ parametersOf(args) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentImagesBinding.inflate(inflater)

        binding.lifecycleOwner = this

        args = ImageFragmentArgs.fromBundle(requireArguments()).userId

        binding.viewModel = viewModel

        binding.photoGrid.adapter = PhotoGridAdapter( PhotoGridAdapter.OnClickListener {
            photo -> viewModel.onImageFullImageClicked(photo.url)
        })

        viewModel.navigateToUser.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    ImageFragmentDirections.imagesToPosts())
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
        viewModel.getAlbums()
        swipeRefreshLayout.isRefreshing = false
    }
}