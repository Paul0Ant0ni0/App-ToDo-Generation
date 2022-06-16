package com.generation.apptodo.api

import com.generation.apptodo.model.Categoria
import com.generation.apptodo.model.Tarefa
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("categoria")
    //Trazer uma resposta e armzenar em uma lista
    //suspend porque ela sempre irá rodar dentro de uma corrontina
    suspend fun listcategoria(): Response<List<Categoria>>

    @POST("tarefa")
    suspend fun addTarefa(
        @Body tarefa: Tarefa
    ): Response<Tarefa>

    @GET("tarefa")
    suspend fun listTarefa(): Response<List<Tarefa>>
}