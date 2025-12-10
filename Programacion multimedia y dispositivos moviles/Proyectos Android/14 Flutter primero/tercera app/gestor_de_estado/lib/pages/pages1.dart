import 'package:flutter/material.dart';

class Pages1 extends StatefulWidget {
  const Pages1({super.key});

  @override
  State<Pages1> createState() => _Pages1State();
}

class _Pages1State extends State<Pages1> {
  int _contador = 0;
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        children: [
          SizedBox(height: 20),
          Text(_contador.toString(), style: TextStyle(fontSize: 120)),
          ElevatedButton(
            onPressed: () {
              setState(() {
                _contador++;
              });
            },
            child: Text('Incrementar'),
          ),
        ],
      ),
    );
  }
}
