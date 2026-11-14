package com.br.mateus.posmoveis.trucocounter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var nomeJogador1: String = "Jogador 1"
    private var nomeJogador2: String = "Jogador 2"

    private lateinit var tvLabelJogador1: TextView
    private lateinit var tvLabelJogador2: TextView

    private lateinit var btnHistoricoDeJogadas: Button
    private lateinit var btnInformarNomes: Button

    private lateinit var btnZerarHistoricoJogadas: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.tvLabelJogador1 = findViewById(R.id.tv_label_jogador_1)
        this.tvLabelJogador2 = findViewById(R.id.tv_label_jogador_2)

        definirNomesJogadores(this.nomeJogador1, this.nomeJogador2)

        this.btnHistoricoDeJogadas = findViewById(R.id.btn_historico_de_jogadas)
        this.btnHistoricoDeJogadas.setOnClickListener {
            btnHistoricoDeJogadasClick()
        }

        this.btnInformarNomes = findViewById(R.id.btn_informar_nomes)
        this.btnInformarNomes.setOnClickListener {
            btnInformarNomesClick()
        }

        this.btnZerarHistoricoJogadas = findViewById(R.id.btn_zerar_historico_de_jogadas)
        this.btnZerarHistoricoJogadas.setOnClickListener {
            btnZerarHistoricoDeJogadasClick()
        }
    }

    fun definirNomesJogadores(nomeJogador1: String, nomeJogador2: String) {
        this.tvLabelJogador1.text = "${nomeJogador1}:"
        this.tvLabelJogador2.text = "${nomeJogador2}:"
    }

    fun btnHistoricoDeJogadasClick() {
        val intent = Intent(this, HistoricoDeJogadasActivity::class.java)
        startActivity(intent)
    }

    fun btnInformarNomesClick() {
        val intent = Intent(this, InformarNomesActivity::class.java)
        intent.putExtra("nomeJogador1", this.nomeJogador1)
        intent.putExtra("nomeJogador2", this.nomeJogador2)
        getResultInformarNomes.launch(intent)
    }

    private val getResultInformarNomes = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK) {
            this.nomeJogador1 = it.data?.getStringExtra("nomeJogador1") ?: "Jogador 1"
            this.nomeJogador2 = it.data?.getStringExtra("nomeJogador2") ?: "Jogador 2"

            definirNomesJogadores(this.nomeJogador1, this.nomeJogador2)
        }
    }

    fun btnZerarHistoricoDeJogadasClick() {

    }
}