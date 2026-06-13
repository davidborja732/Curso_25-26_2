import 'dart:io'; // NECESARIO para FileImage en Android/iOS
import 'package:flutter/material.dart';
import 'package:flutter/foundation.dart' show kIsWeb;
import 'package:image_picker/image_picker.dart';
import 'package:app_gestion_padel/model/socio.dart';
import 'package:app_gestion_padel/services/api_socios_service.dart';
import 'package:app_gestion_padel/services/user_session.dart';

class EditarPerfilScreen extends StatefulWidget {
  const EditarPerfilScreen({Key? key}) : super(key: key);

  @override
  State<EditarPerfilScreen> createState() => _EditarPerfilScreenState();
}

class _EditarPerfilScreenState extends State<EditarPerfilScreen> {
  final _formKey = GlobalKey<FormState>();

  late TextEditingController _nombreController;
  late TextEditingController _emailController;
  late TextEditingController _telefonoController;
  late TextEditingController _direccionController;
  late TextEditingController _cuentaController;

  bool _isLoading = false;

  XFile? _selectedImage;
  MemoryImage? _webPreview;

  @override
  void initState() {
    super.initState();
    final user = UserSession().loggedInUser;

    _nombreController = TextEditingController(text: user?.nombreCompleto ?? '');
    _emailController = TextEditingController(text: user?.email ?? '');
    _telefonoController = TextEditingController(text: user?.telefono ?? '');
    _direccionController = TextEditingController(text: user?.direccion ?? '');
    _cuentaController = TextEditingController(text: user?.cuentaBancaria ?? '');
  }

  @override
  void dispose() {
    _nombreController.dispose();
    _emailController.dispose();
    _telefonoController.dispose();
    _direccionController.dispose();
    _cuentaController.dispose();
    super.dispose();
  }

  Future<void> _pickImage() async {
    final picker = ImagePicker();
    final picked = await picker.pickImage(
      source: ImageSource.gallery,
      maxWidth: 800,
      maxHeight: 800,
      imageQuality: 80,
    );

    if (picked != null) {
      if (kIsWeb) {
        final bytes = await picked.readAsBytes();
        setState(() {
          _selectedImage = picked;
          _webPreview = MemoryImage(bytes);
        });
      } else {
        setState(() {
          _selectedImage = picked;
        });
      }
    }
  }

