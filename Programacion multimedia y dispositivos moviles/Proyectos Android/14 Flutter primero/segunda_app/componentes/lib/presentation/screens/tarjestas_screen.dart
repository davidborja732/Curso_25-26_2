import 'package:componentes/presentation/widgets/tarjeta_personalizada_1.dart';
import 'package:componentes/presentation/widgets/tarjeta_personalizada_2.dart';
import 'package:flutter/material.dart';

class TarjetasScreen extends StatelessWidget {
  const TarjetasScreen({super.key});

  @override
  Widget build(BuildContext context) {
    
    return Scaffold(
      appBar: AppBar(title: Text('Tarjetas'),),
      body: ListView(
        padding: EdgeInsets.all(6),
        children: [
          TarjetaPersonalizada1(),
          SizedBox(height: 10,),
          TarjetaPersonalizada2(urlImagen: 'https://thumbs.dreamstime.com/b/paisaje-id%C3%ADlico-del-verano-con-el-lago-claro-de-la-monta%C3%B1a-en-las-monta%C3%B1as-45054687.jpg', texto: 'Un Paisaje precioso',),
          SizedBox(height: 10,),
          TarjetaPersonalizada2(urlImagen: 'https://t4.ftcdn.net/jpg/01/80/53/89/360_F_180538953_V4zIpN1eHqU1Rmv3J4EHcD8hYIiF5t3R.jpg',),
          SizedBox(height: 10,),
          TarjetaPersonalizada2(urlImagen: 'https://fotoarte.com.uy/wp-content/uploads/2019/03/Landscape-fotoarte.jpg',),



        ],
      ),
    );
  }
}

