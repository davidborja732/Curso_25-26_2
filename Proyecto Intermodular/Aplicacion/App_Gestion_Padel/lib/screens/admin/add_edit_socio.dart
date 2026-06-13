import 'package:app_gestion_padel/model/socio.dart';
import 'package:app_gestion_padel/services/api_socios_service.dart';
import 'package:app_gestion_padel/theme/app_theme.dart';
import 'package:app_gestion_padel/utils/socio_validators.dart';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';

class AddEditSocio extends StatefulWidget {
  final Socio? socio;

  const AddEditSocio({super.key, this.socio});

  bool get isEditing => socio != null;

  @override
  State<AddEditSocio> createState() => _AddEditSocioState();
}

class _AddEditSocioState extends State<AddEditSocio> {
  final _formKey = GlobalKey<FormState>();
  bool _saving = false;

  late final TextEditingController _dniController;
  late final TextEditingController _contrasenaController;
  late final TextEditingController _confirmarContrasenaController;
  late final TextEditingController _nombreController;
  late final TextEditingController _direccionController;
  late final TextEditingController _telefonoController;
  late final TextEditingController _emailController;
  late final TextEditingController _cuentaBancariaController;

  @override
  void initState() {
    super.initState();
    final s = widget.socio;
    _dniController                = TextEditingController(text: s?.dni ?? '');
    _contrasenaController         = TextEditingController(text: s?.contrasena ?? '');
    _confirmarContrasenaController = TextEditingController();
    _nombreController             = TextEditingController(text: s?.nombreCompleto ?? '');
    _direccionController          = TextEditingController(text: s?.direccion ?? '');
    _telefonoController           = TextEditingController(text: s?.telefono ?? '');
    _emailController              = TextEditingController(text: s?.email ?? '');
    _cuentaBancariaController     = TextEditingController(text: s?.cuentaBancaria ?? '');
  }

  @override
  void dispose() {
    _dniController.dispose();
    _contrasenaController.dispose();
    _confirmarContrasenaController.dispose();
    _nombreController.dispose();
    _direccionController.dispose();
    _telefonoController.dispose();
    _emailController.dispose();
    _cuentaBancariaController.dispose();
    super.dispose();
  }

  Future<void> _save() async {
    if (!_formKey.currentState!.validate()) return;

    setState(() => _saving = true);

    final socio = Socio(
      dni:            _dniController.text.trim(),
      contrasena:     _contrasenaController.text.trim(),
      nombreCompleto: _nombreController.text.trim(),
      direccion:      _direccionController.text.trim(),
      telefono:       _telefonoController.text.trim(),
      email:          _emailController.text.trim(),
      cuentaBancaria: _cuentaBancariaController.text.trim(),
    );

    try {
      final service = ApiSociosService();
      if (widget.isEditing) {
        await service.update(socio);
      } else {
        await service.create(socio);
      }
      if (mounted) Navigator.pop(context, true);
    } on DioException catch (e) {
      if (mounted) {
        setState(() => _saving = false);
        final apiMessage = e.response?.data is Map
            ? e.response?.data['message'] as String?
            : null;
        final message = apiMessage ?? 'Error al guardar. Inténtalo de nuevo.';
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text(message)),
        );
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.isEditing ? 'Editar socio' : 'Nuevo socio'),
      ),
      body: Center(
        child: ConstrainedBox(
          constraints: const BoxConstraints(maxWidth: 600),
          child: SingleChildScrollView(
            padding: const EdgeInsets.all(24),
            child: Form(
              key: _formKey,
              child: Column(
                children: [
                  _Field(
                    controller: _dniController,
                    label: 'DNI',
                    readOnly: widget.isEditing,
                    maxLength: SocioMaxLength.dni,
                    validator: SocioValidators.dni,
                  ),
                  _Field(
                    controller: _nombreController,
                    label: 'Nombre completo',
                    maxLength: SocioMaxLength.nombre,
                    validator: SocioValidators.nombre,
                  ),
                  if (!widget.isEditing) ...[
                    _Field(
                      controller: _contrasenaController,
                      label: 'Contraseña',
                      obscureText: true,
                      maxLength: SocioMaxLength.contrasena,
                      validator: SocioValidators.contrasena,
                    ),
                    _Field(
                      controller: _confirmarContrasenaController,
                      label: 'Repite la contraseña',
                      obscureText: true,
                      validator: SocioValidators.confirmarContrasena(
                        _contrasenaController,
                      ),
                    ),
                  ],
                  _Field(
                    controller: _direccionController,
                    label: 'Dirección',
                    maxLength: SocioMaxLength.direccion,
                    validator: SocioValidators.direccion,
                  ),
                  _Field(
                    controller: _telefonoController,
                    label: 'Teléfono',
                    keyboardType: TextInputType.phone,
                    maxLength: SocioMaxLength.telefono,
                    validator: SocioValidators.telefono,
                  ),
                  _Field(
                    controller: _emailController,
                    label: 'Email',
                    keyboardType: TextInputType.emailAddress,
                    validator: SocioValidators.email,
                  ),
                  _Field(
                    controller: _cuentaBancariaController,
                    label: 'Cuenta bancaria',
                    maxLength: SocioMaxLength.cuentaBancaria,
                    validator: SocioValidators.cuentaBancaria,
                  ),
                  const SizedBox(height: 32),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.end,
                    children: [
                      OutlinedButton(
                        onPressed: _saving ? null : () => Navigator.pop(context),
                        child: const Text('Cancelar'),
                      ),
                      const SizedBox(width: 12),
                      FilledButton(
                        onPressed: _saving ? null : _save,
                        child: _saving
                            ? const SizedBox(width: 18, height: 18, child: CircularProgressIndicator(strokeWidth: 2))
                            : const Text('Guardar'),
                      ),
                    ],
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}

class _Field extends StatelessWidget {
  final TextEditingController controller;
  final String label;
  final bool readOnly;
  final bool obscureText;
  final int? maxLength;
  final TextInputType? keyboardType;
  final String? Function(String?)? validator;

  const _Field({
    required this.controller,
    required this.label,
    this.readOnly = false,
    this.obscureText = false,
    this.maxLength,
    this.keyboardType,
    this.validator,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 16),
      child: TextFormField(
        controller: controller,
        readOnly: readOnly,
        obscureText: obscureText,
        keyboardType: keyboardType,
        maxLength: maxLength,
        validator: validator,
        decoration: InputDecoration(
          labelText: label,
          border: const OutlineInputBorder(),
          fillColor: readOnly ? kReadOnlyFill : null,
        ),
      ),
    );
  }
}
