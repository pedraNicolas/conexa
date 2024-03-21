package com.pedra.conexarepositories.usecase

import com.pedra.conexarepositories.RepositoriesFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseFactory {

    @Provides
    fun getNewsUseCase(): NewsUseCase {
        return NewsUseCase(RepositoriesFactory.getNewsRepository())
    }

    @Provides
    fun getUsersUseCase(): UsersUseCase {
        return UsersUseCase(RepositoriesFactory.getUsersRepository())
    }
}