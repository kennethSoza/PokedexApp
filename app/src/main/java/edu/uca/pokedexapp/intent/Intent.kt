package edu.uca.pokedexapp.intent

sealed class Intent {
    object GetTypeEvent: Intent()
    object None: Intent()
}