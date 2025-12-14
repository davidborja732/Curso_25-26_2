package org.iesch.a03_menu_principal
// Paquete principal donde se encuentra este servicio.

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
// Importaciones necesarias para trabajar con Firebase Cloud Messaging (FCM).

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
// Anotación para suprimir el aviso de que no se ha implementado onNewToken().
// Normalmente deberías implementar ese método para manejar la renovación del token de FCM.

class MyFirebaseMessagingService : FirebaseMessagingService() {
    // Clase que extiende FirebaseMessagingService.
    // Esto permite recibir mensajes push enviados desde Firebase Cloud Messaging.

    override fun onMessageReceived(message: RemoteMessage) {
        // Método que se ejecuta automáticamente cuando llega un mensaje FCM.
        super.onMessageReceived(message)

        // Muestra en el log que se ha recibido un mensaje, indicando el remitente.
        Log.d("FCM", "Mensaje Recibido: ${message.from}")
    }
}

