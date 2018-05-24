package com.miramicodigo.listaspersonalizadas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Typeface
import kotlinx.android.synthetic.main.activity_detalle.*
import java.lang.reflect.Type

class DetalleActivity : AppCompatActivity() {

    var tfBold: Typeface? = null
    var tfThin: Typeface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val pokemon = intent.getSerializableExtra("poke") as Pokemon

        tfBold = Typeface.createFromAsset(assets, "fonts/roboto_black.ttf")
        tfThin = Typeface.createFromAsset(assets, "fonts/roboto_thin.ttf")

        tvDetalleNombre.typeface = tfBold
        tvDetalleHabilidades.typeface = tfThin

        tvDetalleNombre.text = pokemon.titulo
        tvDetalleHabilidades.text = pokemon.subtitulo
        ivDetalleImagen.setImageResource(pokemon.imagen)
    }
}
