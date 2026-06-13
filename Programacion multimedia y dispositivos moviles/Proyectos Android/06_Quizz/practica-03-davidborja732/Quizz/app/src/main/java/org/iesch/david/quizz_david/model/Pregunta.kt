package org.iesch.david.quizz_david.model

data class Pregunta(
    val operacion: String,
    val ecuacion: String,
    val opcion1: String,
    val opcion2: String,
    val respuestaCorrecta: String
)

