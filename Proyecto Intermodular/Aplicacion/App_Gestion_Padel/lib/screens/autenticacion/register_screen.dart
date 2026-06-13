import 'package:flutter/material.dart';
import 'package:app_gestion_padel/model/socio.dart';
import 'package:app_gestion_padel/services/api_socios_service.dart';
import 'package:app_gestion_padel/utils/socio_validators.dart';
import 'package:dio/dio.dart';

class RegisterScreen extends StatefulWidget {
  const RegisterScreen({Key? key}) : super(key: key);

  @override
  State<RegisterScreen> createState() => _RegisterScreenState();
}

class _RegisterScreenState extends State<RegisterScreen> {
  final _formKey = GlobalKey<FormState>();
  final _dniController = TextEditingController();
  final _nombreController = TextEditingController();
  final _direccionController = TextEditingController();
  final _telefonoController = TextEditingController();
  final _emailController = TextEditingController();
  final _cuentaBancariaController = TextEditingController();
  final _passwordController = TextEditingController();
  final _confirmPasswordController = TextEditingController();
  bool _isLoading = false;

  @override
  void dispose() {
    _dniController.dispose();
    _nombreController.dispose();
    _direccionController.dispose();
    _telefonoController.dispose();
    _emailController.dispose();
    _cuentaBancariaController.dispose();
    _passwordController.dispose();
    _confirmPasswordController.dispose();
    super.dispose();
  }

  Future<void> _register() async {
    if (!_formKey.currentState!.validate()) return;

    setState(() => _isLoading = true);

    try {
      final socio = Socio(
        dni: _dniController.text.trim(),
        nombreCompleto: _nombreController.text.trim(),
        direccion: _direccionController.text.trim(),
        telefono: _telefonoController.text.trim(),
        email: _emailController.text.trim(),
        cuentaBancaria: _cuentaBancariaController.text.trim(),
        contrasena: _passwordController.text.trim(),
      );

      await ApiSociosService().create(socio);

      if (!mounted) return;

      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text(
            '¡Registro exitoso! Iniciando sesión automáticamente...',
          ),
          backgroundColor: Colors.green,
        ),
      );

