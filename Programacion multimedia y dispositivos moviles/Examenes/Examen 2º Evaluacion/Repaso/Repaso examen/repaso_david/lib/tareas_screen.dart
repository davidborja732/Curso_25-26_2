import 'package:flutter/material.dart';

class TareasScreen extends StatefulWidget {
  const TareasScreen({super.key});

  @override
  State<TareasScreen> createState() => _TareasScreenState();
}

class _TareasScreenState extends State<TareasScreen> {
  final List<String> entries = List.generate(10, (i) => 'Item $i');
  final List<int> colorCodes = List.generate(10, (i) => 100 * (i + 1));

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Tareas'), centerTitle: true),
      body: Column(
        children: [
          const SizedBox(height: 20),
          const Text('Este es mi título', style: TextStyle(fontSize: 20)),
          const SizedBox(height: 20),

          // 👇 Esto permite que el ListView tenga espacio
          Expanded(
            child: ListView.builder(
              padding: const EdgeInsets.all(8),
              itemCount: entries.length,
              itemBuilder: (context, index) {
                return Container(
                  height: 50,
                  color: Colors.amber[colorCodes[index] % 900],
                  child: Center(child: Text('Entry ${entries[index]}')),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
