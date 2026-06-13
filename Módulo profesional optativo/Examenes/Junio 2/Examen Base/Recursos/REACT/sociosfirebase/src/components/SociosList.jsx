/**
 * Componente principal SociosList
 * Muestra una tabla responsiva con todos los socios y acciones para CRUD
 */

import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { colors, breakpoints, shadows } from "../styles/GlobalStyle";
import SocioForm from "./SocioForm";
import SocioTable from "./SocioTable";
import {
  getAllSocios,
  deleteSocio,
  searchSociosByName
} from "../services/sociosService";

/**
 * Contenedor principal con padding y ancho máximo
 */
const Container = styled.div`
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;

  @media (max-width: ${breakpoints.tablet}) {
    padding: 15px;
  }

  @media (max-width: ${breakpoints.mobile}) {
    padding: 10px;
  }
`;

/**
 * Encabezado de la aplicación
 */
const Header = styled.div`
  background: white;
  padding: 30px;
  border-radius: 12px;
  margin-bottom: 30px;
  box-shadow: ${shadows.medium};

  @media (max-width: ${breakpoints.tablet}) {
    padding: 20px;
    margin-bottom: 20px;
  }

  @media (max-width: ${breakpoints.mobile}) {
    padding: 15px;
  }
`;

/**
 * Título principal
 */
const Title = styled.h1`
  color: ${colors.primary};
  margin-bottom: 10px;
  font-size: 2.5rem;

  @media (max-width: ${breakpoints.tablet}) {
    font-size: 2rem;
  }

  @media (max-width: ${breakpoints.mobile}) {
    font-size: 1.5rem;
  }
`;

/**
 * Descripción breve
 */
const Subtitle = styled.p`
  color: ${colors.gray};
  font-size: 1rem;
`;

/**
 * Barra de búsqueda
 */
const SearchBar = styled.div`
  margin-bottom: 30px;
  display: flex;
  gap: 10px;

  @media (max-width: ${breakpoints.tablet}) {
    flex-direction: column;
  }
`;

/**
 * Input para búsqueda
 */
const SearchInput = styled.input`
  flex: 1;
  padding: 12px 15px;
  border: 2px solid ${colors.lightGray};
  border-radius: 8px;
  font-size: 1rem;
  transition: border-color 0.3s ease;

  &:focus {
    outline: none;
    border-color: ${colors.primary};
  }

  @media (max-width: ${breakpoints.mobile}) {
    padding: 10px 12px;
    font-size: 0.95rem;
  }
`;

/**
 * Botón para limpiar búsqueda
 */
const ClearButton = styled.button`
  padding: 12px 25px;
  background: ${colors.light};
  color: ${colors.dark};
  border-radius: 8px;
  border: 2px solid ${colors.lightGray};
  cursor: pointer;
  font-weight: 600;
  transition: all 0.3s ease;

  &:hover {
    background: ${colors.lightGray};
    border-color: ${colors.dark};
  }

  @media (max-width: ${breakpoints.mobile}) {
    padding: 10px 15px;
    font-size: 0.9rem;
  }
`;

/**
 * Contenedor para el mensaje de carga
 */
const LoadingMessage = styled.div`
  text-align: center;
  color: white;
  font-size: 1.2rem;
  padding: 40px;
`;

/**
 * Contenedor para el mensaje de error
 */
const ErrorMessage = styled.div`
  background: ${colors.danger};
  color: white;
  padding: 15px 20px;
  border-radius: 8px;
  margin-bottom: 20px;
  box-shadow: ${shadows.medium};
`;

/**
 * Componente principal SociosList
 * Maneja el estado de los socios y proporciona funcionalidad de búsqueda
 */
