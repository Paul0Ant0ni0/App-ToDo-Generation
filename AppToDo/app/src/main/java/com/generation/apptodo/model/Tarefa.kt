package com.generation.apptodo.model

data class Tarefa(
    var id: Long,
    var nome: String,
    var descricacao: String,
    var responsavel: String,
    var data: String,
    var status: Boolean,
    var categoria: Categoria)