package com.miramicodigo.geolocalizacion

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.AsyncTask
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import android.support.v4.content.ContextCompat
import android.content.Intent
import android.content.Context
import android.content.DialogInterface
import android.location.*
import android.os.Handler
import android.os.Message
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.view.View
import java.io.IOException
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var locationManager: LocationManager? = null

    private var handler: Handler? = null

    private var disponibleGeocoder: Boolean = false
    private var usarFino: Boolean = false
    private var usarAmbos: Boolean = false

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            usarFino = savedInstanceState.getBoolean(CLAVE_FINO)
            usarAmbos = savedInstanceState.getBoolean(CLAVE_AMBOS)
        } else {
            usarFino = false
            usarAmbos = false
        }

        disponibleGeocoder = true

        handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    ACTUALIZAR_DIRECCION -> textoDireccion!!.text = msg.obj as CharSequence?
                    ACTUALIZAR_LATITUD -> textoLatitud!!.text = msg.obj as CharSequence?
                    ACTUALIZAR_LONGITUD -> textoLongitud!!.text = msg.obj as CharSequence?
                }
            }
        }
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(CLAVE_FINO, usarFino)
        outState.putBoolean(CLAVE_AMBOS, usarAmbos)
    }

    fun abrirConfiguracionesUbicacionYSeguridad() {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        val locationManager2 = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val gpsHabilitado = locationManager2.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (!gpsHabilitado) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Quieres activar el GPS?")
                    .setCancelable(false)
                    .setPositiveButton("Si",
                            DialogInterface.OnClickListener { dialog, id -> abrirConfiguracionesUbicacionYSeguridad() })
                    .setNegativeButton("No",
                            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
            val alerta = builder.create()
            alerta.show()
        }
    }

    override fun onResume() {
        super.onResume()
        configurar()
    }

    override fun onStop() {
        super.onStop()
        locationManager!!.removeUpdates(escuchador)
    }

    private val escuchador = object : LocationListener {

        override fun onLocationChanged(location: Location) {
            actualizarIU(location)
        }

        override fun onProviderDisabled(provider: String) {

        }

        override fun onProviderEnabled(provider: String) {

        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

        }
    }

    fun configurar() {
        var localizacionGPS: Location? = null
        var localizacionRED: Location? = null
        locationManager!!.removeUpdates(escuchador)
        textoLatitud!!.setText(R.string.desconocido)
        textoLongitud!!.setText(R.string.desconocido)
        textoDireccion!!.setText(R.string.desconocido)

        if (usarFino) {
            botonFino!!.setBackgroundResource(R.drawable.degradado_naranja)
            botonAmbos!!.setBackgroundResource(R.drawable.degradado_celeste)
            // Obtener la localizacion desde el proveedor GPS

            localizacionGPS = pedirActualizacionesDeProveedor(LocationManager.GPS_PROVIDER, R.string.gps_no_soportado)
            if (localizacionGPS != null) {
                actualizarIU(localizacionGPS!!)
            }

        } else if (usarAmbos) {
            botonFino!!.setBackgroundResource(R.drawable.degradado_celeste)
            botonAmbos!!.setBackgroundResource(R.drawable.degradado_naranja)
            // Obtener la localizacion de ambos proveedores
            localizacionGPS = pedirActualizacionesDeProveedor(LocationManager.GPS_PROVIDER, R.string.gps_no_soportado)
            localizacionRED = pedirActualizacionesDeProveedor(LocationManager.NETWORK_PROVIDER, R.string.red_no_soportado)

            if (localizacionGPS != null && localizacionRED != null) {
                actualizarIU(getMejorLocalizacion(localizacionGPS, localizacionRED))
            } else {
                if (localizacionGPS != null) {
                    actualizarIU(localizacionGPS!!)
                } else {
                    if (localizacionRED != null) {
                        actualizarIU(localizacionRED!!)
                    }
                }
            }
        }
    }

    private fun pedirActualizacionesDeProveedor(proveedor: String, mensajeError: Int): Location? {
        var localizacion: Location? = null
        if (locationManager!!.isProviderEnabled(proveedor)) {
            val DIEZ_SEGUNDOS = 10000
            val DIEZ_METROS = 10

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager!!.requestLocationUpdates(proveedor, DIEZ_SEGUNDOS.toLong(), DIEZ_METROS.toFloat(), escuchador)
                localizacion = locationManager!!.getLastKnownLocation(proveedor)
            } else {
                solicitarPermiso()
            }
        } else {
            Toast.makeText(this, mensajeError, Toast.LENGTH_LONG).show()
        }
        return localizacion
    }

    fun solicitarPermiso() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 1) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }

    fun usarProveedorFino(view: View) {
        usarFino = true
        usarAmbos = false
        configurar()
    }

    fun usarAmbosProveedores(view: View) {
        usarFino = false
        usarAmbos = true
        configurar()
    }

    private fun hacerGeocodificacionReversa(location: Location) {
        TareaGeocodificacionReversa(this).execute(*arrayOf<Location>(location))
    }

    private fun actualizarIU(localizacion: Location) {
        Message.obtain(handler, ACTUALIZAR_LATITUD,
                localizacion.latitude.toString()).sendToTarget()
        Message.obtain(handler, ACTUALIZAR_LONGITUD,
                localizacion.longitude.toString()).sendToTarget()

        if (disponibleGeocoder) {
            hacerGeocodificacionReversa(localizacion)
        }
    }

    protected fun getMejorLocalizacion(newLocation: Location,
                                       currentBestLocation: Location?): Location {
        val TWO_MINUTES = 1000 * 60 * 2
        if (currentBestLocation == null) {
            return newLocation
        }

        val timeDelta = newLocation.time - currentBestLocation!!.time
        val isSignificantlyNewer = timeDelta > TWO_MINUTES
        val isSignificantlyOlder = timeDelta < -TWO_MINUTES
        val isNewer = timeDelta > 0

        if (isSignificantlyNewer) {
            return newLocation
        } else if (isSignificantlyOlder) {
            return currentBestLocation
        }

        val accuracyDelta = (newLocation.accuracy - currentBestLocation!!.accuracy) as Int
        val isLessAccurate = accuracyDelta > 0
        val isMoreAccurate = accuracyDelta < 0
        val isSignificantlyLessAccurate = accuracyDelta > 200

        val isFromSameProvider = esMismoProveedor(
                newLocation.provider, currentBestLocation!!.provider)

        if (isMoreAccurate) {
            return newLocation
        } else if (isNewer && !isLessAccurate) {
            return newLocation
        } else if (isNewer && !isSignificantlyLessAccurate
                && isFromSameProvider) {
            return newLocation
        }
        return currentBestLocation
    }

    private fun esMismoProveedor(provider1: String?, provider2: String?): Boolean {
        return if (provider1 == null) {
            provider2 == null
        } else provider1 == provider2
    }

    private inner class TareaGeocodificacionReversa(internal var mContext: Context) : AsyncTask<Location, Void, Void>() {

        override fun doInBackground(vararg params: Location): Void? {
            val geocoder = Geocoder(mContext, Locale.getDefault())

            val loc = params[0]
            var addresses: List<Address>? = null
            try {
                addresses = geocoder.getFromLocation(loc.latitude,
                        loc.longitude, 1)
            } catch (e: IOException) {
                e.printStackTrace()
                Message.obtain(handler, ACTUALIZAR_DIRECCION, e.toString())
                        .sendToTarget()
            }

            if (addresses != null && addresses.isNotEmpty()) {
                val address = addresses[0]
                val addressText = String.format(
                        "%s, %s, %s", if (address.maxAddressLineIndex > 0) address.getAddressLine(0) else "", address.locality,
                        address.countryName)
                Message.obtain(handler, ACTUALIZAR_DIRECCION, addressText).sendToTarget()
            }
            return null
        }
    }

    companion object {

        private val CLAVE_FINO = "usar_fino"
        private val CLAVE_AMBOS = "usar_ambos"

        private val ACTUALIZAR_DIRECCION = 1
        private val ACTUALIZAR_LATITUD = 2
        private val ACTUALIZAR_LONGITUD = 3
    }
}
