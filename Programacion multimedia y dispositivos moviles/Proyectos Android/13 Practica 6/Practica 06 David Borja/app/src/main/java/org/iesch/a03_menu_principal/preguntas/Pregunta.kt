package org.iesch.a03_menu_principal.preguntas

data class Pregunta(
    val operacion: String,
    val ecuacion: String,
    val opcion1: String,
    val opcion2: String,
    val respuestaCorrecta: String
)