package com.example.ing_app.domain

data class User (
    val userId: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val webiste: String,
    val company: Company
)