package com.generation.apptodo.di

import com.generation.apptodo.api.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//  Reponsável por injetar todos os módulos
@Module
//Singleton quer diver que é único e que pode ser instaciado uma única vez.
//Convertendo a interface SingletonComponent para class
@InstallIn(SingletonComponent::class)
object ServiceModule {

    //Metodo retornando a instância do repositório
    //Prover um único objeto do tipo repositório, que poderá ser injetado.
    //Singleton e Provides Precisa sempre ser seguidos de um metodo.
    @Singleton
    @Provides
    fun retunRepository(): Repository{
        return Repository()
    }
}