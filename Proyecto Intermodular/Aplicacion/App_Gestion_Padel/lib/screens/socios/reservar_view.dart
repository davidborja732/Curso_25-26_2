import 'package:app_gestion_padel/model/instalacion.dart';
import 'package:app_gestion_padel/model/material_deportivo.dart';
import 'package:app_gestion_padel/model/material_reservado.dart';
import 'package:app_gestion_padel/model/reserva.dart';
import 'package:app_gestion_padel/services/api_instalaciones_service.dart';
import 'package:app_gestion_padel/services/api_materiales_service.dart';
import 'package:app_gestion_padel/services/api_reservas_service.dart';
import 'package:app_gestion_padel/services/user_session.dart';
import 'package:app_gestion_padel/theme/app_theme.dart';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';

class ReservarView extends StatefulWidget {
  const ReservarView({super.key});

  @override
  State<ReservarView> createState() => _ReservarViewState();
}

class _ReservarViewState extends State<ReservarView> {
  int _step = 0;
  bool _isLoading = true;
  bool _isSaving = false;

  List<Instalacion> _allInstalaciones = [];
  List<Reserva> _allReservas = [];
  List<MaterialDeportivo> _allMaterials = [];

  String? _selectedTipo;
  Instalacion? _selectedInstalacion;
  DateTime? _selectedDay;
  String? _selectedHour;
  int _numParticipantes = 2;
  List<MaterialReservado> _selectedMaterials = [];

  List<String> get _tipos {
    final set = <String>{};
    for (final i in _allInstalaciones) {
      if (i.tipo != null) set.add(i.tipo!);
    }
    return set.toList()..sort();
  }

  List<Instalacion> get _filteredInstalaciones =>
      _allInstalaciones.where((i) => i.tipo == _selectedTipo).toList();

  List<DateTime> get _availableDays {
    final now = DateTime.now();
    return List.generate(14, (i) {
      final d = now.add(Duration(days: i));
      return DateTime(d.year, d.month, d.day);
    });
  }

  static const _allHours = [
    '08:00',
    '09:00',
    '10:00',
    '11:00',
    '12:00',
    '13:00',
    '14:00',
    '15:00',
    '16:00',
    '17:00',
    '18:00',
    '19:00',
    '20:00',
  ];

  List<String> get _freeHours {
    if (_selectedInstalacion == null || _selectedDay == null) return [];
    
    final now = DateTime.now();
    final isToday =
        _selectedDay!.year == now.year &&
        _selectedDay!.month == now.month &&
        _selectedDay!.day == now.day;

    return _allHours.where((h) {
      final hourPart = int.parse(h.split(':')[0]);
      final minutePart = int.parse(h.split(':')[1]);
      
      // Si es hoy, no permitir horas pasadas o actuales
      if (isToday && hourPart <= now.hour) return false;

      final candidateStart = DateTime(
        _selectedDay!.year,
        _selectedDay!.month,
        _selectedDay!.day,
        hourPart,
        minutePart,
      );
      final candidateEnd = candidateStart.add(const Duration(hours: 1));

      // Comprobar solapamiento con cualquier reserva existente activa de esta instalación
      for (final r in _allReservas) {
        if (r.instalacion?.idInstalacion != _selectedInstalacion!.idInstalacion) {
          continue;
        }
        if (r.activa == false) continue;
        if (r.fechaHoraInicio == null || r.fechaHoraFin == null) continue;

        final rStart = DateTime.parse(r.fechaHoraInicio!).toLocal();
        final rEnd = DateTime.parse(r.fechaHoraFin!).toLocal();

        // Fórmula matemática de solapamiento de intervalos: C < B && D > A
        if (rStart.isBefore(candidateEnd) && rEnd.isAfter(candidateStart)) {
          return false; // Slot solapado, no disponible
        }
      }
      return true;
    }).toList();
  }

  @override
  void initState() {
    super.initState();
    _loadData();
  }

  Future<void> _loadData() async {
    setState(() => _isLoading = true);
    try {
      final results = await Future.wait([
        ApiInstalacionesService().request(),
        ApiReservasService().request(),
        ApiMaterialesService().request(),
      ]);
      _allInstalaciones = results[0] as List<Instalacion>;
      _allReservas = results[1] as List<Reserva>;
      _allMaterials = results[2] as List<MaterialDeportivo>;
    } catch (e) {
      debugPrint('Error loading data: $e');
    }
    if (mounted) setState(() => _isLoading = false);
  }