const SociosList = () => {
  // Estado para almacenar todos los socios
  const [socios, setSocios] = useState([]);
  
  // Estado para almacenar los socios filtrados por búsqueda
  const [filteredSocios, setFilteredSocios] = useState([]);
  
  // Estado para controlar si se está cargando
  const [loading, setLoading] = useState(true);
  
  // Estado para almacenar errores
  const [error, setError] = useState(null);
  
  // Estado para el término de búsqueda
  const [searchTerm, setSearchTerm] = useState("");
  
  // Estado para controlar la visualización del formulario
  const [showForm, setShowForm] = useState(false);
  
  // Estado para el socio en edición (null si se crea uno nuevo)
  const [editingSocio, setEditingSocio] = useState(null);

  /**
   * Efecto que se ejecuta al montar el componente para cargar los socios
   */
  useEffect(() => {
    // Cargar los socios al montar el componente
    loadSocios();
  }, []);

  /**
   * Efecto que filtra los socios cuando cambia el término de búsqueda
   */
  useEffect(() => {
    // Si no hay término de búsqueda, mostrar todos los socios
    if (searchTerm === "") {
      setFilteredSocios(socios);
    } else {
      // Filtrar socios por nombre que contenga el término de búsqueda
      const filtered = socios.filter(socio =>
        socio.nombre.toLowerCase().includes(searchTerm.toLowerCase()) ||
        socio.email.toLowerCase().includes(searchTerm.toLowerCase())
      );
      setFilteredSocios(filtered);
    }
  }, [searchTerm, socios]);

  /**
   * Función para cargar todos los socios desde la base de datos
   */
  const loadSocios = async () => {
    try {
      setLoading(true);
      setError(null);
      const data = await getAllSocios();
      setSocios(data);
    } catch (err) {
      // Mostrar error si algo falla
      setError(err.message);
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  /**
   * Función para manejar la eliminación de un socio
   * @param {string} socioId - ID del socio a eliminar
   */
  const handleDeleteSocio = async (socioId) => {
    // Confirmación antes de eliminar
    if (window.confirm("¿Estás seguro de que deseas eliminar este socio?")) {
      try {
        // Eliminar el socio desde la base de datos
        await deleteSocio(socioId);
        // Recargar la lista de socios
        await loadSocios();
      } catch (err) {
        setError(err.message);
        console.error(err);
      }
    }
  };

  /**
   * Función para manejar la edición de un socio
   * @param {Object} socio - Objeto socio a editar
   */
  const handleEditSocio = (socio) => {
    // Establecer el socio en edición y mostrar el formulario
    setEditingSocio(socio);
    setShowForm(true);
  };

  /**
   * Función para manejar el cierre del formulario
   */
  const handleFormClose = () => {
    // Ocultar el formulario y limpiar el socio en edición
    setShowForm(false);
    setEditingSocio(null);
  };

  /**
   * Función para manejar después de guardar un socio
   */
  const handleSocioSaved = async () => {
    // Cerrar el formulario y recargar los socios
    handleFormClose();
    await loadSocios();
  };

  /**
   * Función para limpiar la búsqueda
   */
  const handleClearSearch = () => {
    setSearchTerm("");
  };

  // Mostrar mensaje de carga mientras se obtienen los datos
  if (loading) {
    return <LoadingMessage>Cargando socios...</LoadingMessage>;
  }

  // Renderizar la interfaz principal
  return (
    <Container>
      {/* Encabezado con título */}
      <Header>
        <Title>Gestión de Socios</Title>
        <Subtitle>
          Sistema completo para administrar información de socios
        </Subtitle>
      </Header>

      {/* Mostrar mensaje de error si existe */}
      {error && <ErrorMessage>⚠️ Error: {error}</ErrorMessage>}

      {/* Barra de búsqueda */}
      <SearchBar>
        <SearchInput
          type="text"
          placeholder="Buscar por nombre o email..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
        <ClearButton onClick={handleClearSearch}>Limpiar</ClearButton>
      </SearchBar>

      {/* Mostrar el formulario si está activado */}
      {showForm && (
        <SocioForm
          socio={editingSocio}
          onClose={handleFormClose}
          onSaved={handleSocioSaved}
        />
      )}

      {/* Mostrar tabla de socios */}
      <SocioTable
        socios={filteredSocios}
        onEdit={handleEditSocio}
        onDelete={handleDeleteSocio}
        onAddNew={() => {
          setEditingSocio(null);
          setShowForm(true);
        }}
      />
    </Container>
  );
};

export default SociosList;
