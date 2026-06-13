class Administrador {
  final int id;
  final String nombre;
  final String contrasena;

  Administrador({
    required this.id,
    required this.nombre,
    required this.contrasena,
  });

  factory Administrador.fromJson(Map<String, dynamic> json) {
    return Administrador(
      id: json['id'] ?? json['ID'] ?? 0,
      nombre: json['nombre'] ?? json['Nombre'] ?? '',
      contrasena: json['contrasena'] ?? json['Contrasena'] ?? '',
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'nombre': nombre,
      'contrasena': contrasena,
    };
  }
}
