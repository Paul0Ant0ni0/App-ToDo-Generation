package com.generation.apptodo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.apptodo.databinding.FragmentFormBinding
import com.generation.apptodo.fragment.DatePickerFragment
import com.generation.apptodo.fragment.TimerPickerListener
import com.generation.apptodo.model.Categoria
import java.time.LocalDate

class FormFragment : Fragment(), TimerPickerListener {

    private lateinit var binding: FragmentFormBinding
    private val mainViewModel : MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Inflando o layout fragment_form
        binding = FragmentFormBinding.inflate(layoutInflater, container, false)

        //Lista de categoria só será inicializada no formfragment
        mainViewModel.listCategoria()

        //Adicionado como valor a data atual
        mainViewModel.dataSelecionada.value = LocalDate.now()
       /*
       Este metodo só funcionaria para um único fragmento, formfragment
       mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java))*/

        //Ficar observando com base no ciclo de vida do fragment
        mainViewModel.myCategoriaResponse.observe(viewLifecycleOwner){
            //Verificar se está trazendo os dados
            //Alterando o nome da variável it, nomeDaVariavel ->
           response -> Log.d("Requisicao", response.body().toString())
            //Metodo adicionando as categorias no spinner com base na lista de categoria da api
            spinnerCategoria(response.body())
        }

        //Observando que assim que a data atual for alterada no campo da data
        mainViewModel.dataSelecionada.observe(viewLifecycleOwner){
                //Setando a data atual dentro do campo editData, de forma automática
                selectDate -> binding.editData.setText(selectDate.toString())
        }

        binding.buttonSalvar.setOnClickListener {
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        }

        binding.editData.setOnClickListener {
            DatePickerFragment(this)
                .show(parentFragmentManager, "DatePìcker")
        }
        return binding.root
    }


    //configurando o spinner -
    //Spinner precisa receber um ArrayAdpater
    //ArrayAdpater precisa de uma lista para poder existir
    //Informando que a lista pode ser nula com o simbolo ?

    fun spinnerCategoria(listCategoria: List<Categoria>?){

        if (listCategoria != null){
            binding.spinnerCategoria.adapter =
                ArrayAdapter(
                    // Contexto onde está sendo criado o ArrayAdpter
                    requireContext(),
                    // Layout para o spinner
                    androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                    listCategoria
                )
        }
    }

    override fun onDateSelectec(data: LocalDate){
        mainViewModel.dataSelecionada.value = data
    }

}