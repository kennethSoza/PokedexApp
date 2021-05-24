package edu.uca.pokedexapp.repository

import edu.uca.pokedexapp.retrofit.ApiRetrofit
import edu.uca.pokedexapp.retrofit.NetworkMapperType
import edu.uca.pokedexapp.room.CacheMapperTypes
import edu.uca.pokedexapp.room.PokedexDao
import edu.uca.pokedexapp.utils.DataStateTypes
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.net.UnknownHostException


class TypesRepository constructor(
    private val typeDao: PokedexDao,
    private val typesRetrofit: ApiRetrofit,
    private val cacheMapper : CacheMapperTypes,
    private val networkMapper: NetworkMapperType
){
    suspend fun getTypes():Flow<DataStateTypes> = flow{
        emit(DataStateTypes.Loading)
        delay(2000)
        try {
            val typeData = typesRetrofit.getTypes()
            val typeMap = networkMapper.mapFromEntityList(typeData)
            for (tempType in typeMap){
                typeDao.insertTypes(cacheMapper.mapToEntity(tempType))
            }
            val cacheTypes = typeDao.getElementalType()
            emit(DataStateTypes.Success(cacheMapper.mapFromEntityList(cacheTypes)))


        }catch (e: Exception){
            when(e){
                is UnknownHostException ->{
                    val cacheType =typeDao.getElementalType()
                    if(cacheType.isEmpty()){
                        emit(DataStateTypes.Error(java.lang.Exception("The" +
                                " table is empty,, please connect to" +
                                " internet")))
                    }else{
                        emit(DataStateTypes.Success(cacheMapper.mapFromEntityList(cacheType)))
                    }
                }
            }
            emit(DataStateTypes.Error(e))
        }
    }
}