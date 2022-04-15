package br.com.felipersumiya.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.felipersumiya.orgs.R
import br.com.felipersumiya.orgs.dao.ProdutoDao
import br.com.felipersumiya.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.felipersumiya.orgs.databinding.FormularioImagemBinding
import br.com.felipersumiya.orgs.extensions.tentaCarregar
import br.com.felipersumiya.orgs.model.Produto
import br.com.felipersumiya.orgs.ui.dialog.FormularioImagemDialog
import coil.load
import java.math.BigDecimal


class FormularioProdutoActivity :
    AppCompatActivity() {

    private val binding by lazy{
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    private var url: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraBotaoSalvar()
        setContentView(binding.root)
        binding.imagemFormulario.setOnClickListener {
          FormularioImagemDialog(this).mostra(url){ imagem->
              url = imagem
              binding.imagemFormulario.tentaCarregar(url)
          }

        }
    }

    fun configuraBotaoSalvar() {

        val botaoSalvar = binding.activityFormularioProdutoButtonSalvar
        //val botaoSalvar = findViewById<Button>(R.id.activity_formulario_produto_button_salvar)
        val dao = ProdutoDao()
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            dao.adiciona(produtoNovo)
            finish()
        }
    }

    fun criaProduto(): Produto {

       //binding.
       val nome = binding.formularioProdutoNome.text.toString()
        //val campoNome = findViewById<EditText>(R.id.activity_formulario_produto_nome)
       // val nome = campoNome.text.toString()
        val descricao = binding.formularioProdutoDescricao.text.toString()
      // val campoDescricao = findViewById<EditText>(R.id.activity_formulario_produto_descricao)
      // val descricao = campoDescricao.text.toString()

        val valorEmTexto = binding.formularioProdutoValor.text.toString()
       // val campoValor = findViewById<EditText>(R.id.activity_formulario_produto_valor)
       // val valorEmTexto = campoValor.text.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )

    }
}