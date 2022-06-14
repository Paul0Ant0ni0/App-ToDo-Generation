package com.generation.apptodo

fun main(){

    /*

    1 - CoroutineScope

    GlobalScope - Escopo global do projeto | (Ex: O processo será finalizado quando fechar o app)
    ViewModelScope - Escopo da ViewModel | (Ciclo da vida da viewModel)
    lifecycleScope - Escopo da nossa UI (Activity ou Fragment) | (Ciclo da vida da UI)

    suspend - Fumção pode ser susoensa

    2 - job - Identificação da corrotina dentro do projeto

    Quando uma corrotina é executada é automaticamente gerado um job
    Que nada mais é do que a identificação única da corrotina dentro do projeto

    3 - Contexto - Dispatchers | Tipo de thread que será executado cada uma das corrotinas

    Main - Otimizado para mexer com dados pequenos | Dados que podem ser processados dentro da UI
    IO - Otimizado para redes | Dados vindos da rede
    Default - Otimizado para operação que exigem mais do processador | Dados que exigem mais
                                                                       do processador do celular
                                                                       ou maquina



     */
}