import 'package:flutter/material.dart';
import 'package:repaso/personas_response.dart';

class PruebaScreen extends StatelessWidget {
  final List<PersonasResponse> personas;

  const PruebaScreen({Key? key, required this.personas}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Personas')),
      body: ListView.builder(
        itemCount: personas.length,
        itemBuilder: (context, index) {
          final persona = personas[index];
          return ListTile(
            leading: CircleAvatar(child: Text(persona.name[0])),
            title: Text(persona.name),
            subtitle: Text(persona.email),
          );
        },
      ),
    );
  }
}
