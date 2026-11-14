package com.br.mateus.posmoveis.trucocounter

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HistoricoDeJogadasActivity : AppCompatActivity() {
    private lateinit var tvLabelJogador1Historico: TextView
    private lateinit var tvLabelJogador2Historico: TextView
    private lateinit var tvVitoriasJogador1: TextView
    private lateinit var tvVitoriasJogador2: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historico_de_jogadas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.tvLabelJogador1Historico = findViewById(R.id.tv_label_jogador_1_historico)
        this.tvLabelJogador2Historico = findViewById(R.id.tv_label_jogador_2_historico)
        this.tvVitoriasJogador1 = findViewById(R.id.tv_vitorias_jogador_1)
        this.tvVitoriasJogador2 = findViewById(R.id.tv_vitorias_jogador_2)

        carregarNomesJogadores()
        carregarVitoriasJogadores()
    }

    fun carregarNomesJogadores() {
        var nomeJogador1: String? = intent.getStringExtra("nomeJogador1")
        if (nomeJogador1 != null) {
            this.tvLabelJogador1Historico.text = "${nomeJogador1}:"
        }

        var nomeJogador2: String? = intent.getStringExtra("nomeJogador2")
        if (nomeJogador2 != null) {
            this.tvLabelJogador2Historico.text = "${nomeJogador2}:"
        }
    }

    fun carregarVitoriasJogadores() {
        var vitoriasJogador1: Int = intent.getIntExtra("vitoriasJogador1", 0)
        this.tvVitoriasJogador1.text = vitoriasJogador1.toString()

        var vitoriasJogador2: Int = intent.getIntExtra("vitoriasJogador2", 0)
        this.tvVitoriasJogador2.text = vitoriasJogador2.toString()
    }
}