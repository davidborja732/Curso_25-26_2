// Importar hooks de React para manejo de estado
import { useState } from 'react';
// Importar styled-components para crear estilos
import styled from 'styled-components';
// Importar el componente Input reutilizable
import Input from './Input';

// Contenedor principal que ocupa el 70% de la pantalla
const FormContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 20px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
`;

// Contenedor del formulario con ancho del 70%
const FormBox = styled.div`
  width: 70%;
  max-width: 600px;
  background: white;
  border-radius: 12px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
  padding: 40px;

  // Responsivo en tablets
  @media (max-width: 768px) {
    width: 90%;
    padding: 30px 20px;
    max-width: 100%;
  }

  // Responsivo en móviles
  @media (max-width: 480px) {
    width: 95%;
    padding: 20px 15px;
  }
`;

// Título del formulario
const Title = styled.h1`
  text-align: center;
  color: #333;
  margin-bottom: 30px;
  font-size: 28px;
  font-weight: 700;

  // Responsivo en tablets
  @media (max-width: 768px) {
    font-size: 24px;
    margin-bottom: 25px;
  }

  // Responsivo en móviles
  @media (max-width: 480px) {
    font-size: 20px;
    margin-bottom: 20px;
  }
`;

// Contenedor para dos inputs en una fila (nombre y apellidos)
const Row = styled.div`
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;

  // En tablets y móviles, un input por fila
  @media (max-width: 768px) {
    grid-template-columns: 1fr;
    gap: 15px;
  }
`;

// Botón de envío del formulario
const SubmitButton = styled.button`
  width: 100%;
  padding: 12px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  margin-top: 20px;
  transition: all 0.3s ease;

  // Efecto hover
  &:hover {
    transform: translateY(-2px);
    box-shadow: 0 5px 20px rgba(102, 126, 234, 0.4);
  }

  // Efecto activo
  &:active {
    transform: translateY(0);
  }

  // Deshabilitado cuando hay errores
  &:disabled {
    opacity: 0.6;
    cursor: not-allowed;
    transform: none;
  }

  // Responsivo en móviles
  @media (max-width: 480px) {
    padding: 10px;
    font-size: 14px;
  }
`;

// Contenedor para mostrar mensajes de éxito
const SuccessMessage = styled.div`
  background: #d4edda;
  color: #155724;
  padding: 15px;
  border-radius: 8px;
  margin-bottom: 20px;
  text-align: center;
  font-weight: 500;
  border: 1px solid #c3e6cb;
