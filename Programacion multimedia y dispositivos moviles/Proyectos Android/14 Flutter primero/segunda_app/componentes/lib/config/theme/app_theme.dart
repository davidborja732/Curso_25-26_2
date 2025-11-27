import 'package:flutter/material.dart';

const listaDeColores = <Color>[
  Colors.blue,
  Colors.indigo,
  Colors.yellow,
  Colors.lightGreen,
  Colors.pink
];
class AppTheme {
  final int colorseleccionado;

  AppTheme({this.colorseleccionado = 0});

  ThemeData obtenertema() => ThemeData(
    useMaterial3: true,
    colorSchemeSeed: listaDeColores[colorseleccionado],
    appBarTheme: AppBarTheme(
      centerTitle: true,
    ),
    iconTheme: IconThemeData(color:listaDeColores[colorseleccionado],),
  );
}