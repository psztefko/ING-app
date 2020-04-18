package com.example.ing_app

import com.example.ing_app.network.Network
import com.example.ing_app.network.PostApi
import com.example.ing_app.network.PostService
import com.example.ing_app.repository.PostRepository
import com.example.ing_app.ui.posts.PostViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.lang.reflect.Array.get
import org.koin.androidx.viewmodel.dsl.viewModel

object MainModule {
    val mainModule = module {
        single { PostApi(androidContext()) }
        single { provideApiService(get()) }
        single { PostRepository(postService = get()) }
        viewModel { PostViewModel(postRepository = get())}
    }

    private fun provideApiService(api: PostApi): PostService {
        return api.getApiService()
    }
}