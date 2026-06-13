# 👥 Aplicación de Gestión de Socios con Firebase

Una aplicación web moderna y responsive para gestionar información de socios utilizando Vite, React y Styled Components.

## 🚀 Características

- ✅ **CRUD Completo**: Crear, Leer, Actualizar y Eliminar socios
- 📱 **Responsive**: Diseño completamente responsivo para dispositivos móviles, tablets y desktops
- 🔥 **Firebase Integration**: Integración con Firestore para almacenamiento en la nube
- 🎨 **Styled Components**: Estilos encapsulados y reutilizables
- 🔍 **Búsqueda**: Filtrar socios por nombre o email
- ⚡ **Vite**: Bundler rápido y moderno
- 💬 **Código Comentado**: Todas las funciones y componentes están ampliamente comentadas

## 📋 Requisitos

- Node.js (versión 14 o superior)
- npm o yarn
- Cuenta de Firebase

## 🛠️ Instalación

### 1. Clonar o descargar el proyecto

```bash
cd sociosfirebase
```

### 2. Instalar las dependencias

```bash
npm install
```

O si usas yarn:

```bash
yarn install
```

## ⚙️ Configuración

La configuración de Firebase ya está incluida en el archivo `src/services/firebase.js`. No es necesario realizar cambios adicionales si tu proyecto Firebase es el mismo proporcionado.

### Si deseas cambiar la configuración de Firebase:

Edita el archivo `src/services/firebase.js` y reemplaza la configuración con tus credenciales de Firebase:

```javascript
const firebaseConfig = {
  apiKey: "TU_API_KEY",
  authDomain: "tu-proyecto.firebaseapp.com",
  projectId: "tu-proyecto",
  storageBucket: "tu-proyecto.firebasestorage.app",
  messagingSenderId: "TU_MESSAGING_SENDER_ID",
  appId: "TU_APP_ID"
};
```

## 🚀 Ejecutar la aplicación

### En modo desarrollo

```bash
npm run dev
```

La aplicación se abrirá en `http://localhost:3000`

### Para crear una compilación de producción

```bash
npm run build
```

### Para previsualizar la compilación de producción

```bash
npm run preview
```

## 📁 Estructura del Proyecto

```
sociosfirebase/
├── src/
│   ├── components/
│   │   ├── SociosList.jsx      # Componente principal con lógica de gestión
│   │   ├── SocioForm.jsx        # Formulario para crear/editar socios
│   │   └── SocioTable.jsx       # Tabla responsiva de socios
│   ├── services/
│   │   ├── firebase.js          # Configuración de Firebase
│   │   └── sociosService.js     # Funciones CRUD para socios
│   ├── styles/
│   │   └── GlobalStyle.js       # Estilos globales y temas
│   ├── App.jsx                  # Componente raíz
│   └── main.jsx                 # Punto de entrada de React
├── index.html                   # HTML principal
├── vite.config.js               # Configuración de Vite
├── package.json                 # Dependencias del proyecto
└── README.md                    # Este archivo
```

## 🎯 Uso de la Aplicación

### Agregar un nuevo socio

1. Haz clic en el botón **"➕ Agregar Nuevo Socio"**
2. Completa el formulario con los datos del socio
3. Haz clic en **"Crear"**

### Editar un socio

1. Localiza el socio en la tabla
2. Haz clic en el botón **"✏️ Editar"**
3. Modifica los datos deseados
4. Haz clic en **"Actualizar"**

### Eliminar un socio

1. Localiza el socio en la tabla
2. Haz clic en el botón **"🗑️ Eliminar"**
3. Confirma la eliminación en el cuadro de diálogo

### Buscar socios

1. Utiliza la **barra de búsqueda** en la parte superior
2. Escribe el nombre o email del socio
3. Los resultados se filtran automáticamente
4. Haz clic en **"Limpiar"** para resetear la búsqueda

## 📱 Responsividad

La aplicación es completamente responsiva y se adapta a diferentes tamaños de pantalla:

- **Móviles**: < 480px
- **Tablets**: 480px - 768px
- **Desktops**: > 768px

En dispositivos móviles, la tabla se transforma en tarjetas para una mejor visualización.

## 🎨 Componentes Principales

### SociosList
Componente principal que gestiona el estado general de los socios, búsqueda y llamadas a funciones CRUD.

### SocioForm
Modal con formulario para crear o editar socios. Incluye validación básica y mensajes de estado.

### SocioTable
Tabla responsiva que muestra todos los socios con acciones de editar y eliminar.

### Global Styles
Estilos globales, colores, breakpoints y sombras reutilizables en toda la aplicación.

## 🔧 Servicios

### firebase.js
Inicializa la configuración de Firebase y exporta la instancia de Firestore para ser utilizada en toda la aplicación.

### sociosService.js
Contiene todas las funciones para interactuar con la base de datos:
- `getAllSocios()` - Obtiene todos los socios
- `getSocioById(socioId)` - Obtiene un socio específico
- `createSocio(socioData)` - Crea un nuevo socio
- `updateSocio(socioId, socioData)` - Actualiza un socio
- `deleteSocio(socioId)` - Elimina un socio
- `searchSociosByName(searchTerm)` - Busca socios por nombre

## 🔐 Seguridad

Esta es una aplicación de demostración. Para un uso en producción, se recomienda:

1. Configurar reglas de seguridad en Firestore
2. Implementar autenticación de usuarios
3. Validar datos en el servidor
4. Usar variables de entorno para las credenciales de Firebase

## 🐛 Resolución de Problemas

### La aplicación no inicia
- Asegúrate de haber instalado todas las dependencias: `npm install`
- Verifica que Node.js esté instalado correctamente

### No puedo conectar a Firebase
- Verifica que la configuración de Firebase sea correcta
- Asegúrate de que tu proyecto de Firebase está activo
- Comprueba que Firestore esté habilitado en tu proyecto Firebase

### La tabla no se ve correctamente en móvil
- Limpia el caché del navegador
- Intenta en un navegador diferente
- Asegúrate de que el viewport está configurado correctamente

## 📚 Recursos Útiles

- [Documentación de Vite](https://vite.dev/)
- [Documentación de React](https://react.dev/)
- [Documentación de Styled Components](https://styled-components.com/)
- [Documentación de Firebase](https://firebase.google.com/docs)

## 📝 Licencia

Este proyecto está disponible bajo la licencia MIT.

## 👤 Autor

Desarrollado como una aplicación de gestión de socios con Firebase.

---

¡Disfruta usando la aplicación! 🎉
