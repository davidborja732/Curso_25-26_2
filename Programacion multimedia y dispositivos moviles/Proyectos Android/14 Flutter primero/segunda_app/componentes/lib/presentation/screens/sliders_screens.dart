import 'package:flutter/material.dart';

class SlidersScreens extends StatefulWidget {
  const SlidersScreens({super.key});
  @override
  State<SlidersScreens> createState() => _SlidersScreensState();
}
class _SlidersScreensState extends State<SlidersScreens> {
  bool _checkvalue=true;
  double valor=50;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Sliders y checks"),),
      body: Column(
        children: [
          Slider.adaptive(
            min: 0,
            max: 400,
            value: valor, 
            onChanged: _checkvalue ?(value){
              setState(() {
              valor=value;
              });
            }:null,
          ),
          /*Checkbox.adaptive(
            value: _checkvalue, 
            onChanged: ( value ){
              setState(() {
                _checkvalue=value!;
              });
            }
          ),*/
          CheckboxListTile(
            title: Text("Habilitar Slider"),
            value: _checkvalue, 
            onChanged: ( value ){
              setState(() {
                _checkvalue=value!;
              });
            }
          ),
          SwitchListTile(
            subtitle: Text("Habilitar slider para garandar imagen"),
            title: Text("Habilitar Slider"),
            value: _checkvalue, 
            onChanged: ( value ){
              setState(() {
                _checkvalue=value;
              });
            }
          ),
          Expanded(
            child: SingleChildScrollView(
              child: Image(
                image: NetworkImage('https://cdn.pixabay.com/photo/2020/07/06/17/51/batman-5377804_1280.png'),
                fit:BoxFit.contain,
                width: valor,
              ),
            ),
          ),
          SizedBox(height: 50,)    
        ]
      )
    );
  }
}

