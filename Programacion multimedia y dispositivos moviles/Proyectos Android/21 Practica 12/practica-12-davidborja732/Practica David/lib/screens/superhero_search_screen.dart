import 'package:cached_network_image/cached_network_image.dart';

import 'package:aplicacion/data/model/superhero_response.dart';
import 'package:aplicacion/data/repository/repository.dart';
import 'package:flutter/material.dart';


class SuperHeroSearchScreen extends StatefulWidget {
  const SuperHeroSearchScreen({super.key});

  @override
  State<SuperHeroSearchScreen> createState() => _SuperHeroSearchScreenState();
}

class _SuperHeroSearchScreenState extends State<SuperHeroSearchScreen> {
  Future<SuperheroResponse?>? _superHeroInfo;
  final Repository repository = Repository();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Busqueda perretes')),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          children: [
            TextField(
              decoration: const InputDecoration(
                hintText: 'Busca un perrete',
                prefixIcon: Icon(Icons.search),
                border: OutlineInputBorder(),
              ),
              onChanged: (nombre) {
                setState(() {
                  _superHeroInfo = repository.getSuperHeroInfo(nombre);
                });
              },
            ),

            const SizedBox(height: 10),

            Expanded(
              child: FutureBuilder<SuperheroResponse?>(
                future: _superHeroInfo,
                builder: (context, snapshot) {
                  if (snapshot.connectionState == ConnectionState.waiting) {
                    return const Center(child: CircularProgressIndicator());
                  }

                  if (snapshot.hasError) {
                    return const Center(
                      child: Text('Error al realizar la búsqueda'),
                    );
                  }

                  if (!snapshot.hasData || snapshot.data!.urls.isEmpty) {
                    return const Center(child: Text('No existe resultado'));
                  }

                  final urls = snapshot.data!.urls;

                  return ListView.builder(
                    itemCount: urls.length,
                    itemBuilder: (context, index) {
                      return Padding(
                        padding: const EdgeInsets.only(bottom: 10),
                        child: CachedNetworkImage(
                          imageUrl: urls[index],
                          fit: BoxFit.cover,
                        ),
                      );
                    },
                  );
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}
