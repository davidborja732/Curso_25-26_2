import 'package:componentes/config/theme/app_theme.dart';
import 'package:flutter/material.dart';
import 'presentation/screens/screens.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: AppTheme( colorSeleccionado: 2).obtenerTema(),
      // home: HomeScreen()
      initialRoute: '/',
      routes: {
        '/' : (context)=> HomeScreen(),
        '/botones' : (context)=> BotonesScreen(),
        '/listas' : (context)=> ListasScreen(),
        '/tarjetas' : (context)=> TarjetasScreen(),
        '/alertas'  : (context)=> AlertasScreen(),
        '/sliders'  : (context)=> SlidersScreen(),
        '/animaciones' : (context)=> AnimacionesScreen(),
        '/snackbar' : (context)=> ProgressScreen(),
        '/formulario': (context)=>FormularioScreen()
      },
    );
  }
}