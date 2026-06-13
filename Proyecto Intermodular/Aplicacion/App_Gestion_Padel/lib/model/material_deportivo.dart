class MaterialDeportivo {
  int? idMaterial;
  String? nombre;
  String? descripcion;

  MaterialDeportivo({this.idMaterial, this.nombre, this.descripcion});

  MaterialDeportivo.fromJson(Map<String, dynamic> json) {
    idMaterial = json['idMaterial'];
    nombre = json['nombre'];
    descripcion = json['descripcion'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['idMaterial'] = idMaterial;
    data['nombre'] = nombre;
    data['descripcion'] = descripcion;
    return data;
  }
}
