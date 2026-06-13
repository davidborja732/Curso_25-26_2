class Instalacion {
  int? idInstalacion;
  String? tipo;
  String? nombre;
  String? fechaUltimoMantenimiento;

  Instalacion(
      {this.idInstalacion,
      this.tipo,
      this.nombre,
      this.fechaUltimoMantenimiento});

  Instalacion.fromJson(Map<String, dynamic> json) {
    idInstalacion = json['idInstalacion'];
    tipo = json['tipo'];
    nombre = json['nombre'];
    fechaUltimoMantenimiento = json['fechaUltimoMantenimiento'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['idInstalacion'] = idInstalacion;
    data['tipo'] = tipo;
    data['nombre'] = nombre;
    data['fechaUltimoMantenimiento'] = fechaUltimoMantenimiento;
    return data;
  }

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is Instalacion &&
          runtimeType == other.runtimeType &&
          idInstalacion == other.idInstalacion;

  @override
  int get hashCode => idInstalacion.hashCode;
}