import 'package:app_gestion_padel/model/material_deportivo.dart';
import 'package:app_gestion_padel/model/material_reservado.dart';
import 'package:dio/dio.dart';
import 'dart:convert';
import 'dart:io' show Platform;
import 'package:flutter/foundation.dart' show kIsWeb;

class ApiMaterialesService {
  final dio = Dio();

  String get _baseUrl {
    if (kIsWeb) return 'http://localhost:8080';
    if (Platform.isAndroid) return 'http://10.0.2.2:8080';
    return 'http://localhost:8080';
  }

  Future<MaterialDeportivo> create(MaterialDeportivo material) async {
    final response = await dio.post(
      '$_baseUrl/api/material-deportivo',
      data: material.toJson()..remove('idMaterial'),
    );
    dynamic data = response.data;
    if (data is String) data = jsonDecode(data);
    return MaterialDeportivo.fromJson(data as Map<String, dynamic>);
  }

  Future<MaterialDeportivo> update(MaterialDeportivo material) async {
    final response = await dio.put(
      '$_baseUrl/api/material-deportivo/${material.idMaterial}',
      data: material.toJson(),
    );
    dynamic data = response.data;
    if (data is String) data = jsonDecode(data);
    return MaterialDeportivo.fromJson(data as Map<String, dynamic>);
  }

  Future<Response> delete(int id) async {
    return dio.delete(
      '$_baseUrl/api/material-deportivo/$id',
      options: Options(validateStatus: (s) => s != null && s < 600),
    );
  }

  Future<List<MaterialDeportivo>> request() async {
    Response response = await dio.get('$_baseUrl/api/material-deportivo');
    dynamic data = response.data;

    if (data is String) {
      data = jsonDecode(data);
    }

    if (data is List) {
      return data
          .map(
            (item) => MaterialDeportivo.fromJson(item as Map<String, dynamic>),
          )
          .toList();
    }

    throw Exception('Unexpected API response: ${data.runtimeType}');
  }

  Future<List<MaterialReservado>> getByReserva(int idReserva) async {
    Response response = await dio.get(
      '$_baseUrl/reservas/$idReserva/materiales',
    );
    dynamic data = response.data;

    if (data is String) {
      data = jsonDecode(data);
    }

    if (data is List) {
      return data
          .map(
            (item) => MaterialReservado.fromJson(item as Map<String, dynamic>),
          )
          .toList();
    }
    return [];
  }

  Future<void> associate(int idReserva, int idMaterial, int cantidad) async {
    final data = {
      'id': {'idReserva': idReserva, 'idMaterial': idMaterial},
      'reserva': {'idReserva': idReserva},
      'material': {'idMaterial': idMaterial},
      'cantidad': cantidad,
    };
    await dio.post('$_baseUrl/material-reservado', data: data);
  }

  Future<void> deleteByReserva(int idReserva) async {
    await dio.delete('$_baseUrl/material-reservado/reserva/$idReserva');
  }
}
