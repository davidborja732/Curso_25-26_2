import 'package:app_gestion_padel/model/reserva.dart';
import 'package:app_gestion_padel/model/material_reservado.dart';
import 'package:app_gestion_padel/screens/socios/add_edit_reserva_screen.dart';
import 'package:app_gestion_padel/screens/socios/reserva_detalle_screen.dart';
import 'package:app_gestion_padel/services/api_reservas_service.dart';
import 'package:app_gestion_padel/services/api_materiales_service.dart';
import 'package:app_gestion_padel/services/user_session.dart';
import 'package:app_gestion_padel/theme/app_theme.dart';
import 'package:flutter/material.dart';

class MisReservasView extends StatefulWidget {
  const MisReservasView({super.key});

  @override
  State<MisReservasView> createState() => _MisReservasViewState();
}

class _MisReservasViewState extends State<MisReservasView> {
  late Future<List<Reserva>> _reservasFuture;

  @override
  void initState() {
    super.initState();
    _loadReservas();
  }

  void _loadReservas() {
    setState(() {
      _reservasFuture = ApiReservasService().request();
    });
  }

  String _formatFecha(String? raw) {
    if (raw == null || raw.isEmpty) return 'N/A';
    try {
      final dt = DateTime.parse(raw).toLocal();
      return '${dt.day.toString().padLeft(2, '0')}/${dt.month.toString().padLeft(2, '0')}/${dt.year}';
    } catch (_) {
      return raw;
    }
  }

  String _formatHora(String? raw) {
    if (raw == null || raw.isEmpty) return 'N/A';
    try {
      final dt = DateTime.parse(raw).toLocal();
      return '${dt.hour.toString().padLeft(2, '0')}:${dt.minute.toString().padLeft(2, '0')}';
    } catch (_) {
      return raw;
    }
  }

  @override
  Widget build(BuildContext context) {
    final currentUserDni = UserSession().loggedInUser?.dni;

    return RefreshIndicator(
      onRefresh: () async {
        _loadReservas();
      },
      child: FutureBuilder<List<Reserva>>(
        future: _reservasFuture,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          }

          if (snapshot.hasError) {
            return Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  const Icon(Icons.error_outline, color: kOrange, size: 48),
                  const SizedBox(height: 16),
                  Text('Error al cargar reservas', style: Theme.of(context).textTheme.titleMedium),
                  TextButton(onPressed: _loadReservas, child: const Text('Reintentar')),
                ],
              ),
            );
          }

          final allReservas = snapshot.data ?? [];
          final userReservas = allReservas.where((r) => r.socio?.dni == currentUserDni).toList();

          if (userReservas.isEmpty) {
            return Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Icon(Icons.event_busy, color: kGreen.withValues(alpha: 0.5), size: 64),
                  const SizedBox(height: 16),
                  Text(
                    'No tienes reservas activas',
                    style: Theme.of(context).textTheme.titleLarge?.copyWith(color: kGreen),
                  ),
                  const SizedBox(height: 8),
                  const Text('¡Reserva tu próxima pista ahora!'),
                ],
              ),
            );
          }

          return ListView.builder(
            padding: const EdgeInsets.all(16),
            itemCount: userReservas.length,
            itemBuilder: (context, index) {
              final reserva = userReservas[index];
              return Card(
                margin: const EdgeInsets.only(bottom: 16),
                elevation: 0,
                color: const Color(0xFFFFF9F2),
                shape: RoundedRectangleBorder(
                  side: const BorderSide(color: Color(0xFFE5DED5)),
                  borderRadius: BorderRadius.circular(20),
                ),
                child: Padding(
                  padding: const EdgeInsets.all(20),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        '${_formatFecha(reserva.fechaHoraInicio)} ${_formatHora(reserva.fechaHoraInicio)} - ${_formatHora(reserva.fechaHoraFin)}',
                        style: const TextStyle(
                          fontSize: 22,
                          fontWeight: FontWeight.w600,
                          color: Color(0xFF1A1A1A),
                        ),
                      ),
                      const SizedBox(height: 12),
                      
                      _buildBulletPoint(reserva.instalacion?.tipo ?? 'Pista de Pádel'),
                      _buildBulletPoint('${reserva.numeroParticipantes ?? 0} participantes'),
                      FutureBuilder<List<MaterialReservado>>(
                        future: ApiMaterialesService().getByReserva(reserva.idReserva!),
                        builder: (context, matSnapshot) {
                          if (matSnapshot.hasData && matSnapshot.data!.isNotEmpty) {
                            return _buildBulletPoint('Con material deportivo');
                          }
                          return const SizedBox.shrink();
                        },
                      ),
                      
                      const SizedBox(height: 20),
                      
                      Wrap(
                        spacing: 12,
                        runSpacing: 12,
                        children: [
                          _buildActionButton(
                            label: 'Detalles',
                            textColor: kGreen,
                            onPressed: () async {
                              final changed = await Navigator.push<bool>(
                                context,
                                MaterialPageRoute(
                                  builder: (context) => ReservaDetalleScreen(reserva: reserva),
                                ),
                              );
                              if (changed == true) _loadReservas();
                            },
                          ),
                          _buildActionButton(
                            label: 'Editar',
                            textColor: const Color(0xFF8D5524),
                            onPressed: () async {
                              final saved = await Navigator.push<bool>(
                                context,
                                MaterialPageRoute(
                                  builder: (context) => AddEditReservaScreen(reserva: reserva),
                                ),
                              );
                              if (saved == true) {
                                _loadReservas();
                              }
                            },
                          ),
                          
                        ],
                      ),
                    ],
                  ),
                ),
              );
            },
          );
        },
      ),
    );
  }

  Widget _buildBulletPoint(String text) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 4),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Padding(
            padding: EdgeInsets.only(top: 8, right: 8),
            child: Icon(Icons.circle, size: 6, color: Colors.black87),
          ),
          Expanded(
            child: Text(
              text,
              style: const TextStyle(
                fontSize: 18,
                color: Color(0xFF1A1A1A),
                height: 1.4,
              ),
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildActionButton({
    required String label,
    required Color textColor,
    required VoidCallback onPressed,
  }) {
    return InkWell(
      onTap: onPressed,
      borderRadius: BorderRadius.circular(30),
      child: Container(
        padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 12),
        decoration: BoxDecoration(
          border: Border.all(color: const Color(0xFFE5DED5)),
          borderRadius: BorderRadius.circular(30),
        ),
        child: Text(
          label,
          style: TextStyle(
            color: textColor,
            fontSize: 16,
            fontWeight: FontWeight.w600,
          ),
        ),
      ),
    );
  }
}
