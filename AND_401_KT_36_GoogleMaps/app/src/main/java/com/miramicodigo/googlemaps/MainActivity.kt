package com.miramicodigo.googlemaps

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.graphics.Bitmap
import android.graphics.drawable.VectorDrawable
import android.os.Build
import android.content.pm.PackageManager
import android.annotation.SuppressLint
import android.graphics.Canvas
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.BitmapDescriptor



class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap

    private var mark: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {

        //Crear Polylines
        /*val sudamericaRect = PolylineOptions()
                .add(LatLng(-9.750976, -69.751063))
                .add(LatLng(-9.781599, -57.942932))
                .add(LatLng(-22.955286, -57.974006))
                .add(LatLng(-22.869419, -69.782137))
                .add(LatLng(-9.750976, -69.751063))
                .color(Color.parseColor("#f44336"))
        val polyline = googleMap.addPolyline(sudamericaRect)
        val zoom = CameraUpdateFactory.zoomTo(5f)
        val centroCamara = CameraUpdateFactory.newLatLng(LatLng(-17.051426, -64.348219))
        googleMap.moveCamera(centroCamara)
        googleMap.animateCamera(zoom)*/



        //Crear Polygonos
        /*val p1 = LatLng(-10.922041, -69.628187)
        val p2 = LatLng(-9.733147, -65.255628)
        val p3 = LatLng(-11.891268, -64.948011)
        val p4 = LatLng(-13.797803, -60.399671)
        val p5 = LatLng(-16.259237, -60.157972)
        val p6 = LatLng(-16.259237, -58.290296)
        val p7 = LatLng(-18.210825, -57.477308)
        val p8 = LatLng(-19.955016, -58.004652)
        val p9 = LatLng(-19.748347, -61.674085)
        val p10 = LatLng(-22.311709, -62.574964)
        val p11 = LatLng(-23.263802, -64.310804)
        val p12 = LatLng(-22.148994, -64.794202)
        val p13 = LatLng(-22.818969, -67.782483)
        val p14 = LatLng(-17.248101, -69.650159)
        val p15 = LatLng(-12.539434, -68.686912)
        googleMap.addPolygon(PolygonOptions()
                .add(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p1)
                .strokeColor(Color.parseColor("#AB47BC"))
                .fillColor(Color.parseColor("#7B1FA2")))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(-16.167173, -64.530626), 5f))*/



        //Crear Circulos.
        /*var centroCirculo = LatLng(-16.508335, -68.126259)
        val radius = 20
        val opcionesCirculo = CircleOptions()
                .center(centroCirculo)
                .radius(radius.toDouble())
                .strokeColor(Color.parseColor("#0D47A1"))
                .fillColor(Color.argb(50, 33, 200, 243))
                .strokeWidth(4f)
        googleMap.addCircle(opcionesCirculo)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centroCirculo, 18f))*/



        //Manejar el Zoom de la camara
        /*val centroCamara = CameraUpdateFactory.newLatLng(LatLng(-16.508335, -68.126259))
        val zoom = CameraUpdateFactory.zoomTo(15f)
        googleMap.moveCamera(centroCamara)
        googleMap.animateCamera(zoom)*/



        //Tipos de mapa
        /*val centroMapa = LatLng(-16.508335, -68.126259)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(centroMapa))
        googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE*/



        //Marcadores y evento click
        /*val centroMarcador = LatLng(-16.508335, -68.126259)
        mark = googleMap.addMarker(MarkerOptions()
                .position(centroMarcador)
                .title("Cognos")
        )
        val zoom = CameraUpdateFactory.zoomTo(15f)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(centroMarcador))
        googleMap.animateCamera(zoom)
        googleMap.setOnMarkerClickListener(this)*/



        //Hacer un marcador arrastrable
        /*val centroMarcadorArrastrable = LatLng(-16.508335, -68.126259)
        googleMap.addMarker(MarkerOptions()
                .position(centroMarcadorArrastrable)
                .title("Cognos")
                .draggable(true))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(centroMarcadorArrastrable))
        val zoom = CameraUpdateFactory.zoomTo(15f)
        googleMap.animateCamera(zoom)*/



        // Asignar una imagen de marcador
        /*val centroImagenMarcador = LatLng(-16.508335, -68.126259)
        googleMap.addMarker(MarkerOptions()
                .position(centroImagenMarcador)
                .title("Marcador con icono personalizado")
                .icon(getBitmapDescriptor(R.drawable.ic_android_black_24dp)))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(centroImagenMarcador))*/



        // Cambiar color marcador
        /*val centroMarcadorColor = LatLng(-16.508335, -68.126259)
        googleMap.addMarker(MarkerOptions()
                .position(centroMarcadorColor)
                .title("Marcador CYAN")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(centroMarcadorColor))*/



        //Habilitar botones de ubicacion y zoom
        /*mMap = googleMap
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            }
        }
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.addMarker(MarkerOptions().position(LatLng(-16.508335, -68.126259)))*/



        //Bloquear los gestos en el mapa
        /*val configuracionUI = googleMap.uiSettings
        configuracionUI.isScrollGesturesEnabled = false
        configuracionUI.isTiltGesturesEnabled = false
        configuracionUI.isRotateGesturesEnabled = false
        configuracionUI.isZoomGesturesEnabled = false
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-16.508335, -68.126259), 15f))*/

    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private fun getBitmapDescriptor(id: Int): BitmapDescriptor {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val vectorDrawable = getDrawable(id) as VectorDrawable

            val h = vectorDrawable.intrinsicHeight
            val w = vectorDrawable.intrinsicWidth

            vectorDrawable.setBounds(0, 0, w, h)

            val bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bm)
            vectorDrawable.draw(canvas)

            return BitmapDescriptorFactory.fromBitmap(bm)

        } else {
            return BitmapDescriptorFactory.fromResource(id)
        }
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 1) {
            if (permissions.isNotEmpty() && permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap!!.isMyLocationEnabled = true
            } else {
                Toast.makeText(this, "Error de permisos", Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        if (marker == mark) {
            Toast.makeText(applicationContext, "LatLng: " + marker.position.latitude + "," +
                    marker.position.longitude, Toast.LENGTH_LONG).show()
        }
        return false
    }

}