package com.generation.apptodo.api

import com.generation.apptodo.model.Categoria
import retrofit2.Response

class Repository {

    suspend fun  listcategoria(): Response<List<Categoria>>{
        return RetrofitInstance.api.listcategoria()
    }
}