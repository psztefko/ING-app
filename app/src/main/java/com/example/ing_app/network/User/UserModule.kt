package com.example.ing_app.network.User

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
        viewModel {(id: Int) -> UserViewModel(id, userRepository = get())}
    }

    private fun provideApiService(api: UserApi): UserService {
        return api.getApiService()
    }
}