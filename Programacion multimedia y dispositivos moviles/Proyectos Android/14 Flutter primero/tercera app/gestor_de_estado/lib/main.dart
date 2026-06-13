import 'package:flutter/material.dart';
import 'package:gestor_de_estado/provider/contador_provider.dart';
import 'package:gestor_de_estado/screens/home_screen.dart';
import 'package:provider/provider.dart';

void main() => runApp(ChangeNotifierProvider(
  create: (_)=>ContadorProvider(),
  child: MyApp(),
));

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Provider APP',
      theme: ThemeData(
        useMaterial3: false,
        primarySwatch: Colors.red,
      ),
      home: HomeScreen()
    );
  }
}