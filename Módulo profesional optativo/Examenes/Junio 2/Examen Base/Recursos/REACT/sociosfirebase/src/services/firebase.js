// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
// Import Firestore para acceder a la base de datos
import { getFirestore } from "firebase/firestore";

/**
 * Configuración de Firebase para la aplicación
 * Contiene las credenciales necesarias para conectar con el proyecto de Firebase
 */
const firebaseConfig = {
  apiKey: "AIzaSyDtMbBDzzJ3tThk2JZ_NoT2G7-6Gx0eFWo",
  authDomain: "socios-cf116.firebaseapp.com",
  projectId: "socios-cf116",
  storageBucket: "socios-cf116.firebasestorage.app",
  messagingSenderId: "830297509873",
  appId: "1:830297509873:web:8ef4c75762ce873786be50"
};

// Inicializar Firebase con la configuración proporcionada
const app = initializeApp(firebaseConfig);

// Obtener la instancia de Firestore para acceder a la base de datos
export const db = getFirestore(app);

// Exportar la aplicación inicializada por si es necesaria en el futuro
export default app;
