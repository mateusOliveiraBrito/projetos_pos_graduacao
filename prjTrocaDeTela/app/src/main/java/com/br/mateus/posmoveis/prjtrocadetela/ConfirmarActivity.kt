package com.br.mateus.posmoveis.prjtrocadetela

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmarActivity : AppCompatActivity() {
    private lateinit var txtCodigoValor: TextView
    private lateinit var txtQuantidadeValor: TextView
    private lateinit var txtValorValor: TextView
    private lateinit var btnEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.txtCodigoValor = findViewById(R.id.txtCodigoValor)
        this.txtQuantidadeValor = findViewById(R.id.txtQuantidadeValor)
        this.txtValorValor = findViewById(R.id.txtValorValor)
        this.btnEnviar = findViewById(R.id.btnEnviar)

        // RECUPERANDO OS PARÂMETROS RECEBIDOS DA ACITIVY ANTERIOR
        this.txtCodigoValor.text = intent.getStringExtra("codigo")
        this.txtQuantidadeValor.text = intent.getStringExtra("quantidade")
        this.txtValorValor.text = intent.getStringExtra("valor")

        this.btnEnviar.setOnClickListener {
            btnEnviarClick()
        }
    }

    fun btnEnviarClick() {
        val sms_body = "Código: ${this.txtCodigoValor.text} Quantidade: ${this.txtQuantidadeValor.text} Valor: ${this.txtValorValor.text}"
        val number = "+5562999999999"

        val intent = Intent(Intent.ACTION_VIEW)
        intent.putExtra("sms_body", sms_body)
        intent.setData(Uri.parse("sms:${number}"))

        startActivity(intent)
    }
}