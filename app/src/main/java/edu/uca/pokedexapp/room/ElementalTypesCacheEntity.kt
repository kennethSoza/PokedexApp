package edu.uca.pokedexapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "elemental_types")
class ElementalTypesCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var idType: Int,

    @ColumnInfo(name = "typename")
    var typename: String,

    @ColumnInfo(name = "url_image")
    var url_Image: String

)