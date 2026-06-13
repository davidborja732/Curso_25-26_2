import 'package:app_gestion_padel/model/instalacion.dart';
import 'package:app_gestion_padel/model/reserva.dart';
import 'package:app_gestion_padel/model/socio.dart';
import 'package:app_gestion_padel/services/api_instalaciones_service.dart';
import 'package:app_gestion_padel/services/api_reservas_service.dart';
import 'package:app_gestion_padel/services/api_socios_service.dart';
import 'package:app_gestion_padel/theme/app_theme.dart';
import 'package:app_gestion_padel/widgets/admin_app_bar.dart';
import 'package:app_gestion_padel/widgets/admin_drawer.dart';
import 'package:flutter/material.dart';

class AdminHomeScreen extends StatefulWidget {
  const AdminHomeScreen({super.key});

  @override
  State<AdminHomeScreen> createState() => _AdminHomeScreenState();
}

class _AdminHomeScreenState extends State<AdminHomeScreen> {
  late Future<_DashboardData> _future;

  @override
  void initState() {
    super.initState();
    _future = _load();
  }

  Future<_DashboardData> _load() async {
    final socios = await ApiSociosService().request();
    final reservas = await ApiReservasService().request();
    final instalaciones = await ApiInstalacionesService().request();
    return _DashboardData(
      socios: socios,
      reservas: reservas,
      instalaciones: instalaciones,
    );
  }

  String _formatFechaCorta(String? raw) {
    if (raw == null || raw.isEmpty) return '';
    try {
      final dt = DateTime.parse(raw).toLocal();
      return '${dt.day.toString().padLeft(2, '0')}/${dt.month.toString().padLeft(2, '0')} '
          '${dt.hour.toString().padLeft(2, '0')}:${dt.minute.toString().padLeft(2, '0')}';
    } catch (_) {
      return raw;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: const AdminDrawer(currentRoute: '/admin-home'),
      appBar: const AdminAppBar(title: 'INICIO', showLogout: true),
      body: FutureBuilder<_DashboardData>(
        future: _future,
        builder: (context, snapshot) {
          if (snapshot.connectionState != ConnectionState.done) {
            return const Center(child: CircularProgressIndicator());
          }
          if (snapshot.hasError) {
            return Center(
              child: Text(
                'Error al cargar datos: ${snapshot.error}',
                style: const TextStyle(color: Colors.red),
              ),
            );
          }
          return _buildBody(context, snapshot.data!);
        },
      ),
    );
  }

