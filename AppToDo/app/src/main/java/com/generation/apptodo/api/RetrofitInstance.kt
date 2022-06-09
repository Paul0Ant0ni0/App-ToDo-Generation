package com.generation.apptodo.api

import com.generation.apptodo.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    //RetrofitInstance é um objeto que será uma instância do retrofit só uma vez dentro do projeto


    //Para ser inicializada toda vez quando inializar by lazy
    private val retrofit by lazy {
        //Builder contrui toda a estrutura
        Retrofit.Builder()
            .baseUrl(Constants.Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}