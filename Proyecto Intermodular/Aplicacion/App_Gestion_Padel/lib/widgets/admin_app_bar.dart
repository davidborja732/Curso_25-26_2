import 'package:flutter/material.dart';

class AdminAppBar extends StatelessWidget implements PreferredSizeWidget {
  final String title;
  final bool showLogout;

  const AdminAppBar({super.key, required this.title, this.showLogout = false});

  @override
  Size get preferredSize => const Size.fromHeight(100);

  void _showLogoutDialog(BuildContext context) {
    showDialog(
      context: context,
      builder: (ctx) => AlertDialog(
        title: const Text('Cerrar sesión'),
        content: const Text('¿Seguro que quieres cerrar sesión?'),
        actions: [
          TextButton(
            onPressed: () => Navigator.of(ctx).pop(),
            child: const Text('Cancelar'),
          ),
          FilledButton(
            onPressed: () {
              Navigator.of(ctx).pop();
              Navigator.pushReplacementNamed(context, '/login');
            },
            child: const Text('Cerrar sesión'),
          ),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Stack(
      fit: StackFit.expand,
      children: [
        Image.asset(
          'assets/tennis_court_canva.png',
          fit: BoxFit.cover,
          // alignment: Alignment.bottomCenter,
        ),
        Builder(
          builder: (ctx) => Align(
            alignment: Alignment.bottomLeft,
            child: Padding(
              padding: const EdgeInsets.fromLTRB(4, 0, 0, 4),
              child: IconButton(
                icon: const Icon(Icons.menu, color: Colors.white),
                onPressed: () => Scaffold.of(ctx).openDrawer(),
              ),
            ),
          ),
        ),
        Align(
          alignment: Alignment.bottomLeft,
          child: Padding(
            padding: const EdgeInsets.fromLTRB(64, 0, 0, 14),
            child: Text(
              title,
              style: Theme.of(context).appBarTheme.titleTextStyle,
            ),
          ),
        ),
        if (showLogout)
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
    );
  }
}
