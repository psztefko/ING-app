package com.example.ing_app

import com.example.ing_app.modules.CommentModule
import com.example.ing_app.modules.ImageModule
import com.example.ing_app.modules.PostModule
import com.example.ing_app.modules.UserModule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest

class MyTest : KoinTest{

    var listOfModules =
        listOf(
            PostModule.mainModule,
            UserModule.mainModule,
            CommentModule.mainModule,
            ImageModule.mainModule
        )

    @Test
    fun makeATestWithKoin() {
        startKoin { modules(listOfModules)}
    }
}