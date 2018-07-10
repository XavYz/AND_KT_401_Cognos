package com.miramicodigo.asynctask

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.AsyncTask
import android.widget.Toast
import android.app.ProgressDialog
import android.view.View
import android.widget.TextView
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private var dialog: ProgressDialog? = null
    private var textView: TextView? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById<TextView>(R.id.tvResultado)
        dialog = ProgressDialog(this)
    }

    fun onClick(view: View) {
        val task = DownloadWebPageTask()
        task.execute(*arrayOf("http://www.instagram.com", "http://www.google.com", "http://www.bolivia.com"))
    }

    fun toast(v: View) {
        Toast.makeText(
                applicationContext,
                "Muestra Toast",
                Toast.LENGTH_SHORT).show()
    }

    private inner class DownloadWebPageTask : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            dialog!!.setMessage("Progreso iniciado")
            dialog!!.setCancelable(false)
            dialog!!.show()
        }

        override fun doInBackground(vararg strings: String): String {
            var response = ""
            for (url in strings) {
                val connection = URL(url).openConnection() as HttpURLConnection
                try {
                    val data = connection.inputStream.bufferedReader().readText()
                    response += data
                } finally {
                    connection.disconnect()
                }

            }
            return response
        }

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
            if (dialog!!.isShowing) {
                dialog!!.dismiss()
            }
            textView!!.text = s
        }
    }

}
