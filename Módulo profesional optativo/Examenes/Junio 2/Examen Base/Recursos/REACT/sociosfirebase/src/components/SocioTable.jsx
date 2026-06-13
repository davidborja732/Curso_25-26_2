/**
 * Componente SocioTable
 * Tabla responsiva que muestra los socios y acciones CRUD
 */

import React from "react";
import styled from "styled-components";
import { colors, breakpoints, shadows } from "../styles/GlobalStyle";

/**
 * Contenedor de la tabla con scroll horizontal en móviles
 */
const TableContainer = styled.div`
  background: white;
  border-radius: 12px;
  box-shadow: ${shadows.medium};
  overflow-x: auto;

  @media (max-width: ${breakpoints.tablet}) {
    overflow-x: auto;
  }
`;

/**
 * Tabla
 */
const Table = styled.table`
  width: 100%;
  border-collapse: collapse;

  @media (max-width: ${breakpoints.tablet}) {
    font-size: 0.9rem;
  }

  @media (max-width: ${breakpoints.mobile}) {
    font-size: 0.8rem;
  }
`;

/**
 * Header de la tabla
 */
const TableHead = styled.thead`
  background: linear-gradient(135deg, ${colors.primary} 0%, ${colors.secondary} 100%);
  color: white;
`;

/**
 * Fila del header
 */
const HeaderRow = styled.tr`
  border-bottom: 3px solid ${colors.primary};
`;

/**
 * Celda del header
 */
const HeaderCell = styled.th`
  padding: 15px;
  text-align: left;
  font-weight: 600;
  font-size: 0.95rem;

  @media (max-width: ${breakpoints.mobile}) {
    padding: 10px;
    font-size: 0.8rem;
  }
`;

/**
 * Body de la tabla
 */
const TableBody = styled.tbody``;

/**
 * Fila del cuerpo de la tabla
 */
const BodyRow = styled.tr`
  border-bottom: 1px solid ${colors.lightGray};
  transition: background-color 0.3s ease;

  &:hover {
    background-color: ${colors.lightGray};
  }

  @media (max-width: ${breakpoints.mobile}) {
    display: block;
    margin-bottom: 15px;
    border: 1px solid ${colors.lightGray};
    border-radius: 8px;
    background: white;
  }
`;

/**
 * Celda del cuerpo de la tabla
 */
const BodyCell = styled.td`
  padding: 15px;
  color: ${colors.dark};

  @media (max-width: ${breakpoints.mobile}) {
    padding: 12px;
    display: block;
    text-align: left;
    border-bottom: 1px solid ${colors.lightGray};

    &::before {
      content: attr(data-label);
      font-weight: 600;
      display: block;
      margin-bottom: 5px;
      color: ${colors.primary};
      font-size: 0.85rem;
    }

    &:last-child {
      border-bottom: none;
    }
  }
`;

/**
 * Contenedor de acciones
 */
const ActionsCell = styled(BodyCell)`
  display: flex;
  gap: 10px;

  @media (max-width: ${breakpoints.mobile}) {
    display: flex;
    gap: 8px;
    justify-content: flex-start;

    &::before {
      display: none;
    }
  }
`;

/**
 * Botón de acción
 */
const ActionButton = styled.button`
  padding: 8px 12px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-weight: 600;
  font-size: 0.9rem;
  transition: all 0.3s ease;

  &:hover {
    transform: translateY(-2px);
    box-shadow: ${shadows.small};
  }

  @media (max-width: ${breakpoints.mobile}) {
    padding: 6px 10px;
    font-size: 0.8rem;
  }
`;

/**
 * Botón editar
 */
const EditButton = styled(ActionButton)`
  background: ${colors.info};
  color: white;

  &:hover {
    background: #2563eb;
  }
`;

/**
 * Botón eliminar
 */
const DeleteButton = styled(ActionButton)`
  background: ${colors.danger};
  color: white;

  &:hover {
    background: #dc2626;
  }
`;

/**
 * Botón para agregar nuevo socio
 */
const AddButton = styled.button`
  display: inline-block;
  margin-top: 20px;
  padding: 12px 25px;
  background: linear-gradient(135deg, ${colors.primary} 0%, ${colors.secondary} 100%);
  color: white;
  border: none;
  border-radius: 8px;
  font-weight: 600;
  cursor: pointer;
  font-size: 1rem;
  transition: all 0.3s ease;

  &:hover {
    transform: translateY(-2px);
    box-shadow: ${shadows.medium};
  }

  @media (max-width: ${breakpoints.mobile}) {
    width: 100%;
    padding: 12px 15px;
    margin-top: 15px;
  }
`;

/**
 * Mensaje cuando no hay socios
 */
const EmptyMessage = styled.div`
  text-align: center;
  padding: 40px;
  color: ${colors.gray};
  background: white;
  border-radius: 12px;

  h3 {
    color: ${colors.dark};
    margin-bottom: 10px;
  }

  p {
    margin-bottom: 20px;
  }

  @media (max-width: ${breakpoints.mobile}) {
    padding: 30px 20px;
  }
`;

/**
 * Componente SocioTable
 * Muestra una tabla responsiva con los socios
 * 
 * @param {Object} props - Props del componente
 * @param {Array} props.socios - Array de socios a mostrar
 * @param {Function} props.onEdit - Función para editar un socio
 * @param {Function} props.onDelete - Función para eliminar un socio
 * @param {Function} props.onAddNew - Función para agregar un nuevo socio
 */
const SocioTable = ({ socios, onEdit, onDelete, onAddNew }) => {
  // Si no hay socios, mostrar mensaje vacío
  if (socios.length === 0) {
    return (
      <EmptyMessage>
        <h3>📋 Sin Socios</h3>
        <p>No hay socios registrados. ¡Comienza agregando uno nuevo!</p>
        <AddButton onClick={onAddNew}>➕ Agregar Nuevo Socio</AddButton>
      </EmptyMessage>
    );
  }

  // Renderizar la tabla
  return (
    <div>
      <TableContainer>
        <Table>
          {/* Encabezado de la tabla */}
          <TableHead>
            <HeaderRow>
              <HeaderCell>Nombre</HeaderCell>
              <HeaderCell>Apellido</HeaderCell>
              <HeaderCell>Email</HeaderCell>
              <HeaderCell>Género</HeaderCell>

              <HeaderCell>Acciones</HeaderCell>
            </HeaderRow>
          </TableHead>

          {/* Cuerpo de la tabla */}
          <TableBody>
            {socios.map(socio => (
              <BodyRow key={socio.id}>
                {/* Celda de nombre */}
                <BodyCell data-label="Nombre">
                  {socio.first_name}
                </BodyCell>

                 {/* Celda de apllido */}
                <BodyCell data-label="Apellido">
                  {socio.last_name}
                </BodyCell>

                {/* Celda de email */}
                <BodyCell data-label="Email">
                  <a href={`mailto:${socio.email}`}>
                    {socio.email}
                  </a>
                </BodyCell>

                {/* Celda de genero */}
                <BodyCell data-label="Género">
                  {socio.gender || "-"}
                </BodyCell>

                {/* Celda de acciones */}
                <ActionsCell data-label="Acciones">
                  <EditButton onClick={() => onEdit(socio)}>
                    ✏️ Editar
                  </EditButton>
                  <DeleteButton onClick={() => onDelete(socio.id)}>
                    🗑️ Eliminar
                  </DeleteButton>
                </ActionsCell>
              </BodyRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      {/* Botón para agregar nuevo socio */}
      <AddButton onClick={onAddNew}>➕ Agregar Nuevo Socio</AddButton>
    </div>
  );
};

export default SocioTable;
