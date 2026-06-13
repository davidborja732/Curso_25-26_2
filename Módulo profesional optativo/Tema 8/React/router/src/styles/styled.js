// Importar styled para crear componentes estilizados
import styled from 'styled-components';

/**
 * Container - Contenedor principal responsive
 * Usa media queries para adaptarse a diferentes tamaños de pantalla
 */
export const Container = styled.div`
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;

  /* Para tablets */
  @media (max-width: 768px) {
    padding: 15px;
  }

  /* Para móviles */
  @media (max-width: 480px) {
    padding: 10px;
  }
`;

/**
 * Header - Encabezado de la página con navegación
 * Tiene fondo oscuro y está fijo en la parte superior
 */
export const Header = styled.header`
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 20px 0;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 30px;

  /* Para móviles */
  @media (max-width: 480px) {
    margin-bottom: 20px;
    padding: 15px 0;
  }
`;

/**
 * Nav - Barra de navegación
 * Contiene los links de navegación entre pantallas
 */
export const Nav = styled.nav`
  display: flex;
  gap: 20px;
  align-items: center;
  flex-wrap: wrap;

  /* Para móviles */
  @media (max-width: 480px) {
    gap: 10px;
    justify-content: center;
  }
`;

/**
 * NavLink - Estilo para los links de navegación
 * Tiene efectos hover y transiciones suaves
 */
export const NavLink = styled.a`
  color: white;
  text-decoration: none;
  font-weight: 500;
  padding: 8px 16px;
  border-radius: 4px;
  transition: all 0.3s ease;
  cursor: pointer;

  &:hover {
    background-color: rgba(255, 255, 255, 0.2);
    transform: translateY(-2px);
  }

  &:active {
    transform: translateY(0);
  }

  /* Para móviles */
  @media (max-width: 480px) {
    padding: 6px 12px;
    font-size: 14px;
  }
`;

/**
 * Title - Título principal de las páginas
 * Texto grande y con color degradado
 */
export const Title = styled.h1`
  font-size: 2.5rem;
  margin-bottom: 20px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;

  /* Para tablets */
  @media (max-width: 768px) {
    font-size: 2rem;
    margin-bottom: 15px;
  }

  /* Para móviles */
  @media (max-width: 480px) {
    font-size: 1.5rem;
    margin-bottom: 10px;
  }
`;

/**
 * Card - Componente de tarjeta para mostrar información
 * Tiene sombra y bordes redondeados
 */
export const Card = styled.div`
  background: white;
  border-radius: 8px;
  padding: 24px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;

  &:hover {
    transform: translateY(-4px);
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.15);
  }

  /* Para tablets */
  @media (max-width: 768px) {
    padding: 18px;
    margin-bottom: 15px;
  }

  /* Para móviles */
  @media (max-width: 480px) {
    padding: 12px;
    margin-bottom: 10px;
  }
`;

/**
 * CardGrid - Grid responsive para mostrar múltiples tarjetas
 * Cambia el número de columnas según el tamaño de la pantalla
 */
export const CardGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 20px;
  margin-bottom: 20px;

  /* Para tablets */
  @media (max-width: 768px) {
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
    gap: 15px;
  }

  /* Para móviles */
  @media (max-width: 480px) {
    grid-template-columns: 1fr;
    gap: 10px;
  }
`;

/**
 * Button - Botón personalizado con efectos
 * Tiene color de fondo degradado y efectos hover
 */
export const Button = styled.button`
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 12px 24px;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
  margin: 10px 10px 10px 0;

  &:hover {
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(102, 126, 234, 0.4);
  }

  &:active {
    transform: translateY(0);
  }

  &:disabled {
    opacity: 0.6;
    cursor: not-allowed;
  }

  /* Para móviles */
  @media (max-width: 480px) {
    padding: 10px 16px;
    font-size: 14px;
    margin: 8px 8px 8px 0;
  }
`;

/**
 * BackButton - Botón especial para volver atrás
 * Tiene un estilo secundario
 */
export const BackButton = styled(Button)`
  background: #6c757d;
 
  &:hover {
    box-shadow: 0 4px 12px rgba(108, 117, 125, 0.4);
  }
`;

/**
 * Row - Contenedor flex para alinear elementos horizontalmente
 * Responsive y con gap configurable
 */
export const Row = styled.div`
  display: flex;
  justify-content: ${props => props.justify || 'flex-start'};
  align-items: ${props => props.align || 'center'};
  gap: 20px;
  flex-wrap: wrap;

  /* Para móviles */
  @media (max-width: 480px) {
    gap: 10px;
  }
`;

/**
 * Text - Componente de texto con estilos personalizables
 */
export const Text = styled.p`
  font-size: ${props => props.size || '16px'};
  color: ${props => props.color || '#333'};
  margin-bottom: 10px;
  line-height: 1.6;

  /* Para móviles */
  @media (max-width: 480px) {
    font-size: ${props => {
      if (props.size === '16px') return '14px';
      if (props.size === '18px') return '16px';
      return props.size;
    }};
  }
`;

/**
 * BadgeContainer - Contenedor para mostrar información de forma destacada
 */
export const BadgeContainer = styled.div`
  display: inline-block;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 4px 12px;
  border-radius: 20px;
  font-size: 12px;
  font-weight: 600;
  margin: 5px;
`;

/**
 * LoadingSpinner - Contenedor para el spinner de carga
 */
export const LoadingSpinner = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 300px;
  font-size: 18px;
  color: #667eea;
  font-weight: 600;
`;
