// Importar React y los hooks necesarios
import React, { useState, useEffect } from 'react';
// Importar useNavigate de React Router para navegar entre pantallas
import { useNavigate } from 'react-router-dom';
// Importar componentes estilizados
import {
  Container,
  Header,
  Nav,
  NavLink,
  Title,
  Card,
  CardGrid,
  Button,
  Text,
  Row
} from '../styles/styled';
// Importar los datos de productos desde el archivo JSON
import productsData from '../data/products.json';

/**
 * Componente Home - Pantalla de inicio
 * Muestra una lista de items que se pueden clickear para ir a la página de detalles
 */
function Home() {
  // Hook para navegar entre rutas
  const navigate = useNavigate();
  // Estado: Lista de productos obtenida del archivo JSON
  const [items, setItems] = useState([]);

  /**
   * useEffect - Se ejecuta cuando el componente se monta
   * Carga los productos desde el archivo JSON
   */
  useEffect(() => {
    // Cargar los productos del JSON en el estado
    setItems(productsData);
    
  }, []); // Dependencia vacía: se ejecuta solo una vez al montar el componente

  /**
   * handleViewDetails - Navega a la pantalla de detalles
   * Pasa el id como parámetro en la URL
   * 
   * @param {number} id - ID del producto a visualizar
   */
  const handleViewDetails = (id) => {
    // Navegar a la ruta /details/:id pasando el id del producto
    navigate(`/details/${id}`);

  };

  /**
   * handleNavigateAbout - Navega a la pantalla "Acerca de"
   */
  const handleNavigateAbout = () => {
    navigate('/about');
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
              <NavLink href="/">Inicio</NavLink>
              <NavLink onClick={handleNavigateAbout}>Acerca de</NavLink>
            </Nav>
          </Row>
        </Container>
      </Header>

      {/* Contenido principal */}
      <Container>
        {/* Título de bienvenida */}
        <Title>Bienvenido a nuestra tienda</Title>

        {/* Texto descriptivo */}
        <Card>
          <Text>
            Esta es una aplicación web responsiva creada con React, Vite, Styled Components y React Router.
            Explora nuestros productos haciendo click en cualquiera de las tarjetas a continuación.
          </Text>
        </Card>

        {/* Grid de productos */}
        <CardGrid>
          {/* Mapear sobre los items y crear una tarjeta para cada uno */}
          {items.map((item) => (
            <Card key={item.id}>
              {/* Nombre del producto */}
              <h3 style={{ marginBottom: '10px', color: '#667eea' }}>{item.name}</h3>

              {/* Categoría del producto */}
              <Text size="14px" style={{ marginBottom: '10px', color: '#999' }}>
                {item.category}
              </Text>

              {/* Descripción del producto */}
              <Text style={{ marginBottom: '10px' }}>{item.description}</Text>

              {/* Rating del producto */}
              <Text size="14px" style={{ marginBottom: '10px', color: '#ff9800' }}>
                ⭐ {item.rating} / 5
              </Text>

              {/* Precio del producto */}
              <Text size="18px" style={{ color: '#764ba2', fontWeight: 'bold', marginBottom: '15px' }}>
                {item.price}
              </Text>

              {/* Botón para ver detalles - Pasa el id como parámetro */}
              <Button onClick={() => handleViewDetails(item.id)}>
                Ver Detalles
              </Button>
            </Card>
          ))}
        </CardGrid>
      </Container>
    </>
  );
}

export default Home;
