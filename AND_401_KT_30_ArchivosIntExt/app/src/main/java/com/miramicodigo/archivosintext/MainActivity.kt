package com.miramicodigo.archivosintext

import android.Manifest
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.os.Environment
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.util.Log

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val nombreArchivoInterno = "prueba_archivo_int.txt"
    val nombreArchivoExterno = "prueba_archivo_ext.txt"
    val nombreCarpeta = "/COGNOS/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnInternoLeer.setOnClickListener(this)
        btnInternoGuardar.setOnClickListener(this)
        btnExternoLeer.setOnClickListener(this)
        btnExternoGuardar.setOnClickListener(this)

        verificaPermiso()
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.btnInternoGuardar -> guardarInterno()
            R.id.btnInternoLeer -> leerInterno()
            R.id.btnExternoGuardar -> guardarExterno()
            R.id.btnExternoLeer -> leerExterno()
        }
    }

    fun guardarInterno() {
        if (etInterno.text.toString() != "") {
            try {
                val output = OutputStreamWriter(openFileOutput(nombreArchivoInterno, Context.MODE_PRIVATE))
                output.write(etInterno.text.toString())
                output.close()
                etInterno.setText("")
                Toast.makeText(applicationContext, "Se guardo exitosamente", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                println("Error: " + e.message)
            }
        } else {
            Toast.makeText(applicationContext, "Debe ingresar datos para guardar", Toast.LENGTH_SHORT).show()
        }
    }

    fun leerInterno() {
        try {
            val directory = this.filesDir
            val file = File(directory, nombreArchivoInterno)
            val bufferedReader = file.bufferedReader()
            val text:List<String> = bufferedReader.readLines()
            var resultado = ""
            for(line in text){
                resultado = resultado + line + "\n"
            }
            etInterno.setText(resultado)
        } catch (e: Exception) {
            println("Error: " + e.message)
        }
    }

    fun guardarExterno() {
        if (!etExterno.text.toString().equals("")) {
            var sdDisponible = false
            var sdAccesoEscritura = false
            val state = Environment.getExternalStorageState()
            if (Environment.MEDIA_MOUNTED == state) {
                sdDisponible = true
                sdAccesoEscritura = true
            } else {
                if (Environment.MEDIA_MOUNTED_READ_ONLY == state) {
                    sdDisponible = true
                    sdAccesoEscritura = false
                } else {
                    sdDisponible = false
                    sdAccesoEscritura = false
                }
            }
            if (sdDisponible && sdAccesoEscritura) {
                try {
                    val tarjeta = Environment.getExternalStorageDirectory()
                    val dir = File(tarjeta.toString() + nombreCarpeta)
                    if (!dir.exists()) {
                        dir.mkdirs()
                    }
                    val file = File(dir, nombreArchivoExterno)
                    val osw = OutputStreamWriter(FileOutputStream(file))
                    osw.write(etExterno.text.toString())
                    osw.flush()
                    osw.close()
                    Toast.makeText(this, "Se guardo exitosamente", Toast.LENGTH_SHORT).show()
                    etExterno.setText("")
                } catch (ioe: IOException) {
                    Toast.makeText(this, "No se pudo grabar", Toast.LENGTH_SHORT).show()
                }

            } else {
                println("No se puede escribir en su memoria")
            }
        } else {
            Toast.makeText(applicationContext,
                    "Debe ingresar datos para guardar",
                    Toast.LENGTH_SHORT).show()
        }

    }

    fun leerExterno() {
        try {
            val directory = Environment.getExternalStorageDirectory()
            val file = File(directory, nombreCarpeta+nombreArchivoExterno)
            val bufferedReader = file.bufferedReader()
            val text:List<String> = bufferedReader.readLines()
            var resultado = ""
            for(line in text){
                resultado = resultado + line + "\n"
            }
            etExterno.setText(resultado)
        } catch (e: Exception) {
            println("Error: " + e.message)
        }
    }

    fun verificaPermiso() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permiso", "Concedido")
                } else {
                    Log.e("Permiso", "Denegado")
                }
                return
            }
        }
    }

}
