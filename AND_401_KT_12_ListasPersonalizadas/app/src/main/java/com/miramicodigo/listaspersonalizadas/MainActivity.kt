package com.miramicodigo.listaspersonalizadas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.AdapterView
import android.app.Activity
import android.view.View
import com.miramicodigo.listaspersonalizadas.R.id.lvLista
import kotlinx.android.synthetic.main.activity_main.lvLista
import java.util.*


class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    var activity: Activity? = null
    var adaptador: CustomAdapter? = null
    var datos: ArrayList<Pokemon>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun llenarPokemones() {

    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {

    }
}
