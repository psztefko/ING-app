package com.example.ing_app

import android.app.Application
import com.example.ing_app.modules.CommentModule
import com.example.ing_app.modules.ImageModule
import com.example.ing_app.modules.PostModule
import com.example.ing_app.modules.UserModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application (){
    var listofModules =
        listOf(
            PostModule.mainModule,
            UserModule.mainModule,
            CommentModule.mainModule,
            ImageModule.mainModule
        )

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listofModules)
        }
    }
}