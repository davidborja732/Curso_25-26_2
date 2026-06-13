import 'package:app_gestion_padel/model/instalacion.dart';
import 'package:app_gestion_padel/model/material_deportivo.dart';
import 'package:app_gestion_padel/model/material_reservado.dart';
import 'package:app_gestion_padel/model/reserva.dart';
import 'package:app_gestion_padel/model/socio.dart';
import 'package:app_gestion_padel/services/api_instalaciones_service.dart';
import 'package:app_gestion_padel/services/api_materiales_service.dart';
import 'package:app_gestion_padel/services/api_reservas_service.dart';
import 'package:app_gestion_padel/services/api_socios_service.dart';
import 'package:dio/dio.dart';
import 'package:app_gestion_padel/services/user_session.dart';
import 'package:app_gestion_padel/theme/app_theme.dart';
import 'package:flutter/material.dart';

class AddEditReservaScreen extends StatefulWidget {
  final Reserva? reserva;

  const AddEditReservaScreen({super.key, this.reserva});

  @override
  State<AddEditReservaScreen> createState() => _AddEditReservaScreenState();
}

class _AddEditReservaScreenState extends State<AddEditReservaScreen> {
  final _formKey = GlobalKey<FormState>();
  final _numParticipantesController = TextEditingController();

  DateTime _selectedDate = DateTime.now();
  TimeOfDay _startTime = const TimeOfDay(hour: 9, minute: 0);
  TimeOfDay _endTime = const TimeOfDay(hour: 10, minute: 30);

  Instalacion? _selectedInstalacion;
  List<Instalacion> _instalaciones = [];
  bool _isLoading = false;
  bool _isFetchingInstalaciones = true;
  List<MaterialDeportivo> _availableMaterials = [];
  List<MaterialReservado> _selectedMaterials = [];

  Socio? _selectedSocio;
  List<Socio> _socios = [];
  bool _isFetchingSocios = false;

  bool get _needsSocioSelector =>
      UserSession().loggedInUser == null && widget.reserva == null;

  @override
  void initState() {
    super.initState();
    _fetchInstalaciones();
    _fetchMaterials();
    if (_needsSocioSelector) _fetchSocios();

    if (widget.reserva != null) {
      _fetchReservedMaterials();
      _numParticipantesController.text =
          widget.reserva!.numeroParticipantes?.toString() ?? '';
      if (widget.reserva!.fechaHoraInicio != null) {
        final start = DateTime.parse(
          widget.reserva!.fechaHoraInicio!,
        ).toLocal();
        _selectedDate = DateTime(start.year, start.month, start.day);
        _startTime = TimeOfDay(hour: start.hour, minute: start.minute);
      }
      if (widget.reserva!.fechaHoraFin != null) {
        final end = DateTime.parse(widget.reserva!.fechaHoraFin!).toLocal();
        _endTime = TimeOfDay(hour: end.hour, minute: end.minute);
      }
      _selectedInstalacion = widget.reserva!.instalacion;
    }
  }

