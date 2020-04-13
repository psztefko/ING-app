package com.example.ing_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ing_app.domain.Comment

/*class CommentAdapter(val clickListener: CommentListener) :
    ListAdapter<Comment, CommentAdapter.ViewHolder>(CommentDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    class ViewHolder private constructor(val binding: CommentItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: PostListener, item: Comment) {
            //binding.comment = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CommentItemBinding.inflate(layoutInflater, parent , false)
                return ViewHolder(binding)
            }
        }
    }
}
class CommentDiffCallback : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        // TODO: Should check all
        return oldItem.commentId == newItem.commentId
    }
    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        // TODO: Should check all
        return oldItem == newItem
    }
}
// TODO: Change onClick from postId to username and comment
class CommentListener(val clickListener: (commentId: Int) -> Unit) {
    fun onClick(comment: Comment) = clickListener(comment.commentId)
}*/