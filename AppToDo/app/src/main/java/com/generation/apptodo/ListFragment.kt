package com.generation.apptodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.generation.apptodo.adapter.TarefaAdapter
import com.generation.apptodo.databinding.FragmentListBinding
import com.generation.apptodo.model.Tarefa
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater, container,false)

        //Instancinado a data class Tarefa, sendo assim, criando objetos.
        val listTarefas = listOf(
            Tarefa(
                "Caminhada",
                "Dar 03 voltas pelo bairro",
                "Paulo",
                "2022-07-07",
                false,
                "Atividade Física"
            ),
            Tarefa(
                "Limpar o quintal",
                "Lavar o quintal com a água do tanquinho",
                "Paulo",
                "2022-06-07",
                false,
                "Doméstico"
            ),
            Tarefa(
                "Assistir a Série: Cavaleiro da Lua",
                "Maratonar com a Disney Plus",
                "Paulo",
                "2022-06-10",
                false,
                "Lazer"
            )

        )

        //Configuração do RecyclerView para exibir o layout de forma linear

        val adapter = TarefaAdapter()
        binding.recyclerTarefa.layoutManager = LinearLayoutManager(context)
        binding.recyclerTarefa.adapter = adapter
        binding.recyclerTarefa.setHasFixedSize(true)

        adapter.setlist(listTarefas)

        binding.floatingAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_formFragment)
        }

        return binding.root
    }
}


