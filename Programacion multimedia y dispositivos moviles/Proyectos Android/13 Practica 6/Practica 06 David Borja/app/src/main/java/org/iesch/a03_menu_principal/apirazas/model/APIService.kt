package org.iesch.a03_menu_principal.apirazas.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

// 2 - Creamos la interfaz, la cual contndrá el mét odo (o los metodos)
// por los cuales queremos consumir nuestro API
interface APIService {
    // Definimos una interfaz llamada APIService.
    // En Retrofit, las interfaces sirven para declarar las operaciones HTTP que vamos a realizar.
    // Retrofit se encargará de implementar esta interfaz automáticamente.

    // 3 - Aquí usaré Retrofit, y lo primero que debo poner es el tipo de operación que realizo (GET, POST, etc.)
    // 4 - Esta función recibirá por parámetro una dirección completa (URL) que apunta a la API de perros.
    //     Ejemplo: "https://dog.ceo/api/breed/hound/images"
    //     Y devolverá un objeto de tipo DogsResponse envuelto en Response.

    @GET
    // Anotación de Retrofit que indica que esta función realizará una petición HTTP GET.

    suspend fun getPerrosPorRaza(@Url url: String): Response<DogsResponse>
    // Declaramos una función suspendida (se ejecuta en corrutinas).
    // @Url indica que el parámetro "url" será la dirección completa de la petición.
    // La función devuelve un objeto Response<DogsResponse>, donde DogsResponse es el modelo de datos
    // que representa la respuesta JSON de la API (lista de imágenes de perros).
}
