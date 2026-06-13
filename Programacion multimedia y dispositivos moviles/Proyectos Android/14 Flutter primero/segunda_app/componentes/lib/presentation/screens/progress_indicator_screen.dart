import 'package:flutter/material.dart';

class ProgressScreen extends StatelessWidget {
   
  const ProgressScreen({Key? key}) : super(key: key);
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Progress & Snackbar'),), 
      body: Center(
        child: Column(
          children: [
            SizedBox(height: 10,),
            CircularProgressIndicator(
              strokeWidth: 2,
              backgroundColor: Colors.black38,
            ),
            Text('Circular Progress Indicator'),
            SizedBox(height: 10,),
            LinearProgressIndicator(
              backgroundColor: Colors.black12,
            ),
            Text('Linear Progress Indicator'),
            SizedBox(height: 10,),
            _CircularControlado()
          ],
        )
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: (){
          mostrarSnackBar(context);
        },
        child: Icon(Icons.remove_red_eye_outlined),
        )
    );
  }
  
  void mostrarSnackBar(BuildContext context) {
    ScaffoldMessenger.of(context).clearSnackBars();
    final snackbar = SnackBar(
      content: Text('Hola a DAM2'),
      duration: Duration(seconds: 2),
      action: SnackBarAction(
        label: "OK", 
        onPressed: (){}
        ),
      );
    ScaffoldMessenger.of(context).showSnackBar(snackbar);
  }
}

class _CircularControlado extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        CircularProgressIndicator(
          value: 0.25,
          strokeWidth: 5,
          backgroundColor: Colors.black26,
        ),
        SizedBox(width: 20,),

        Expanded(child: LinearProgressIndicator(
          value: 0.25,
        ))
      ],
    );
  }
}