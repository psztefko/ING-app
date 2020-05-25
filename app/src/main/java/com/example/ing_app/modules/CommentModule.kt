package com.example.ing_app.modules

import com.example.ing_app.network.comment.CommentApi
import com.example.ing_app.network.comment.CommentService
import com.example.ing_app.repository.CommentRepository
import com.example.ing_app.ui.comments.CommentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

object CommentModule {
    val mainModule = module {
        single { CommentApi(androidContext()) }
        single {
            provideApiService(
                get()
            )
        }
        single { CommentRepository(commentService = get()) }
        viewModel {(postId: Int) -> CommentViewModel(postId, commentRepository = get()) }
    }

    private fun provideApiService(api: CommentApi): CommentService {
        return api.getApiService()
    }
}