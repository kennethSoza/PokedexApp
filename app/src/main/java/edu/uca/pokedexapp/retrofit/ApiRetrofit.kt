package edu.uca.pokedexapp.retrofit

import retrofit2.http.GET

interface ApiRetrofit {
    @GET("elemental-types")
    suspend fun getTypes() : List<TypeNetworkEntity>

    @GET("mons")
    suspend fun getMons() : List<PokemonNetworkEntity>
}