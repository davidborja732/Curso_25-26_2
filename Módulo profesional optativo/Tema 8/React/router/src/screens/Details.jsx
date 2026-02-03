// Importar React y los hooks necesarios
import React, { useState, useEffect } from 'react';
// Importar useParams para obtener parámetros de la URL
// Importar useNavigate para navegar entre pantallas
import { useParams, useNavigate } from 'react-router-dom';
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
  BackButton,
  Row,
  BadgeContainer
} from '../styles/styled';
// Importar los datos de productos desde el archivo JSON
import productsData from '../data/products.json';

/**
 * Componente Details - Pantalla de detalles del producto
 * Recibe el id del producto como parámetro de ruta y muestra información detallada
 */
function Details() {
  // Hook para obtener los parámetros de la URL
  const { id } = useParams();

  // Hook para navegar entre rutas
  const navigate = useNavigate();

  // Estado: Información del producto
  const [product, setProduct] = useState(null);

  // Estado: Cantidad en carrito
  const [quantity, setQuantity] = useState(1);

  /**
   * useEffect - Se ejecuta cuando el componente se monta o cuando cambia el id
   * Busca el producto con el id proporcionado en la URL
   */
  useEffect(() => {
    // Convertir el array de productos en un objeto con el id como clave para búsqueda rápida
    const productsMap = {};
    productsData.forEach(product => {
      productsMap[product.id] = product;
    });

    // Obtener el producto del objeto usando el id de la URL
    setProduct(productsMap[id]);
  }, [id]); // Dependencia: se ejecuta cuando cambia el id

  /**
   * handleGoBack - Vuelve a la pantalla anterior
   */
  const handleGoBack = () => {
    // Usar -1 para volver a la pantalla anterior en el historial del navegador
    navigate(-1);
  };

  /**
   * handleAddToCart - Simula agregar el producto al carrito
   */
  const handleAddToCart = () => {
    // Mostrar alerta indicando que se agregó al carrito
    alert(`${quantity} ${quantity === 1 ? 'unidad' : 'unidades'} de ${product.name} agregada al carrito`);
    // Reiniciar la cantidad a 1
    setQuantity(1);
  };

  /**
   * handleIncreaseQuantity - Aumenta la cantidad del producto
   */
  const handleIncreaseQuantity = () => {
    // Aumentar cantidad si no excede el stock
    if (quantity < product.stock) {
      setQuantity(quantity + 1);
    }
  };

  /**
   * handleDecreaseQuantity - Disminuye la cantidad del producto
   */
  const handleDecreaseQuantity = () => {
    // Disminuir cantidad si es mayor a 1
    if (quantity > 1) {
      setQuantity(quantity - 1);
    }
  };

  // Si el producto no existe, mostrar error
  if (!product) {
    return (
      <>
        <Header>
          <Container>
            <Row justify="space-between" align="center">
              <h2>Mi Aplicación</h2>
            </Row>
          </Container>
        </Header>
        <Container>
          <BackButton onClick={handleGoBack}>← Volver Atrás</BackButton>
          <Card>
            <Text size="18px" style={{ color: 'red', fontWeight: 'bold' }}>
              Producto no encontrado
            </Text>
          </Card>
        </Container>
      </>
    );
  }

  return (
    <>
      {/* Header con navegación */}
      <Header>
        <Container>
          <Row justify="space-between" align="center">
            <h2>Mi Aplicación</h2>
            <Nav>
              <NavLink href="/">Inicio</NavLink>
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

        {/* Información del producto */}
        <Card>
          {/* Título del producto */}
          <Title>{product.name}</Title>

          {/* Categoría del producto */}
          <div style={{ marginBottom: '15px' }}>
            <BadgeContainer>{product.category}</BadgeContainer>
            <BadgeContainer>⭐ {product.rating}</BadgeContainer>
          </div>

          {/* Descripción */}
          <Text size="18px" style={{ marginBottom: '20px' }}>
            {product.description}
          </Text>

          {/* Precio */}
          <Text size="24px" style={{ color: '#764ba2', fontWeight: 'bold', marginBottom: '20px' }}>
            Precio: {product.price}
          </Text>

          {/* Stock disponible */}
          <Text size="16px" style={{ marginBottom: '20px' }}>
            <strong>Stock disponible:</strong> {product.stock} unidades
          </Text>

          {/* Características del producto */}
          <div style={{ marginBottom: '30px' }}>
            <h3 style={{ marginBottom: '10px', color: '#667eea' }}>Características:</h3>
            <ul style={{ marginLeft: '20px' }}>
              {/* Mapear sobre las características y mostrarlas en una lista */}
              {product.features.map((feature, index) => (
                <li key={index} style={{ marginBottom: '8px' }}>
                  {feature}
                </li>
              ))}
            </ul>
          </div>

          {/* Selector de cantidad */}
          <div style={{ marginBottom: '20px' }}>
            <Text style={{ marginBottom: '10px' }}>
              <strong>Cantidad:</strong>
            </Text>
            <Row align="center">
              {/* Botón para disminuir cantidad */}
              <Button onClick={handleDecreaseQuantity} style={{ marginRight: '10px' }}>
                −
              </Button>

              {/* Display de la cantidad actual */}
              <Text style={{ minWidth: '50px', textAlign: 'center', fontSize: '18px', fontWeight: 'bold' }}>
                {quantity}
              </Text>

              {/* Botón para aumentar cantidad */}
              <Button onClick={handleIncreaseQuantity} style={{ marginLeft: '10px' }}>
                +
              </Button>
            </Row>
          </div>

          {/* Botón para agregar al carrito */}
          <Button onClick={handleAddToCart} style={{ width: '100%', marginTop: '20px' }}>
            Agregar al carrito ({product.price})
          </Button>
        </Card>

        {/* Sección adicional con información */}
        <Card>
          <h3 style={{ marginBottom: '15px', color: '#667eea' }}>Información adicional</h3>
          <Text>
            Este producto ha sido verificado por nuestro equipo de calidad.
            Si tienes preguntas sobre este artículo, no dudes en contactarnos.
          </Text>
        </Card>
      </Container>
    </>
  );
}

export default Details;
