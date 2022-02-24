package com.example.banco

import android.content.ContentValues
import android.content.Context

class PessoaDAO (context: Context) {
    private lateinit var banco: BancoHelper

    init {
        this.banco = BancoHelper(context)
    }

    fun add(pessoa: Pessoa){
        val cv = ContentValues()
        cv.put("nome", pessoa.nome)
        cv.put("datahora", pessoa.datahora)
        this.banco.writableDatabase.insert("pessoas", null, cv)
    }

    fun get(): MutableList<Pessoa>{
        val lista = mutableListOf<Pessoa>()
        val colunas = arrayOf("id", "nome", "datahora")
        val cursor = this.banco.readableDatabase.query("pessoas", colunas, null, null, null, null, null)
        cursor.moveToFirst()
        for (i in 1..cursor.count){
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val nome = cursor.getString(cursor.getColumnIndex("nome"))
            val datahora = cursor.getLong(cursor.getColumnIndex("datahora"))
            lista.add(Pessoa(id, nome, datahora))
            cursor.moveToNext()
        }

        return lista
    }


    fun get(id: Int): Pessoa?{

        val colunas = arrayOf("id", "nome", "datahora")
        val where = "id = ?"
        val pWhere = arrayOf(id.toString())

        // irá retornar as colunas id, nome e datahora baseado no id recebido no método
        val cursor = this.banco.readableDatabase.query("pessoas", colunas, where, pWhere,
            null, null, null)

        cursor.moveToFirst()

        if (cursor.count == 1){ // se retornar o resultado desejado
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val nome = cursor.getString(cursor.getColumnIndex("nome"))
            val datahora = cursor.getLong(cursor.getColumnIndex("datahora"))

            return Pessoa(id, nome, datahora)
        }
        return null // se retornar 0 ou mais de uma Pessoa com o mesmo id
    }

    fun delete(pessoa: Pessoa){
        val where = "id = ?"
        val pWhere = arrayOf(pessoa.id.toString())
        this.banco.writableDatabase.delete("pessoas", where, pWhere)
    }

    fun update(pessoa: Pessoa){
        val cv = ContentValues()
        val where = "id = ?"
        val pWhere = arrayOf(pessoa.id.toString())

        cv.put("nome", pessoa.nome)
        this.banco.writableDatabase.update("pessoas", cv, where, pWhere)
    }
}