package com.miramicodigo.fragmentcomunicacion

import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), ComunicacionInterface{

    lateinit var fragUno : UnoFragment
    lateinit var fragDos : DosFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragUno = UnoFragment()
        fragDos = DosFragment()

        showFragment(fragUno, R.id.frameUno)
        showFragment(fragDos, R.id.frameDos)
    }

    fun showFragment(fragment: Fragment, id: Int) {
        val fragManager : FragmentManager = fragmentManager
        var fragTransaction : FragmentTransaction = fragManager.beginTransaction()
        fragTransaction.add(id, fragment).commit()
    }

    override fun enviarDatoADosFragment(cadena: String) {
        (fragDos as DosFragment).llegaDato(cadena)
    }

}