  Widget _buildBody(BuildContext context, _DashboardData data) {
    final now = DateTime.now();
    final today = DateTime(now.year, now.month, now.day);
    final startOfWeek = today.subtract(Duration(days: today.weekday - 1));
    final endOfWeek = startOfWeek.add(
      const Duration(days: 6, hours: 23, minutes: 59),
    );

    final sociosActivos =
        data.socios.where((s) => (s.desactivado ?? 0) == 0).length;
    final sociosInactivos = data.socios.length - sociosActivos;

    final reservasHoy = data.reservas.where((r) {
      if (r.fechaHoraInicio == null) return false;
      try {
        final dt = DateTime.parse(r.fechaHoraInicio!).toLocal();
        return dt.year == today.year &&
            dt.month == today.month &&
            dt.day == today.day;
      } catch (_) {
        return false;
      }
    }).length;

    final reservasEstaSemana = data.reservas.where((r) {
      if (r.fechaHoraInicio == null) return false;
      try {
        final dt = DateTime.parse(r.fechaHoraInicio!).toLocal();
        return !dt.isBefore(startOfWeek) && !dt.isAfter(endOfWeek);
      } catch (_) {
        return false;
      }
    }).length;

    final Map<String, int> usoPorInstalacion = {};
    for (final r in data.reservas) {
      final nombre = r.instalacion?.nombre ?? 'Sin nombre';
      usoPorInstalacion[nombre] = (usoPorInstalacion[nombre] ?? 0) + 1;
    }
    final maxUso = usoPorInstalacion.values.fold(
      0,
      (a, b) => a > b ? a : b,
    );
    final usoOrdenado = usoPorInstalacion.entries.toList()
      ..sort((a, b) => b.value.compareTo(a.value));

    final recientes = [...data.reservas]..sort((a, b) {
        if (a.fechaHoraInicio == null) return 1;
        if (b.fechaHoraInicio == null) return -1;
        return b.fechaHoraInicio!.compareTo(a.fechaHoraInicio!);
      });
    final actividadReciente = recientes.take(6).toList();

    return Center(
      child: ConstrainedBox(
        constraints: const BoxConstraints(maxWidth: 1200),
        child: SingleChildScrollView(
          padding: const EdgeInsets.all(24),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Wrap(
                spacing: 16,
                runSpacing: 16,
                children: [
                  _StatCard(
                    label: 'Socios activos',
                    value: '$sociosActivos',
                    icon: Icons.people_outline,
                    color: kGreen,
                  ),
                  _StatCard(
                    label: 'Socios inactivos',
                    value: '$sociosInactivos',
                    icon: Icons.person_off_outlined,
                    color: kOrange,
                  ),
                  _StatCard(
                    label: 'Total reservas',
                    value: '${data.reservas.length}',
                    icon: Icons.calendar_month_outlined,
                    color: kGreen,
                  ),
                  _StatCard(
                    label: 'Reservas hoy',
                    value: '$reservasHoy',
                    icon: Icons.today_outlined,
                    color: kGreen,
                  ),
                  _StatCard(
                    label: 'Esta semana',
                    value: '$reservasEstaSemana',
                    icon: Icons.date_range_outlined,
                    color: kGreen,
                  ),
                  _StatCard(
                    label: 'Instalaciones',
                    value: '${data.instalaciones.length}',
                    icon: Icons.sports_tennis_outlined,
                    color: kGreen,
                  ),
                ],
              ),
              const SizedBox(height: 32),

              Row(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  if (usoOrdenado.isNotEmpty) ...[
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          _SectionTitle('Uso de instalaciones'),
                          const SizedBox(height: 12),
                          Container(
                            width: double.infinity,
                            padding: const EdgeInsets.all(20),
                            decoration: BoxDecoration(
                              color: Colors.white,
                              border: Border.all(color: kLightGreen),
                            ),
                            child: Column(
                              children: usoOrdenado.map((entry) {
                                final ratio =
                                    maxUso > 0 ? entry.value / maxUso : 0.0;
                                return Padding(
                                  padding: const EdgeInsets.only(bottom: 14),
                                  child: Row(
                                    children: [
                                      SizedBox(
                                        width: 120,
                                        child: Text(
                                          entry.key,
                                          style: const TextStyle(
                                            fontWeight: FontWeight.w600,
                                            color: kOlive,
                                          ),
                                          overflow: TextOverflow.ellipsis,
                                        ),
                                      ),
                                      Expanded(
                                        child: LinearProgressIndicator(
                                          value: ratio,
                                          minHeight: 10,
                                          borderRadius: BorderRadius.zero,
                                          backgroundColor: kLightGreen,
                                          valueColor:
                                              const AlwaysStoppedAnimation(
                                                kGreen,
                                              ),
                                        ),
                                      ),
                                      const SizedBox(width: 12),
                                      SizedBox(
                                        width: 30,
                                        child: Text(
                                          '${entry.value}',
                                          textAlign: TextAlign.right,
                                          style: const TextStyle(
                                            fontWeight: FontWeight.bold,
                                            color: kOlive,
                                          ),
                                        ),
                                      ),
                                    ],
                                  ),
                                );
                              }).toList(),
                            ),
                          ),
                        ],
                      ),
                    ),
                    const SizedBox(width: 24),
                  ],
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        _SectionTitle('Actividad reciente'),
                        const SizedBox(height: 12),
                        Container(
                          width: double.infinity,
                          decoration: BoxDecoration(
                            color: Colors.white,
                            border: Border.all(color: kLightGreen),
                          ),
                          child: actividadReciente.isEmpty
                              ? const Padding(
                                  padding: EdgeInsets.all(16),
                                  child: Text('No hay reservas recientes.'),
                                )
                              : Column(
                                  children: actividadReciente.map((r) {
                                    return Container(
                                      decoration: const BoxDecoration(
                                        border: Border(
                                          bottom:
                                              BorderSide(color: kLightGreen),
                                        ),
                                      ),
                                      child: ListTile(
                                        leading: const Icon(
                                          Icons.event_outlined,
                                          color: kGreen,
                                        ),
                                        title: Text(
                                          r.instalacion?.nombre ??
                                              'Sin instalación',
                                          style: const TextStyle(
                                            fontWeight: FontWeight.w600,
                                          ),
                                        ),
                                        subtitle: Text(
                                          r.socio?.nombreCompleto ?? 'Sin socio',
                                        ),
                                        trailing: Text(
                                          _formatFechaCorta(r.fechaHoraInicio),
                                          style: const TextStyle(
                                            color: Color(0xFF777750),
                                            fontSize: 13,
                                          ),
                                        ),
                                      ),
                                    );
                                  }).toList(),
                                ),
                        ),
                      ],
                    ),
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class _DashboardData {
  final List<Socio> socios;
  final List<Reserva> reservas;
  final List<Instalacion> instalaciones;

  const _DashboardData({
    required this.socios,
    required this.reservas,
    required this.instalaciones,
  });
}

class _SectionTitle extends StatelessWidget {
  final String text;
  const _SectionTitle(this.text);

  @override
  Widget build(BuildContext context) {
    return Text(
      text.toUpperCase(),
      style: Theme.of(context).textTheme.labelLarge?.copyWith(
        color: kGreen,
        letterSpacing: 1.2,
      ),
    );
  }
}

class _StatCard extends StatelessWidget {
  final String label;
  final String value;
  final IconData icon;
  final Color color;

  const _StatCard({
    required this.label,
    required this.value,
    required this.icon,
    required this.color,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 175,
      padding: const EdgeInsets.all(20),
      decoration: BoxDecoration(
        color: Colors.white,
        border: Border.all(color: kLightGreen),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Icon(icon, color: color, size: 26),
          const SizedBox(height: 12),
          Text(
            value,
            style: TextStyle(
              fontSize: 36,
              fontWeight: FontWeight.w900,
              color: color,
              height: 1,
            ),
          ),
          const SizedBox(height: 6),
          Text(
            label,
            style: const TextStyle(color: Color(0xFF777750), fontSize: 13),
          ),
        ],
      ),
    );
  }
}
