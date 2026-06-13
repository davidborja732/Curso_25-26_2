import 'package:app_gestion_padel/model/reserva.dart';
import 'package:app_gestion_padel/screens/admin/reserva_detalle_admin_screen.dart';
import 'package:app_gestion_padel/screens/socios/add_edit_reserva_screen.dart';
import 'package:app_gestion_padel/services/api_reservas_service.dart';
import 'package:app_gestion_padel/theme/app_theme.dart';
import 'package:app_gestion_padel/widgets/admin_app_bar.dart';
import 'package:app_gestion_padel/widgets/admin_drawer.dart';
import 'package:flutter/material.dart';

enum _ViewMode { lista, calendario }

class ReservasScreen extends StatefulWidget {
  const ReservasScreen({super.key});

  @override
  State<ReservasScreen> createState() => _ReservasScreenState();
}

class _ReservasScreenState extends State<ReservasScreen> {
  late Future<List<Reserva>> _reservasFuture;
  List<Reserva> _reservas = [];
  List<Reserva>? _filtered;
  DateTime _selectedDay = DateTime.now();
  _ViewMode _viewMode = _ViewMode.lista;

  static const _meses = [
    'enero', 'febrero', 'marzo', 'abril', 'mayo', 'junio',
    'julio', 'agosto', 'septiembre', 'octubre', 'noviembre', 'diciembre',
  ];
  static const _diasSemana = [
    'lunes', 'martes', 'miércoles', 'jueves', 'viernes', 'sábado', 'domingo',
  ];

  @override
  void initState() {
    super.initState();
    _reload();
  }

  void _reload() {
    _reservasFuture = ApiReservasService().request();
    _reservasFuture.then((reservas) => setState(() => _reservas = reservas));
  }

  List<Reserva> get _reservasDelDia => _reservas.where((r) {
        if (r.fechaHoraInicio == null) return false;
        try {
          final dt = DateTime.parse(r.fechaHoraInicio!).toLocal();
          return dt.year == _selectedDay.year &&
              dt.month == _selectedDay.month &&
              dt.day == _selectedDay.day;
        } catch (_) {
          return false;
        }
      }).toList();

  String _formatDia(DateTime d) {
    final dia = _diasSemana[d.weekday - 1];
    final mes = _meses[d.month - 1];
    return '$dia, ${d.day} de $mes de ${d.year}';
  }

