import 'package:flutter/material.dart';

class SeleccionarScreen extends StatelessWidget {

  const SeleccionarScreen({Key? key}) : super(key: key);
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('SeleccionarScreen'),),
      body: Center(
        child: Text('SeleccionarScreen'),
      ),
    );
  }
}