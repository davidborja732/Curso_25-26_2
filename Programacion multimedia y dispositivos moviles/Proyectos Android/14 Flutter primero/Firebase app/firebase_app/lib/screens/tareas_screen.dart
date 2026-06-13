import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_app/services/auth_service.dart';
import 'package:flutter/material.dart';

class TareasScreen extends StatefulWidget {
  const TareasScreen({super.key});

  @override
  State<TareasScreen> createState() => _TareasScreenState();
}

class _TareasScreenState extends State<TareasScreen> {
  final Stream<QuerySnapshot> _tareasStream = FirebaseFirestore.instance
      .collection('tareas')
      .snapshots();
  @override
  Widget build(BuildContext context) {
    final authService = AuthService();
    // ignore: unused_local_variable
    final user = authService.currenUser;

    return Scaffold(
      appBar: AppBar(
        title: Text('Lista de tareas'),
        actions: [
          IconButton(
            onPressed: () async {
              // Mostrar un diálogo de confirmación
              final shouldLogout = await showDialog<bool>(
                context: context,
                builder: (context) => AlertDialog.adaptive(
                  title: Text('Cerrar Sesión'),
                  content: Text('¿Estás seguro que quieres cerrar la sesión?'),
                  actions: [
                    TextButton(
                      onPressed: () => Navigator.pop(context, false),
                      child: Text('Cancelar'),
                    ),
                    TextButton(
                      onPressed: () => Navigator.pop(context, true),
                      child: Text('Aceptar'),
                    ),
                  ],
                ),
              );

              if (shouldLogout == true) {
                await authService.cerrarSesion();
              }
            },
            icon: Icon(Icons.logout),
          ),
        ],
      ),
      body: StreamBuilder<QuerySnapshot>(
        stream: _tareasStream,
        builder: (BuildContext context, AsyncSnapshot<QuerySnapshot> snapshot) {
          if (snapshot.hasError) {
            return Text('Error al descargar los datos');
          }

          if (snapshot.connectionState == ConnectionState.waiting) {
            return Text("Loading");
          }

          return ListView(
            children: snapshot.data!.docs.map((DocumentSnapshot document) {
              Map<String, dynamic> data =
                  document.data()! as Map<String, dynamic>;
              String docId=document.id;
              return ListTile(
                title: Text(data['titulo']),
                subtitle: Text(data['descripcion']),
                trailing: Row(
                  mainAxisSize: .min,
                  children: [
                    IconButton(
                      onPressed: () {
                        Navigator.pushNamed(
                          context, '/add_tareas',
                          arguments: {
                            'id': docId,
                            'titulo': data['titulo'],
                            'descripcion':data['descripcion']
                          }
                        );
                      }, 
                      icon: Icon(Icons.edit,color: Colors.blue,)
                    ),
                    IconButton(
                      onPressed: () async{
                        final showDelete=await showDialog(
                          context: context, 
                          builder: (context) => AlertDialog.adaptive(
                            title: Text("¿Quieres eliminar esta tarea?"),
                            actions: [
                              TextButton(onPressed: () => Navigator.pop(context,false), child: Text('Cancelar')),
                              TextButton(onPressed: () => Navigator.pop(context,true), child: Text('Eliminar'))
                            ],
                          ),
                        );
                        if (showDelete==true){
                          //Eliminamos la tarea de firestore
                          await FirebaseFirestore.instance.collection('tareas').doc(docId).delete();
                        }
                      }, 
                      icon: Icon(Icons.delete,color: Colors.red,)
                    )
                  ],
                ),
              );
            }).toList(),
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.pushNamed(context, '/add_tareas');
        },
        child: Icon(Icons.add),
      ),
    );
  }
}
