package org.iesch.firebase.model

data class Tarea (
    var id: String="",
    var titulo: String="",
    var descripcion: String="",
    var completada: Boolean = false
){
    fun toMap(): Map<Any, Any>{
        return mapOf(
            "id" to id,
            "titulo" to titulo,
            "descripcion" to descripcion,
            "completada" to completada
        )
    }
}



