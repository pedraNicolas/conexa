package com.pedra.conexarepositories.usecase

import com.pedra.conexamodel.UserUI
import com.pedra.conexarepositories.interfaces.UsersRepositoryInterface
import com.pedra.conexarepositories.mappers.UsersMapper
import java.lang.Exception
import javax.inject.Inject

class UsersUseCase @Inject constructor(
    private val usersRepository: UsersRepositoryInterface
) {

    @Throws(Exception::class)
    suspend fun getAllUsers(): List<UserUI> {
        val mapper = UsersMapper.INSTANCE
        return usersRepository.getAllUsers().map {
            mapper.userToUI(it, it.addressDTO.geoDTO.lat, it.addressDTO.geoDTO.lng, it.company.companyName)
        }
    }

    @Throws(Exception::class)
    suspend fun getUserById(id: String): UserUI? {
        val mapper = UsersMapper.INSTANCE
        return usersRepository.getUserById(id)?.let {
            mapper.userToUI(it, it.addressDTO.geoDTO.lat, it.addressDTO.geoDTO.lng, it.company.companyName)
        }
    }
}