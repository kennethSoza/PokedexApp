package edu.uca.pokedexapp.retrofit

import javax.inject.Inject
import edu.uca.pokedexapp.model.ElementalTypes
import edu.uca.pokedexapp.utils.EntityMapper

class NetworkMapperType
@Inject
constructor():EntityMapper<TypeNetworkEntity, ElementalTypes>{

    override fun mapFromEntity(entity: TypeNetworkEntity): ElementalTypes {
        return ElementalTypes(
            idType = entity.idType,
            typename = entity.typename,
            url_Image = entity.url_image

        )
    }

    override fun mapToEntity(domainModel: ElementalTypes): TypeNetworkEntity {
        return TypeNetworkEntity(
            idType = domainModel.idType,
            typename = domainModel.typename,
            url_image = domainModel.url_Image
        )
    }

    fun mapFromEntityList(entities: List<TypeNetworkEntity>):List<ElementalTypes>{
        return entities.map { mapFromEntity(it) }
    }
}

