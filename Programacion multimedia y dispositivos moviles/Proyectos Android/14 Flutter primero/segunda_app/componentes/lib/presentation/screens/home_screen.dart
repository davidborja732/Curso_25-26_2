import 'package:componentes/config/routes/menu_items.dart';
import 'package:flutter/material.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Home Screen'),
        ),
      body: ListView.builder(
        itemCount: menuItems.length,
        itemBuilder: (context, index) {
          final menuItem = menuItems[index];
          final colors = Theme.of(context).colorScheme;


          return ListTile(
            title: Text( menuItem.titulo ),
            subtitle: Text( menuItem.subtitulo ),
            leading: Icon( menuItem.icono, color: colors.primary, ),
            trailing: Icon( Icons.arrow_forward_ios, color: colors.primary,),
            onTap: () {
              // Navegar a otra pantalla
              /*
              Navigator.of(context).push(
                MaterialPageRoute(
                  builder: (context) => BotonesScreen(),
                  )
              );
              */
              Navigator.pushNamed(context, menuItem.link);
            },
            
          );
        },
        )
    );
  }
}