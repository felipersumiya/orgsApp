package br.com.felipersumiya.orgs.ui.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.felipersumiya.orgs.databinding.FormularioImagemBinding
import br.com.felipersumiya.orgs.extensions.tentaCarregar

class FormularioImagemDialog(private val context: Context) {

    fun mostra(
        urlPadrao: String? = null,
        quandoImagemCarregada: (imagem: String) -> Unit,
    ){

        val bidingFormularioImagem = FormularioImagemBinding.inflate(LayoutInflater.from(context)).apply {
          urlPadrao?.let{
                formularioImagemImageview.tentaCarregar(it)
                formularioImagemUrl.setText(it)
            }

            formularioImagemButton.setOnClickListener {
                val urlImagem = formularioImagemUrl.text.toString()
                formularioImagemImageview.tentaCarregar(urlImagem)
            }


            AlertDialog.Builder(context)
                .setPositiveButton("Confirmar"){ _, _ ->
                    val url = formularioImagemUrl.text.toString()
                    Log.i("FormularioImagemDialog", "mostra $url")
                    //binding.imagemFormulario.tentaCarregar(url)
                    quandoImagemCarregada(url)

                }.setView(root)
                .setNegativeButton("Cancelar"){_,_->

                }
                .show()

            //Foi utlizado o Apply aqui para aproveitar o FormularioImagemBiding que é
             // utilizado várias vezes nesta classe, evitando que fosse chamado todo o instante a variável bidingFormularioImagem

        }









    }
}