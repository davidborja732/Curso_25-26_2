import 'dart:convert';
import 'package:dio/dio.dart';
import 'package:http/http.dart' as http;

class JsonPlaceHolderAPIService {

  // Método para obtener lalista de usuarios mediante http
  static Future<List<dynamic>> fetchUsersWithHttp() async {

    final response = await http.get(
      Uri.parse('https://jsonplaceholder.typicode.com/users')
    );

    //Comprobamos la respuesta
    if (response.statusCode == 200 ) {
      return json.decode( response.body );
    } else {
      // Si la respuesta no es correcta lanzamos un error
      throw Exception("Error al cargar las lista de usuarios");
    }
  }

  // Metodo para obtener la listas de usuarios usando DIO
  static Future<List<dynamic>> fetchUsersWithDio() async {
    final dio = Dio();
    final response = await dio.get(
      'https://jsonplaceholder.typicode.com/users'
    );
    if (response.statusCode == 200 ){
      return response.data;
    } else {
      throw Exception("Error  al cargar usuarios con Dio");
    }
  }
  }