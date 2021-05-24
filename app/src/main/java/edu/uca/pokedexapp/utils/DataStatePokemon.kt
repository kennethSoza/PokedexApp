package edu.uca.pokedexapp.utils

import edu.uca.pokedexapp.model.Pokemon
import java.lang.Exception

sealed class DataStatePokemon {
    object Idle:DataStatePokemon()
    data class Success(val pkmn:List<Pokemon>): DataStatePokemon()
    data class Error(val exception: Exception):DataStatePokemon()
    object Loading: DataStatePokemon()
}