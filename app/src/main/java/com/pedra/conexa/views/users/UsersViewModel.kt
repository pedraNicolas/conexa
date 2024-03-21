package com.pedra.conexa.views.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedra.conexamodel.UserUI
import com.pedra.conexarepositories.usecase.UsersUseCase
import com.pedra.core.ConstantsNetwork
import com.pedra.core.utils.ResourceState
import com.pedra.core.utils.UtilConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase
) : ViewModel() {

    private val utilConnection = UtilConnection.getInstance()

    private val _usersListLiveData = MutableLiveData<ResourceState<List<UserUI>>>()
    val usersListLiveData: LiveData<ResourceState<List<UserUI>>> = _usersListLiveData

    fun getUsersList() {
        viewModelScope.launch {

            _usersListLiveData.value = ResourceState.Loading
            viewModelScope.launch {
                try {
                    if (utilConnection.checkInternetConnection()) {
                        val list = usersUseCase.getAllUsers()
                        if (list.isNotEmpty()) {
                            _usersListLiveData.value = ResourceState.Success(list)
                        } else {
                            _usersListLiveData.value =
                                ResourceState.Failure(ConstantsNetwork.USERS_NOT_FOUND)
                        }
                    } else {
                        _usersListLiveData.value =
                            ResourceState.Failure(ConstantsNetwork.NOT_CONNECTION)
                    }
                } catch (e: Exception) {
                    _usersListLiveData.value = ResourceState.Failure(ConstantsNetwork.USERS_FAILED)
                    e.printStackTrace()
                }
            }
        }
    }
}