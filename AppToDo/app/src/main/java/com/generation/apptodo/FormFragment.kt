package com.generation.apptodo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.apptodo.databinding.FragmentFormBinding

class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding
    private val mainViewModel : MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Inflando o layout fragment_form
        binding = FragmentFormBinding.inflate(layoutInflater, container, false)

       /*
       Este metodo só funcionaria para um único fragmento, formfragment
       mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java))*/

        //Ficar observando com base no ciclo de vida do fragment
        mainViewModel.myCategoriaResponse.observe(viewLifecycleOwner){
            //Verificar se está trazendo os dados
            Log.d("Requisicao", it.body().toString())
        }


        binding.buttonSalvar.setOnClickListener {
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        }

        return binding.root
    }


}