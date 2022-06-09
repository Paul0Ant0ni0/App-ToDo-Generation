package com.generation.apptodo.api

import com.generation.apptodo.model.Categoria
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("categoria")
    //Trazer uma resposta e armzenar em uma lista
    //suspend porque ela sempre irá rodar dentro de uma corrontina
    suspend fun listcategoria(): Response<List<Categoria>>
}