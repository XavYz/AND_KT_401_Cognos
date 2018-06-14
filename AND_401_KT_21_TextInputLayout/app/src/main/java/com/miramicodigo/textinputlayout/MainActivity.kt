package com.miramicodigo.textinputlayout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        errorEditText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.length > errorInputLayout!!.counterMaxLength)
                    errorInputLayout!!.error = "Caracteres maximo es " + errorInputLayout!!.counterMaxLength
                else
                    errorInputLayout!!.error = null
            }
        })

        customErrorEditText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.length > customErrorInputLayout!!.counterMaxLength)
                    customErrorInputLayout!!.error = "Caracteres maximo es " + customErrorInputLayout!!.counterMaxLength
                else
                    customErrorInputLayout!!.error = null
            }
        })
    }

}
