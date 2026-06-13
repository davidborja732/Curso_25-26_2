import 'dart:convert';
import 'package:app_gestion_padel/model/instalacion.dart';
import 'package:app_gestion_padel/screens/admin/edit_instalacion_screen.dart';
import 'package:app_gestion_padel/services/api_instalaciones_service.dart';
import 'package:app_gestion_padel/theme/app_theme.dart';
import 'package:app_gestion_padel/widgets/admin_app_bar.dart';
import 'package:app_gestion_padel/widgets/admin_drawer.dart';
import 'package:flutter/material.dart';

class InstalacionesScreen extends StatefulWidget {
  const InstalacionesScreen({super.key});

  @override
  State<InstalacionesScreen> createState() => _InstalacionesScreenState();
}

class _InstalacionesScreenState extends State<InstalacionesScreen> {
  late Future<List<Instalacion>> _future;
  List<Instalacion> _instalaciones = [];
  List<Instalacion>? _filtered;

  @override
  void initState() {
    super.initState();
    _reload();
  }

  void _reload() {
    _future = ApiInstalacionesService().request();
    _future.then((list) => setState(() => _instalaciones = list));
  }

  Future<void> _confirmDelete(Instalacion instalacion) async {
    final confirmed = await showDialog<bool>(
      context: context,
      builder: (ctx) => AlertDialog(
        title: const Text('Confirmar eliminación'),
        content: Text(
          '¿Seguro que quieres eliminar la instalación "${instalacion.nombre}"?',
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
      final response = await ApiInstalacionesService().delete(instalacion.idInstalacion!);
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
            : 'Error al eliminar la instalación.';
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
        builder: (_) => const EditInstalacionScreen(),
      ),
    );
    if (saved == true) setState(() => _reload());
  }

  Future<void> _openEdit(Instalacion instalacion) async {
    final saved = await Navigator.push<bool>(
      context,
      MaterialPageRoute(
        builder: (_) => EditInstalacionScreen(instalacion: instalacion),
      ),
    );
    if (saved == true) setState(() => _reload());
  }

  String _formatFecha(String? raw) {
    if (raw == null || raw.isEmpty) return '—';
    try {
      final dt = DateTime.parse(raw).toLocal();
      return '${dt.day.toString().padLeft(2, '0')}/'
          '${dt.month.toString().padLeft(2, '0')}/'
          '${dt.year}';
    } catch (_) {
      return raw;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: const AdminDrawer(currentRoute: '/instalaciones'),
      appBar: const AdminAppBar(
        title: 'INSTALACIONES',
        showLogout: true,
      ),
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
                          hintText: 'Buscar instalación...',
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
                          final matches = _instalaciones.where(
                            (i) =>
                                (i.nombre ?? '').toLowerCase().contains(q) ||
                                (i.tipo ?? '').toLowerCase().contains(q),
                          );
                          return matches.map(
                            (i) => ListTile(
                              title: Text(i.nombre ?? ''),
                              subtitle: Text(i.tipo ?? ''),
                              onTap: () {
                                setState(() => _filtered = [i]);
                                controller.closeView(i.nombre);
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
                        label: const Text('Nueva instalación'),
                        onPressed: _openCreate,
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 24),
                Expanded(
                  child: FutureBuilder<List<Instalacion>>(
                    future: _future,
                    builder: (context, snapshot) {
                      if (snapshot.connectionState != ConnectionState.done) {
                        return const Center(child: CircularProgressIndicator());
                      }
                      if (snapshot.hasError) {
                        return Center(
                          child: Text(
                            'Error al cargar instalaciones: ${snapshot.error}',
                            style: const TextStyle(color: Colors.red),
                          ),
                        );
                      }

                      final items = _filtered ?? snapshot.data ?? [];
                      if (items.isEmpty) {
                        return const Center(
                          child: Text('No hay instalaciones disponibles.'),
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
                              DataColumn(label: Text('Tipo')),
                              DataColumn(label: Text('Último mantenimiento')),
                              DataColumn(label: Text('Acciones')),
                            ],
                            rows: items.map((i) {
                              return DataRow(
                                cells: [
                                  DataCell(Text('${i.idInstalacion ?? ''}')),
                                  DataCell(Text(i.nombre ?? '')),
                                  DataCell(Text(i.tipo ?? '')),
                                  DataCell(
                                    Text(_formatFecha(i.fechaUltimoMantenimiento)),
                                  ),
                                  DataCell(
                                    Row(
                                      mainAxisSize: MainAxisSize.min,
                                      children: [
                                        FilledButton(
                                          onPressed: () => _openEdit(i),
                                          child: const Text('Editar'),
                                        ),
                                        const SizedBox(width: 8),
                                        OutlinedButton(
                                          onPressed: () => _confirmDelete(i),
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
