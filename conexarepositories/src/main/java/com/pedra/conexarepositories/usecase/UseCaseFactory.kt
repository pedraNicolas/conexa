package com.pedra.conexarepositories.usecase

import com.pedra.conexarepositories.RepositoriesFactory

object UseCaseFactory {

    fun getNewsUseCase(): NewsUseCase {
        return NewsUseCase(RepositoriesFactory.getNewsRepository())
    }

    fun getUsersUseCase(): UsersUseCase {
        return UsersUseCase(RepositoriesFactory.getUsersRepository())
    }
}