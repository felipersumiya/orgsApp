package br.com.felipersumiya.orgs.extensions

import android.widget.ImageView
import br.com.felipersumiya.orgs.R
import coil.load

fun ImageView.tentaCarregar(url:String?=null){

    load(url){
        error(R.drawable.erro)
        fallback(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}