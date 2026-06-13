import 'package:app_gestion_padel/screens/admin/admin_home_screen.dart';
import 'package:app_gestion_padel/screens/admin/instalaciones_screen.dart';
import 'package:app_gestion_padel/screens/admin/material_screen.dart';
import 'package:app_gestion_padel/screens/admin/reservas_lista_screen.dart';
import 'package:app_gestion_padel/screens/admin/socios_screen.dart';
import 'package:app_gestion_padel/screens/socios/home_screen.dart';
import 'package:app_gestion_padel/screens/autenticacion/login_screen.dart';
import 'package:app_gestion_padel/screens/autenticacion/register_screen.dart';
import 'package:app_gestion_padel/screens/autenticacion/recuperar_password_screen.dart';
import 'package:app_gestion_padel/theme/app_theme.dart';
import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: appTheme,
      locale: const Locale('es', 'ES'),
      localizationsDelegates: const [
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
      ],
      supportedLocales: const [Locale('es', 'ES')],
      initialRoute: '/login',
      routes: {
        '/': (context) => const LoginScreen(),
        '/login': (context) => const LoginScreen(),
        '/register': (context) => const RegisterScreen(),
        '/recuperar-password': (context) => const RecuperarPasswordScreen(),
        '/home': (context) => const HomeScreen(),
        '/admin-home': (context) => const AdminHomeScreen(),
        '/socios': (context) => const SociosScreen(),
        '/reservas': (context) => const ReservasScreen(),
        '/instalaciones': (context) => const InstalacionesScreen(),
        '/material': (context) => const MaterialScreen(),
      },
    );
  }
}
