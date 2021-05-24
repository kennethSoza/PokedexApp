package edu.uca.pokedexapp.room

import edu.uca.pokedexapp.model.ElementalTypes
import edu.uca.pokedexapp.utils.EntityMapper
import javax.inject.Inject

class CacheMapperTypes
@Inject
constructor():
EntityMapper<ElementalTypesCacheEntity, ElementalTypes>{
    override fun mapFromEntity(entity: ElementalTypesCacheEntity): ElementalTypes {
        return ElementalTypes(
            idType = entity.idType,
            typename = entity.typename,
            url_Image = entity.url_Image
        )
    }

    override fun mapToEntity(domainModel: ElementalTypes): ElementalTypesCacheEntity {
        return ElementalTypesCacheEntity(
            idType = domainModel.idType,
            typename = domainModel.typename,
            url_Image = domainModel.url_Image
        )
    }

    fun mapFromEntityList(entities: List<ElementalTypesCacheEntity>): List<ElementalTypes>{
        return entities.map { mapFromEntity(it) }
    }
}