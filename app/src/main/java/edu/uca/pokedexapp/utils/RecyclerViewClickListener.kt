package edu.uca.pokedexapp.utils

import edu.uca.pokedexapp.model.ElementalTypes
import edu.uca.pokedexapp.model.Pokemon

interface RecyclerViewClickListener {
    fun onClickType(position: Int, elementalTypes: ElementalTypes)
    fun onClickPokemon(position: Int, pokemon: Pokemon)
}