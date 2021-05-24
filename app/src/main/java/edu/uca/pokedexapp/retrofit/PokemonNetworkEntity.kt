package edu.uca.pokedexapp.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PokemonNetworkEntity (
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("pkdxnumber")
    @Expose
    var pkdxnumber: String,

    @SerializedName("pkmnname")
    @Expose
    var pkmnname: String,

    @SerializedName("description")
    @Expose
    var description: String,

    @SerializedName("primarytype")
    @Expose
    var primarytype: Int,

    @SerializedName("secondarytype")
    @Expose
    var secondarytype: Int,

    @SerializedName("url_image")
    @Expose
    var url_image: String,
)