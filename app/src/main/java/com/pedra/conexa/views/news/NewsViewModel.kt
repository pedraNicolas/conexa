package com.pedra.conexa.views.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedra.conexarepositories.usecase.NewsUseCase
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {
    private val newsUseCase = NewsUseCase()

    fun getNews(){
        viewModelScope.launch {
            newsUseCase.getNews()
        }
    }
}