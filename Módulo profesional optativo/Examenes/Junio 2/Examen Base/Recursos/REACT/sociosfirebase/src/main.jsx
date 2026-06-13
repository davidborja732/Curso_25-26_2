/**
 * Archivo main.jsx
 * Punto de entrada de la aplicación React con ReactDOM
 * Renderiza el componente App en el elemento con id "root"
 */

import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";

/**
 * Renderizar la aplicación React en el DOM
 * Busca el elemento con id "root" y renderiza el componente App en él
 */
ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
