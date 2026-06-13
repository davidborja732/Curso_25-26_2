import 'package:app_gestion_padel/model/material_deportivo.dart';
import 'package:app_gestion_padel/screens/admin/edit_material_screen.dart';
import 'package:app_gestion_padel/services/api_materiales_service.dart';
import 'package:app_gestion_padel/theme/app_theme.dart';
import 'package:app_gestion_padel/widgets/admin_app_bar.dart';
import 'package:app_gestion_padel/widgets/admin_drawer.dart';
import 'dart:convert';
import 'package:flutter/material.dart';

class MaterialScreen extends StatefulWidget {
  const MaterialScreen({super.key});

  @override
  State<MaterialScreen> createState() => _MaterialScreenState();
}

class _MaterialScreenState extends State<MaterialScreen> {
  late Future<List<MaterialDeportivo>> _future;
  List<MaterialDeportivo> _materiales = [];
  List<MaterialDeportivo>? _filtered;

  @override
  void initState() {
    super.initState();
    _reload();
  }

  void _reload() {
    _future = ApiMaterialesService().request();
    _future.then((list) => setState(() => _materiales = list));
  }

  Future<void> _confirmDelete(MaterialDeportivo material) async {
    final confirmed = await showDialog<bool>(
      context: context,
      builder: (ctx) => AlertDialog(
        title: const Text('Confirmar eliminación'),
        content: Text(
          '¿Seguro que quieres eliminar el material "${material.nombre}"?',
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(ctx, false),
            child: const Text('Cancelar'),
          ),
          FilledButton(
            onPressed: () => Navigator.pop(ctx, true),
            style: FilledButton.styleFrom(backgroundColor: kOrange),
            child: const Text('Eliminar'),
          ),
        ],
      ),
    );
    if (confirmed == true) {
      final response = await ApiMaterialesService().delete(material.idMaterial!);
      if (!mounted) return;
      if (response.statusCode == 204) {
        setState(() => _reload());
      } else {
        dynamic data = response.data;
        if (data is String) {
          try { data = jsonDecode(data); } catch (_) {}
        }
        final msg = (data is Map && data['message'] != null)
            ? data['message'] as String
            : 'Error al eliminar el material.';
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text(msg), backgroundColor: Colors.red),
        );
      }
    }
  }

  Future<void> _openCreate() async {
    final saved = await Navigator.push<bool>(
      context,
      MaterialPageRoute(
        builder: (_) => const EditMaterialScreen(),
      ),
    );
    if (saved == true) setState(() => _reload());
  }

  Future<void> _openEdit(MaterialDeportivo material) async {
    final saved = await Navigator.push<bool>(
      context,
      MaterialPageRoute(
        builder: (_) => EditMaterialScreen(material: material),
      ),
    );
    if (saved == true) setState(() => _reload());
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: const AdminDrawer(currentRoute: '/material'),
      appBar: const AdminAppBar(title: 'MATERIAL', showLogout: true),
      body: Center(
        child: ConstrainedBox(
          constraints: const BoxConstraints(maxWidth: 1200),
          child: Padding(
            padding: const EdgeInsets.all(16),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  children: [
                    Expanded(
                      child: SearchAnchor(
                        builder: (context, controller) => SearchBar(
                          controller: controller,
                          hintText: 'Buscar material...',
                          onTap: () => controller.openView(),
                          onChanged: (_) {
                            setState(() => _filtered = null);
                            controller.openView();
                          },
                          constraints: const BoxConstraints(
                              minHeight: 44,
                              maxHeight: 44,
                            ),
                        ),
                        suggestionsBuilder: (context, controller) {
                          final q = controller.text.toLowerCase();
                          final matches = _materiales.where(
                            (m) =>
                                (m.nombre ?? '').toLowerCase().contains(q) ||
                                (m.descripcion ?? '').toLowerCase().contains(q),
                          );
                          return matches.map(
                            (m) => ListTile(
                              title: Text(m.nombre ?? ''),
                              subtitle: Text(m.descripcion ?? ''),
                              onTap: () {
                                setState(() => _filtered = [m]);
                                controller.closeView(m.nombre);
                              },
                            ),
                          );
                        },
                      ),
                    ),
                    const SizedBox(width: 12),
                    SizedBox(
                      height: 44,
                      child: FilledButton.icon(
                        icon: const Icon(Icons.add, size: 16),
                        label: const Text('Nuevo material'),
                        onPressed: _openCreate,
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 24),
                Expanded(
                  child: FutureBuilder<List<MaterialDeportivo>>(
                    future: _future,
                    builder: (context, snapshot) {
                      if (snapshot.connectionState != ConnectionState.done) {
                        return const Center(child: CircularProgressIndicator());
                      }
                      if (snapshot.hasError) {
                        return Center(
                          child: Text(
                            'Error al cargar material: ${snapshot.error}',
                            style: const TextStyle(color: Colors.red),
                          ),
                        );
                      }

                      final items = _filtered ?? snapshot.data ?? [];
                      if (items.isEmpty) {
                        return const Center(
                          child: Text('No hay material disponible.'),
                        );
                      }

                      return SingleChildScrollView(
                        child: Center(
                          child: SingleChildScrollView(
                            scrollDirection: Axis.horizontal,
                            child: DataTable(
                            columns: const [
                              DataColumn(label: Text('ID')),
                              DataColumn(label: Text('Nombre')),
                              DataColumn(label: Text('Descripción')),
                              DataColumn(label: Text('Acciones')),
                            ],
                            rows: items.map((m) {
                              return DataRow(
                                cells: [
                                  DataCell(Text('${m.idMaterial ?? ''}')),
                                  DataCell(Text(m.nombre ?? '')),
                                  DataCell(Text(m.descripcion ?? '')),
                                  DataCell(
                                    Row(
                                      mainAxisSize: MainAxisSize.min,
                                      children: [
                                        FilledButton(
                                          onPressed: () => _openEdit(m),
                                          child: const Text('Editar'),
                                        ),
                                        const SizedBox(width: 8),
                                        OutlinedButton(
                                          onPressed: () => _confirmDelete(m),
                                          child: const Text('Eliminar'),
                                        ),
                                      ],
                                    ),
                                  ),
                                ],
                              );
                            }).toList(),
                          ),
                        ),
                      ),
                      );
                    },
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
