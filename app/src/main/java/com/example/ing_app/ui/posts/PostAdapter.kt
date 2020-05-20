package com.example.ing_app.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ing_app.databinding.PostRowBinding
import timber.log.Timber

// Two listeners (probably not the best idea but android docs are literally the worst)
class PostAdapter(private val userClickListener: UserClickListener, private val commentClickListener: CommentClickListener) :
    ListAdapter<Post, PostAdapter.ViewHolder>(PostDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(userClickListener, commentClickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    class ViewHolder private constructor(val binding: PostRowBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userClickListener: UserClickListener, commentClickListener: CommentClickListener, item: Post) {
            binding.post = item
            binding.userClickListener = userClickListener
            binding.commentClickListener = commentClickListener
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

class UserClickListener(val ClickListener: (userId: Int) -> Unit) {
    fun onUserClick(post: Post) {
        ClickListener(post.userId)
    }
}

class CommentClickListener(val ClickListener: (id: Int) -> Unit) {
    fun onCommentClick(post: Post) = ClickListener(post.id)
}