package com.generation.apptodo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.generation.apptodo.api.Repository
import com.generation.apptodo.model.Categoria
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val repository: Repository
        ): ViewModel() {



    //Guardando os valores que é retornado do Repository
    private  var _myCategoriaResponse =
        MutableLiveData<Response<List<Categoria>>>()


    val myCategoriaResponse : LiveData<Response<List<Categoria>>> =
        _myCategoriaResponse

    init {
        listCategoria()
    }


    //Executando este metodo de forma assincrona, sendo assim,
    // não impactando nos outros metodos
    private fun listCategoria(){
        viewModelScope.launch {
            //try para evitar erro, ex: Se a não estiver conectado na internet
            try {
             val response = repository.listcategoria()
                _myCategoriaResponse.value = response

            }catch (e: Exception){
               Log.d("Erro", e.message.toString())
            }

        }
    }

}