import 'package:app_gestion_padel/theme/app_theme.dart';
import 'package:flutter/material.dart';
import 'package:app_gestion_padel/screens/socios/perfil_screen.dart';
import 'package:app_gestion_padel/screens/socios/reservar_view.dart';
import 'package:app_gestion_padel/screens/socios/disponibilidad_view.dart';
import 'package:app_gestion_padel/widgets/mis_reservas_view.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  int _selectedIndex = 0;

  final List<Widget> _widgetOptions = <Widget>[
    const MisReservasView(),
    const ReservarView(),
    const DisponibilidadView(),
    const PerfilScreen(),
  ];

  static const List<String> _titles = [
    'Mis Reservas',
    'Reservar',
    'Disponibilidad',
    'Perfil',
  ];

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  void _showLogoutDialog(BuildContext context) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text('Cerrar sesión'),
          content: const Text('¿Seguro que quieres cerrar sesión?'),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: const Text('Cancelar'),
            ),
            FilledButton(
              onPressed: () {
                Navigator.of(context).pop();
                Navigator.pushReplacementNamed(context, '/login');
              },
              child: const Text('Cerrar sesión'),
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: PreferredSize(
        preferredSize: const Size.fromHeight(100),
        child: Stack(
          fit: StackFit.expand,
          children: [
            Image.asset('assets/tennis_court_canva.png', fit: BoxFit.cover, alignment: Alignment.bottomCenter),
            
            Align(
              alignment: Alignment.bottomLeft,
              child: Padding(
                padding: const EdgeInsets.fromLTRB(20, 0, 0, 14),
                child: Text(
                  _titles[_selectedIndex].toUpperCase(),
                  style: Theme.of(context).appBarTheme.titleTextStyle,
                ),
              ),
            ),
            Align(
              alignment: Alignment.bottomRight,
              child: Padding(
                padding: const EdgeInsets.fromLTRB(0, 0, 8, 4),
                child: IconButton(
                  icon: const Icon(Icons.logout, color: Colors.white),
                  onPressed: () => _showLogoutDialog(context),
                ),
              ),
            ),
          ],
        ),
      ),
      body: _widgetOptions.elementAt(_selectedIndex),
      bottomNavigationBar: BottomNavigationBar(backgroundColor: kOlive,
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.list_alt),
            label: 'Mis Reservas',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.add_circle_outline),
            label: 'Reservar',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.calendar_today),
            label: 'Disponibilidad',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.person_outline),
            label: 'Perfil',
          ),
        ],
        currentIndex: _selectedIndex,
        selectedItemColor: Theme.of(context).colorScheme.primary,
        unselectedItemColor: kCream,
        onTap: _onItemTapped,
        type: BottomNavigationBarType.fixed,
      ),
    );
  }
}
