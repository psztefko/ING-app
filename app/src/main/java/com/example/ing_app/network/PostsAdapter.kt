package com.example.ing_app.network

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ing_app.R
import kotlinx.android.synthetic.main.post_row.view.*

class PostsAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        //parsing id to string
        holder.userId.text = post.userId.toString()
        holder.postId = post.postId
/*        holder.title = post.title
        holder.body = post.body*/
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userId: TextView = itemView.userId
        var postId: Int = itemView.postId
        var title: TextView = itemView.title
        var body: TextView = itemView.body
    }

}
