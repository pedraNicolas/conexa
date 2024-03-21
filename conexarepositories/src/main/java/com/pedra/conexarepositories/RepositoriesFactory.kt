package com.pedra.conexarepositories

import com.pedra.conexanetwork.NetworkFactory
import com.pedra.conexarepositories.interfaces.NewsRepositoryInterface
import com.pedra.conexarepositories.interfaces.UsersRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesFactory {

    @Provides
    fun getNewsRepository(): NewsRepositoryInterface{
        return NewsRepository(NetworkFactory.getApiManager())
    }

    @Provides
    fun getUsersRepository(): UsersRepositoryInterface{
        return UsersRepository(NetworkFactory.getApiManager())
    }
}