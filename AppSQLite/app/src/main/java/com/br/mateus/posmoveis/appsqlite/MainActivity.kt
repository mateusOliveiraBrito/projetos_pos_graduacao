package com.br.mateus.posmoveis.appsqlite

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.mateus.posmoveis.appsqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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

    fun btnIncluirClick() {}

    fun btnAlterarClick() {}

    fun btnExcluirClick() {}

    fun btnPesquisarClick() {}

    fun btnListarClick() {}
}