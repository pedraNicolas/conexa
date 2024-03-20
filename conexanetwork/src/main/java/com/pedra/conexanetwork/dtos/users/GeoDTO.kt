package com.pedra.conexanetwork.dtos.users

import com.google.gson.annotations.SerializedName

data class GeoDTO(
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String
)