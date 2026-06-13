/**
 * Archivo de servicio para operaciones CRUD con Firestore
 * Contiene todas las funciones para leer, crear, actualizar y eliminar socios
 */

import {
  collection,
  addDoc,
  updateDoc,
  deleteDoc,
  doc,
  getDocs,
  getDoc,
  query,
  where,
  serverTimestamp
} from "firebase/firestore";
import { db } from "./firebase";

// Nombre de la colección en Firestore donde se almacenan los socios
const SOCIOS_COLLECTION = "socios";

/**
 * Obtiene la referencia a la colección de socios
 * @returns {CollectionReference} Referencia a la colección de socios
 */
const getSociosCollection = () => collection(db, SOCIOS_COLLECTION);

/**
 * Obtiene todos los socios de la base de datos
 * @async
 * @returns {Promise<Array>} Array con todos los socios encontrados
 */
export const getAllSocios = async () => {
  try {
    // Ejecutar la consulta para obtener todos los documentos de la colección
    const querySnapshot = await getDocs(getSociosCollection());
    
    // Mapear los documentos a un array con el ID del documento incluido
    const socios = querySnapshot.docs.map(doc => ({
      id: doc.id,
      ...doc.data()
    }));
    
    return socios;
  } catch (error) {
    // Registrar el error en la consola para debugging
    console.error("Error al obtener socios:", error);
    throw new Error("No se pudieron obtener los socios");
  }
};

/**
 * Obtiene un socio específico por su ID
 * @async
 * @param {string} socioId - El ID del socio a obtener
 * @returns {Promise<Object>} El objeto socio con su ID
 */
export const getSocioById = async (socioId) => {
  try {
    // Obtener el documento específico de la colección
    const docRef = doc(db, SOCIOS_COLLECTION, socioId);
    const docSnap = await getDoc(docRef);
    
    // Verificar si el documento existe
    if (docSnap.exists()) {
      return {
        id: docSnap.id,
        ...docSnap.data()
      };
    } else {
      throw new Error("El socio no existe");
    }
  } catch (error) {
    console.error("Error al obtener el socio:", error);
    throw error;
  }
};

/**
 * Crea un nuevo socio en la base de datos
 * @async
 * @param {Object} socioData - Objeto con los datos del nuevo socio
 * @param {string} socioData.first_name - Nombre del socio
 * @param {string} socioData.last_name - Apellido del socio
 * @param {string} socioData.email - Email del socio
 * @param {string} socioData.gender - Género del socio

 * @returns {Promise<string>} El ID del documento creado
 */
export const createSocio = async (socioData) => {
  try {
    // Validar que los datos requeridos estén presentes
    if (!socioData.first_name || !socioData.email) {
      throw new Error("El nombre y email son requeridos");
    }
    
    // Agregar timestamp de creación automáticamente
    const newSocio = {
      ...socioData,
      createdAt: serverTimestamp(),
      updatedAt: serverTimestamp()
    };
    
    // Agregar el nuevo documento a la colección
    const docRef = await addDoc(getSociosCollection(), newSocio);
    
    // Retornar el ID del documento creado
    return docRef.id;
  } catch (error) {
    console.error("Error al crear el socio:", error);
    throw error;
  }
};

/**
 * Actualiza un socio existente
 * @async
 * @param {string} socioId - El ID del socio a actualizar
 * @param {Object} socioData - Objeto con los datos a actualizar
 * @returns {Promise<void>}
 */
export const updateSocio = async (socioId, socioData) => {
  try {
    // Validar que el ID no esté vacío
    if (!socioId) {
      throw new Error("El ID del socio es requerido");
    }
    
    // Preparar los datos con el timestamp de actualización
   // Esto copia todas las propiedades del objeto socioData dentro de updateData,
   //Añade la propiedad updatedAt con el valor de serverTimestamp() para registrar la fecha y hora de la actualización
    const updateData = {
      ...socioData,
      updatedAt: serverTimestamp()
    };
    
    // Actualizar el documento en Firestore
    const docRef = doc(db, SOCIOS_COLLECTION, socioId);
    await updateDoc(docRef, updateData);
  } catch (error) {
    console.error("Error al actualizar el socio:", error);
    throw error;
  }
};

/**
 * Elimina un socio de la base de datos
 * @async
 * @param {string} socioId - El ID del socio a eliminar
 * @returns {Promise<void>}
 */
export const deleteSocio = async (socioId) => {
  try {
    // Validar que el ID no esté vacío
    if (!socioId) {
      throw new Error("El ID del socio es requerido");
    }
    
    // Eliminar el documento de Firestore
    const docRef = doc(db, SOCIOS_COLLECTION, socioId);
    await deleteDoc(docRef);
  } catch (error) {
    console.error("Error al eliminar el socio:", error);
    throw error;
  }
};

/**
 * Busca socios por nombre (búsqueda parcial)
 * @async
 * @param {string} searchTerm - Término de búsqueda
 * @returns {Promise<Array>} Array con los socios que coinciden con la búsqueda
 */
export const searchSociosByName = async (searchTerm) => {
  try {
    // Obtener todos los socios
    const allSocios = await getAllSocios();
    
    // Filtrar los socios que contengan el término de búsqueda en el nombre
    const filteredSocios = allSocios.filter(socio =>
      socio.first_name.toLowerCase().includes(searchTerm.toLowerCase())
    );
    
    return filteredSocios;
  } catch (error) {
    console.error("Error al buscar socios:", error);
    throw error;
  }
};
