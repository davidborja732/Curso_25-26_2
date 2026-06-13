//import React from 'react';
import styled from 'styled-components';

const colores= {
  borde: "rgb(191, 253, 205)",
  error: "#e7"
}

const Formulario = styled.form`
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    gap: 100px;
    @media (max-width: 1200px){
        grid-template-columns: 1fr 1fr 1fr;
    }
    @media (max-width: 1200px){
        grid-template-columns: 1fr ;
    }
`;

/*const Input= styled.input`
    background-color:rgb(191, 253, 205);
    border-color: rgb(0, 87, 20);

`
const Input2= styled.input`
    background-color:rgb(191, 246, 253);
    border-color: rgb(0, 77, 87);

`*/

export{Formulario }