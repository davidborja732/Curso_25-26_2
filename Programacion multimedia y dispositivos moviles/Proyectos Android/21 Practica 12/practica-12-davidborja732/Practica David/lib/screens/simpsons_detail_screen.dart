import 'package:aplicacion/api/simpsons_personajes_response.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';

class SimpsonsDetailScreen extends StatelessWidget {
  final Result character;

  const SimpsonsDetailScreen({super.key, required this.character});

  @override
  Widget build(BuildContext context) {
    String edadTexto;
    if (character.age != null) {
      edadTexto = "Edad: ${character.age}";
    } else {
      edadTexto = "Edad: Desconocida";
    }

    String generoTexto;
    if (character.gender == Gender.MALE) {
      generoTexto = "Genero: Masculino";
    } else {
      generoTexto = "Genero: Femenino";
    }

    String fechaTexto;
    if (character.birthdate != null) {
      fechaTexto =
          "Fecha de nacimiento: ${character.birthdate.toString().split(' ')[0]}";
    } else {
      fechaTexto = "Fecha de nacimiento: Desconocida";
    }

    String estadoTexto;
    if (character.status == Status.ALIVE) {
      estadoTexto = "Estado: Vivo";
    } else {
      estadoTexto = "Estado: Fallecido";
    }

    return Scaffold(
      appBar: AppBar(
        title: Text(character.name),
        backgroundColor: Colors.amber,
      ),
      body: Column(
        children: [
          const SizedBox(height: 10),

          Hero(
            tag: character.id,
            child: SizedBox(
              height: 230,
              child: CachedNetworkImage(
                imageUrl:
                    'https://cdn.thesimpsonsapi.com/500/character/${character.id}.webp',
                fit: BoxFit.contain,
                placeholder: (context, url) =>
                    const Center(child: CircularProgressIndicator()),
                errorWidget: (context, url, error) =>
                    const Icon(Icons.error, size: 40),
              ),
            ),
          ),

          const SizedBox(height: 6),

          Text(
            character.occupation,
            style: const TextStyle(
              fontSize: 18,
              fontStyle: FontStyle.italic,
              color: Colors.grey,
            ),
            textAlign: TextAlign.center,
          ),

          const Divider(height: 5),
          Column(
            children: [
              ListTile(
                dense: true,
                title: Text(edadTexto, style: const TextStyle(fontSize: 18)),
              ),
              ListTile(
                dense: true,
                title: Text(generoTexto, style: const TextStyle(fontSize: 18)),
              ),
              ListTile(
                dense: true,
                title: Text(fechaTexto, style: const TextStyle(fontSize: 18)),
              ),
              ListTile(
                dense: true,
                title: Text(estadoTexto, style: const TextStyle(fontSize: 18)),
              ),
            ],
          ),

          const SizedBox(height: 5),
        ],
      ),
    );
  }
}
