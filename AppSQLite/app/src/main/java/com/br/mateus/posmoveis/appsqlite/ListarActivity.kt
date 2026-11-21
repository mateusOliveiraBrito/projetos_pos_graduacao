package com.br.mateus.posmoveis.appsqlite

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.CursorAdapter
import android.widget.SimpleCursorAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.mateus.posmoveis.appsqlite.adapter.ItemListaAdapter
import com.br.mateus.posmoveis.appsqlite.database.DatabaseHandler
import com.br.mateus.posmoveis.appsqlite.databinding.ActivityListarBinding

class ListarActivity : AppCompatActivity() {
    private lateinit var bindingListar: ActivityListarBinding
    private lateinit var banco: DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        banco = DatabaseHandler.getInstance(this)

        bindingListar = ActivityListarBinding.inflate(layoutInflater)

        setContentView(bindingListar.root)
        ViewCompat.setOnApplyWindowInsetsListener(bindingListar.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        montarListaInicial()
    }

    private fun montarListaInicial() {
        val cadastros = banco.listarCadastros()

        val listaAdapter = ItemListaAdapter(this, cadastros)

        bindingListar.lvCadastro.adapter = listaAdapter
    }
}
