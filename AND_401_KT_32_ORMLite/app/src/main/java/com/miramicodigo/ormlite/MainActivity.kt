package com.miramicodigo.ormlite

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Toast
import com.miramicodigo.ormlite.model.Persona
import com.miramicodigo.ormlite.model.PersonaDAO
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val personaDao = PersonaDAO()
    //var id: Int? = 0
    var personaAux: Persona? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadList()

        btnAdicionar.setOnClickListener {
            val input = etValores.text.toString()
            if (!input.isEmpty()) {
                personaDao.add(Persona(null, input))
                loadList()
            } else {
                Toast.makeText(applicationContext, "Debe ingresar un valor a guardar", Toast.LENGTH_SHORT).show()
            }
        }

        btnEliminar.setOnClickListener {
            if(personaAux != null) {
                personaDao.delete(personaAux!!)
                loadList()
            } else {
                Toast.makeText(applicationContext, "Seleccione un registro", Toast.LENGTH_SHORT).show()
            }
        }

        btnEliminarTodo.setOnClickListener {
            personaDao.removeAll()
            loadList()
        }
    }

    private fun loadList() {
        val allStudents: List<Persona>? = personaDao.queryForAll()
        val adapter = ArrayAdapter(this@MainActivity,
                android.R.layout.simple_list_item_1,
                allStudents)
        lvDatos.adapter = adapter

        lvDatos.setOnItemClickListener { adapterView, view, i, l ->
            personaAux = adapterView.getItemAtPosition(i) as Persona
            tvClickeado.text = "Nombre: ${personaAux!!.nombre} - ID: ${personaAux!!.id}"
            id = personaAux!!.id
        }

        tvClickeado.text= ""
        etValores.setText("")
        personaAux = null
    }

}
