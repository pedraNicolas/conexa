package com.pedra.conexa.views.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedra.conexamodel.NewsUI
import com.pedra.conexarepositories.usecase.UseCaseFactory
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {

    private val newsUseCase = UseCaseFactory.getNewsUseCase()

    private val _newsLiveData = MutableLiveData<List<NewsUI>>()
    val newsLiveData: LiveData<List<NewsUI>> = _newsLiveData

    fun getNewsUIList(){
        viewModelScope.launch {
            _newsLiveData.postValue(newsUseCase.getNewsUIList())
        }
    }
}