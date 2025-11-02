package org.iesch.a03_menu_principal.superheroes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// 6 - Me creo el objeto SuperHeroe y lo hago parcelizable
@Parcelize
data class SuperHeroe (
    val nombre: String,
    val alterEgo: String,
    val bio: String,
    val power: Float
) : Parcelable