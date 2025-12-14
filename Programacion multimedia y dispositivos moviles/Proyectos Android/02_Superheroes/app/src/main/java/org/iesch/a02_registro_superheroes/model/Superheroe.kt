package org.iesch.a02_registro_superheroes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
// Me creo el objeto superheroe y lo hago parcelizable
@Parcelize
data class Superheroe(
    val nombre: String,
    val alterego: String,
    val bio: String,
    val power: Float
): Parcelable


