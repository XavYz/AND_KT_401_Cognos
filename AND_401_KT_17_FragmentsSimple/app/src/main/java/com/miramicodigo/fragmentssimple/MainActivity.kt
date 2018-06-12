package com.miramicodigo.fragmentssimple

<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
=======
import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{
>>>>>>> a5f3984f06776ec12ede3f7c09c996f5b1772dbc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
<<<<<<< HEAD
=======

        ibBolivia.setOnClickListener(this)
        ibParaguay.setOnClickListener(this)
        ibBrasil.setOnClickListener(this)

        showFragment(BoliviaFragment())
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            ibBolivia.id -> showFragment(BoliviaFragment())
            ibParaguay.id -> showFragment(ParaguayFragment())
            ibBrasil.id -> showFragment(BrasilFragment())
        }
    }

    fun showFragment(fragment: Fragment) {
        val fragManager: FragmentManager = fragmentManager
        var fragTransaction: FragmentTransaction = fragManager.beginTransaction()
        fragTransaction.replace(R.id.frameContent, fragment).commit()
>>>>>>> a5f3984f06776ec12ede3f7c09c996f5b1772dbc
    }
}
