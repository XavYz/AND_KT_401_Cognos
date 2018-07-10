package com.miramicodigo.sqlite.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.AdapterView
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import com.miramicodigo.sqlite.R
import com.miramicodigo.sqlite.db.DatabaseAdapter
import com.miramicodigo.sqlite.adapter.ListaAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var adaptadorLista: ListaAdapter? = null
    private var db: DatabaseAdapter? = null
    private val ids = ArrayList<Long>()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "SQLite"

        lvLista!!.onItemClickListener = this
        adaptadorLista = ListaAdapter(this)
        lvLista!!.adapter = adaptadorLista
        registerForContextMenu(lvLista)

        db = DatabaseAdapter(this)
    }

    override fun onStart() {
        super.onStart()
        db!!.abrir()
        cargarDatosLista()
    }

    fun cargarDatosLista() {
        ids.clear()
        adaptadorLista!!.eliminarTodo()
        val cursor = db!!.obtenerTodasPersonas()
        if (cursor.moveToFirst()) {
            llContent.visibility = View.INVISIBLE
            do {
                val id = cursor.getInt(0)
                val nombre = cursor.getString(1)
                val correo = cursor.getString(3)
                val genero = cursor.getString(4)
                ids.add(id.toLong())
                if (genero.equals("m", ignoreCase = true)) {
                    adaptadorLista!!.adicionarItem(R.drawable.man, nombre, correo)
                } else {
                    adaptadorLista!!.adicionarItem(R.drawable.woman, nombre, correo)
                }
            } while (cursor.moveToNext())
        } else {
            llContent.visibility = View.VISIBLE
        }
        adaptadorLista!!.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_adicionar -> {
                val intent = Intent(this, FormularioActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.item_lista, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val index = info.position
        when (item.itemId) {
            R.id.menu_editar -> {
                val intent = Intent(this, FormularioActivity::class.java)
                intent.putExtra("id", ids[index])
                startActivity(intent)
            }
            R.id.menu_eliminar -> {
                db!!.eliminarPersona(ids[index])
                ids.removeAt(index)
                cargarDatosLista()
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onStop() {
        super.onStop()
        db!!.cerrar()
    }

    override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
        val intent = Intent(this, DetalleActivity::class.java)
        intent.putExtra("id", ids[i])
        startActivity(intent)
    }

}
