import 'package:flutter/material.dart';

class ListaPersonajesWidget extends StatefulWidget {
  const ListaPersonajesWidget({super.key});

  @override
  State<ListaPersonajesWidget> createState() => _ListaPersonajesWidgetState();
}

class _ListaPersonajesWidgetState extends State<ListaPersonajesWidget> {
  final tituloStyleText = TextStyle(
    fontSize: 16,
    fontWeight: FontWeight.bold,
    color: Colors.white,
  );

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: ListView(
        padding: EdgeInsets.all(25),
        children: [
          Text('Personajes', style: tituloStyleText),
          Row(
            children: [
              _personajeLista('p1', 'Titulo', 'subtitulo'),
              SizedBox(width: 15),
              _personajeLista('p2', 'Titulo', 'subtitulo'),
              SizedBox(width: 15),
              _personajeLista('p3', 'Titulo', 'subtitulo'),
            ],
          ),
        ],
      ),
    );
  }

  Widget _personajeLista(String imagen, String titulo, String subtitulo) {
    double anchoPantalla = MediaQuery.of(context).size.width - 50;
    return Column(
      children: [
        ClipRRect(
          borderRadius: BorderRadius.circular(20),
          child: Image.asset(
            "assets/$imagen.jpg",
            width: anchoPantalla * 0.3,
            height: 110,
            fit: BoxFit.cover,
          ),
        ),
        SizedBox(height: 15),
        RichText(
          text: TextSpan(
            text: titulo,
            style: TextStyle(color: Colors.white70, fontSize: 14),
            children: [
              TextSpan(
                text: subtitulo,
                style: TextStyle(
                  color: Colors.white70,
                  fontSize: 12,
                  fontWeight: FontWeight.w300,
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }
}
