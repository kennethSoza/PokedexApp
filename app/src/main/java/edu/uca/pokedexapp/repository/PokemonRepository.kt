package edu.uca.pokedexapp.repository

import edu.uca.pokedexapp.retrofit.ApiRetrofit
import edu.uca.pokedexapp.retrofit.NetworkMapperPokemon
import edu.uca.pokedexapp.room.CacheMapperPokemon
import edu.uca.pokedexapp.room.PokedexDao
import edu.uca.pokedexapp.utils.DataStatePokemon
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.net.UnknownHostException

class PokemonRepository constructor(
    private val pokemonDao: PokedexDao,
    private val pokemonRetrofit: ApiRetrofit,
    private val cacheMapper : CacheMapperPokemon,
    private val networkMapper: NetworkMapperPokemon
){
    suspend fun getPokemon():Flow<DataStatePokemon> = flow{
        emit(DataStatePokemon.Loading)
        delay(2000)
        try {
            val pokemonData = pokemonRetrofit.getMons()
            val pokemonMap = networkMapper.mapFromEntityList(pokemonData)
            for (tempPokemon in pokemonMap){
                pokemonDao.insertPokemon(cacheMapper.mapToEntity(tempPokemon))
            }
            val cachePokemon = pokemonDao.getPokemon()
            emit(DataStatePokemon.Success(cacheMapper.mapFromEntityList(cachePokemon)))


        }catch (e: Exception){
            when(e){
                is UnknownHostException ->{
                    val cachePokemon =pokemonDao.getPokemon()
                    if(cachePokemon.isEmpty()){
                        emit(DataStatePokemon.Error(java.lang.Exception("The" +
                                " table is empty,, please connect to" +
                                " internet")))
                    }else{
                        emit(DataStatePokemon.Success(cacheMapper.mapFromEntityList(cachePokemon)))
                    }
                }
            }
            emit(DataStatePokemon.Error(e))
        }
    }

}