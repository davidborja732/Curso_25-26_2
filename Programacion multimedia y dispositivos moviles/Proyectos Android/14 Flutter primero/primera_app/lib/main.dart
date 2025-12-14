import 'package:flutter/material.dart';
import 'package:primera_app/screens/counter_screen.dart';
import 'package:primera_app/screens/CounterFunctionsScreen.dart';

void main(List<String> args) {
  runApp(MyAPP());
}

class MyAPP extends StatelessWidget {
  const MyAPP({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(useMaterial3: true,colorSchemeSeed: const Color.fromARGB(255, 204, 14, 0)),
      debugShowCheckedModeBanner: false,
      home: CounterFunctionsScreen());
  }
}