package com.br.mateus.posmoveis.calculaimc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var edTxtPeso: EditText
    private lateinit var edTxtAltura: EditText
    private lateinit var txtResultado: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.edTxtPeso = findViewById(R.id.edTxtPeso)
        this.edTxtAltura = findViewById(R.id.edTxtAltura)
        this.txtResultado = findViewById(R.id.txtResultado)
        this.btnCalcular = findViewById(R.id.btnCalcular)
        this.btnLimpar = findViewById(R.id.btnLimpar)

        this.btnCalcular.setOnClickListener {
            btnCalcularOnClick()
        }

        this.btnLimpar.setOnClickListener {
            btnLimparOnClick()
        }
    }

    fun btnCalcularOnClick() {
        var resultado = 0.0
        val peso = this.edTxtPeso.text.toString().toDoubleOrNull()?: run {
            this.edTxtPeso.error = "O campo peso deve ser informado"
            this.edTxtPeso.requestFocus()
            return
        }

        val altura = this.edTxtAltura.text.toString().toDoubleOrNull()?: run {
            this.edTxtAltura.error = "O campo altura deve ser informado"
            this.edTxtAltura.requestFocus()
            return
        }

        resultado = peso / altura.pow(2)

        this.txtResultado.text = "%.2f".format(resultado)
    }

    private fun btnLimparOnClick() {
        this.edTxtPeso.text.clear()
        this.edTxtAltura.text.clear()
        this.txtResultado.text = "0.0"
        this.edTxtPeso.requestFocus()
    }
}