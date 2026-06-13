import 'dart:convert';

import 'package:aplicacion/data/model/superhero_response.dart';
import 'package:http/http.dart' as http;

class Repository {
  Future<SuperheroResponse?> getSuperHeroInfo(String nombre) async {
    final response = await http.get(
      Uri.parse("https://dog.ceo/api/breed/$nombre/images"),
    );

  
    await Future.delayed(const Duration(seconds: 3));

    if (response.statusCode == 200) {
      final decodeJson = jsonDecode(response.body);
      return SuperheroResponse.fromJson(decodeJson);
    } else {
      return null;
    }
  }
}
