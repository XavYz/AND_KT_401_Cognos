package com.miramicodigo.restful_2.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.miramicodigo.restful_2.R
import com.miramicodigo.restful_2.SessionPrefs
import com.miramicodigo.restful_2.model.ApiError
import com.miramicodigo.restful_2.model.LoginBody
import com.miramicodigo.restful_2.model.Persona
import com.miramicodigo.restful_2.service.PersonaService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var retrofit: Retrofit? = null
    private var personaService: PersonaService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)





        btnSignIn.setOnClickListener {
            attemptLogin()
        }
    }

    private fun attemptLogin() {
        tilUsuario!!.error = null
        tilPassword!!.error = null

        val userId = etUsuario!!.text.toString()
        val password = etPassword!!.text.toString()

        var cancel = false
        var focusView: View? = null

        if (TextUtils.isEmpty(password)) {
            tilPassword!!.error = getString(R.string.error_field_required)
            focusView = tilPassword
            cancel = true
        } else if (!isPasswordValid(password)) {
            tilPassword!!.error = getString(R.string.error_invalid_password)
            focusView = tilPassword
            cancel = true
        }

        if (TextUtils.isEmpty(userId)) {
            tilUsuario!!.error = getString(R.string.error_field_required)
            focusView = tilUsuario
            cancel = true
        } else if (!isUserIdValid(userId)) {
            tilUsuario!!.error = getString(R.string.error_invalid_user_id)
            focusView = tilUsuario
            cancel = true
        }

        if (cancel) {
            focusView!!.requestFocus()
        } else {



        }
    }

    private fun isUserIdValid(userId: String): Boolean {
        return userId.length >= 3
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }

    private fun showProgress(show: Boolean) {
        pbProgreso!!.visibility = if (show) View.VISIBLE else View.GONE
        val visibility = if (show) View.GONE else View.VISIBLE
    }

    private fun showAppointmentsScreen(persona: Persona?) {



    }

    private fun showLoginError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

}
