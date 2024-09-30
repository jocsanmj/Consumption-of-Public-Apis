# Consumo de APIs Públicas - Android Studio
<img src="https://uxwing.com/wp-content/themes/uxwing/download/brands-and-social-media/android-studio-icon.png" alt="Logo Android" width="100" />

Este proyecto es una aplicación de Android que muestra cómo consumir APIs públicas utilizando Kotlin y la arquitectura de Android. La aplicación recupera y muestra datos obtenidos de una API pública y los muestra en una lista utilizando `RecyclerView`. 

## Características
- Arquitectura basada en `RecyclerView` para mostrar datos obtenidos de una API pública.
- Uso de `ProgressBar` para indicar cuando los datos están cargándose.
- Diseño simple y responsivo utilizando `ConstraintLayout`.
- Manejo de errores de red básico.
- Botón estilizado para actualizar los datos.

## Requisitos
- Android Studio Bumblebee o posterior.
- Gradle 7.0 o superior.
- Conexión a internet para consumir la API pública.

## Estructura del Proyecto
```bash
.
├── app/
│   ├── manifests/
│   ├── java/
│   │   └── com.appsnica.consumodeapispblicas/
│   │       ├── ApiAdapter.kt         # Adaptador de la API para realizar las llamadas HTTP.
│   │       ├── MainActivity.kt       # Actividad principal donde se consumen los datos.
│   │       └── Pantallainicio.kt     # Pantalla inicial.
│   ├── res/
│   │   ├── drawable/                 # Imágenes y fondos.
│   │   ├── layout/
│   │   │   ├── activity_main.xml     # Diseño de la pantalla principal.
│   │   │   ├── activity_pantallainicio.xml # Diseño de la pantalla de inicio.
│   │   │   └── item_api.xml          # Diseño de cada ítem del RecyclerView.
│   │   └── values/                   # Archivos de recursos como strings, colores y dimensiones.
├── build.gradle.kts                  # Script de compilación del proyecto.
└── settings.gradle.kts               # Configuración de los módulos del proyecto.
```
## Instalación:
1. Clona este repositorio:
 ```sh
   git clone https://github.com/jocsanmj/Consumption-of-Public-Apis
   ```
2. Abre el proyecto en Android Studio.
3. Compila y ejecuta la aplicación en un emulador o dispositivo físico.

## Uso
1. Al abrir la aplicación, se mostrará una pantalla con una lista vacía.
2. Al hacer clic en el botón "Obtener Datos", la aplicación realizará una solicitud a la API pública y mostrará los resultados en la lista.
3. Los datos obtenidos se visualizarán en una vista de lista personalizada.

## Personalización
- API: Puedes modificar el endpoint de la API dentro del archivo ApiAdapter.kt para consumir diferentes datos públicos.
- Interfaz: Los archivos XML en la carpeta res/layout/ contienen el diseño de la interfaz de usuario y pueden ser modificados para cambiar la apariencia de la aplicación.

## Librerías utilizadas
- Retrofit para la comunicación con la API.
- Gson para el parseo de los datos JSON.
- RecyclerView para mostrar listas de datos.
