package com.pedra.conexanetwork.dtos.users

import com.google.gson.annotations.SerializedName

data class AddressDTO(
    @SerializedName("city")
    val city: String,
    @SerializedName("geo")
    val geoDTO: GeoDTO,
    @SerializedName("street")
    val street: String,
    @SerializedName("suite")
    val suite: String,
    @SerializedName("zipcode")
    val zipcode: String
)