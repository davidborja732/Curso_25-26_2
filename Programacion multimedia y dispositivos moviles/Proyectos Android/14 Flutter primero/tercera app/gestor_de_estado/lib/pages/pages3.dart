import 'package:flutter/material.dart';
import 'package:gestor_de_estado/provider/contador_provider.dart';
import 'package:provider/provider.dart';

class Pages3 extends StatelessWidget {
  const Pages3({super.key});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        children: [
          SizedBox(height: 20),Text(context.watch<ContadorProvider>().contador.toString(), style: TextStyle(fontSize: 120)),
          ElevatedButton(
            onPressed: () {
              //Aqui vamos a acceder al provider y a la funcion incrementar
              context.read<ContadorProvider>().Incrementar();
            },
            child: Text('Incrementar'),
          ),
        ],
      ),
    );
  }
}
