// ignore: unnecessary_import
// ignore_for_file: non_constant_identifier_names

import 'package:flutter/material.dart';

class MenuItem {
  final String titulo;
  final String subtitulo;
  final String link;
  final IconData icono;
  MenuItem({
    required this.titulo,
    required this.subtitulo,
    required this.link,
    required this.icono,
  });
}

final MenuItems = <MenuItem>[
  MenuItem(
    titulo: 'Listas',
    subtitulo: 'Ejemplo de lista',
    link: '/listas',
    icono: Icons.list,
  ),
  MenuItem(
    titulo: 'Tarjetas',
    subtitulo: 'Ejemplo de tarjetas',
    link: '/tarjetas',
    icono: Icons.credit_card,
  ),
  MenuItem(
    titulo: 'Botones',
    subtitulo: 'Ejemplo de Botones',
    link: '/boton',
    icono: Icons.radio_button_checked,
  ),
  MenuItem(
    titulo: 'Alertas',
    subtitulo: 'Ejemplo de Alertas',
    link: '/alertas',
    icono: Icons.add_alert,
  ),
  MenuItem(
    titulo: 'Sliders & Checks',
    subtitulo: 'Ejemplo de Sliders',
    link: '/sliders',
    icono: Icons.check_box,
  ),
  MenuItem(
    titulo: 'GridView (Practica 8)',
    subtitulo: 'Ejemplo de GridView Practica 8 David Borja Mateo',
    link: '/gridview',
    icono: Icons.grid_on,
  ),
  MenuItem(
    titulo: 'Animaciones',
    subtitulo: 'Ejemplo de animaciones',
    link: '/animations',
    icono: Icons.animation,
  ),
  MenuItem(
    titulo: 'Progress Indicator & SnackBar',
    subtitulo: 'Progress Indicator & SnackBar ejemplo',
    link: '/progreso',
    icono: Icons.refresh,
  ),
];
