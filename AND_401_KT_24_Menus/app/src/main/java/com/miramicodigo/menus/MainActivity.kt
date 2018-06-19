package com.miramicodigo.menus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var btnShowPopup: Button? = null
    private var lvDatos: ListView? = null
    private var datos: ArrayList<String>? = null
    private var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvDatos = findViewById<ListView>(R.id.lvDatos)
        btnShowPopup = findViewById<Button>(R.id.btnPopup)

        btnShowPopup!!.setOnClickListener(this)

        llenarDatos()
        adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, datos)
        lvDatos!!.adapter = adapter

        registerForContextMenu(lvDatos)
    }

    fun llenarDatos() {
        datos = ArrayList()
        for (i in 0..49) {
            datos!!.add("Elemento " + (i + 1) + " de la lista.")
        }
    }

    override fun onClick(view: View) {
        var menu = PopupMenu(applicationContext, btnShowPopup)
        menu.menuInflater.inflate(R.menu.main_menu_popup, menu.menu)
        menu.setOnMenuItemClickListener { menuItem ->
            when (menuItem!!.itemId) {
                R.id.menuPopupUno -> {
                    Toast.makeText(applicationContext, "Popup Uno",
                            Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }
                R.id.menuPopupDos -> {
                    Toast.makeText(applicationContext, "Popup Dos",
                            Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }
        menu.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu_opciones, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.menuUno -> {
                Toast.makeText(applicationContext, "Menu Uno",
                        Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menuDos -> {
                Toast.makeText(applicationContext, "Menu Dos",
                        Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menuTres -> {
                Toast.makeText(applicationContext, "Menu Tres",
                        Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menuCuatro -> {
                Toast.makeText(applicationContext, "Menu Cuatro",
                        Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menuCinco -> {
                Toast.makeText(applicationContext, "Menu Cinco",
                        Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.submenu1 -> {
                Toast.makeText(applicationContext, "Sub menu Uno",
                        Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.submenu2 -> {
                Toast.makeText(applicationContext, "Sub menu Dos",
                        Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu_contextual, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.menuContextUno -> {
                Toast.makeText(applicationContext, "Compartir",
                        Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menuContextDos -> {
                Toast.makeText(applicationContext, "Descargar",
                        Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menuContextTres -> {
                Toast.makeText(applicationContext, "Enviar a...",
                        Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }

}
