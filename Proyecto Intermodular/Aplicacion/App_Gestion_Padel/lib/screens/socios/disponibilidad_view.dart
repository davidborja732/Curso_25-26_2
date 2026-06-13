import 'package:app_gestion_padel/model/instalacion.dart';
import 'package:app_gestion_padel/model/reserva.dart';
import 'package:app_gestion_padel/services/api_instalaciones_service.dart';
import 'package:app_gestion_padel/services/api_reservas_service.dart';
import 'package:app_gestion_padel/theme/app_theme.dart';
import 'package:flutter/material.dart';

class DisponibilidadView extends StatefulWidget {
  const DisponibilidadView({super.key});

  @override
  State<DisponibilidadView> createState() => _DisponibilidadViewState();
}

class _DisponibilidadViewState extends State<DisponibilidadView> {
  bool _cargando = true;
  List<Instalacion> _todasInstalaciones = [];
  List<Reserva> _todasReservas = [];

  String? _tipoSeleccionado;
  DateTime? _diaSeleccionado;

  static const _todasHoras = [
    '08:00','09:00','10:00','11:00','12:00','13:00',
    '14:00','15:00','16:00','17:00','18:00','19:00','20:00',
  ];

  List<String> get _tipos {
    final conjunto = <String>{};
    for (final inst in _todasInstalaciones) {
      if (inst.tipo != null) conjunto.add(inst.tipo!);
    }
    return conjunto.toList()..sort();
  }

  List<Instalacion> get _instalacionesFiltradas =>
      _todasInstalaciones.where((inst) => inst.tipo == _tipoSeleccionado).toList();

  List<DateTime> get _diasDisponibles {
    final ahora = DateTime.now();
    return List.generate(14, (i) {
      final dia = ahora.add(Duration(days: i));
      return DateTime(dia.year, dia.month, dia.day);
    });
  }

  String _etiquetaDia(DateTime dia) {
    const dias = ['Lunes','Martes','Miércoles','Jueves','Viernes','Sábado','Domingo'];
    return '${dias[dia.weekday - 1]} ${dia.day.toString().padLeft(2, '0')}/${dia.month.toString().padLeft(2, '0')}/${dia.year}';
  }

  Map<String, List<String>> _calcularDisponibilidad() {
    if (_tipoSeleccionado == null || _diaSeleccionado == null) return {};
    final instalaciones = _instalacionesFiltradas;
    final ahora = DateTime.now();
    final esHoy = _diaSeleccionado!.year == ahora.year &&
        _diaSeleccionado!.month == ahora.month &&
        _diaSeleccionado!.day == ahora.day;

    final resultado = <String, List<String>>{};

    for (final hora in _todasHoras) {
      final h = int.parse(hora.split(':')[0]);
      if (esHoy && h <= ahora.hour) continue;

      final candidateStart = DateTime(
        _diaSeleccionado!.year,
        _diaSeleccionado!.month,
        _diaSeleccionado!.day,
        h,
      );
      final candidateEnd = candidateStart.add(const Duration(hours: 1));

      final instalacionesLibres = <String>[];
      for (final inst in instalaciones) {
        final estaOcupada = _todasReservas.any((reserva) {
          if (reserva.instalacion?.idInstalacion != inst.idInstalacion) return false;
          if (reserva.activa == false) return false;
          if (reserva.fechaHoraInicio == null || reserva.fechaHoraFin == null) return false;
          final inicio = DateTime.parse(reserva.fechaHoraInicio!).toLocal();
          final fin = DateTime.parse(reserva.fechaHoraFin!).toLocal();
          
          // C < B && D > A (Fórmula matemática de solapamiento)
          return inicio.isBefore(candidateEnd) && fin.isAfter(candidateStart);
        });
        if (!estaOcupada) {
          instalacionesLibres.add(inst.nombre ?? 'Sin nombre');
        }
      }
      if (instalacionesLibres.isNotEmpty) {
        resultado[hora] = instalacionesLibres;
      }
    }
    return resultado;
  }

  @override
  void initState() {
    super.initState();
    _cargarDatos();
  }

  Future<void> _cargarDatos() async {
    setState(() => _cargando = true);
    try {
      final resultados = await Future.wait([
        ApiInstalacionesService().request(),
        ApiReservasService().request(),
      ]);
      _todasInstalaciones = resultados[0] as List<Instalacion>;
      _todasReservas = resultados[1] as List<Reserva>;
    } catch (e) {
      debugPrint('Error al cargar datos: $e');
    }
    if (mounted) setState(() => _cargando = false);
  }

