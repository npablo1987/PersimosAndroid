package com.example.project1912.Activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project1912.R
import com.example.project1912.Util.JavaMailAPI
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ActivityCapturaCI : AppCompatActivity() {

    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>
    private var photoUri: Uri? = null

    // Código de solicitud de permisos
    private val REQUEST_CAMERA_PERMISSION = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_captura_ci)

        // Configurar padding para las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar el launcher de la cámara
        cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageView = findViewById<ImageView>(R.id.goBtn)

                // La foto ya está guardada en photoUri
                imageView.setImageURI(photoUri)

                // Enviar el correo con la foto adjunta
                if (photoUri != null) {
                    sendEmailWithPhoto(photoUri!!)
                } else {
                    Toast.makeText(this, "Error al capturar la imagen", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Captura cancelada", Toast.LENGTH_SHORT).show()
            }
        }

        // Evento para abrir la cámara
        val goBtn = findViewById<ImageView>(R.id.goBtn)
        goBtn.setOnClickListener {
            if (checkPermissions()) {
                openCamera()
            } else {
                requestPermissions()
            }
        }
    }

    // Verificar permisos
    private fun checkPermissions(): Boolean {
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        return cameraPermission == PackageManager.PERMISSION_GRANTED
    }

    // Solicitar permisos
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION)
    }

    // Abrir la cámara
    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        // Crear un archivo donde se guardará la foto
        val photoFile: File? = try {
            createImageFile()
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }

        // Continuar solo si el archivo fue creado correctamente
        photoFile?.also {
            photoUri = FileProvider.getUriForFile(
                this,
                "${applicationContext.packageName}.provider",
                it
            )
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            cameraLauncher.launch(cameraIntent)
        } ?: run {
            Toast.makeText(this, "No se pudo crear el archivo para la imagen", Toast.LENGTH_SHORT).show()
        }
    }

    // Crear un archivo de imagen
    @Throws(IOException::class)
    private fun createImageFile(): File {
        val imageFileName = "captura.jpg"
        val storageDir = File(cacheDir, "images")
        storageDir.mkdirs()
        return File(storageDir, imageFileName)
    }

    // Enviar correo con la foto adjunta usando JavaMailAPI
    private fun sendEmailWithPhoto(photoUri: Uri) {
        // Convertir el URI a File
        val file = uriToFile(photoUri)

        if (file != null && file.exists()) {
            val asunto = "Foto Capturada"
            val mensaje = "Adjunto la foto que acabo de tomar."

            // Crear y ejecutar JavaMailAPI
            val javaMailAPI = JavaMailAPI(
                context = this,
                destinatario = "kyovilches@gmail.com",
                asunto = asunto,
                mensaje = mensaje,
                archivoAdjunto = file
            )
            javaMailAPI.start()
            //Toast.makeText(this, "Enviando correo...", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))

        } else {
            Toast.makeText(this, "No se pudo acceder al archivo adjunto.", Toast.LENGTH_SHORT).show()
        }
    }

    // Convertir URI a File
    private fun uriToFile(uri: Uri): File? {
        return when (uri.scheme) {
            "content" -> {
                // Obtener el archivo desde el FileProvider
                val filePath = File(cacheDir, "images/captura.jpg").absolutePath
                File(filePath)
            }
            "file" -> {
                File(uri.path)
            }
            else -> null
        }
    }

    // Manejar el resultado de la solicitud de permisos
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA_PERMISSION && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            openCamera()
        } else {
            Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }
}
