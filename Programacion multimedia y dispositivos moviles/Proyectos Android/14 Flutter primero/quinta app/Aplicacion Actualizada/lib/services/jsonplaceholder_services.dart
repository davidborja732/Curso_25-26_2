import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:http/http.dart' as http;

class JsonplaceholderAPIServices {
  // metodo para obtener la lista de usuarios mediante http
  static Future<List<dynamic>> fetchuserswithHttp() async{
    final response = await http.get(
      Uri.parse("https://jsonplaceholder.typicode.com/users")
    );
    // comprobamos la respuesta
    if (response.statusCode==200){
      return json.decode(response.body);
    }else{
      // si la respuesta no es correcta lanzamos un error
      throw Exception("Error al cargar lista usuarios");
    }
  }
  static Future<List<dynamic>> fetchuserswithDio() async{
    final dio=Dio();
    final response = await dio.get(
      "https://jsonplaceholder.typicode.com/users"
    );
    // comprobamos la respuesta
    if (response.statusCode==200){
      return response.data;
    }else{
      // si la respuesta no es correcta lanzamos un error
      throw Exception("Error al cargar lista usuarios");
    }
  }
}