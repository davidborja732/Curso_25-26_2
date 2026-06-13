import 'package:flutter/material.dart';

class CounterScreen extends StatefulWidget {
  const CounterScreen({super.key});

  @override
  State<CounterScreen> createState() => _CounterScreenState();
}

class _CounterScreenState extends State<CounterScreen> {
  int contador=0;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Counter screen'),
        centerTitle: true,
      ),
        body: Center(
          child:Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(contador.toString(),style: TextStyle(fontSize: 160,fontWeight: FontWeight.w100),),
            Text('Clicks',style: TextStyle(fontSize: 25,)),
          ],
        )
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: (){
          setState(() {
            contador++;
          });},
        child: Icon(Icons.add),
        )
    );
  }
}
