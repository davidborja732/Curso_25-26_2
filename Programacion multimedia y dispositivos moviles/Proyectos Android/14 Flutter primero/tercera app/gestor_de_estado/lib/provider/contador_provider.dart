import 'package:flutter/material.dart';

// Este provider manajera el contador de la pantalla 1
// ChangeNotifier es una clase que proporciona notificaciones a los widget 
class ContadorProvider extends ChangeNotifier{
  int _contador = 0;
  //cuando tenemos un estado con provider hemos de poder hacer dos cosas
  // 1- Poder ller el valor de ese estado
  // 2- Poder modificar el valor de ese estado
  int get contador => _contador;
  void Incrementar(){
    _contador++;
    notifyListeners();
  }
}