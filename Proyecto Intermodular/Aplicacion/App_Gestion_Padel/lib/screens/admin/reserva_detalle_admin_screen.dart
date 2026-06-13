import 'package:app_gestion_padel/model/material_reservado.dart';
import 'package:app_gestion_padel/model/reserva.dart';
import 'package:app_gestion_padel/services/api_materiales_service.dart';
import 'package:app_gestion_padel/services/api_reservas_service.dart';
import 'package:app_gestion_padel/screens/socios/add_edit_reserva_screen.dart';
import 'package:app_gestion_padel/theme/app_theme.dart';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';

class ReservaDetalleAdminScreen extends StatefulWidget {
  final Reserva reserva;

  const ReservaDetalleAdminScreen({super.key, required this.reserva});

  @override
  State<ReservaDetalleAdminScreen> createState() =>
      _ReservaDetalleAdminScreenState();
}

class _ReservaDetalleAdminScreenState
    extends State<ReservaDetalleAdminScreen> {
  List<MaterialReservado> _materiales = [];
  bool _isLoadingMaterials = true;
  bool _isLoading = false;

  @override
  void initState() {
    super.initState();
    _fetchMaterials();
  }

  Future<void> _fetchMaterials() async {
    if (widget.reserva.idReserva == null) {
      setState(() => _isLoadingMaterials = false);
      return;
    }
    try {
      final list =
          await ApiMaterialesService().getByReserva(widget.reserva.idReserva!);
      setState(() {
        _materiales = list;
        _isLoadingMaterials = false;
      });
    } catch (_) {
      setState(() => _isLoadingMaterials = false);
    }
  }

  Future<void> _cancelarReserva() async {
    final confirmed = await showDialog<bool>(
      context: context,
      builder: (ctx) => AlertDialog(
        title: const Text('Cancelar reserva'),
        content: const Text(
          '¿Seguro de que quieres cancelar esta reserva? Esta acción no se puede deshacer.',
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(ctx, false),
            child: const Text('Volver'),
          ),
          FilledButton(
            style: FilledButton.styleFrom(backgroundColor: kOrange),
            onPressed: () => Navigator.pop(ctx, true),
            child: const Text('Cancelar reserva'),
          ),
        ],
      ),
    );

    if (confirmed != true || !mounted) return;

    setState(() => _isLoading = true);
    try {
      await ApiReservasService().cancelar(widget.reserva.idReserva!);
      if (mounted) {
        Navigator.pop(context, true);
        ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(
            content: Text('Reserva cancelada'),
            backgroundColor: kOrange,
          ),
        );
      }
    } on DioException catch (e) {
      if (mounted) {
        final data = e.response?.data;
        String message = 'Error al cancelar la reserva';
        if (data is String && data.isNotEmpty) {
          message = data;
        } else if (data is Map) {
          message = (data['message'] ?? data['error'] ?? message).toString();
        }
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text(message), backgroundColor: Colors.red),
        );
      }
    } catch (e) {
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Error al cancelar: $e'), backgroundColor: Colors.red),
        );
      }
    } finally {
      if (mounted) setState(() => _isLoading = false);
    }
  }

  String _formatFecha(String? raw) {
    if (raw == null || raw.isEmpty) return 'N/A';
    try {
      final dt = DateTime.parse(raw).toLocal();
      return '${dt.day.toString().padLeft(2, '0')}/'
          '${dt.month.toString().padLeft(2, '0')}/'
          '${dt.year}  '
          '${dt.hour.toString().padLeft(2, '0')}:'
          '${dt.minute.toString().padLeft(2, '0')}';
    } catch (_) {
      return raw;
    }
  }

  @override
  Widget build(BuildContext context) {
    final r = widget.reserva;
    return Scaffold(
      appBar: AppBar(
        title: const Text('DETALLE DE RESERVA'),
        actions: [
          IconButton(
            icon: const Icon(Icons.edit_outlined),
            tooltip: 'Editar',
            onPressed: () async {
              final nav = Navigator.of(context);
              final saved = await Navigator.push<bool>(
                context,
                MaterialPageRoute(
                  builder: (_) => AddEditReservaScreen(reserva: widget.reserva),
                ),
              );
              if (saved == true && mounted) nav.pop(true);
            },
          ),
        ],
      ),
      body: Center(
        child: ConstrainedBox(
          constraints: const BoxConstraints(maxWidth: 800),
          child: SingleChildScrollView(
            padding: const EdgeInsets.all(24),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                _buildSection(
                  context,
                  title: 'Información General',
                  icon: Icons.info_outline,
                  children: [
                    _buildRow('ID Reserva', '#${r.idReserva ?? 'N/A'}'),
                    _buildRow(
                      'Fecha de creación',
                      _formatFecha(r.fechaCreacion),
                    ),
                  ],
                ),
                const SizedBox(height: 20),
                _buildSection(
                  context,
                  title: 'Horario y Participantes',
                  icon: Icons.access_time,
                  children: [
                    _buildRow('Inicio', _formatFecha(r.fechaHoraInicio)),
                    _buildRow('Fin', _formatFecha(r.fechaHoraFin)),
                    _buildRow(
                      'Participantes',
                      '${r.numeroParticipantes ?? 'N/A'}',
                    ),
                  ],
                ),
                const SizedBox(height: 20),
                _buildSection(
                  context,
                  title: 'Instalación',
                  icon: Icons.location_on_outlined,
                  children: [
                    _buildRow('Nombre', r.instalacion?.nombre ?? 'N/A'),
                    _buildRow('Tipo', r.instalacion?.tipo ?? 'N/A'),
                  ],
                ),
                const SizedBox(height: 20),
                _buildSection(
                  context,
                  title: 'Socio',
                  icon: Icons.person_outline,
                  children: [
                    _buildRow(
                      'Nombre',
                      r.socio?.nombreCompleto ?? 'N/A',
                    ),
                    _buildRow('DNI', r.socio?.dni ?? 'N/A'),
                    _buildRow('Email', r.socio?.email ?? 'N/A'),
                  ],
                ),
                if (_isLoadingMaterials)
                  const Padding(
                    padding: EdgeInsets.symmetric(vertical: 20),
                    child: Center(child: CircularProgressIndicator()),
                  )
                else if (_materiales.isNotEmpty) ...[
                  const SizedBox(height: 20),
                  _buildSection(
                    context,
                    title: 'Material Reservado',
                    icon: Icons.inventory_2_outlined,
                    children: _materiales
                        .map(
                          (m) => _buildRow(
                            m.material?.nombre ?? 'Material',
                            'x${m.cantidad}',
                          ),
                        )
                        .toList(),
                  ),
                ],
                const SizedBox(height: 32),
                SizedBox(
                  width: double.infinity,
                  child: FilledButton(
                    style: FilledButton.styleFrom(backgroundColor: kOrange),
                    onPressed: _isLoading ? null : _cancelarReserva,
                    child: _isLoading
                        ? const SizedBox(
                            width: 20,
                            height: 20,
                            child: CircularProgressIndicator(color: Colors.white, strokeWidth: 2),
                          )
                        : const Text('CANCELAR RESERVA'),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Widget _buildSection(
    BuildContext context, {
    required String title,
    required IconData icon,
    required List<Widget> children,
  }) {
    return Container(
      width: double.infinity,
      decoration: BoxDecoration(
        color: Colors.white,
        border: Border.all(color: kLightGreen),
      ),
      padding: const EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            children: [
              Icon(icon, color: kGreen, size: 20),
              const SizedBox(width: 8),
              Text(
                title.toUpperCase(),
                style: Theme.of(context).textTheme.titleMedium?.copyWith(
                  color: kGreen,
                  letterSpacing: 1.2,
                ),
              ),
            ],
          ),
          const Divider(height: 24),
          ...children,
        ],
      ),
    );
  }

  Widget _buildRow(String label, String value, {Color? valueColor}) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 8),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(
            label,
            style: const TextStyle(
              fontWeight: FontWeight.w600,
              color: Color(0xFF777750),
            ),
          ),
          Text(
            value,
            style: TextStyle(
              fontWeight: FontWeight.bold,
              color: valueColor ?? kOlive,
            ),
          ),
        ],
      ),
    );
  }
}
