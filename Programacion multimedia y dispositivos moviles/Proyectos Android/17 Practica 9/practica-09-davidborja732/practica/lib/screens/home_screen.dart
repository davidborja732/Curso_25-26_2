import 'package:flutter/material.dart';
import 'package:gestor_de_estado/pages/pages1.dart';
import 'package:gestor_de_estado/pages/pages2.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  int _currentindex = 0;
  final List<Widget> _paginas = [Pages1(), Pages2()];
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Basketball score')),
      body: _paginas[_currentindex],
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _currentindex,
        items: [
          BottomNavigationBarItem(icon: Icon(Icons.games), label: 'Juego'),
          BottomNavigationBarItem(
            icon: Icon(Icons.rocket_launch_sharp),
            label: 'Resultado',
          ),
        ],
        onTap: (value) {
          setState(() {
            _currentindex = value;
          });
        },
      ),
    );
  }
}
