package com.miramicodigo.listaspersonalizadas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Typeface
import kotlinx.android.synthetic.main.activity_detalle.*

class DetalleActivity : AppCompatActivity() {

    var tfBold: Typeface? = null
    var tfThin: Typeface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)


    }
}
