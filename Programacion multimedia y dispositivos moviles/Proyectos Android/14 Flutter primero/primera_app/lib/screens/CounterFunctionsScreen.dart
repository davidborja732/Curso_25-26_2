import 'package:flutter/material.dart';

class CounterFunctionsScreen extends StatefulWidget {
  const CounterFunctionsScreen({super.key});

  @override
  State<CounterFunctionsScreen> createState() => _CounterFunctionsScreenState();
}

class _CounterFunctionsScreenState extends State<CounterFunctionsScreen> {
  int contador=0;
  String palabra=' ';
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        //leading: IconButton(onPressed: null, icon: Icon(Icons.supervised_user_circle)),
        title: Text('Counter screen'),
        centerTitle: true,
        actions: [
          IconButton(onPressed: null, icon: Icon(Icons.settings)),
          IconButton(onPressed: null, icon: Icon(Icons.supervised_user_circle)),
        ],
      ),
        body: Center(
          child:Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(contador.toString(),style: TextStyle(fontSize: 160,fontWeight: FontWeight.w100),),
            Text('Click${contador==1?'':'s'}',style: TextStyle(fontSize: 25,)),
          ],
        )
      ),
      floatingActionButton: Column(
        mainAxisAlignment: MainAxisAlignment.end,
        children: [
          botonpersonalizado(icono: Icons.plus_one,
          onPressed: () {
            setState(() {
              contador++;
            });
          },),
          SizedBox(height: 10,),
          botonpersonalizado(icono: Icons.refresh_rounded,
          onPressed: () {
            setState(() {
              contador=0;
            });
          },),
          SizedBox(height: 10,),
          botonpersonalizado(icono: Icons.exposure_minus_1,
          onPressed: () {
            setState(() {
              if (contador>0){
                contador--;
              }else{
                contador=0;
              }
            });
          },),
        ],
      )
      );
  }
}

class botonpersonalizado extends StatelessWidget {
  const botonpersonalizado({
    super.key, required this.icono, this.onPressed,
  });
  final IconData icono;
  final VoidCallback? onPressed;
  @override
  Widget build(BuildContext context) {
    return FloatingActionButton(
      isExtended: false,
      shape: BoxBorder.all(),
      enableFeedback: true,
      elevation: 5,
      backgroundColor: const Color.fromARGB(255, 34, 18, 177),
      child: Icon(icono),
        onPressed: onPressed,
      );
    }
  }
