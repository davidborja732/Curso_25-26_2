import 'package:flutter/material.dart';
import 'package:repaso/prueba_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});


  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      routes: {
        '/':(context) =>PruebaScreen(personas: [],),
      },
    );
  }
}

