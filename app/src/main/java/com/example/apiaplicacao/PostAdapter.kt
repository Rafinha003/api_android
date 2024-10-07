package com.example.apiaplicacao

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter (private val postList: List<Post>) :  RecyclerView.Adapter<PostAdapter.PostViewHolder> (){

    class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleTextView : TextView = itemView.findViewById(R.id.postTitle)
        val bodyTextView : TextView = itemView.findViewById(R.id.postBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.titleTextView.text = post.title
        holder.bodyTextView.text = post.body
    }

}