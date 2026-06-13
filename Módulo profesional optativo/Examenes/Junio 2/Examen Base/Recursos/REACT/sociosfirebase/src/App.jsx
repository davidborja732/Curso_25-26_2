/**
 * Componente principal App
 * Punto de entrada de la aplicación React
 */

import React from "react";
import { GlobalStyle } from "./styles/GlobalStyle";
import SociosList from "./components/SociosList";

/**
 * Componente App
 * Renderiza los estilos globales y el componente principal de la aplicación
 */
function App() {
  return (
    <>
      {/* Aplicar estilos globales a toda la aplicación */}
      <GlobalStyle />
      
      {/* Renderizar el componente principal de gestión de socios */}
      <SociosList />
    </>
  );
}

export default App;
