package edu.uca.pokedexapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import edu.uca.pokedexapp.room.PokedexDao
import edu.uca.pokedexapp.room.PokedexDataBase

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun providePkmnDb(@ApplicationContext context: Context): PokedexDataBase{
        return Room.databaseBuilder(context, PokedexDataBase::class.java, PokedexDataBase.DATABASE_NAME).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providePkmnDao(pkmnDatabase: PokedexDataBase): PokedexDao{
        return pkmnDatabase.pokedexDao()
    }
}