import 'package:firebase_app/services/auth_service.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  String email = "";
  String pass = "";

  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passController = TextEditingController();
  final _formkey = GlobalKey<FormState>();

  final _authService = AuthService();
  bool _isLoading = false;

  @override
  void dispose() {
    _emailController.dispose();
    _passController.dispose();
    super.dispose();
  }

  Future<void> _signIn() async {
    if (!_formkey.currentState!.validate()) return;
    setState(() => _isLoading = true);

    try {
      _authService.iniciarSesion(
        email: _emailController.text.trim(),
        password: _passController.text,
      );
      // No necesitamos navegar manualmente, el Streambuilder lo hace automaticamente
    } catch (e) {
      // Mostramos mensaje al usuario
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text(e.toString()), backgroundColor: Colors.red),
        );
      }
    } finally {
      if (mounted) {
        setState(() => _isLoading = false);
      }
    }
  }
  Future <void> _loguoConGoogle() async {
    setState(()=>_isLoading= false);
    try {
      final UserCredential = await _authService.loginConGoogle();
      if (UserCredential !=null){
        print('Logueo correcto');
      }
    }catch (e){
      throw FirebaseAuthException(code: 'Error al log');
    }finally{
      setState(()=>_isLoading= true);
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
              child: Image.asset("assets/car.PNG", fit: BoxFit.cover),
            ),
            SizedBox(height: 30),
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
                        borderRadius: BorderRadius.circular(30),
                      ),
                      child: TextFormField(
                        controller: _emailController,
                        decoration: InputDecoration(
                          border: InputBorder.none,
                          hintText: "Email",
                          hintStyle: TextStyle(
                            color: Color(0xFFB2B7BF),
                            fontSize: 18,
                          ),
                        ),
                        validator: (value) {
                          if (value == null || value.isEmpty) {
                            return 'Por favor  ingresa un correo';
                          }
                          if (!value.contains('@')) {
                            return 'Ingresa un email válido';
                          }
                          return null;
                        },
                      ),
                    ),
                    SizedBox(height: 30),
                    Container(
                      padding: EdgeInsets.symmetric(
                        vertical: 2.0,
                        horizontal: 30.0,
                      ),
                      decoration: BoxDecoration(
                        color: Color(0xFFEDf0f8),
                        borderRadius: BorderRadius.circular(30),
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
                          ),
                        ),
                        validator: (value) {
                          if (value == null || value.isEmpty) {
                            return 'Por favor ingresa una contraseña';
                          }
                          if (value.length < 6) {
                            return 'La contraseña debe tener al menos 6 caracteres';
                          }
                          return null;
                        },
                      ),
                    ),
                    SizedBox(height: 30),
                    GestureDetector(
                      onTap: _isLoading ? null : _signIn,
                      child: Container(
                        width: MediaQuery.of(context).size.width,
                        padding: EdgeInsets.symmetric(
                          vertical: 13,
                          horizontal: 30,
                        ),
                        decoration: BoxDecoration(
                          color: Color(0xFF273671),
                          borderRadius: BorderRadius.circular(30),
                        ),
                        child: Center(
                          child: Text(
                            "Login con Firebase",
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 20,
                              fontWeight: .w500,
                            ),
                          ),
                        ),
                      ),
                    ),
                    SizedBox(height: 20),
                    GestureDetector(
                      onTap: () {},
                      child: Text(
                        '¿Contraseña olvidada?',
                        style: TextStyle(
                          color: Color(0xFF8c8e98),
                          fontSize: 18,
                          fontWeight: .w500,
                        ),
                      ),
                    ),
                    SizedBox(height: 40),
                    Text(
                      'O Logueate con:',
                      style: TextStyle(
                        color: Color(0xFF273671),
                        fontSize: 20,
                        fontWeight: .w500,
                      ),
                    ),
                    SizedBox(height: 30),
                    Row(
                      mainAxisAlignment: .center,
                      children: [
                        GestureDetector(
                          onTap: _isLoading ? null : _loguoConGoogle,
                          child: Image.asset(
                            'assets/google.png',
                            height: 45,
                            width: 45,
                            fit: BoxFit.cover,
                          ),
                        ),
                        SizedBox(width: 30),
                        GestureDetector(
                          child: Image.asset(
                            'assets/apple1.png',
                            height: 50,
                            width: 50,
                            fit: BoxFit.cover,
                          ),
                        ),
                      ],
                    ),
                    SizedBox(height: 30),
                    Row(
                      mainAxisAlignment: .center,
                      children: [
                        Text(
                          '¿Aún no tienes cuenta?',
                          style: TextStyle(
                            color: Color(0xFF8c8e98),
                            fontSize: 16,
                            fontWeight: .w500,
                          ),
                        ),
                        SizedBox(width: 5),
                        GestureDetector(
                          onTap: () {
                            Navigator.pushNamed(context, '/register');
                          },
                          child: Text(
                            'Registrar Usuario',
                            style: TextStyle(
                              color: Color(0xFF273671),
                              fontSize: 18,
                              fontWeight: .w500,
                            ),
                          ),
                        ),
                      ],
                    ),
                    SizedBox(height: 30),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
