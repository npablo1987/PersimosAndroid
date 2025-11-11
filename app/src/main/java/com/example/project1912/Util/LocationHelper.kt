package com.example.project1912.Util

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import java.util.*

class LocationHelper(private val context: Context) : LocationListener {

    private var callback: ((LocationInfo) -> Unit)? = null
    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @SuppressLint("MissingPermission")
    fun getLocation(callback: (LocationInfo) -> Unit) {
        this.callback = callback

        // Verificar permisos
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Los permisos no están otorgados
            return
        }

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, this)
        } catch (ex: Exception) {
            Log.d("LocationHelper", "Error al solicitar actualizaciones de ubicación: ${ex.message}")
        }
    }

    override fun onLocationChanged(location: Location) {
        val latitude = location.latitude
        val longitude = location.longitude

        // Obtener el nombre de la ciudad utilizando Geocoder
        val geocoder = Geocoder(context, Locale.getDefault())
        var cityName = "Ciudad desconocida"
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses != null && addresses.isNotEmpty()) {
                val address = addresses[0]
                cityName = address.locality ?: "Ciudad desconocida"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            cityName = "Error obteniendo la ciudad"
        }

        // Obtener la fecha y hora actuales
        val currentDate = Calendar.getInstance().time
        val dateTime = currentDate.toString()

        // Detener las actualizaciones de ubicación
        locationManager.removeUpdates(this)

        // Crear un objeto con la información
        val locationInfo = LocationInfo(
            cityName = cityName,
            latitude = latitude,
            longitude = longitude,
            dateTime = dateTime
        )

        // Llamar al callback con la información de ubicación
        callback?.invoke(locationInfo)
    }

    // Otras funciones de LocationListener pueden permanecer vacías
    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onProviderDisabled(provider: String) {}

    // Clase de datos para encapsular la información de ubicación
    data class LocationInfo(
        val cityName: String,
        val latitude: Double,
        val longitude: Double,
        val dateTime: String
    )
}
