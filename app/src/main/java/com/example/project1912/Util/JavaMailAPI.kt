package com.example.project1912.Util

import android.content.Context
import java.io.File
import java.util.*
import javax.activation.DataHandler
import javax.activation.FileDataSource
import javax.mail.*
import javax.mail.internet.*

class JavaMailAPI(
    private val context: Context,
    private val destinatario: String,
    private val asunto: String,
    private val mensaje: String,
    private val archivoAdjunto: File? = null
) : Thread() {

    override fun run() {
        try {
            // Configuración de las propiedades para la conexión SMTP
            val properties = Properties().apply {
                put("mail.smtp.auth", "true")
                put("mail.smtp.starttls.enable", "true")
                put("mail.smtp.host", "smtp.gmail.com")
                put("mail.smtp.port", "587")
                put("mail.smtp.ssl.trust", "smtp.gmail.com")
            }

            // Autenticación
            val session = Session.getInstance(properties, object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    // Reemplaza con tu correo y contraseña de aplicación
                  return PasswordAuthentication("pvilches1987@gmail.com", "")
                }
            })

            val mimeMessage = MimeMessage(session).apply {
                setFrom(InternetAddress("pvilches1987@gmail.com"))
                addRecipient(Message.RecipientType.TO, InternetAddress(destinatario))
                subject = asunto

                // Crear el cuerpo del mensaje
                val messageBodyPart = MimeBodyPart()
                messageBodyPart.setText(mensaje)

                // Crear un multipart para agregar el cuerpo y el adjunto
                val multipart = MimeMultipart()
                multipart.addBodyPart(messageBodyPart)

                // Adjuntar el archivo si existe
                archivoAdjunto?.let {
                    val attachmentBodyPart = MimeBodyPart()

                    // Crear DataSource para el archivo
                    val source = FileDataSource(it)
                    attachmentBodyPart.dataHandler = DataHandler(source)
                    attachmentBodyPart.fileName = it.name

                    multipart.addBodyPart(attachmentBodyPart)
                }

                // Establecer el contenido del mensaje
                setContent(multipart)
            }

            // Enviar el mensaje
            Transport.send(mimeMessage)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}