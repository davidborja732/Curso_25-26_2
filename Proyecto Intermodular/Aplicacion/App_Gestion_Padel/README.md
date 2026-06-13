# App Gestión Pádel

Aplicación desarrollada en **Flutter** para gestionar usuarios y actividades relacionadas con un club de pádel. Se conecta a una **API REST Spring Boot** para la autenticación y gestión de datos.

## Tecnologías

- **Frontend:** Flutter (Dart) con SDK `^3.10.0`
- **Backend:** API REST Spring Boot (servidor externo)
- **HTTP Client:** [Dio](https://pub.dev/packages/dio) `^5.9.2`
- **Plataformas soportadas:** Android, iOS, Web, Windows, Linux, macOS

## Estructura del proyecto

```
lib/
├── main.dart                          # Punto de entrada y configuración de rutas
├── model/
│   ├── socio.dart                     # Modelo de datos Socio
│   ├── reserva.dart                   # Modelo de datos Reserva
│   ├── material_deportivo.dart        # Catálogo de materiales
│   └── material_reservado.dart        # Asociación material-reserva
├── screens/
│   ├── autenticacion/
│   │   ├── login_screen.dart          # Login dual (Socio/Admin)
│   │   └── register_screen.dart       # Registro de nuevos socios
│   ├── socios/
│   │   ├── home_screen.dart           # Pantalla principal con navegación inferior
│   │   ├── perfil_screen.dart         # Visualización del perfil del socio
│   │   ├── editar_perfil_screen.dart  # Edición de datos personales
│   │   ├── reservar_view.dart         # Flujo de reserva paso a paso (Pista → Material → Confirmar)
│   │   ├── disponibilidad_view.dart   # Consulta de horas libres por deporte y día
│   │   ├── add_edit_reserva_screen.dart # Formulario de edición de reservas
│   │   └── reserva_detalle_screen.dart # Detalle completo de una reserva
│   └── admin/
│       ├── socios_screen.dart         # Panel de gestión CRUD de socios (Admin)
│       ├── add_edit_socio.dart        # Formulario crear/editar socios
│       └── reservas_lista_screen.dart # Listado global de reservas (Admin)
├── services/
│   ├── api_socios_service.dart        # Gestión de socios
│   ├── api_administradores_service.dart # Gestión de admins
│   ├── api_reservas_service.dart      # Gestión de reservas
│   ├── api_materiales_service.dart    # Gestión de material deportivo
│   ├── api_instalaciones_service.dart # Gestión de pistas
│   └── user_session.dart              # Singleton para gestionar la sesión activa
└── theme/
    └── app_theme.dart                 # Configuración visual global
```

## Funcionalidades

### Autenticación Dual

- **Login Inteligente:** El sistema diferencia automáticamente entre **Socios** y **Administradores**.
- **Soporte de Credenciales:** Permite el acceso mediante Correo Electrónico o Nombre de Usuario (DNI).
- **Gestión de Sesión:** Implementación de `UserSession` para mantener los datos del usuario logueado en memoria de forma segura.
- **Diseño Responsive:** Layout adaptativo (Web/Móvil) con saneamiento automático de espacios (`trim`) y normalización de mayúsculas/minúsculas.

### Registro de Socios

- **Validación Robusta:** Control de duplicados (DNI/Email) y formatos correctos en tiempo real.
- **Manejo de Errores:** Interceptación de errores 500/400 del servidor mediante `DioException` con feedback visual inmediato.

### Recuperación de Contraseña Offline

- **Flujo en 2 Pasos:** Solicitar un código temporal de 6 dígitos ingresando el Correo o DNI (se imprime en la terminal del backend) e introducir el código junto a la nueva contraseña para cambiarla de forma encriptada.
- **Validaciones en Caliente:** Comprobación de contraseñas idénticas y códigos de longitud exacta.

#### Implementación del Cambio de Contraseña (Archivos y Flujo)

La funcionalidad de recuperar/cambiar la contraseña del usuario está implementada a través de los siguientes archivos y capas:

##### 1. Interfaz de Usuario — `lib/screens/autenticacion/recuperar_password_screen.dart`

Pantalla `RecuperarPasswordScreen` (widget `StatefulWidget`) que gestiona todo el flujo visual. Internamente controla dos pasos mediante la variable `_currentStep`:

*   **Paso 0 — Solicitar código** (`_buildStepRequestEmail`):
    - Campo de texto `_emailController` donde el usuario introduce su **correo electrónico o DNI**.
    - Botón *"Enviar código de recuperación"* que invoca `_solicitarCodigo()`.
    - Validación: comprueba que el campo no esté vacío.
    - Si la API responde `200 OK`, avanza a `_currentStep = 1` con un `AnimatedSwitcher` para una transición fluida.

*   **Paso 1 — Restablecer contraseña** (`_buildStepResetPassword`):
    - Campo `_codeController`: código numérico de 6 dígitos con `maxLength: 6`, estilizado con `letterSpacing: 8` para aspecto de PIN.
    - Campo `_newPasswordController`: nueva contraseña (mínimo 6 caracteres, `obscureText: true`).
    - Campo `_confirmPasswordController`: confirmación de contraseña, validada contra `_newPasswordController.text`.
    - Botón *"Restablecer contraseña"* que invoca `_restablecerPassword()`.
    - Enlace *"Volver a solicitar código"* para regresar al Paso 0.

La pantalla muestra feedback inmediato al usuario mediante `SnackBar` de color verde (éxito) o rojo (error), y al completar exitosamente el cambio ejecuta `Navigator.pop(context)` para volver al Login.

##### 2. Capa de Servicio — `lib/services/api_socios_service.dart`

Dos métodos en `ApiSociosService` gestionan la comunicación con la API REST:

```dart
// Paso 1: Envía el correo/DNI al backend para generar el código temporal
Future<bool> solicitarRecuperacion(String email) async {
  final response = await dio.post(
    '$_baseUrl/socios/recuperar-password/solicitar',
    queryParameters: {'email': email},
  );
  return response.statusCode == 200;
}

// Paso 2: Envía el código + nueva contraseña para restablecer
Future<bool> confirmarRecuperacion({
  required String email,
  required String codigo,
  required String nuevaContrasena,
}) async {
  final response = await dio.post(
    '$_baseUrl/socios/recuperar-password/confirmar',
    data: {
      'email': email,
      'codigo': codigo,
      'nuevaContrasena': nuevaContrasena,
    },
  );
  return response.statusCode == 200;
}
```

Ambos métodos usan `Options(validateStatus: (s) => s != null && s < 500)` para interceptar errores `4xx` sin lanzar excepción, y devuelven un `bool` que la pantalla consume para decidir si avanzar o mostrar el error.

##### 3. Validaciones — `lib/utils/socio_validators.dart`

La clase `SocioValidators` proporciona dos validadores reutilizables para los campos de contraseña usados en el registro (`register_screen.dart`) y en la gestión de socios del admin (`add_edit_socio.dart`):

- `SocioValidators.contrasena(value)` → Comprueba que el campo no esté vacío.
- `SocioValidators.confirmarContrasena(passwordController)` → Devuelve un validador `closure` que compara el valor con el del controlador de contraseña, asegurando que ambos campos coincidan.
- `SocioMaxLength.contrasena = 64` → Limita la longitud máxima del campo.

> **Nota:** En `recuperar_password_screen.dart` las validaciones de la contraseña se hacen inline (mínimo 6 caracteres y coincidencia de campos) sin usar `SocioValidators`, ya que la pantalla de recuperación tiene reglas ligeramente distintas (exige un mínimo de longitud).

##### 4. Navegación — `lib/main.dart` y `lib/screens/autenticacion/login_screen.dart`

- **Ruta declarada** en `main.dart` (línea 38):
  ```dart
  '/recuperar-password': (context) => const RecuperarPasswordScreen(),
  ```
- **Acceso desde el Login** en `login_screen.dart` (líneas 99 y 249):
  El `LoginScreen` incluye un enlace de texto *"¿Olvidaste tu contraseña?"* que ejecuta:
  ```dart
  Navigator.pushNamed(context, '/recuperar-password');
  ```
  Este enlace aparece dos veces para cubrir tanto el layout de **pantalla ancha** (Web) como el de **pantalla móvil**.

##### 5. Diagrama de Flujo Completo

```
LoginScreen                     RecuperarPasswordScreen              API Spring Boot
    │                                    │                                │
    │─── Tap "¿Olvidaste tu             │                                │
    │     contraseña?" ─────────────────▶│                                │
    │                         Paso 0:    │                                │
    │                         Email/DNI  │                                │
    │                                    │── POST /solicitar ────────────▶│
    │                                    │◀── 200 OK (código generado) ──│
    │                         Paso 1:    │                                │
    │                         Código +   │                                │
    │                         Nueva pass │                                │
    │                                    │── POST /confirmar ────────────▶│
    │                                    │◀── 200 OK (pass cambiada) ────│
    │◀── Navigator.pop ─────────────────│                                │
    │     (vuelve al login)              │                                │
```


### Área del Socio (Home)

- **Mis Reservas:** Consulta detallada de reservas personales con acciones de editar, ver detalles y cancelar.
- **Reservar (Flujo paso a paso):**
  1. Selección de deporte e instalación mediante combo boxes encadenados.
  2. Selección de día (próximos 14 días) y hora disponible (08:00–21:00, solo slots libres).
  3. Selección de número de participantes y material deportivo disponible (con información de uso en el slot).
  4. Pantalla de resumen con toda la información y confirmación.
  - La reserva se asigna automáticamente al usuario con sesión iniciada.
  - Las horas ya ocupadas y las horas pasadas del día actual se filtran automáticamente.
- **Disponibilidad:** Consulta de horas libres seleccionando deporte y día, mostrando por cada franja horaria qué instalaciones están disponibles.
- **Detalle de Reserva:** Pantalla para consultar toda la información de una reserva, incluyendo el material alquilado.
- **Perfil:** Gestión de la cuenta personal y edición de datos.

### Gestión de Perfil

- **Perfil de Usuario:** Visualización detallada de los datos del socio.
- **Imagen de Perfil:** Subida y actualización de fotos de perfil personalizadas compatibles con plataformas móviles (Android/iOS) y Web.
- **Límite de Almacenamiento:** Control de tamaño en la subida (máximo 1MB por imagen).
- **Edición en Caliente:** Permite actualizar información (teléfono, dirección, email, etc.) con sincronización inmediata con la API.

#### Implementación de la Imagen de Perfil (Multiplataforma)

La aplicación implementa un flujo adaptativo según el entorno de ejecución para subir y renderizar la foto del usuario de manera fluida y libre de errores:

1. **Subida Adaptativa de Archivos (`ApiSociosService`):**
   *   **En Web (Navegador):** Dado que los navegadores web no soportan la clase `File` de `dart:io` (lanzaría un error de seguridad/compilación), la imagen se captura como un array de bytes (`List<int>`) y se añade al paquete usando:
       ```dart
       multipartFile = MultipartFile.fromBytes(imageBytes, filename: '$dni.jpg');
       ```
   *   **En Móvil (Android/iOS):** Se accede al archivo de imagen guardado en el almacenamiento local o galería usando su ruta física (`path`):
       ```dart
       multipartFile = await MultipartFile.fromFile(imageFile.path, filename: '$dni.jpg');
       ```
   *   El recurso se encapsula en un objeto `FormData` con la clave `'file'` y se envía mediante una solicitud HTTP `POST` a la API.

2. **Visualización y Carga Dinámica:**
   *   Para renderizar la imagen del usuario en los widgets `PerfilScreen` o `EditarPerfilScreen`, la aplicación genera la URL llamando a `getProfileImageUrl(dni)`.
   *   Se carga la imagen en tiempo real apuntando a esta URL mediante widgets de red (`NetworkImage` o `CachedNetworkImage`).
   *   **Fallback Automático:** Si el socio no tiene asignada una foto de perfil personalizada (la API responde `404 Not Found`), el widget captura el error y renderiza en su lugar un `CircleAvatar` estilizado con las iniciales del socio para garantizar una interfaz sumamente premium.

### Panel de Administración

- **Gestión de Socios:** CRUD completo con búsqueda avanzada y filtrado por nombre/DNI.
- **Control de Reservas:** Visualización de todas las reservas del sistema (en desarrollo).
- **Gestión de Instalaciones:** Consulta y edición del catálogo de pistas.
- **Gestión de Material:** Control de inventario de material deportivo.

## Modelo de datos — Socio

| Campo            | Tipo     | Descripción                  |
|------------------|----------|------------------------------|
| `dni`            | `String` | Documento de identidad (PK)  |
| `contrasena`     | `String` | Contraseña del usuario       |
| `nombreCompleto` | `String` | Nombre completo              |
| `direccion`      | `String` | Dirección postal             |
| `telefono`       | `String` | Número de teléfono           |
| `email`          | `String` | Correo electrónico           |
| `cuentaBancaria` | `String` | Cuenta bancaria              |

## Servicios API

El sistema utiliza varios servicios para comunicarse con la API REST de Spring Boot:

| Servicio | Endpoint Base | Descripción |
|----------|---------------|-------------|
| `ApiSociosService` | `/api/socios` | Gestión CRUD de socios y autenticación. |
| `ApiAdministradoresService` | `/api/administradores` | Autenticación de personal administrativo. |
| `ApiReservasService` | `/api/reservas` | Gestión de pistas y horarios. |
| `ApiMaterialesService` | `/api/material-deportivo` | Gestión de alquiler de material. |
| `ApiInstalacionesService` | `/api/instalaciones` | Consulta de pistas disponibles. |

### URL base dinámica

El servicio detecta automáticamente la plataforma en la que se ejecuta la app para usar la dirección IP correcta al comunicarse con el servidor Spring Boot:

| Plataforma              | IP utilizada   | URL completa                     |
|-------------------------|----------------|----------------------------------|
| **Android (emulador)**  | `10.0.2.2`     | `http://10.0.2.2:8080/api`      |
| **Web (navegador)**     | `127.0.0.1`    | `http://127.0.0.1:8080/api`     |

#### ¿Por qué se usan IPs diferentes?

- **`10.0.2.2` (Android emulador):** El emulador de Android se ejecuta en una máquina virtual aislada con su propia interfaz de red. Dentro del emulador, `localhost` (`127.0.0.1`) apunta al propio emulador, no al PC host donde corre el servidor Spring Boot. Google reserva la dirección especial `10.0.2.2` como alias que redirige al `localhost` de la máquina host, permitiendo así que la app acceda al servidor.

- **`127.0.0.1` (Web):** Cuando la app se ejecuta en el navegador, comparte la misma red que el servidor, por lo que `127.0.0.1` (localhost) apunta directamente al servidor Spring Boot.

#### Implementación en código

El getter `_baseUrl` en `lib/services/api_socios_service.dart` resuelve la URL en tiempo de ejecución:

```dart
import 'dart:io' show Platform;
import 'package:flutter/foundation.dart' show kIsWeb;

class ApiSociosService {
  final dio = Dio();

  String get _baseUrl {
    if (kIsWeb) return 'http://127.0.0.1:8080/api';
    if (Platform.isAndroid) return 'http://10.0.2.2:8080/api';
    return 'http://127.0.0.1:8080/api';
  }

  Future<List<Socio>> request() async {
    Response response = await dio.get('$_baseUrl/socios');
    // ...
  }
}
```

> **Nota:** Se comprueba `kIsWeb` antes que `Platform.isAndroid` porque la clase `Platform` de `dart:io` no está disponible en Web y lanzaría un error en tiempo de ejecución.

## Configuración Android

- **Permiso de Internet:** `android.permission.INTERNET` habilitado en `AndroidManifest.xml`.
- **Cleartext Traffic:** `android:usesCleartextTraffic="true"` para permitir peticiones HTTP sin TLS durante el desarrollo.

## Icono de la Aplicación

Se ha configurado un icono personalizado para Android e iOS utilizando el paquete `flutter_launcher_icons`:

- **Asset:** `assets/Logo_Android.png`
- **Comando de generación:**
  ```bash
  flutter pub run flutter_launcher_icons
  ```

## Navegación (Rutas)

| Ruta         | Pantalla         | Descripción |
|--------------|------------------|-------------|
| `/login`     | `LoginScreen`    | Punto de entrada único. |
| `/register`  | `RegisterScreen` | Registro de nuevos socios. |
| `/recuperar-password` | `RecuperarPasswordScreen` | Pantalla de recuperación de contraseñas olvidadas por código. |
| `/home`      | `HomeScreen`     | Panel principal para Socios. |
| `/socios`    | `SociosScreen`   | Panel principal para Administradores. |

La ruta inicial es `/login`.

## Diseño

- Estilo minimalista con fondo blanco y colores cálidos (`#EBE0D9`, `#D6C8C3`).
- Tema basado en `Colors.orange` mediante `colorSchemeSeed`.
- Imagen principal del club ubicada en `assets/Foto_Login.png`.
- **`10.0.2.2` (Android emulador):** El emulador de Android se ejecuta en una máquina virtual aislada con su propia interfaz de red. Google reserva la dirección especial `10.0.2.2` como alias que redirige al `localhost` de la máquina host.
- **`127.0.0.1` (Web):** Comparte la misma red que el servidor, por lo que apunta directamente al servidor Spring Boot.

## 🗓️ Validación Proactiva de Solapamientos (UI/UX)

La aplicación móvil/web no solo confía en las validaciones de la API, sino que implementa una lógica de filtrado proactivo en tiempo real para evitar que el usuario visualice o elija franjas horarias o materiales deportivos en conflicto:

### 1. Filtrado de Horas Disponibles
En la vista de reservas (`reservar_view.dart`) y en la pantalla de disponibilidad (`disponibilidad_view.dart`), las horas se calculan dinámicamente. Para cada slot de una hora $[A, B]$ ($candidatoStart, candidatoEnd$):
*   Se compara con la lista local de reservas activas del servidor.
*   Si una reserva de la misma instalación se solapa temporalmente (aplicando la lógica `rStart.isBefore(candidatoEnd) && rEnd.isAfter(candidatoStart)`), el slot queda descartado y no se muestra al usuario.

### 2. Bloqueo de Material Deportivo Ocupado
En el paso de selección de equipamiento, se calcula si los artículos ya están asignados a reservas solapadas en la misma franja horaria:
*   Si `_materialUsedInSlot(idMaterial) > 0`, significa que el material está alquilado por otra reserva activa en ese mismo horario.
*   La interfaz deshabilita el control de selección, oculta el botón de incrementar y muestra un badge en color rojo indicando **"Reservado"**.
*   Si el usuario edita manualmente y causa un solapamiento (por ejemplo, desde el panel de administrador), el cliente intercepta la respuesta `409 Conflict` devuelta por la API y la muestra mediante un SnackBar amigable informando del solapamiento exacto.

## Mejoras Técnicas Recientes

- **Validación Avanzada de Solapamientos**: Implementación tanto en backend (JPQL) como en frontend (Flutter) de la fórmula matemática de intersección de intervalos `(C < B) && (D > A)`. Se filtran las horas ya reservadas parcial o totalmente en la vista de reserva y la pantalla de disponibilidad, impidiendo solapamientos accidentales.
- **Control de Inventario y Alquiler de Material Deportivo**: Bloqueo dinámico del material deportivo ya reservado por otro socio en el mismo rango horario. El backend lanza `MaterialEnUsoException` (HTTP 409 Conflict) y el frontend muestra un indicador visual en color rojo ("Reservado") inhabilitando la selección del material ocupado.
- **Subida de Imágenes de Perfil**: Implementación de subida y visualización dinámica de fotos de perfil personalizadas, adaptando el envío a bytes en Web y archivos multipart en dispositivos móviles (Android/iOS).
- **Recuperación de Contraseña Offline**: Diseño e implementación de la pantalla `RecuperarPasswordScreen` con navegación fluida y asistida, sincronizada a la perfección con la base de datos local de la API mediante Dio, garantizando una demostración offline 100% confiable.
- **Pantalla de Reservar:** Flujo guiado en 3 pasos (Pista y Hora → Material → Confirmar) con combo boxes encadenados, filtrado de horas ocupadas en tiempo real, detección de materiales en uso por slot horario y asignación automática del socio logueado.
- **Pantalla de Disponibilidad:** Consulta rápida de horas libres por deporte y día, mostrando las instalaciones disponibles en cada franja horaria (08:00–21:00).
- **Sistema de Material Reservado:** Flujo completo para asociar materiales deportivos a las reservas de pistas, incluyendo lógica de limpieza (cleanup) para evitar errores de integridad referencial.
- **Pantalla de Detalles:** Creación de `ReservaDetalleScreen` con carga asíncrona de materiales asociados a la reserva.
- **Corrección de Errores 500:** Resolución de conflictos de claves foráneas en el borrado de reservas mediante la orquestación de la eliminación de materiales hijos antes que la reserva padre.
- **Compatibilidad Web Optimizada:** Refactorización de todos los servicios para usar `defaultTargetPlatform` en lugar de `Platform`, solucionando el problema de la "pantalla en blanco" en entornos web.

## Ejecución

### Android / iOS
```bash
flutter run
```

### Web (con puerto específico para CORS de la API Spring)
```bash
flutter run -d web-server --web-port 54870
```

## Requisitos previos

1. Tener el **servidor Spring Boot** corriendo en `localhost:8080` con los endpoints configurados.
2. Flutter SDK `^3.10.0` instalado.
3. Base de datos MySQL `proyecto` inicializada con el script de creación correspondiente.
