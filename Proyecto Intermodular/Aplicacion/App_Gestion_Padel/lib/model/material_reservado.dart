import 'package:app_gestion_padel/model/material_deportivo.dart';

class MaterialReservado {
  MaterialDeportivo? material;
  int cantidad;
  String? observaciones;

  MaterialReservado({
    this.material,
    this.cantidad = 1,
    this.observaciones,
  });

  MaterialReservado.fromJson(Map<String, dynamic> json)
      : material = json['material'] != null
            ? MaterialDeportivo.fromJson(json['material'] as Map<String, dynamic>)
            : null,
        cantidad = json['cantidad'] ?? 1,
        observaciones = json['observaciones'];

  Map<String, dynamic> toJson() {
    return {
      'idMaterial': material?.idMaterial,
      'cantidad': cantidad,
      'observaciones': observaciones,
    };
  }
}
