package com.generation.apptodo.model

data class Tarefa(
    var id: String,
    var nome: String,
    var descricacao: String,
    var responsavel: String,
    var data: Boolean,
    var status: String,
    var categoria: Categoria
        ) {
}