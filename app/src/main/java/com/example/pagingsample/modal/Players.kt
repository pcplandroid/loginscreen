package com.example.pagingsample.modal

import com.google.gson.annotations.SerializedName


data class Players(
    @SerializedName("data")
    val playerList: List<Player>,
    val meta: Meta
)