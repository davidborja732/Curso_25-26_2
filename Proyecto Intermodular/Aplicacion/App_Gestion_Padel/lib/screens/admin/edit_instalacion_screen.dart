import 'package:app_gestion_padel/model/instalacion.dart';
import 'package:app_gestion_padel/services/api_instalaciones_service.dart';
import 'package:app_gestion_padel/theme/app_theme.dart';
import 'package:flutter/material.dart';

class EditInstalacionScreen extends StatefulWidget {
  final Instalacion? instalacion;

  const EditInstalacionScreen({super.key, this.instalacion});

  @override
  State<EditInstalacionScreen> createState() => _EditInstalacionScreenState();
}

class _EditInstalacionScreenState extends State<EditInstalacionScreen> {
  final _formKey = GlobalKey<FormState>();
  late final TextEditingController _nombreController;
  late final TextEditingController _tipoController;
  DateTime? _fechaMantenimiento;
  bool _isLoading = false;

  static const _meses = [
    'enero', 'febrero', 'marzo', 'abril', 'mayo', 'junio',
    'julio', 'agosto', 'septiembre', 'octubre', 'noviembre', 'diciembre',
  ];

  bool get _isCreating => widget.instalacion == null;

  @override
  void initState() {
    super.initState();
    _nombreController =
        TextEditingController(text: widget.instalacion?.nombre ?? '');
    _tipoController =
        TextEditingController(text: widget.instalacion?.tipo ?? '');
    final raw = widget.instalacion?.fechaUltimoMantenimiento;
    if (raw != null && raw.isNotEmpty) {
      try {
        _fechaMantenimiento = DateTime.parse(raw).toLocal();
      } catch (_) {}
    }
  }

  @override
  void dispose() {
    _nombreController.dispose();
    _tipoController.dispose();
    super.dispose();
  }

  String _formatFecha(DateTime d) =>
      '${d.day} de ${_meses[d.month - 1]} de ${d.year}';

  Future<void> _pickFecha() async {
    final picked = await showDatePicker(
      context: context,
      initialDate: _fechaMantenimiento ?? DateTime.now(),
      firstDate: DateTime(2000),
      lastDate: DateTime(2100),
    );
    if (picked != null) setState(() => _fechaMantenimiento = picked);
  }

  Future<void> _save() async {
    if (!_formKey.currentState!.validate()) return;
    setState(() => _isLoading = true);
    try {
      final instalacion = Instalacion(
        idInstalacion: widget.instalacion?.idInstalacion,
        nombre: _nombreController.text.trim(),
        tipo: _tipoController.text.trim(),
        fechaUltimoMantenimiento: _fechaMantenimiento != null
            ? '${_fechaMantenimiento!.year.toString().padLeft(4, '0')}-'
                '${_fechaMantenimiento!.month.toString().padLeft(2, '0')}-'
                '${_fechaMantenimiento!.day.toString().padLeft(2, '0')}'
            : widget.instalacion?.fechaUltimoMantenimiento,
      );
      if (_isCreating) {
        await ApiInstalacionesService().create(instalacion);
      } else {
        await ApiInstalacionesService().update(instalacion);
      }
      if (mounted) Navigator.pop(context, true);
    } catch (e) {
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: Text('Error al guardar: $e'),
            backgroundColor: Colors.red,
          ),
        );
      }
    } finally {
      if (mounted) setState(() => _isLoading = false);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(_isCreating ? 'NUEVA INSTALACIÓN' : 'EDITAR INSTALACIÓN'),
      ),
      body: Center(
        child: ConstrainedBox(
          constraints: const BoxConstraints(maxWidth: 600),
          child: SingleChildScrollView(
            padding: const EdgeInsets.all(24),
            child: Form(
              key: _formKey,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: [
                  Text(
                    'DETALLES DE LA INSTALACIÓN',
                    style: Theme.of(context)
                        .textTheme
                        .labelLarge
                        ?.copyWith(color: kGreen),
                  ),
                  const SizedBox(height: 24),
                  TextFormField(
                    controller: _nombreController,
                    decoration: const InputDecoration(labelText: 'Nombre'),
                    validator: (v) =>
                        v == null || v.trim().isEmpty ? 'Campo requerido' : null,
                  ),
                  const SizedBox(height: 20),
                  TextFormField(
                    controller: _tipoController,
                    decoration: const InputDecoration(labelText: 'Tipo'),
                    validator: (v) =>
                        v == null || v.trim().isEmpty ? 'Campo requerido' : null,
                  ),
                  const SizedBox(height: 20),
                  ListTile(
                    title: const Text('Último mantenimiento'),
                    subtitle: Text(
                      _fechaMantenimiento != null
                          ? _formatFecha(_fechaMantenimiento!)
                          : 'Sin fecha',
                    ),
                    trailing:
                        const Icon(Icons.calendar_today, color: kGreen),
                    onTap: _pickFecha,
                    tileColor: Colors.white,
                    shape: const RoundedRectangleBorder(
                      side: BorderSide(color: kLightGreen),
                    ),
                  ),
                  const SizedBox(height: 48),
                  FilledButton(
                    onPressed: _isLoading ? null : _save,
                    child: _isLoading
                        ? const SizedBox(
                            width: 20,
                            height: 20,
                            child: CircularProgressIndicator(
                              color: Colors.white,
                              strokeWidth: 2,
                            ),
                          )
                        : Text(_isCreating ? 'CREAR INSTALACIÓN' : 'GUARDAR CAMBIOS'),
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
