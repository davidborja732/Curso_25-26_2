import 'package:app_gestion_padel/model/reserva.dart';
import 'package:app_gestion_padel/model/material_reservado.dart';
import 'package:app_gestion_padel/services/api_materiales_service.dart';
import 'package:app_gestion_padel/services/api_reservas_service.dart';
import 'package:app_gestion_padel/theme/app_theme.dart';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';

class ReservaDetalleScreen extends StatefulWidget {
  final Reserva reserva;

  const ReservaDetalleScreen({super.key, required this.reserva});

  @override
  State<ReservaDetalleScreen> createState() => _ReservaDetalleScreenState();
}

class _ReservaDetalleScreenState extends State<ReservaDetalleScreen> {
  List<MaterialReservado> _materiales = [];
  bool _isLoadingMaterials = true;
  bool _isLoading = false;

  @override
  void initState() {
    super.initState();
    _fetchMaterials();
  }

  Future<void> _fetchMaterials() async {
    if (widget.reserva.idReserva == null) return;
    try {
      final list =
          await ApiMaterialesService().getByReserva(widget.reserva.idReserva!);
      setState(() {
        _materiales = list;
        _isLoadingMaterials = false;
      });
    } catch (e) {
      setState(() => _isLoadingMaterials = false);
      debugPrint('Error fetching materials: $e');
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
      return '${dt.day.toString().padLeft(2, '0')}/${dt.month.toString().padLeft(2, '0')}/${dt.year} ${dt.hour.toString().padLeft(2, '0')}:${dt.minute.toString().padLeft(2, '0')}';
    } catch (_) {
      return raw;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('DETALLE DE RESERVA'),
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(20),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            _buildDetailSection(
              context,
              title: 'Información General',
              icon: Icons.info_outline,
              children: [
                _buildDetailRow('ID Reserva', '#${widget.reserva.idReserva}'),
                _buildDetailRow('Estado', 'Confirmada', valueColor: Colors.green),
                _buildDetailRow('Fecha Creación', _formatFecha(widget.reserva.fechaCreacion)),
              ],
            ),
            const SizedBox(height: 20),
            _buildDetailSection(
              context,
              title: 'Horario y Participantes',
              icon: Icons.access_time,
              children: [
                _buildDetailRow('Inicio', _formatFecha(widget.reserva.fechaHoraInicio)),
                _buildDetailRow('Fin', _formatFecha(widget.reserva.fechaHoraFin)),
                _buildDetailRow('Participantes', '${widget.reserva.numeroParticipantes} personas'),
              ],
            ),
            const SizedBox(height: 20),
            _buildDetailSection(
              context,
              title: 'Instalación',
              icon: Icons.location_on_outlined,
              children: [
                _buildDetailRow('Nombre', widget.reserva.instalacion?.nombre ?? 'N/A'),
                _buildDetailRow('Tipo', widget.reserva.instalacion?.tipo ?? 'N/A'),
              ],
            ),
            const SizedBox(height: 20),
            _buildDetailSection(
              context,
              title: 'Socio',
              icon: Icons.person_outline,
              children: [
                _buildDetailRow('Nombre', widget.reserva.socio?.nombreCompleto ?? 'N/A'),
                _buildDetailRow('DNI', widget.reserva.socio?.dni ?? 'N/A'),
                _buildDetailRow('Email', widget.reserva.socio?.email ?? 'N/A'),
              ],
            ),
            if (_isLoadingMaterials)
              const Padding(
                padding: EdgeInsets.symmetric(vertical: 20),
                child: Center(child: CircularProgressIndicator()),
              )
            else if (_materiales.isNotEmpty) ...[
              const SizedBox(height: 20),
              _buildDetailSection(
                context,
                title: 'Material Reservado',
                icon: Icons.inventory_2_outlined,
                children: _materiales
                    .map((m) => _buildDetailRow(
                        m.material?.nombre ?? 'Material', 'x${m.cantidad}'))
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
    );
  }

  Widget _buildDetailSection(BuildContext context, {required String title, required IconData icon, required List<Widget> children}) {
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

  Widget _buildDetailRow(String label, String value, {Color? valueColor}) {
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
