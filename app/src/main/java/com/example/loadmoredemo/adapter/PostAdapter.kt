package com.example.loadmoredemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loadmoredemo.databinding.ItemLoadingBinding
import com.example.loadmoredemo.databinding.ItemPostBinding
import com.example.loadmoredemo.model.Post

class PostAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val posts = mutableListOf<Post>()
    private var isLoading = false

    companion object{
        private const val VIEW_TYPE_POST = 1
        private const val VIEW_TYPE_LOADING = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoading && position == posts.size) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_POST
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == VIEW_TYPE_LOADING){
            val binding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return LoadingViewHolder(binding)
        }else {
            val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PostViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is PostViewHolder) {
            holder.bind(posts[position])
        }
    }

    override fun getItemCount(): Int {
        return if (isLoading){
            posts.size + 1
        }else{
            posts.size
        }
    }

    fun submitList(newPosts: List<Post>) {
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }

    fun addLoading(){
        isLoading = true
        notifyItemInserted(posts.size)
    }

    fun removeLoading(){
        isLoading = false
        val position = posts.size
        notifyItemRemoved(position)
    }

    inner class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.title.text = post.title
            binding.body.text = post.body
        }
    }

    inner class LoadingViewHolder(binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root) {}
}
