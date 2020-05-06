package com.example.ing_app.ui.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ing_app.databinding.FragmentImagesBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf
import kotlin.properties.Delegates

class ImageFragment : Fragment() {
    var args by Delegates.notNull<Int>()
    private val viewModel: ImageViewModel by sharedViewModel{ parametersOf(args) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentImagesBinding.inflate(inflater)

        binding.lifecycleOwner = this

        args = ImageFragmentArgs.fromBundle(requireArguments()).userId

        binding.viewModel = viewModel

        /*val adapter = PhotoGridAdapter( PhotoGridAdapter.OnClickListener {
                photo -> viewModel.onImageFullImageClicked(photo.url)
        })

        binding.adapter = adapter*/

        return binding.root
    }
}