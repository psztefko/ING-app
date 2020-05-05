package com.example.ing_app.network.Comment

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
        viewModel {(id: Int) -> CommentViewModel(id, commentRepository = get()) }
    }

    private fun provideApiService(api: CommentApi): CommentService{
        return api.getApiService()
    }
}