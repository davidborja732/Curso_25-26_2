import 'package:aplicacion/widgets/option_menu_item.dart';
import 'package:flutter/material.dart';

class MenuItem extends StatelessWidget {
  const MenuItem({
    super.key,
    required OptionMenuItem opcion,
  }) : _opcion = opcion;

  final OptionMenuItem _opcion;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => Navigator.pushNamed(context, _opcion.screenName),
      child: Card(
        elevation: 10,
        shadowColor: _opcion.color,
        color: _opcion.color,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(_opcion.icono,color: Colors.white,size: 65.0,),
            SizedBox(height: 30,),
            Text(_opcion.texto,style: TextStyle(
              color: Colors.white,
              fontSize: 22,
              fontWeight: FontWeight.bold
              ),
            )
          ],
        ),
      ),
    );
  }
}