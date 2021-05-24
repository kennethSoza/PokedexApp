package edu.uca.pokedexapp.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TypeNetworkEntity (
    @SerializedName("id")
    @Expose
    var idType: Int,

    @SerializedName("typename")
    @Expose
    var typename: String,

    @SerializedName("url_image")
    @Expose
    var url_image: String,
)