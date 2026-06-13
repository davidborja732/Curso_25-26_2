import 'package:app_gestion_padel/model/material_deportivo.dart';
import 'package:app_gestion_padel/services/api_materiales_service.dart';
import 'package:app_gestion_padel/theme/app_theme.dart';
import 'package:flutter/material.dart';

class EditMaterialScreen extends StatefulWidget {
  final MaterialDeportivo? material;

  const EditMaterialScreen({super.key, this.material});

  @override
  State<EditMaterialScreen> createState() => _EditMaterialScreenState();
}

class _EditMaterialScreenState extends State<EditMaterialScreen> {
  final _formKey = GlobalKey<FormState>();
  late final TextEditingController _nombreController;
  late final TextEditingController _descripcionController;
  bool _isLoading = false;

  bool get _isCreating => widget.material == null;

  @override
  void initState() {
    super.initState();
    _nombreController =
        TextEditingController(text: widget.material?.nombre ?? '');
    _descripcionController =
        TextEditingController(text: widget.material?.descripcion ?? '');
  }

  @override
  void dispose() {
    _nombreController.dispose();
    _descripcionController.dispose();
    super.dispose();
  }

  Future<void> _save() async {
    if (!_formKey.currentState!.validate()) return;
    setState(() => _isLoading = true);
    try {
      final material = MaterialDeportivo(
        idMaterial: widget.material?.idMaterial,
        nombre: _nombreController.text.trim(),
        descripcion: _descripcionController.text.trim(),
      );
      if (_isCreating) {
        await ApiMaterialesService().create(material);
      } else {
        await ApiMaterialesService().update(material);
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
        title: Text(_isCreating ? 'NUEVO MATERIAL' : 'EDITAR MATERIAL'),
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
                    'DETALLES DEL MATERIAL',
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
                    controller: _descripcionController,
                    decoration: const InputDecoration(labelText: 'Descripción'),
                    maxLines: 3,
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
                        : Text(_isCreating ? 'CREAR MATERIAL' : 'GUARDAR CAMBIOS'),
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
