package org.iesch.a03_menu_principal.apirazas.model
// Indica el paquete donde se encuentra esta clase.
// Sirve para organizar el código dentro del proyecto.

import com.google.gson.annotations.SerializedName
// Importa la anotación SerializedName de la librería Gson.
// Esta anotación permite mapear nombres de campos JSON a propiedades de Kotlin.


// 1 - Tras observar la respuesta obtenida fabricamos la llegada del API
data class DogsResponse(
    // Definimos una data class llamada DogsResponse.
    // Una data class en Kotlin se usa para representar datos y automáticamente genera métodos como toString(), equals(), hashCode().

    @SerializedName("status") var status: String,
    // Campo "status" del JSON. Se mapea a la propiedad status de tipo String.
    // Ejemplo de respuesta: "success".

    @SerializedName("message") var images: List<String>
    // Campo "message" del JSON. Se mapea a la propiedad images, que es una lista de Strings.
    // En la API de perros, "message" contiene una lista de URLs de imágenes.
)

