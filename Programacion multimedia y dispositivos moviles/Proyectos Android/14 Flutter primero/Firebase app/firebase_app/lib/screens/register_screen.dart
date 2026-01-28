import 'package:firebase_app/services/auth_service.dart';
import 'package:flutter/material.dart';

class RegisterScreen extends StatefulWidget {
  const RegisterScreen({super.key});

  @override
  State<RegisterScreen> createState() => _RegisterScreenState();
}

class _RegisterScreenState extends State<RegisterScreen> {
  
  String email = "";
  String pass = "";
  String nombre = "";

  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passController = TextEditingController();
  final TextEditingController _nombreController = TextEditingController();
  final _formkey = GlobalKey<FormState>();

  final _authService = AuthService();
  bool _isLoading = false;

  @override
  void dispose() {
    _nombreController.dispose();
    _emailController.dispose();
    _passController.dispose();
    super.dispose();
  }

  Future<void> _signUp() async {
    if (!_formkey.currentState!.validate()) return;

    setState(()=> _isLoading = true );

    try {
      _authService.registroConEmailYContrasena(
        email: _emailController.text.trim(), 
        password: _passController.text,
        );
      // Mostrar un mensaje de éxito
      if (mounted){
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: Text('Usuario creado correctamente!'),
            backgroundColor: Colors.green,
            )
        );
      }
      Navigator.pop(context);

    } catch (e) {
      // Mostramos mensaje al usuario
      if(mounted){
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: Text(e.toString()),
            backgroundColor: Colors.red,
            )
        );
      }
    } finally {
      if (mounted){
        setState(()=> _isLoading = false );
      }
    }
  }

  
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: SingleChildScrollView(
        child: Column(
          children: [
            SizedBox(
              width: MediaQuery.of(context).size.width,
              child: Image.asset("assets/car.PNG", fit: BoxFit.cover,),
            ),
            SizedBox(height: 30,),
            Padding(
              padding: EdgeInsets.only(left: 20, right: 20),
              child: Form(
                key: _formkey,
                child: Column(
                  children: [
                    Container(
                      padding: EdgeInsets.symmetric(
                        vertical: 2.0,
                        horizontal: 30.0,
                      ),
                      decoration: BoxDecoration(
                        color: Color(0xFFEDf0f8),
                        borderRadius: BorderRadius.circular(30)
                      ),
                      child: TextFormField(
                        controller: _nombreController,
                        decoration: InputDecoration(
                          border: InputBorder.none,
                          hintText: "Nombre",
                          hintStyle: TextStyle(
                            color: Color(0xFFB2B7BF),
                            fontSize: 18,
                          )
                        ),
                      ),
                    ),
                    SizedBox(height: 30,),
                    Container(
                      padding: EdgeInsets.symmetric(
                        vertical: 2.0,
                        horizontal: 30.0,
                      ),
                      decoration: BoxDecoration(
                        color: Color(0xFFEDf0f8),
                        borderRadius: BorderRadius.circular(30)
                      ),
                      child: TextFormField(
                        controller: _emailController,
                        decoration: InputDecoration(
                          border: InputBorder.none,
                          hintText: "Email",
                          hintStyle: TextStyle(
                            color: Color(0xFFB2B7BF),
                            fontSize: 18,
                          )
                        ),
                        validator: (value) {
                          if ( value == null || value.isEmpty ){
                            return 'Por favor  ingresa un correo';
                          }
                          if (!value.contains('@')) {
                            return 'Ingresa un email válido';
                          }
                          return null;
                        },
                      ),
                    ),
                    SizedBox(height: 30,),
                    Container(
                      padding: EdgeInsets.symmetric(
                        vertical: 2.0,
                        horizontal: 30.0,
                      ),
                      decoration: BoxDecoration(
                        color: Color(0xFFEDf0f8),
                        borderRadius: BorderRadius.circular(30)
                      ),
                      child: TextFormField(
                        obscureText: true,
                        controller: _passController,
                        decoration: InputDecoration(
                          border: InputBorder.none,
                          hintText: "Password",
                          hintStyle: TextStyle(
                            color: Color(0xFFB2B7BF),
                            fontSize: 18,
                          )
                        ),
                        validator: (value) {
                          if ( value == null || value.isEmpty ){
                            return 'Por favor ingresa una contraseña';
                          }
                          if (value.length < 6) {
                            return 'La contraseña debe tener al menos 6 caracteres';
                          }
                          // Validaciones adicionales opcionales
                          /*
                          if (!value.contains(RegExp(r'[A-Z]'))) {
                            return 'Debe contener al menos una mayúscula';
                          }
                          if (!value.contains(RegExp(r'[0-9]'))) {
                            return 'Debe contener al menos un número';
                          }
                          */
                          return null;
                        },
                      ),
                    ),
                    SizedBox(height: 30,),
                    GestureDetector(
                      onTap: _isLoading ? null : _signUp,
                      child: _isLoading 
                        ? SizedBox(
                          height: 20,
                          width: 20,
                          child: CircularProgressIndicator(),
                        ) 
                        : Container(
                        width: MediaQuery.of(context).size.width,
                        padding: EdgeInsets.symmetric(
                          vertical: 13,
                          horizontal: 30
                        ),
                        decoration: BoxDecoration(
                          color: Color(0xFF273671),
                          borderRadius: BorderRadius.circular(30)
                        ),
                        child: Center(
                          child: Text(
                            "Registrar Usuario",
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 20,
                              fontWeight: .w500
                            ),
                          ),
                        ),
                      ),
                    ),
                    SizedBox(height: 20,),

                    
                    SizedBox(height: 30,),
                    Row(
                      mainAxisAlignment: .center,
                      children: [
                        Text('¿Ya tienes cuenta?',
                            style: TextStyle(
                              color: Color(0xFF8c8e98),
                              fontSize: 16,
                              fontWeight: .w500, 
                            ),
                        ),
                        SizedBox(width: 5,),
                        GestureDetector(
                          onTap: () {
                            Navigator.pop(context);
                          },
                          child: Text('Ir a Login',
                              style: TextStyle(
                                color: Color(0xFF273671),
                                fontSize: 18,
                                fontWeight: .w500, 
                              ),
                          ),
                        ),
                      ],
                    )
                  ],
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}