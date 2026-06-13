/**
 * Configuración de Vite
 * Vite es un bundler de módulos rápido para aplicaciones web modernas
 */

import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

/**
 * Configuración exportada para Vite
 * - plugins: Incluye el plugin de React para transformar JSX a JavaScript
 * - server: Configuración del servidor de desarrollo
 */
export default defineConfig({
  // Plugins utilizados en la aplicación
  plugins: [react()],
  
  // Configuración del servidor de desarrollo
  server: {
    // Puerto en el que se ejecutará la aplicación
    port: 3000,
    // Permitir acceso desde la red
    host: true,
  }
})
