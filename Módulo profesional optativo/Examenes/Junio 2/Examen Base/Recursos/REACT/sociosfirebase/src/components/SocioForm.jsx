/**
 * Componente SocioForm
 * Formulario para crear y editar socios
 */

import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { colors, breakpoints, shadows } from "../styles/GlobalStyle";
import { createSocio, updateSocio } from "../services/sociosService";

/**
 * Overlay oscuro del modal
 */
const ModalOverlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
  padding: 20px;
`;

/**
 * Contenedor del modal
 */
const ModalContent = styled.div`
  background: white;
  border-radius: 12px;
  padding: 40px;
  max-width: 500px;
  width: 100%;
  box-shadow: ${shadows.xl};
  max-height: 90vh;
  overflow-y: auto;

  @media (max-width: ${breakpoints.tablet}) {
    padding: 30px;
    max-width: 90%;
  }

  @media (max-width: ${breakpoints.mobile}) {
    padding: 20px;
    max-width: 95%;
  }
`;

/**
 * Título del formulario
 */
const FormTitle = styled.h2`
  color: ${colors.primary};
  margin-bottom: 30px;
  font-size: 1.8rem;

  @media (max-width: ${breakpoints.mobile}) {
    font-size: 1.5rem;
    margin-bottom: 20px;
  }
`;

/**
 * Grupo de campo del formulario
 */
const FormGroup = styled.div`
  margin-bottom: 20px;

  @media (max-width: ${breakpoints.mobile}) {
    margin-bottom: 15px;
  }
`;

/**
 * Etiqueta del campo
 */
const Label = styled.label`
  display: block;
  margin-bottom: 8px;
  color: ${colors.dark};
  font-weight: 600;
  font-size: 0.95rem;
`;

/**
 * Input del formulario
 */
const Input = styled.input`
  width: 100%;
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
 * Textarea del formulario
 */
const Textarea = styled.textarea`
  width: 100%;
  padding: 12px 15px;
  border: 2px solid ${colors.lightGray};
  border-radius: 8px;
  font-size: 1rem;
  resize: vertical;
  min-height: 100px;
  transition: border-color 0.3s ease;
  font-family: inherit;

  &:focus {
    outline: none;
    border-color: ${colors.primary};
  }

  @media (max-width: ${breakpoints.mobile}) {
    padding: 10px 12px;
    font-size: 0.95rem;
    min-height: 80px;
  }
`;

/**
 * Contenedor de botones
 */
const ButtonGroup = styled.div`
  display: flex;
  gap: 10px;
  margin-top: 30px;

  @media (max-width: ${breakpoints.mobile}) {
    flex-direction: column;
    gap: 10px;
    margin-top: 20px;
  }
`;

/**
 * Botón enviar/guardar
 */
const SubmitButton = styled.button`
  flex: 1;
  padding: 12px 20px;
  background: ${colors.primary};
  color: white;
  border: none;
  border-radius: 8px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;

  &:hover {
    background: ${colors.secondary};
    transform: translateY(-2px);
    box-shadow: ${shadows.medium};
  }

  &:disabled {
    opacity: 0.6;
    cursor: not-allowed;
    transform: none;
  }

  @media (max-width: ${breakpoints.mobile}) {
    padding: 12px 15px;
  }
`;

/**
 * Botón cancelar
 */
const CancelButton = styled.button`
  flex: 1;
  padding: 12px 20px;
  background: ${colors.light};
  color: ${colors.dark};
  border: 2px solid ${colors.lightGray};
  border-radius: 8px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;

  &:hover {
    background: ${colors.lightGray};
    border-color: ${colors.dark};
  }

  @media (max-width: ${breakpoints.mobile}) {
    padding: 12px 15px;
  }
`;

/**
 * Mensaje de estado
 */
const StatusMessage = styled.div`
  padding: 10px 15px;
  border-radius: 8px;
  margin-bottom: 20px;
  font-weight: 500;

  &.success {
    background: ${colors.success};
    color: white;
  }

  &.error {
    background: ${colors.danger};
    color: white;
  }
`;

/**
 * Componente SocioForm
 * Formulario para crear o editar un socio
 * 
 * @param {Object} props - Props del componente
 * @param {Object} props.socio - Socio a editar (null para crear nuevo)
 * @param {Function} props.onClose - Función para cerrar el formulario
 * @param {Function} props.onSaved - Función a ejecutar después de guardar
 */
