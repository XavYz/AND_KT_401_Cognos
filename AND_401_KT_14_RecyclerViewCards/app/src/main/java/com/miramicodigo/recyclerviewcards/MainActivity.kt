package com.miramicodigo.recyclerviewcards

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.RecyclerView
import android.app.Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var datos: ArrayList<Pokemon>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<*>? = null
    private var activity: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity = this

        llenarPokemones()

        //layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //layoutManager = GridLayoutManager(this,2)
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        rvContenido!!.layoutManager = layoutManager

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
