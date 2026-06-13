/**
 * Estilos globales de la aplicación
 * Define colores, tipografía y estilos base para toda la aplicación
 */

import { createGlobalStyle } from "styled-components";

/**
 * Estilos globales aplicados a toda la aplicación
 * Incluye resets, variables de color y configuración base
 */
export const GlobalStyle = createGlobalStyle`
  /* Reset universal para todos los elementos */
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }

  /* Estilos base para el elemento html */
  html {
    scroll-behavior: smooth;
  }

  /* Estilos base para el cuerpo de la página */
  body {
    font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    min-height: 100vh;
    color: #333;
    line-height: 1.6;
  }

  /* Estilos para el contenedor raíz de React */
  #root {
    min-height: 100vh;
    display: flex;
    flex-direction: column;
  }

  /* Estilos para inputs y textareas */
  input,
  textarea,
  select {
    font-family: inherit;
    font-size: inherit;
  }

  /* Estilos para botones */
  button {
    cursor: pointer;
    border: none;
    font-family: inherit;
  }

  /* Remover estilos por defecto de listas */
  ul,
  ol {
    list-style: none;
  }

  /* Remover subrayado de links */
  a {
    text-decoration: none;
    color: inherit;
  }

  /* Estilos para elementos interactivos */
  button:hover,
  a:hover {
    transition: all 0.3s ease;
  }
`;

/**
 * Colores utilizados en la aplicación
 * Se pueden reutilizar en componentes styled-components
 */
export const colors = {
  primary: "#667eea",
  secondary: "#764ba2",
  success: "#10b981",
  danger: "#ef4444",
  warning: "#f59e0b",
  info: "#3b82f6",
  light: "#f3f4f6",
  dark: "#1f2937",
  white: "#ffffff",
  gray: "#6b7280",
  lightGray: "#f0f4f8"
};

/**
 * Breakpoints para responsive design
 * Se utilizan para hacer la aplicación responsive
 */
export const breakpoints = {
  mobile: "480px",
  tablet: "768px",
  desktop: "1024px",
  wide: "1280px"
};

/**
 * Sombras para agregar profundidad a los componentes
 */
export const shadows = {
  small: "0 1px 2px 0 rgba(0, 0, 0, 0.05)",
  medium: "0 4px 6px -1px rgba(0, 0, 0, 0.1)",
  large: "0 10px 15px -3px rgba(0, 0, 0, 0.1)",
  xl: "0 20px 25px -5px rgba(0, 0, 0, 0.1)"
};
