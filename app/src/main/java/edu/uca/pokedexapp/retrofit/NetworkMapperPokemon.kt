package edu.uca.pokedexapp.retrofit

import javax.inject.Inject
import edu.uca.pokedexapp.model.Pokemon
import edu.uca.pokedexapp.utils.EntityMapper

class NetworkMapperPokemon
@Inject
constructor():
    EntityMapper<PokemonNetworkEntity, Pokemon>{
    override fun mapFromEntity(entity: PokemonNetworkEntity): Pokemon {
        return Pokemon(
            id = entity.id,
            pkdxnumber = entity.pkdxnumber,
            pkmnname = entity.pkmnname,
            description = entity.description,
            primaryType = entity.primarytype,
            secondaryType = entity.secondarytype,
            url_image = entity.url_image,
        )
    }

    override fun mapToEntity(domainModel: Pokemon): PokemonNetworkEntity {
        return PokemonNetworkEntity(
            id = domainModel.id,
            pkdxnumber = domainModel.pkdxnumber,
            pkmnname = domainModel.pkmnname,
            description = domainModel.description,
            primarytype = domainModel.primaryType,
            secondarytype = domainModel.secondaryType,
            url_image = domainModel.url_image
        )
    }

    fun mapFromEntityList(entities: List<PokemonNetworkEntity>):List<Pokemon>{
        return entities.map { mapFromEntity(it) }
    }
}