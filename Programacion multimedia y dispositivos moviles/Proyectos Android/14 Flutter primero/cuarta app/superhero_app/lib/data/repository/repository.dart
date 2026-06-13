import 'dart:convert';

import 'package:superhero_app/data/model/superhero_response.dart';
import 'package:http/http.dart' as http;

class Repository {
  Future<SuperheroResponse?> getSuperHeroInfo(String nombre) async{
    final response=await http.get(
      Uri.parse("https://www.superheroapi.com/api.php/4ae458af4d2528c0c14423b71970be77/search/$nombre"),
    );

    if (response.statusCode==200){
      var decodeJson = jsonDecode(response.body);
      // Aqui ya tenemos el formato perfecto para poder usar nuestro constructor 
      SuperheroResponse superheroResponse=SuperheroResponse.fromJson(decodeJson);
      return superheroResponse;
    }else{
      // Podemos hacer un par de cosas
      //throw Exception('Fallo al cargar informacion de superheroe');
      return null;
    }
  }
}