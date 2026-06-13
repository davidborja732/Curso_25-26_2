import 'package:aplicacion/pages/pages1.dart';
import 'package:aplicacion/pages/pages2.dart';
import 'package:flutter/material.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  int _currentIndex = 0;

  final List<Widget> _paginas = const [Pages1(), Pages2()];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Basketball score'),
        backgroundColor: Colors.purple,
      ),

      body: _paginas[_currentIndex],
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _currentIndex,
        items: const [
          BottomNavigationBarItem(icon: Icon(Icons.games), label: 'Juego'),
          BottomNavigationBarItem(
            icon: Icon(Icons.rocket_launch_sharp),
            label: 'Resultado',
          ),
        ],
        onTap: (value) {
          setState(() {
            _currentIndex = value;
          });
        },
      ),
    );
  }
}
