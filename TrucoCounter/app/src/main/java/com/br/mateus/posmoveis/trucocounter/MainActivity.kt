package com.br.mateus.posmoveis.trucocounter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnHistoricoDeJogadas: Button
    private lateinit var btnInformarNomes: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.btnHistoricoDeJogadas = findViewById(R.id.btn_historico_de_jogadas)
        this.btnHistoricoDeJogadas.setOnClickListener {
            btnHistoricoDeJogadasClick()
        }

        this.btnInformarNomes = findViewById(R.id.btn_informar_nomes)
        this.btnInformarNomes.setOnClickListener {
            btnInformarNomesClick()
        }
    }

    fun btnHistoricoDeJogadasClick() {
        val intent = Intent(this, HistoricoDeJogadasActivity::class.java)
        startActivity(intent)
    }

    fun btnInformarNomesClick() {
        val intent = Intent(this, InformarNomesActivity::class.java)
        startActivity(intent)
    }
}