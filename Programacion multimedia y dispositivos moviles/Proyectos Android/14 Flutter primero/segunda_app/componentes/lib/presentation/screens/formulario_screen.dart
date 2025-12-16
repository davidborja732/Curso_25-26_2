import 'package:componentes/presentation/widgets/text_form_personalizado.dart';
import 'package:flutter/material.dart';

class FormularioScreen extends StatelessWidget {
   
  const FormularioScreen({Key? key}) : super(key: key);
  
  @override
  Widget build(BuildContext context) {
    // Los keys me ayudan a mantener la referencia a los valores del formulario
    final formKey=GlobalKey<FormState>();
    final Map<String,String> formValues={
      'nombre' : 'David',
      'apellido' : 'Borja',
      'correo' : 'dborjam@iesch.org',
      'password' : '123456',
      'rol' : 'Admin',
    };
    return Scaffold(
      appBar: AppBar(title: Text('Formulario'),), 
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 10),
          child: Form(
            key: formKey,
            child: Column(
              children: [
                TextFormPersonalizado(
                  labelText: 'Nombre',
                  hintText: 'Nombre del usuario',
                  icon: Icons.person,
                  tipoteclado: TextInputType.name,
                  obscureText: false, 
                  formPropiedad: 'nombre', 
                  formValues: formValues,
                  
                ),
                SizedBox(height: 20,),
                TextFormPersonalizado(
                  labelText: 'Apellido',
                  hintText: 'Apellido del usuario',
                  icon: Icons.person_2,
                  tipoteclado: TextInputType.name,
                  obscureText: false,
                  formPropiedad: 'apellido', 
                  formValues: formValues,
                ),
                SizedBox(height: 20,),
                TextFormPersonalizado(
                  labelText: 'Correo',
                  hintText: 'Email del usuario',
                  icon: Icons.email,
                  tipoteclado: TextInputType.emailAddress,
                  obscureText: false,
                  formPropiedad: 'correo', 
                  formValues: formValues,
                ),
                SizedBox(height: 20,),
                TextFormPersonalizado(
                  labelText: 'Password',
                  hintText: 'Contraseña del usuario',
                  tipoteclado: TextInputType.name,
                  obscureText: false,
                  icon: Icons.lock,
                  formPropiedad: 'password', 
                  formValues: formValues,
                ),
                SizedBox(height: 20,),
                DropdownButtonFormField(
                  initialValue: 'Admin',
                  items: [
                    DropdownMenuItem(value: 'Admin',child: Text('Admin'),),
                    DropdownMenuItem(value: 'User',child: Text('User'),),
                  ], 
                  onChanged: (value){
                    formValues['rol']=value!;
                  },
                ),
                SizedBox(height: 30,),
                ElevatedButton(
                  onPressed: (){},
                  child: SizedBox(
                    width: double.infinity,
                    child: Center(child: Text('Guardar'),),
                  )
                )
              ],
            ),
          ),
        ),
      )
    );
  }
}

