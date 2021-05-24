package edu.uca.pokedexapp.room

import javax.inject.Inject
import edu.uca.pokedexapp.model.Pokemon
import edu.uca.pokedexapp.utils.EntityMapper

class CacheMapperPokemon
@Inject
constructor():
    EntityMapper<PokemonCacheEntity, Pokemon>{
    override fun mapFromEntity(entity: PokemonCacheEntity): Pokemon {
        return Pokemon(
            id = entity.id,
            pkdxnumber = entity.pkdxnumber,
            pkmnname = entity.pkmnname,
            description = entity.description,
            primaryType = entity.primarytype,
            secondaryType = entity.secondaryType,
            url_image = entity.url_image
        )
    }

    override fun mapToEntity(domainModel: Pokemon): PokemonCacheEntity {
        return PokemonCacheEntity(
            id = domainModel.id,
            pkdxnumber = domainModel.pkdxnumber,
            pkmnname = domainModel.pkmnname,
            description = domainModel.description,
            primarytype = domainModel.primaryType,
            secondaryType = domainModel.secondaryType,
            url_image = domainModel.url_image
        )
    }

    fun mapFromEntityList(entities: List<PokemonCacheEntity>): List<Pokemon>{
        return entities.map { mapFromEntity(it) }
    }
}