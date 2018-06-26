package com.miramicodigo.pickersdatetime

import android.widget.DatePicker
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var datePicker: DatePickerDialog? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        datePicker = DatePickerDialog(activity, this, year, month, day)
        //datePicker!!.datePicker.minDate = minRangeDatePicker().timeInMillis
        //datePicker!!.datePicker.maxDate = maxRangeDatePicker().timeInMillis

        return datePicker as DatePickerDialog
    }

    override fun onDateSet(datePicker: DatePicker, anio: Int, mes: Int, dia: Int) {
        val mesActual = mes + 1
        val diaFormateado = if (dia < 10) "0" + dia.toString() else dia.toString()
        val mesFormateado = if (mesActual < 10) "0" + mesActual.toString() else mesActual.toString()
        val resultado = "$diaFormateado/$mesFormateado/$anio"

        (activity as DateTimeInterface).obtieneFecha(resultado)
    }

    fun maxRangeDatePicker(): Calendar {
        val aTime = "2018-02-25"
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        var cal: Calendar? = null
        try {
            cal = Calendar.getInstance()
            cal!!.time = sdf.parse(aTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return cal!!
    }

    fun minRangeDatePicker(): Calendar {
        val aTime = "2018-02-15"
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        var cal: Calendar? = null
        try {
            cal = Calendar.getInstance()
            cal!!.time = sdf.parse(aTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return cal!!
    }
}