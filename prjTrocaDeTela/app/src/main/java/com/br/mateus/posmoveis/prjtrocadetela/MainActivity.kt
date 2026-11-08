package com.br.mateus.posmoveis.prjtrocadetela

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnOpenLancamento: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.btnOpenLancamento = findViewById(R.id.btn_open_lancamento)
        this.btnOpenLancamento.setOnClickListener {
            btnOpenLancamentoClick()
        }
    }

    fun btnOpenLancamentoClick() {
        // TROCA DE TELA SEM PASSAGEM DE PARÂMETROS
        val intent = Intent(this, LancamentoActivity::class.java )
        startActivity(intent)
    }
}