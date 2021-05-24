package edu.uca.pokedexapp.model

data class Pokemon (
    var id: Int,
    var pkdxnumber: String,
    var pkmnname: String,
    var description: String,
    var primaryType: Int,
    var secondaryType: Int,
    var url_image: String,
)