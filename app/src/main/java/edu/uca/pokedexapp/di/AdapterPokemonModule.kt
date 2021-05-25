package edu.uca.pokedexapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import edu.uca.pokedexapp.utils.AdapterPokemon
import edu.uca.pokedexapp.utils.AdapterType
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AdapterPokemonModule {
    @Singleton
    @Provides
    fun provideAdapterGenres(application: Application): AdapterPokemon {
        return AdapterPokemon()
    }
}