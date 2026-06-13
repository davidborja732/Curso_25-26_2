import 'package:aplicacion/config/menu/menu_items.dart';
import 'package:aplicacion/widgets/menu_item.dart';
import 'package:aplicacion/widgets/option_menu_item.dart';
import 'package:flutter/material.dart';

class MenuScreen extends StatelessWidget {
  const MenuScreen({super.key});
  
  @override
  Widget build(BuildContext context) {

    final List<OptionMenuItem> listaOpcionesMenu=MenuItems().listaOpcionesMenu;
    return Scaffold(
      //appBar: AppBar(title: Text('Menu'),),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: GridView.builder(
          itemCount: listaOpcionesMenu.length,
          //Griddelegate sirve para personalizar el comportamiento del grip
          gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            // Elementos en vertical
            crossAxisCount: 2,
            crossAxisSpacing: 8.0,
            mainAxisSpacing: 8.0,
          ), 
          itemBuilder: (context,index){
            final OptionMenuItem opcion=listaOpcionesMenu[index];
            return MenuItem(opcion: opcion);
            
          }
        ),
      ),
    );
  }
}