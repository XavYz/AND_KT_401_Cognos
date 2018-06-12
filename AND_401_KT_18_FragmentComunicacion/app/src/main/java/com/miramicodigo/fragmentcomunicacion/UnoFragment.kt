package com.miramicodigo.fragmentcomunicacion


import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class UnoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view : View = inflater.inflate(R.layout.fragment_uno, container, false)

        val etDato : EditText = view.findViewById(R.id.etTexto)
        val btnEnviar : Button = view.findViewById(R.id.btnEnviar)

        btnEnviar.setOnClickListener {
            (activity as ComunicacionInterface).enviarDatoADosFragment(etDato.text.toString())
        }

        return view
    }


}
