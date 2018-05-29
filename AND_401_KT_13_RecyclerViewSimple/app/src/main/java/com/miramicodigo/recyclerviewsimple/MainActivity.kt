package com.miramicodigo.recyclerviewsimple

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var listaDatos: ArrayList<String>? = null
    private var adaptador: RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //asignar la forma en que se visualizaran los items
        rvDatos.layoutManager = LinearLayoutManager(applicationContext)

        listaDatos = llenarDatos()

        adaptador = RecyclerViewAdapter(listaDatos!!)

        rvDatos.adapter = adaptador
    }

    fun llenarDatos(): ArrayList<String> {
        var datos = ArrayList<String>()
        for (i in 0..500) {
            datos.add("Item en posicion: ${i + 1}")
        }
        return datos
    }

    //Enlace con el diseño del Item de la lista
    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTexto : TextView = itemView as TextView
    }

    //Enlazar un LAYOUT, asignar datos
    inner class RecyclerViewAdapter(val data: ArrayList<String>) : RecyclerView.Adapter<RecyclerViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val layoutInflater = LayoutInflater.from(applicationContext)
            val view = layoutInflater.inflate(android.R.layout.simple_list_item_1,
                    parent, false)
            return RecyclerViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            val dato = data[position]
            holder.tvTexto.text = dato
        }

        override fun getItemCount(): Int {
            return data.size
        }
    }
}
