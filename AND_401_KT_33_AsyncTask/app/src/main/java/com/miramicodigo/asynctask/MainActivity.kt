package com.miramicodigo.asynctask

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.AsyncTask
import android.view.View
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CallBtn.setOnClickListener {
            AsyncTaskExample().execute()
        }
    }

    inner class AsyncTaskExample : AsyncTask<String, String, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.visibility = View.VISIBLE;
        }

        override fun doInBackground(vararg p0: String?): String {
            var result: String = "";
            val apiUrl = "http://androidpala.com/tutorial/http.php?get=1"
            try {
                val url = URL(apiUrl)
                val connect = url.openConnection() as HttpURLConnection
                connect.readTimeout = 8000
                connect.connectTimeout = 8000
                connect.requestMethod = "GET"
                connect.doOutput = true
                connect.connect()

                val responseCode: Int = connect.responseCode
                println("Res: $responseCode")
                if (responseCode == 200) {
                    val tempStream: InputStream = connect.inputStream
                    if (tempStream != null) {
                        result = ConvertToString(tempStream)
                    }
                }
            } catch(Ex: Exception) {
                println("Error en doInBackground ${Ex.message}")
            }
            return result

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            progressBar.visibility = View.INVISIBLE

            if (result == "") {
                tvResultado.text = "Error de red"
            } else {
                tvResultado.text = result
            }
        }
    }

    fun ConvertToString(inStream: InputStream): String {
        var result: String = ""
        val isReader = InputStreamReader(inStream)
        var bReader = BufferedReader(isReader)
        var tempStr: String?

        try {
            while (true) {
                tempStr = bReader.readLine()
                if (tempStr == null) {
                    break
                }
                result += tempStr;
            }
        } catch(Ex: Exception) {
            println("Error en convertir a String ${Ex.printStackTrace()}")
        }
        return result
    }

}
