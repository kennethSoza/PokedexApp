package edu.uca.pokedexapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
class PokemonCacheEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "pkdxnumber")
    var pkdxnumber: String,
    @ColumnInfo(name = "pkmnname")
    var pkmnname: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "primarytype")
    var primarytype: Int,
    @ColumnInfo(name = "secondarytype")
    var secondaryType: Int,
    @ColumnInfo(name = "url_image")
    var url_image: String,
)