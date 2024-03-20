package com.pedra.conexarepositories.mappers

import org.mapstruct.factory.Mappers

class UsersMapper {

    companion object {
        val INSTANCE: UsersMapper = Mappers.getMapper(UsersMapper::class.java)
    }
}