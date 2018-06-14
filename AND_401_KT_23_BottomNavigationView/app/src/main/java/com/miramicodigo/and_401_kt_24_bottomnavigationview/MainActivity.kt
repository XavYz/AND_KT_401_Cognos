package com.miramicodigo.and_401_kt_24_bottomnavigationview

import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var fm: FragmentManager
    lateinit var transaction: FragmentTransaction
    lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fm = fragmentManager
        transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frameContent, MenuUnoFragment()).commit()

        bottomNavigationView.setOnNavigationItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.menu_1 -> fragment = MenuUnoFragment()
                R.id.menu_2 -> fragment = MenuDosFragment()
                R.id.menu_3 -> fragment = MenuTresFragment()
            }
            transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.frameContent, fragment).commit()

            return@setOnNavigationItemSelectedListener true
        }
    }

}
