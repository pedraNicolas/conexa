package com.pedra.conexanetwork.dtos.users

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("address")
    val addressDTO: AddressDTO,
    @SerializedName("birthDate")
    val birthDate: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstname")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lastname")
    val lastName: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("company")
    val company: CompanyDTO,
)