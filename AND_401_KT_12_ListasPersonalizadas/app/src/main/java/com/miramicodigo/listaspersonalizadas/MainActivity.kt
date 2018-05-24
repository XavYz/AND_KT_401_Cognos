package com.miramicodigo.listaspersonalizadas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.AdapterView
import android.app.Activity
import android.view.View
import com.miramicodigo.listaspersonalizadas.R.id.lvLista
import kotlinx.android.synthetic.main.activity_main.lvLista
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    var activity: Activity? = null
    var adaptador: CustomAdapter? = null
    var datos: ArrayList<Pokemon>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        datos = ArrayList()
        activity = this

        llenarPokemones()

        adaptador = CustomAdapter(activity!!, datos!!)

        lvLista!!.adapter = adaptador
        lvLista!!.onItemClickListener = this
    }

    fun llenarPokemones() {
        val arrayNombres = resources.getStringArray(R.array.nombre)
        val arrayTipos = resources.getStringArray(R.array.tipos)
        val arrayImagenes = resources.obtainTypedArray(R.array.image)

        for (i in arrayNombres.indices) {
            var poke = Pokemon()
            poke.titulo = arrayNombres[i]
            poke.subtitulo = arrayTipos[i]
            poke.imagen = arrayImagenes.getResourceId(i, -1)
            datos!!.add(poke)
        }
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val intent = Intent(this, DetalleActivity::class.java)
        intent.putExtra("poke", datos!![position])
        startActivity(intent)
    }
}
