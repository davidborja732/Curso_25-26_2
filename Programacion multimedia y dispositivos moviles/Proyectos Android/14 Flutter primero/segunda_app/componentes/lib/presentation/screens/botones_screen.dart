import 'package:flutter/material.dart';

class BotonesScreen extends StatelessWidget {
  const BotonesScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar( title: Text('Botones'),),
      body: _BotonesScreenView(),
      floatingActionButton: FloatingActionButton(
        child: Icon( Icons.arrow_back_ios),
        onPressed: (){
          Navigator.pop(context);
        },
        ),
    );
  }
}


class _BotonesScreenView extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      child: Padding(
        padding: EdgeInsetsGeometry.symmetric(vertical: 5, horizontal: 10),
        child: Wrap(
          spacing: 14,
          alignment: WrapAlignment.center,
          children: [
            ElevatedButton(
              onPressed: () {  }, 
              child: Text('ElevatedButton'),
              ),
            ElevatedButton.icon(
              onPressed: (){},
              label: Text('ElevatedButton Icon'),
              icon: Icon( Icons.access_alarm_outlined),
              ),
            FilledButton(
              onPressed: (){}, 
              child: Text('Filled Button')
              ),
            FilledButton.icon(
              iconAlignment: IconAlignment.end,
              onPressed: (){}, 
              label: Text('Filled Button'),
              icon: Icon( Icons.access_alarm),
              ),
            OutlinedButton(
            
              onPressed: (){}, 
              child: Text('Outlined Button')
              ),
            TextButton(
              onPressed: (){}, 
              child: Text('Text Button'),
              ),
            IconButton(
              onPressed: (){}, 
              icon: Icon( Icons.account_balance_wallet_sharp,),
              color: Colors.amber,
              iconSize: 50,
              )
          ],
        ),
      ),
    );
  }
}