  Future<void> _guardarCambios() async {
    if (_formKey.currentState!.validate()) {
      setState(() {
        _isLoading = true;
      });

      try {
        final currentUser = UserSession().loggedInUser;
        if (currentUser == null)
          throw Exception("No hay sesión de usuario activa.");

        // Upload image first if selected
        if (_selectedImage != null) {
          if (kIsWeb) {
            final bytes = await _selectedImage!.readAsBytes();
            await ApiSociosService().uploadProfileImage(
              currentUser.dni!,
              bytes,
            );
          } else {
            await ApiSociosService().uploadProfileImage(
              currentUser.dni!,
              _selectedImage!,
            );
          }
          currentUser.imagenPerfil = '${currentUser.dni}.jpeg';
        }

        final updatedSocio = Socio(
          dni: currentUser.dni,
          contrasena: currentUser.contrasena,
          desactivado: currentUser.desactivado,
          imagenPerfil: currentUser.imagenPerfil,
          nombreCompleto: _nombreController.text.trim(),
          email: _emailController.text.trim(),
          telefono: _telefonoController.text.trim(),
          direccion: _direccionController.text.trim(),
          cuentaBancaria: _cuentaController.text.trim(),
        );

        await ApiSociosService().update(updatedSocio);

        UserSession().loggedInUser = updatedSocio;

        if (!mounted) return;
        ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(content: Text('Perfil actualizado correctamente')),
        );
        Navigator.pop(context, true);
      } catch (e) {
        if (!mounted) return;
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: Text('Error al actualizar: $e'),
            backgroundColor: Colors.red,
          ),
        );
      } finally {
        if (mounted) {
          setState(() {
            _isLoading = false;
          });
        }
      }
    }
  }

  // 🔥 CÓDIGO CORREGIDO AQUÍ
  Widget _buildImagePicker(ThemeData theme) {
    final user = UserSession().loggedInUser;
    final hasExistingImage =
        user?.imagenPerfil != null && user!.imagenPerfil!.isNotEmpty;

    ImageProvider? backgroundImage;

    // 1. Si el usuario seleccionó una imagen
    if (_selectedImage != null) {
      if (kIsWeb && _webPreview != null) {
        backgroundImage = _webPreview!;
      } else if (!kIsWeb) {
        backgroundImage = FileImage(File(_selectedImage!.path));
      }
    }
    // 2. Si no seleccionó imagen pero ya tiene una en el servidor
    else if (hasExistingImage) {
      backgroundImage = NetworkImage(
        ApiSociosService().getProfileImageUrl(user!.dni!),
      );
    }

    return Column(
      children: [
        Stack(
          alignment: Alignment.bottomRight,
          children: [
            CircleAvatar(
              radius: 60,
              backgroundColor: theme.colorScheme.secondary,
              backgroundImage: backgroundImage,
              child: backgroundImage == null
                  ? Icon(
                      Icons.person_outline,
                      size: 70,
                      color: theme.colorScheme.onSecondary,
                    )
                  : null,
            ),
            Positioned(
              bottom: 0,
              right: 0,
              child: GestureDetector(
                onTap: _pickImage,
                child: Container(
                  padding: const EdgeInsets.all(8),
                  decoration: BoxDecoration(
                    color: theme.colorScheme.primary,
                    shape: BoxShape.circle,
                    border: Border.all(color: Colors.white, width: 2),
                  ),
                  child: Icon(
                    Icons.camera_alt,
                    size: 20,
                    color: theme.colorScheme.onPrimary,
                  ),
                ),
              ),
            ),
          ],
        ),
        const SizedBox(height: 8),
        TextButton(
          onPressed: _pickImage,
          child: Text(
            _selectedImage != null ? 'Cambiar foto' : 'Añadir foto de perfil',
            style: TextStyle(
              color: theme.colorScheme.primary,
              fontWeight: FontWeight.w600,
            ),
          ),
        ),
      ],
    );
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return Scaffold(
      appBar: AppBar(title: const Text('Editar Perfil'), centerTitle: true),
      body: SafeArea(
        child: SingleChildScrollView(
          padding: const EdgeInsets.all(24.0),
          child: Center(
            child: ConstrainedBox(
              constraints: const BoxConstraints(maxWidth: 600),
              child: Form(
                key: _formKey,
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.stretch,
                  children: [
                    Center(child: _buildImagePicker(theme)),
                    const SizedBox(height: 24),
                    TextFormField(
                      controller: _nombreController,
                      decoration: const InputDecoration(
                        labelText: 'Nombre completo',
                      ),
                      validator: (value) =>
                          value == null || value.isEmpty ? 'Requerido' : null,
                    ),
                    const SizedBox(height: 16),
                    TextFormField(
                      controller: _emailController,
                      keyboardType: TextInputType.emailAddress,
                      decoration: const InputDecoration(
                        labelText: 'Correo electrónico',
                      ),
                      validator: (value) {
                        if (value == null || value.isEmpty) return 'Requerido';
                        if (!value.contains('@')) return 'Correo no válido';
                        return null;
                      },
                    ),
                    const SizedBox(height: 16),
                    TextFormField(
                      controller: _telefonoController,
                      keyboardType: TextInputType.phone,
                      decoration: const InputDecoration(labelText: 'Teléfono'),
                      validator: (value) =>
                          value == null || value.isEmpty ? 'Requerido' : null,
                    ),
                    const SizedBox(height: 16),
                    TextFormField(
                      controller: _direccionController,
                      decoration: const InputDecoration(labelText: 'Dirección'),
                      validator: (value) =>
                          value == null || value.isEmpty ? 'Requerido' : null,
                    ),
                    const SizedBox(height: 16),
                    TextFormField(
                      controller: _cuentaController,
                      decoration: const InputDecoration(
                        labelText: 'Cuenta bancaria',
                      ),
                      validator: (value) =>
                          value == null || value.isEmpty ? 'Requerido' : null,
                    ),
                    const SizedBox(height: 48),
                    SizedBox(
                      height: 50,
                      child: FilledButton(
                        onPressed: _isLoading ? null : _guardarCambios,
                        child: _isLoading
                            ? const CircularProgressIndicator(
                                color: Colors.white,
                              )
                            : const Text('Guardar cambios'),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
