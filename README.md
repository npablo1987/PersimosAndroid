# 📱 PersimosAndroid - Aplicación de Captura y Gestión de Presupuestos

<div align="center">

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)
![API](https://img.shields.io/badge/API-24%2B-brightgreen?style=for-the-badge)
![License](https://img.shields.io/badge/License-Academic-blue?style=for-the-badge)

**Aplicación Android moderna que integra captura de imágenes, geolocalización automática y envío de correos electrónicos con sistema de gestión de presupuestos.**

[Características](#-características) • [Instalación](#-instalación) • [Configuración](#-configuración) • [Documentación](#-documentación)

</div>

---

## 📋 Tabla de Contenidos

- [Acerca del Proyecto](#-acerca-del-proyecto)
- [Características](#-características)
- [Tecnologías](#-tecnologías)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación](#-instalación)
- [Configuración](#-configuración)
- [Arquitectura](#-arquitectura)
- [Permisos](#-permisos)
- [Uso](#-uso)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Librerías](#-librerías-utilizadas)
- [Solución de Problemas](#-solución-de-problemas)
- [Contribución](#-contribución)
- [Contacto](#-contacto)

---

## 🎯 Acerca del Proyecto

**PersimosAndroid** es una aplicación móvil integral desarrollada en Kotlin que combina múltiples funcionalidades empresariales: captura de imágenes con metadatos de ubicación, gestión de presupuestos y comunicación automática vía email. Desarrollada como proyecto académico en Duoc UC, demuestra la implementación de APIs de Android y mejores prácticas de desarrollo móvil.

### 🌟 Casos de Uso

- ✅ **Registro Fotográfico con Geolocalización**: Inspecciones, verificaciones de campo
- ✅ **Gestión de Presupuestos**: Control de gastos y seguimiento financiero
- ✅ **Notificaciones Automáticas**: Envío de información sin intervención manual
- ✅ **Aplicaciones Empresariales**: Reportes de campo, auditorías, seguimiento

---

## ✨ Características

### 🎯 Funcionalidades Principales

| Característica | Descripción |
|---------------|-------------|
| 📸 **Captura de Imágenes** | Integración nativa con cámara para fotografías de alta calidad |
| 📍 **Geolocalización Automática** | GPS + Geocoder para coordenadas y nombre de ciudad |
| 📧 **Sistema de Correo** | Envío automático de emails con archivos adjuntos (JavaMail API) |
| 💰 **Gestión de Presupuestos** | Sistema completo de seguimiento y reportes de gastos |
| 🎨 **UI Moderna** | Material Design 3 con efectos blur y animaciones |
| 📊 **Reportes Visuales** | Gráficos circulares y listas de presupuestos |
| 🔐 **Gestión de Permisos** | Solicitud dinámica siguiendo Android Best Practices |
| 📁 **FileProvider** | Compartición segura de archivos |

### 🚀 Características Técnicas

- ✅ Arquitectura MVVM (Model-View-ViewModel)
- ✅ ViewBinding para acceso seguro a vistas
- ✅ Lifecycle-aware components
- ✅ Kotlin Coroutines ready
- ✅ Material Design Components
- ✅ Responsive UI adaptable
- ✅ Manejo robusto de errores

---

## 🛠️ Tecnologías

### Stack Principal

```
Lenguaje:      Kotlin 100%
SDK Mínimo:    API 24 (Android 7.0 Nougat)
SDK Target:    API 34 (Android 14)
Build System:  Gradle 8.x (Kotlin DSL)
IDE:           Android Studio Hedgehog | 2023.1.1+
```

### Librerías Principales

- **AndroidX**: AppCompat, Core KTX, Material Design
- **UI**: CircularProgressBar, BlurView, Glide
- **Email**: JavaMail API (android-mail 1.6.7)
- **Architecture**: Lifecycle, ViewModel, LiveData

---

## 📦 Requisitos Previos

### Software Necesario

- ✅ **Android Studio** Hedgehog (2023.1.1) o superior
- ✅ **JDK** 8+ (recomendado JDK 17)
- ✅ **Gradle** 8.0+ (incluido con Android Studio)
- ✅ **Git** para clonar el repositorio

### Hardware Requerido

**Dispositivo Android o Emulador con:**
- Android 7.0 (API 24) o superior
- Cámara (física o emulada)
- GPS/Location Services habilitados
- Conexión a Internet activa
- Mínimo 2GB de RAM

---

## 🚀 Instalación

### Paso 1: Clonar el Repositorio

```bash
# Clonar desde GitHub
git clone https://github.com/npablo1987/PersimosAndroid.git

# Navegar al directorio
cd PersimosAndroid/project191
```

### Paso 2: Abrir en Android Studio

1. Abre **Android Studio**
2. `File` → `Open`
3. Selecciona la carpeta `project191`
4. Espera a que Gradle sincronice automáticamente
5. Si hay errores: `File` → `Sync Project with Gradle Files`

### Paso 3: Verificar Build

```bash
# En el terminal de Android Studio
./gradlew clean build

# Windows
gradlew.bat clean build
```

---

## ⚙️ Configuración

### 🔴 1. Configuración de Correo Electrónico (OBLIGATORIO)

**Archivo**: `app/src/main/java/com/example/project1912/Util/JavaMailAPI.kt`

```kotlin
// Línea 34 - Reemplaza con tus credenciales
return PasswordAuthentication("tu_correo@gmail.com", "tu_contraseña_app")
```

#### 🔑 Generar Contraseña de Aplicación Gmail

⚠️ **NO uses tu contraseña personal de Gmail**

**Pasos**:
1. Ve a [myaccount.google.com](https://myaccount.google.com)
2. **Seguridad** → **Verificación en dos pasos** (habilitar)
3. Busca **Contraseñas de aplicaciones**
4. Selecciona **Correo** → **Otro** → Escribe "PersimosAndroid"
5. Click **Generar**
6. Copia la contraseña de 16 caracteres
7. Pégala en el código (sin espacios)

### 2. Configurar Destinatarios

**MainActivity.kt** - Línea 119:
```kotlin
destinatario = "tu_email_destino@gmail.com"
```

**ActivityCapturaCI.kt** - Línea 135:
```kotlin
destinatario = "tu_email_destino@gmail.com"
```

### 3. Configurar SDK de Android

**Archivo**: `local.properties`

```properties
sdk.dir=/ruta/a/tu/Android/sdk

# Ejemplos:
# macOS:   sdk.dir=/Users/usuario/Library/Android/sdk
# Windows: sdk.dir=C\:\\Users\\usuario\\AppData\\Local\\Android\\sdk
```

### 4. Configurar Emulador (Opcional)

**GPS en Emulador**:
1. Click en `...` (More) en el emulador
2. **Location** → Ingresar coordenadas

**Cámara Virtual**:
1. `Tools` → `AVD Manager`
2. Editar AVD → **Camera**: `Emulated` o `Webcam`

---

## 🏗️ Arquitectura

### Patrón MVVM (Model-View-ViewModel)

```
┌─────────────────────────────────────────┐
│           VIEW LAYER                     │
│  IntroActivity → MainActivity → Report  │
└──────────────┬──────────────────────────┘
               │ ViewBinding
               ▼
┌─────────────────────────────────────────┐
│        VIEWMODEL LAYER                   │
│      MainViewModel (LiveData)           │
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│      REPOSITORY LAYER                    │
│      MainRepository (Data Access)       │
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│         MODEL LAYER                      │
│    BudgetDomain • ExpenseDomain         │
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│       UTILITY LAYER                      │
│   JavaMailAPI • LocationHelper          │
└─────────────────────────────────────────┘
```

---

## 🔒 Permisos

### Declarados en AndroidManifest.xml

```xml
<!-- CÁMARA -->
<uses-permission android:name="android.permission.CAMERA"/>

<!-- ALMACENAMIENTO -->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>

<!-- INTERNET -->
<uses-permission android:name="android.permission.INTERNET" />

<!-- UBICACIÓN -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

**Todos los permisos peligrosos se solicitan en tiempo de ejecución** siguiendo las mejores prácticas de Android.

---

## 💻 Uso

### 🎬 Flujo de la Aplicación

#### 1️⃣ Pantalla de Introducción
- **Activity**: `IntroActivity`
- **Función**: Bienvenida y splash screen

#### 2️⃣ Captura de Imagen
```
Usuario → Botón Cámara → Permisos → Captura → Envío Email Automático
```

**Proceso**:
1. Abre `ActivityCapturaCI`
2. Toca botón de cámara
3. Sistema verifica/solicita permisos
4. Usuario captura foto
5. Foto se envía automáticamente por email con adjunto
6. Redirección a `MainActivity`

#### 3️⃣ Pantalla Principal con Ubicación
```
App Inicia → Permisos GPS → Ubicación → Email Automático
```

**Datos enviados**:
- 📍 Ciudad (mediante Geocoder)
- 🌐 Latitud/Longitud
- 🕐 Fecha y hora

#### 4️⃣ Reportes de Presupuestos
- **Activity**: `ReportActivity`
- Muestra lista con título, precio y porcentaje

### 📧 Ejemplos de Código

#### Envío de Ubicación (Automático)

```kotlin
// MainActivity.kt
locationHelper.getLocation { info ->
    val mensaje = """
        Ciudad: ${info.cityName}
        Latitud: ${info.latitude}
        Longitud: ${info.longitude}
        Fecha: ${info.dateTime}
    """.trimIndent()
    
    JavaMailAPI(
        context = this,
        destinatario = "destino@gmail.com",
        asunto = "Información de Ubicación",
        mensaje = mensaje
    ).start()
}
```

#### Envío de Foto con Adjunto

```kotlin
// ActivityCapturaCI.kt
val javaMailAPI = JavaMailAPI(
    context = this,
    destinatario = "destino@gmail.com",
    asunto = "Foto Capturada",
    mensaje = "Adjunto la foto",
    archivoAdjunto = file  // ← Archivo adjunto
)
javaMailAPI.start()
```

---

## 📁 Estructura del Proyecto

```
project191/
├── app/src/main/java/com/example/project1912/
│   ├── Activity/
│   │   ├── IntroActivity.kt          # 🏠 Pantalla inicial
│   │   ├── MainActivity.kt           # 📍 Principal con GPS
│   │   ├── ReportActivity.kt         # 📊 Reportes
│   │   ├── RegisterActivity.kt       # 📝 Registro
│   │   └── ActivityCapturaCI.kt      # 📸 Captura de imágenes
│   │
│   ├── Adapter/
│   │   └── ReportListAdapter.kt      # RecyclerView adapter
│   │
│   ├── Domain/
│   │   ├── BudgetDomain.kt           # 💰 Modelo presupuesto
│   │   └── ExpenseDomain.kt          # 💸 Modelo gasto
│   │
│   ├── Repository/
│   │   └── MainRepository.kt         # 🗄️ Acceso a datos
│   │
│   ├── ViewModel/
│   │   └── MainViewModel.kt          # 🧠 Lógica de negocio
│   │
│   └── Util/
│       ├── JavaMailAPI.kt            # 📧 API de email
│       └── LocationHelper.kt         # 📍 Helper GPS
│
├── res/
│   ├── layout/                       # 🎨 Diseños XML
│   ├── values/                       # 🎭 Recursos
│   ├── drawable/                     # 🖼️ Gráficos
│   └── mipmap/                       # 🚀 Iconos
│
└── AndroidManifest.xml               # 📋 Manifest
```

---

## 📚 Librerías Utilizadas

### Core Android

```gradle
// AndroidX Core
implementation("androidx.core:core-ktx:1.12.0")
implementation("androidx.appcompat:appcompat:1.6.1")
implementation("androidx.constraintlayout:constraintlayout:2.1.4")
implementation("com.google.android.material:material:1.10.0")
```

### UI Components

```gradle
// Circular Progress Bar para visualización de porcentajes
implementation("com.mikhaellopez:circularprogressbar:3.1.0")

// Blur Effect para efectos visuales modernos
implementation("com.github.Dimezis:BlurView:version-2.0.3")

// Glide para carga eficiente de imágenes
implementation("com.github.bumptech.glide:glide:4.12.0")
```

### Email

```gradle
// JavaMail API para envío de emails con adjuntos
implementation("com.sun.mail:android-mail:1.6.7")
implementation("com.sun.mail:android-activation:1.6.7")
```

### Architecture Components

```gradle
// Lifecycle-aware Components
implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

// ViewModel
implementation("androidx.activity:activity-ktx:1.4.0")
```

---

## 🐛 Solución de Problemas

### ❌ Error: Autenticación de Correo Falló

**Síntoma**: `AuthenticationFailedException: Username and Password not accepted`

**Solución**:
```
✅ Usa contraseña de aplicación, NO tu contraseña de Gmail
✅ Habilita verificación en dos pasos
✅ Genera nueva contraseña desde myaccount.google.com
✅ Verifica que no haya espacios en la contraseña
```

### ❌ Error: Permisos de Cámara Denegados

**Solución**:
```
Dispositivo: Configuración → Apps → PersimosAndroid → Permisos → Cámara → Permitir
Emulador:    Settings → Apps → PersimosAndroid → Permissions → Camera → Allow
```

### ❌ Error: No se Obtiene Ubicación

**Solución**:
```
✅ Habilitar GPS: Configuración → Ubicación → Activar
✅ Otorgar permisos de ubicación
✅ Emulador: Extended Controls (...) → Location → Ingresar coordenadas
```

### ❌ Error: Gradle Sync Failed

**Síntoma**: `Could not resolve com.github.Dimezis:BlurView`

**Solución**: Agregar JitPack en `settings.gradle.kts`:
```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }  // ← Agregar
    }
}
```

### ❌ Error: FileProvider Exception

**Síntoma**: `Failed to find configured root`

**Solución**: Verificar `res/xml/file_paths.xml`:
```xml
<?xml version="1.0" encoding="utf-8"?>
<paths xmlns:android="http://schemas.android.com/apk/res/android">
    <cache-path name="my_images" path="images/" />
    <external-files-path name="external_files" path="." />
</paths>
```

---

## 🧪 Testing

### Comandos Útiles

```bash
# Instalar APK
./gradlew installDebug

# Ver logs
adb logcat | grep "project1912"

# Otorgar permisos manualmente
adb shell pm grant com.example.project1912 android.permission.CAMERA
adb shell pm grant com.example.project1912 android.permission.ACCESS_FINE_LOCATION

# Limpiar datos de la app
adb shell pm clear com.example.project1912

# Tomar screenshot
adb shell screencap /sdcard/screen.png
adb pull /sdcard/screen.png
```

### Checklist de Pruebas

- [ ] Instalación limpia funciona
- [ ] Permisos de cámara se solicitan
- [ ] Permisos de ubicación se solicitan
- [ ] Captura de foto exitosa
- [ ] Email con foto se envía con adjunto
- [ ] Email con ubicación contiene datos correctos
- [ ] Lista de presupuestos se muestra
- [ ] Rotación de pantalla mantiene datos

---

## 🤝 Contribución

¡Contribuciones son bienvenidas!

### Cómo Contribuir

1. **Fork** el proyecto
2. Crea tu rama (`git checkout -b feature/AmazingFeature`)
3. Commit cambios (`git commit -m 'Add: Nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un **Pull Request**

### Convenciones de Código

```kotlin
// ✅ Clases: PascalCase
class MainActivity : AppCompatActivity()

// ✅ Funciones: camelCase
private fun sendLocationEmail()

// ✅ Variables: camelCase
val locationHelper = LocationHelper(this)

// ✅ Constantes: SCREAMING_SNAKE_CASE
companion object {
    private const val REQUEST_CODE = 200
}
```

### Formato de Commits

```
Add: Nueva funcionalidad
Fix: Corrección de bug
Update: Actualización de código
Refactor: Refactorización
Docs: Cambios en documentación
Style: Formato/estilo
Test: Tests
```

---

## 🔮 Roadmap

### Próximas Funcionalidades

- [ ] **Tests Unitarios**: JUnit + Mockito
- [ ] **Tests UI**: Espresso
- [ ] **Internacionalización**: Soporte multi-idioma (ES, EN, PT)
- [ ] **Dark Mode**: Tema oscuro completo
- [ ] **Base de Datos**: Room para persistencia local
- [ ] **Sincronización**: Backend con Firebase/API REST
- [ ] **Notificaciones Push**: Firebase Cloud Messaging
- [ ] **Export**: PDF de reportes de presupuestos
- [ ] **Gráficos Avanzados**: Charts detallados
- [ ] **Offline Mode**: Funcionamiento sin Internet

---

## 📄 Licencia

Este proyecto es parte de un trabajo académico de **Duoc UC**.

**Uso Académico** - No destinado para uso comercial.

---

## 👥 Autor

**Pablo Vilches Valenzuela**
- 🎓 Duoc UC
- 📧 Email: [pvilches1987@gmail.com](mailto:pvilches1987@gmail.com)
- 💼 GitHub: [@npablo1987](https://github.com/npablo1987)

---

## 🙏 Agradecimientos

- **Duoc UC** - Apoyo académico y recursos
- **Android Developer Community** - Documentación y mejores prácticas
- **Open Source Contributors** - Autores de las librerías utilizadas:
  - [CircularProgressBar](https://github.com/lopspower/CircularProgressBar) - MikhaeL Lopez
  - [BlurView](https://github.com/Dimezis/BlurView) - Dimezis
  - [Glide](https://github.com/bumptech/glide) - Bumptech
  - [JavaMail](https://javaee.github.io/javamail/) - Oracle/Eclipse Foundation

---

## 📞 Soporte

### ¿Necesitas Ayuda?

- 📧 **Email**: pvilches1987@gmail.com
- 🐛 **Issues**: [GitHub Issues](https://github.com/npablo1987/PersimosAndroid/issues)
- 💬 **Discusiones**: [GitHub Discussions](https://github.com/npablo1987/PersimosAndroid/discussions)

### Documentación Adicional

- [Android Developer Guide](https://developer.android.com/guide)
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Material Design Guidelines](https://m3.material.io/)
- [JavaMail API Docs](https://javaee.github.io/javamail/)

---

<div align="center">

### ⭐ Si este proyecto te ayudó, considera darle una estrella

**Desarrollado con ❤️ en Chile**

© 2024 PersimosAndroid - Todos los derechos reservados

[![GitHub Stars](https://img.shields.io/github/stars/npablo1987/PersimosAndroid?style=social)](https://github.com/npablo1987/PersimosAndroid)
[![GitHub Forks](https://img.shields.io/github/forks/npablo1987/PersimosAndroid?style=social)](https://github.com/npablo1987/PersimosAndroid/fork)

</div>
