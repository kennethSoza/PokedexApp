package edu.uca.pokedexapp.di

import edu.uca.pokedexapp.repository.PokemonRepository
import edu.uca.pokedexapp.repository.TypesRepository
import edu.uca.pokedexapp.retrofit.NetworkMapperPokemon
import edu.uca.pokedexapp.retrofit.NetworkMapperType
import edu.uca.pokedexapp.room.CacheMapperPokemon
import edu.uca.pokedexapp.room.CacheMapperTypes
import edu.uca.pokedexapp.room.PokedexDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import edu.uca.pokedexapp.retrofit.ApiRetrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent :: class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideTypesRepository(
        typeDao: PokedexDao,
        typeRetrofit: ApiRetrofit,
        cacheMapperTypes: CacheMapperTypes,
        networkMapperTypes: NetworkMapperType
    ):TypesRepository{
        return TypesRepository(typeDao,typeRetrofit,cacheMapperTypes,networkMapperTypes)
    }

    @Singleton
    @Provides
    fun providePokemonRepository(
        pokemonDao: PokedexDao,
        pokemonRetrofit: ApiRetrofit,
        cacheMapperPokemon: CacheMapperPokemon,
        networkMapperPokemon: NetworkMapperPokemon
    ):PokemonRepository{
        return PokemonRepository(pokemonDao,pokemonRetrofit,cacheMapperPokemon,networkMapperPokemon)
    }

}