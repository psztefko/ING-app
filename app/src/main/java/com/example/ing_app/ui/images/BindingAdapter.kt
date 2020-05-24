package com.example.ing_app.ui.images

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ing_app.R
import com.example.ing_app.domain.Photo
import com.squareup.picasso.Picasso

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Photo>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Picasso.get()
            .load(imgUrl)
            .resize(150,150)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image_black_24dp)
            .into(imgView)
    }
}