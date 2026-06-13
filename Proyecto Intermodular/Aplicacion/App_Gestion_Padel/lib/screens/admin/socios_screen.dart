import 'package:app_gestion_padel/model/socio.dart';
import 'package:app_gestion_padel/screens/admin/add_edit_socio.dart';
import 'package:app_gestion_padel/screens/admin/socio_detalle_screen.dart';
import 'package:app_gestion_padel/services/api_socios_service.dart';
import 'package:app_gestion_padel/widgets/admin_app_bar.dart';
import 'package:app_gestion_padel/widgets/admin_drawer.dart';
import 'package:flutter/material.dart';

class SociosScreen extends StatefulWidget {
  const SociosScreen({super.key});

  @override
  State<SociosScreen> createState() => _SociosScreenState();
}

class _SociosScreenState extends State<SociosScreen> {
  late Future<List<Socio>> _sociosFuture;
  List<Socio> _socios = [];
  List<Socio>? _filtered;

  @override
  void initState() {
    super.initState();
    _reload();
  }

  void _reload() {
    _sociosFuture = ApiSociosService().request();
    _sociosFuture.then((socios) => setState(() => _socios = socios));
  }

  Future<bool> _confirm(String mensaje) async {
    return await showDialog<bool>(
          context: context,
          builder: (ctx) => AlertDialog(
            content: Text(mensaje),
            actions: [
              TextButton(
                onPressed: () => Navigator.pop(ctx, false),
                child: const Text('Cancelar'),
              ),
              TextButton(
                onPressed: () => Navigator.pop(ctx, true),
                child: const Text('Confirmar'),
              ),
            ],
          ),
        ) ??
        false;
  }

