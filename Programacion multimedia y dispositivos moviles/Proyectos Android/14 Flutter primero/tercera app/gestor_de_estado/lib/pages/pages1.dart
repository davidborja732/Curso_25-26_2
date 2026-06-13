import 'package:flutter/material.dart';
import 'package:gestor_de_estado/provider/contador_provider.dart';
import 'package:provider/provider.dart';

class Pages1 extends StatefulWidget {
  const Pages1({super.key});

  @override
  State<Pages1> createState() => _Pages1State();
}

class _Pages1State extends State<Pages1> {
  
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
