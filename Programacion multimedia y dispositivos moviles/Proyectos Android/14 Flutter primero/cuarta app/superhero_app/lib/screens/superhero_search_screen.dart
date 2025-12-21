import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:superhero_app/data/model/superhero_response.dart';
import 'package:superhero_app/data/repository/repository.dart';

class SuperHeroSearchScreen extends StatefulWidget {
  const SuperHeroSearchScreen({super.key});

  @override
  State<SuperHeroSearchScreen> createState() => _SuperHeroSearchScreenState();
}

class _SuperHeroSearchScreenState extends State<SuperHeroSearchScreen> {
  Future<SuperheroResponse?>? _superHeroInfo;
  Repository repository=Repository();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Busqueda superheroe'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          children: [
            TextField(
              decoration: InputDecoration(
                hintText: 'Busca un superheroe',
                prefixIcon: Icon(Icons.search),
                border: OutlineInputBorder()
              ),
              onChanged: (nombreheroe){
                setState(() {
                  _superHeroInfo=repository.getSuperHeroInfo(nombreheroe);
                });
              },
            ),
            FutureBuilder(
              future: _superHeroInfo,
              builder: (context, snapshot) {
                if (snapshot.connectionState == ConnectionState.waiting){
                  return CircularProgressIndicator();
                }else if(snapshot.hasError){
                  return Text('Error al realizar la busquda');
                }else if(!snapshot.hasData){
                  return Text('No existe resultado');
                }else{
                  // Ahora Aqui Tenemos un listado de superheroes 
                  var listaSuperheroes = snapshot.data?.listaSuperheroes;
                  return Expanded(
                    child: ListView.builder(
                      itemCount: listaSuperheroes?.length??0,
                      itemBuilder: (context, index) {
                        /*return ListTile(
                          title: Text(listaSuperheroes![index].name),
                          subtitle: Text(listaSuperheroes[index].id),
                          trailing: Icon(Icons.arrow_forward_ios),
                          onTap: (){

                          },
                        );*/
                        return CachedNetworkImage(
                          imageUrl:listaSuperheroes![index].url
                        );
                      },
                    ),
                  );
                }
              },
            )
          ],
        ),
      ),
    );
  }
}