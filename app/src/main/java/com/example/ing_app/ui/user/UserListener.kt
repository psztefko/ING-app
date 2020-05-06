package com.example.ing_app.ui.user

import com.example.ing_app.domain.User

class UserListener(val clickListener: (userId: Int) -> Unit) {
    fun onPhotosClicked(user: User) = clickListener(user.id)
}