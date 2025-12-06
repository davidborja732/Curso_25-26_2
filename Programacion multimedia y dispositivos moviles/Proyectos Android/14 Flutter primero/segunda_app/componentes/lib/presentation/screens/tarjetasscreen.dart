import 'package:componentes/widgets/tarjetas.dart';
import 'package:flutter/material.dart';

class Tarjetasscreen extends StatelessWidget {
  const Tarjetasscreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Tarjetas'),
      ),
      body: ListView(
        padding: EdgeInsets.all(14),
        children: [
          TarjetaPersonalizada1(),
          TarjetaPersonalizada2(),
        ],
      ),
    );
  }
}

