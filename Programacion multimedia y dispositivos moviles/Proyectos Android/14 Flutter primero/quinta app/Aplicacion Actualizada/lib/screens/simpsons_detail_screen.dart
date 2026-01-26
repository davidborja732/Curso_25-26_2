import 'package:aplicacion/api/simpsons_personajes_response.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';

class SimpsonsDetailScreen extends StatelessWidget {
  final Result character;

  const SimpsonsDetailScreen({super.key, required this.character});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(character.name),
        backgroundColor: Colors.amber,
      ),
      body: Column(
        children: [
          const SizedBox(height: 20),
          Hero(
            tag: character.id,
            child: SizedBox(
              height: 250,
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
          const SizedBox(height: 10),
          Text(
            character.occupation,
            style: const TextStyle(
              fontSize: 18,
              fontStyle: FontStyle.italic,
              color: Colors.grey,
            ),
            textAlign: TextAlign.center,
          ),
          const Divider(height: 30),

      
          Expanded(
            child: ListView.builder(
              itemCount: character.phrases.length,
              itemBuilder: (context, index) {
                return Padding(
                  padding: const EdgeInsets.symmetric(
                    vertical: 6.0,
                    horizontal: 16.0, 
                  ),
                  child: Text(
                    character.phrases[index],
                    style: const TextStyle(fontSize: 16),
                  ),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
