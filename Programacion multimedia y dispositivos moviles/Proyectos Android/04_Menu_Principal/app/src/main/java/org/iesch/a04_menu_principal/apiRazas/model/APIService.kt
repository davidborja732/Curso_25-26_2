package org.iesch.a04_menu_principal.apiRazas.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

// Creamos la interfaz la cual contendra los metodos por los cuales queremos consumir nuestro API
interface APIService {
    // Usare retrofit y lo primero que he de poner es el tipo de operacion que realizo
    // Esta funcion recibira por parametro algo. Una direccion + hound/images
    // y devolvera un objeto del tipo DogsResponse
    @GET
    suspend fun getPerrosPorRaza(@Url url: String): Response<DogsResponse>

}