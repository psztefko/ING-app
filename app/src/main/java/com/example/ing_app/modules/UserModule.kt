package com.example.ing_app.modules

import com.example.ing_app.network.user.UserApi
import com.example.ing_app.network.user.UserService
import com.example.ing_app.repository.UserRepository
import com.example.ing_app.ui.user.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UserModule {
    val mainModule = module {
        single { UserApi(androidContext()) }
        single {
            provideApiService(
                get()
            )
        }
        single { UserRepository(userService = get()) }
        viewModel {(userId: Int) -> UserViewModel(userId, userRepository = get())}
    }

    private fun provideApiService(api: UserApi): UserService {
        return api.getApiService()
    }
}