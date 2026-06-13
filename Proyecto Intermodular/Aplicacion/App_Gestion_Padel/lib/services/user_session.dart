import 'package:app_gestion_padel/model/socio.dart';

class UserSession {
  static final UserSession _instance = UserSession._internal();

  factory UserSession() {
    return _instance;
  }

  UserSession._internal();

  Socio? loggedInUser;
  bool isAdmin = false;

  void clear() {
    loggedInUser = null;
    isAdmin = false;
  }
}
