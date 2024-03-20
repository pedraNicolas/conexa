package com.pedra.conexarepositories.interfaces

import com.pedra.conexanetwork.dtos.users.UserDTO

interface UsersRepositoryInterface {

    suspend fun getAllUsers(): List<UserDTO>
    suspend fun getUserById(id: String): UserDTO
}