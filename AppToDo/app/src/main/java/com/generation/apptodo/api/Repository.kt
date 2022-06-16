package com.generation.apptodo.api

import com.generation.apptodo.model.Categoria
import com.generation.apptodo.model.Tarefa
import retrofit2.Response
// Class Repository com atributos e metodos para utilizar dentro da classe ViewModel

class Repository {

    //Suspend, pois, assim a função listacategoria poderá sobreviver ao ciclo de vida e ser suspensa
    //Ela é execuatada de forma assincrona, atuando diferente do modo cascata.
    suspend fun  listcategoria(): Response<List<Categoria>>{
        return RetrofitInstance.api.listcategoria()
    }

    suspend fun addTarefa(tarefa: Tarefa): Response<Tarefa>{
        return RetrofitInstance.api.addTarefa(tarefa)
    }

    suspend fun listTarefa(): Response<List<Tarefa>>{
        return RetrofitInstance.api.listTarefa()
    }
}