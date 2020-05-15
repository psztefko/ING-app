package com.example.ing_app.network.Image

import com.example.ing_app.repository.ImageRepository
import com.example.ing_app.ui.images.ImageViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ImageModule {
    val mainModule = module{
        single { ImageApi(androidContext()) }
        single {
            provideApiService(
                get()
            )
        }
        single { ImageRepository(imageService = get()) }
        viewModel {(userId: Int) -> ImageViewModel(userId, imageRepository = get()) }
    }

    private fun provideApiService(api: ImageApi): ImageService {
        return api.getApiService()
    }
}
