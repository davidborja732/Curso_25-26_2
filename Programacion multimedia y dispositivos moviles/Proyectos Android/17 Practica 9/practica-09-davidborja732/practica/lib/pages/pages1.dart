import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:gestor_de_estado/provider/contador_provider.dart';

class Pages1 extends StatelessWidget {
  const Pages1({super.key});

  @override
  Widget build(BuildContext context) {
    final marcador = context.watch<ContadorProvider>();

    return Scaffold(
      body: Padding(
        padding: EdgeInsets.symmetric(horizontal: 20),
        child: Column(
          children: [
            // Columna para local
            Column(
              children: [
                SizedBox(height: 20),
                Text('Local', style: TextStyle(fontSize: 24)),
                Text(
                  marcador.puntosLocal.toString(),
                  style: TextStyle(fontSize: 80, fontWeight: FontWeight.bold),
                ),
                SizedBox(height: 10),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    _boton(
                      () =>
                          context.read<ContadorProvider>().decrementarLocal(1),
                      '-1',
                    ),
                    _boton(
                      () =>
                          context.read<ContadorProvider>().incrementarLocal(1),
                      '+1',
                    ),
                    _boton(
                      () =>
                          context.read<ContadorProvider>().incrementarLocal(2),
                      '+2',
                    ),
                  ],
                ),
              ],
            ),
            // Icono y boton
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                IconButton(
                  onPressed: () =>
                      context.read<ContadorProvider>().resetearMarcador(),
                  icon: Icon(Icons.refresh, size: 30),
                ),
                SizedBox(width: 30),
                Icon(Icons.sports_basketball, size: 150),
                SizedBox(width: 30),
                IconButton(
                  onPressed: () {},
                  icon: Icon(
                    Icons.arrow_forward_ios,
                    size: 30,
                    color: Colors.white,
                  ),
                ),
              ],
            ),

            // Columna para visitante
            Column(
              children: [
                Text(
                  marcador.puntosVisitante.toString(),
                  style: TextStyle(fontSize: 80, fontWeight: FontWeight.bold),
                ),
                SizedBox(height: 10),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    _boton(
                      () => context
                          .read<ContadorProvider>()
                          .decrementarVisitante(1),
                      '-1',
                    ),
                    _boton(
                      () => context
                          .read<ContadorProvider>()
                          .incrementarVisitante(1),
                      '+1',
                    ),
                    _boton(
                      () => context
                          .read<ContadorProvider>()
                          .incrementarVisitante(2),
                      '+2',
                    ),
                  ],
                ),
              ],
            ),
            SizedBox(height: 10),
            Text('Visitante', style: TextStyle(fontSize: 24)),
          ],
        ),
      ),
    );
  }

  Widget _boton(VoidCallback onPressed, String texto) {
    return Padding(
      padding: EdgeInsets.symmetric(horizontal: 6),
      child: ElevatedButton(
        onPressed: onPressed,
        style: ElevatedButton.styleFrom(
          minimumSize: Size(60, 40),
          textStyle: TextStyle(fontSize: 18),
        ),
        child: Text(texto),
      ),
    );
  }
}
