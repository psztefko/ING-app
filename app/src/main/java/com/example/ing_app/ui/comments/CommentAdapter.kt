package com.example.ing_app.ui.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ing_app.databinding.CommentRowBinding
import com.example.ing_app.domain.Comment

class CommentAdapter :
    ListAdapter<Comment, CommentAdapter.ViewHolder>(PostDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    class ViewHolder private constructor(val binding: CommentRowBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Comment) {
            binding.comment = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CommentRowBinding.inflate(layoutInflater, parent , false)

                return ViewHolder(binding)
            }
        }
    }
}
class PostDiffCallback : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}