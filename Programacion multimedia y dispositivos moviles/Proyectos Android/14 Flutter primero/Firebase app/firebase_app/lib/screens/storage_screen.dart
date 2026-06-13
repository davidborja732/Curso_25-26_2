
import 'dart:io';

import 'package:file_picker/file_picker.dart';
import 'package:firebase_storage/firebase_storage.dart';
import 'package:flutter/material.dart';

class StorageScreen extends StatefulWidget {
  const StorageScreen({Key? key}) : super(key: key);

  @override
  State<StorageScreen> createState() => _StorageScreenState();
}

class _StorageScreenState extends State<StorageScreen> {

  PlatformFile? selectedFile;
  UploadTask? uploadTask;
  Future _subirArchivo() async {
    if (selectedFile==null){
      return;
    }
    final path='dam2/${selectedFile!.name}';
    final file=File(selectedFile!.path!);
    final ref=FirebaseStorage.instance.ref().child(path);
    uploadTask=ref.putFile(file);
    //Puedo esperar a que la tarea se complete
    // ignore: unused_local_variable
    final snapshot=await uploadTask!.whenComplete(
      () {
        
      },
    );
    final downloadUrl = await ref.getDownloadURL();
    print('Archivo subido correctamente a $downloadUrl');
  }

  Future _seleccionarArchivo() async {
    final result = await FilePicker.platform.pickFiles();
    if ( result == null ) return;
    setState(() {
      selectedFile = result.files.first;
    });
  }



  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Subir Archivo'),), 
      body: Center(
        child:Column(
          mainAxisAlignment: .center,
          children: [

            if ( selectedFile != null ) Container(
              height: 350,
              color: Colors.blue[100],
              //child: Text('Archivo seleccionado: ${selectedFile!.name}')
              child: Image.file( 
                File(selectedFile!.path!),
                fit: .cover,
                width: double.infinity,
              ),
            ) else 
            Icon(Icons.cloud_upload, size: 100, color: Colors.blue,),


            SizedBox(height: 24,),
            Text('Aquí puedes subir archivos a la nube', style: TextStyle(fontSize: 18),),




            SizedBox(height: 24,),
            ElevatedButton(onPressed: _seleccionarArchivo,
            child: Text('Seleccionar archivo')),
            SizedBox(height: 24,),
            ElevatedButton(onPressed: _subirArchivo,
            child: Text('Subir archivo')),
          ],
        )
      ),
    );
  }
}