package com.miramicodigo.sqlite.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.miramicodigo.sqlite.db.DatabaseAdapter
import android.widget.RadioButton
import android.widget.EditText
import com.miramicodigo.sqlite.R
import kotlinx.android.synthetic.main.activity_formulario.*

class FormularioActivity : AppCompatActivity() {

    private var db: DatabaseAdapter? = null

    private var edicion: Boolean = false

    private var id: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        db = DatabaseAdapter(this)
        db!!.abrir()

        if (intent.extras != null) {
            edicion = true
            title = "Editar persona"
            id = intent.getLongExtra("id", 0)
            val cursor = db!!.obtenerPersona(id)
            if (cursor.moveToFirst()) {
                val nombre = cursor.getString(1)
                val telefono = cursor.getString(2)
                val correo = cursor.getString(3)
                val genero = cursor.getString(4)

                etNombre!!.setText(nombre)
                etTelefono!!.setText(telefono)
                etCorreo!!.setText(correo)
                if (genero.equals("m", ignoreCase = true)) {
                    rbMasculino!!.isChecked = true
                } else {
                    rbFemenino!!.isChecked = true
                }
            }
        } else {
            edicion = false
            title = "Nueva persona"
        }

    }

    fun aceptar(view: View) {
        if (etNombre!!.text.toString() == "" ||
                etCorreo!!.text.toString() == "" ||
                etTelefono!!.text.toString() == "") {
            Toast.makeText(applicationContext, "Hay campos vacios", Toast.LENGTH_LONG).show()
        } else {
            val nombre = etNombre!!.text.toString()
            val telefono = java.lang.Long.parseLong(etTelefono!!.text.toString())
            val correo = etCorreo!!.text.toString()
            val genero = if (rbMasculino!!.isChecked) "m" else "f"
            if (edicion) {
                db!!.actualizarPersona(id, nombre, telefono, correo, genero)
            } else {
                db!!.adicionarPersona(nombre, telefono, correo, genero)
            }
            finish()
        }
    }

    fun cancelar(view: View) {
        finish()
    }

    override fun onStop() {
        super.onStop()
        db!!.cerrar()
    }

}
