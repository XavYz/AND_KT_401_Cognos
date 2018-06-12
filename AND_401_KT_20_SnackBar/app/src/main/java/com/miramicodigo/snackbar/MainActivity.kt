package com.miramicodigo.snackbar

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var coord : CoordinatorLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coord = findViewById(R.id.coordinatorLayout) as CoordinatorLayout

        btnSnackbarSimple.setOnClickListener {
            val snackbar: Snackbar = Snackbar.make(coord!!, "Mensaje en SnackBar", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }

        btnSnackbarConAccion.setOnClickListener {
            val snackbar: Snackbar = Snackbar.make(coord!!, "Snackbar con accion", Snackbar.LENGTH_SHORT)
            snackbar.setAction("Reintentar", View.OnClickListener {
                Toast.makeText(applicationContext, "Accion", Toast.LENGTH_SHORT).show()
            })
            snackbar.show()
        }

        btnSnackbarPersonalizado.setOnClickListener {
            val snackbar: Snackbar = Snackbar.make(coord!!, "SnackBar personalizado", Snackbar.LENGTH_SHORT)
            snackbar.setAction("Actualizar", View.OnClickListener {
                Toast.makeText(applicationContext, "Accion", Toast.LENGTH_SHORT).show()
            })
            snackbar.setActionTextColor(Color.RED)
            var view: View = snackbar.view
            var textView: TextView = view.findViewById(android.support.design.R.id.snackbar_text)
            textView.setTextColor(Color.BLUE)
            view.setBackgroundColor(Color.YELLOW)

            snackbar.show()
        }

    }
}
