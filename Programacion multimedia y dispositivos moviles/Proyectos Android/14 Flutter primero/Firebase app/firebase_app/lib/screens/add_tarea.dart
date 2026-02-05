import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';

class AddTareaScreen extends StatefulWidget {
  const AddTareaScreen({Key? key}) : super(key: key);

  @override
  State<AddTareaScreen> createState() => _AddTareaScreenState();
}

class _AddTareaScreenState extends State<AddTareaScreen> {
  final _titleController = TextEditingController();
  final _descriptionController = TextEditingController();
  DateTime selectedDate = DateTime.now();
  String? _tareaId;
  bool _isEditing = false;

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();

    // Miramos a ver si es Una tarea nueva o Editar alguna que ya tuviéramos
    final args = ModalRoute.of(context)?.settings.arguments as Map<String, dynamic>?;

    if ( args != null && args['id'] != null ){
      // Estamos editando una tarea
      _isEditing = true;
      _tareaId = args['id'];
      _titleController.text = args['titulo'] ?? '';
      _descriptionController.text = args['descripcion'] ?? '';
    }
  }

  @override
  void dispose() {
    _titleController.dispose();
    _descriptionController.dispose();
    super.dispose();
  }

  Future _guardarEditarTarea() async {

    if ( _titleController.text.isEmpty || _descriptionController.text.isEmpty ){
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Por favor, completa todos los campos.'))
      );
      return;
    }

    try {
      // Comprobamos si estamos EDITANDO
      if ( _isEditing ){
        // Actualizamos la tarea existente
        await FirebaseFirestore.instance
        .collection('tareas')
        .doc(_tareaId)
        .update({
            'titulo' : _titleController.text,
            'descripcion' : _descriptionController.text,
            'ult_mod' : DateTime.now()
        });
         // Mostramos unmensaje de exito
          ScaffoldMessenger.of(context).showSnackBar(
            SnackBar(content: Text('Tarea Modificada correctamente'))
          );
        
      } else {
        // Añadimos una tarea NUEVA
        await FirebaseFirestore.instance
        .collection('tareas')
        .add({
            'titulo' : _titleController.text,
            'descripcion' : _descriptionController.text,
            'fecha_creacion' : DateTime.now()
        });

         // Mostramos unmensaje de exito
          ScaffoldMessenger.of(context).showSnackBar(
            SnackBar(content: Text('Tarea Añadida correctamente'))
          );
      }

      // Volvemos a la lista de tareas
      Navigator.pop(context);


    } catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Error al MODIFICAR tarea: $e'))
        );
    }
  }
















  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text( _isEditing ? 'Editar tarea' : 'Añadir tarea' )),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Column(
            children: [
              SizedBox(height: 20),
              TextFormField(
                controller: _titleController,
                decoration: InputDecoration(hintText: 'Título'),
              ),
              SizedBox(height: 20),
              TextFormField(
                controller: _descriptionController,
                decoration: InputDecoration(hintText: 'Descripción'),
                maxLines: 3,
              ),
              SizedBox(height: 20),
              ElevatedButton(
                onPressed: _guardarEditarTarea, 
                child: Text(_isEditing ? 'Editar tarea' : 'Añadir tarea',
                style: TextStyle(fontSize: 16, color: Colors.purple),)),
            ],
          ),
        ),
      ),
    );
  }
}
