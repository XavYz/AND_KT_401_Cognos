package com.miramicodigo.collapsingtoolbarlayout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        collapsingTL.title = "¡Oh Linda La Paz!"

        fab.setOnClickListener{
            Toast.makeText(applicationContext, "Click en FAB", Toast.LENGTH_SHORT).show()
        }

    }
}
