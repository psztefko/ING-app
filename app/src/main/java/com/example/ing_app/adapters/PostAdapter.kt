package com.example.ing_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ing_app.domain.Post

/*class PostAdapter(val clickListener: PostListener) :
    ListAdapter<Post, PostAdapter.ViewHolder>(PostDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    class ViewHolder private constructor(val binding: PostItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: PostListener, item: Post) {
            //binding.post = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PostItemBinding.inflate(layoutInflater, parent , false)
                return ViewHolder(binding)
            }
        }
    }
}
class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        // TODO: Should check all
        return oldItem.postId == newItem.postId
    }
    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        // TODO: Should check all
        return oldItem == newItem
    }
}
// TODO: Change onClick from postId to username and comment
class PostListener(val clickListener: (postId: Int) -> Unit) {
    fun onClick(post: Post) = clickListener(post.postId)
}*/