  String _formatFecha(String? raw) {
    if (raw == null || raw.isEmpty) return '';
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

  Future<void> _pickDay() async {
    final picked = await showDatePicker(
      context: context,
      initialDate: _selectedDay,
      firstDate: DateTime(2020),
      lastDate: DateTime(2030),
    );
    if (picked != null) {
      setState(() {
        _selectedDay = picked;
        _filtered = null;
      });
    }
  }

  Widget _viewButton(_ViewMode mode, IconData icon, String label) {
    if (_viewMode == mode) {
      return FilledButton.icon(
        icon: Icon(icon, size: 16),
        label: Text(label),
        onPressed: () {},
        style: FilledButton.styleFrom(
          backgroundColor: kOlive,
          foregroundColor: kCream,
        ),
      );
    }
    return OutlinedButton.icon(
      icon: Icon(icon, size: 16),
      label: Text(label),
      onPressed: () => setState(() => _viewMode = mode),
    );
  }

  Widget _buildListView() {
    final reservas = _filtered ?? _reservasDelDia;
    if (reservas.isEmpty) {
      return const Center(child: Text('No hay reservas para este día.'));
    }
    return SingleChildScrollView(
      child: Center(
        child: SingleChildScrollView(
          scrollDirection: Axis.horizontal,
          child: DataTable(
          showCheckboxColumn: false,
          columns: const [
            DataColumn(label: Text('ID')),
            DataColumn(label: Text('Instalación')),
            DataColumn(label: Text('Socio')),
            DataColumn(label: Text('Inicio')),
            DataColumn(label: Text('Fin')),
            DataColumn(label: Text('Participantes')),
          ],
          rows: reservas.map((r) {
            return DataRow(
              onSelectChanged: (_) async {
                final changed = await Navigator.push<bool>(
                  context,
                  MaterialPageRoute(
                    builder: (_) => ReservaDetalleAdminScreen(reserva: r),
                  ),
                );
                if (changed == true) setState(() => _reload());
              },
              cells: [
                DataCell(Text('${r.idReserva ?? ''}')),
                DataCell(Text(r.instalacion?.nombre ?? '')),
                DataCell(Text(r.socio?.nombreCompleto ?? '')),
                DataCell(Text(_formatFecha(r.fechaHoraInicio))),
                DataCell(Text(_formatFecha(r.fechaHoraFin))),
                DataCell(Text('${r.numeroParticipantes ?? ''}')),
              ],
            );
          }).toList(),
        ),
      ),
    ),
    );
  }

  Widget _buildCalendarView(List<Reserva> reservas) {
    final year = _selectedDay.year;
    final month = _selectedDay.month;
    final firstDay = DateTime(year, month, 1);
    final daysInMonth = DateTime(year, month + 1, 0).day;
    final startOffset = firstDay.weekday - 1; // Monday = 0
    final today = DateTime.now();

    final Map<int, int> countPorDia = {};
    for (final r in reservas) {
      if (r.fechaHoraInicio == null) continue;
      try {
        final dt = DateTime.parse(r.fechaHoraInicio!).toLocal();
        if (dt.year == year && dt.month == month) {
          countPorDia[dt.day] = (countPorDia[dt.day] ?? 0) + 1;
        }
      } catch (_) {}
    }

    final rows = ((startOffset + daysInMonth) / 7).ceil();
    final mesLabel =
        '${_meses[month - 1][0].toUpperCase()}${_meses[month - 1].substring(1)} $year';

    return Column(
      children: [
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            IconButton(
              icon: const Icon(Icons.chevron_left),
              onPressed: () => setState(
                () => _selectedDay = DateTime(year, month - 1, 1),
              ),
            ),
            Text(mesLabel, style: Theme.of(context).textTheme.titleMedium),
            IconButton(
              icon: const Icon(Icons.chevron_right),
              onPressed: () => setState(
                () => _selectedDay = DateTime(year, month + 1, 1),
              ),
            ),
          ],
        ),
        const SizedBox(height: 4),
        Row(
          children: ['L', 'M', 'X', 'J', 'V', 'S', 'D'].map((d) {
            return Expanded(
              child: Center(
                child: Text(
                  d,
                  style: const TextStyle(
                    fontWeight: FontWeight.bold,
                    fontSize: 12,
                  ),
                ),
              ),
            );
          }).toList(),
        ),
        const Divider(height: 16),
        Expanded(
          child: Column(
            children: List.generate(rows, (rowIdx) {
              return Expanded(
                child: Row(
                  children: List.generate(7, (colIdx) {
                    final day = rowIdx * 7 + colIdx - startOffset + 1;
                    if (day < 1 || day > daysInMonth) {
                      return const Expanded(child: SizedBox());
                    }
                    final count = countPorDia[day] ?? 0;
                    final isSelected = _selectedDay.day == day;
                    final isToday = today.year == year &&
                        today.month == month &&
                        today.day == day;
                    return Expanded(
                      child: GestureDetector(
                        onTap: () => setState(
                          () => _selectedDay = DateTime(year, month, day),
                        ),
                        child: Container(
                          margin: const EdgeInsets.all(2),
                          decoration: BoxDecoration(
                            color: isSelected ? kGreen : Colors.transparent,
                            border: isToday && !isSelected
                                ? Border.all(color: kGreen)
                                : null,
                          ),
                          child: Column(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Text(
                                '$day',
                                style: TextStyle(
                                  fontWeight: isSelected || isToday
                                      ? FontWeight.bold
                                      : FontWeight.normal,
                                  color: isSelected ? kOlive : null,
                                ),
                              ),
                              if (count > 0)
                                Container(
                                  width: 20,
                                  height: 20,
                                  margin: const EdgeInsets.only(top: 2),
                                  decoration: const BoxDecoration(
                                    color: kOrange,
                                    shape: BoxShape.circle,
                                  ),
                                  child: Center(
                                    child: Text(
                                      '$count',
                                      style: const TextStyle(
                                        color: Colors.white,
                                        fontSize: 11,
                                        fontWeight: FontWeight.bold,
                                      ),
                                    ),
                                  ),
                                ),
                            ],
                          ),
                        ),
                      ),
                    );
                  }),
                ),
              );
            }),
          ),
        ),
      ],
    );
  }

  @override
  Widget build(BuildContext context) {
    final delDia = _reservasDelDia;

    return Scaffold(
      drawer: const AdminDrawer(currentRoute: '/reservas-lista'),
      appBar: const AdminAppBar(
        title: 'GESTIÓN DE RESERVAS',
        showLogout: true,
      ),
      body: Center(
        child: ConstrainedBox(
          constraints: const BoxConstraints(maxWidth: 1400),
          child: Padding(
            padding: const EdgeInsets.all(16),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  children: [
                    OutlinedButton.icon(
                      icon: const Icon(Icons.calendar_today, size: 16),
                      label: Text(_formatDia(_selectedDay)),
                      onPressed: _pickDay,
                    ),
                    const SizedBox(width: 12),
                    Text(
                      '${delDia.length} reserva${delDia.length == 1 ? '' : 's'}',
                      style: Theme.of(context).textTheme.bodyMedium,
                    ),
                    const Spacer(),
                    _viewButton(_ViewMode.lista, Icons.list, 'Lista'),
                    const SizedBox(width: 8),
                    _viewButton(
                      _ViewMode.calendario,
                      Icons.calendar_view_month,
                      'Calendario',
                    ),
                    const SizedBox(width: 8),
                    FilledButton.icon(
                      icon: const Icon(Icons.add, size: 16),
                      label: const Text('Nueva reserva'),
                      onPressed: () async {
                        final saved = await Navigator.push<bool>(
                          context,
                          MaterialPageRoute(
                            builder: (_) => const AddEditReservaScreen(),
                          ),
                        );
                        if (saved == true) setState(() => _reload());
                      },
                    ),
                  ],
                ),
                const SizedBox(height: 24),
                Expanded(
                  child: FutureBuilder<List<Reserva>>(
                    future: _reservasFuture,
                    builder: (context, snapshot) {
                      if (snapshot.connectionState != ConnectionState.done) {
                        return const Center(child: CircularProgressIndicator());
                      }
                      if (snapshot.hasError) {
                        return Center(
                          child: Text(
                            'Error al cargar reservas: ${snapshot.error}',
                            style: const TextStyle(color: Colors.red),
                          ),
                        );
                      }
                      if (_viewMode == _ViewMode.lista) {
                        return _buildListView();
                      }
                      return _buildCalendarView(snapshot.data ?? []);
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