const SocioForm = ({ socio, onClose, onSaved }) => {
  // Estado para los datos del formulario
  const [formData, setFormData] = useState({
    first_name: "",
    last_name: "",
    email: "",
    gender: ""
  });

  // Estado para mostrar mensaje de carga
  const [loading, setLoading] = useState(false);

  // Estado para mensajes de error/éxito
  const [message, setMessage] = useState("");

  // Estado para el tipo de mensaje (error o success)
  const [messageType, setMessageType] = useState("");

  /**
   * Efecto para llenar el formulario cuando se edita un socio existente
   */
  useEffect(() => {
    if (socio) {
      // Si estamos editando, llenar el formulario con los datos del socio
      setFormData({
        first_name: socio.first_name || "",
        last_name: socio.last_name || "",
        email: socio.email || "",
        gender: socio.gender || ""
      });
    }
  }, [socio]);

  /**
   * Manejar cambios en los inputs
   * @param {Object} e - Evento del input
   */
  const handleChange = (e) => {
    const { name, value } = e.target;
    // Actualizar el estado del formulario
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  /**
   * Manejar el envío del formulario
   * @param {Object} e - Evento del formulario
   */
  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validar que los campos requeridos no estén vacíos
    if (!formData.first_name.trim() || !formData.email.trim()) {
      setMessage("El nombre y email son requeridos");
      setMessageType("error");
      return;
    }

    try {
      setLoading(true);
      setMessage("");

      if (socio && socio.id) {
        // Si estamos editando, actualizar el socio
        await updateSocio(socio.id, formData);
        setMessage("Socio actualizado correctamente");
      } else {
        // Si estamos creando, crear un nuevo socio
        await createSocio(formData);
        setMessage("Socio creado correctamente");
      }

      setMessageType("success");

      // Esperar 1 segundo y luego cerrar el formulario
      setTimeout(() => {
        onSaved();
      }, 1000);
    } catch (error) {
      // Mostrar error si algo falla
      setMessage(error.message || "Error al guardar el socio");
      setMessageType("error");
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  // Renderizar el formulario
  return (
    <ModalOverlay onClick={onClose}>
      {/* Detener la propagación del click en el contenedor */}
      <ModalContent onClick={(e) => e.stopPropagation()}>
        {/* Título del formulario */}
        <FormTitle>
          {socio && socio.id ? "✏️ Editar Socio" : "➕ Crear Nuevo Socio"}
        </FormTitle>

        {/* Mostrar mensaje de estado si existe */}
        {message && (
          <StatusMessage className={messageType}>
            {message}
          </StatusMessage>
        )}

        {/* Formulario */}
        <form onSubmit={handleSubmit}>
          {/* Campo de nombre */}
          <FormGroup>
            <Label htmlFor="nombre">Nombre *</Label>
            <Input
              id="first_name"
              type="text"
              name="first_name"
              value={formData.first_name}
              onChange={handleChange}
              placeholder="Ej: Juan "
              required
            />
          </FormGroup>

           {/* Campo de apellido */}
          <FormGroup>
            <Label htmlFor="apellido">Apellido</Label>
            <Input
              id="last_name"
              type="text"
              name="last_name"
              value={formData.last_name}
              onChange={handleChange}
              placeholder="Ej: Pérez"
              required
            />
          </FormGroup>

          {/* Campo de email */}
          <FormGroup>
            <Label htmlFor="email">Email *</Label>
            <Input
              id="email"
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              placeholder="Ej: juan@example.com"
              required
            />
          </FormGroup>

          {/* Campo de genero */}
          <FormGroup>
            <Label htmlFor="genero">Género</Label>
            <Input
              id="gender"
              type="text"
              name="gender"
              value={formData.gender}
              onChange={handleChange}
              placeholder="Ej: Female"
            />
          </FormGroup>

          {/* Botones de acción */}
          <ButtonGroup>
            <SubmitButton type="submit" disabled={loading}>
              {loading ? "Guardando..." : socio && socio.id ? "Actualizar" : "Crear"}
            </SubmitButton>
            <CancelButton type="button" onClick={onClose} disabled={loading}>
              Cancelar
            </CancelButton>
          </ButtonGroup>
        </form>
      </ModalContent>
    </ModalOverlay>
  );
};

export default SocioForm;
