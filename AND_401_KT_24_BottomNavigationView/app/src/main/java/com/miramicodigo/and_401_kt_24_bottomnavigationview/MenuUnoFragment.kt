package com.miramicodigo.and_401_kt_24_bottomnavigationview


import android.os.Bundle
import android.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 *
 */
class MenuUnoFragment : Fragment() {

    lateinit var etCaja: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_menu_uno, container, false)

        etCaja = view.findViewById(R.id.etDato) as EditText

        if(savedInstanceState != null) {
            etCaja.setText(savedInstanceState.getString("valor", "").toString())
        }

        return view
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putString("valor", etCaja.text.toString())
    }

}
