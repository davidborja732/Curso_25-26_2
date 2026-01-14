import 'package:aplicacion/widgets/header_widget.dart';
import 'package:aplicacion/widgets/lista_personajes_widget.dart';
import 'package:flutter/material.dart';


class DiseniosScreen extends StatelessWidget {
   
  const DiseniosScreen({Key? key}) : super(key: key);
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Color.fromARGB(255, 16, 16, 16),
      body: Column(
        children: [
          HeaderWidget(),
          ListaPersonajesWidget(),
        ],
      )
    );
  }
}