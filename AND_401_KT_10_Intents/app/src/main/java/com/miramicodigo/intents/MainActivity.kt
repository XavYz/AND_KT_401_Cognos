package com.miramicodigo.intents

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.app.Activity
import android.app.SearchManager
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener {
    val DEVUELVE_DATOS = 2
    val PERMISO_LLAMADA = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ibAbrirActivity.setOnClickListener{
            abrirActivity()
        }

        ibEnviarDatos.setOnClickListener{
            enviarDatos()
        }

        ibDevolverDatos.setOnClickListener{
            devolverDatos()
        }

        ibAbrirMarcado.setOnClickListener(this)
        ibLlamar.setOnClickListener(this)
        ibAbrirGoogleMaps.setOnClickListener(this)
        ibAbrirStreetView.setOnClickListener(this)
        ibAbrirPaginaWeb.setOnClickListener(this)
        ibAbrirBuscador.setOnClickListener(this)
        ibCompartirTexto.setOnClickListener(this)
        ibEnviarEmail.setOnClickListener(this)
        ibAbrirApp.setOnClickListener(this)
        ibAsignarWallpaper.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when(view!!.id) {
            ibAbrirMarcado.id -> abrirMarcado()
            ibLlamar.id -> llamar()
            ibAbrirGoogleMaps.id -> abrirGoogleMaps()
            ibAbrirStreetView.id -> abrirStreetView()
            ibAbrirPaginaWeb.id -> abrirPaginaWeb()
            ibAbrirBuscador.id -> abrirBuscador()
            ibCompartirTexto.id -> compartirTexto()
            ibEnviarEmail.id -> enviarEmail()
            ibAbrirApp.id -> abrirApp()
            ibAsignarWallpaper.id -> asignarWallpaper()
        }
    }

    fun abrirActivity() {
        val intent = Intent(this, SegundaActivity::class.java)
        startActivity(intent)
    }

    fun enviarDatos() {
        val intent = Intent(this, SegundaActivity::class.java)
        intent.putExtra("valor1", "Envio de Dato")
        intent.putExtra("valor2", "Hola Mundo")
        startActivity(intent)
    }

    fun devolverDatos() {
        val intent = Intent(this, SegundaActivity::class.java)
        intent.putExtra("valor3", "Mi nombre es ")
        startActivityForResult(intent, DEVUELVE_DATOS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DEVUELVE_DATOS) {
            if (resultCode == Activity.RESULT_OK) {
                val resultado = data.getStringExtra("respuesta").toString()
                Toast.makeText(this, "RESPUESTA: $resultado", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Se cancelo la respuesta", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun abrirMarcado() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:77752810")
        startActivity(intent)
    }

    fun llamar() {
        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CALL_PHONE), PERMISO_LLAMADA)
        } else {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:77752810")
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISO_LLAMADA -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                llamar()
            } else {
                Toast.makeText(this, "PERMISO DENEGADO", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun abrirGoogleMaps() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("geo:-16.508355,-68.126270")
        startActivity(intent)
    }

    fun abrirStreetView() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("google.streetview:cbll=-16.508355,-68.126270")
        startActivity(intent)
    }

    fun abrirPaginaWeb() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://www.google.com")
        startActivity(intent)
    }

    fun abrirBuscador() {
        val intent = Intent(Intent.ACTION_WEB_SEARCH)
        intent.putExtra(SearchManager.QUERY, "Android")
        startActivity(intent)
    }

    fun compartirTexto() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Hola a todos")
        startActivity(intent)
    }

    fun enviarEmail() {
        val TO = arrayOf("lizarraga.gux@gmail.com, gustavo@gmail.com")
        val CC = arrayOf("android@gmail.com")
        val asunto = "Correo importante"
        val contenido = "Este correo electronico es de prueba"

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.type = "text/plain"
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, TO)
        intent.putExtra(Intent.EXTRA_CC, CC)
        intent.putExtra(Intent.EXTRA_SUBJECT, asunto)
        intent.putExtra(Intent.EXTRA_TEXT, contenido)

        startActivity(Intent.createChooser(intent, "Enviar correo (demo)"))
    }

    fun abrirApp() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.setClassName("com.cognos.aplicacion5",
                "com.cognos.aplicacion5.MainActivity")
        startActivity(intent)
    }

    fun asignarWallpaper() {
        val intent = Intent(Intent.ACTION_SET_WALLPAPER)
        startActivity(Intent.createChooser(intent, "Cambiar fondo de pantalla"))
    }

}
