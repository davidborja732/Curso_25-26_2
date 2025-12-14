import 'package:flutter/material.dart';
import 'package:gestor_de_estado/provider/contador_provider.dart';
import 'package:provider/provider.dart';

class Pages2 extends StatelessWidget {
  const Pages2({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(body: Center(child: Text(context.watch<ContadorProvider>().contador.toString(),style: TextStyle(fontSize: 120))));
  }
}
