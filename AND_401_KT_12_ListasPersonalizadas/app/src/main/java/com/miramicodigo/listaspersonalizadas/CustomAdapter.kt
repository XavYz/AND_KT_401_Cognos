package com.miramicodigo.listaspersonalizadas

import android.widget.TextView
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.BaseAdapter
import android.widget.ImageView
import java.util.*


class CustomAdapter(activity: Activity, data: ArrayList<Pokemon>) : BaseAdapter() {

    private val context: Context
    private val items: ArrayList<Pokemon>

    init {
        this.context = activity
        this.items = data
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val viewHolder: ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lista, parent, false)
            viewHolder = ViewHolder(convertView!!)
            convertView!!.setTag(viewHolder)
        } else {
            viewHolder = convertView!!.tag as ViewHolder
        }


        return convertView
    }

    private inner class ViewHolder(view: View) {



    }

}
