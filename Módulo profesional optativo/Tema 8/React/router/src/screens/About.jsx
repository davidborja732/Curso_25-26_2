// Importar React
import React from 'react';
// Importar useNavigate para navegar entre pantallas
import { useNavigate } from 'react-router-dom';
// Importar componentes estilizados
import {
  Container,
  Header,
  Nav,
  NavLink,
  Title,
  Card,
  Button,
  Text,
  Row,
  BackButton
} from '../styles/styled';

/**
 * Componente About - Pantalla "Acerca de"
 * Muestra información sobre la aplicación y la empresa
 */
function About() {
  // Hook para navegar entre rutas
  const navigate = useNavigate();

  /**
   * handleGoHome - Navega a la pantalla de inicio
   */
  const handleGoHome = () => {
    navigate('/');
  };

  /**
   * handleGoBack - Vuelve a la pantalla anterior
   */
  const handleGoBack = () => {
    navigate(-1);
  };

  return (
    <>
      {/* Header con navegación */}
      <Header>
        <Container>
          <Row justify="space-between" align="center">
            <h2>Mi Aplicación</h2>
            {/* Navegación */}
            <Nav>
              <NavLink onClick={handleGoHome}>Inicio</NavLink>
              <NavLink href="/about">Acerca de</NavLink>
            </Nav>
          </Row>
        </Container>
      </Header>

      {/* Contenido principal */}
      <Container>
        {/* Botón para volver atrás */}
        <BackButton onClick={handleGoBack}>
          ← Volver Atrás
        </BackButton>

        {/* Título de la página */}
        <Title>Acerca de Nosotros</Title>

        {/* Card principal con información */}
        <Card>
          <h3 style={{ marginBottom: '15px', color: '#667eea' }}>¿Quiénes somos?</h3>
          <Text>
            Somos una aplicación web moderna desarrollada con las últimas tecnologías de React.
            Nuestro objetivo es proporcionar una experiencia de usuario excelente y responsive
            en todos los dispositivos.
          </Text>
        </Card>

        {/* Card con características de la app */}
        <Card>
          <h3 style={{ marginBottom: '15px', color: '#667eea' }}>Características principales</h3>

          {/* Lista de características */}
          <div style={{ marginBottom: '20px' }}>
            <ul style={{ marginLeft: '20px', lineHeight: '2' }}>
              {/* Navegación */}
              <li>
                ✅ <strong>Navegación fluida</strong> entre diferentes pantallas
              </li>

              {/* Parámetros */}
              <li>
                ✅ <strong>Paso de parámetros</strong> entre rutas para pasar datos
              </li>

              {/* Volver atrás */}
              <li>
                ✅ <strong>Botón "Volver atrás"</strong> que utiliza el historial del navegador
              </li>

              {/* Responsive */}
              <li>
                ✅ <strong>Diseño responsive</strong> con media queries para móvil, tablet y desktop
              </li>

              {/* Componentes reutilizables */}
              <li>
                ✅ <strong>Componentes reutilizables</strong> con styled-components
              </li>

              {/* Estado */}
              <li>
                ✅ <strong>Gestión de estado</strong> con hooks de React (useState, useEffect)
              </li>
            </ul>
          </div>
        </Card>


        {/* Botones de navegación */}
        <Row justify="center">
          <Button onClick={handleGoHome}>
            Ir a Inicio
          </Button>
          <BackButton onClick={handleGoBack}>
            Volver Atrás
          </BackButton>
        </Row>
      </Container>
    </>
  );
}

export default About;
