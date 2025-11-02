package org.iesch.a03_menu_principal.apirazas.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

// 2 - Creamos la interfaz, la cual contndrá el mét odo (o los metodos)
// por los cuales queremos consumir nuestro API
interface APIService {

    // 3 - Aqui usaré Retrofi, y lo promero que he de poner es el tipo de operacion que realizo
    // 4 - Esta funcion recibira por parámetro algo. Una direccion + hound/images
    // Y devolverá un objeto de tipo DogsResponse
    @GET
    suspend fun getPerrosPorRaza( @Url url: String ) : Response<DogsResponse>
}