  String _dayLabel(DateTime d) {
    const dias = [
      'Lunes',
      'Martes',
      'Miércoles',
      'Jueves',
      'Viernes',
      'Sábado',
      'Domingo',
    ];
    return '${dias[d.weekday - 1]} ${d.day.toString().padLeft(2, '0')}/${d.month.toString().padLeft(2, '0')}/${d.year}';
  }

  int _materialUsedInSlot(int idMaterial) {
    if (_selectedInstalacion == null ||
        _selectedDay == null ||
        _selectedHour == null)
      return 0;
    int total = 0;
    final slotStart = DateTime(
      _selectedDay!.year,
      _selectedDay!.month,
      _selectedDay!.day,
      int.parse(_selectedHour!.split(':')[0]),
      int.parse(_selectedHour!.split(':')[1]),
    );
    final slotEnd = slotStart.add(const Duration(hours: 1));

    for (final r in _allReservas) {
      if (r.activa == false) continue;
      if (r.fechaHoraInicio == null || r.fechaHoraFin == null) continue;
      final rStart = DateTime.parse(r.fechaHoraInicio!).toLocal();
      final rEnd = DateTime.parse(r.fechaHoraFin!).toLocal();
      if (rStart.isBefore(slotEnd) && rEnd.isAfter(slotStart)) {
        if (r.materiales != null) {
          for (final m in r.materiales!) {
            if (m.material?.idMaterial == idMaterial) {
              total += m.cantidad;
            }
          }
        }
      }
    }
    return total;
  }

  void _goBack() {
    if (_step == 0) return;
    setState(() {
      if (_step == 1) {
        _selectedMaterials = [];
      }
      _step--;
    });
  }

