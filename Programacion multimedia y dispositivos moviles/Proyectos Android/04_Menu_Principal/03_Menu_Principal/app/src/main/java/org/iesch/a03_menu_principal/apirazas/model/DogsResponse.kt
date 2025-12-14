package org.iesch.a03_menu_principal.apirazas.model

import com.google.gson.annotations.SerializedName

// 1 - Tras observar la respuesta obtenida fabricamos la llegada del API
data class DogsResponse(
    @SerializedName("status") var status: String,
    @SerializedName("message") var images: List<String>
)