`;

/**
 * Función para validar que la contraseña sea robusta
 * Requisitos:
 * - Mínimo 8 caracteres
 * - Al menos una mayúscula
 * - Al menos una minúscula
 * - Al menos un número
 * - Al menos un carácter especial
 * @param {string} password - Contraseña a validar
 * @returns {boolean} True si la contraseña es robusta
 */
const isStrongPassword = (password) => {
  const strongPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
  return strongPasswordRegex.test(password);
};

/**
 * Función para validar formato de email
 * @param {string} email - Email a validar
 * @returns {boolean} True si el email es válido
 */
const isValidEmail = (email) => {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
};

/**
 * Función para validar teléfono
 * Acepta formatos: 123456789, +34123456789, 034123456789
 * @param {string} phone - Teléfono a validar
 * @returns {boolean} True si el teléfono es válido
 */
const isValidPhone = (phone) => {
  const phoneRegex = /^(\+?\d{1,3})?(\s?|-?)\d{6,14}$/;
  return phoneRegex.test(phone.replace(/\s/g, ''));
};

/**
 * Componente del Formulario de Registro
 * Gestiona estado del formulario, validación y envío
 * @returns {JSX.Element} Formulario renderizado
 */
const FormularioRegistro = () => {
  // Estado del formulario con todos los campos inicializados vacíos
  const [formData, setFormData] = useState({
    nombre: '',
    apellidos: '',
    email: '',
    password: '',
    repeatPassword: '',
    telefono: '',
  });

  // Estado para almacenar errores de validación
  const [errors, setErrors] = useState({});

  // Estado para indicar si el formulario fue enviado exitosamente
  const [submitted, setSubmitted] = useState(false);

  /**
   * Manejador de cambios en los inputs
   * Actualiza el estado del formulario cuando cambia un valor
   * @param {Object} e - Evento del input
   */
  const handleChange = (e) => {
    const { name, value } = e.target;
    // Actualizar el campo específico en el estado
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
    // Limpiar el error del campo cuando el usuario empieza a escribir
    if (errors[name]) {
      setErrors((prev) => ({
        ...prev,
        [name]: '',
      }));
    }
  };

  /**
   * Función principal de validación del formulario
   * Valida todos los campos y retorna un objeto con errores
   * @returns {Object} Objeto con errores por campo
   */
  const validateForm = () => {
    const newErrors = {};

    // Validar nombre: requerido y sin números
    if (!formData.nombre.trim()) {
      newErrors.nombre = 'El nombre es requerido';
    } else if (/\d/.test(formData.nombre)) {
      newErrors.nombre = 'El nombre no puede contener números';
    }

    // Validar apellidos: requerido y sin números
    if (!formData.apellidos.trim()) {
      newErrors.apellidos = 'Los apellidos son requeridos';
    } else if (/\d/.test(formData.apellidos)) {
      newErrors.apellidos = 'Los apellidos no pueden contener números';
    }

    // Validar email: requerido y formato válido
    if (!formData.email.trim()) {
      newErrors.email = 'El email es requerido';
    } else if (!isValidEmail(formData.email)) {
      newErrors.email = 'El email no es válido';
    }

    // Validar contraseña: requerida y robusta
    if (!formData.password) {
      newErrors.password = 'La contraseña es requerida';
    } else if (!isStrongPassword(formData.password)) {
      newErrors.password =
        'La contraseña debe tener mínimo 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial (@$!%*?&)';
    }

    // Validar repetición de contraseña: requerida y debe coincidir
    if (!formData.repeatPassword) {
      newErrors.repeatPassword = 'Debes repetir la contraseña';
    } else if (formData.password !== formData.repeatPassword) {
      newErrors.repeatPassword = 'Las contraseñas no coinciden';
    }

    // Validar teléfono: requerido y formato válido
    if (!formData.telefono.trim()) {
      newErrors.telefono = 'El teléfono es requerido';
    } else if (!isValidPhone(formData.telefono)) {
      newErrors.telefono = 'El teléfono no es válido';
    }

    return newErrors;
  };

  /**
   * Manejador del envío del formulario
   * Valida los datos y si son correctos los envía
   * @param {Object} e - Evento del formulario
   */
  const handleSubmit = (e) => {
    // Prevenir el comportamiento por defecto del formulario
    e.preventDefault();

    // Validar el formulario
    const newErrors = validateForm();

    // Si hay errores, actualizar estado y no enviar
    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
      setSubmitted(false);
      return;
    }

    // Si no hay errores, marcar como enviado
    setSubmitted(true);
    
    // En una aplicación real, aquí se enviarían los datos al servidor
    console.log('Formulario enviado:', {
      nombre: formData.nombre,
      apellidos: formData.apellidos,
      email: formData.email,
      telefono: formData.telefono,
      // NOTA: Nunca enviar la contraseña tal cual a través de logs en producción
    });

    // Limpiar el formulario después de 3 segundos
    setTimeout(() => {
      setFormData({
        nombre: '',
        apellidos: '',
        email: '',
        password: '',
        repeatPassword: '',
        telefono: '',
      });
      setSubmitted(false);
    }, 3000);
  };

  return (
    <FormContainer>
      <FormBox>
        {/* Mostrar mensaje de éxito si el formulario fue enviado */}
        {submitted && (
          <SuccessMessage>
            ✓ ¡Registro completado exitosamente!
          </SuccessMessage>
        )}

        {/* Título del formulario */}
        <Title>Crear Cuenta</Title>

        {/* Formulario */}
        <form onSubmit={handleSubmit}>
          {/* Fila con nombre y apellidos */}
          <Row>
            <Input
              id="nombre"
              name="nombre"
              label="Nombre"
              type="text"
              placeholder="Ingresa tu nombre"
              value={formData.nombre}
              onChange={handleChange}
              error={errors.nombre}
            />
            <Input
              id="apellidos"
              name="apellidos"
              label="Apellidos"
              type="text"
              placeholder="Ingresa tus apellidos"
              value={formData.apellidos}
              onChange={handleChange}
              error={errors.apellidos}
            />
          </Row>

         

          {/* Contraseña */}
          <Row>
            <Input
              id="password"
              name="password"
              label="Contraseña"
              type="password"
              placeholder="Crea una contraseña robusta"
              value={formData.password}
              onChange={handleChange}
              error={errors.password}
            />

            {/* Repetir contraseña */}
            <Input
              id="repeatPassword"
              name="repeatPassword"
              label="Repetir Contraseña"
              type="password"
              placeholder="Repite tu contraseña"
              value={formData.repeatPassword}
              onChange={handleChange}
              error={errors.repeatPassword}
            />
          </Row>


          {/* Email */}
          <Input
            id="email"
            name="email"
            label="Correo Electrónico"
            type="email"
            placeholder="ejemplo@correo.com"
            value={formData.email}
            onChange={handleChange}
            error={errors.email}
          />

          {/* Teléfono */}
          <Input
            id="telefono"
            name="telefono"
            label="Teléfono"
            type="tel"
            placeholder="+34 123456789"
            value={formData.telefono}
            onChange={handleChange}
            error={errors.telefono}
          />

          {/* Botón de envío */}
          <SubmitButton type="submit">
            Registrarse
          </SubmitButton>
        </form>
      </FormBox>
    </FormContainer>
  );
};

export default FormularioRegistro;
