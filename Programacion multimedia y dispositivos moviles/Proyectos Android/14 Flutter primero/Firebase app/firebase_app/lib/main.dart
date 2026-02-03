import 'package:firebase_app/screens/add_tarea.dart';
import 'package:firebase_app/screens/home_screen.dart';
import 'package:firebase_app/screens/login_screen.dart';
import 'package:firebase_app/screens/register_screen.dart';
// ignore: unused_import
import 'package:firebase_app/screens/seleccionar_screen.dart';
import 'package:firebase_app/screens/tareas_screen.dart';
// ignore: unused_import
import 'package:firebase_crashlytics/firebase_crashlytics.dart';
import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
// ignore: unused_import
import 'package:firebase_analytics/firebase_analytics.dart';
import 'firebase_options.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(options: DefaultFirebaseOptions.currentPlatform);
  /*FirebaseAnalytics analytics = FirebaseAnalytics.instance;
  await analytics.logEvent(
    name: 'prueba_DAM2',
    parameters: {'timestamp':DateTime.now().toIso8601String()},
  );
  FirebaseCrashlytics.instance.crash();
  FlutterError.onError = (errorDetails) {
      FirebaseCrashlytics.instance.recordFlutterFatalError(errorDetails);
    };*/
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      routes: {
        '/': (context) => SeleccionarScreen(),
        '/login': (context) => LoginScreen(),
        '/register': (context) => RegisterScreen(),
        '/home': (context) => HomeScreen(),
        '/lista_tareas': (context) => TareasScreen(),
        '/add_tareas': (context) => AddTareaScreen(),
      },
    );
  }
}
