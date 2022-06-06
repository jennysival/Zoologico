package br.com.zup.zoologico.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

class Animal(private val nome: String, private val descricao: String): Parcelable {

    fun getNome() = nome

    fun getDescricao() = descricao
}