  Future<void> _confirm() async {
    setState(() => _isSaving = true);
    try {
      final startDt = DateTime(
        _selectedDay!.year,
        _selectedDay!.month,
        _selectedDay!.day,
        int.parse(_selectedHour!.split(':')[0]),
      );
      final endDt = startDt.add(const Duration(hours: 1));

      final reserva = Reserva(
        numeroParticipantes: _numParticipantes,
        fechaHoraInicio: startDt.toIso8601String(),
        fechaHoraFin: endDt.toIso8601String(),
        fechaCreacion: DateTime.now().toIso8601String(),
        instalacion: _selectedInstalacion,
        socio: UserSession().loggedInUser,
      );

      final saved = await ApiReservasService().create(reserva);

      if (saved.idReserva != null) {
        for (final m in _selectedMaterials) {
          if (m.material?.idMaterial != null) {
            await ApiMaterialesService().associate(
              saved.idReserva!,
              m.material!.idMaterial!,
              m.cantidad,
            );
          }
        }
      }

      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(
            content: Text('¡Reserva creada con éxito!'),
            backgroundColor: kGreen,
          ),
        );
        setState(() {
          _step = 0;
          _selectedTipo = null;
          _selectedInstalacion = null;
          _selectedDay = null;
          _selectedHour = null;
          _numParticipantes = 2;
          _selectedMaterials = [];
        });
        _loadData();
      }
    } on DioException catch (e) {
      if (mounted) {
        final data = e.response?.data;
        String msg = 'Error al crear la reserva';
        if (data is String && data.isNotEmpty) {
          msg = data;
        } else if (data is Map) {
          msg = (data['message'] ?? data['error'] ?? msg).toString();
        }
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text(msg), backgroundColor: Colors.red),
        );
      }
    } catch (e) {
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Error: $e'), backgroundColor: Colors.red),
        );
      }
    } finally {
      if (mounted) setState(() => _isSaving = false);
    }
  }

  @override
  Widget build(BuildContext context) {
    if (_isLoading) return const Center(child: CircularProgressIndicator());

    return Column(
      children: [
        _buildStepIndicator(),
        Expanded(
          child: SingleChildScrollView(
            padding: const EdgeInsets.all(20),
            child: AnimatedSwitcher(
              duration: const Duration(milliseconds: 300),
              child: _buildCurrentStep(),
            ),
          ),
        ),
      ],
    );
  }

  Widget _buildStepIndicator() {
    const labels = ['Pista y Hora', 'Material', 'Confirmar'];
    return Container(
      color: Colors.white,
      padding: const EdgeInsets.symmetric(vertical: 12, horizontal: 16),
      child: Row(
        children: List.generate(labels.length, (i) {
          final isActive = i == _step;
          final isDone = i < _step;
          return Expanded(
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                Container(
                  width: 28,
                  height: 28,
                  decoration: BoxDecoration(
                    shape: BoxShape.circle,
                    color: isDone
                        ? kGreen
                        : isActive
                        ? kOlive
                        : kLightGreen,
                  ),
                  child: Center(
                    child: isDone
                        ? const Icon(Icons.check, size: 16, color: Colors.white)
                        : Text(
                            '${i + 1}',
                            style: TextStyle(
                              color: isActive ? kCream : kOlive,
                              fontWeight: FontWeight.bold,
                              fontSize: 12,
                            ),
                          ),
                  ),
                ),
                const SizedBox(height: 4),
                Text(
                  labels[i],
                  style: TextStyle(
                    fontSize: 11,
                    fontWeight: isActive ? FontWeight.bold : FontWeight.normal,
                    color: isActive ? kOlive : const Color(0xFF777750),
                  ),
                ),
              ],
            ),
          );
        }),
      ),
    );
  }

  Widget _buildCurrentStep() {
    switch (_step) {
      case 0:
        return _buildStep0PistaDiaHora(key: const ValueKey(0));
      case 1:
        return _buildStep1Material(key: const ValueKey(1));
      case 2:
        return _buildStep2Resumen(key: const ValueKey(2));
      default:
        return const SizedBox.shrink();
    }
  }

  // ── STEP 0: Pista + Día + Hora ──
  Widget _buildStep0PistaDiaHora({Key? key}) {
    final tipos = _tipos;
    final instalaciones = _filteredInstalaciones;
    final freeHours = _freeHours;

    return Column(
      key: key,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          'SELECCIONA PISTA, DÍA Y HORA',
          style: Theme.of(
            context,
          ).textTheme.labelLarge?.copyWith(color: kGreen),
        ),
        const SizedBox(height: 8),
        Text(
          'Elige dónde y cuándo quieres jugar',
          style: Theme.of(context).textTheme.bodyMedium,
        ),
        const SizedBox(height: 20),

        // Deporte
        DropdownButtonFormField<String>(
          initialValue: _selectedTipo,
          decoration: const InputDecoration(labelText: 'Deporte'),
          items: tipos
              .map((t) => DropdownMenuItem(value: t, child: Text(t)))
              .toList(),
          onChanged: (val) => setState(() {
            _selectedTipo = val;
            _selectedInstalacion = null;
            _selectedHour = null;
          }),
        ),
        const SizedBox(height: 16),

        // Instalación
        if (_selectedTipo != null) ...[
          DropdownButtonFormField<Instalacion>(
            initialValue: _selectedInstalacion,
            decoration: const InputDecoration(labelText: 'Instalación'),
            items: instalaciones
                .map(
                  (i) => DropdownMenuItem(
                    value: i,
                    child: Text(i.nombre ?? 'Sin nombre'),
                  ),
                )
                .toList(),
            onChanged: (val) => setState(() {
              _selectedInstalacion = val;
              _selectedHour = null;
            }),
          ),
          const SizedBox(height: 16),
        ],

        // Día
        if (_selectedInstalacion != null) ...[
          DropdownButtonFormField<DateTime>(
            initialValue: _selectedDay,
            decoration: const InputDecoration(labelText: 'Día'),
            items: _availableDays
                .map(
                  (d) => DropdownMenuItem(value: d, child: Text(_dayLabel(d))),
                )
                .toList(),
            onChanged: (val) => setState(() {
              _selectedDay = val;
              _selectedHour = null;
            }),
          ),
          const SizedBox(height: 16),
        ],

        // Hora
        if (_selectedInstalacion != null && _selectedDay != null) ...[
          if (freeHours.isEmpty)
            Container(
              padding: const EdgeInsets.all(16),
              decoration: BoxDecoration(
                color: kOrange.withValues(alpha: 0.1),
                border: Border.all(color: kOrange),
              ),
              child: const Row(
                children: [
                  Icon(Icons.info_outline, color: kOrange),
                  SizedBox(width: 8),
                  Expanded(
                    child: Text(
                      'No hay horas disponibles para este día e instalación.',
                      style: TextStyle(
                        color: kOrange,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                  ),
                ],
              ),
            )
          else
            DropdownButtonFormField<String>(
              initialValue: _selectedHour,
              decoration: const InputDecoration(labelText: 'Hora disponible'),
              items: freeHours.map((h) {
                final end =
                    '${(int.parse(h.split(':')[0]) + 1).toString().padLeft(2, '0')}:00';
                return DropdownMenuItem(value: h, child: Text('$h - $end'));
              }).toList(),
              onChanged: (val) => setState(() => _selectedHour = val),
            ),
          const SizedBox(height: 24),
          if (_selectedHour != null)
            SizedBox(
              width: double.infinity,
              child: FilledButton(
                onPressed: () => setState(() => _step = 1),
                child: const Text('CONTINUAR'),
              ),
            ),
        ],
      ],
    );
  }

  // ── STEP 1: Material + Participantes ──
  Widget _buildStep1Material({Key? key}) {
    return Column(
      key: key,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        _buildBackButton(),
        const SizedBox(height: 12),
        Text(
          'PARTICIPANTES Y MATERIAL',
          style: Theme.of(
            context,
          ).textTheme.labelLarge?.copyWith(color: kGreen),
        ),
        const SizedBox(height: 20),

        Row(
          children: [
            const Text(
              'Participantes:',
              style: TextStyle(fontWeight: FontWeight.w600, color: kOlive),
            ),
            const SizedBox(width: 16),
            IconButton(
              icon: const Icon(Icons.remove_circle, color: kGreen),
              onPressed: _numParticipantes > 1
                  ? () => setState(() => _numParticipantes--)
                  : null,
            ),
            Container(
              padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
              decoration: BoxDecoration(
                color: Colors.white,
                border: Border.all(color: kLightGreen),
              ),
              child: Text(
                '$_numParticipantes',
                style: const TextStyle(
                  fontWeight: FontWeight.bold,
                  fontSize: 18,
                ),
              ),
            ),
            IconButton(
              icon: const Icon(Icons.add_circle, color: kGreen),
              onPressed: () => setState(() => _numParticipantes++),
            ),
          ],
        ),
        const SizedBox(height: 24),

        Text(
          'MATERIAL DISPONIBLE',
          style: Theme.of(
            context,
          ).textTheme.labelLarge?.copyWith(color: kGreen),
        ),
        const SizedBox(height: 8),
        Text(
          'Selecciona el material que necesitas para tu reserva',
          style: Theme.of(context).textTheme.bodyMedium,
        ),
        const SizedBox(height: 12),

        if (_allMaterials.isEmpty)
          const Text('No hay materiales registrados.')
        else
          ..._allMaterials.map((mat) {
            final used = _materialUsedInSlot(mat.idMaterial!);
            final selected = _selectedMaterials.where(
              (m) => m.material?.idMaterial == mat.idMaterial,
            );
            final alreadySelected = selected.isNotEmpty;
            final currentQty = alreadySelected ? selected.first.cantidad : 0;

            return Container(
              margin: const EdgeInsets.only(bottom: 10),
              padding: const EdgeInsets.all(14),
              decoration: BoxDecoration(
                color: alreadySelected
                    ? kLightGreen.withValues(alpha: 0.3)
                    : Colors.white,
                border: Border.all(
                  color: alreadySelected ? kGreen : kLightGreen,
                ),
              ),
              child: Row(
                children: [
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          mat.nombre ?? '',
                          style: const TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 15,
                          ),
                        ),
                        if (mat.descripcion != null &&
                            mat.descripcion!.isNotEmpty)
                          Text(
                            mat.descripcion!,
                            style: const TextStyle(
                              color: Color(0xFF777750),
                              fontSize: 12,
                            ),
                          ),
                        Text(
                          'En uso esta hora: $used',
                          style: const TextStyle(
                            color: Color(0xFF777750),
                            fontSize: 11,
                          ),
                        ),
                      ],
                    ),
                  ),
                  if (used > 0)
                    const Text(
                      'Reservado',
                      style: TextStyle(
                        color: Colors.red,
                        fontWeight: FontWeight.bold,
                        fontSize: 13,
                      ),
                    )
                  else if (!alreadySelected)
                    IconButton(
                      icon: const Icon(Icons.add_circle_outline, color: kGreen),
                      onPressed: () {
                        setState(() {
                          _selectedMaterials.add(
                            MaterialReservado(material: mat, cantidad: 1),
                          );
                        });
                      },
                    )
                  else
                    Row(
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        IconButton(
                          icon: const Icon(
                            Icons.remove_circle,
                            color: kGreen,
                            size: 22,
                          ),
                          onPressed: () {
                            setState(() {
                              if (currentQty <= 1) {
                                _selectedMaterials.removeWhere(
                                  (m) =>
                                      m.material?.idMaterial == mat.idMaterial,
                                );
                              } else {
                                selected.first.cantidad--;
                              }
                            });
                          },
                        ),
                        Container(
                          padding: const EdgeInsets.symmetric(
                            horizontal: 10,
                            vertical: 4,
                          ),
                          decoration: BoxDecoration(
                            color: Colors.white,
                            border: Border.all(color: kLightGreen),
                            borderRadius: BorderRadius.circular(6),
                          ),
                          child: Text(
                            '$currentQty',
                            style: const TextStyle(fontWeight: FontWeight.bold),
                          ),
                        ),
                        IconButton(
                          icon: const Icon(
                            Icons.add_circle,
                            color: kGreen,
                            size: 22,
                          ),
                          onPressed: () =>
                              setState(() => selected.first.cantidad++),
                        ),
                        IconButton(
                          icon: const Icon(
                            Icons.delete_outline,
                            color: Colors.red,
                            size: 22,
                          ),
                          onPressed: () {
                            setState(() {
                              _selectedMaterials.removeWhere(
                                (m) => m.material?.idMaterial == mat.idMaterial,
                              );
                            });
                          },
                        ),
                      ],
                    ),
                ],
              ),
            );
          }),

        const SizedBox(height: 24),
        SizedBox(
          width: double.infinity,
          child: FilledButton(
            onPressed: () => setState(() => _step = 2),
            child: const Text('VER RESUMEN'),
          ),
        ),
      ],
    );
  }

  // ── STEP 2: Resumen ──
  Widget _buildStep2Resumen({Key? key}) {
    final endHour =
        '${(int.parse(_selectedHour!.split(':')[0]) + 1).toString().padLeft(2, '0')}:00';
    final user = UserSession().loggedInUser;

    return Column(
      key: key,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        _buildBackButton(),
        const SizedBox(height: 12),
        Text(
          'RESUMEN DE TU RESERVA',
          style: Theme.of(
            context,
          ).textTheme.labelLarge?.copyWith(color: kGreen),
        ),
        const SizedBox(height: 20),

        _buildSummarySection('Información General', Icons.info_outline, [
          _buildSummaryRow('Socio', user?.nombreCompleto ?? 'N/A'),
          _buildSummaryRow('DNI', user?.dni ?? 'N/A'),
        ]),
        const SizedBox(height: 14),

        _buildSummarySection('Instalación', Icons.location_on_outlined, [
          _buildSummaryRow('Tipo', _selectedTipo ?? 'N/A'),
          _buildSummaryRow('Nombre', _selectedInstalacion?.nombre ?? 'N/A'),
        ]),
        const SizedBox(height: 14),

        _buildSummarySection('Horario', Icons.access_time, [
          _buildSummaryRow('Día', _dayLabel(_selectedDay!)),
          _buildSummaryRow('Hora', '$_selectedHour - $endHour'),
          _buildSummaryRow('Participantes', '$_numParticipantes'),
        ]),
        const SizedBox(height: 14),

        if (_selectedMaterials.isNotEmpty) ...[
          _buildSummarySection(
            'Material Reservado',
            Icons.inventory_2_outlined,
            _selectedMaterials
                .map(
                  (m) => _buildSummaryRow(
                    m.material?.nombre ?? 'Material',
                    'x${m.cantidad}',
                  ),
                )
                .toList(),
          ),
          const SizedBox(height: 14),
        ],

        const SizedBox(height: 10),
        SizedBox(
          width: double.infinity,
          child: FilledButton(
            onPressed: _isSaving ? null : _confirm,
            child: _isSaving
                ? const SizedBox(
                    width: 20,
                    height: 20,
                    child: CircularProgressIndicator(
                      color: Colors.white,
                      strokeWidth: 2,
                    ),
                  )
                : const Text('CONFIRMAR RESERVA'),
          ),
        ),
      ],
    );
  }

  // ── shared widgets ──
  Widget _buildBackButton() {
    return Align(
      alignment: Alignment.centerLeft,
      child: TextButton.icon(
        onPressed: _goBack,
        icon: const Icon(Icons.arrow_back, size: 18),
        label: const Text('Volver'),
      ),
    );
  }

  Widget _buildSummarySection(
    String title,
    IconData icon,
    List<Widget> children,
  ) {
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

  Widget _buildSummaryRow(String label, String value) {
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
          Flexible(
            child: Text(
              value,
              style: const TextStyle(
                fontWeight: FontWeight.bold,
                color: kOlive,
              ),
              textAlign: TextAlign.end,
            ),
          ),
        ],
      ),
    );
  }
}
