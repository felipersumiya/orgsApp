package br.com.felipersumiya.orgs.dao

import br.com.felipersumiya.orgs.model.Produto

class ProdutoDao {

    fun adiciona(produto:Produto){

        produtos.add(produto)
    }

    fun buscaTodos(): List<Produto>{

        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto(
                "Salada de Frutas", "Frutas em geral","20.20".toBigDecimal()
            )
        ) // mantém a mesma lista na instância
    }
}