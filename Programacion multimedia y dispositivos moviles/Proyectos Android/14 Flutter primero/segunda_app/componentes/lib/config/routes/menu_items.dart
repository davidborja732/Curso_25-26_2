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


final menuItems = <MenuItem>[

  MenuItem(
    titulo: 'Botones', 
    subtitulo: 'Muchos botones que ofrece Flutter', 
    link: '/botones', 
    icono: Icons.radio_button_checked,
  ),

  MenuItem(
    titulo: 'Listas', 
    subtitulo: 'Un simple ejemplo de Lista', 
    link: '/listas', 
    icono: Icons.list,
  ),

  MenuItem(
    titulo: 'Tarjetas', 
    subtitulo: 'Un simple ejemplo de tarjetas', 
    link: '/tarjetas', 
    icono: Icons.credit_card,
  ),

  MenuItem(
    titulo: 'Alertas', 
    subtitulo: 'Un simple ejemplo de alertas en Flutter', 
    link: '/alertas', 
    icono: Icons.alarm_add_outlined,
  ),
  MenuItem(
    titulo: 'Sliders & Checks', 
    subtitulo: 'Un simple ejemplo de sliders y checks en Flutter', 
    link: '/sliders', 
    icono: Icons.check_box,
  ),
  MenuItem(
    titulo: 'Animaciones', 
    subtitulo: 'Un simple ejemplo de animaciones en Flutter', 
    link: '/animaciones', 
    icono: Icons.animation,
  ),
  MenuItem(
    titulo: 'Progress Indicator & SnackBar', 
    subtitulo: 'Un simple ejemplo de Indicators y SnackBar', 
    link: '/snackbar', 
    icono: Icons.refresh_rounded,
  ),
  MenuItem(
    titulo: 'Formulario', 
    subtitulo: 'Un simple ejemplo de formulario', 
    link: '/formulario', 
    icono: Icons.text_format,
  ),
];