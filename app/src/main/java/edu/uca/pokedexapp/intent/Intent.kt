package edu.uca.pokedexapp.intent

sealed class Intent {
    object GetTypeEvent: Intent()
    object GetPokemonEvent: Intent()
    object None: Intent()
}