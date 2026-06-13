import 'package:aplicacion/config/theme/app_theme.dart';
import 'package:aplicacion/widgets/option_menu_item.dart';
import 'package:flutter/material.dart';

class MenuItems {
  final List<OptionMenuItem> listaOpcionesMenu = [
    OptionMenuItem(
      color: AppTheme.listaColores[0], 
      icono: Icons.sports_basketball, 
      texto: 'Marcador Basket', 
      screenName: 'baloncesto',
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[1], 
      icono: Icons.pets, 
      texto: 'API Perretes', 
      screenName: 'perretes',
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[2], 
      icono: Icons.supervised_user_circle, 
      texto: 'API Json', 
      screenName: 'api1',
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[3], 
      icono: Icons.tv, 
      texto: 'API Simpsons', 
      screenName: 'simpsons',
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[4], 
      icono: Icons.design_services, 
      texto: 'Diseño', 
      screenName: 'estilos',
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[5], 
      icono: Icons.qr_code, 
      texto: 'Lector Codigo QR', 
      screenName: 'lector_qr',
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[6], 
      icono: Icons.error, 
      texto: 'Opcion 7', 
      screenName: 'Opcion 7',
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[7], 
      icono: Icons.error, 
      texto: 'Opcion 8', 
      screenName: 'Opcion 8',
    ),
  ];

}