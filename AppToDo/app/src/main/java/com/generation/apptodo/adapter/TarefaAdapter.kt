package com.generation.apptodo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.generation.apptodo.databinding.CardLayoutBinding
import com.generation.apptodo.model.Tarefa

class TarefaAdapter : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>(){

    private var listTarefa = emptyList<Tarefa>()

    class TarefaViewHolder (val binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root){

    }

    //Criando uma viewHolder/Card e jogando os itens
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        return TarefaViewHolder(CardLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    //Criando a tarefa e jogando o item dentro do card layout
    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = listTarefa[position]

        holder.binding.textNome.text = tarefa.nome
        holder.binding.textDescricao.text = tarefa.descricacao
        holder.binding.textResponsavel.text = tarefa.responsavel
        holder.binding.textData.text = tarefa.data
        holder.binding.switchAndamento.isChecked = tarefa.status
        holder.binding.textCategoria.text = tarefa.categoria.descricao

    }

    //Retornado a quantidade de componentes que vai ser criado.
    override fun getItemCount(): Int {
        return listTarefa.size
    }

    fun setlist(List: List<Tarefa>){
        listTarefa = List
        notifyDataSetChanged()
    }

}