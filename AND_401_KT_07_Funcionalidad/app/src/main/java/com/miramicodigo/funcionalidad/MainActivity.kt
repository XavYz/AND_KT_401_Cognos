package com.miramicodigo.funcionalidad

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var num1 = etNumber1.text
        var num2 = etNumber2.text

        btnSuma.setOnClickListener {
            if(num1.toString().equals("") || num2.toString().equals("")) {
                tvResultado.text= "Campos vacios"
            } else {
                var sum_result= num1.toString().toDouble()+ num2.toString().toDouble()
                tvResultado.text= sum_result.toString()
            }
        }

        btnResta.setOnClickListener {
            if(num1.toString().equals("") || num2.toString().equals("")) {
                tvResultado.text = "Campos vacios"
            } else {
                var minus_result= num1.toString().toDouble() - num2.toString().toDouble()
                tvResultado.text= minus_result.toString()
            }
        }
        btnMultiplica.setOnClickListener {
            if(num1.toString().equals("") || num2.toString().equals("")) {
                tvResultado.text = "Campos vacios"
            }else{
                var multiply_result= num1.toString().toDouble() * num2.toString().toDouble()
                tvResultado.text= multiply_result.toString()
            }
        }

        btnDivide.setOnClickListener {
            if(num1.toString().equals("") || num2.toString().equals("")) {
                tvResultado.text = "Campos vacios"
            }else {
                if(num2.toString().toInt() == 0) {
                    tvResultado.text = "No se puede dividir entre 0"
                } else {
                    var division_result= num1.toString().toDouble() / num2.toString().toDouble()
                    tvResultado.text= division_result.toString()
                }
            }
        }

    }

}
