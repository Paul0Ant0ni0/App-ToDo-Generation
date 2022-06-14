package com.generation.apptodo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.generation.apptodo.api.Repository
import com.generation.apptodo.model.Categoria
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import java.time.LocalDate
import javax.inject.Inject

//Saber o que precisa injetar e sobreviver ao ciclo de vida.

@HiltViewModel
class MainViewModel @Inject constructor (
    private val repository: Repository
        ): ViewModel() {



    //Guardando os valores que é retornado do Repository
    private  var _myCategoriaResponse =
        MutableLiveData<Response<List<Categoria>>>()


    val myCategoriaResponse : LiveData<Response<List<Categoria>>> =
        _myCategoriaResponse


    //Dado observavel e mutavel
    //LocalDate recupera a data atual
    val dataSelecionada = MutableLiveData<LocalDate>()

    init {
        //listCategoria()
    }


    //Executando este método de forma assincrona, sendo assim,
    //não impactando nos outros metodos.
    //Está sobrevivendo ao ciclo de vida da MainViewModel por causa da corrotina listcategoria,
    //com a thread Dispatchers.IO
    fun listCategoria(){
        viewModelScope.launch(Dispatchers.IO) {
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