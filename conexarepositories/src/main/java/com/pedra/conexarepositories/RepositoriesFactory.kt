package com.pedra.conexarepositories

import com.pedra.conexanetwork.NetworkFactory
import com.pedra.conexarepositories.interfaces.NewsRepositoryInterface
import com.pedra.conexarepositories.interfaces.UsersRepositoryInterface

object RepositoriesFactory {

    fun getNewsRepository(): NewsRepositoryInterface{
        return NewsRepository(NetworkFactory.getApiManager())
    }

    fun getUsersRepository(): UsersRepositoryInterface{
        return UsersRepository(NetworkFactory.getApiManager())
    }
}