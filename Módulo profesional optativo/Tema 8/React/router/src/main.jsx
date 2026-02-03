// Importar React y ReactDOM
import React from 'react';
import ReactDOM from 'react-dom/client';
// Importar el componente principal
import App from './App';
// Importar estilos globales
import './styles/GlobalStyles.js';

// Renderizar la aplicación en el elemento root del HTML
ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
