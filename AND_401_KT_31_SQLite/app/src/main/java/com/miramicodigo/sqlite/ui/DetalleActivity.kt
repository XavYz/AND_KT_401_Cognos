package com.miramicodigo.sqlite.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.miramicodigo.sqlite.db.DatabaseAdapter
import com.miramicodigo.sqlite.R
import kotlinx.android.synthetic.main.activity_detalle.*

class DetalleActivity : AppCompatActivity() {

    var id: Long = 0
    var db: DatabaseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        title = "Detalle"

        db = DatabaseAdapter(this)
        db!!.abrir()

        id = intent.getLongExtra("id", 0)

        val cursor = db!!.obtenerPersona(id)

        if (cursor.moveToFirst()) {
            val nombre = cursor.getString(1)
            val telefono = cursor.getString(2)
            val correo = cursor.getString(3)
            val genero = cursor.getString(4)
            tvNombre!!.text = nombre
            tvTelefono!!.text = telefono
            tvCorreo!!.text = correo
            if (genero.equals("m", ignoreCase = true)) {
                tvGenero!!.text = "Masculino"
                ivImagen!!.setImageResource(R.drawable.man)
            } else {
                tvGenero!!.text = "Femenino"
                ivImagen!!.setImageResource(R.drawable.woman)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_detalle, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_editar -> {
                val intent = Intent(this, FormularioActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
                finish()
            }
            R.id.menu_eliminar -> {
                db!!.eliminarPersona(id)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        super.onStop()
        db!!.cerrar()
    }

}
