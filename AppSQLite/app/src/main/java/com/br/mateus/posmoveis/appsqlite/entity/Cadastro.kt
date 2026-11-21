package com.br.mateus.posmoveis.appsqlite.entity

data class Cadastro(
    val id: Int,
    val nome: String,
    val telefone: String
) {
    constructor(id: Any) : this()
}
