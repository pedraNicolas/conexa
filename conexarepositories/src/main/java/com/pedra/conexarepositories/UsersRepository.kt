package com.pedra.conexarepositories

import com.pedra.conexanetwork.ApiRetrofitInterface
import com.pedra.conexanetwork.dtos.users.UserDTO
import com.pedra.conexarepositories.interfaces.UsersRepositoryInterface
import java.lang.Exception

class UsersRepository(
    private val apiRetrofitImpl: ApiRetrofitInterface
) : UsersRepositoryInterface {

    @Throws(Exception::class)
    override suspend fun getAllUsers(): List<UserDTO> {
        return apiRetrofitImpl.getAllUsers()
    }

    @Throws(Exception::class)
    override suspend fun getUserById(id: String): UserDTO? {
        return apiRetrofitImpl.getUserById(id)
    }
}