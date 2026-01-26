import 'package:aplicacion/provider/contador_provider.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';



class Pages2 extends StatelessWidget {
  const Pages2({super.key});

  @override
  Widget build(BuildContext context) {
    final marcador = context.watch<ContadorProvider>();
    final local = marcador.puntosLocal;
    final visitante = marcador.puntosVisitante;

    String mensaje;
    if (local > visitante) {
      mensaje = 'Local gana';
    } else if (visitante > local) {
      mensaje = 'Visitante gana';
    } else {
      mensaje = 'Partido empatado';
    }

    return Scaffold(
      body: Center(
        child: Column(
          children: [
            SizedBox(height: 20),
            const Text('Local - Visitante', style: TextStyle(fontSize: 28)),
            SizedBox(height: 160),
            const SizedBox(height: 20),
            Text(
              '$local - $visitante',
              style: TextStyle(fontSize: 80, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 35),
            Text(mensaje, style: TextStyle(fontSize: 24)),
          ],
        ),
      ),
    );
  }
}
