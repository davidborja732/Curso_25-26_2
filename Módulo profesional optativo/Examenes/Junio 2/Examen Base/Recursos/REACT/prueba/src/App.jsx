
import { Formulario} from './elements/Componentes'
import {Input} from './elements/Input.jsx'




function App() {

  return (
    <>
      <Formulario action="">
        <div>
          <Input 
            id='nombre'
            name="nombre"
            label ="Nombre"
            type="text"
            placeholder='Introduzca su nombre'
          />
          <Input 
            id='apellido'
            name="apellido"
            label ="Apellido"
            type="text"
            placeholder='Introduzca su apellido'
          />
        </div>
        
      </Formulario>
      
      


    </>
  )
}

export default App
