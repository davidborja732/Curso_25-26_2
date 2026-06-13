import 'package:flutter/material.dart';

class ContadorProvider extends ChangeNotifier {
  int _puntosLocal = 0;
  int _puntosVisitante = 0;

  int get puntosLocal => _puntosLocal;
  int get puntosVisitante => _puntosVisitante;

  void incrementarLocal(int puntos) {
    _puntosLocal += puntos;
    notifyListeners();
  }

  void decrementarLocal(int puntos) {
    _puntosLocal = (_puntosLocal - puntos);
    notifyListeners();
  }

  void incrementarVisitante(int puntos) {
    _puntosVisitante += puntos;
    notifyListeners();
  }

  void decrementarVisitante(int puntos) {
    _puntosVisitante = (_puntosVisitante - puntos);
    notifyListeners();
  }

  void resetearMarcador() {
    _puntosLocal = 0;
    _puntosVisitante = 0;
    notifyListeners();
  }
}
