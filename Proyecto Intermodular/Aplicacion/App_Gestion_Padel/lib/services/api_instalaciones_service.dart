import 'package:app_gestion_padel/model/instalacion.dart';
import 'package:dio/dio.dart';
import 'dart:convert';
import 'dart:io' show Platform;
import 'package:flutter/foundation.dart' show kIsWeb;

class ApiInstalacionesService {
  final dio = Dio();

  String get _baseUrl {
    if (kIsWeb) return 'http://localhost:8080';
    if (Platform.isAndroid) return 'http://10.0.2.2:8080';
    return 'http://localhost:8080';
  }

  Future<Instalacion> create(Instalacion instalacion) async {
    final response = await dio.post(
      '$_baseUrl/instalaciones',
      data: instalacion.toJson()..remove('idInstalacion'),
    );
    dynamic data = response.data;
    if (data is String) data = jsonDecode(data);
    return Instalacion.fromJson(data as Map<String, dynamic>);
  }

  Future<Instalacion> update(Instalacion instalacion) async {
    final response = await dio.put(
      '$_baseUrl/instalaciones/${instalacion.idInstalacion}',
      data: instalacion.toJson(),
    );
    dynamic data = response.data;
    if (data is String) data = jsonDecode(data);
    return Instalacion.fromJson(data as Map<String, dynamic>);
  }

  Future<Response> delete(int id) async {
    return dio.delete(
      '$_baseUrl/instalaciones/$id',
      options: Options(validateStatus: (s) => s != null && s < 600),
    );
  }

  Future<List<Instalacion>> request() async {
    Response response = await dio.get('$_baseUrl/instalaciones');
    dynamic data = response.data;

    if (data is String) {
      data = jsonDecode(data);
    }

    if (data is List) {
      return data
          .map((item) => Instalacion.fromJson(item as Map<String, dynamic>))
          .toList();
    }

    throw Exception('Unexpected API response: ${data.runtimeType}');
  }
}