  Future<void> _fetchInstalaciones() async {
    try {
      final list = await ApiInstalacionesService().request();
      setState(() {
        _instalaciones = list;
        _isFetchingInstalaciones = false;

        if (widget.reserva != null && widget.reserva!.instalacion != null) {
          final match = _instalaciones.where(
            (i) =>
                i.idInstalacion == widget.reserva!.instalacion?.idInstalacion,
          );

          if (match.isNotEmpty) {
            _selectedInstalacion = match.first;
          }
        }
      });
    } catch (e) {
      setState(() => _isFetchingInstalaciones = false);
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: Text('Error al cargar instalaciones: $e'),
            backgroundColor: Colors.red,
          ),
        );
      }
    }
  }

  Future<void> _fetchReservedMaterials() async {
    if (widget.reserva?.idReserva == null) return;
    try {
      final list = await ApiMaterialesService().getByReserva(widget.reserva!.idReserva!);
      setState(() {
        _selectedMaterials = list;
      });
    } catch (e) {
      debugPrint('Error fetching reserved materials: $e');
    }
  }

  Future<void> _fetchMaterials() async {
    try {
      final list = await ApiMaterialesService().request();
      setState(() {
        _availableMaterials = list;
      });
    } catch (e) {
      debugPrint('Error loading materials: $e');
    }
  }

  Future<void> _fetchSocios() async {
    setState(() => _isFetchingSocios = true);
    try {
      final list = await ApiSociosService().request();
      setState(() {
        _socios = list.where((s) => (s.desactivado ?? 0) == 0).toList();
        _isFetchingSocios = false;
      });
    } catch (e) {
      setState(() => _isFetchingSocios = false);
    }
  }

  Future<void> _showSocioSelector() async {
    String query = '';
    final selected = await showDialog<Socio>(
      context: context,
      builder: (ctx) => StatefulBuilder(
        builder: (ctx, setDialogState) {
          final filtered = _socios.where((s) {
            final q = query.toLowerCase();
            return (s.nombreCompleto ?? '').toLowerCase().contains(q) ||
                (s.dni ?? '').toLowerCase().contains(q);
          }).toList();
          return AlertDialog(
            title: const Text('Seleccionar socio'),
            content: SizedBox(
              width: double.maxFinite,
              height: 400,
              child: Column(
                children: [
                  TextField(
                    autofocus: true,
                    decoration: const InputDecoration(
                      hintText: 'Buscar por nombre o DNI...',
                      prefixIcon: Icon(Icons.search),
                    ),
                    onChanged: (v) => setDialogState(() => query = v),
                  ),
                  const SizedBox(height: 8),
                  Expanded(
                    child: ListView.builder(
                      itemCount: filtered.length,
                      itemBuilder: (ctx, i) {
                        final s = filtered[i];
                        return ListTile(
                          title: Text(s.nombreCompleto ?? ''),
                          subtitle: Text(s.dni ?? ''),
                          onTap: () => Navigator.pop(ctx, s),
                        );
                      },
                    ),
                  ),
                ],
              ),
            ),
            actions: [
              TextButton(
                onPressed: () => Navigator.pop(ctx),
                child: const Text('Cancelar'),
              ),
            ],
          );
        },
      ),
    );
    if (selected != null) setState(() => _selectedSocio = selected);
  }

  void _showAddMaterialDialog() {
    showDialog(
      context: context,
      builder: (ctx) {
        return AlertDialog(
          title: const Text('Seleccionar Material'),
          content: SizedBox(
            width: double.maxFinite,
            child: ListView.builder(
              shrinkWrap: true,
              itemCount: _availableMaterials.length,
              itemBuilder: (ctx, idx) {
                final mat = _availableMaterials[idx];
                return ListTile(
                  title: Text(mat.nombre ?? ''),
                  subtitle: Text(mat.descripcion ?? ''),
                  onTap: () {
                    setState(() {
                      _selectedMaterials.add(MaterialReservado(material: mat, cantidad: 1));
                    });
                    Navigator.pop(ctx);
                  },
                );
              },
            ),
          ),
          actions: [
            TextButton(
              onPressed: () => Navigator.pop(ctx),
              child: const Text('Cancelar'),
            ),
          ],
        );
      },
    );
  }

  Future<void> _selectDate() async {
    final picked = await showDatePicker(
      context: context,
      initialDate: _selectedDate,
      firstDate: DateTime.now().subtract(const Duration(days: 365)),
      lastDate: DateTime.now().add(const Duration(days: 365)),
    );
    if (picked != null) setState(() => _selectedDate = picked);
  }

  Future<void> _selectTime(bool isStart) async {
    final picked = await showTimePicker(
      context: context,
      initialTime: isStart ? _startTime : _endTime,
    );
    if (picked != null) {
      setState(() {
        if (isStart) {
          _startTime = picked;
        } else {
          _endTime = picked;
        }
      });
    }
  }

  String _formatDateTime(DateTime date, TimeOfDay time) {
    final dt = DateTime(
      date.year,
      date.month,
      date.day,
      time.hour,
      time.minute,
    );
    return dt.toIso8601String();
  }

  void _save() async {
    if (!_formKey.currentState!.validate()) return;
    if (_selectedInstalacion == null) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Por favor, selecciona una instalación')),
      );
      return;
    }
    if (_needsSocioSelector && _selectedSocio == null) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Por favor, selecciona un socio')),
      );
      return;
    }

    setState(() => _isLoading = true);

    try {
      final reserva = Reserva(
        idReserva: widget.reserva?.idReserva,
        numeroParticipantes: int.tryParse(_numParticipantesController.text),
        fechaHoraInicio: _formatDateTime(_selectedDate, _startTime),
        fechaHoraFin: _formatDateTime(_selectedDate, _endTime),
        fechaCreacion:
            widget.reserva?.fechaCreacion ?? DateTime.now().toIso8601String(),
        instalacion: _selectedInstalacion,
        socio: widget.reserva?.socio ?? _selectedSocio ?? UserSession().loggedInUser,
        materiales: _selectedMaterials,
      );

      Reserva savedReserva;
      if (widget.reserva == null) {
        savedReserva = await ApiReservasService().create(reserva);
      } else {
        await ApiReservasService().update(reserva);
        savedReserva = reserva;
      }

      if (savedReserva.idReserva != null) {
        try {
          await ApiMaterialesService().deleteByReserva(savedReserva.idReserva!);
        } catch (_) {}

        for (var item in _selectedMaterials) {
          if (item.material?.idMaterial != null) {
            await ApiMaterialesService().associate(
              savedReserva.idReserva!,
              item.material!.idMaterial!,
              item.cantidad,
            );
          }
        }
      }

      if (mounted) {
        Navigator.pop(context, true);
        ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(
            content: Text('Reserva guardada con éxito'),
            backgroundColor: kGreen,
          ),
        );
      }
    } on DioException catch (e) {
      if (mounted) {
        final data = e.response?.data;
        String message = 'Error al guardar la reserva';
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
        title: Text(
          widget.reserva == null ? 'NUEVA RESERVA' : 'EDITAR RESERVA',
        ),
      ),
      body: _isFetchingInstalaciones
          ? const Center(child: CircularProgressIndicator())
          : Center(
              child: ConstrainedBox(
                constraints: const BoxConstraints(maxWidth: 800),
                child: SingleChildScrollView(
                  padding: const EdgeInsets.all(24),
                  child: Form(
                    key: _formKey,
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.stretch,
                      children: [
                        Text(
                          'DETALLES DE LA RESERVA',
                          style: Theme.of(context).textTheme.labelLarge
                              ?.copyWith(color: kGreen),
                        ),
                        const SizedBox(height: 24),

                        Row(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Expanded(
                              flex: 2,
                              child: DropdownButtonFormField<Instalacion>(
                                value: _instalaciones
                                        .contains(_selectedInstalacion)
                                    ? _selectedInstalacion
                                    : null,
                                decoration: const InputDecoration(
                                  labelText: 'Instalación',
                                ),
                                items: _instalaciones
                                    .map(
                                      (i) => DropdownMenuItem(
                                        value: i,
                                        child: Text(i.nombre ?? 'Sin nombre'),
                                      ),
                                    )
                                    .toList(),
                                onChanged: (val) =>
                                    setState(() => _selectedInstalacion = val),
                                validator: (val) =>
                                    val == null ? 'Campo requerido' : null,
                              ),
                            ),
                            const SizedBox(width: 12),
                            Expanded(
                              child: TextFormField(
                                controller: _numParticipantesController,
                                keyboardType: TextInputType.number,
                                decoration: const InputDecoration(
                                  labelText: 'Participantes',
                                ),
                                validator: (val) =>
                                    val == null || val.isEmpty
                                        ? 'Campo requerido'
                                        : null,
                              ),
                            ),
                          ],
                        ),
                        if (_needsSocioSelector) ...[
                          const SizedBox(height: 20),
                          _isFetchingSocios
                              ? const LinearProgressIndicator()
                              : ListTile(
                                  title: const Text('Socio'),
                                  subtitle: Text(
                                    _selectedSocio?.nombreCompleto ??
                                        'Sin seleccionar',
                                    style: TextStyle(
                                      color: _selectedSocio == null
                                          ? Colors.red
                                          : null,
                                    ),
                                  ),
                                  trailing: const Icon(
                                    Icons.person_search_outlined,
                                    color: kGreen,
                                  ),
                                  onTap: _showSocioSelector,
                                  tileColor: Colors.white,
                                  shape: const RoundedRectangleBorder(
                                    side: BorderSide(color: kLightGreen),
                                  ),
                                ),
                        ],
                        const SizedBox(height: 20),

                        Row(
                          children: [
                            Expanded(
                              child: ListTile(
                                title: const Text('Fecha'),
                                subtitle: Text(
                                  '${_selectedDate.day}/${_selectedDate.month}/${_selectedDate.year}',
                                ),
                                trailing: const Icon(
                                  Icons.calendar_today,
                                  color: kGreen,
                                ),
                                onTap: _selectDate,
                                tileColor: Colors.white,
                                shape: const RoundedRectangleBorder(
                                  side: BorderSide(color: kLightGreen),
                                ),
                              ),
                            ),
                            const SizedBox(width: 12),
                            Expanded(
                              child: ListTile(
                                title: const Text('Inicio'),
                                subtitle: Text(_startTime.format(context)),
                                trailing: const Icon(
                                  Icons.access_time,
                                  color: kGreen,
                                ),
                                onTap: () => _selectTime(true),
                                tileColor: Colors.white,
                                shape: const RoundedRectangleBorder(
                                  side: BorderSide(color: kLightGreen),
                                ),
                              ),
                            ),
                            const SizedBox(width: 12),
                            Expanded(
                              child: ListTile(
                                title: const Text('Fin'),
                                subtitle: Text(_endTime.format(context)),
                                trailing: const Icon(
                                  Icons.access_time,
                                  color: kGreen,
                                ),
                                onTap: () => _selectTime(false),
                                tileColor: Colors.white,
                                shape: const RoundedRectangleBorder(
                                  side: BorderSide(color: kLightGreen),
                                ),
                              ),
                            ),
                          ],
                        ),

                        const SizedBox(height: 24),
                        Text(
                          'SELECCIÓN DE MATERIAL',
                          style: Theme.of(context).textTheme.labelLarge
                              ?.copyWith(color: kGreen),
                        ),
                    const SizedBox(height: 12),
                    ..._selectedMaterials.asMap().entries.map((entry) {
                      final idx = entry.key;
                      final item = entry.value;
                      return Container(
                        margin: const EdgeInsets.only(bottom: 12),
                        padding: const EdgeInsets.all(16),
                        decoration: BoxDecoration(
                          color: kLightGreen.withValues(alpha: 0.3),
                          border: Border.all(color: kLightGreen),
                          borderRadius: BorderRadius.circular(12),
                        ),
                        child: Row(
                          children: [
                            Expanded(
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                    item.material?.nombre ?? 'Material',
                                    style: const TextStyle(
                                      fontWeight: FontWeight.bold,
                                      fontSize: 16,
                                    ),
                                  ),
                                  Text(
                                    item.material?.descripcion ?? '',
                                    style: TextStyle(
                                      color: Colors.grey[600],
                                      fontSize: 12,
                                    ),
                                  ),
                                ],
                              ),
                            ),
                            Row(
                              children: [
                                IconButton(
                                  icon: const Icon(Icons.remove_circle, color: kGreen),
                                  onPressed: () {
                                    if (item.cantidad > 1) {
                                      setState(() => item.cantidad--);
                                    }
                                  },
                                ),
                                Container(
                                  padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 4),
                                  decoration: BoxDecoration(
                                    color: Colors.white,
                                    borderRadius: BorderRadius.circular(8),
                                    border: Border.all(color: kLightGreen),
                                  ),
                                  child: Text(
                                    '${item.cantidad}',
                                    style: const TextStyle(fontWeight: FontWeight.bold),
                                  ),
                                ),
                                IconButton(
                                  icon: const Icon(Icons.add_circle, color: kGreen),
                                  onPressed: () {
                                    setState(() => item.cantidad++);
                                  },
                                ),
                              ],
                            ),
                            const SizedBox(width: 8),
                            IconButton(
                              icon: const Icon(Icons.delete_outline, color: Colors.red),
                              onPressed: () {
                                setState(() => _selectedMaterials.removeAt(idx));
                              },
                            ),
                          ],
                        ),
                      );
                    }),
                    OutlinedButton.icon(
                      onPressed: _showAddMaterialDialog,
                      icon: const Icon(Icons.add),
                      label: const Text('AÑADIR MATERIAL'),
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
                          : const Text('GUARDAR CAMBIOS'),
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
