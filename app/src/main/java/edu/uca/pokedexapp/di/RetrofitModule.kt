package edu.uca.pokedexapp.di

import edu.uca.pokedexapp.retrofit.ApiRetrofit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent :: class)
object RetrofitModule {
    @Singleton
    @Provides
    fun providesGsonBuilder(): Gson{
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit.Builder{
        return Retrofit.Builder()
            /**Consulta a la api que creamos, para más detalles
             * revisar el README.md*/
            .baseUrl("https://mini-pkmn-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun providePokedexService(retrofit: Retrofit.Builder): ApiRetrofit{
        return retrofit.build().create(ApiRetrofit::class.java)
    }


}