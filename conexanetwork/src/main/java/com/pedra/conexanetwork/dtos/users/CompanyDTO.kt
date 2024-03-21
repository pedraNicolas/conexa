package com.pedra.conexanetwork.dtos.users

import com.google.gson.annotations.SerializedName

data class CompanyDTO(
    @SerializedName("name")
    val companyName: String,
)
