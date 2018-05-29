package com.miramicodigo.recyclerviewcards

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.RecyclerView
import android.app.Activity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var datos: ArrayList<Pokemon>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<*>? = null
    private var activity: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        //layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        //layoutManager = GridLayoutManager(applicationContext, 2)
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        rvContenido!!.layoutManager = layoutManager

        activity = this
        llenarPokemones()
        adapter = RVAdapter(activity!!, datos!!)
        rvContenido!!.adapter = adapter
    }

    fun llenarPokemones() {
        datos = ArrayList()
        val resources = resources
        val arrayNombres = resources.getStringArray(R.array.nombre)
        val arrayTipos = resources.getStringArray(R.array.tipos)
        val arrayImgs = resources.obtainTypedArray(R.array.image)
        for (i in arrayNombres.indices) {
            val poke = Pokemon(
                    arrayNombres[i],
                    arrayTipos[i],
                    arrayImgs.getResourceId(i, -1)
            )
            datos!!.add(poke)
        }
    }

}
