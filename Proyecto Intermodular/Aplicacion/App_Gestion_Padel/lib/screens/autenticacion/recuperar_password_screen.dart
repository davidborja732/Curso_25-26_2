import 'package:flutter/material.dart';
import 'package:app_gestion_padel/services/api_socios_service.dart';

class RecuperarPasswordScreen extends StatefulWidget {
  const RecuperarPasswordScreen({super.key});

  @override
  State<RecuperarPasswordScreen> createState() =>
      _RecuperarPasswordScreenState();
}

class _RecuperarPasswordScreenState extends State<RecuperarPasswordScreen> {
  final _emailFormKey = GlobalKey<FormState>();
  final _codeFormKey = GlobalKey<FormState>();

  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _codeController = TextEditingController();
  final TextEditingController _newPasswordController = TextEditingController();
  final TextEditingController _confirmPasswordController =
      TextEditingController();

  int _currentStep =
      0; // 0 = Solicitar código, 1 = Confirmar código y nueva contraseña
  bool _isLoading = false;

  @override
  void dispose() {
    _emailController.dispose();
    _codeController.dispose();
    _newPasswordController.dispose();
    _confirmPasswordController.dispose();
    super.dispose();
  }

  Future<void> _solicitarCodigo() async {
    if (!_emailFormKey.currentState!.validate()) return;

    setState(() => _isLoading = true);
    final email = _emailController.text.trim();

    final success = await ApiSociosService().solicitarRecuperacion(email);

    if (!mounted) return;
    setState(() => _isLoading = false);

    if (success) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Código de verificación enviado a tu correo.'),
          backgroundColor: Colors.green,
        ),
      );
      setState(() {
        _currentStep = 1;
      });
    } else {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Error al enviar el código. Verifica el correo.'),
          backgroundColor: Colors.red,
        ),
      );
    }
  }

  Future<void> _restablecerPassword() async {
    if (!_codeFormKey.currentState!.validate()) return;

    setState(() => _isLoading = true);
    final email = _emailController.text.trim();
    final codigo = _codeController.text.trim();
    final nuevaContrasena = _newPasswordController.text.trim();

    final success = await ApiSociosService().confirmarRecuperacion(
      email: email,
      codigo: codigo,
      nuevaContrasena: nuevaContrasena,
    );

    if (!mounted) return;
    setState(() => _isLoading = false);

    if (success) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text(
            'Contraseña restablecida correctamente. Ya puedes iniciar sesión.',
          ),
          backgroundColor: Colors.green,
        ),
      );
      Navigator.pop(context); // Volver al login
    } else {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Código incorrecto, expirado o error del servidor.'),
          backgroundColor: Colors.red,
        ),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final isWide = MediaQuery.of(context).size.width > 600;

    return Scaffold(
      appBar: AppBar(
        title: const Text('Recuperar Contraseña'),
        centerTitle: true,
      ),
      body: SafeArea(
        child: Center(
          child: SingleChildScrollView(
            padding: const EdgeInsets.all(24.0),
            child: ConstrainedBox(
              constraints: const BoxConstraints(maxWidth: 450),
              child: Card(
                elevation: isWide ? 4 : 0,
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(16),
                ),
                color: isWide ? theme.cardColor : Colors.transparent,
                child: Padding(
                  padding: EdgeInsets.all(isWide ? 32.0 : 8.0),
                  child: AnimatedSwitcher(
                    duration: const Duration(milliseconds: 300),
                    child: _currentStep == 0
                        ? _buildStepRequestEmail()
                        : _buildStepResetPassword(),
                  ),
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }

  Widget _buildStepRequestEmail() {
    return Form(
      key: _emailFormKey,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        mainAxisSize: MainAxisSize.min,
        children: [
          const Icon(Icons.lock_reset, size: 80, color: Colors.blueAccent),
          const SizedBox(height: 16),
          const Text(
            '¿Olvidaste tu contraseña?',
            textAlign: TextAlign.center,
            style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold),
          ),
          const SizedBox(height: 8),
          const Text(
            'Introduce tu dirección de correo electrónico o DNI y te enviaremos un código de seguridad de 6 dígitos para restablecer tu contraseña.',
            textAlign: TextAlign.center,
            style: TextStyle(fontSize: 14, color: Colors.black54),
          ),
          const SizedBox(height: 32),
          TextFormField(
            controller: _emailController,
            keyboardType: TextInputType.emailAddress,
            decoration: const InputDecoration(
              labelText: 'Correo electrónico o DNI',
              prefixIcon: Icon(Icons.email_outlined),
            ),
            validator: (value) {
              if (value == null || value.trim().isEmpty) {
                return 'Introduce tu correo o DNI';
              }
              return null;
            },
          ),
          const SizedBox(height: 32),
          SizedBox(
            height: 50,
            child: ElevatedButton(
              onPressed: _isLoading ? null : _solicitarCodigo,
              style: ElevatedButton.styleFrom(
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(12),
                ),
              ),
              child: _isLoading
                  ? const SizedBox(
                      width: 22,
                      height: 22,
                      child: CircularProgressIndicator(strokeWidth: 2),
                    )
                  : const Text(
                      'Enviar código de recuperación',
                      style: TextStyle(
                        fontSize: 16,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildStepResetPassword() {
    return Form(
      key: _codeFormKey,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        mainAxisSize: MainAxisSize.min,
        children: [
          const Icon(
            Icons.mark_email_read_outlined,
            size: 80,
            color: Colors.green,
          ),
          const SizedBox(height: 16),
          const Text(
            'Introduce el código',
            textAlign: TextAlign.center,
            style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold),
          ),
          const SizedBox(height: 8),
          const Text(
            'Hemos enviado un código a tu correo. Introdúcelo a continuación junto con tu nueva contraseña.',
            textAlign: TextAlign.center,
            style: TextStyle(fontSize: 14, color: Colors.black54),
          ),
          const SizedBox(height: 24),
          TextFormField(
            controller: _codeController,
            keyboardType: TextInputType.number,
            maxLength: 6,
            textAlign: TextAlign.center,
            style: const TextStyle(
              fontSize: 20,
              letterSpacing: 8,
              fontWeight: FontWeight.bold,
            ),
            decoration: const InputDecoration(
              labelText: 'Código de 6 dígitos',
              prefixIcon: Icon(Icons.pin_outlined),
              counterText: '',
            ),
            validator: (value) {
              if (value == null || value.trim().length != 6) {
                return 'El código debe tener 6 dígitos';
              }
              return null;
            },
          ),
          const SizedBox(height: 16),
          TextFormField(
            controller: _newPasswordController,
            obscureText: true,
            decoration: const InputDecoration(
              labelText: 'Nueva contraseña',
              prefixIcon: Icon(Icons.lock_outline),
            ),
            validator: (value) {
              if (value == null || value.length < 6) {
                return 'La contraseña debe tener al menos 6 caracteres';
              }
              return null;
            },
          ),
          const SizedBox(height: 16),
          TextFormField(
            controller: _confirmPasswordController,
            obscureText: true,
            decoration: const InputDecoration(
              labelText: 'Confirmar nueva contraseña',
              prefixIcon: Icon(Icons.lock_outline),
            ),
            validator: (value) {
              if (value != _newPasswordController.text) {
                return 'Las contraseñas no coinciden';
              }
              return null;
            },
          ),
          const SizedBox(height: 32),
          SizedBox(
            height: 50,
            child: ElevatedButton(
              onPressed: _isLoading ? null : _restablecerPassword,
              style: ElevatedButton.styleFrom(
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(12),
                ),
              ),
              child: _isLoading
                  ? const SizedBox(
                      width: 22,
                      height: 22,
                      child: CircularProgressIndicator(strokeWidth: 2),
                    )
                  : const Text(
                      'Restablecer contraseña',
                      style: TextStyle(
                        fontSize: 16,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
            ),
          ),
          const SizedBox(height: 16),
          TextButton(
            onPressed: () {
              setState(() {
                _currentStep = 0;
                _codeController.clear();
                _newPasswordController.clear();
                _confirmPasswordController.clear();
              });
            },
            child: const Text('Volver a solicitar código'),
          ),
        ],
      ),
    );
  }
}
