package paulo.antonio.listadetarefas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import paulo.antonio.listadetarefas.databinding.CardLayoutBinding
import paulo.antonio.listadetarefas.model.Tarefa

class TarefaAdapter : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>(){

    private var listTarefa = emptyList<Tarefa>()

    class TarefaViewHolder (val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root){

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
        holder.binding.textDescricao.text = tarefa.descricao
        holder.binding.textResponsavel.text = tarefa.responsavel
        holder.binding.textData.text = tarefa.data
        holder.binding.switchAtivo.isChecked = tarefa.status
        holder.binding.textCategoria.text = tarefa.categoria

    }

    //Retornado a quantidade de componentes que vai ser criado.
    override fun getItemCount(): Int {
        return listTarefa.size
    }

    fun setList(list: List<Tarefa>){
        listTarefa = list
        notifyDataSetChanged()
    }
}