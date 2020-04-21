package com.example.ing_app.network.Post

import com.example.ing_app.network.Comment.CommentApi
import com.example.ing_app.network.Comment.CommentService
import com.example.ing_app.network.User.UserApi
import com.example.ing_app.network.User.UserService
import com.example.ing_app.repository.PostRepository
import com.example.ing_app.ui.posts.PostViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

object PostModule {
    val mainModule = module {
        single { PostApi(androidContext()) }
        single { UserApi(androidContext()) }
        single { CommentApi(androidContext()) }
        single {
            providePostApiService(
                get()
            )
        }
        single {
            provideUserApiService(
                get()
            )
        }
        single {
            provideCommentApiService(
                get()
            )
        }
        single { PostRepository(postService = get(), userService = get(), commentService = get()) }
        viewModel { PostViewModel(postRepository = get())}
    }

    private fun providePostApiService(api: PostApi): PostService {
        return api.getApiService()
    }
    private fun provideUserApiService(api: UserApi): UserService {
        return api.getApiService()
    }
    private fun provideCommentApiService(api: CommentApi): CommentService {
        return api.getApiService()
    }
}