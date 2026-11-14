package com.br.mateus.posmoveis.trucocounter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var nomeJogador1: String = "Jogador 1"
    private var nomeJogador2: String = "Jogador 2"

    private var pontuacaoJogador1: Int = 0
    private var vitoriasJogador1: Int = 0
    private var pontuacaoJogador2: Int = 0
    private var vitoriasJogador2: Int = 0

    private lateinit var tvLabelJogador1: TextView
    private lateinit var tvLabelJogador2: TextView

    private lateinit var tvPontuacaoJogador1: TextView
    private lateinit var tvPontuacaoJogador2: TextView

    private lateinit var btnHistoricoDeJogadas: Button
    private lateinit var btnInformarNomes: Button
    private lateinit var btnZerarHistoricoJogadas: Button

    private lateinit var btnJogador11: Button
    private lateinit var btnJogador13: Button
    private lateinit var btnJogador16: Button
    private lateinit var btnJogador19: Button
    private lateinit var btnJogador112: Button

    private lateinit var btnJogador21: Button
    private lateinit var btnJogador23: Button
    private lateinit var btnJogador26: Button
    private lateinit var btnJogador29: Button
    private lateinit var btnJogador212: Button

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

        this.tvPontuacaoJogador1 = findViewById(R.id.tv_pontuacao_jogador_1)
        this.tvPontuacaoJogador2 = findViewById(R.id.tv_pontuacao_jogador_2)

        definirPontuacoesJogadores()

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

        this.btnJogador11 = findViewById(R.id.btn_jogador_1_1)
        this.btnJogador11.setOnClickListener {
            setPontuacaoJogador1(1)
        }

        this.btnJogador13 = findViewById(R.id.btn_jogador_1_3)
        this.btnJogador13.setOnClickListener {
            setPontuacaoJogador1(3)
        }

        this.btnJogador16 = findViewById(R.id.btn_jogador_1_6)
        this.btnJogador16.setOnClickListener {
            setPontuacaoJogador1(6)
        }

        this.btnJogador19 = findViewById(R.id.btn_jogador_1_9)
        this.btnJogador19.setOnClickListener {
            setPontuacaoJogador1(9)
        }

        this.btnJogador112 = findViewById(R.id.btn_jogador_1_12)
        this.btnJogador112.setOnClickListener {
            setPontuacaoJogador1(12)
        }

        this.btnJogador21 = findViewById(R.id.btn_jogador_2_1)
        this.btnJogador21.setOnClickListener {
            setPontuacaoJogador2(1)
        }

        this.btnJogador23 = findViewById(R.id.btn_jogador_2_3)
        this.btnJogador23.setOnClickListener {
            setPontuacaoJogador2(3)
        }

        this.btnJogador26 = findViewById(R.id.btn_jogador_2_6)
        this.btnJogador26.setOnClickListener {
            setPontuacaoJogador2(6)
        }

        this.btnJogador29 = findViewById(R.id.btn_jogador_2_9)
        this.btnJogador29.setOnClickListener {
            setPontuacaoJogador2(9)
        }

        this.btnJogador212 = findViewById(R.id.btn_jogador_2_12)
        this.btnJogador212.setOnClickListener {
            setPontuacaoJogador2(12)
        }
    }

    fun definirPontuacoesJogadores() {
        this.tvPontuacaoJogador1.setText(this.pontuacaoJogador1.toString())
        this.tvPontuacaoJogador2.setText(this.pontuacaoJogador2.toString())
    }

    fun definirNomesJogadores(nomeJogador1: String, nomeJogador2: String) {
        this.tvLabelJogador1.text = "${nomeJogador1}:"
        this.tvLabelJogador2.text = "${nomeJogador2}:"
    }

    fun btnHistoricoDeJogadasClick() {
        val intent = Intent(this, HistoricoDeJogadasActivity::class.java)
        intent.putExtra("nomeJogador1", this.nomeJogador1)
        intent.putExtra("nomeJogador2", this.nomeJogador2)
        intent.putExtra("vitoriasJogador1", this.vitoriasJogador1)
        intent.putExtra("vitoriasJogador2", this.vitoriasJogador2)
        startActivity(intent)
    }

    fun btnInformarNomesClick() {
        val intent = Intent(this, InformarNomesActivity::class.java)
        intent.putExtra("nomeJogador1", this.nomeJogador1)
        intent.putExtra("nomeJogador2", this.nomeJogador2)
        getResultInformarNomes.launch(intent)
    }

    private val getResultInformarNomes =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                this.nomeJogador1 = it.data?.getStringExtra("nomeJogador1") ?: "Jogador 1"
                this.nomeJogador2 = it.data?.getStringExtra("nomeJogador2") ?: "Jogador 2"

                definirNomesJogadores(this.nomeJogador1, this.nomeJogador2)
            }
        }

    fun btnZerarHistoricoDeJogadasClick() {
        this.vitoriasJogador1 = 0
        this.vitoriasJogador2 = 0
        zerarPontuacoes()
        definirPontuacoesJogadores()
    }

    fun setPontuacaoJogador1(pontos: Int) {
        this.pontuacaoJogador1 += pontos
        checarVitoriaJogador()
        definirPontuacoesJogadores()
    }

    fun setPontuacaoJogador2(pontos: Int) {
        this.pontuacaoJogador2 += pontos
        checarVitoriaJogador()
        definirPontuacoesJogadores()
    }

    fun checarVitoriaJogador() {
        if (this.pontuacaoJogador1 >= 12) {
            Toast.makeText(this, "${this.nomeJogador1} ganhou a partida!", Toast.LENGTH_SHORT)
                .show()
            zerarPontuacoes()
            this.vitoriasJogador1++
        }

        if (this.pontuacaoJogador2 >= 12) {
            Toast.makeText(this, "${this.nomeJogador2} ganhou a partida!", Toast.LENGTH_SHORT)
                .show()
            zerarPontuacoes()
            this.vitoriasJogador2++
        }
    }

    fun zerarPontuacoes() {
        this.pontuacaoJogador1 = 0
        this.pontuacaoJogador2 = 0
    }
}