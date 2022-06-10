package com.generation.apptodo.di

import com.generation.apptodo.api.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
//Convertendo a interface SingletonComponent para class
@InstallIn(SingletonComponent::class)
object ServiceModule {

    //Metodo retornando a instância do repositório
    @Singleton
    @Provides
    fun retunRepository(): Repository{
        return Repository()
    }
}