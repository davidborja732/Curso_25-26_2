import 'package:shared_preferences/shared_preferences.dart';

class Preferencias {
  static late SharedPreferences _preferencias;
  // creo las propiedade globales a manejar
  static String _nombre=' ';
  static bool _isDarkMode=false;
  static bool _camaraAccess=false;
  static bool _userLocation=false;
  static Future init() async{
    _preferencias= await SharedPreferences.getInstance();
  }
  //Me creo los metodos que devuelven los valores
  static get nombre{
    return _preferencias.getString('nombre')??_nombre;
  }
  static set nombre(String nombre){
    _nombre=nombre;
  }
  static get isDarkMode{
    return _preferencias.getBool('isDark')??_isDarkMode;
  }
  static set isDarkMode(bool darkmode){
    _isDarkMode=darkmode;
    _preferencias.setBool('modo_oscuro', darkmode);
  }
  static get camaraAccess{
    return _preferencias.getBool('camara')??_camaraAccess;
  }
static set camaraAccess(bool camarapermiso){
    _camaraAccess=camarapermiso;
    _preferencias.setBool('camara', camarapermiso);
  }
  static get userLocation{
    return _preferencias.getBool('ubicacion')??_isDarkMode;
  }
  static set userLocation(bool userLocationPermision){
    _userLocation=userLocationPermision;
    _preferencias.setBool('ubicacion', userLocationPermision);
  }
}