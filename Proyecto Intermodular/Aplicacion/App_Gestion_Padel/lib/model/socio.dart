class Socio {
  String? dni;
  String? contrasena;
  String? nombreCompleto;
  String? direccion;
  String? telefono;
  String? email;
  String? cuentaBancaria;
  int? desactivado;
  String? imagenPerfil;

  Socio({
    this.dni,
    this.contrasena,
    this.nombreCompleto,
    this.direccion,
    this.telefono,
    this.email,
    this.cuentaBancaria,
    this.desactivado,
    this.imagenPerfil,
  });

  Socio.fromJson(Map<String, dynamic> json) {
    dni = json['dni'];
    contrasena = json['contrasena'];
    nombreCompleto = json['nombreCompleto'];
    direccion = json['direccion'];
    telefono = json['telefono'];
    email = json['email'];
    cuentaBancaria = json['cuentaBancaria'];
    desactivado = json['desactivado'];
    imagenPerfil = json['imagenPerfil'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = {};
    data['dni'] = dni;
    data['contrasena'] = contrasena;
    data['nombreCompleto'] = nombreCompleto;
    data['direccion'] = direccion;
    data['telefono'] = telefono;
    data['email'] = email;
    data['cuentaBancaria'] = cuentaBancaria;
    data['desactivado'] = desactivado;
    data['imagenPerfil'] = imagenPerfil;
    return data;
  }
}
