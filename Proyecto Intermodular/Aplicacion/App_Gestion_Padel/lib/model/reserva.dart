import 'package:app_gestion_padel/model/instalacion.dart';
import 'package:app_gestion_padel/model/socio.dart';
import 'package:app_gestion_padel/model/material_reservado.dart';

class Reserva {
  String? fechaCreacion;
  String? fechaHoraFin;
  String? fechaHoraInicio;
  int? idReserva;
  Instalacion? instalacion;
  int? numeroParticipantes;
  Socio? socio;
  bool? sinMaterial;
  List<MaterialReservado>? materiales;
  bool? activa;

  Reserva({
    this.fechaCreacion,
    this.fechaHoraFin,
    this.fechaHoraInicio,
    this.idReserva,
    this.instalacion,
    this.numeroParticipantes,
    this.socio,
    this.sinMaterial,
    this.materiales,
    this.activa,
  });

  Reserva.fromJson(Map<String, dynamic> json) {
    fechaCreacion = json['fechaCreacion'];
    fechaHoraFin = json['fechaHoraFin'];
    fechaHoraInicio = json['fechaHoraInicio'];
    idReserva = json['idReserva'];
    instalacion = json['instalacion'] != null
        ? Instalacion.fromJson(json['instalacion'] as Map<String, dynamic>)
        : null;
    numeroParticipantes = json['numeroParticipantes'];
    socio = json['socio'] != null
        ? Socio.fromJson(json['socio'] as Map<String, dynamic>)
        : null;
    sinMaterial = json['sinMaterial'];
    activa = json['activa'];
    
    final dynamic materialsJson = json['materiales'] ?? json['materialesReservados'] ?? json['material_reservado'];
    if (materialsJson != null && materialsJson is List) {
      materiales = <MaterialReservado>[];
      for (var v in materialsJson) {
        materiales!.add(MaterialReservado.fromJson(v as Map<String, dynamic>));
      }
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['fechaCreacion'] = fechaCreacion;
    data['fechaHoraFin'] = fechaHoraFin;
    data['fechaHoraInicio'] = fechaHoraInicio;
    data['idReserva'] = idReserva;
    if (instalacion != null) {
      data['instalacion'] = instalacion!.toJson();
    }
    data['numeroParticipantes'] = numeroParticipantes;
    if (socio != null) {
      data['socio'] = socio!.toJson();
    }
    data['sinMaterial'] = sinMaterial;
    data['activa'] = activa;
    if (materiales != null) {
      data['materiales'] = materiales!.map((v) => v.toJson()).toList();
    }
    return data;
  }
}