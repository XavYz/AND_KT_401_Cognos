package com.miramicodigo.sqlite.adapter

import android.content.Context
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.BaseAdapter
import android.widget.ImageView
import com.miramicodigo.sqlite.R

class ListaAdapter(contexto: Context) : BaseAdapter() {

    val inflater: LayoutInflater = LayoutInflater.from(contexto)
    val imagenes: ArrayList<Int> = ArrayList()
    val textosPrincipales: ArrayList<String> = ArrayList()
    val textosSecundarios: ArrayList<String> = ArrayList()

    override fun getCount(): Int {
        return textosPrincipales.size
    }

    override fun getItem(i: Int): Any {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        var view = view
        val holder: ViewHolder
        if (view == null) {
            view = inflater.inflate(R.layout.item_lista, null)
            holder = ViewHolder()
            holder.ivImagen = view!!.findViewById(R.id.ivImageItem) as ImageView
            holder.tvTitulo = view!!.findViewById(R.id.tvPrincipalItem)
            holder.tvSubtitulo = view!!.findViewById(R.id.tvSecundarioItem)
            view!!.setTag(holder)
        } else {
            holder = view!!.tag as ViewHolder
        }
        holder.ivImagen!!.setImageResource(imagenes[i])
        holder.tvTitulo!!.text = textosPrincipales[i]
        holder.tvSubtitulo!!.text = textosSecundarios[i]
        return view
    }


    internal class ViewHolder {
        var ivImagen: ImageView? = null
        var tvTitulo: TextView? = null
        var tvSubtitulo: TextView? = null
    }

    fun adicionarItem(idRecurso: Int, textoPrincipal: String, textoSecundario: String) {
        imagenes.add(idRecurso)
        textosPrincipales.add(textoPrincipal)
        textosSecundarios.add(textoSecundario)
        notifyDataSetChanged()
    }

    fun eliminarTodo() {
        imagenes.clear()
        textosPrincipales.clear()
        textosSecundarios.clear()
        notifyDataSetChanged()
    }
}