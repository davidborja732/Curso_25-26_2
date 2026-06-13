import 'package:aplicacion/preferences/preferencias.dart';
import 'package:flutter/material.dart';

class SettingsScreen extends StatefulWidget {
  const SettingsScreen({Key? key}) : super(key: key);

  @override
  State<SettingsScreen> createState() => _SettingsScreenState();
}
bool _isDarkMode=false;
bool _userLocation=false;
bool _camaraAccess=false;
String nombre="David";
class _SettingsScreenState extends State<SettingsScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Preferencias'),),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Column(
            children: [
              CheckboxListTile.adaptive(
                title: Text('Permiso de ubicacion'),
                value: Preferencias.userLocation, 
                onChanged: (value) {
                  setState(() {
                    Preferencias.userLocation=value!;
                  });
                },
              ),
              SwitchListTile.adaptive(
                value: Preferencias.isDarkMode, 
                onChanged: (value) {
                  setState(() {
                    Preferencias.isDarkMode=value;
                  });
                },
                title: Text('Darkmode'),
              ),
              SwitchListTile.adaptive(
                value: Preferencias.camaraAccess, 
                onChanged: (value) {
                  setState(() {
                    Preferencias.camaraAccess=value;
                  });
                },
                title: Text('Permiso camara'),
              ),
              Divider(color: Colors.red,),
              TextFormField(
                  initialValue: Preferencias.nombre,
                  decoration: InputDecoration(
                    labelText: 'Nombre',
                    helperText: 'Nombre del usuario'
                  ),
                  onChanged: (value) {
                  setState(() {
                    Preferencias.nombre=value;
                  });
                },
                ),
            ],
          ),
        ),
      )
    );
  }
}