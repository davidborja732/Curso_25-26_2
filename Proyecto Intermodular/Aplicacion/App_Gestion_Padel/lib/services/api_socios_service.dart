import 'package:app_gestion_padel/model/socio.dart';
import 'package:dio/dio.dart';
import 'dart:convert';
import 'dart:io' show Platform;
import 'package:flutter/foundation.dart' show kIsWeb;

class ApiSociosService {
  final dio = Dio();

  String get _baseUrl {
    if (kIsWeb) return 'http://127.0.0.1:8080/api';
    if (Platform.isAndroid) return 'http://10.0.2.2:8080/api';
    return 'http://127.0.0.1:8080/api';
  }

  Future<Socio?> login(String identificador, String contrasena) async {
    final response = await dio.post(
      '$_baseUrl/socios/login',
      data: {'identificador': identificador, 'contrasena': contrasena},
      options: Options(validateStatus: (s) => s != null && s < 500),
    );

    if (response.statusCode == 200) {
      dynamic data = response.data;
      if (data is String) data = jsonDecode(data);
      return Socio.fromJson(data as Map<String, dynamic>);
    }

    return null;
  }

  Future<List<Socio>> request() async {
    Response response = await dio.get('$_baseUrl/socios');
    dynamic data = response.data;

    if (data is String) {
      data = jsonDecode(data);
    }

    if (data is List) {
      return data
          .map((item) => Socio.fromJson(item as Map<String, dynamic>))
          .toList();
    }

    throw Exception('Unexpected API response: ${data.runtimeType}');
  }

  Future<void> create(Socio socio) async {
    await dio.post('$_baseUrl/socios', data: socio.toJson());
  }

  Future<void> update(Socio socio) async {
    await dio.put(
      '$_baseUrl/socios/${socio.dni}',
      data: {
        'nombreCompleto': socio.nombreCompleto,
        'direccion': socio.direccion,
        'telefono': socio.telefono,
        'email': socio.email,
        'cuentaBancaria': socio.cuentaBancaria,
      },
    );
  }

  Future<void> deactivate(String dni) async {
    await dio.put('$_baseUrl/socios/deactivate/$dni');
  }

  Future<void> activate(String dni) async {
    await dio.put('$_baseUrl/socios/activate/$dni');
  }

  Future<void> uploadProfileImage(String dni, dynamic imageFile) async {
    late MultipartFile multipartFile;

    if (imageFile is List<int>) {
      multipartFile = MultipartFile.fromBytes(imageFile, filename: '$dni.jpg');
    } else {
      multipartFile = await MultipartFile.fromFile(
        imageFile.path,
        filename: '$dni.jpg',
      );
    }

    final formData = FormData.fromMap({'file': multipartFile});

    await dio.post('$_baseUrl/socios/$dni/imagen', data: formData);
  }

  String getProfileImageUrl(String dni) {
    return '$_baseUrl/socios/$dni/imagen';
  }

  Future<bool> solicitarRecuperacion(String email) async {
    try {
      final response = await dio.post(
        '$_baseUrl/socios/recuperar-password/solicitar',
        queryParameters: {'email': email},
        options: Options(validateStatus: (status) => status != null && status < 500),
      );
      return response.statusCode == 200;
    } catch (e) {
      return false;
    }
  }

  Future<bool> confirmarRecuperacion({
    required String email,
    required String codigo,
    required String nuevaContrasena,
  }) async {
    try {
      final response = await dio.post(
        '$_baseUrl/socios/recuperar-password/confirmar',
        data: {
          'email': email,
          'codigo': codigo,
          'nuevaContrasena': nuevaContrasena,
        },
        options: Options(validateStatus: (status) => status != null && status < 500),
      );
      return response.statusCode == 200;
    } catch (e) {
      return false;
    }
  }
}
