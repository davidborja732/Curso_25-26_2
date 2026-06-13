import 'package:aplicacion/api/simpsons_personajes_response.dart';

// ignore: unused_import
import 'package:dio/dio.dart';
import 'package:http/http.dart' as http;

class SimpsonsServices {
  Future<SimpsonsPersonajesResponse> getPersonajesSimsonsWitchHttp() async {
    final response = await http.get(
      Uri.parse('https://thesimpsonsapi.com/api/characters?limit=20'),
    );
    if (response.statusCode == 200) {
      return simpsonsPersonajesResponseFromJson(response.body);
    } else {
      throw Exception("Error al cargar a los simpsons");
    }
  }
}
