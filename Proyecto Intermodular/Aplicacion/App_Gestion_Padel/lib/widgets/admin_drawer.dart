import 'package:app_gestion_padel/theme/app_theme.dart';
import 'package:flutter/material.dart';

class AdminDrawer extends StatelessWidget {
  final String currentRoute;

  const AdminDrawer({super.key, required this.currentRoute});

  @override
  Widget build(BuildContext context) {
    return Drawer(
      backgroundColor: kCream,
      child: Column(
        children: [
          Container(
            width: double.infinity,
            padding: const EdgeInsets.fromLTRB(16, 52, 16, 24),
            color: kOlive,
            child: const Text(
              'PANEL ADMIN',
              style: TextStyle(
                color: kCream,
                fontSize: 18,
                fontWeight: FontWeight.w900,
                letterSpacing: 2,
              ),
            ),
          ),
          _Item(icon: Icons.home_outlined, label: 'Inicio', route: '/admin-home', current: currentRoute),
          _Item(icon: Icons.people_outlined, label: 'Socios', route: '/socios', current: currentRoute),
          _Item(icon: Icons.calendar_today_outlined, label: 'Reservas', route: '/reservas', current: currentRoute),
          _Item(icon: Icons.sports_tennis_outlined, label: 'Instalaciones', route: '/instalaciones', current: currentRoute),
          _Item(icon: Icons.inventory_2_outlined, label: 'Material', route: '/material', current: currentRoute),
        ],
      ),
    );
  }
}

class _Item extends StatelessWidget {
  final IconData icon;
  final String label;
  final String route;
  final String current;

  const _Item({required this.icon, required this.label, required this.route, required this.current});

  @override
  Widget build(BuildContext context) {
    final selected = current == route;
    return ListTile(
      leading: Icon(icon, color: selected ? kGreen : kOlive),
      title: Text(
        label,
        style: TextStyle(
          color: kOlive,
          fontWeight: selected ? FontWeight.w700 : FontWeight.normal,
        ),
      ),
      selected: selected,
      selectedTileColor: kLightGreen,
      onTap: () {
        Navigator.of(context).pop();
        if (!selected) Navigator.of(context).pushReplacementNamed(route);
      },
    );
  }
}
