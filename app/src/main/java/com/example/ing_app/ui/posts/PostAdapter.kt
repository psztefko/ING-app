package com.example.ing_app.ui.posts

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ing_app.databinding.PostRowBinding
import com.example.ing_app.ui.posts.Post
import timber.log.Timber


class PostAdapter :
    ListAdapter<Post, PostAdapter.ViewHolder>(PostDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Timber.d("onBindViewHolder")
        val item = getItem(position)
        Timber.d("onBindViewHolderItem: ${item}")
        holder.bind(item)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Timber.d("onCreateViewHolder")
        return ViewHolder.from(parent)
    }
    class ViewHolder private constructor(val binding: PostRowBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) {
            binding.post = item
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PostRowBinding.inflate(layoutInflater, parent , false)
                return ViewHolder(binding)
            }
        }
    }
}
class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}