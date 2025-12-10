import 'package:componentes/config/theme/app_theme.dart';
import 'package:componentes/presentation/screens/indicador_screen.dart';

import 'package:flutter/material.dart';

import 'presentation/screens/screens.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: AppTheme(colorseleccionado: 2).obtenertema(),
      title: 'Material App',
      //home: HomeScreen(),
      initialRoute: '/',
      routes: {
        '/': (context) => HomeScreen(),
        '/boton': (context) => Botonesscreen(),
        '/tarjetas': (context) => Tarjetasscreen(),
        '/listas': (context) => Listasscreen(),
        '/alertas': (context) => Alertasscreen(),
        '/sliders': (context) => SlidersScreens(),
        '/gridview': (context) => GridScreen(),
        '/animations': (context) => AnimacionesScreen(),
        '/progreso':(context) => IndicadorScreen(),
      },
    );
  }
}
