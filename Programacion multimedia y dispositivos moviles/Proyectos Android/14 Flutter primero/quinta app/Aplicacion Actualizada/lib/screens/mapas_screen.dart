import 'package:flutter/material.dart';
import 'package:mapbox_maps_flutter/mapbox_maps_flutter.dart';

class MapasScreen extends StatelessWidget {
  const MapasScreen({Key? key}) : super(key: key);
  
  @override
  Widget build(BuildContext context) {
    Position plaza=Position(-1.107100491260428, 40.34278970692194);
    MapboxMap _mapboxMap;
    CameraOptions camara=CameraOptions(
      center: Point(
        coordinates: plaza
      ),
      zoom: 18.0,
      /*bearing: -161.81,
      pitch: 70.0*/
    );
    _onMapCreated(MapboxMap mapBoxMap){
      _mapboxMap=mapBoxMap;
      mapBoxMap.flyTo(
        camara,
        MapAnimationOptions(
          duration: 5000,
          startDelay: 2000
        )
      );
      // animacion
    }
    return Scaffold(
      appBar: AppBar(title: Text('Mapas'),),
      body: MapWidget(
        styleUri: MapboxStyles.STANDARD,
        //cameraOptions: camara,
        onMapCreated: _onMapCreated,
      )
    );
  }
}