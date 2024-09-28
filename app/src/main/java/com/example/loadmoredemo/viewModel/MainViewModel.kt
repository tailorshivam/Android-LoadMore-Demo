package com.example.loadmoredemo.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loadmoredemo.adapter.PostAdapter
import com.example.loadmoredemo.network.RetrofitClient
import com.example.loadmoredemo.model.Post
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val postsLiveData = MutableLiveData<List<Post>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    
    private var currentPage = 1
    private val pageSize = 10

    fun loadMorePosts() {
        viewModelScope.launch {
            loadingLiveData.value = true
            try {
                val newPosts = RetrofitClient.apiService.getPosts(currentPage, pageSize)
                val currentPosts = postsLiveData.value.orEmpty().toMutableList()
                currentPosts.addAll(newPosts)
                postsLiveData.value = currentPosts
                currentPage++
                // Remove loading view after loading posts
                (postsLiveData.value as? PostAdapter)?.removeLoading()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                loadingLiveData.value = false
            }
        }
    }
}
