import 'package:app_gestion_padel/model/reserva.dart';
import 'package:dio/dio.dart';
import 'dart:convert';
import 'dart:io' show Platform;
import 'package:flutter/foundation.dart' show kIsWeb;

class ApiReservasService {
  final dio = Dio();

  String get _baseUrl {
    if (kIsWeb) return 'http://127.0.0.1:8080/api';
    if (Platform.isAndroid) return 'http://10.0.2.2:8080/api';
    return 'http://127.0.0.1:8080/api';
  }

  Future<List<Reserva>> request() async {
    Response response = await dio.get('$_baseUrl/reservas');
    dynamic data = response.data;

    if (data is String) {
      data = jsonDecode(data);
    }

    if (data is List) {
      return data
          .map((item) => Reserva.fromJson(item as Map<String, dynamic>))
          .toList();
    }

    throw Exception('Unexpected API response: ${data.runtimeType}');
  }

  Future<Reserva> create(Reserva reserva, {bool sinMaterial = false}) async {
    final data = {
      'numeroParticipantes': reserva.numeroParticipantes,
      'fechaHoraInicio': reserva.fechaHoraInicio,
      'fechaHoraFin': reserva.fechaHoraFin,
      'socio': reserva.socio?.dni, // DNI string as expected by CrearReservaDTO
      'instalacion': reserva
          .instalacion
          ?.idInstalacion, // ID integer as expected by CrearReservaDTO
    };
    Response response = await dio.post('$_baseUrl/reservas', data: data);
    return Reserva.fromJson(response.data as Map<String, dynamic>);
  }

  Future<void> update(Reserva reserva, {bool sinMaterial = false}) async {
    final data = {
      'numeroParticipantes': reserva.numeroParticipantes,
      'fechaHoraInicio': reserva.fechaHoraInicio,
      'fechaHoraFin': reserva.fechaHoraFin,
      'socio': reserva.socio?.dni,
      'instalacion': reserva.instalacion?.idInstalacion,
    };
    await dio.put('$_baseUrl/reservas/${reserva.idReserva}', data: data);
  }

  Future<void> delete(int id) async {
    await dio.delete('$_baseUrl/reservas/$id');
  }

  Future<void> cancelar(int id) async {
    await dio.put('$_baseUrl/reservas/$id/cancelar');
  }
}
