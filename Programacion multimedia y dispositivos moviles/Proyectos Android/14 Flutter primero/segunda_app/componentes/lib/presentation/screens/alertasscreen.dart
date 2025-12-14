import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class Alertasscreen extends StatelessWidget {
  const Alertasscreen({super.key});
  @override
  Widget build(BuildContext context) {
    void mostraralertaandroid(){
    showDialog(
              barrierDismissible: true,
              context: context, 
              builder: ( context )=>AlertDialog(
                title: Text("Esto es el titulo de la alerta"),
                content: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    Text("Esto es el contenido de la alerta"),
                    SizedBox(height: 10,),
                    FlutterLogo(
                      textColor: Colors.yellowAccent,
                      style: FlutterLogoStyle.horizontal,
                      size: 100,
                    )
                  ],
                ),
                actions: [
                  TextButton(
                    onPressed: () {
                      Navigator.pop(context);
                    },
                    child: Text("Cancelar",style: TextStyle(color: Colors.green,))
                  ),
                  TextButton(
                    onPressed: ()=>Navigator.pop(context),
                    child: Text("Aceptar",style: TextStyle(color: Colors.red,))
                  )
                ],
              )  
            );
  }
  void mostraralertaIOS(){
    showDialog(
              barrierDismissible: true,
              context: context, 
              builder: ( context )=>CupertinoAlertDialog(
                title: Text("Esto es el titulo de la alerta"),
                content: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    Text("Esto es el contenido de la alerta"),
                    SizedBox(height: 10,),
                    FlutterLogo(
                      textColor: Colors.yellowAccent,
                      style: FlutterLogoStyle.horizontal,
                      size: 100,
                    )
                  ],
                ),
                actions: [
                  TextButton(
                    onPressed: () {
                      Navigator.pop(context);
                    },
                    child: Text("Cancelar",style: TextStyle(color: Colors.green,))
                  ),
                  TextButton(
                    onPressed: ()=>Navigator.pop(context),
                    child: Text("Aceptar",style: TextStyle(color: Colors.red,))
                  )
                ],
              )  
            );
  }
    return Scaffold(
      appBar: AppBar(title: Text('Alertas'),),
      floatingActionButton: FloatingActionButton(
        backgroundColor: Theme.of(context).primaryColor,
        onPressed: () {
          Navigator.pop(context);
        }
        ),
      body: Center(
        child: ElevatedButton(
          onPressed: () {
            Platform.isAndroid ? mostraralertaandroid() : mostraralertaIOS();
          }, 
          child: Text("Mostrar Alerta")
        ),
      ),
    );
  }
}