import 'package:flutter/material.dart';

class Botonesscreen extends StatelessWidget {
  const Botonesscreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('botones'),
      ),
      body: _BotonesScreenView(),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.arrow_back_ios),
        onPressed: () {
          Navigator.pop(context);
        },
      ),
    );
  }
}

class _BotonesScreenView extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    // ignore: avoid_unnecessary_containers
    return Container(
      child: Padding(
        padding: EdgeInsets.symmetric(vertical: 10,horizontal: 5),
        child: Wrap( 
          spacing: 14,
          alignment: WrapAlignment.center,
          children: [
            ElevatedButton(onPressed: () { }, child: Text('Boton')),
            ElevatedButton.icon(onPressed: () { }, label: Text('Elevated Button Icon'),icon: Icon(Icons.access_alarm),),
            FilledButton(onPressed: () { }, child: Text('Filled Button')),
            FilledButton.icon(onPressed: () {}, label: Text('FilledButton Con icono'),icon: Icon(Icons.add_alarm),iconAlignment: IconAlignment.end,),
            OutlinedButton(onPressed: () {}, child: Text('OutlinedBoton'),),
            TextButton(onPressed: () {}, child: Text('TextButton')),
            IconButton(onPressed: () {}, icon: Icon(Icons.add_to_home_screen_rounded),alignment: Alignment.topCenter,color: Colors.deepPurple,iconSize: 45,),
          ],
        ),
      ),
    );
  }
}