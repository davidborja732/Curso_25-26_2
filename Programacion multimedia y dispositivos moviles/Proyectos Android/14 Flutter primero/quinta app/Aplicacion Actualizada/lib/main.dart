import 'package:aplicacion/screens/lector_qr_screen.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'package:aplicacion/provider/contador_provider.dart';
import 'package:aplicacion/screens/menu_screen.dart';
import 'package:aplicacion/screens/apijson_screen.dart';
import 'package:aplicacion/screens/simpsons_screen.dart';
import 'package:aplicacion/screens/disenios_screen.dart';

void main() {
  runApp(
    ChangeNotifierProvider(
      create: (_) => ContadorProvider(),
      child: const MyApp(),
    ),
  );
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Material App',
      debugShowCheckedModeBanner: false,
      routes: {
        '/': (context) => MenuScreen(),
        'api1': (context) => ApiJsonPlaceUsersScreen(),
        'simpsons': (context) => SimpsonsScreen(),
        'estilos': (context) => DiseniosScreen(),
        'lector_qr': (context) => LectorQrScreen(),
      },
    );
  }
}
