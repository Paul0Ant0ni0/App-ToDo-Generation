package com.generation.apptodo.api

import com.generation.apptodo.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    //RetrofitInstance é um objeto que será uma instância do retrofit só uma vez dentro do projeto


    //Para ser inicializada toda vez quando inializar by lazy, tendo prioridade máxima
    private val retrofit by lazy {
        //Builder contrui toda a estrutura
        Retrofit.Builder()
            //Metodo para intânciar o local da api
            .baseUrl(Constants.BASE_URL)
            //Metodo para converter do tipo Json para um formato que o kotlin consiga ler
            .addConverterFactory(GsonConverterFactory.create())
                //Metodo para contruir a intância
            .build()
    }

    //Api com prioridade por isso o by lazy
    //Variável criando uma instancia do retrofit com base na ApiService
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}