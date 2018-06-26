package com.miramicodigo.pickersdatetime

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , DateTimeInterface{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ibFecha.setOnClickListener {
            showDatePickerDialog()
        }

        ibHora.setOnClickListener {
            showTimePickerDialog()
        }
    }

    fun showDatePickerDialog() {
        val dialogFragment = DatePickerFragment()
        dialogFragment.show(fragmentManager, "datePicker")
    }

    fun showTimePickerDialog() {
        val dialogFragment = TimePickerFragment()
        dialogFragment.show(fragmentManager, "timePicker")
    }

    override fun obtieneFecha(date: String) {
        etFecha.setText(date)
    }

    override fun obtieneHora(hour: String) {
        etHora.setText(hour)
    }

}
