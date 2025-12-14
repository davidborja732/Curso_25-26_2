import 'package:flutter/material.dart';

class TarjetaPersonalizada1 extends StatelessWidget {
  const TarjetaPersonalizada1( {
    super.key,
  });


  @override
  Widget build(BuildContext context) {
    final colors=Theme.of(context).colorScheme;
    return Card(
      child: Column(
        children: [
          ListTile(
            title: Text('Soy un titulo'),
            subtitle: Text('Lorem ipsum dolor sit amet consectetur adipiscing, elit vulputate nostra natoque urna. Imperdiet suscipit tellus potenti dis facilisis augue tristique, sem luctus nunc pretium mi primis cubilia, magna ut aliquam feugiat dignissim lacus. Curabitur taciti nunc volutpat ad facilisi scelerisque faucibus, auctor gravida augue sapien purus leo erat cras, nostra sollicitudin vestibulum justo sem tincidunt.Quis etiam pulvinar leo ultrices aliquet sodales habitant dis facilisis cubilia montes dictumst venenatis, potenti odio in vestibulum vulputate eget molestie class duis morbi euismod. Etiam leo mollis fermentum nibh morbi suspendisse sollicitudin feugiat conubia id, torquent lacinia urna felis eu himenaeos platea arcu odio purus, justo massa accumsan condimentum proin vulputate eget magnis integer. Fusce lacus convallis nisi eu placerat quisque dignissim gravida nec litora tempor interdum tortor vitae, himenaeos morbi venenatis nibh nascetur ac quam est a sed lectus laoreet odio.'),
            leading: Icon(Icons.photo_camera,color:colors.primary,),
            textColor: Colors.red,
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.end,
            children: [
              TextButton(onPressed: () {}, child: Text('Cancelar'),style: TextButton.styleFrom(foregroundColor:Colors.red),),
              Padding(
                padding: const EdgeInsets.only(right: 40),
                child: TextButton(onPressed: () {}, child: Text('ok'),style: TextButton.styleFrom(foregroundColor:Colors.green),),
              ),
            ],
          )
        ],
      ),
    );
  }
}
class TarjetaPersonalizada2 extends StatelessWidget {
  const TarjetaPersonalizada2( {
    super.key,
  });


  @override
  Widget build(BuildContext context) {
    // ignore: unused_local_variable
    final colors=Theme.of(context).colorScheme;
    return Card(
      child: Column(
        children: [
          FadeInImage(image: NetworkImage('https://fotoarte.com.uy/wp-content/uploads/2019/03/Landscape-fotoarte.jpg'),fit: BoxFit.cover,placeholder: AssetImage('assets/cargando.gif'),),
          
        ],
      ),
    );
  }
}