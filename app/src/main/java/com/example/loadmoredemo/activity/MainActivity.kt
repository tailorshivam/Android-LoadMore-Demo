package com.example.loadmoredemo.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loadmoredemo.viewModel.MainViewModel
import com.example.loadmoredemo.adapter.PostAdapter
import com.example.loadmoredemo.R
import com.example.loadmoredemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var postAdapter: PostAdapter
    private lateinit var binding: ActivityMainBinding
    private val handler = Handler(Looper.getMainLooper()) // Handler to post updates

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView adapter
        postAdapter = PostAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = postAdapter

        // Observe LiveData for posts
        viewModel.postsLiveData.observe(this, Observer { posts ->
            postAdapter.submitList(posts)
        })

        // Observe loading state
        viewModel.loadingLiveData.observe(this, Observer { isLoading ->
            // Show or hide loading indicator if necessary
        })

        // Set up RecyclerView's scroll listener for loading more posts
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)  // Call super method

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (!viewModel.loadingLiveData.value!! && totalItemCount <= (lastVisibleItem + 2)) {
                    handler.post{
                        postAdapter.addLoading() // Show loading view
                        viewModel.loadMorePosts() // Load more posts
                    }
                }
            }
        })

        // Initial load
        viewModel.loadMorePosts()
    }
}