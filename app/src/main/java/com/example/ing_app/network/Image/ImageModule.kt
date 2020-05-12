package com.example.ing_app.network.Image

import com.example.ing_app.repository.ImageRepository
import com.example.ing_app.ui.images.ImageViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.lang.reflect.Array
import java.lang.reflect.Array.get

object ImageModule {
    val mainModule = module{
        single { ImageApi(androidContext()) }
        single {
            provideApiSerice(
                get()
        ) }
        single { ImageRepository(photoService = get()) }
        viewModel {(userId: Int) -> ImageViewModel(userId, imageRepository = get()) }
    }

    private fun provideApiSerice(api: ImageApi): PhotoService{
        return api.getApiService()
    }
}
