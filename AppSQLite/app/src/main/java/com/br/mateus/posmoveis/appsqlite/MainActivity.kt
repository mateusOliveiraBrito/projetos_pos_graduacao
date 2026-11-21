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
import com.br.mateus.posmoveis.appsqlite.database.DatabaseHandler
import com.br.mateus.posmoveis.appsqlite.databinding.ActivityMainBinding
import com.br.mateus.posmoveis.appsqlite.entity.Cadastro

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var banco: DatabaseHandler

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

        this.banco = DatabaseHandler(this)

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
        val cadastro = Cadastro(
            0,
            binding.edTxtNome.text.toString(),
            binding.edTxtTelefone.text.toString(),
        )

        banco.inserir(cadastro)

        Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
    }

    fun btnAlterarClick() {
        val cadastro = Cadastro(
            binding.edTxtCodigo.text.toString().toInt(),
            binding.edTxtNome.text.toString(),
            binding.edTxtTelefone.text.toString(),
        )

        banco.alterar(cadastro)

        Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
    }

    fun btnExcluirClick() {
        banco.excluir(binding.edTxtCodigo.text.toString().toInt())

        Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
    }

    fun btnPesquisarClick() {
        val cadastro: Cadastro? = banco.pesquisar(binding.edTxtCodigo.text.toString().toInt())

        if (cadastro != null) {
            binding.edTxtNome.setText(cadastro.nome)
            binding.edTxtTelefone.setText(cadastro.telefone)
        } else {
            Toast.makeText(this, "Registro não encontrado", Toast.LENGTH_SHORT).show()
        }
    }

    fun btnListarClick() {
        val cadastros = banco.listarCadastros()

        val saida = StringBuilder()

        for (cadastro in cadastros) {
            saida.append(cadastro.nome)
            saida.append(" - ")
            saida.append(cadastro.telefone)
            saida.append("\n")
        }

        Toast.makeText(
            this,
            saida.toString(),
            Toast.LENGTH_SHORT
        ).show()
    }
}