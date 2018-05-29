package com.miramicodigo.recyclerviewcards

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Typeface
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_detalle.*

class DetalleActivity : AppCompatActivity() {

    private var tf_black: Typeface? = null
    private var tf_thin: Typeface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        tf_black = Typeface.createFromAsset(assets, "fonts/roboto_black.ttf")
        tf_thin = Typeface.createFromAsset(assets, "fonts/roboto_thin.ttf")

        tvDetalleTitulo!!.typeface = tf_black
        tvDetalleSubtitulo!!.typeface = tf_thin

        val pokemon = intent.getSerializableExtra("poke") as Pokemon
        tvDetalleTitulo!!.text = pokemon.nombre
        tvDetalleSubtitulo!!.text = pokemon.tipo
        ivDetalleImagen!!.setImageResource(pokemon.imagen)
    }
}
