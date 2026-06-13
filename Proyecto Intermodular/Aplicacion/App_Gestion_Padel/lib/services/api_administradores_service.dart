import 'package:app_gestion_padel/model/administrador.dart';
import 'package:dio/dio.dart';
import 'dart:convert';
import 'dart:io' show Platform;
import 'package:flutter/foundation.dart' show kIsWeb;

class ApiAdministradoresService {
  final dio = Dio();

  String get _baseUrl {
    if (kIsWeb) return 'http://127.0.0.1:8080/api';
    if (Platform.isAndroid) return 'http://10.0.2.2:8080/api';
    return 'http://127.0.0.1:8080/api';
  }

  Future<bool> login(String nombre, String contrasena) async {
    final response = await dio.post(
      '$_baseUrl/administradores/login',
      data: {'identificador': nombre, 'contrasena': contrasena},
      options: Options(validateStatus: (s) => s != null && s < 500),
    );
    return response.statusCode == 200;
  }

  Future<List<Administrador>> request() async {
    Response response = await dio.get('$_baseUrl/administradores');
    dynamic data = response.data;

    if (data is String) {
      data = jsonDecode(data);
    }

    if (data is List) {
      return data
          .map((item) => Administrador.fromJson(item as Map<String, dynamic>))
          .toList();
    }

    throw Exception('Unexpected API response: ${data.runtimeType}');
  }
}
