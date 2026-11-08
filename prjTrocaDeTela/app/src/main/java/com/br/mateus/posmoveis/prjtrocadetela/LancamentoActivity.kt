package com.br.mateus.posmoveis.prjtrocadetela

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LancamentoActivity : AppCompatActivity() {

    private lateinit var edTxtCodigo: EditText
    private lateinit var edTxtQuantidade: EditText
    private lateinit var edTxtValor: EditText
    private lateinit var btnConfirmar: Button
    private lateinit var btnListar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lancamento)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.edTxtCodigo = findViewById(R.id.edTxtCodigo)
        this.edTxtQuantidade = findViewById(R.id.edTxtQuantidade)
        this.edTxtValor = findViewById(R.id.edTxtValor)
        this.btnConfirmar = findViewById(R.id.btnConfirmar)
        this.btnListar = findViewById(R.id.btnListar)

        this.btnConfirmar.setOnClickListener {
            btnConfirmarClick()
        }

        this.btnListar.setOnClickListener {
            btnListarClick()
        }
    }

    fun btnConfirmarClick() {
        // TROCA DE TELA COM PASSAGEM DE PARÂMETROS
        val intent = Intent(this, ConfirmarActivity::class.java)
        intent.putExtra("codigo", this.edTxtCodigo.text.toString())
        intent.putExtra("quantidade", this.edTxtQuantidade.text.toString())
        intent.putExtra("valor", this.edTxtValor.text.toString())
        startActivity(intent)
    }

    fun btnListarClick() {
        // TROCA DE TELA AGUARDANDO RETORNO DE PARÂMETROS
        val intent = Intent(this, ListarActivity::class.java)
        getResult.launch(intent)
    }

    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK) {
            val codigo = it.data?.getIntExtra("codigo", 0)
            this.edTxtCodigo.setText(codigo.toString())
        }
    }
}