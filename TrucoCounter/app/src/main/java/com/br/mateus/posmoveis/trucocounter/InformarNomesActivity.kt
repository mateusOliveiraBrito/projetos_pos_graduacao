package com.br.mateus.posmoveis.trucocounter

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class InformarNomesActivity : AppCompatActivity() {
    private lateinit var inputNomejogador1: TextInputLayout
    private lateinit var inputNomejogador2: TextInputLayout
    private lateinit var edtxtNomejogador1: TextInputEditText
    private lateinit var edtxtNomejogador2: TextInputEditText
    private lateinit var btnConfirmarNomes: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_informar_nomes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.inputNomejogador1 = findViewById(R.id.input_nome_jogador_1)
        this.inputNomejogador2 = findViewById(R.id.input_nome_jogador_2)
        this.edtxtNomejogador1 = findViewById(R.id.edtxt_nome_jogador_1)
        this.edtxtNomejogador2 = findViewById(R.id.edtxt_nome_jogador_2)
        this.btnConfirmarNomes = findViewById(R.id.btn_confirmar_nomes)
        this.btnConfirmarNomes.setOnClickListener {
            btnConfirmarNomesClick()
        }

        carregarNomesJogadores()
    }

    fun carregarNomesJogadores() {
        var nomeJogador1: String? = intent.getStringExtra("nomeJogador1")
        if (nomeJogador1 != null) {
            this.edtxtNomejogador1.setText(nomeJogador1)
        }

        var nomeJogador2: String? = intent.getStringExtra("nomeJogador2")
        if (nomeJogador2 != null) {
            this.edtxtNomejogador2.setText(nomeJogador2)
        }
    }

    fun btnConfirmarNomesClick() {
        val nomeJogador1 = this.edtxtNomejogador1.text.toString()
        if (nomeJogador1.isBlank()){
            this.inputNomejogador1.error = "O nome do Jogador 1 deve ser informado"
        } else {
            this.inputNomejogador1.error = null
        }

        val nomeJogador2 = this.edtxtNomejogador2.text.toString()
        if (nomeJogador2.isBlank()){
            this.inputNomejogador2.error = "O nome do Jogador 2 deve ser informado"
        } else {
            this.inputNomejogador2.error = null
        }

        if (this.inputNomejogador1.error != null || this.inputNomejogador2.error != null) {
            return
        }

        intent.putExtra("nomeJogador1", nomeJogador1)
        intent.putExtra("nomeJogador2", nomeJogador2)
        setResult(RESULT_OK, intent)
        finish()
    }
}