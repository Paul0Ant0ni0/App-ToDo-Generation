package com.generation.apptodo.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.generation.apptodo.MainViewModel
import com.generation.apptodo.databinding.CardLayoutBinding
import com.generation.apptodo.main
import com.generation.apptodo.model.Tarefa

class TarefaAdapter (
    val taskClickListener: TeskClickListener,
    val mainViewModel: MainViewModel,
    val context: Context
        ): RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>(){

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

        holder.itemView.setOnClickListener{
            taskClickListener.onTaskClickListener(tarefa)
        }

        holder.binding.switchAndamento
            .setOnCheckedChangeListener { buttonView, isChecked ->
                tarefa.status = isChecked
                mainViewModel.updateTarefa(tarefa)
            }

        holder.binding.buttonDeletar.setOnClickListener {
            showAlertDialog(tarefa.id)
        }


    }

    //Retornado a quantidade de componentes que vai ser criado.
    override fun getItemCount(): Int {
        return listTarefa.size
    }

    fun setlist(list: List<Tarefa>){
        listTarefa = list
        notifyDataSetChanged()
    }
    private fun showAlertDialog(id: Long){
        AlertDialog.Builder(context)
            .setTitle("Excluir Tarefa")
            .setMessage("Deseja exluir a tarefa?")
            .setPositiveButton("Sim"){
                _,_-> mainViewModel.deleteTarefa(id)
            }
            .setNegativeButton("Não"){
                _,_ ->
            }.show()
    }

}