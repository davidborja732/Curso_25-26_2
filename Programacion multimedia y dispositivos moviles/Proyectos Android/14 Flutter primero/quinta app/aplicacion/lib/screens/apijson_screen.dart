import 'package:flutter/material.dart';

class ApijsonScreen extends StatelessWidget {
  const ApijsonScreen({Key? key}) : super(key: key);
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Home'),),
      body: Center(
        child: Text('HomeScreen'),
      ),
    );
  }
}