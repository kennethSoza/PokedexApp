package edu.uca.pokedexapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import edu.uca.pokedexapp.utils.AdapterType
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AdapterModule {
    @Singleton
    @Provides
    fun provideAdapterGenres(application: Application): AdapterType{
        return AdapterType()
    }
}