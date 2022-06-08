package paulo.antonio.listadetarefas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import paulo.antonio.listadetarefas.databinding.FragmentFormBinding
import paulo.antonio.listadetarefas.databinding.FragmentListBinding


class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /* Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_form, container, false)
        Instanciando o botão salvar
        val buttonSalvar = view.findViewById<Button>(R.id.buttonSalvar)*/

        //Inflando o layot fragment_form
        binding = FragmentFormBinding.inflate(layoutInflater, container, false)



        binding.buttonSalvar.setOnClickListener {
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        }


        return binding.root
    }
}