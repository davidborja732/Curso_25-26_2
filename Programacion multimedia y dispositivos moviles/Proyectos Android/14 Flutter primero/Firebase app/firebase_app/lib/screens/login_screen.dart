import 'package:flutter/material.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  String gmail = "";
  String pass = "";
  TextEditingController _emailController = TextEditingController();
  TextEditingController _passController = TextEditingController();
  final _formkey = GlobalKey<FormState>();

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
              padding: const EdgeInsets.only(left: 20.0, right: 20),
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
                        borderRadius: BorderRadius.circular(20),
                      ),
                      child: TextFormField(
                        controller: _emailController,
                        decoration: InputDecoration(
                          border: InputBorder.none,
                          hintText: 'Email',
                          hintStyle: TextStyle(
                            color: Color(0xFFB2B7BF),
                            fontSize: 18,
                          ),
                        ),
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
                        borderRadius: BorderRadius.circular(20),
                      ),
                      child: TextFormField(
                        obscureText: true,
                        controller: _passController,
                        decoration: InputDecoration(
                          border: InputBorder.none,
                          hintText: 'Contraseña',
                          hintStyle: TextStyle(
                            color: Color(0xFFB2B7BF),
                            fontSize: 18,
                          ),
                        ),
                      ),
                    ),
                    SizedBox(height: 30),
                    GestureDetector(
                      child: Container(
                        width: MediaQuery.of(context).size.width,
                        padding: EdgeInsets.symmetric(
                          vertical: 13,
                          horizontal: 30,
                        ),
                        decoration: BoxDecoration(
                          color: Color(0xFF273671),
                          borderRadius: BorderRadius.circular(15),
                        ),
                        child: Center(
                          child: Text(
                            "Login con firebase",
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 22,
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
                          color: Color(0xff8c8e98),
                          fontSize: 18,
                          fontWeight: .w500,
                        ),
                      ),
                    ),
                    SizedBox(height: 40),
                    Text(
                      'O Logueate con',
                      style: TextStyle(
                        color: Color(0xff8c8e98),
                        fontSize: 20,
                        fontWeight: .w500,
                      ),
                    ),
                    SizedBox(height: 30),
                    Row(
                      mainAxisAlignment: .center,
                      children: [
                        GestureDetector(
                          onTap: () {},
                          child: Image.asset(
                            'assets/google.png',
                            height: 45,
                            width: 45,
                            fit: BoxFit.cover,
                          ),
                        ),
                        SizedBox(width: 30),
                        Image.asset(
                          'assets/apple1.png',
                          height: 50,
                          width: 50,
                          fit: BoxFit.cover,
                        ),
                      ],
                    ),
                    SizedBox(height: 30),
                    Row(
                      mainAxisAlignment: .center,
                      children: [
                        Text(
                          'Aun no tienes cuenta',
                          style: TextStyle(
                            color: Color(0xff8c8e98),
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
                              color: Color(0xff273361),
                              fontSize: 16,
                              fontWeight: .w500,
                            ),
                          ),
                        ),
                      ],
                    ),
                    SizedBox(height: 20),
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
