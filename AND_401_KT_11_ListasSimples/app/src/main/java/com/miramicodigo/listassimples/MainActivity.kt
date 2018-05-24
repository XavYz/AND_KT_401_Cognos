package com.miramicodigo.listassimples

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.ArrayAdapter
import android.widget.ListView


class MainActivity : AppCompatActivity() {

    var lvDatos: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvDatos = findViewById(R.id.lvDatos) as ListView

        val datos = ArrayList<String>()

        for (i in 0..499) {
            datos.add("Item en posicion: " + (i + 1))
        }

        val adaptador = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, datos)

        lvDatos!!.adapter = adaptador

        lvDatos!!.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(applicationContext,
                    "Click en ${ i + 1 }", Toast.LENGTH_SHORT).show()
        }

    }
}
