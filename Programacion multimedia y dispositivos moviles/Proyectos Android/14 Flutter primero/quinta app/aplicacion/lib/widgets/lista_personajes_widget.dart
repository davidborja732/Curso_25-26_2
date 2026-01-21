import 'package:aplicacion/screens/personaje_detalle_screen.dart';
import 'package:flutter/material.dart';

class ListaPersonajesWidget extends StatefulWidget {
  const ListaPersonajesWidget({super.key});

  @override
  State<ListaPersonajesWidget> createState() => _ListaPersonajesWidgetState();
}

class _ListaPersonajesWidgetState extends State<ListaPersonajesWidget> {
  final tituloStyleText = TextStyle(
    fontSize: 16,
    fontWeight: FontWeight.bold,
    color: Colors.white,
  );

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: ListView(
        padding: EdgeInsets.all(25),
        children: [
          Text('Personajes', style: tituloStyleText),
          Row(
            children: [
              _personajeLista('p1', 'Titulo', 'subtitulo'),
              SizedBox(width: 15),
              _personajeLista('p2', 'Titulo', 'subtitulo'),
              SizedBox(width: 15),
              _personajeLista('p3', 'Titulo', 'subtitulo'),
            ],
          ),
          Divider(color: Colors.grey,thickness: 1,),
          SizedBox(height: 15,),
          _bloquePersonajes("Brook",Colors.red,"o1"),
          _bloquePersonajes("Luffy",Colors.green,"o2"),
          _bloquePersonajes("Portgas D. Ace",Colors.blue,"o3"),
          _bloquePersonajes("Boa Hancok",Colors.yellow,"o4"),
          _bloquePersonajes("Boa Hancok",Colors.purple,"o5"),
          _bloquePersonajes("Roronoa Zero",Colors.white,"o6")
        ],
      ),
    );
  }

  Widget _bloquePersonajes(String nombre,Color color,String imagen){
  return GestureDetector(
    onTap: () => Navigator.of(context).push(
      MaterialPageRoute(builder: (context)=>PersonajeDetalleScreen(color: color, imagen: imagen, nombre: nombre,))
    ),
    child: Container(
      margin: EdgeInsets.only(bottom: 20),
      decoration: BoxDecoration(
        //color: Color.fromARGB(66, 168, 165, 165),
        borderRadius: BorderRadius.circular(10)
      ),
      height: 65,
      child: Row(
        mainAxisAlignment: .spaceBetween,
        children: [
          Row(
            children: [
              Container(
                decoration: BoxDecoration(
                  boxShadow: [
                    BoxShadow(color: color,blurRadius: 10,offset: Offset(0,5))
                  ]
                ),
                child: Image.asset("assets/$imagen.png"),
              ),
              SizedBox(height: 15,),
              Text(nombre,style: TextStyle(color: Colors.white70,fontSize: 16,),),
            ],
          ),
          IconButton(onPressed: (){}, icon: Icon(Icons.more_vert_outlined,color: Colors.grey,))
        ],
      ),
      
    )
  );
}
  Widget _personajeLista(String imagen, String titulo, String subtitulo) {

    double anchoPantalla = MediaQuery.of(context).size.width - 50;
    return Column(
      children: [
        ClipRRect(
          borderRadius: BorderRadius.circular(20),
          child : Hero(tag: imagen, child:Image.asset(
            "assets/$imagen.jpg",
            width: anchoPantalla * 0.3,
            height: 110,
            fit: BoxFit.cover,
          ),)
          
        ),
        SizedBox(height: 15),
        RichText(
          text: TextSpan(
            text: titulo,
            style: TextStyle(color: Colors.white70, fontSize: 14),
            children: [
              TextSpan(
                text: subtitulo,
                style: TextStyle(
                  color: Colors.white70,
                  fontSize: 12,
                  fontWeight: FontWeight.w300,
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }
}
