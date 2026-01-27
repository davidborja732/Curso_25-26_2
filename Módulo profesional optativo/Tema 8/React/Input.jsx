// Importar styled-components para crear estilos CSS-in-JS
import styled from 'styled-components';

// Contenedor del input con estilos responsivos
const InputContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
  width: 100%;
`;

// Etiqueta del input con estilos
const Label = styled.label`
  font-size: 14px;
  font-weight: 600;
  color: #333;
  margin-bottom: 8px;
  display: block;

  // Hacer responsivo en pantallas pequeñas
  @media (max-width: 768px) {
    font-size: 13px;
  }
`;

// Elemento de entrada con estilos base
const StyledInput = styled.input`
  padding: 12px 15px;
  border: 2px solid #ddd;
  border-radius: 8px;
  font-size: 14px;
  font-family: inherit;
  transition: all 0.3s ease;
  width: 100%;
  box-sizing: border-box;

  // Estilos cuando el input está enfocado
  &:focus {
    outline: none;
    border-color: #4a90e2;
    box-shadow: 0 0 5px rgba(74, 144, 226, 0.3);
    background-color: #f9f9f9;
  }

  // Cambiar estilos según el estado de error
  border-color: ${(props) => (props.error ? '#e74c3c' : '#ddd')};
  
  // Estilos en pantallas pequeñas
  @media (max-width: 768px) {
    padding: 10px 12px;
    font-size: 13px;
  }
`;

// Mensaje de error que aparece debajo del input
const ErrorMessage = styled.span`
  color: #e74c3c;
  font-size: 12px;
  margin-top: 5px;
  display: block;
  min-height: 16px;

  // Estilos responsivos
  @media (max-width: 768px) {
    font-size: 11px;
  }
`;

/**
 * Componente Input reutilizable
 * @param {string} label - Etiqueta del input
 * @param {string} type - Tipo de input (text, email, password, etc.)
 * @param {string} placeholder - Texto de ayuda dentro del input
 * @param {string} value - Valor actual del input
 * @param {function} onChange - Función que se ejecuta al cambiar el valor
 * @param {string} error - Mensaje de error a mostrar
 * @param {string} name - Nombre del input
 * @param {string} id - ID único del input
 * @returns {JSX.Element} Componente Input renderizado
 */
const Input = ({ 
  label, 
  type = 'text', 
  placeholder = '', 
  value = '', 
  onChange, 
  error = '', 
  name = '', 
  id = '' 
}) => {
  return (
    <InputContainer>
      {/* Renderizar la etiqueta si se proporciona */}
      {label && <Label htmlFor={id}>{label}</Label>}
      
      {/* Input principal */}
      <StyledInput
        id={id}
        name={name}
        type={type}
        placeholder={placeholder}
        value={value}
        onChange={onChange}
        error={error}
        // Accesibilidad: añadir atributo aria-invalid cuando hay error
        aria-invalid={error ? 'true' : 'false'}
        // Accesibilidad: describir el error si existe
        aria-describedby={error ? `${id}-error` : undefined}
      />
      
      {/* Mostrar mensaje de error si existe */}
      {error && <ErrorMessage id={`${id}-error`}>{error}</ErrorMessage>}
    </InputContainer>
  );
};

export default Input;