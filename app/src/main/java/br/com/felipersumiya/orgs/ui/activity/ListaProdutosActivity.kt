package br.com.felipersumiya.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.felipersumiya.orgs.R
import br.com.felipersumiya.orgs.dao.ProdutoDao
import br.com.felipersumiya.orgs.databinding.ActivityListaProdutosBinding
import br.com.felipersumiya.orgs.ui.dialog.FormularioImagemDialog
import br.com.felipersumiya.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListaProdutosActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    private val dao = ProdutoDao()
    private val adapter = ListaProdutosAdapter(context = this, listaProdutos = dao.buscaTodos())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraRecyclerView()
        configuraFab()
        setContentView(binding.root)
//        FormularioImagemDialog(this).mostra{imagemUrl->
//            Log.i("ListaProdutos","url:$imagemUrl")
//
//        }
        Log.i("ListaProdutos","lista produtos:${dao.buscaTodos()}")

    }

    override fun onResume() {
        super.onResume()
        adapter.atuddaliza(dao.buscaTodos())
        Log.i("ListaProdutos","lista produtos:${dao.buscaTodos()}")

    }

    fun configuraFab() {

        val fab = binding.extentedFAB
        //val fab = findViewById<FloatingActionButton>(R.id.activity_lista_produtos_floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this, FormularioProdutoActivity::class.java)//chama o formulário a partir do componente fab
            startActivity(intent)

        }
    }

    fun configuraRecyclerView() {

        val recyclerView= binding.activityListaProdutosRecyclerview
        //val recyclerView = findViewById<RecyclerView>(R.id.activity_lista_produtos_recyclerview)
        val dao = ProdutoDao()
        Log.i("MainActivity", "onCreate:${dao.buscaTodos()} ")
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}