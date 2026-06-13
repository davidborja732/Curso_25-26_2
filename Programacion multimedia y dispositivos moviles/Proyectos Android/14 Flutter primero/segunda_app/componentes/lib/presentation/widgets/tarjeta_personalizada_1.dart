import 'package:flutter/material.dart';
class TarjetaPersonalizada1 extends StatelessWidget {
  const TarjetaPersonalizada1({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    final colors = Theme.of(context).colorScheme;
    return Card(   
      child: Column(
        children: [
          ListTile(
            leading: Icon( Icons.photo_album_outlined, color: colors.primary,),
            title: Text('Soy un título'),
            subtitle: Text('Ex aliqua dolore fugiat velit sit aliqua pariatur aute culpa. Elit magna mollit magna exercitation eu. Aute fugiat est exercitation labore mollit magna laboris cupidatat pariatur labore irure. Lorem magna cupidatat eiusmod amet deserunt do esse fugiat et id occaecat consequat. Proident dolore Lorem aliqua sint exercitation ipsum occaecat.'),
          ),
          Padding(
            padding: const EdgeInsets.only(right: 10),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: [
                TextButton(onPressed: (){}, child: Text('Cancelar'), style: TextButton.styleFrom(foregroundColor: colors.error),),
                TextButton(onPressed: (){}, child: Text('Ok')),
              ],
            ),
          )
        ],
      ),
    );
  }
}