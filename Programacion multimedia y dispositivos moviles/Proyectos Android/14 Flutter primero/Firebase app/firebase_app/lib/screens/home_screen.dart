import 'package:firebase_app/services/auth_service.dart';
import 'package:flutter/material.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final authService = AuthService();
    final user = authService.currenUser;

    return Scaffold(
      appBar: AppBar(
        title: Text('Inicio'),
        actions: [
          IconButton(
            onPressed: () async {
              // Mostrar un diálogo de confirmación
              final shouldLogout = await showDialog<bool>(
                context: context,
                builder: (context) => AlertDialog.adaptive(
                  title: Text('Cerrar Sesión'),
                  content: Text('¿Estás seguro que quieres cerrar la sesión?'),
                  actions: [
                    TextButton(
                      onPressed: () => Navigator.pop(context, false),
                      child: Text('Cancelar'),
                    ),
                    TextButton(
                      onPressed: () => Navigator.pop(context, true),
                      child: Text('Aceptar'),
                    ),
                  ],
                ),
              );

              if (shouldLogout == true) {
                await authService.cerrarSesion();
              }
            },
            icon: Icon(Icons.logout),
          ),
        ],
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: .center,
          children: [
            user?.photoURL!=null ? CircleAvatar(
              radius: 50,
              backgroundImage: NetworkImage(user!.photoURL!),
            ):
            Icon(Icons.check_circle_outline, size: 100, color: Colors.green),
            SizedBox(height: 24),
            if (user?.displayName!=null)
              Text(
              user!.displayName!,
              style:TextStyle(fontSize: 22,fontWeight: .w200)),
            Text(
              'Sesión Iniciada correctamente!',
              style: TextStyle(fontSize: 24, fontWeight: .bold),
            ),
            SizedBox(height: 16),
            Text('Email: ${user?.email}', style: TextStyle(fontSize: 16)),
            SizedBox(height: 8),
            Text(
              'UID: ${user?.uid}',
              style: TextStyle(fontSize: 12, color: Colors.grey),
            ),
            SizedBox(height: 24,),
            ElevatedButton(
              
              onPressed: () {Navigator.pushNamed(context, '/lista_tareas');}, 
              child: Text('Ir a tareas')
            )
          ],
        ),
      ),
    );
  }
}
