package edu.uca.pokedexapp.utils

import edu.uca.pokedexapp.model.ElementalTypes
import java.lang.Exception

sealed class DataStateTypes {
    object Idle:DataStateTypes()
    data class Success(val elementalTypes: List<ElementalTypes>): DataStateTypes()
    data class Error(val exception: Exception):DataStateTypes()
    object Loading: DataStateTypes()
}