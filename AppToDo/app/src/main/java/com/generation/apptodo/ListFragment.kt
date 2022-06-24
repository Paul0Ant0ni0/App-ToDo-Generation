package com.generation.apptodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.generation.apptodo.adapter.TarefaAdapter
import com.generation.apptodo.adapter.TeskClickListener
import com.generation.apptodo.databinding.FragmentListBinding
import com.generation.apptodo.model.Tarefa

class ListFragment : Fragment(), TeskClickListener {

    /*Deve utilizar este código nas dependicias para ativar

   Ele serve para ter todos os componentes a partir de apenas uma referência
   viewBinding{
       enabled = true
   }*/

private lateinit var binding: FragmentListBinding
//Instanciar para pegar os dados da formfragment
private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater, container,false)


        mainViewModel.listTarefa()

        //Configuração do RecyclerView para exibir o layout de forma linear
        //Irá receber uma lista externa vinda da api.

        val adapter = TarefaAdapter(this, mainViewModel)

        binding.recyclerTarefa.adapter = adapter
        binding.recyclerTarefa.layoutManager = LinearLayoutManager(context)
        binding.recyclerTarefa.setHasFixedSize(true)

        binding.floatingAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_formFragment)
        }

        mainViewModel.myTarefaResponse.observe(viewLifecycleOwner){
            response -> if (response.body() != null){
                adapter.setlist(response.body()!!)
            }
        }

        return binding.root
    }

    override fun onTaskClickListener(tarefa: Tarefa) {
        mainViewModel.tarefaSelecionada = tarefa
        findNavController().navigate(R.id.action_listFragment_to_formFragment)
    }
}


