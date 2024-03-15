package br.com.movieapp.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductionCountriesItem(
    @SerializedName("iso")
    val iso: String = "",
    @SerializedName("name")
    val name: String = ""
)