package com.miramicodigo.dialogos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.support.v7.app.AlertDialog
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.EditText
import android.widget.CheckBox

class MainActivity : AppCompatActivity() {

    private var btnCrear: Button? = null
    private var btnEntrar: Button? = null
    private var etNombre: EditText? = null
    private var etContrasenia: EditText? = null
    private var posicion: Int = 0
    private var res: String? = null
    private var cbRecordar: CheckBox? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDialogoSimple.setOnClickListener{
            crearDialogoSimple().show()
        }

        btnDialogoConLista.setOnClickListener {
            crearDialogoConLista().show()
        }

        btnDialogoConListaRadio.setOnClickListener {
            crearDialogoConListaRadio().show()
        }

        btnDialogoConListaCheckbox.setOnClickListener {
            crearDialogoCheckBox().show()
        }

        btnDialogoPersonalizado.setOnClickListener {
            crearDialogoPersonalizado()
        }

    }

    fun crearDialogoSimple(): AlertDialog {
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.setTitle("Mi Dialogo")
                .setMessage("Este es el contenido de mi dialogo")
                .setCancelable(false)
                .setPositiveButton("Aceptar") {dialogInterface, i ->
                    Toast.makeText(applicationContext, "Aceptar",
                            Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancelar") {dialogInterface, i ->
                    Toast.makeText(applicationContext, "Negative",
                            Toast.LENGTH_SHORT).show()
                }
                .setNeutralButton("Cerrar"){dialogInterface, i ->
                    Toast.makeText(applicationContext, "Neutral",
                            Toast.LENGTH_SHORT).show()
                }
        return alertDialog.create()
    }


    fun crearDialogoConLista(): AlertDialog {
        val alertDialog = AlertDialog.Builder(this)
        val opciones = arrayOf("Opcion 1", "Opcion 2", "Opcion 3", "Opcion 4", "Opcion 5", "Opcion 6", "Opcion 7", "Opcion 8")
        alertDialog.setTitle("Elija una opcion")
                .setCancelable(false)
                .setItems(opciones){ dialogInterface, i ->
                    Toast.makeText(applicationContext,
                            "Hizo click en: " + opciones[i], Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancelar"){ dialogInterface, i ->
                    Toast.makeText(applicationContext,
                            "Negative", Toast.LENGTH_SHORT).show()
                }
        return alertDialog.create()
    }

    fun crearDialogoConListaRadio(): AlertDialog {
        val alertDialog = AlertDialog.Builder(this)
        val opciones = arrayOf("Opcion 1", "Opcion 2", "Opcion 3", "Opcion 4")
        posicion = 0
        alertDialog.setTitle("Seleccione una opcion")
                .setSingleChoiceItems(opciones, 0) { dialogInterface, i -> posicion = i }
                .setPositiveButton("ACEPTAR"){ dialogInterface, i ->
                    Toast.makeText(applicationContext,
                            "Seleccionaste: ${opciones.get(posicion)}", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("CANCELAR") { dialogInterface, i ->

                }
        return alertDialog.create()
    }

    fun crearDialogoCheckBox(): AlertDialog {
        val alertDialog = AlertDialog.Builder(this)
        val opciones = arrayOf("Desarrollo Android", "Desarrollo iOS", "Back-end", "Front-end")
        val itemsSeleccionados = ArrayList<Int>()

        alertDialog.setTitle("Seleccione sus Skills")
                .setMultiChoiceItems(opciones, null) { dialogInterface, i, b ->
                    if (b) {
                        itemsSeleccionados.add(i)
                    } else {
                        if (itemsSeleccionados.contains(i)) {
                            itemsSeleccionados.remove(Integer.valueOf(i))
                        }
                    }
                }
                .setPositiveButton("Aceptar"){ dialogInterface, i ->
                    var res = ""
                    for (j in 0 until itemsSeleccionados.size) {
                        res = res + "\n- " + opciones[itemsSeleccionados[j]]
                    }
                    Toast.makeText(applicationContext, res, Toast.LENGTH_LONG).show()
                }
        return alertDialog.create()
    }

    fun crearDialogoPersonalizado(): AlertDialog {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setCancelable(false)
        val inflater = layoutInflater
        val v = inflater.inflate(R.layout.custom_dialog, null)
        alertDialog.setView(v)

        btnCrear = v.findViewById(R.id.btnCrear)
        btnEntrar = v.findViewById(R.id.btnEntrar)
        etNombre = v.findViewById(R.id.etNombre)
        etContrasenia = v.findViewById(R.id.etContrasenia)
        cbRecordar = v.findViewById(R.id.cbRecordar)

        val dialog = alertDialog.show()

        btnCrear!!.setOnClickListener {
            dialog.dismiss()
        }

        res = ""

        btnEntrar!!.setOnClickListener{
            res = "Nombre: " + etNombre!!.text.toString() +
                        "\nRecordar: " + cbRecordar!!.isChecked
                Toast.makeText(applicationContext, res, Toast.LENGTH_SHORT).show()

        }

        return alertDialog.create()
    }
}
