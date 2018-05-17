package com.miramicodigo.pedidos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var cantidad = 1
    var precio = 2.5
    var total: Double = 0.toDouble()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun menos(v: View) {
        if (cantidad > 1) {
            cantidad = cantidad - 1
        }
        tvCantidad.text = cantidad.toString()
    }

    fun mas(v: View) {
        cantidad = cantidad + 1
        tvCantidad.text = cantidad.toString()
    }

    fun calcular(v: View) {
        total = cantidad * precio
        val nombre = etNombre.text.toString()
        if (nombre == "") {
            Toast.makeText(this,"Debe colocar su apellido", Toast.LENGTH_SHORT).show()
        } else {
            tvResumenCantidad.text = cantidad.toString()
            tvResumenPrecioUnitario.text = "Bs. $precio"
            tvResumenCostoTotal.text = "Bs. $total"
            tvResumenNombre.text = nombre
        }
    }
}
