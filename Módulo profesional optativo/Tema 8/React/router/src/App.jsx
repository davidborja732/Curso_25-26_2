// Importar los módulos necesarios
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
// Importar los componentes de las pantallas
import Home from './screens/Home';
import Details from './screens/Details';
import About from './screens/About';

/**
 * Componente App - Configurador de rutas de la aplicación
 * Define las rutas disponibles y los componentes que se renderizan en cada una
 */
function App() {
  return (
    // Router: Envuelve toda la aplicación para habilitar la navegación
    <Router>
      {/* Routes: Define las rutas disponibles en la aplicación */}
      <Routes>
        {/* Ruta raíz - Pantalla de inicio */}
        <Route path="/" element={<Home />} />
        
        {/* Ruta para detalles - Acepta un parámetro 'id' */}
        <Route path="/details/:id" element={<Details />} />
        
        {/* Ruta para la página acerca de */}
        <Route path="/about" element={<About />} />
      </Routes>
    </Router>
  );
}

export default App;