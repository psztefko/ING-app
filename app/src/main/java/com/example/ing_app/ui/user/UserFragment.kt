package com.example.ing_app.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ing_app.databinding.FragmentUserBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf

class UserFragment : Fragment() {
    val args = UserFragmentArgs.fromBundle(requireArguments())

    private val viewModel: UserViewModel by sharedViewModel{ parametersOf(args) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUserBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }
}