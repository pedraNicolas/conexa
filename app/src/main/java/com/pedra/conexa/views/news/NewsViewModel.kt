package com.pedra.conexa.views.news

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedra.conexamodel.NewsUI
import com.pedra.conexamodel.UserUI
import com.pedra.conexarepositories.usecase.NewsUseCase
import com.pedra.conexarepositories.usecase.UseCaseFactory
import com.pedra.conexarepositories.usecase.UsersUseCase
import com.pedra.core.ConstantsConexa
import com.pedra.core.ConstantsNetwork
import com.pedra.core.utils.ResourceState
import com.pedra.core.utils.UtilConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase,
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private val utilConnection = UtilConnection.getInstance()

    private val _newsLiveData = MutableLiveData<ResourceState<List<NewsUI>>>()
    val newsLiveData: LiveData<ResourceState<List<NewsUI>>> = _newsLiveData

    private val _userLiveData = MutableLiveData<ResourceState<UserUI>>()
    val userLiveData: LiveData<ResourceState<UserUI>> = _userLiveData

    fun getNewsUIList() {
        _newsLiveData.value = ResourceState.Loading
        viewModelScope.launch {
            try {
                if (utilConnection.checkInternetConnection()) {
                    val list = newsUseCase.getNewsUIList()
                    if (list.isNotEmpty()) {
                        _newsLiveData.value = ResourceState.Success(list)
                    } else {
                        _newsLiveData.value = ResourceState.Failure(ConstantsNetwork.NEWS_NOT_FOUND)
                    }
                } else {
                    _newsLiveData.value = ResourceState.Failure(ConstantsNetwork.NOT_CONNECTION)
                }
            } catch (e: Exception) {
                _newsLiveData.value = ResourceState.Failure(ConstantsNetwork.NEWS_FAILED)
                e.printStackTrace()
            }
        }
    }

    fun getUSerById(id: String) {
        _userLiveData.value = ResourceState.Loading
        viewModelScope.launch {
            try {
                if (utilConnection.checkInternetConnection()) {
                    val userUI = usersUseCase.getUserById(id)
                    if (userUI != null) {
                        _userLiveData.value = ResourceState.Success(userUI)
                    } else {
                        _userLiveData.value = ResourceState.Failure(ConstantsNetwork.USERS_NOT_FOUND)
                    }
                } else {
                    _userLiveData.value = ResourceState.Failure(ConstantsNetwork.NOT_CONNECTION)
                }
            } catch (e: Exception) {
                _userLiveData.value = ResourceState.Failure(ConstantsNetwork.USERS_FAILED)
                e.printStackTrace()
            }
        }
    }
}
