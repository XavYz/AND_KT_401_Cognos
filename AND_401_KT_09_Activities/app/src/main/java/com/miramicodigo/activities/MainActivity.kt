package com.miramicodigo.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnIrASegunda.setOnClickListener {
            //Intent intent = new Intent(this, SegundaActivity.class);
            val intent = Intent(this, SegundaActivity::class.java)
            startActivity(intent)
        }

        btnIrATercera.setOnClickListener {
            val intent = Intent(this, TerceraActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        print("Entro a metodo onStart")
    }

    override fun onResume() {
        super.onResume()
        print("Entro a metodo OnResume")

    }

    override fun onPause() {
        super.onPause()
        print("Entro a metodo OnPause")

    }

    override fun onStop() {
        super.onStop()
        print("Entro a metodo OnStop")

    }

    override fun onRestart() {
        super.onRestart()
        print("Entro a metodo OnRestart")

    }

    override fun onDestroy() {
        super.onDestroy()
        print("Entro a metodo OnDestroy")

    }
}
