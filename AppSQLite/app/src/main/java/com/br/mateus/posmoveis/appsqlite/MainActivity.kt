package com.br.mateus.posmoveis.appsqlite

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.mateus.posmoveis.appsqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var banco: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.banco = openOrCreateDatabase("banco.db", MODE_PRIVATE, null)
        this.banco.execSQL(
            "CREATE TABLE IF NOT EXISTS cadastro( " +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT," +
                    "telefone TEXT)"
        )

        this.binding.btnIncluir.setOnClickListener {
            btnIncluirClick()
        }

        this.binding.btnAlterar.setOnClickListener {
            btnAlterarClick()
        }

        this.binding.btnExcluir.setOnClickListener {
            btnExcluirClick()
        }

        this.binding.btnPesquisar.setOnClickListener {
            btnPesquisarClick()
        }

        this.binding.btnListar.setOnClickListener {
            btnListarClick()
        }
    }

    fun btnIncluirClick() {
        Toast.makeText(this, "TESTE", Toast.LENGTH_SHORT).show()

        val registro = ContentValues()
        registro.put("nome", this.binding.edTxtNome.text.toString())
        registro.put("telefone", this.binding.edTxtTelefone.text.toString())

        banco.insert("cadastro", null, registro)

        Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
    }

    fun btnAlterarClick() {
        val registro = ContentValues()
        registro.put("nome", this.binding.edTxtNome.text.toString())
        registro.put("telefone", this.binding.edTxtTelefone.text.toString())

        banco.update(
            "cadastro",
            registro,
            "_id = ${this.binding.edTxtCodigo.text.toString()}",
            null
        )

        Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
    }

    fun btnExcluirClick() {
        banco.delete("cadastro", "_id =  ${this.binding.edTxtCodigo.text.toString()}", null)

        Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
    }

    fun btnPesquisarClick() {
        val cursor: Cursor = banco.query(
            "cadastro",
            null,
            "_id = ${this.binding.edTxtCodigo.text.toString()}",
            null,
            null,
            null,
            null
        )

        if (cursor.moveToNext()) {
            binding.edTxtNome.setText(cursor.getString(1))
            binding.edTxtTelefone.setText(cursor.getString(2))
        } else {
            Toast.makeText(this, "Registro não encontrado", Toast.LENGTH_SHORT).show()
        }
    }

    fun btnListarClick() {
        val cursor = banco.query(
            "cadastro",
            null,
            null,
            null,
            null,
            null,
            null
        )

        val saida = StringBuilder()

        while (cursor.moveToNext()) {
            saida.append(cursor.getString(NOME))
            saida.append(" - ")
            saida.append(cursor.getString(TELEFONE))
            saida.append("\n")
        }

        Toast.makeText(
            this,
            saida.toString(),
            Toast.LENGTH_SHORT
        ).show()
    }

    companion object {
        private const val ID = 0
        private const val NOME = 1
        private const val TELEFONE = 2
    }
}