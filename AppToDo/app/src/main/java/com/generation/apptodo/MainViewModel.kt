package com.generation.apptodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.generation.apptodo.api.Repository
import com.generation.apptodo.model.Categoria
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {

    val repositorio = Repository()

    private  val _myCategoriaResponse =
        MutableLiveData<Response<List<Categoria>>>()

    val myCategoriaResponse : LiveData<Response<List<Categoria>>> =
        _myCategoriaResponse

    //Executando este metodo de forma assincrona, sendo assim,
    // não impactando nos outros metodos
    fun listCategoria(){
        viewModelScope.launch {
            _myCategoriaResponse.value = repositorio.ListCatgoria()
        }
    }

}