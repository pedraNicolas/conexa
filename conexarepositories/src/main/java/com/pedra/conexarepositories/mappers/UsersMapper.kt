package com.pedra.conexarepositories.mappers

import com.pedra.conexamodel.UserUI
import com.pedra.conexanetwork.dtos.users.UserDTO
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

@Mapper
interface UsersMapper {

    companion object {
        val INSTANCE: UsersMapper = Mappers.getMapper(UsersMapper::class.java)
    }

    @Mapping(source = "lat", target = "lat")
    @Mapping(source = "lng", target = "lng")
    @Mapping(source = "companyName", target = "companyName")
    fun userToUI(
        userDTO: UserDTO,
        lat: String,
        lng: String,
        companyName: String
    ): UserUI
}