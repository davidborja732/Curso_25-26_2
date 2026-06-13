import 'package:flutter/material.dart';

class TarjetaPersonalizada2 extends StatelessWidget {

  final String urlImagen;
  final String? texto;
  
  const TarjetaPersonalizada2({
    super.key, 
    required this.urlImagen, 
    this.texto
    });



  @override
  Widget build(BuildContext context) {
    return Card(
      clipBehavior: Clip.antiAlias, // para que la imagen no se salga de la tarjeta
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(15)),
      elevation: 10,
      child: Column(
        children: [
          FadeInImage(
              image: NetworkImage(urlImagen),
              //image: NetworkImage('https://thumbs.dreamstime.com/b/paisaje-id%C3%ADlico-del-verano-con-el-lago-claro-de-la-monta%C3%B1a-en-las-monta%C3%B1as-45054687.jpg'),
              fit: BoxFit.cover, 
              placeholder: AssetImage('assets/jar-loading.gif'),
              width: double.infinity,
              height: 230,
              fadeInDuration: Duration(milliseconds: 400),
          ),
          if ( texto != null ) 
          Container(
            alignment: AlignmentDirectional.centerEnd,
            padding: EdgeInsets.only(right: 20, top: 10, bottom: 10),
            child: Text(texto!),
            )
        ],
      ),
    );
  }
}