  @override
  Widget build(BuildContext context) {
    if (_cargando) return const Center(child: CircularProgressIndicator());

    return SingleChildScrollView(
      padding: const EdgeInsets.all(20),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text('CONSULTAR DISPONIBILIDAD',
              style: Theme.of(context).textTheme.labelLarge?.copyWith(color: kGreen)),
          const SizedBox(height: 8),
          Text('Selecciona un deporte y un día para ver las horas libres',
              style: Theme.of(context).textTheme.bodyMedium),
          const SizedBox(height: 20),

          DropdownButtonFormField<String>(
            initialValue: _tipoSeleccionado,
            decoration: const InputDecoration(labelText: 'Deporte'),
            items: _tipos
                .map((tipo) => DropdownMenuItem(
                      value: tipo,
                      child: Text(tipo),
                    ))
                .toList(),
            onChanged: (val) => setState(() {
              _tipoSeleccionado = val;
              _diaSeleccionado = null;
            }),
          ),
          const SizedBox(height: 16),

          if (_tipoSeleccionado != null) ...[
            DropdownButtonFormField<DateTime>(
              initialValue: _diaSeleccionado,
              decoration: const InputDecoration(labelText: 'Día'),
              items: _diasDisponibles
                  .map((dia) => DropdownMenuItem(value: dia, child: Text(_etiquetaDia(dia))))
                  .toList(),
              onChanged: (val) => setState(() => _diaSeleccionado = val),
            ),
            const SizedBox(height: 24),
          ],

          if (_tipoSeleccionado != null && _diaSeleccionado != null) _construirResultados(),
        ],
      ),
    );
  }

  Widget _construirResultados() {
    final disponibilidad = _calcularDisponibilidad();

    if (disponibilidad.isEmpty) {
      return Container(
        width: double.infinity,
        padding: const EdgeInsets.all(24),
        decoration: BoxDecoration(
          color: kOrange.withValues(alpha: 0.08),
          border: Border.all(color: kOrange),
        ),
        child: Column(
          children: [
            const Icon(Icons.event_busy, color: kOrange, size: 40),
            const SizedBox(height: 12),
            Text('No hay horas disponibles',
                style: Theme.of(context).textTheme.titleMedium?.copyWith(color: kOrange)),
            const SizedBox(height: 4),
            const Text('Prueba con otro día u otro deporte',
                style: TextStyle(color: Color(0xFF777750))),
          ],
        ),
      );
    }

    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Container(
          width: double.infinity,
          padding: const EdgeInsets.symmetric(vertical: 8, horizontal: 12),
          color: kGreen,
          child: Text(
            '${disponibilidad.length} HORAS DISPONIBLES',
            style: const TextStyle(
              color: Colors.white, fontWeight: FontWeight.w800, letterSpacing: 1.2, fontSize: 13,
            ),
          ),
        ),
        const SizedBox(height: 4),
        ...disponibilidad.entries.map((entrada) {
          final hora = entrada.key;
          final horaFin = '${(int.parse(hora.split(':')[0]) + 1).toString().padLeft(2, '0')}:00';
          final instalaciones = entrada.value;

          return Container(
            margin: const EdgeInsets.only(bottom: 8),
            decoration: BoxDecoration(
              color: Colors.white,
              border: Border.all(color: kLightGreen),
            ),
            child: Row(
              children: [
                Container(
                  width: 100,
                  padding: const EdgeInsets.symmetric(vertical: 16),
                  color: kLightGreen.withValues(alpha: 0.35),
                  child: Column(
                    children: [
                      Text(hora, style: const TextStyle(
                        fontSize: 20, fontWeight: FontWeight.w800, color: kOlive)),
                      Text(horaFin, style: const TextStyle(
                        fontSize: 13, color: Color(0xFF777750))),
                    ],
                  ),
                ),
                Expanded(
                  child: Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 14, vertical: 10),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: instalaciones.map((nombre) => Padding(
                        padding: const EdgeInsets.only(bottom: 4),
                        child: Row(
                          children: [
                            const Icon(Icons.check_circle, color: kGreen, size: 16),
                            const SizedBox(width: 6),
                            Expanded(
                              child: Text(nombre, style: const TextStyle(
                                fontWeight: FontWeight.w600, color: kOlive)),
                            ),
                          ],
                        ),
                      )).toList(),
                    ),
                  ),
                ),
              ],
            ),
          );
        }),
      ],
    );
  }
}
