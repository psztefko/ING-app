package com.example.ing_app.modules

import com.example.ing_app.network.post.PostApi
import com.example.ing_app.network.post.PostService
import com.example.ing_app.repository.PostRepository
import com.example.ing_app.ui.posts.PostViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

object PostModule {
    val mainModule = module {
        single { PostApi(androidContext()) }
        single {
            providePostApiService(
                get()
            )
        }
        single { PostRepository(postService = get(), commentService = get(), userService = get()) }
        viewModel { PostViewModel(postRepository = get())}
    }

    private fun providePostApiService(api: PostApi): PostService {
        return api.getApiService()
    }
}