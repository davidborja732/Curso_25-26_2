import 'package:aplicacion/services/simpsons_services.dart';
import 'package:flutter/material.dart';

class SimpsonsScreen extends StatelessWidget {
  const SimpsonsScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Simpsons'),),
      body: FutureBuilder(
          future: SimpsonsServices().getPersonajesSimsonsWitchHttp(), 
          builder: (context,snapshot){
            if (snapshot.connectionState==ConnectionState.waiting){
              return Center(child: CircularProgressIndicator(),);
            }else if (snapshot.hasError){
              return Center(child: Text('Error'),);
            }else{
              final personajes=snapshot.data?.results ?? [];
              return ListView.builder(
                itemCount: personajes.length,
                itemBuilder: (context,index){
                  final personaje=personajes[index];
                  return ListTile(
                    
                    title: Text(personaje.name),
                    subtitle: Text(personaje.occupation),
                  );
                }
              );
            }
        }
      )
    );
  }
}