      Navigator.pushReplacementNamed(context, '/home');
    } on DioException catch (e) {
      if (!mounted) return;

      String errorMessage = 'Error al registrar: ${e.message}';
      if (e.response != null && e.response?.data != null) {
        if (e.response?.data is Map && e.response?.data['error'] != null) {
          errorMessage = 'Error del servidor: ${e.response?.data['error']}';
        } else {
          errorMessage =
              'Error del servidor (Código ${e.response?.statusCode})';
        }
      }

      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text(errorMessage), backgroundColor: Colors.red),
      );
    } catch (e) {
      if (!mounted) return;
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text('Error inesperado: $e'),
          backgroundColor: Colors.red,
        ),
      );
    } finally {
      if (mounted) {
        setState(() => _isLoading = false);
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    final screenWidth = MediaQuery.of(context).size.width;
    final isWide = screenWidth > 900;

    if (isWide) {
      return _buildWebLayout(context);
    } else {
      return _buildMobileLayout(context);
    }
  }

  List<Widget> _buildFormFields({required double spacing}) {
    return [
      _buildTextField(
        'DNI',
        controller: _dniController,
        maxLength: SocioMaxLength.dni,
        validator: SocioValidators.dni,
      ),
      SizedBox(height: spacing),
      _buildTextField(
        'Nombre completo',
        controller: _nombreController,
        maxLength: SocioMaxLength.nombre,
        validator: SocioValidators.nombre,
      ),
      SizedBox(height: spacing),
      _buildTextField(
        'Dirección',
        controller: _direccionController,
        maxLength: SocioMaxLength.direccion,
        required: false,
        validator: SocioValidators.direccion,
      ),
      SizedBox(height: spacing),
      _buildTextField(
        'Teléfono',
        controller: _telefonoController,
        keyboardType: TextInputType.phone,
        maxLength: SocioMaxLength.telefono,
        required: false,
        validator: SocioValidators.telefono,
      ),
      SizedBox(height: spacing),
      _buildEmailField(),
      SizedBox(height: spacing),
      _buildTextField(
        'Cuenta bancaria',
        controller: _cuentaBancariaController,
        required: false,
        maxLength: SocioMaxLength.cuentaBancaria,
        validator: SocioValidators.cuentaBancaria,
      ),
      SizedBox(height: spacing),
      _buildTextField(
        'Contraseña',
        controller: _passwordController,
        obscureText: true,
        maxLength: SocioMaxLength.contrasena,
        validator: SocioValidators.contrasena,
      ),
      SizedBox(height: spacing),
      _buildTextField(
        'Repite la contraseña',
        controller: _confirmPasswordController,
        obscureText: true,
        validator: SocioValidators.confirmarContrasena(_passwordController),
      ),
    ];
  }

  Widget _buildWebLayout(BuildContext context) {
    return Scaffold(
      backgroundColor: Theme.of(context).scaffoldBackgroundColor,
      body: Row(
        children: [
          Expanded(
            flex: 5,
            child: Container(
              decoration: BoxDecoration(
                color: Theme.of(context).colorScheme.surfaceContainerHighest,
              ),
              child: Image.asset(
                'assets/Foto_Login.png',
                fit: BoxFit.cover,
                height: double.infinity,
                width: double.infinity,
                errorBuilder: (context, error, stackTrace) => const Center(
                  child: Icon(
                    Icons.sports_tennis,
                    size: 100,
                    color: Colors.white54,
                  ),
                ),
              ),
            ),
          ),

          Expanded(
            flex: 5,
            child: Center(
              child: SingleChildScrollView(
                padding: const EdgeInsets.symmetric(
                  horizontal: 64,
                  vertical: 48,
                ),
                child: ConstrainedBox(
                  constraints: const BoxConstraints(maxWidth: 500),
                  child: Form(
                    key: _formKey,
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.stretch,
                      children: [
                        const Text(
                          'Crear cuenta',
                          style: TextStyle(
                            fontSize: 32,
                            fontWeight: FontWeight.bold,
                            color: Colors.black87,
                          ),
                        ),
                        const SizedBox(height: 8),
                        const Text(
                          'Rellena tus datos para registrarte como socio.',
                          style: TextStyle(fontSize: 16, color: Colors.black54),
                        ),
                        const SizedBox(height: 40),
                        ..._buildFormFields(spacing: 20),
                        const SizedBox(height: 40),
                        SizedBox(
                          height: 50,
                          child: ElevatedButton(
                            onPressed: _isLoading ? null : _register,
                            style: ElevatedButton.styleFrom(
                              backgroundColor: Theme.of(
                                context,
                              ).colorScheme.primary,
                              foregroundColor: Theme.of(
                                context,
                              ).colorScheme.onPrimary,
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(12),
                              ),
                              elevation: 0,
                            ),
                            child: _isLoading
                                ? const SizedBox(
                                    width: 22,
                                    height: 22,
                                    child: CircularProgressIndicator(
                                      strokeWidth: 2,
                                      color: Colors.white,
                                    ),
                                  )
                                : const Text(
                                    'Registrarme',
                                    style: TextStyle(
                                      fontSize: 16,
                                      fontWeight: FontWeight.w600,
                                    ),
                                  ),
                          ),
                        ),
                        const SizedBox(height: 32),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            const Text(
                              '¿Ya tienes cuenta? ',
                              style: TextStyle(
                                color: Colors.black54,
                                fontSize: 15,
                              ),
                            ),
                            GestureDetector(
                              onTap: () {
                                Navigator.pushNamed(context, '/login');
                              },
                              child: Text(
                                'Iniciar sesión',
                                style: TextStyle(
                                  color: Theme.of(context).colorScheme.primary,
                                  fontWeight: FontWeight.w600,
                                  fontSize: 15,
                                  decoration: TextDecoration.underline,
                                ),
                              ),
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildMobileLayout(BuildContext context) {
    return Scaffold(
      backgroundColor: Theme.of(context).scaffoldBackgroundColor,
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        centerTitle: true,
        title: const Text(
          'Registro',
          style: TextStyle(color: Colors.black87, fontWeight: FontWeight.bold),
        ),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back, color: Colors.black87),
          onPressed: () {
            if (Navigator.canPop(context)) {
              Navigator.pop(context);
            }
          },
        ),
      ),
      body: SafeArea(
        child: SingleChildScrollView(
          padding: const EdgeInsets.fromLTRB(32.0, 16.0, 32.0, 48.0),
          child: Form(
            key: _formKey,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                ClipRRect(
                  borderRadius: BorderRadius.circular(12),
                  child: Image.asset(
                    'assets/Foto_Login.png',
                    height: 180,
                    fit: BoxFit.cover,
                    errorBuilder: (context, error, stackTrace) =>
                        const SizedBox(
                          height: 180,
                          child: Center(
                            child: Icon(
                              Icons.image,
                              size: 50,
                              color: Colors.grey,
                            ),
                          ),
                        ),
                  ),
                ),
                const SizedBox(height: 24),
                ..._buildFormFields(spacing: 16),
                const SizedBox(height: 48),
                Center(
                  child: OutlinedButton(
                    onPressed: _isLoading ? null : _register,
                    style: OutlinedButton.styleFrom(
                      padding: const EdgeInsets.symmetric(
                        horizontal: 24,
                        vertical: 12,
                      ),
                      side: BorderSide(
                        color: Theme.of(context).colorScheme.primary,
                      ),
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(20),
                      ),
                    ),
                    child: _isLoading
                        ? const SizedBox(
                            width: 20,
                            height: 20,
                            child: CircularProgressIndicator(strokeWidth: 2),
                          )
                        : const Text(
                            'Registrarme',
                            style: TextStyle(
                              color: Colors.black87,
                              fontWeight: FontWeight.w500,
                            ),
                          ),
                  ),
                ),
                const SizedBox(height: 48),
                Column(
                  children: [
                    const Text(
                      '¿Ya tienes cuenta?',
                      style: TextStyle(color: Colors.black87),
                    ),
                    GestureDetector(
                      onTap: () {
                        Navigator.pushNamed(context, '/login');
                      },
                      child: const Text(
                        'Iniciar sesión',
                        style: TextStyle(
                          color: Colors.black87,
                          fontWeight: FontWeight.w500,
                          decoration: TextDecoration.underline,
                        ),
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Widget _buildEmailField() {
    return TextFormField(
      controller: _emailController,
      keyboardType: TextInputType.emailAddress,
      decoration: const InputDecoration(hintText: 'Correo electrónico'),
      validator: SocioValidators.email,
    );
  }

  Widget _buildTextField(
    String hintText, {
    bool obscureText = false,
    TextEditingController? controller,
    TextInputType? keyboardType,
    bool required = true,
    int? maxLength,
    String? Function(String?)? validator,
  }) {
    return TextFormField(
      controller: controller,
      obscureText: obscureText,
      keyboardType: keyboardType,
      maxLength: maxLength,
      decoration: InputDecoration(hintText: hintText),
      validator:
          validator ??
          (value) {
            if (required && (value == null || value.isEmpty)) {
              return 'Este campo no puede estar vacío';
            }
            return null;
          },
    );
  }
}
