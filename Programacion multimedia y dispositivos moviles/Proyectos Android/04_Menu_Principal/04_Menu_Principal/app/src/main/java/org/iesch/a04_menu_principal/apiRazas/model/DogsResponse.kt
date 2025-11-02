package org.iesch.a04_menu_principal.apiRazas.model

import com.google.gson.annotations.SerializedName

// Tras observar la respuesta obtenida
data class DogsResponse(
    @SerializedName("status") var status: String,
    @SerializedName("message") var images:List<String>
)

