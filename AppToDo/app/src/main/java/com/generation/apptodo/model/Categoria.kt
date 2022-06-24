package com.generation.apptodo.model

data class Categoria(
    var id: Long,
    var descricao: String?,
    var tarefas: List<Tarefa>?,
){
    //Alterar o modo como é exibilido a lista de categoria no spinner
    override fun toString(): String {
        // Informando que a descrição que irá retonar não pode ser nula, com o simbolo !!
        return descricao!!
    }
}