  Future<void> _openForm({Socio? socio}) async {
    final saved = await Navigator.push<bool>(
      context,
      MaterialPageRoute(builder: (_) => AddEditSocio(socio: socio)),
    );
    if (saved == true) setState(() => _reload());
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: const AdminDrawer(currentRoute: '/socios'),
      appBar: const AdminAppBar(
        title: 'GESTIÓN DE SOCIOS',
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
                        builder: (context, controller) {
                          return SearchBar(
                            controller: controller,
                            hintText: 'Buscar socio...',
                            onTap: () => controller.openView(),
                            onChanged: (_) {
                              setState(() => _filtered = null);
                              controller.openView();
                            },
                            constraints: const BoxConstraints(
                              minHeight: 44,
                              maxHeight: 44,
                            ),
                          );
                        },
                        suggestionsBuilder: (context, controller) {
                          final query = controller.text.toLowerCase();
                          final filtered = _socios.where(
                            (s) =>
                                (s.nombreCompleto ?? '').toLowerCase().contains(
                                  query,
                                ) ||
                                (s.dni ?? '').toLowerCase().contains(query),
                          );
                          return filtered.map(
                            (socio) => ListTile(
                              title: Text(socio.nombreCompleto ?? ''),
                              subtitle: Text(socio.dni ?? ''),
                              onTap: () {
                                setState(() => _filtered = [socio]);
                                controller.closeView(socio.nombreCompleto);
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
                        label: const Text('Nuevo socio'),
                        onPressed: () => _openForm(),
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 24),
                Expanded(
                  child: FutureBuilder<List<Socio>>(
                    future: _sociosFuture,
                    builder: (context, snapshot) {
                      if (snapshot.connectionState != ConnectionState.done) {
                        return const Center(child: CircularProgressIndicator());
                      }

                      if (snapshot.hasError) {
                        return Center(
                          child: Text(
                            'Error al cargar socios: ${snapshot.error}',
                            style: const TextStyle(color: Colors.red),
                          ),
                        );
                      }

                      final socios = snapshot.data ?? [];

                      if (socios.isEmpty) {
                        return const Center(
                          child: Text('No hay socios disponibles.'),
                        );
                      }

                      return SingleChildScrollView(
                        child: Center(
                          child: SingleChildScrollView(
                            scrollDirection: Axis.horizontal,
                            child: DataTable(
                            showCheckboxColumn: false,
                            columns: const [
                              DataColumn(label: Text('Nombre')),
                              DataColumn(label: Text('DNI')),
                              DataColumn(label: Text('Dirección')),
                              DataColumn(label: Text('Teléfono')),
                              DataColumn(label: Text('Email')),
                              DataColumn(label: Text('Acciones')),
                            ],
                            rows:
                                ((_filtered ?? socios).toList()..sort((a, b) {
                                      final aActive = (a.desactivado ?? 0) == 0
                                          ? 0
                                          : 1;
                                      final bActive = (b.desactivado ?? 0) == 0
                                          ? 0
                                          : 1;
                                      return aActive.compareTo(bActive);
                                    }))
                                    .map((socio) {
                                      final isInactive =
                                          (socio.desactivado ?? 0) != 0;
                                      final textStyle = isInactive
                                          ? const TextStyle(color: Colors.grey)
                                          : null;
                                      return DataRow(
                                        onSelectChanged: (_) async {
                                          final result =
                                              await Navigator.push<bool>(
                                            context,
                                            MaterialPageRoute(
                                              builder: (_) =>
                                                  SocioDetalleScreen(
                                                    socio: socio,
                                                  ),
                                            ),
                                          );
                                          if (result == true) {
                                            setState(() => _reload());
                                          }
                                        },
                                        cells: [
                                          DataCell(
                                            Text(
                                              socio.nombreCompleto ?? '',
                                              style: textStyle,
                                            ),
                                          ),
                                          DataCell(
                                            Text(
                                              socio.dni ?? '',
                                              style: textStyle,
                                            ),
                                          ),
                                          DataCell(
                                            Text(
                                              socio.direccion ?? '',
                                              style: textStyle,
                                            ),
                                          ),
                                          DataCell(
                                            Text(
                                              socio.telefono ?? '',
                                              style: textStyle,
                                            ),
                                          ),
                                          DataCell(
                                            Text(
                                              socio.email ?? '',
                                              style: textStyle,
                                            ),
                                          ),
                                          DataCell(
                                            Row(
                                              children: [
                                                SizedBox(
                                                  width: 100,
                                                  child: socio.desactivado == 0
                                                      ? FilledButton(
                                                          onPressed: () =>
                                                              _openForm(
                                                                socio: socio,
                                                              ),
                                                          child: const Text(
                                                            'Editar',
                                                          ),
                                                        )
                                                      : FilledButton(
                                                          onPressed: null,
                                                          child: const Text(
                                                            'Editar',
                                                          ),
                                                        ),
                                                ),
                                                const SizedBox(width: 5),
                                                SizedBox(
                                                  width: 130,
                                                  child: socio.desactivado == 0
                                                      ? OutlinedButton(
                                                          onPressed: () async {
                                                            final ok =
                                                                await _confirm(
                                                                  '¿Desactivar a ${socio.nombreCompleto}?',
                                                                );
                                                            if (!ok) return;
                                                            await ApiSociosService()
                                                                .deactivate(
                                                                  socio.dni!,
                                                                );
                                                            setState(
                                                              () => _reload(),
                                                            );
                                                          },
                                                          child: const Text(
                                                            'Desactivar',
                                                          ),
                                                        )
                                                      : OutlinedButton(
                                                          onPressed: () async {
                                                            final ok =
                                                                await _confirm(
                                                                  '¿Activar a ${socio.nombreCompleto}?',
                                                                );
                                                            if (!ok) return;
                                                            await ApiSociosService()
                                                                .activate(
                                                                  socio.dni!,
                                                                );
                                                            setState(
                                                              () => _reload(),
                                                            );
                                                          },
                                                          child: const Text(
                                                            'Activar',
                                                          ),
                                                        ),
                                                ),
                                              ],
                                            ),
                                          ),
                                        ],
                                      );
                                    })
                                    .toList(),
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
