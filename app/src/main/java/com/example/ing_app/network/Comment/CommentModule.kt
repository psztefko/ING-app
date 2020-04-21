package com.example.ing_app.network.Comment

import com.example.ing_app.network.Post.PostApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object CommentModule {
    val mainModule = module {
        single { CommentApi(androidContext()) }
        single {
            provideApiService(
                get()
            )
        }
    }

    private fun provideApiService(api: CommentApi): CommentService{
        return api.getApiService()
    }
}