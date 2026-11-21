package com.br.mateus.posmoveis.appsqlite.adapter

import android.widget.BaseAdapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.br.mateus.posmoveis.appsqlite.R
import com.br.mateus.posmoveis.appsqlite.entity.Cadastro

class ItemListaAdapter(
    val context: Context,
    val registros: MutableList<Cadastro>): BaseAdapter() {
    override fun getCount(): Int {
        return registros.size
    }

    override fun getItem(position: Int): Any? {
        return registros.get(position)
    }

    override fun getItemId(position: Int): Long {
        return registros.get(position).id.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val v = inflater.inflate(R.layout.item_lista, null )

        val txtNomeElemento = v.findViewById<TextView>(R.id.txt_nome_elemento)
        val txtTelefoneElemento = v.findViewById<TextView>(R.id.txt_telefone_elemento)
        val btnEditarElemento = v.findViewById<Button>(R.id.btn_editar)

        txtNomeElemento.text = registros.get(position).nome
        txtTelefoneElemento.text = registros.get(position).telefone

        btnEditarElemento.setOnClickListener {
            Toast.makeText(context, "Em desenvolvimento", Toast.LENGTH_SHORT).show()
        }

        return v
    }
}