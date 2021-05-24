package edu.uca.pokedexapp.utils

import edu.uca.pokedexapp.model.ElementalTypes

interface RecyclerViewClickListener {
    fun onClickType(position: Int, elementalTypes: ElementalTypes)
}