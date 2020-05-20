package com.example.ing_app.ui.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.ing_app.databinding.PhotoGridBinding
import com.example.ing_app.domain.Photo

 class PhotoGridAdapter( val onClickListener: OnClickListener ) :
        ListAdapter<Photo, PhotoGridAdapter.PhotoViewHolder>(DiffCallback) {

    class PhotoViewHolder(private var binding: PhotoGridBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {
            binding.photo = photo
            binding.executePendingBindings()
        }
    }

     companion object DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }
     }

     override fun onCreateViewHolder(parent: ViewGroup,
                                     viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(PhotoGridBinding.inflate(LayoutInflater.from(parent.context)))
     }

     override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(photo)
        }
        holder.bind(photo)
     }

    class OnClickListener(val clickListener: (photo:Photo) -> Unit) {
        fun onClick(photo:Photo) = clickListener(photo)
    }
 }