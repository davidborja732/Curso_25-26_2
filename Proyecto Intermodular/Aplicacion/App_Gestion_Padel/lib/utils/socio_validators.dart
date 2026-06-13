import 'package:flutter/material.dart';

class SocioMaxLength {
  static const int dni = 9;
  static const int nombre = 100;
  static const int direccion = 150;
  static const int telefono = 9;
  static const int cuentaBancaria = 34;
  static const int contrasena = 64;
}

class SocioValidators {
  static String? dni(String? value) {
    if (value == null || value.trim().isEmpty) return 'Introduce tu DNI';
    final v = value.trim();
    if (v.length != 9) return 'El DNI debe tener exactamente 9 caracteres';
    if (!RegExp(r'^[a-zA-Z0-9]+$').hasMatch(v)) {
      return 'El DNI solo puede contener letras y números';
    }
    return null;
  }

  static String? nombre(String? value) {
    if (value == null || value.trim().isEmpty) {
      return 'Este campo no puede estar vacío';
    }
    return null;
  }

  // Opcional: puede estar vacío
  static String? direccion(String? value) => null;

  // Opcional: puede estar vacío, pero si se rellena solo admite dígitos
  static String? telefono(String? value) {
    if (value == null || value.trim().isEmpty) return null;
    if (!RegExp(r'^[0-9]+$').hasMatch(value.trim())) {
      return 'El teléfono solo puede contener números';
    }
    return null;
  }

  static String? email(String? value) {
    if (value == null || value.trim().isEmpty) {
      return 'Por favor, ingresa tu correo';
    }
    final regex = RegExp(r'^[^@\s]+@[^@\s]+\.[^@\s]+$');
    if (!regex.hasMatch(value.trim())) {
      return 'Introduce un correo válido (ejemplo@dominio.com)';
    }
    return null;
  }

  // Opcional: sin validación de obligatoriedad
  static String? cuentaBancaria(String? value) => null;

  static String? contrasena(String? value) {
    if (value == null || value.isEmpty) {
      return 'Este campo no puede estar vacío';
    }
    return null;
  }

  static String? Function(String?) confirmarContrasena(
    TextEditingController passwordController,
  ) {
    return (value) {
      if (value == null || value.isEmpty) {
        return 'Este campo no puede estar vacío';
      }
      if (value != passwordController.text) {
        return 'Las contraseñas no coinciden';
      }
      return null;
    };
  }
}
