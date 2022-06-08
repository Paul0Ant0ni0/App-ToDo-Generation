package paulo.antonio.listadetarefas

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import paulo.antonio.listadetarefas.adapter.TarefaAdapter
import paulo.antonio.listadetarefas.databinding.FragmentListBinding
import paulo.antonio.listadetarefas.model.Tarefa

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

        binding = FragmentListBinding.inflate(layoutInflater, container, false)

        //Armazenar o objeto/layout dentro da variável e retorna-lá.
        /*val view = inflater.inflate(R.layout.fragment_list, container, false)
        val floatingAdd = view.findViewById<FloatingActionButton>(R.id.floatingAdd)*/

        //Instancinado a data class Tarefa, sendo assim, criando objetos.
        val listTarefas = listOf(
            Tarefa (
                "Lavar a Louça",
                "Lavar a loouça do dia todo",
                "João",
                "2022-05-15",
                false,
                "Dia-aDia"
                    ),

            Tarefa (
                "Ir ao cinema",
                "Lavar a loouça do dia todo",
                "João",
                "2022-05-15",
                false,
                "Dia-a-Dia"
                    ),

            Tarefa (
                "Ir ao Parque",
                "Lavar a loouça do dia todo",
                "Otávio",
                "2022-05-15",
                false,
                "Dia-a-Dia"
            ),
            Tarefa (
                "Assistir OrangeBlack",
                "Lavar a loouça do dia todo",
                "João",
                "2022-05-15",
                false,
                "Dia-a-Dia"
            )



        )

        //Configuração do RecyclerView
        val adapter = TarefaAdapter()

        binding.recyclerTarefa.layoutManager = LinearLayoutManager(context)
        binding.recyclerTarefa.adapter = adapter
        binding.recyclerTarefa.setHasFixedSize(true)

        adapter.setList(listTarefas)

        //Referenciando componentes
        binding.floatingAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_formFragment2)
        }


        return binding.root

    }

}