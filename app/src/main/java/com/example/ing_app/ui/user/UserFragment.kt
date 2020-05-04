package com.example.ing_app.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.ing_app.databinding.FragmentUserBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf
import kotlin.properties.Delegates

class UserFragment : Fragment() {
    // Why kotlin sugested Delegates
    var args by Delegates.notNull<Int>()
    private val viewModel: UserViewModel by sharedViewModel{ parametersOf(args) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUserBinding.inflate(inflater)

        binding.lifecycleOwner = this

        args = UserFragmentArgs.fromBundle(requireArguments()).id

        binding.viewModel = viewModel

        return binding.root
    }
}