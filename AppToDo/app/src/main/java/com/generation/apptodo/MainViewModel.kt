package com.generation.apptodo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.generation.apptodo.api.Repository
import com.generation.apptodo.model.Categoria
import com.generation.apptodo.model.Tarefa
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDate
import javax.inject.Inject
import kotlin.Exception

//Saber o que precisa injetar e sobreviver ao ciclo de vida.

@HiltViewModel
class MainViewModel @Inject constructor (
    private val repository: Repository
        ): ViewModel() {

    var tarefaSelecionada: Tarefa? = null


    //Guardando os valores que é retornado do Repository
    private  var _myCategoriaResponse =
        MutableLiveData<Response<List<Categoria>>>()


    val myCategoriaResponse : LiveData<Response<List<Categoria>>> =
        _myCategoriaResponse


    private val _myTarefaResponse =
        MutableLiveData<Response<List<Tarefa>>>()

    val myTarefaResponse: LiveData<Response<List<Tarefa>>> =
        _myTarefaResponse

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
        viewModelScope.launch {
            //try para evitar erro, ex: Se a não estiver conectado na internet
            try {
             val response = repository.listCategoria()
                _myCategoriaResponse.value = response

            }catch (e: Exception){
               Log.d("Erro", e.message.toString())
            }

        }
    }

    fun addTarefa(tarefa: Tarefa){
        viewModelScope.launch {
            try {
                repository.addTarefa(tarefa)
                listTarefa()
            }catch (e: Exception){
                Log.d("Erro Htto - Post", e.message.toString())
            }
        }
    }

    fun listTarefa(){
        viewModelScope.launch {
            try {
                val response = repository.listTarefa()
                _myTarefaResponse.value = response
            }catch(e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun updateTarefa(tarefa: Tarefa) {
        viewModelScope.launch {
            try {
                repository.updateTarefa(tarefa)
                listTarefa()

            } catch (e: Exception) {
                Log.d("Erro Put", e.message.toString())
            }
        }

    }

    fun deleteTarefa(id: Long){
        viewModelScope.launch {
            try {
                    repository.deleteTarefa(id)
                listTarefa()
            }catch (e: Exception){
                Log.d("Erro Delete", e.message.toString())
            }
        }
    }

}