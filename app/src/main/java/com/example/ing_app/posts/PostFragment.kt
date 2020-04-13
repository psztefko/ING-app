package com.example.ing_app.posts

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class PostFragment : Fragment() {
    private val viewModel: PostViewModel by lazy {
        ViewModelProvider(this).get(PostViewModel::class.java)
    }
}