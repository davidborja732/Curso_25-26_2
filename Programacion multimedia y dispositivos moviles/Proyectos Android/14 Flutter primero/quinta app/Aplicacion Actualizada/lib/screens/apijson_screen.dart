import 'package:aplicacion/services/jsonplaceholder_services.dart';
import 'package:flutter/material.dart';

class ApiJsonPlaceUsersScreen extends StatefulWidget {

  const ApiJsonPlaceUsersScreen({super.key});

  @override
  State<ApiJsonPlaceUsersScreen> createState() => _ApiJsonPlaceUsersScreenState();
}

class _ApiJsonPlaceUsersScreenState extends State<ApiJsonPlaceUsersScreen> {

  late Future<List<dynamic>> _futureUsers;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    //_futureUsers = JsonplaceholderAPIServices.fetchuserswithHttp();
    _futureUsers = JsonplaceholderAPIServices.fetchuserswithDio();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Usuarios API'),), 
      body: FutureBuilder(
        future: _futureUsers, 
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting){
            return Center(child: CircularProgressIndicator.adaptive(),);
          } else if (snapshot.hasError) {
            return Text('Error: ${snapshot.error}');
          } else {
            final usuarios = snapshot.data!;
            return ListView.builder(
              itemCount: usuarios.length,
              itemBuilder: (context, index) {
                final usuario = usuarios[index];
                return ListTile(
                  title: Text(usuario['name']),
                  subtitle: Text(usuario['address']['street']),
                );
              },
              );
          }
        },
        )

    );
  }
}