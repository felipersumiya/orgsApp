package br.com.felipersumiya.orgs.ui.recyclerview.adapter

import android.content.Context
import android.icu.text.NumberFormat
import android.util.Log
import android.util.LogPrinter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.felipersumiya.orgs.R
import br.com.felipersumiya.orgs.databinding.ProdutoItemBinding
import br.com.felipersumiya.orgs.extensions.tentaCarregar
import br.com.felipersumiya.orgs.model.Produto
import coil.load
import java.util.*

class ListaProdutosAdapter(

    private val context :Context,
    listaProdutos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>(){

    private val produtos = listaProdutos.toMutableList()

    class ViewHolder(private val biding: ProdutoItemBinding) : RecyclerView.ViewHolder(biding.root)//acessa a view que está em produto_item -View Responsavél para integrar a Recycler view
    {

        private val nome = biding.produtoItemNome
        private val descricao = biding.produtoItemDescricao
        private val valor = biding.produtoItemValor
        private  val imagem = biding.imageView

        fun vincula(produto: Produto) {

            nome.text = produto.nome
            descricao.text = produto.descricao
            val formaTador = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            val valorEmMoeda: String = formaTador.format(produto.valor)
            valor.text = valorEmMoeda
            print(valorEmMoeda)


            val visibilidade = if(produto.imagem != null){
                View.VISIBLE
            }else{
                View.GONE
            }

            imagem.visibility = visibilidade

            imagem.tentaCarregar(produto.imagem)
            LogPrinter(1,valorEmMoeda)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProdutoItemBinding.inflate(LayoutInflater.from(context), parent,false)
//        val inflater = LayoutInflater.from(context) // transformar layout em view (view que possui os produtos)
//        val view = inflater.inflate(R.layout.produto_item,parent,false)//layout que queremos mostrar em nosso RecyclerView
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {//vai pegar cada item de produto_item e colocar a posição na view
        val produto = produtos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int {
       return produtos.size
    }

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)

    }

}
