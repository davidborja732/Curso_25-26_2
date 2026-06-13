import 'package:aplicacion/api/simpsons_personajes_response.dart';
import 'package:aplicacion/screens/simpsons_detail_screen.dart';
import 'package:aplicacion/services/simpsons_services.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';

class SimpsonsScreen extends StatelessWidget {
  const SimpsonsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Simpsons'),
        backgroundColor: Colors.yellow,
        foregroundColor: Colors.black,
      ),
      body: FutureBuilder<SimpsonsPersonajesResponse>(
        future: SimpsonsServices().getPersonajesSimsonsWitchHttp(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          } else {
            final allPersonajes = snapshot.data?.results ?? [];
            final personajes = allPersonajes.take(20).toList();
            return Padding(
              padding: const EdgeInsets.all(8.0),
              child: GridView.builder(
                gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount: 2,
                  childAspectRatio: 0.7,
                  crossAxisSpacing: 10,
                  mainAxisSpacing: 10,
                ),
                itemCount: personajes.length,
                itemBuilder: (context, index) {
                  final personaje = personajes[index];
                  return GestureDetector(
                    onTap: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) =>
                              SimpsonsDetailScreen(character: personaje),
                        ),
                      );
                    },
                    child: Card(
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(10),
                        side: BorderSide(color: Colors.grey.shade300),
                      ),
                      elevation: 2,
                      clipBehavior: Clip.antiAlias,
                      child: Column(
                        children: [
                          Expanded(
                            child: Hero(
                              tag: personaje.id,
                              child: Container(
                                color: Colors.white,
                                width: double.infinity,
                                padding: const EdgeInsets.all(8.0),
                                child: CachedNetworkImage(
                                  imageUrl:
                                      'https://cdn.thesimpsonsapi.com/500/character/${personaje.id}.webp',
                                  fit: BoxFit.contain,
                                  placeholder: (context, url) => const Center(
                                    child: CircularProgressIndicator(),
                                  ),
                                  errorWidget: (context, url, error) =>
                                      const Icon(Icons.error),
                                ),
                              ),
                            ),
                          ),
                          Container(
                            width: double.infinity,
                            color: Colors.yellow,
                            padding: const EdgeInsets.symmetric(
                              vertical: 8.0,
                              horizontal: 4.0,
                            ),
                            child: Text(
                              personaje.name,
                              textAlign: TextAlign.center,
                              style: const TextStyle(
                                fontWeight: FontWeight.bold,
                                fontSize: 14,
                                color: Colors.black,
                              ),
                              maxLines: 2,
                              overflow: TextOverflow.ellipsis,
                            ),
                          ),
                        ],
                      ),
                    ),
                  );
                },
              ),
            );
          }
        },
      ),
    );
  }
}
