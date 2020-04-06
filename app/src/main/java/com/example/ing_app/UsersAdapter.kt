package com.example.ing_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ing_app.network.PostsAdapter
import com.example.ing_app.network.User
import kotlinx.android.synthetic.main.post_row.view.*
import org.w3c.dom.Text

class UsersAdapter(private val users: List<User>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent, false)
        return PostsAdapter.ViewHolder(view)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = users[position]

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val userId: TextView = itemView.userId
        val name: TextView = itemView.

    }
}
