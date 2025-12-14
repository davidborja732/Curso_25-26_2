import 'package:flutter/material.dart';
import 'package:gestor_de_estado/pages/pages1.dart';
import 'package:gestor_de_estado/pages/pages2.dart';
import 'package:gestor_de_estado/pages/pages3.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  int _currentindex = 0;
  final List<Widget> _paginas = [Pages1(), Pages2(), Pages3()];
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Provider APP')),
      body: _paginas[_currentindex],
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _currentindex,
        items: [
          BottomNavigationBarItem(icon: Icon(Icons.home), label: 'pagina 1'),
          BottomNavigationBarItem(icon: Icon(Icons.search), label: 'pagina 2'),
          BottomNavigationBarItem(icon: Icon(Icons.person), label: 'pagina 3'),
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
