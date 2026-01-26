import 'package:aplicacion/provider/contador_provider.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class Pages1 extends StatelessWidget {
  const Pages1({super.key});

  @override
  Widget build(BuildContext context) {
    final marcador = context.watch<ContadorProvider>();

    return Scaffold(
      body: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 20),
        child: SingleChildScrollView(
          child: Column(
            children: [
              const SizedBox(height: 20),
              // Local
              Column(
                children: [
                  const Text('Local', style: TextStyle(fontSize: 24)),
                  Text(
                    marcador.puntosLocal.toString(),
                    style: const TextStyle(
                      fontSize: 80,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  const SizedBox(height: 10),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      _boton(
                        () => context.read<ContadorProvider>().decrementarLocal(
                          1,
                        ),
                        '-1',
                      ),
                      _boton(
                        () => context.read<ContadorProvider>().incrementarLocal(
                          1,
                        ),
                        '+1',
                      ),
                      _boton(
                        () => context.read<ContadorProvider>().incrementarLocal(
                          2,
                        ),
                        '+2',
                      ),
                    ],
                  ),
                ],
              ),

              const SizedBox(height: 20),

              // Icono y boton
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  IconButton(
                    onPressed: () =>
                        context.read<ContadorProvider>().resetearMarcador(),
                    icon: const Icon(Icons.refresh, size: 30),
                  ),
                  const SizedBox(width: 30),
                  const Icon(Icons.sports_basketball, size: 80),
                  const SizedBox(width: 30),
                  IconButton(
                    onPressed: () {},
                    icon: const Icon(
                      Icons.arrow_forward_ios,
                      size: 30,
                      color: Colors.white,
                    ),
                  ),
                ],
              ),

              const SizedBox(height: 20),

              // Visitante
              Column(
                children: [
                  Text(
                    marcador.puntosVisitante.toString(),
                    style: const TextStyle(
                      fontSize: 80,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  const SizedBox(height: 10),
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
                  const SizedBox(height: 10),
                  const Text('Visitante', style: TextStyle(fontSize: 24)),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _boton(VoidCallback onPressed, String texto) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 6),
      child: ElevatedButton(
        onPressed: onPressed,
        style: ElevatedButton.styleFrom(
          minimumSize: const Size(60, 40),
          textStyle: const TextStyle(fontSize: 18),
        ),
        child: Text(texto),
      ),
    );
  }
}
