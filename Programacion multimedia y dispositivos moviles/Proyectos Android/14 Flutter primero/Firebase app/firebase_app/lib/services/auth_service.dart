import 'package:firebase_auth/firebase_auth.dart';

class AuthService {
  // Instancia de firebas OUTH
  final FirebaseAuth _auth=FirebaseAuth.instance;
  // Me vreo un Stream para que emita cambios en el estado de autenticacion
  Stream<User?> get authStateChanges => _auth.authStateChanges();
  // Para obtner el usuario actual
  User? get currenUser => _auth.currentUser;

  // Registro con Email y Contraseña
  Future<UserCredential?> registroConEmailYContrasena({
    required String email,
    required String password,
  }) async {
    try{
      UserCredential userCredential=await _auth.createUserWithEmailAndPassword(email: email, password: password);
      return userCredential;
    }on FirebaseAuthException catch(e){
      //Manejo de errores especificos
      if (e.code=='email-already-in-use'){
        throw Exception('Este email ya esta registrado: ${e.message}');
      }else if (e.code=='invalid-email'){
        throw Exception('Este email no es valido: ${e.message}');
      }
      throw Exception('Error al registrar usuario: ${e.message}');
    }catch (e){
      throw Exception('Error general: $e');
    }
  }
  // Iniciar sesion con email y contraseña
  Future<UserCredential?> iniciarSesion({
    required String email,
    required String password,
  }) async {
    try{
      UserCredential userCredential=await _auth.signInWithEmailAndPassword(email: email, password: password);
      return userCredential;
    }on FirebaseAuthException catch(e){
      //Manejo de errores especificos
      if (e.code=='user-not-found'){
        throw Exception('Usuario no encontrado: ${e.message}');
      }else if (e.code=='wrong-password'){
        throw Exception('Contraseña incorrecta: ${e.message}');
      }
      throw Exception('Error al loguear usuario: ${e.message}');
    }catch (e){
      throw Exception('Error general: $e');
    }
  }
  // cerrar sesion
  Future<void> cerrarSesion() async{
    try{
      await _auth.signOut();
    }catch (e){
      throw Exception('Error al cerrar la sesion: $e');
    }
  }
}