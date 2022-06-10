package com.generation.apptodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.generation.apptodo.adapter.TarefaAdapter
import com.generation.apptodo.databinding.FragmentListBinding

class ListFragment : Fragment() {

    /*Deve utilizar este código nas dependicias para ativar

   Ele serve para ter todos os componentes a partir de apenas uma referência
   viewBinding{
       enabled = true
   }*/

private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater, container,false)


        //Configuração do RecyclerView para exibir o layout de forma linear
        //Irá receber uma lista externa vinda da api.

        val adapter = TarefaAdapter()
        binding.recyclerTarefa.layoutManager = LinearLayoutManager(context)
        binding.recyclerTarefa.adapter = adapter
        binding.recyclerTarefa.setHasFixedSize(true)

        binding.floatingAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_formFragment)
        }

        return binding.root
    }
}


