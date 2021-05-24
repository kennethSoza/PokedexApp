package edu.uca.pokedexapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PokemonCacheEntity::class, ElementalTypesCacheEntity::class], version = 1)
abstract class PokedexDataBase: RoomDatabase() {
    companion object{
        val DATABASE_NAME = "PokedexDB"
    }
    abstract fun pokedexDao(): PokedexDao
}