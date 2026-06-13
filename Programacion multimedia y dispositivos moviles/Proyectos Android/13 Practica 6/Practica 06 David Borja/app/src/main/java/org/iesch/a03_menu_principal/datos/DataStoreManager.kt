package org.iesch.a03_menu_principal.datos
// Define el paquete donde se encuentra este archivo.
// Sirve para organizar el código dentro del proyecto.

import android.content.Context
// Importa la clase Context, necesaria para acceder a recursos y servicios del sistema.

import androidx.datastore.core.DataStore
// Importa la clase DataStore, que es el contenedor principal para guardar datos de forma persistente.

import androidx.datastore.preferences.core.Preferences
// Importa la interfaz Preferences, que representa un conjunto de pares clave-valor almacenados en DataStore.

import androidx.datastore.preferences.preferencesDataStore
// Importa el delegado de extensión preferencesDataStore, que facilita la creación de un DataStore de tipo Preferences.

// Declaramos una propiedad de extensión para Context llamada "dataStore".
// Esto permite acceder a DataStore desde cualquier contexto de la aplicación (por ejemplo, Activity, Application).
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "ajustes_usuario")
// Se crea un DataStore de tipo Preferences con el nombre "ajustes_usuario".
// El "by preferencesDataStore" es un delegado que inicializa el DataStore automáticamente y lo mantiene como singleton.
// Así, cada vez que se accede a "context.dataStore", se obtiene la misma instancia.

