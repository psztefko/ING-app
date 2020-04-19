package com.example.ing_app.ui.images

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ing_app.repository.ImageRepository
import kotlinx.coroutines.launch

class ImageViewModel (private val imageRepository: ImageRepository): ViewModel(){
    init {
        getImages()
    }

    fun getImages(){
        viewModelScope.launch {
            val apiResult = imageRepository.getImages()
        }
    }
}