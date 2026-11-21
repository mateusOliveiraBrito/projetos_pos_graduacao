package com.br.mateus.posmoveis.appsqlite.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.br.mateus.posmoveis.appsqlite.entity.Cadastro

class DatabaseHandler(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    override fun onCreate(banco: SQLiteDatabase?) {
        banco?.execSQL(
            "CREATE TABLE IF NOT EXISTS cadastro( " +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT," +
                    "telefone TEXT)"
        )
    }

    override fun onUpgrade(
        banco: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        banco?.execSQL("DROP TABLE IF EXISTS cadastro")
        this.onCreate(banco)
    }

    fun inserir(cadastro: Cadastro) {
        val banco = this.writableDatabase

        val registro = ContentValues()
        registro.put("nome", cadastro.nome)
        registro.put("telefone", cadastro.telefone)

        banco.insert("cadastro", null, registro)
    }

    fun alterar(cadastro: Cadastro) {
        val banco = this.writableDatabase

        val registro = ContentValues()
        registro.put("nome", cadastro.nome)
        registro.put("telefone", cadastro.telefone)

        banco.update(
            "cadastro",
            registro,
            "_id = ${cadastro.id}",
            null
        )
    }

    fun excluir(id: Int) {
        val banco = this.writableDatabase
        banco.delete("cadastro", "_id =  ${id}", null)
    }

    fun pesquisar(id: Int): Cadastro? {
        val banco = this.writableDatabase
        val cursor: Cursor = banco.query(
            "cadastro",
            null,
            "_id = ${id}",
            null,
            null,
            null,
            null
        )

        if (cursor.moveToNext()) {
            val cadastro = Cadastro(
                cursor.getInt(ID),
                cursor.getString(NOME),
                cursor.getString(TELEFONE)
            )

            return cadastro
        }

        return null
    }

    fun listarCursor(): Cursor? {
        val banco = this.writableDatabase

        val cursor = banco.query(
            TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        return cursor
    }

    fun listarCadastros(): MutableList<Cadastro> {
        val banco = this.writableDatabase

        val cursor = banco.query(
            TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val registros: MutableList<Cadastro> = mutableListOf<Cadastro>()
        while (cursor.moveToNext()) {
            val cadastro = Cadastro(
                cursor.getInt(ID),
                cursor.getString(NOME),
                cursor.getString(TELEFONE)
            )

            registros.add(cadastro)
        }

        return registros
    }

    companion object {
        private const val ID = 0
        private const val NOME = 1
        private const val TELEFONE = 2
        private const val DATABASE_NAME = "banco.db"
        private const val TABLE_NAME = "cadastro"
        private const val DATABASE_VERSION = 1

        @Volatile
        private var INSTANCE: DatabaseHandler? = null

        fun getInstance(context: Context): DatabaseHandler {
            return INSTANCE ?: synchronized(this) {
                val instance = DatabaseHandler(context.applicationContext)
                INSTANCE = instance
                return instance
            }
        }
    }
}