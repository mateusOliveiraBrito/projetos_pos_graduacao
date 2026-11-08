package com.br.mateus.posmoveis.prjtrocadetela

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListarActivity : AppCompatActivity() {

    private lateinit var listViewProdutos: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.listViewProdutos)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.listViewProdutos = findViewById(R.id.listViewProdutos)
        this.listViewProdutos.setOnItemClickListener { parent, view, position, id ->
            val codigoProduto = position + 1
            intent.putExtra("codigo", codigoProduto)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}