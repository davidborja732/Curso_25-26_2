// Importar createGlobalStyle de styled-components para definir estilos globales
import { createGlobalStyle } from 'styled-components';

/**
 * GlobalStyles - Define los estilos globales de la aplicación
 * Se aplican a toda la página y establecen reset CSS y variables comunes
 */
const GlobalStyles = createGlobalStyle`
  /* Reset de estilos por defecto del navegador */
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }

  /* Estilos del body */
  body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #f5f5f5;
    color: #333;
    line-height: 1.6;
  }

  /* Estilos del elemento html */
  html {
    scroll-behavior: smooth;
  }

  /* Scroll personalizado */
  ::-webkit-scrollbar {
    width: 8px;
  }

  ::-webkit-scrollbar-track {
    background: #f1f1f1;
  }

  ::-webkit-scrollbar-thumb {
    background: #888;
    border-radius: 4px;

    &:hover {
      background: #555;
    }
  }
`;

export default GlobalStyles;
