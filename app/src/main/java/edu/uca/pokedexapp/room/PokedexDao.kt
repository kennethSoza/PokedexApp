package edu.uca.pokedexapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokedexDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTypes(typesEntity : ElementalTypesCacheEntity)
    @Query("select * from elemental_types")
    suspend fun getElementalType(): List<ElementalTypesCacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(typesEntity : PokemonCacheEntity)
    @Query("select * from pokemon")
    suspend fun getPokemon(): List<PokemonCacheEntity>


}