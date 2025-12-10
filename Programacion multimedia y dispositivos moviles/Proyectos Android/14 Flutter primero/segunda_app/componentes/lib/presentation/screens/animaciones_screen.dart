import 'dart:math';

import 'package:flutter/material.dart';

class AnimacionesScreen extends StatefulWidget {
   
  const AnimacionesScreen({Key? key}) : super(key: key);

  @override
  State<AnimacionesScreen> createState() => _AnimacionesScreenState();
}

class _AnimacionesScreenState extends State<AnimacionesScreen> {

  double _width = 50;
  double _height = 50;
  Color _color = Colors.indigo;
  BorderRadiusGeometry _borderRadius = BorderRadius.circular(8);

  void changeShape(){
    setState(() {
      final random = Random();
      _width = random.nextInt(300).toDouble() + 50;
      _height = random.nextInt(300).toDouble() + 50;
      _color = Color.fromRGBO(
        random.nextInt(255), 
        random.nextInt(255), 
        random.nextInt(255),
        1);
      _borderRadius = BorderRadius.circular(
        random.nextInt(100).toDouble(),
      );
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Animaciones'),), 
      body: Center(
        child: AnimatedContainer(
          duration: Duration(milliseconds: 1200),
          curve: Curves.elasticInOut,
          width: _width,
          height: _height,
          decoration: BoxDecoration(
            color: _color,
            borderRadius: _borderRadius
          ),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          changeShape();
        },
        child: Icon(Icons.play_circle_outline, size: 40,),
      ),
    );
  }
}

