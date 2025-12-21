// ignore: unused_import
import 'package:http/http.dart';
import 'package:superhero_app/data/model/superhero_detail_response.dart';

class SuperheroResponse {
  final String response;
  final List<SuperheroDetailResponse> listaSuperheroes;
  SuperheroResponse({required this.response, required this.listaSuperheroes});
  // Necesito devolver un objeto SuperHeroResponse con los campos que me interesan 
  factory SuperheroResponse.fromJson(Map<String,dynamic> json){
    var lista = json['results'] as List;
    // El metodo .map es como un for que recorre la lista y la aplica a la funcion que pasamos
    List<SuperheroDetailResponse> listaSuperheroes = lista
      .map((heroe)=>SuperheroDetailResponse.fromJson(heroe))
      .toList();
    final response=json['response'];
    return SuperheroResponse(
      response: response, 
      listaSuperheroes: listaSuperheroes,
    );  
  }
}