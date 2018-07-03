package com.miramicodigo.sharedpreferences

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.content.SharedPreferences
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE);

        btnGuardarUno.setOnClickListener(this)
        btnGuardarDos.setOnClickListener(this)
        btnLeerUno.setOnClickListener(this)
        btnLeerDos.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.btnGuardarUno -> guardarValor(etTextoUno.text.toString(), "valor1", etTextoUno)
            R.id.btnGuardarDos -> guardarValor(etTextoDos.text.toString(), "valor2", etTextoDos)
            R.id.btnLeerUno -> etTextoUno.setText(leerValor("valor1"))
            R.id.btnLeerDos -> etTextoDos.setText(leerValor("valor2"))
        }
    }

    fun guardarValor(texto: String, nombre: String, et: EditText) {
        val editor = sharedPreferences!!.edit()
        editor.putString(nombre, texto)
        editor.commit()
        et.setText("")
    }

    fun leerValor(nombre: String): String {
        return sharedPreferences!!.getString(nombre, "")
    }
}
