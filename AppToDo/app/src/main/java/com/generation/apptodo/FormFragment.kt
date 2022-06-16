package com.generation.apptodo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.apptodo.databinding.FragmentFormBinding
import com.generation.apptodo.fragment.DatePickerFragment
import com.generation.apptodo.fragment.TimerPickerListener
import com.generation.apptodo.model.Categoria
import com.generation.apptodo.model.Tarefa
import java.time.LocalDate

class FormFragment : Fragment(), TimerPickerListener {

    private lateinit var binding: FragmentFormBinding
    private val mainViewModel : MainViewModel by activityViewModels()
    private var categoriaSelecionada = 0L

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
           response -> Log.d("Requisicao", response.body().toString())
            spinnerCategoria(response.body())
        }

        //Observando que assim que a data atual for alterada no campo da data
        mainViewModel.dataSelecionada.observe(viewLifecycleOwner){
                //Setando a data atual dentro do campo editData, de forma automática
                selectedDate -> binding.editData.setText(selectedDate.toString())
        }

        binding.buttonSalvar.setOnClickListener {
            inserirNoBanco()
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

    private fun spinnerCategoria(listCategoria: List<Categoria>?){

        if (listCategoria != null){
            binding.spinnerCategoria.adapter =
                ArrayAdapter(
                    // Contexto onde está sendo criado o ArrayAdpter
                    requireContext(),
                    // Layout para o spinner
                    androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                    listCategoria
                )

            binding.spinnerCategoria.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val selected = binding.spinnerCategoria.selectedItem as Categoria

                        categoriaSelecionada = selected.id
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }

        }
    }

    private fun validarCampos(nome: String, descricao: String, responsavel: String
    ): Boolean{
        return !(
                (nome == "" || nome.length < 3 || nome.length > 20) ||
                (descricao == "" || descricao.length < 5 || descricao.length > 200) ||
                (responsavel == "" || responsavel.length < 3 || responsavel.length > 20)
                )
    }

    private fun inserirNoBanco(){
        val nome = binding.editNome.text.toString()
        val desc = binding.editDescricao.text.toString()
        val resp = binding.editResponsavel.text.toString()
        val data = binding.editData.text.toString()
        val status = binding.switchAtivoCard.isChecked
        val categoria = Categoria(categoriaSelecionada, null, null)

        if (validarCampos(nome, desc, resp)){
            val tarefa = Tarefa(0, nome, desc, resp, data, status, categoria)
            mainViewModel.addTarefa(tarefa)
            Toast.makeText(context, "Tarefa criada", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        }else{
            Toast.makeText(context, "Verifique os campos", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDateSelectec(data: LocalDate){
        mainViewModel.dataSelecionada.value = data
    }

}