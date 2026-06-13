import 'package:flutter/material.dart';

class HeaderWidget extends StatelessWidget {
  const HeaderWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(25),
      height: 140.0,
      decoration: BoxDecoration( color: Color.fromARGB(255, 41, 40, 39)),
      child: Row(
        children: [
          Text('Diseños',
          style: TextStyle(
            color: Colors.white,
            fontSize: 24,
            fontWeight: FontWeight.bold,
          ),
          )
        ],
      ),
    );
  }
}