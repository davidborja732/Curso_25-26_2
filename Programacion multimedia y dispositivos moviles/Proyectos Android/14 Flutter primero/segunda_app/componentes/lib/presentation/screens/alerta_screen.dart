import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AlertasScreen extends StatelessWidget {
  const AlertasScreen({super.key});



  @override
  Widget build(BuildContext context) {


    void mostrarAlertaAndroid(){
      showDialog(
              barrierDismissible: true,
              context: context, 
              builder: ( context ) => AlertDialog(
                title: Text('Título de la alerta'),
                content: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    Text('Esto es el contenido de la Alerta'),
                    SizedBox(height: 10,),
                    FlutterLogo(
                      size: 100,
                    )
                  ],
                ),
                actions: [
                  TextButton(
                    onPressed: (){
                      Navigator.pop(context);
                    }, 
                    child: Text('Cancelar', style: TextStyle(color: Colors.red),),
                    ),
                  TextButton(
                    onPressed: ()=>Navigator.pop(context), 
                    child: Text('Aceptar'),
                    ),
                ],
              )
            );
  }
    
    void mostrarAlertaIos(){
      showDialog(
              barrierDismissible: true,
              context: context, 
              builder: ( context ) => CupertinoAlertDialog(
                title: Text('Título de la alerta'),
                content: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    Text('Esto es el contenido de la Alerta'),
                    SizedBox(height: 10,),
                    FlutterLogo(
                      size: 100,
                    )
                  ],
                ),
                actions: [
                  TextButton(
                    onPressed: (){
                      Navigator.pop(context);
                    }, 
                    child: Text('Cancelar', style: TextStyle(color: Colors.red),),
                    ),
                  TextButton(
                    onPressed: ()=>Navigator.pop(context), 
                    child: Text('Aceptar'),
                    ),
                ],
              )
            );
  }
    
    
    
    return Scaffold(
      appBar: AppBar(title: Text('Alertas'),),
      body: Center(
        child: ElevatedButton(
          onPressed: (){
            Platform.isAndroid ? mostrarAlertaAndroid() : mostrarAlertaIos();
          }, 
          child: Text('Mostrar Alerta'),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        backgroundColor: Theme.of(context).primaryColor,
        child: Icon(Icons.close),
        onPressed: (){
          Navigator.pop(context);
        }),
    );
  }
}